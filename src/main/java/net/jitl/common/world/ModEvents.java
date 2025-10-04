package net.jitl.common.world;

import net.jitl.common.capability.essence.PlayerEssence;
import net.jitl.common.capability.player.Portal;
import net.jitl.common.capability.stats.PlayerStats;
import net.jitl.common.world.dimension.Dimensions;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JAttributes;
import net.jitl.core.init.internal.JDataAttachments;
import net.jitl.core.init.internal.JDimension;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.spongepowered.asm.mixin.Unique;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.Objects;

@EventBusSubscriber(modid = JITL.MOD_ID)
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

            Level level = player.level();
            if(level instanceof ServerLevel serverLevel) {
                //maybe add a config if falling will change dim
                if(player.getY() <= serverLevel.getMinY()) {
                    if(serverLevel.dimension() == Dimensions.EUCA) {
                        entityFell(player, Dimensions.OVERWORLD);
                    }
                    if(serverLevel.dimension() == Dimensions.CLOUDIA) {
                        entityFell(player, Dimensions.TERRANIA);
                    }
                }
            }
        }
    }

    private static void entityFell(Player entity, ResourceKey<Level> to) {
        Level serverLevel = entity.level();
        MinecraftServer minecraftserver = serverLevel.getServer();
        if(minecraftserver != null) {
            ServerLevel destination = minecraftserver.getLevel(to);
            if(destination != null) {
                entity.setPortalCooldown();
                TeleportTransition transition = new TeleportTransition(destination, new Vec3(entity.getX(), destination.getMaxY() - entity.getBbHeight(), entity.getZ()), entity.getDeltaMovement(), entity.getYRot(), entity.getXRot(), TeleportTransition.DO_NOTHING);
                entity.teleport(transition);
                entity.displayClientMessage(Component.translatable("jitl.death_pos", (int)entity.getX(), (int)entity.getZ()), false);
            }
        }
    }
}