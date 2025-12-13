package net.jitl.common.entity.base;

import net.jitl.core.init.internal.JBlocks;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class JBoat extends Boat {

    public JBoat(EntityType<? extends JBoat> entityType, Level level, Supplier<Item> item) {
        super(entityType, level, item);
    }

    public enum Type {
        GOLD_EUCA(JBlocks.EUCA_GOLD_PLANKS.get(), "gold_euca"),
        BROWN_EUCA(JBlocks.EUCA_BROWN_PLANKS.get(), "brown_euca"),
        FROZEN(JBlocks.FROZEN_PLANKS.get(), "frozen"),
        DEPTHS(JBlocks.DEPTHS_PLANKS.get(), "depths"),
        BURNED(JBlocks.BURNED_PLANKS.get(), "burned"),
        CORBA(JBlocks.CORBA_PLANKS.get(), "corba"),
        TERRANIA(JBlocks.TERRANIAN_PLANKS.get(), "terranian"),
        CLOUDIA(JBlocks.CLOUDIA_PLANKS.get(), "cloudia")
        ;

        private final String name;
        private final Block planks;

        Type(Block baseBlock, String name) {
            this.name = name;
            this.planks = baseBlock;
        }

        public String getName() {
            return this.name;
        }

        public Block getPlanks() {
            return this.planks;
        }

        @Override
        public String toString() {
            return this.name;
        }

        public static JBoat.Type byId(int id) {
            JBoat.Type[] b = values();
            if(id < 0 || id >= b.length) {
                id = 0;
            }
            return b[id];
        }

        public static JBoat.Type byName(String name) {
            JBoat.Type[] b = values();
            for (Type type : b) {
                if (type.getName().equals(name)) {
                    return type;
                }
            }
            return b[0];
        }
    }
}
