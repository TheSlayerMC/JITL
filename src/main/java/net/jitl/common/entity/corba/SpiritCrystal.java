package net.jitl.common.entity.corba;

import net.jitl.common.block.TotemBlock;
import net.jitl.common.entity.base.MobStats;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JLootTables;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.Objects;

public class SpiritCrystal extends PathfinderMob implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final NonNullList<ItemStack> storedItems = NonNullList.create();

    public SpiritCrystal(EntityType<? extends SpiritCrystal> entityType, Level world) {
        super(entityType, world);
        this.setDeltaMovement(0, 0, 0);
    }

    @Override
    protected void registerGoals() { }

    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.spirit_crystal.idle");

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 5, state -> state.setAndContinue(IDLE)));
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, MobStats.NPC_HEALTH)
                .add(Attributes.KNOCKBACK_RESISTANCE, MobStats.STANDARD_KNOCKBACK_RESISTANCE)
                .add(Attributes.FOLLOW_RANGE, MobStats.STANDARD_FOLLOW_RANGE)
                .add(Attributes.MOVEMENT_SPEED, MobStats.STANDARD_MOVEMENT_SPEED).build();
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        //ContainerHelper.saveAllItems(compound, storedItems);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        //ContainerHelper.loadAllItems(compound, storedItems);
    }

    @Override
    protected InteractionResult mobInteract(Player playerEntity, InteractionHand hand) {
        int check_radius = 10;
        int totalAwakeTotems = 0;

        final Level world = this.level();
        final BlockPos entityPos = BlockPos.containing(this.position());
        for(int x = -check_radius; x <= check_radius; x++) {
            for(int z = -check_radius; z <= check_radius; z++) {
                for(int y = -check_radius; y <= check_radius; y++) {
                    final BlockPos pos = entityPos.offset(x, y, z);
                    final Block block = world.getBlockState(pos).getBlock();
                    if(block != JBlocks.TOTEM_BASE.get() && block instanceof TotemBlock) {
                        if(world.getBlockState(pos).getValue(TotemBlock.AWAKE)) {
                            totalAwakeTotems++;
                        }
                    }
                }
            }
        }
        if(totalAwakeTotems >= 4) {
            if(!this.level().isClientSide) {
                LootTable table = Objects.requireNonNull(level().getServer()).reloadableRegistries().getLootTable(JLootTables.SPIRIT_CRYSTAL);
                List<ItemStack> itemList = table.getRandomItems(new LootParams.Builder((ServerLevel)level()).withParameter(LootContextParams.THIS_ENTITY, playerEntity)
                        .withParameter(LootContextParams.ORIGIN, playerEntity.position()).create(LootContextParamSets.GIFT));
                storedItems.addAll(itemList);
                for(ItemStack storedItem : storedItems) {
                    ItemStack item = new ItemStack(storedItem.getItem());
                    item.setCount(storedItem.getCount());
                    ItemEntity entity = new ItemEntity(level(), this.getX(), this.getY(), this.getZ(), item);
                    level().addFreshEntity(entity);
                }
                playSound(JSounds.CRYSTAL_PICKUP.get(), 1.0F, 1.0F);
                remove(RemovalReason.DISCARDED);
            }
        }
        return super.mobInteract(playerEntity, hand);
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public boolean canCollideWith(Entity e) {
        return false;
    }

    @Override
    public boolean canBeLeashed() {
        return false;
    }

    @Override
    public boolean isAttackable() {
        return true;
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
    public boolean hurt(DamageSource source, float amount) {
        return source.isCreativePlayer();
    }

    @Override
    public boolean fireImmune() {
        return true;
    }
}