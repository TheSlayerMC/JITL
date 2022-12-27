package net.jitl.common.event;

import net.jitl.common.entity.base.JBossEntity;
import net.jitl.common.entity.base.JBossInfo;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class BossDeathEvent {

    public static void deathEvent(LivingDeathEvent event) {
        if(event.getSource().getEntity() instanceof Player player && event.getEntity() instanceof JBossEntity boss) {
            ServerPlayer p = (ServerPlayer)player;
            JBossInfo.removeInfo(p, boss.getEvent(), boss);

        }
    }
}
