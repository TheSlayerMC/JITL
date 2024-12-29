package net.jitl.client.render.world.clouds;

import com.mojang.blaze3d.buffers.BufferUsage;
import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.MeshData;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexBuffer;
import com.mojang.logging.LogUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.client.CloudStatus;
import net.minecraft.client.renderer.CloudRenderer;
import net.minecraft.client.renderer.CompiledShaderProgram;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.slf4j.Logger;

@OnlyIn(Dist.CLIENT)
public class JCloudRenderer extends CloudRenderer {

    private static final Logger LOGGER = LogUtils.getLogger();
    private final ResourceLocation TEXTURE_LOCATION;
    private boolean needsRebuild = true;
    private int prevCellX = Integer.MIN_VALUE;
    private int prevCellZ = Integer.MIN_VALUE;
    private RelativeCameraPos prevRelativeCameraPos;
    @Nullable
    private CloudStatus prevType;
    @Nullable
    private TextureData texture;
    private final VertexBuffer vertexBuffer;
    private boolean vertexBufferEmpty;

    public JCloudRenderer(ResourceLocation texture) {
        this.TEXTURE_LOCATION = texture;
        this.prevRelativeCameraPos = JCloudRenderer.RelativeCameraPos.INSIDE_CLOUDS;
        this.vertexBuffer = new VertexBuffer(BufferUsage.STATIC_WRITE);
    }

    @Override
    protected Optional<TextureData> prepare(ResourceManager resource, ProfilerFiller profile) {
        try {
            InputStream inputstream = resource.open(TEXTURE_LOCATION);

            Optional optional;
            try {
                NativeImage nativeimage = NativeImage.read(inputstream);

                try {
                    int i = nativeimage.getWidth();
                    int j = nativeimage.getHeight();
                    long[] along = new long[i * j];
                    int k = 0;

                    while(true) {
                        if (k >= j) {
                            optional = Optional.of(new TextureData(along, i, j));
                            break;
                        }

                        for(int l = 0; l < i; ++l) {
                            int i1 = nativeimage.getPixel(l, k);
                            if (isCellEmpty(i1)) {
                                along[l + k * i] = 0L;
                            } else {
                                boolean flag = isCellEmpty(nativeimage.getPixel(l, Math.floorMod(k - 1, j)));
                                boolean flag1 = isCellEmpty(nativeimage.getPixel(Math.floorMod(l + 1, j), k));
                                boolean flag2 = isCellEmpty(nativeimage.getPixel(l, Math.floorMod(k + 1, j)));
                                boolean flag3 = isCellEmpty(nativeimage.getPixel(Math.floorMod(l - 1, j), k));
                                along[l + k * i] = packCellData(i1, flag, flag1, flag2, flag3);
                            }
                        }

                        ++k;
                    }
                } catch (Throwable var18) {
                    if (nativeimage != null) {
                        try {
                            nativeimage.close();
                        } catch (Throwable var17) {
                            var18.addSuppressed(var17);
                        }
                    }

                    throw var18;
                }

                if (nativeimage != null) {
                    nativeimage.close();
                }
            } catch (Throwable var19) {
                if (inputstream != null) {
                    try {
                        inputstream.close();
                    } catch (Throwable var16) {
                        var19.addSuppressed(var16);
                    }
                }

                throw var19;
            }

            if (inputstream != null) {
                inputstream.close();
            }

            return optional;
        } catch (IOException var20) {
            IOException ioexception = var20;
            LOGGER.error("Failed to load cloud texture", ioexception);
            return Optional.empty();
        }
    }

    @Override
    protected void apply(Optional<TextureData> t, ResourceManager r, ProfilerFiller p) {
        this.texture = (TextureData)t.orElse(null);
        this.needsRebuild = true;
    }

    private static boolean isCellEmpty(int c) {
        return ARGB.alpha(c) < 10;
    }

