package net.jitl.common.world;

import net.jitl.common.capability.essence.PlayerEssence;
import net.jitl.common.capability.stats.PlayerStats;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JAttributes;
import net.jitl.core.init.internal.JDataAttachments;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = JITL.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getEntity().getData(JDataAttachments.PLAYER_STATS).copyFrom(event.getOriginal().getData(JDataAttachments.PLAYER_STATS));
            event.getEntity().getData(JDataAttachments.ESSENCE).copyFrom(event.getOriginal().getData(JDataAttachments.ESSENCE));
            event.getEntity().getData(JDataAttachments.KEY_PRESSED).copyFrom(event.getOriginal().getData(JDataAttachments.KEY_PRESSED));
            event.getEntity().getData(JDataAttachments.PLAYER_ARMOR).copyFrom(event.getOriginal().getData(JDataAttachments.PLAYER_ARMOR));
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.phase == TickEvent.Phase.END) {

            PlayerStats stats = event.player.getData(JDataAttachments.PLAYER_STATS);
            stats.update(event.player);

            PlayerEssence essence = event.player.getData(JDataAttachments.ESSENCE);
            if(essence.isRegenReady()) {
                essence.addEssence(event.player, (float) Objects.requireNonNull(event.player.getAttribute(JAttributes.ESSENCE_REGEN_SPEED.get())).getValue());
            } else {
                essence.setBurnout(essence.getBurnout() - 0.1F);
            }
            essence.sendPacket(event.player);
        }
    }
}