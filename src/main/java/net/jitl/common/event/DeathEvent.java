package net.jitl.common.event;

import net.jitl.core.config.JCommonConfig;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class DeathEvent {

    @SubscribeEvent
    public static void onEntityDrop(LivingDropsEvent event) {
        RandomSource random = RandomSource.create();
        LivingEntity entity = event.getEntity();
        if(event.getSource().getEntity() instanceof Player player) {

            if(entity instanceof Ghast) {
                if(random.nextInt(3) == 0)
                    entity.spawnAtLocation(JItems.GHAST_TENTACLE.get(), 1);
            }

            if(JCommonConfig.ENABLE_LOOT_POUCH_DROP.get()) {
                if(random.nextInt(JCommonConfig.COMMON_LOOT_CHANCE.get()) == 0) {
                    entity.spawnAtLocation(JItems.LOOT_POUCH.get(), 1);
                }
                if(random.nextInt(JCommonConfig.GOLD_LOOT_CHANCE.get()) == 0) {
                    entity.spawnAtLocation(JItems.GOLD_LOOT_POUCH.get(), 1);
                }
                if(random.nextInt(JCommonConfig.DIAMOND_LOOT_CHANCE.get()) == 0) {
                    entity.spawnAtLocation(JItems.DIAMOND_LOOT_POUCH.get(), 1);
                }
            }
        }
    }
}