    private static long packCellData(int i, boolean b, boolean b1, boolean b2, boolean b3) {
        return (long)i << 4 | (long)((b ? 1 : 0) << 3) | (long)((b1 ? 1 : 0) << 2) | (long)((b2 ? 1 : 0) << 1) | (long)((b3 ? 1 : 0) << 0);
    }

    private static int getColor(long colour) {
        return (int)(colour >> 4 & 4294967295L);
    }

    private static boolean isNorthEmpty(long d) {
        return (d >> 3 & 1L) != 0L;
    }

    private static boolean isEastEmpty(long d) {
        return (d >> 2 & 1L) != 0L;
    }

    private static boolean isSouthEmpty(long d) {
        return (d >> 1 & 1L) != 0L;
    }

    private static boolean isWestEmpty(long d) {
        return (d >> 0 & 1L) != 0L;
    }

    @Override
    public void render(int height, @NotNull CloudStatus status, float colour, @NotNull Matrix4f frustumMatrix, @NotNull Matrix4f projectionMatrix, @NotNull Vec3 loc, float tick) {
        if (this.texture != null) {
            float f = (float)((double)colour - loc.y);
            float f1 = f + 4.0F;
            RelativeCameraPos cam;
            if (f1 < 0.0F) {
                cam = JCloudRenderer.RelativeCameraPos.ABOVE_CLOUDS;
            } else if (f > 0.0F) {
                cam = JCloudRenderer.RelativeCameraPos.BELOW_CLOUDS;
            } else {
                cam = JCloudRenderer.RelativeCameraPos.INSIDE_CLOUDS;
            }

            double d0 = loc.x + (double)(tick * 0.030000001F);
            double d1 = loc.z + 3.9600000381469727;
            double d2 = (double)this.texture.width() * 12.0;
            double d3 = (double)this.texture.height() * 12.0;
            d0 -= (double)Mth.floor(d0 / d2) * d2;
            d1 -= (double)Mth.floor(d1 / d3) * d3;
            int i = Mth.floor(d0 / 12.0);
            int j = Mth.floor(d1 / 12.0);
            float f2 = (float)(d0 - (double)((float)i * 12.0F));
            float f3 = (float)(d1 - (double)((float)j * 12.0F));
            RenderType rendertype = RenderType.clouds();
            this.vertexBuffer.bind();
            if (this.needsRebuild || i != this.prevCellX || j != this.prevCellZ || cam != this.prevRelativeCameraPos || status != this.prevType) {
                this.needsRebuild = false;
                this.prevCellX = i;
                this.prevCellZ = j;
                this.prevRelativeCameraPos = cam;
                this.prevType = status;
                MeshData meshdata = this.buildMesh(Tesselator.getInstance(), i, j, status, cam, rendertype);
                if (meshdata != null) {
                    this.vertexBuffer.upload(meshdata);
                    this.vertexBufferEmpty = false;
                } else {
                    this.vertexBufferEmpty = true;
                }
            }

            if (!this.vertexBufferEmpty) {
                RenderSystem.setShaderColor(from8BitChannel(ARGB.red(height)), from8BitChannel(ARGB.green(height)), from8BitChannel(ARGB.blue(height)), 1.0F);
                if (status == CloudStatus.FANCY) {
                    this.drawWithRenderType(RenderType.cloudsDepthOnly(), frustumMatrix, projectionMatrix, f2, f, f3);
                }

                this.drawWithRenderType(rendertype, frustumMatrix, projectionMatrix, f2, f, f3);
                VertexBuffer.unbind();
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            }
        }

    }

    private static float from8BitChannel(int i) {
        return (float)i / 255.0F;
    }

    private void drawWithRenderType(RenderType r, Matrix4f frustumMatrix, Matrix4f projectionMatrix, float x, float y, float z) {
        r.setupRenderState();
        CompiledShaderProgram compiledshaderprogram = RenderSystem.getShader();
        if (compiledshaderprogram != null && compiledshaderprogram.MODEL_OFFSET != null) {
            compiledshaderprogram.MODEL_OFFSET.set(-x, y, -z);
        }

        this.vertexBuffer.drawWithShader(frustumMatrix, projectionMatrix, compiledshaderprogram);
        r.clearRenderState();
    }

