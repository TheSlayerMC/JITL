package net.jitl.common.items.curios.ring;

import net.jitl.common.capability.player.CurioCooldown;
import net.jitl.common.items.curios.JCurioItem;
import net.jitl.core.init.internal.JDataAttachments;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

public class JRingItem extends JCurioItem {
    private Holder<MobEffect> potion;

    public JRingItem(Item.Properties properties) {
        super(properties);
    }

    public JRingItem effect(Holder<MobEffect> effectSupplier) {
        this.potion = effectSupplier;
        return this;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if(slotContext.entity() instanceof Player player) {
            CurioCooldown curios = slotContext.entity().getData(JDataAttachments.CURIOS_COOLDOWN);
            if(!player.level().isClientSide()) {
                int cooldown = curios.getCooldown();
                if(cooldown == 0) {
                    if(player.hasEffect(potion)) {
                        player.removeEffect(potion);
                        curios.setCooldown(400);
                    }
                } else {
                    curios.setCooldown(Math.max(0, cooldown - (player.hasEffect(potion) ? 1 : 4)));
                }
                curios.sendPacket(player);
            }
        }
    }
}
