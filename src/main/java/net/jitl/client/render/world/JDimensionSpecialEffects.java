package net.jitl.client.render.world;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.jitl.client.render.world.clouds.JCloudRenderer;
import net.jitl.core.init.JITL;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;

public abstract class JDimensionSpecialEffects extends DimensionSpecialEffects {

    private final GpuBuffer topSkyBuffer;
    private final GpuBuffer bottomSkyBuffer;

    public JDimensionSpecialEffects(SkyType type, boolean forceBrightLightmap, boolean constantAmbientLight) {
        super(type, forceBrightLightmap, constantAmbientLight);
        try (ByteBufferBuilder bytebufferbuilder = ByteBufferBuilder.exactlySized(10 * DefaultVertexFormat.POSITION.getVertexSize())) {
            BufferBuilder bufferbuilder = new BufferBuilder(bytebufferbuilder, VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION);
            this.buildSkyDisc(bufferbuilder, 16.0F);

            try (MeshData meshdata = bufferbuilder.buildOrThrow()) {
                this.topSkyBuffer = RenderSystem.getDevice().createBuffer(() -> "Top sky vertex buffer", 32, meshdata.vertexBuffer());
            }

            bufferbuilder = new BufferBuilder(bytebufferbuilder, VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION);
            this.buildSkyDisc(bufferbuilder, -16.0F);

            try (MeshData meshdata1 = bufferbuilder.buildOrThrow()) {
                this.bottomSkyBuffer = RenderSystem.getDevice().createBuffer(() -> "Bottom sky vertex buffer", 32, meshdata1.vertexBuffer());
            }
        }
    }

    private void buildSkyDisc(VertexConsumer buffer, float y) {
        float f = Math.signum(y) * 512.0F;
        buffer.addVertex(0.0F, y, 0.0F);

        for (int i = -180; i <= 180; i += 45) {
            buffer.addVertex(f * Mth.cos(i * (float) (Math.PI / 180.0)), y, 512.0F * Mth.sin(i * (float) (Math.PI / 180.0)));
        }
    }

