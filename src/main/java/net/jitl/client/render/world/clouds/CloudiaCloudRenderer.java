package net.jitl.client.render.world.clouds;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.jitl.core.init.JITL;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;

public class CloudiaCloudRenderer extends JCloudRenderer {

    private RenderType createClouds(boolean colour) {
        return RenderType.create(
                "cloudia_clouds",
                DefaultVertexFormat.POSITION_TEX_COLOR_NORMAL,
                VertexFormat.Mode.QUADS,
                786432,
                false,
                false,
                RenderType.CompositeState.builder()
                        .setShaderState(RenderStateShard.RENDERTYPE_CLOUDS_SHADER)
                        .setTextureState(new RenderStateShard.TextureStateShard(JITL.rl("textures/environment/cloudia_clouds.png"), false, false))
                        .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                        .setCullState(RenderStateShard.NO_CULL)
                        .setWriteMaskState(colour ? RenderStateShard.DEPTH_WRITE : RenderStateShard.COLOR_DEPTH_WRITE)
                        .setOutputState(RenderStateShard.CLOUDS_TARGET)
                        .createCompositeState(true)
        );
    }

    @Override
    public RenderType clouds() {
        return createClouds(false);
    }

    @Override
    public RenderType depthClouds() {
        return createClouds(true);
    }
}
