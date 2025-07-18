package net.jitl.common.entity.projectile;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class EssenciaBoltEntity extends LightningBolt {

    private static final EntityDataAccessor<Integer> DATA_COLOR_ID = SynchedEntityData.defineId(EssenciaBoltEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> DATA_STRIKE_SOUND_VOL = SynchedEntityData.defineId(EssenciaBoltEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> DATA_THUNDER_SOUND_VOL = SynchedEntityData.defineId(EssenciaBoltEntity.class, EntityDataSerializers.FLOAT);

    public EssenciaBoltEntity(EntityType<? extends LightningBolt> entityType, Level worldIn) {
        super(entityType, worldIn);
    }

    public void setARGB(int argb) {
        this.entityData.set(DATA_COLOR_ID, argb);
    }

    public int getARGB() {
        return this.entityData.get(DATA_COLOR_ID);
    }

    public void setStrikeVolume(float volume) {
        this.entityData.set(DATA_STRIKE_SOUND_VOL, volume);
    }

    public float getStrikeVolume() {
        return this.entityData.get(DATA_STRIKE_SOUND_VOL);
    }

    public void setThunderVolume(float volume) {
        this.entityData.set(DATA_THUNDER_SOUND_VOL, volume);
    }

    public float getThunderVolume() {
        return this.entityData.get(DATA_THUNDER_SOUND_VOL);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(DATA_COLOR_ID, 0);
        pBuilder.define(DATA_STRIKE_SOUND_VOL, 2.0F);
        pBuilder.define(DATA_THUNDER_SOUND_VOL, 10000.0F);
    }

    @Override
    public void addAdditionalSaveData(ValueOutput compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Color", getARGB());
        compound.putFloat("Strike Volume", getStrikeVolume());
        compound.putFloat("Thunder Volume", getThunderVolume());
    }

    @Override
    public void readAdditionalSaveData(ValueInput compound) {
        super.readAdditionalSaveData(compound);
        this.setARGB(compound.getIntOr("Color", 0));
        this.setStrikeVolume(compound.getFloatOr("Strike Volume", 0F));
        this.setThunderVolume(compound.getFloatOr("Thunder Volume", 0F));
    }
}
