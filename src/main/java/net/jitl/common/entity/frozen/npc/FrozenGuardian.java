package net.jitl.common.entity.frozen.npc;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.client.util.ChatUtils;
import net.jitl.common.block.entity.PedestalTile;
import net.jitl.common.capability.stats.PlayerStats;
import net.jitl.common.entity.projectile.EssenciaBoltEntity;
import net.jitl.common.items.LoreScrollItem;
import net.jitl.core.init.internal.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class FrozenGuardian extends PathfinderMob implements GeoEntity {

    private static final EntityDataAccessor<Boolean> DATA_IS_ACTIVATED = SynchedEntityData.defineId(FrozenGuardian.class, EntityDataSerializers.BOOLEAN);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private int death_timer;

    public FrozenGuardian(EntityType<? extends FrozenGuardian> entityType, Level world) {
        super(entityType, world);
        this.death_timer = 50;
    }

    @Override
    protected void registerGoals() { }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.frozen_guardian.idle");

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("controller", 5, state -> state.setAndContinue(IDLE)));
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).build();
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        if(isActivated()) {
            this.death_timer--;
            this.level().addParticle(ParticleTypes.CLOUD,
                    this.getX() - 0.5D + random.nextDouble(),
                    this.getY() + 0.5D + random.nextDouble(),
                    this.getZ() - 0.5D + random.nextDouble(),
                    this.random.nextGaussian() * 0.05D,
                    0.15D,
                    this.random.nextGaussian() * 0.05D);
            if (death_timer <= 0) {
                for (int i = 0; i < 24; ++i) {
                    this.level().addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE,
                            this.getX() - Mth.nextDouble(random, -0.45D, 0.75D),
                            this.getY() + Mth.nextDouble(random, 0.5D, 2.0D),
                            this.getZ() - Mth.nextDouble(random, -0.45D, 0.75D),
                            this.random.nextGaussian() * 0.05D,
                            0.15D,
                            this.random.nextGaussian() * 0.05D);
                }
                this.playSound(SoundEvents.FIRE_EXTINGUISH, 1.0F, 1.0F);
                this.remove(RemovalReason.DISCARDED);
                
                if(!level().isClientSide()) {
                    ItemStack scrollStack = new ItemStack(JItems.LORE_SCROLL.asItem());
                    LoreScrollItem.bindScrollEntry(scrollStack, ScrollEntries.FROZEN_DESPAIR, EnumKnowledge.FROZEN, 25);
                    this.level().addFreshEntity(new ItemEntity(level(), this.position().x + 0.5F, this.position().y + 1.4F, this.position().z + 0.5F, scrollStack));
                }
                this.death_timer = 100;
            }
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(DATA_IS_ACTIVATED, false);
    }

    @Override
    public void addAdditionalSaveData(ValueOutput compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("activated", this.entityData.get(DATA_IS_ACTIVATED));
    }

    @Override
    public void readAdditionalSaveData(ValueInput compound) {
        super.readAdditionalSaveData(compound);
        setActivated(compound.getBooleanOr("activated", false));
    }

    public void setActivated(boolean activated) {
        this.entityData.set(DATA_IS_ACTIVATED, activated);
    }

    public boolean isActivated() {
        return this.entityData.get(DATA_IS_ACTIVATED);
    }

    @Override
    public boolean canBeCollidedWith(@Nullable Entity p_423659_) {
        return false;
    }

    @Override
    public boolean canBeLeashed() {
        return false;
    }

    @Override
    public boolean isAttackable() {
        return false;
    }

    @Override
    public void push(Entity entity) { }

    @Override
    protected void doPush(Entity entityIn) { }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean hurtServer(ServerLevel p_376221_, DamageSource p_376460_, float p_376610_) {
        return false;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    protected InteractionResult mobInteract(Player playerEntity, InteractionHand hand) {
        int check_radius = 8;
        int totalPedestals = 0;
        final Level world = this.level();
        final BlockPos entityPos = BlockPos.containing(this.position());
        for (int x = -check_radius; x <= check_radius; x++) {
            for (int z = -check_radius; z <= check_radius; z++) {
                for (int y = -check_radius; y <= check_radius; y++) {
                    final BlockPos pos = entityPos.offset(x, y, z);
                    final Block block = world.getBlockState(pos).getBlock();
                    if (block == JBlocks.FROZEN_PEDESTAL.get()) {
                        PedestalTile tile = (PedestalTile) world.getBlockEntity(pos);
                        if (tile != null && tile.getItem(0).getItem().equals(JItems.FROSTBORN_SOUL.get())) {
                            if (isActivated()) {
                                if (!level().isClientSide()) {
                                    summonLightning(pos);
                                    disableFrozenBlizzard();
                                }
                                tile.setItem(0, ItemStack.EMPTY);
                            }
                            totalPedestals++;
                        }
                    }
                }
            }
        }
        if(totalPedestals >= 8) {
            this.playSound(JSounds.FROZEN_GUARDIAN_DEATH.get(), 1.5F, 1.0F);
            ChatUtils.addDialogStyleChat(playerEntity, "jitl.npc.frozen_guardian1");
            setActivated(true);
        } else {
            ChatUtils.addDialogStyleChat(playerEntity, "jitl.npc.frozen_guardian2");
        }
        return super.mobInteract(playerEntity, hand);
    }

    public void summonLightning(BlockPos pos) {
        EssenciaBoltEntity bolt = new EssenciaBoltEntity(JEntities.ESSENCIA_BOLT_TYPE.get(), level());
        bolt.setPos(pos.getX(), pos.getY() + 1.0D, pos.getZ());
        bolt.setARGB(0x5acbff);
        bolt.setVisualOnly(true);
        if(!level().isClientSide())
            this.level().addFreshEntity(bolt);
    }

    public void disableFrozenBlizzard() {
        int playerArea = 10;
        AABB axisalignedbb = AABB.unitCubeFromLowerCorner(this.position()).inflate(playerArea);
        for (Player player : this.level().getEntitiesOfClass(Player.class, axisalignedbb)) {
            PlayerStats stats = player.getData(JDataAttachments.PLAYER_STATS);
            stats.setBlizzard(true);
            stats.setLevel(EnumKnowledge.FROZEN, 100);
        }
    }
}
