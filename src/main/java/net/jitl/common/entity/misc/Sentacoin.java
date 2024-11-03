package net.jitl.common.entity.misc;

import net.jitl.core.init.internal.JDataAttachments;
import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class Sentacoin extends Entity {

    public int coinAge;
    private int coinHealth = 5;
    private Type type = Type.COIN;

    public Sentacoin(EntityType<? extends Sentacoin> entity, Entity spawningEntity) {
        super(entity, spawningEntity.level());

        if(entity == JEntities.SENTACOIN_BAG_TYPE.get())
            this.type = Type.BAG;

        this.setPos(spawningEntity.getX(), spawningEntity.getY(), spawningEntity.getZ());
        this.setYRot((float)(this.random.nextDouble() * 360.0D));
        this.setDeltaMovement((this.random.nextDouble() * (double)0.2F - (double)0.1F) * 2.0D, this.random.nextDouble() * 0.2D * 2.0D, (this.random.nextDouble() * (double)0.2F - (double)0.1F) * 2.0D);
    }

    public Sentacoin(EntityType<? extends Sentacoin> entity, Level level) {
        super(entity, level);
    }

    @Override
    protected Entity.@NotNull MovementEmission getMovementEmission() {
        return Entity.MovementEmission.NONE;
    }

    @Override
    public void tick() {
        super.tick();

        this.xo = this.getX();
        this.yo = this.getY();
        this.zo = this.getZ();

        if(this.isEyeInFluid(FluidTags.WATER)) {
            this.setUnderwaterMovement();
        } else if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.03D, 0.0D));
        }

        if(this.level().getFluidState(this.blockPosition()).is(FluidTags.LAVA))
            this.setDeltaMovement(((this.random.nextFloat() - this.random.nextFloat()) * 0.2F), (double)0.2F, (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.2F));

        if(!this.level().noCollision(this.getBoundingBox()))
            this.moveTowardsClosestSpace(this.getX(), (this.getBoundingBox().minY + this.getBoundingBox().maxY) / 2.0D, this.getZ());


        this.move(MoverType.SELF, this.getDeltaMovement());

        float f = 0.98F;
        if(this.onGround()) {
            BlockPos pos = getBlockPosBelowThatAffectsMyMovement();
            f = this.level().getBlockState(pos).getFriction(this.level(), pos, this) * 0.98F;
        }

        this.setDeltaMovement(this.getDeltaMovement().multiply(f, 0.98D, f));
        if(this.onGround())
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, -0.9D, 1.0D));

        this.coinAge++;
        if(this.coinAge >= 3000)
            this.discard();
    }

    private void setUnderwaterMovement() {
        Vec3 vec3 = this.getDeltaMovement();
        this.setDeltaMovement(vec3.x * (double)0.99F, Math.min(vec3.y + (double)5.0E-4F, 0.06F), vec3.z * (double)0.99F);
    }

    @Override
    public boolean hurtServer(@NotNull ServerLevel serverLevel, DamageSource damageSource, float amount) {
        if (damageSource.getEntity() instanceof Player player) {
            if (this.isInvisibleTo(player)) {
                return false;
            } else if (this.level().isClientSide) {
                return true;
            } else {
                this.markHurt();
                this.coinHealth = (int) ((float) this.coinHealth - amount);
                if (this.coinHealth <= 0)
                    this.discard();
                return true;
            }
        }
        return false;
    }

    @Override
    public void playerTouch(@NotNull Player player) {
        if(!this.level().isClientSide()) {
            int amount = 0;
            int bagRand = random.nextInt(10);
            int coinRand = random.nextInt(1);
            switch(type) {
                case BAG -> amount = 5 + bagRand;
                case COIN -> amount = 1 + coinRand;
            }
            final int finalAmount = amount;
                player.take(this, 1);
            player.getData(JDataAttachments.PLAYER_STATS).addSentacoins(finalAmount);
                if(this.type == Type.BAG) {
                    for(int i = 0; i < 5; i++) {
                        this.playSound(JSounds.COIN_PICKUP.get(), 1.0F, 1.0F + random.nextFloat());
                    }
                } else {
                    this.playSound(JSounds.COIN_PICKUP.get(), 1.0F, 1.0F + random.nextFloat());
                }
                this.discard();
        }
    }

    @Override
    public @NotNull BlockPos getBlockPosBelowThatAffectsMyMovement() {
        return this.getOnPos(0.999999F);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) { }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        this.coinHealth = compound.getShort("Health");
        this.coinAge = compound.getShort("Age");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.putShort("Health", (short)this.coinHealth);
        compound.putShort("Age", (short)this.coinAge);
    }

    @Override
    public boolean isAttackable() {
        return false;
    }

    @Override
    public @NotNull SoundSource getSoundSource() {
        return SoundSource.AMBIENT;
    }

    public enum Type {
        COIN,
        BAG
    }
}
