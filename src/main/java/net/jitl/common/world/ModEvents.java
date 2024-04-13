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
//            event.getOriginal().getData(JDataAttachments.ESSENCE)(oldStore ->
//                    event.getOriginal().getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).ifPresent(newStore ->
//                            newStore.copyFrom(oldStore)));
//
//            event.getOriginal().getCapability(PlayerStatsProvider.PLAYER_STATS).ifPresent(oldStore ->
//                    event.getOriginal().getCapability(PlayerStatsProvider.PLAYER_STATS).ifPresent(newStore ->
//                            newStore.copyFrom(oldStore)));
//
//            event.getOriginal().getCapability(PressedKeyCapProvider.PRESSED_KEY_CAP).ifPresent(oldStore ->
//                    event.getOriginal().getCapability(PressedKeyCapProvider.PRESSED_KEY_CAP).ifPresent(newStore ->
//                            newStore.copyFrom(oldStore)));
//
//            event.getOriginal().getCapability(PlayerArmorProvider.PLAYER_ARMOR).ifPresent(oldStore ->
//                    event.getOriginal().getCapability(PlayerArmorProvider.PLAYER_ARMOR).ifPresent(newStore ->
//                            newStore.copyFrom(oldStore)));
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