package net.jitl.common.event;

import net.jitl.core.init.JITL;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class CurioEventHandler {
//TODO
    @SubscribeEvent
    public static void onPlayerAttacked(LivingHurtEvent event) {
      /*  LivingEntity entity = event.getEntity();
        if (!entity.level.isClientSide()) {
            if (entity instanceof Player player) {
                Optional<SlotResult> equippedCurio = getEquippedCurio(player, JItems.SKULL_OF_DECAY.get());
                if (equippedCurio.isPresent()) {
                    if (event.getSource().getEntity() instanceof LivingEntity attacker) {
                        attacker.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 1));
                        equippedCurio.get().stack().hurtAndBreak(1, player, (context) -> context.broadcastBreakEvent(player.getUsedItemHand()));
                    }
                }
            }
        }*/
    }

    /*private static Optional<SlotResult> getEquippedCurio(LivingEntity entity, Item curio) {
        return CuriosApi.getCuriosHelper().findFirstCurio(entity, curio);
    }*/

    public static void onKeyPressed(Player player) {

    }
}
