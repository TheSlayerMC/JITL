package net.jitl.core.helper;

import net.jitl.core.helper.internal.EnumLookup;
import net.minecraft.world.level.levelgen.structure.Structure;

public enum EnumStructureMusic {
    //GUARDIAN_TOWER(JStructures.GUARDIAN_TOWER_HOLDER.getStructure(), new JMusic(JSounds.TOWER_THEME.get(), 1, 200, 1000), 1);
;
    private final Structure structure;
    private final JMusic music;
    private final int id;
    private static final EnumLookup<EnumStructureMusic, Integer> STRUCTURE_FINDER = EnumLookup.make(EnumStructureMusic.class, EnumStructureMusic::getID);

    EnumStructureMusic(Structure structureIn, JMusic structureMusic, int musicID) {
        structure = structureIn;
        music = structureMusic;
        id = musicID;
    }

    public Structure getStructure() {
            return structure;
        }

    public int getID() {
        return id;
    }

    public JMusic getMusicForStructure() {
        return music;
    }

    public static EnumStructureMusic getFromID(int id) {
        if (id == 0) {
            return null;
        } else {
            return STRUCTURE_FINDER.by(id);
        }
    }
}