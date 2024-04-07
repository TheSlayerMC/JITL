package net.jitl.common.world;

import net.jitl.common.capability.essence.PlayerEssence;
import net.jitl.common.capability.essence.PlayerEssenceProvider;
import net.jitl.common.capability.gear.PlayerArmor;
import net.jitl.common.capability.gear.PlayerArmorProvider;
import net.jitl.common.capability.keypressed.PressedKeyCap;
import net.jitl.common.capability.keypressed.PressedKeyCapProvider;
import net.jitl.common.capability.stats.PlayerStats;
import net.jitl.common.capability.stats.PlayerStatsProvider;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JAttributes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = JITL.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {

    public static void onPlayerAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player player) {
            if(!player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).isPresent()) {
                event.addCapability(new ResourceLocation(JITL.MODID, "essence"), new PlayerEssenceProvider());
            }
            if(!player.getCapability(PlayerStatsProvider.PLAYER_STATS).isPresent()) {
                event.addCapability(new ResourceLocation(JITL.MODID, "player_stats"), new PlayerStatsProvider());
            }
            if(!player.getCapability(PressedKeyCapProvider.PRESSED_KEY_CAP).isPresent()) {
                event.addCapability(new ResourceLocation(JITL.MODID, "pressed_keys"), new PressedKeyCapProvider());
            }
            if(!player.getCapability(PlayerArmorProvider.PLAYER_ARMOR).isPresent()) {
                event.addCapability(new ResourceLocation(JITL.MODID, "player_armor"), new PlayerArmorProvider());
            }
        }
    }

    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerEssence.class);
        event.register(PlayerStats.class);
        event.register(PressedKeyCap.class);
        event.register(PlayerArmor.class);
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).ifPresent(oldStore ->
                    event.getOriginal().getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).ifPresent(newStore ->
                            newStore.copyFrom(oldStore)));

            event.getOriginal().getCapability(PlayerStatsProvider.PLAYER_STATS).ifPresent(oldStore ->
                    event.getOriginal().getCapability(PlayerStatsProvider.PLAYER_STATS).ifPresent(newStore ->
                            newStore.copyFrom(oldStore)));

            event.getOriginal().getCapability(PressedKeyCapProvider.PRESSED_KEY_CAP).ifPresent(oldStore ->
                    event.getOriginal().getCapability(PressedKeyCapProvider.PRESSED_KEY_CAP).ifPresent(newStore ->
                            newStore.copyFrom(oldStore)));

            event.getOriginal().getCapability(PlayerArmorProvider.PLAYER_ARMOR).ifPresent(oldStore ->
                    event.getOriginal().getCapability(PlayerArmorProvider.PLAYER_ARMOR).ifPresent(newStore ->
                            newStore.copyFrom(oldStore)));
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.player != null) {
            event.player.getCapability(PlayerStatsProvider.PLAYER_STATS).ifPresent(stats -> {
                if (event.phase == TickEvent.Phase.END) {
                    stats.update(event.player);
                }
            });
            event.player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).ifPresent(essence -> {
                if (event.phase == TickEvent.Phase.END) {
                    if (essence.isRegenReady()) {
                        essence.addEssence(event.player, (float) Objects.requireNonNull(event.player.getAttribute(JAttributes.ESSENCE_REGEN_SPEED.get())).getValue());
                    } else {
                        essence.setBurnout(essence.getBurnout() - 0.1F);
                    }
                    essence.sendPacket(event.player);
                }
            });
        }
    }
}