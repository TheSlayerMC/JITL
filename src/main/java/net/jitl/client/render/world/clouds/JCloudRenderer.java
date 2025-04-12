package net.jitl.client.render.world.clouds;

import com.mojang.blaze3d.buffers.BufferType;
import com.mojang.blaze3d.buffers.BufferUsage;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.MeshData;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.CloudStatus;
import net.minecraft.client.renderer.CloudRenderer;
import net.minecraft.client.renderer.RenderPipelines;
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
public class JCloudRenderer extends CloudRenderer {

    public JCloudRenderer(ResourceLocation texture) {
        TEXTURE_LOCATION = texture;
    }

    public JCloudRenderer() { }

    @Override
    public void render(int p_363907_, CloudStatus p_364293_, float p_363260_, Vec3 p_363573_, float p_360711_) {
        if (this.texture != null) {
            float f = (float)((double)p_363260_ - p_363573_.y);
            float f1 = f + 4.0F;
            RelativeCameraPos cloudrenderer$relativecamerapos;
            if (f1 < 0.0F) {
                cloudrenderer$relativecamerapos = CloudRenderer.RelativeCameraPos.ABOVE_CLOUDS;
            } else if (f > 0.0F) {
                cloudrenderer$relativecamerapos = CloudRenderer.RelativeCameraPos.BELOW_CLOUDS;
            } else {
                cloudrenderer$relativecamerapos = CloudRenderer.RelativeCameraPos.INSIDE_CLOUDS;
            }

            double d0 = p_363573_.x + (double)(p_360711_ * 0.030000001F);
            double d1 = p_363573_.z + (double)3.96F;
            double d2 = (double)this.texture.width() * (double)12.0F;
            double d3 = (double)this.texture.height() * (double)12.0F;
            d0 -= (double)Mth.floor(d0 / d2) * d2;
            d1 -= (double)Mth.floor(d1 / d3) * d3;
            int i = Mth.floor(d0 / (double)12.0F);
            int j = Mth.floor(d1 / (double)12.0F);
            float f2 = (float)(d0 - (double)((float)i * 12.0F));
            float f3 = (float)(d1 - (double)((float)j * 12.0F));
            boolean flag = p_364293_ == CloudStatus.FANCY;
            RenderPipeline renderpipeline = flag ? RenderPipelines.CLOUDS : RenderPipelines.FLAT_CLOUDS;
            if (this.needsRebuild || i != this.prevCellX || j != this.prevCellZ || cloudrenderer$relativecamerapos != this.prevRelativeCameraPos || p_364293_ != this.prevType) {
                this.needsRebuild = false;
                this.prevCellX = i;
                this.prevCellZ = j;
                this.prevRelativeCameraPos = cloudrenderer$relativecamerapos;
                this.prevType = p_364293_;

                try (MeshData meshdata = this.buildMesh(Tesselator.getInstance(), i, j, p_364293_, cloudrenderer$relativecamerapos, renderpipeline)) {
                    if (meshdata == null) {
                        this.indexCount = 0;
                    } else {
                        if (this.vertexBuffer != null && this.vertexBuffer.size >= meshdata.vertexBuffer().remaining()) {
                            CommandEncoder commandencoder = RenderSystem.getDevice().createCommandEncoder();
                            commandencoder.writeToBuffer(this.vertexBuffer, meshdata.vertexBuffer(), 0);
                        } else {
                            if (this.vertexBuffer != null) {
                                this.vertexBuffer.close();
                            }

                            this.vertexBuffer = RenderSystem.getDevice().createBuffer(() -> "Cloud vertex buffer", BufferType.VERTICES, BufferUsage.DYNAMIC_WRITE, meshdata.vertexBuffer());
                        }

                        this.indexCount = meshdata.drawState().indexCount();
                    }
                }
            }

            if (this.indexCount != 0) {
                RenderSystem.setShaderColor(ARGB.redFloat(p_363907_), ARGB.greenFloat(p_363907_), ARGB.blueFloat(p_363907_), 1.0F);
                if (flag) {
                    this.draw(RenderPipelines.CLOUDS_DEPTH_ONLY, f2, f, f3);
                }

                this.draw(renderpipeline, f2, f, f3);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            }
        }

    }
}