    @Nullable
    private MeshData buildMesh(Tesselator t, int x, int y, CloudStatus s, RelativeCameraPos p, RenderType r) {
        int i = ARGB.colorFromFloat(0.8F, 1.0F, 1.0F, 1.0F);
        int j = ARGB.colorFromFloat(0.8F, 0.9F, 0.9F, 0.9F);
        int k = ARGB.colorFromFloat(0.8F, 0.7F, 0.7F, 0.7F);
        int l = ARGB.colorFromFloat(0.8F, 0.8F, 0.8F, 0.8F);
        BufferBuilder bufferbuilder = t.begin(r.mode(), r.format());
        this.buildMesh(p, bufferbuilder, x, y, k, i, j, l, s == CloudStatus.FANCY);
        return bufferbuilder.build();
    }

    private void buildMesh(RelativeCameraPos r, BufferBuilder buf, int p_361006_, int p_362674_, int p_362100_, int p_360889_, int p_360776_, int p_365003_, boolean p_362207_) {
        if (this.texture != null) {
            long[] along = this.texture.cells();
            int j = this.texture.width();
            int k = this.texture.height();

            for(int l = -32; l <= 32; ++l) {
                for(int i1 = -32; i1 <= 32; ++i1) {
                    int j1 = Math.floorMod(p_361006_ + i1, j);
                    int k1 = Math.floorMod(p_362674_ + l, k);
                    long l1 = along[j1 + k1 * j];
                    if (l1 != 0L) {
                        int i2 = getColor(l1);
                        if (p_362207_) {
                            this.buildExtrudedCell(r, buf, ARGB.multiply(p_362100_, i2), ARGB.multiply(p_360889_, i2), ARGB.multiply(p_360776_, i2), ARGB.multiply(p_365003_, i2), i1, l, l1);
                        } else {
                            this.buildFlatCell(buf, ARGB.multiply(p_360889_, i2), i1, l);
                        }
                    }
                }
            }
        }

    }

    private void buildFlatCell(BufferBuilder p_363638_, int p_364027_, int p_361818_, int p_364671_) {
        float f = (float)p_361818_ * 12.0F;
        float f1 = f + 12.0F;
        float f2 = (float)p_364671_ * 12.0F;
        float f3 = f2 + 12.0F;
        p_363638_.addVertex(f, 0.0F, f2).setColor(p_364027_);
        p_363638_.addVertex(f, 0.0F, f3).setColor(p_364027_);
        p_363638_.addVertex(f1, 0.0F, f3).setColor(p_364027_);
        p_363638_.addVertex(f1, 0.0F, f2).setColor(p_364027_);
    }

