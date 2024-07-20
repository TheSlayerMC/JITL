package net.jitl.common.event;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import java.util.Optional;

@EventBusSubscriber(modid = JITL.MODID)
public class CurioEventHandler {

    @SubscribeEvent
    public static void onPlayerAttacked(LivingDamageEvent.Pre event) {
       LivingEntity entity = event.getEntity();
        if(!entity.level().isClientSide()) {
            if(entity instanceof Player player) {
                Optional<SlotResult> equippedCurio = getEquippedCurio(player, JItems.SKULL_OF_DECAY.get());
                if(equippedCurio.isPresent()) {
                    if (event.getSource().getEntity() instanceof LivingEntity attacker) {
                        attacker.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 1));
                        equippedCurio.get().stack().hurtAndBreak(1,  (ServerLevel)player.level(), player, item -> {});
                    }
                }
            }
        }
    }

    private static Optional<SlotResult> getEquippedCurio(LivingEntity entity, @NotNull Item curio) {
        ICuriosItemHandler curios = CuriosApi.getCuriosInventory(entity).get();
        return curios.findFirstCurio(curio);
    }
}
