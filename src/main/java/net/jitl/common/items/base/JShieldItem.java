package net.jitl.common.items.base;

import net.jitl.core.init.JITL;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.component.BlocksAttacks;

import java.util.List;
import java.util.Optional;

public class JShieldItem extends ShieldItem {

    public final ResourceLocation texture;

    public JShieldItem(Properties p, String name, int uses, Item repairItem) {
        super(p.durability(uses).equippableUnswappable(EquipmentSlot.OFFHAND)
                .component(
                        DataComponents.BLOCKS_ATTACKS,
                        new BlocksAttacks(
                                0.25F,
                                1.0F,
                                List.of(new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)),
                                new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F),
                                Optional.of(DamageTypeTags.BYPASSES_SHIELD),
                                Optional.of(SoundEvents.SHIELD_BLOCK),
                                Optional.of(SoundEvents.SHIELD_BREAK)
                        )
                )
                .component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK).repairable(repairItem));
        this.texture = JITL.rl("textures/shield/" + name + "_shield.png");
    }
}
