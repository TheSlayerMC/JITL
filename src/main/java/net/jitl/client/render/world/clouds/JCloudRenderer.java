package net.jitl.client.render.world.clouds;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.MeshData;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.CloudStatus;
import net.minecraft.client.renderer.CloudRenderer;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.util.TriState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
@OnlyIn(Dist.CLIENT)
public class JCloudRenderer {//extends CloudRenderer {
/*
    public JCloudRenderer(ResourceLocation texture) {
        TEXTURE_LOCATION = texture;
    }

    public JCloudRenderer() { }

    @Override
    public void render(int height, @NotNull CloudStatus status, float colour, @NotNull Matrix4f frustumMatrix, @NotNull Matrix4f projectionMatrix, @NotNull Vec3 loc, float tick) {
        if(this.texture != null) {
            float f = (float)((double)colour - loc.y);
            float f1 = f + 4.0F;
            RelativeCameraPos cam;
            if (f1 < 0.0F) {
                cam = RelativeCameraPos.ABOVE_CLOUDS;
            } else if(f > 0.0F) {
                cam = RelativeCameraPos.BELOW_CLOUDS;
            } else {
                cam = RelativeCameraPos.INSIDE_CLOUDS;
            }

            double d0 = loc.x + (double)(tick * 0.030000001F);
            double d1 = loc.z + 3.9600000381469727;
            double d2 = (double)this.texture.width() * 12.0;
            double d3 = (double)this.texture.height() * 12.0;
            d0 -= (double) Mth.floor(d0 / d2) * d2;
            d1 -= (double)Mth.floor(d1 / d3) * d3;
            int i = Mth.floor(d0 / 12.0);
            int j = Mth.floor(d1 / 12.0);
            float f2 = (float)(d0 - (double)((float)i * 12.0F));
            float f3 = (float)(d1 - (double)((float)j * 12.0F));
            RenderType rendertype = createClouds(true);
            this.vertexBuffer.bind();
            if(this.needsRebuild || i != this.prevCellX || j != this.prevCellZ || cam != this.prevRelativeCameraPos || status != this.prevType) {
                this.needsRebuild = false;
                this.prevCellX = i;
                this.prevCellZ = j;
                this.prevRelativeCameraPos = cam;
                this.prevType = status;
                MeshData meshdata = this.buildMesh(Tesselator.getInstance(), i, j, status, cam, rendertype);
                if(meshdata != null) {
                    this.vertexBuffer.upload(meshdata);
                    this.vertexBufferEmpty = false;
                } else {
                    this.vertexBufferEmpty = true;
                }
            }

            if(!this.vertexBufferEmpty) {
                RenderSystem.setShaderColor(from8BitChannel(ARGB.red(height)), from8BitChannel(ARGB.green(height)), from8BitChannel(ARGB.blue(height)), 1.0F);
                if(status == CloudStatus.FANCY) {
                    this.drawWithRenderType(RenderType.cloudsDepthOnly(), frustumMatrix, projectionMatrix, f2, f, f3);
                }
                this.drawWithRenderType(rendertype, frustumMatrix, projectionMatrix, f2, f, f3);
                VertexBuffer.unbind();
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            }
        }
    }

    public static float from8BitChannel(int i) {
        return (float)i / 255.0F;
    }

    public static RenderType createClouds(boolean colour) {
        return RenderType.create(
                "jitl_clouds",
                DefaultVertexFormat.POSITION_COLOR,
                VertexFormat.Mode.QUADS,
                786432,
                false,
                false,
                RenderType.CompositeState.builder()
                        .setShaderState(RenderStateShard.RENDERTYPE_CLOUDS_SHADER)
                        .setTextureState(new RenderStateShard.TextureStateShard(TEXTURE_LOCATION, TriState.FALSE, false))
                        .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                        .setCullState(RenderStateShard.NO_CULL)
                        .setWriteMaskState(colour ? RenderStateShard.DEPTH_WRITE : RenderStateShard.COLOR_DEPTH_WRITE)
                        .setOutputState(RenderStateShard.CLOUDS_TARGET)
                        .createCompositeState(true)
        );
    }*/
}
