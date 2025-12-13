package net.jitl.client.render.world;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.SkyRenderer;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.model.AtlasManager;
import net.minecraft.data.AtlasIds;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.OptionalDouble;
import java.util.OptionalInt;

public class JDimensionSpecialEffects extends SkyRenderer {

    private final GpuBuffer topSkyBuffer;
    private final GpuBuffer bottomSkyBuffer;
    private final RenderSystem.AutoStorageIndexBuffer quadIndices;
    public final TextureAtlas celestialsAtlas;

    public JDimensionSpecialEffects(TextureManager textureManager, AtlasManager atlasManager) {
        super(textureManager, atlasManager);
        this.celestialsAtlas = atlasManager.getAtlasOrThrow(AtlasIds.CELESTIALS);

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
        this.quadIndices = RenderSystem.getSequentialBuffer(VertexFormat.Mode.QUADS);

    }

    private void buildSkyDisc(VertexConsumer buffer, float y) {
        float f = Math.signum(y) * 512.0F;
        buffer.addVertex(0.0F, y, 0.0F);

        for (int i = -180; i <= 180; i += 45) {
            buffer.addVertex(f * Mth.cos(i * (float) (Math.PI / 180.0)), y, 512.0F * Mth.sin(i * (float) (Math.PI / 180.0)));
        }
    }

    private AbstractTexture getTexture(Identifier location) {
        TextureManager texturemanager = Minecraft.getInstance().getTextureManager();
        return texturemanager.getTexture(location);
    }

    public void renderSun(float alpha, PoseStack poseStack, GpuBuffer sunBuffer) {
        Matrix4fStack matrix4fstack = RenderSystem.getModelViewStack();
        matrix4fstack.pushMatrix();
        matrix4fstack.mul(poseStack.last().pose());
        matrix4fstack.translate(0.0F, 100.0F, 0.0F);
        matrix4fstack.scale(30.0F, 1.0F, 30.0F);
        GpuBufferSlice gpubufferslice = RenderSystem.getDynamicUniforms()
                .writeTransform(matrix4fstack, new Vector4f(1.0F, 1.0F, 1.0F, alpha), new Vector3f(), new Matrix4f());
        GpuTextureView gputextureview = Minecraft.getInstance().getMainRenderTarget().getColorTextureView();
        GpuTextureView gputextureview1 = Minecraft.getInstance().getMainRenderTarget().getDepthTextureView();
        GpuBuffer gpubuffer = this.quadIndices.getBuffer(6);

        try (RenderPass renderpass = RenderSystem.getDevice()
                .createCommandEncoder()
                .createRenderPass(() -> "Sky sun", gputextureview, OptionalInt.empty(), gputextureview1, OptionalDouble.empty())) {
            renderpass.setPipeline(RenderPipelines.CELESTIAL);
            RenderSystem.bindDefaultUniforms(renderpass);
            renderpass.setUniform("DynamicTransforms", gpubufferslice);
            renderpass.bindTexture("Sampler0", this.celestialsAtlas.getTextureView(), this.celestialsAtlas.getSampler());
            renderpass.setVertexBuffer(0, sunBuffer);
            renderpass.setIndexBuffer(gpubuffer, this.quadIndices.type());
            renderpass.drawIndexed(0, 0, 6, 1);
        }

        matrix4fstack.popMatrix();
    }

    public GpuBuffer buildSunQuad(TextureAtlas atlas, float size, Identifier texture) {
        return buildCelestialQuad("Sun quad", size, atlas.getSprite(texture));
    }

    public GpuBuffer buildCelestialQuad(String name, float size, TextureAtlasSprite atlas) {
        VertexFormat vertexformat = DefaultVertexFormat.POSITION_TEX;

        GpuBuffer gpubuffer;
        try (ByteBufferBuilder bytebufferbuilder = ByteBufferBuilder.exactlySized(4 * vertexformat.getVertexSize())) {
            BufferBuilder bufferbuilder = new BufferBuilder(bytebufferbuilder, VertexFormat.Mode.QUADS, vertexformat);
            bufferbuilder.addVertex(-size, 0.0F, -size).setUv(atlas.getU0(), atlas.getV0());
            bufferbuilder.addVertex(size, 0.0F, -size).setUv(atlas.getU1(), atlas.getV0());
            bufferbuilder.addVertex(size, 0.0F, size).setUv(atlas.getU1(), atlas.getV1());
            bufferbuilder.addVertex(-size, 0.0F, size).setUv(atlas.getU0(), atlas.getV1());

            try (MeshData meshdata = bufferbuilder.buildOrThrow()) {
                gpubuffer = RenderSystem.getDevice().createBuffer(() -> name, 32, meshdata.vertexBuffer());
            }
        }

        return gpubuffer;
    }

    protected void renderSky(Identifier texture) {
        renderSky(texture, 1F);
    }

    protected void renderSky(Identifier texture, float brightness) {
        AbstractTexture abstracttexture = getTexture(texture);

        RenderSystem.AutoStorageIndexBuffer rendersystem$autostorageindexbuffer = RenderSystem.getSequentialBuffer(VertexFormat.Mode.QUADS);
        GpuBuffer gpubuffer = rendersystem$autostorageindexbuffer.getBuffer(36);
        GpuTextureView gputextureview = Minecraft.getInstance().getMainRenderTarget().getColorTextureView();
        GpuTextureView gputextureview1 = Minecraft.getInstance().getMainRenderTarget().getDepthTextureView();
        GpuBufferSlice gpubufferslice = RenderSystem.getDynamicUniforms().writeTransform(RenderSystem.getModelViewMatrix(), new Vector4f(brightness, brightness, brightness, brightness), new Vector3f(), new Matrix4f());

        try (RenderPass renderpass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Journey Sky", gputextureview, OptionalInt.empty(), gputextureview1, OptionalDouble.empty())) {
            renderpass.setPipeline(RenderPipelines.END_SKY);
            RenderSystem.bindDefaultUniforms(renderpass);
            renderpass.setUniform("DynamicTransforms", gpubufferslice);
            renderpass.bindTexture("Sampler0", abstracttexture.getTextureView(), abstracttexture.getSampler());
            renderpass.setVertexBuffer(0, buildSky());
            renderpass.setIndexBuffer(gpubuffer, rendersystem$autostorageindexbuffer.type());
            renderpass.drawIndexed(0, 0, 36, 1);
        }
    }

    public void renderSkyDisc(float red, float green, float blue) {
        GpuBufferSlice gpubufferslice = RenderSystem.getDynamicUniforms()
                .writeTransform(RenderSystem.getModelViewMatrix(), new Vector4f(red, green, blue, 1.0F), new Vector3f(), new Matrix4f());
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
                .writeTransform(matrix4fstack, new Vector4f(0.0F, 0.0F, 0.0F, 1.0F), new Vector3f(), new Matrix4f());
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

//    @Override
//    public boolean renderClouds(LevelRenderState levelRenderState, Vec3 camPos, CloudStatus cloudStatus, int cloudColor, float cloudHeight, Matrix4f modelViewMatrix) {
//        getCloudRenderer().render(cloudColor, cloudStatus, cloudHeight, camPos, levelRenderState.skyRenderState.timeOfDay);//todo ticks
////        return true;
////    }
//
//    public abstract JCloudRenderer getCloudRenderer();

    public boolean doesMobEffectBlockSky(Camera camera) {
        Entity entity = camera.entity();
        if(!(entity instanceof LivingEntity livingentity)) return false;
        return livingentity.hasEffect(MobEffects.BLINDNESS) || livingentity.hasEffect(MobEffects.DARKNESS);
    }
}
