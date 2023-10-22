package net.jitl.common.event;

import net.jitl.core.init.JITL;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class CurioEventHandler {

   /* @SubscribeEvent
    public static void onPlayerAttacked(LivingHurtEvent event) {
       LivingEntity entity = event.getEntity();
        if (!entity.level().isClientSide()) {
            if (entity instanceof Player player) {
                Optional<SlotResult> equippedCurio = getEquippedCurio(player, JItems.SKULL_OF_DECAY.get());
                if (equippedCurio.isPresent()) {
                    if (event.getSource().getEntity() instanceof LivingEntity attacker) {
                        attacker.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 1));
                        equippedCurio.get().stack().hurtAndBreak(1, player, (context) -> context.broadcastBreakEvent(player.getUsedItemHand()));
                    }
                }
            }
        }
    }

    private static Optional<SlotResult> getEquippedCurio(LivingEntity entity, @NotNull Item curio) {
        return CuriosApi.getCuriosHelper().findFirstCurio(entity, curio);
    }

    public static void onKeyPressed(Player player) {

    }*/
}
