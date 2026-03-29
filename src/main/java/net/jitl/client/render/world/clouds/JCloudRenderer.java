package net.jitl.client.render.world.clouds;

import net.minecraft.client.CloudStatus;
import net.minecraft.client.renderer.CloudRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;

public class JCloudRenderer extends CloudRenderer {

    public JCloudRenderer(Identifier texture) {
        TEXTURE_LOCATION = texture;
    }

    public JCloudRenderer() { }

    @Override
    public void render(int color, CloudStatus cloudStatus, float bottomY, int range, Vec3 cameraPosition, long gameTime, float partialTicks) {
        super.render(color, cloudStatus, bottomY, range, cameraPosition, gameTime, partialTicks);
        if(texture != null) {
            System.out.println("RENDERING CLOUDS");
        }
    }
}
