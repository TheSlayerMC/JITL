package net.jitl.common.world;

import net.jitl.client.essence.PlayerEssence;
import net.jitl.client.essence.PlayerEssenceProvider;
import net.jitl.client.knowledge.PlayerKnowledgeProvider;
import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.atomic.AtomicInteger;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class ModEvents {

    private static int regenTicks = 75;

    @SubscribeEvent
    public static void onPlayerAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).isPresent()) {
                event.addCapability(new ResourceLocation(JITL.MODID, "essence"), new PlayerEssenceProvider());
            }

            if(!event.getObject().getCapability(PlayerKnowledgeProvider.PLAYER_KNOWLEDGE).isPresent()) {
                event.addCapability(new ResourceLocation(JITL.MODID, "knowledge"), new PlayerKnowledgeProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });

            event.getOriginal().getCapability(PlayerKnowledgeProvider.PLAYER_KNOWLEDGE).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerKnowledgeProvider.PLAYER_KNOWLEDGE).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.side == LogicalSide.SERVER) {
            event.player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).ifPresent(essence -> {
                if(event.phase == TickEvent.Phase.END) {
                    if(regenTicks-- <= 0) {
                        essence.regen();
                        essence.update(event.player);
                        regenTicks = 75;
                    }
                }
            });

            event.player.getCapability(PlayerKnowledgeProvider.PLAYER_KNOWLEDGE).ifPresent(knowledge -> {
                knowledge.update(event.player);
            });
        }
    }
}