    private void buildExtrudedCell(RelativeCameraPos r, BufferBuilder b, int p_362180_, int p_364234_, int p_364613_, int p_361634_, int p_364709_, int p_363252_, long p_364423_) {
        float f = (float)p_364709_ * 12.0F;
        float f1 = f + 12.0F;
        float f4 = (float)p_363252_ * 12.0F;
        float f5 = f4 + 12.0F;
        if (r != JCloudRenderer.RelativeCameraPos.BELOW_CLOUDS) {
            b.addVertex(f, 4.0F, f4).setColor(p_364234_);
            b.addVertex(f, 4.0F, f5).setColor(p_364234_);
            b.addVertex(f1, 4.0F, f5).setColor(p_364234_);
            b.addVertex(f1, 4.0F, f4).setColor(p_364234_);
        }

        if (r != JCloudRenderer.RelativeCameraPos.ABOVE_CLOUDS) {
            b.addVertex(f1, 0.0F, f4).setColor(p_362180_);
            b.addVertex(f1, 0.0F, f5).setColor(p_362180_);
            b.addVertex(f, 0.0F, f5).setColor(p_362180_);
            b.addVertex(f, 0.0F, f4).setColor(p_362180_);
        }

        if (isNorthEmpty(p_364423_) && p_363252_ > 0) {
            b.addVertex(f, 0.0F, f4).setColor(p_361634_);
            b.addVertex(f, 4.0F, f4).setColor(p_361634_);
            b.addVertex(f1, 4.0F, f4).setColor(p_361634_);
            b.addVertex(f1, 0.0F, f4).setColor(p_361634_);
        }

        if (isSouthEmpty(p_364423_) && p_363252_ < 0) {
            b.addVertex(f1, 0.0F, f5).setColor(p_361634_);
            b.addVertex(f1, 4.0F, f5).setColor(p_361634_);
            b.addVertex(f, 4.0F, f5).setColor(p_361634_);
            b.addVertex(f, 0.0F, f5).setColor(p_361634_);
        }

        if (isWestEmpty(p_364423_) && p_364709_ > 0) {
            b.addVertex(f, 0.0F, f5).setColor(p_364613_);
            b.addVertex(f, 4.0F, f5).setColor(p_364613_);
            b.addVertex(f, 4.0F, f4).setColor(p_364613_);
            b.addVertex(f, 0.0F, f4).setColor(p_364613_);
        }

        if (isEastEmpty(p_364423_) && p_364709_ < 0) {
            b.addVertex(f1, 0.0F, f4).setColor(p_364613_);
            b.addVertex(f1, 4.0F, f4).setColor(p_364613_);
            b.addVertex(f1, 4.0F, f5).setColor(p_364613_);
            b.addVertex(f1, 0.0F, f5).setColor(p_364613_);
        }

        boolean flag = Math.abs(p_364709_) <= 1 && Math.abs(p_363252_) <= 1;
        if (flag) {
            b.addVertex(f1, 4.0F, f4).setColor(p_364234_);
            b.addVertex(f1, 4.0F, f5).setColor(p_364234_);
            b.addVertex(f, 4.0F, f5).setColor(p_364234_);
            b.addVertex(f, 4.0F, f4).setColor(p_364234_);
            b.addVertex(f, 0.0F, f4).setColor(p_362180_);
            b.addVertex(f, 0.0F, f5).setColor(p_362180_);
            b.addVertex(f1, 0.0F, f5).setColor(p_362180_);
            b.addVertex(f1, 0.0F, f4).setColor(p_362180_);
            b.addVertex(f1, 0.0F, f4).setColor(p_361634_);
            b.addVertex(f1, 4.0F, f4).setColor(p_361634_);
            b.addVertex(f, 4.0F, f4).setColor(p_361634_);
            b.addVertex(f, 0.0F, f4).setColor(p_361634_);
            b.addVertex(f, 0.0F, f5).setColor(p_361634_);
            b.addVertex(f, 4.0F, f5).setColor(p_361634_);
            b.addVertex(f1, 4.0F, f5).setColor(p_361634_);
            b.addVertex(f1, 0.0F, f5).setColor(p_361634_);
            b.addVertex(f, 0.0F, f4).setColor(p_364613_);
            b.addVertex(f, 4.0F, f4).setColor(p_364613_);
            b.addVertex(f, 4.0F, f5).setColor(p_364613_);
            b.addVertex(f, 0.0F, f5).setColor(p_364613_);
            b.addVertex(f1, 0.0F, f5).setColor(p_364613_);
            b.addVertex(f1, 4.0F, f5).setColor(p_364613_);
            b.addVertex(f1, 4.0F, f4).setColor(p_364613_);
            b.addVertex(f1, 0.0F, f4).setColor(p_364613_);
        }

    }

    public void markForRebuild() {
        this.needsRebuild = true;
    }

    public void close() {
        this.vertexBuffer.close();
    }

    @OnlyIn(Dist.CLIENT)
    enum RelativeCameraPos {
        ABOVE_CLOUDS,
        INSIDE_CLOUDS,
        BELOW_CLOUDS;

        private RelativeCameraPos() {
        }
    }
}
