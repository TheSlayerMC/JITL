package net.jitl.client;

import com.google.common.collect.Sets;
import net.jitl.common.entity.base.JBoat;
import net.jitl.core.init.JITL;
import net.minecraft.client.model.geom.ModelLayerLocation;

import java.util.Set;

public class JModelLayers {

    private static final Set<ModelLayerLocation> ALL_MODELS = Sets.newHashSet();



    private static ModelLayerLocation register(String path) {
        return register(path, "main");
    }

    private static ModelLayerLocation register(String path, String model) {
        ModelLayerLocation modellayerlocation = createLocation(path, model);
        if (!ALL_MODELS.add(modellayerlocation)) {
            throw new IllegalStateException("Duplicate registration for " + modellayerlocation);
        } else {
            return modellayerlocation;
        }
    }

    private static ModelLayerLocation createLocation(String path, String model) {
        return new ModelLayerLocation(JITL.rl(path), model);
    }

    public static ModelLayerLocation createBoatModelName(JBoat.Type type) {
        return createLocation("boat/" + type.getName(), "main");
    }
}
