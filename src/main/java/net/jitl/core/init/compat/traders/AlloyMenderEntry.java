package net.jitl.core.init.compat.traders;

import jeresources.compatibility.CompatBase;
import jeresources.entry.AbstractVillagerEntry;
import net.jitl.common.entity.euca.npc.AlloyMender;
import net.jitl.core.init.internal.JEntities;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;

public class AlloyMenderEntry extends AbstractVillagerEntry<AlloyMender> {

    public AlloyMenderEntry() {
        this.addITradeLists(AlloyMender.TRADES);
    }

    @Override
    public String getName() {
        return "alloy_mender";
    }

    @Override
    public String getDisplayName() {
        return "entity.jitl.alloy_mender";
    }

    @Override
    public AlloyMender getVillagerEntity() {
        return JEntities.ALLOY_MENDER_TYPE.get().create(CompatBase.getLevel());
    }

    @Override
    public List<ItemStack> getPois() {
        return Collections.emptyList();
    }

    @Override
    public boolean hasPois() {
        return false;
    }

    @Override
    public boolean hasLevels() {
        return false;
    }
}
