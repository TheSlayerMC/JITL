package net.jitl.common.items.base;

import net.jitl.common.entity.base.JBoat;
import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class JBoatItem extends Item {

    private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);
    private final JBoat.Type type;

    public JBoatItem(Properties p, JBoat.Type type) {
        super(p);
        this.type = type;
    }

    @Override
    public InteractionResult use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        HitResult hitresult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);
        if(hitresult.getType() == HitResult.Type.MISS) {
            return InteractionResult.PASS;
        } else {
            Vec3 vec3 = player.getViewVector(1.0F);
            List<Entity> list = level.getEntities(player, player.getBoundingBox().expandTowards(vec3.scale(5.0D)).inflate(1.0D), ENTITY_PREDICATE);
            if (!list.isEmpty()) {
                Vec3 vec31 = player.getEyePosition();

                for(Entity entity : list) {
                    AABB aabb = entity.getBoundingBox().inflate(entity.getPickRadius());
                    if (aabb.contains(vec31)) {
                        return InteractionResult.PASS;
                    }
                }
            }
            if(hitresult.getType() == HitResult.Type.BLOCK) {
                AbstractBoat boat = this.getBoat(level, hitresult, itemstack, player);
                boat.setYRot(player.getYRot());
                if(!level.noCollision(boat, boat.getBoundingBox())) {
                    return InteractionResult.FAIL;
                } else {
                    if(!level.isClientSide()) {
                        level.addFreshEntity(boat);
                        level.gameEvent(player, GameEvent.ENTITY_PLACE, BlockPos.containing(hitresult.getLocation()));
                        if(!player.getAbilities().instabuild) {
                            itemstack.shrink(1);
                        }
                    }
                    player.awardStat(Stats.ITEM_USED.get(this));
                    return InteractionResult.SUCCESS_SERVER;
                }
            } else {
                return InteractionResult.PASS;
            }
        }
    }

    @Nullable
    private AbstractBoat getBoat(Level level, HitResult hitResult, ItemStack stack, Player player) {
        AbstractBoat abstractboat = getBoatFromItem().create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (abstractboat != null) {
            Vec3 vec3 = hitResult.getLocation();
            abstractboat.setInitialPos(vec3.x, vec3.y, vec3.z);
            if (level instanceof ServerLevel) {
                ServerLevel serverlevel = (ServerLevel)level;
                EntityType.createDefaultStackConfig(serverlevel, stack, player).accept(abstractboat);
            }
        }
        return abstractboat;
    }

    public EntityType<? extends JBoat> getBoatFromItem() {
        EntityType<? extends JBoat> boat = JEntities.GOLD_EUCA_BOAT_TYPE.get();
        if(this == JItems.GOLDEN_EUCA_BOAT.get()) {
            boat = JEntities.GOLD_EUCA_BOAT_TYPE.get();
        }
        if(this == JItems.BROWN_EUCA_BOAT.get()) {
            boat = JEntities.BROWN_EUCA_BOAT_TYPE.get();
        }
        if(this == JItems.DEPTHS_BOAT.get()) {
            boat = JEntities.DEPTHS_BOAT_TYPE.get();
        }
        if(this == JItems.BURNED_BOAT.get()) {
            boat = JEntities.BURNED_BOAT_TYPE.get();
        }
        if(this == JItems.CORBA_BOAT.get()) {
            boat = JEntities.CORBA_BOAT_TYPE.get();
        }
        if(this == JItems.FROZEN_BOAT.get()) {
            boat = JEntities.FROZEN_BOAT_TYPE.get();
        }
        if(this == JItems.CLOUDIA_BOAT.get()) {
            boat = JEntities.CLOUDIA_BOAT_TYPE.get();
        }
        if(this == JItems.TERRANIAN_BOAT.get()) {
            boat = JEntities.TERRANIA_BOAT_TYPE.get();
        }
        return boat;
    }
}