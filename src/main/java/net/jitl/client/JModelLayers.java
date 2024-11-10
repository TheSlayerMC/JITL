package net.jitl.client;

import com.google.common.collect.Sets;
import net.jitl.common.entity.base.JBoat;
import net.jitl.core.init.JITL;
import net.minecraft.client.model.WolfModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.LayerDefinition;

import java.util.Set;

public class JModelLayers {

    private static final Set<ModelLayerLocation> ALL_MODELS = Sets.newHashSet();

    public static final ModelLayerLocation JCHEST = register("jchest");
    public static final ModelLayerLocation JDOUBLE_CHEST_LEFT = register("jdouble_chest_left");
    public static final ModelLayerLocation JDOUBLE_CHEST_RIGHT = register("jdouble_chest_right");
    public static final ModelLayerLocation FROZEN_TROLL_MODEL_LAYER = register("frozen_troll");
    public static final ModelLayerLocation SHIVERWOLF_MODEL_LAYER = register("shiverwolf");
    public static final ModelLayerLocation SHIVERWOLF_BABY_MODEL_LAYER = register("shiverwolf_baby");
    public static final ModelLayerLocation SHIVERWOLF_ARMOR_LAYER = register("shiverwolf_armor");
    public static final ModelLayerLocation BROWN_EUCA_BOAT = createBoatModelName(JBoat.Type.BROWN_EUCA);
    public static final ModelLayerLocation GOLD_EUCA_BOAT = createBoatModelName(JBoat.Type.GOLD_EUCA);
    public static final ModelLayerLocation CLOUDIA_BOAT = createBoatModelName(JBoat.Type.CLOUDIA);
    public static final ModelLayerLocation CORBA_BOAT = createBoatModelName(JBoat.Type.CORBA);
    public static final ModelLayerLocation DEPTHS_BOAT = createBoatModelName(JBoat.Type.DEPTHS);
    public static final ModelLayerLocation FROZEN_BOAT = createBoatModelName(JBoat.Type.FROZEN);
    public static final ModelLayerLocation TERRANIA_BOAT = createBoatModelName(JBoat.Type.TERRANIA);


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