    public void renderSun(float size, float alpha, MultiBufferSource.BufferSource bufferSource, PoseStack poseStack, ResourceLocation tex) {
        VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderType.celestial(tex));
        int i = ARGB.white(alpha);
        Matrix4f matrix4f = poseStack.last().pose();
        poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
        float f = Mth.sin(0) < 0.0F ? 180.0F : 0.0F;
        poseStack.mulPose(Axis.ZP.rotationDegrees(f));
        poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
        vertexconsumer.addVertex(matrix4f, -size, 100.0F, -size).setUv(0.0F, 0.0F).setColor(i);
        vertexconsumer.addVertex(matrix4f, size, 100.0F, -size).setUv(1.0F, 0.0F).setColor(i);
        vertexconsumer.addVertex(matrix4f, size, 100.0F, size).setUv(1.0F, 1.0F).setColor(i);
        vertexconsumer.addVertex(matrix4f, -size, 100.0F, size).setUv(0.0F, 1.0F).setColor(i);
        bufferSource.endBatch();
    }

    protected void renderSky(ResourceLocation texture) {
        TextureManager texturemanager = Minecraft.getInstance().getTextureManager();
        AbstractTexture abstracttexture = texturemanager.getTexture(texture);
        abstracttexture.setUseMipmaps(false);
        RenderSystem.AutoStorageIndexBuffer rendersystem$autostorageindexbuffer = RenderSystem.getSequentialBuffer(VertexFormat.Mode.QUADS);
        GpuBuffer gpubuffer = rendersystem$autostorageindexbuffer.getBuffer(36);
        GpuTextureView gputextureview = Minecraft.getInstance().getMainRenderTarget().getColorTextureView();
        GpuTextureView gputextureview1 = Minecraft.getInstance().getMainRenderTarget().getDepthTextureView();
        GpuBufferSlice gpubufferslice = RenderSystem.getDynamicUniforms().writeTransform(RenderSystem.getModelViewMatrix(), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), new Vector3f(), new Matrix4f(), 0.0F);

        try (RenderPass renderpass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Journey Sky", gputextureview, OptionalInt.empty(), gputextureview1, OptionalDouble.empty())) {
            renderpass.setPipeline(RenderPipelines.END_SKY);
            RenderSystem.bindDefaultUniforms(renderpass);
            renderpass.setUniform("DynamicTransforms", gpubufferslice);
            renderpass.bindSampler("Sampler0", abstracttexture.getTextureView());
            renderpass.setVertexBuffer(0, buildSky());
            renderpass.setIndexBuffer(gpubuffer, rendersystem$autostorageindexbuffer.type());
            renderpass.drawIndexed(0, 0, 36, 1);
        }
    }

    public void renderSkyDisc(float red, float green, float blue) {
        GpuBufferSlice gpubufferslice = RenderSystem.getDynamicUniforms()
                .writeTransform(RenderSystem.getModelViewMatrix(), new Vector4f(red, green, blue, 1.0F), new Vector3f(), new Matrix4f(), 0.0F);
        GpuTextureView gputextureview = Minecraft.getInstance().getMainRenderTarget().getColorTextureView();
        GpuTextureView gputextureview1 = Minecraft.getInstance().getMainRenderTarget().getDepthTextureView();

        try (RenderPass renderpass = RenderSystem.getDevice()
                .createCommandEncoder()
                .createRenderPass(() -> "Sky disc", gputextureview, OptionalInt.empty(), gputextureview1, OptionalDouble.empty())) {
            renderpass.setPipeline(RenderPipelines.SKY);
            RenderSystem.bindDefaultUniforms(renderpass);
            renderpass.setUniform("DynamicTransforms", gpubufferslice);
            renderpass.setVertexBuffer(0, this.topSkyBuffer);
            renderpass.draw(0, 10);
        }
    }

    public void renderDarkDisc() {
        Matrix4fStack matrix4fstack = RenderSystem.getModelViewStack();
        matrix4fstack.pushMatrix();
        matrix4fstack.translate(0.0F, 12.0F, 0.0F);
        GpuBufferSlice gpubufferslice = RenderSystem.getDynamicUniforms()
                .writeTransform(matrix4fstack, new Vector4f(0.0F, 0.0F, 0.0F, 1.0F), new Vector3f(), new Matrix4f(), 0.0F);
        GpuTextureView gputextureview = Minecraft.getInstance().getMainRenderTarget().getColorTextureView();
        GpuTextureView gputextureview1 = Minecraft.getInstance().getMainRenderTarget().getDepthTextureView();

        try (RenderPass renderpass = RenderSystem.getDevice()
                .createCommandEncoder()
                .createRenderPass(() -> "Sky dark", gputextureview, OptionalInt.empty(), gputextureview1, OptionalDouble.empty())) {
            renderpass.setPipeline(RenderPipelines.SKY);
            RenderSystem.bindDefaultUniforms(renderpass);
            renderpass.setUniform("DynamicTransforms", gpubufferslice);
            renderpass.setVertexBuffer(0, this.bottomSkyBuffer);
            renderpass.draw(0, 10);
        }

        matrix4fstack.popMatrix();
    }

    protected static GpuBuffer buildSky() {
        GpuBuffer gpubuffer;
        try (ByteBufferBuilder bytebufferbuilder = ByteBufferBuilder.exactlySized(24 * DefaultVertexFormat.POSITION_TEX_COLOR.getVertexSize())) {
            BufferBuilder bufferbuilder = new BufferBuilder(bytebufferbuilder, VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);

            for(int i = 0; i < 6; ++i) {
                Matrix4f matrix4f = new Matrix4f();
                switch (i) {
                    case 1 -> matrix4f.rotationX(((float)Math.PI / 2F));
                    case 2 -> matrix4f.rotationX((-(float)Math.PI / 2F));
                    case 3 -> matrix4f.rotationX((float)Math.PI);
                    case 4 -> matrix4f.rotationZ(((float)Math.PI / 2F));
                    case 5 -> matrix4f.rotationZ((-(float)Math.PI / 2F));
                }

                bufferbuilder.addVertex(matrix4f, -100.0F, -100.0F, -100.0F).setUv(0.0F, 0.0F).setColor(-14145496);
                bufferbuilder.addVertex(matrix4f, -100.0F, -100.0F, 100.0F).setUv(0.0F, 16.0F).setColor(-14145496);
                bufferbuilder.addVertex(matrix4f, 100.0F, -100.0F, 100.0F).setUv(16.0F, 16.0F).setColor(-14145496);
                bufferbuilder.addVertex(matrix4f, 100.0F, -100.0F, -100.0F).setUv(16.0F, 0.0F).setColor(-14145496);
            }

            try (MeshData meshdata = bufferbuilder.buildOrThrow()) {
                gpubuffer = RenderSystem.getDevice().createBuffer(() -> "sky vertex buffer", 40, meshdata.vertexBuffer());
            }
        }
        return gpubuffer;
    }

    @Override
    public boolean renderClouds(ClientLevel level, int ticks, float partialTick, double camX, double camY, double camZ, Matrix4f modelViewMatrix) {
        if(getCloudRenderer() != null) {
            Optional<Integer> optional = level.dimensionType().cloudHeight();
            optional.ifPresent(height -> getCloudRenderer().render(level.getCloudColor(partialTick), Minecraft.getInstance().options.getCloudsType(), height + 0.33F, new Vec3(camX, camY, camZ), ticks + partialTick));
        }
        return true;
    }

    public abstract JCloudRenderer getCloudRenderer();

    public boolean doesMobEffectBlockSky(Camera camera) {
        Entity entity = camera.getEntity();
        if(!(entity instanceof LivingEntity livingentity)) return false;
        return livingentity.hasEffect(MobEffects.BLINDNESS) || livingentity.hasEffect(MobEffects.DARKNESS);
    }
}
