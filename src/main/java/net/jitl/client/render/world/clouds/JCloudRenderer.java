package net.jitl.client.render.world.clouds;

import net.minecraft.client.renderer.CloudRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JCloudRenderer extends CloudRenderer {

    public JCloudRenderer(ResourceLocation texture) {
        TEXTURE_LOCATION = texture;
    }

    public JCloudRenderer() { }
}
