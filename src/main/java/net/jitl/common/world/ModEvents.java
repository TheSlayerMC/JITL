package net.jitl.common.world;

import net.jitl.common.capability.essence.PlayerEssence;
import net.jitl.common.capability.player.Portal;
import net.jitl.common.capability.stats.PlayerStats;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JAttributes;
import net.jitl.core.init.internal.JDataAttachments;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.Objects;

@EventBusSubscriber(modid = JITL.MOD_ID)//bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getEntity().getData(JDataAttachments.PLAYER_STATS).copyFrom(event.getOriginal().getData(JDataAttachments.PLAYER_STATS));
            event.getEntity().getData(JDataAttachments.ESSENCE).copyFrom(event.getOriginal().getData(JDataAttachments.ESSENCE));
            event.getEntity().getData(JDataAttachments.KEY_PRESSED).copyFrom(event.getOriginal().getData(JDataAttachments.KEY_PRESSED));
            event.getEntity().getData(JDataAttachments.PLAYER_ARMOR).copyFrom(event.getOriginal().getData(JDataAttachments.PLAYER_ARMOR));
            event.getEntity().getData(JDataAttachments.PORTAL_OVERLAY).copyFrom(event.getOriginal().getData(JDataAttachments.PORTAL_OVERLAY));
            event.getEntity().getData(JDataAttachments.CELESTIUM_ARMOR).copyFrom(event.getOriginal().getData(JDataAttachments.CELESTIUM_ARMOR));
            event.getEntity().getData(JDataAttachments.ITEM_COOLDOWN).copyFrom(event.getOriginal().getData(JDataAttachments.ITEM_COOLDOWN));
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if(event.getEntity() instanceof Player player) {
            PlayerStats stats = player.getData(JDataAttachments.PLAYER_STATS);
            stats.update(player);

            Portal portal = player.getData(JDataAttachments.PORTAL_OVERLAY);
            portal.serverTick();

            PlayerEssence essence = player.getData(JDataAttachments.ESSENCE);
            if (essence.isRegenReady()) {
                essence.addEssence(player, (float) Objects.requireNonNull(player.getAttribute(JAttributes.ESSENCE_REGEN_SPEED)).getValue());
            } else {
                essence.setBurnout(essence.getBurnout() - 0.1F);
            }
            essence.sendPacket(player);
        }
    }
}