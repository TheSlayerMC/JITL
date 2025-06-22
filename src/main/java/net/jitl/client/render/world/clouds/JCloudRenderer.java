package net.jitl.client.render.world.clouds;

import net.minecraft.client.CloudStatus;
import net.minecraft.client.renderer.CloudRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.Optional;

@OnlyIn(Dist.CLIENT)
public class JCloudRenderer extends CloudRenderer {

    public JCloudRenderer(ResourceLocation texture) {
        TEXTURE_LOCATION = texture;
    }

    public JCloudRenderer() { }

    @Override
    public void render(int cloudColor, CloudStatus cloudStatus, float height, Vec3 cameraPosition, float ticks) {
        super.render(cloudColor, cloudStatus, height, cameraPosition, ticks);
        if(texture != null) {
            System.out.println("RENDERING CLOUDS");
        }
    }
}
