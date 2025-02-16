package net.jitl.client.render.world.clouds;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.jitl.core.init.JITL;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.TriState;

public class BoilCloudRenderer {

    private RenderType createClouds(boolean colour) {
        return RenderType.create(
                "boil_clouds",
                DefaultVertexFormat.POSITION_TEX_COLOR_NORMAL,
                VertexFormat.Mode.QUADS,
                786432,
                false,
                false,
                RenderType.CompositeState.builder()
                        .setShaderState(RenderStateShard.RENDERTYPE_CLOUDS_SHADER)
                        .setTextureState(new RenderStateShard.TextureStateShard(JITL.rl("textures/environment/boil_clouds.png"), TriState.FALSE, false))
                        .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                        .setCullState(RenderStateShard.NO_CULL)
                        .setWriteMaskState(colour ? RenderStateShard.DEPTH_WRITE : RenderStateShard.COLOR_DEPTH_WRITE)
                        .setOutputState(RenderStateShard.CLOUDS_TARGET)
                        .createCompositeState(true)
        );
    }

}
