package net.jitl.common.block.entity.logic;

import com.mojang.datafixers.util.Either;
import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.event.EventHooks;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Function;

public abstract class DarkNotNeededSpawner extends BaseSpawner {

    private static final Logger LOGGER = LogUtils.getLogger();
    private int spawnDelay = 20;
    private SimpleWeightedRandomList<SpawnData> spawnPotentials = SimpleWeightedRandomList.empty();
    @Nullable
    private SpawnData nextSpawnData;
    private double spin;
    private double oSpin;
    private int minSpawnDelay = 200;
    private int maxSpawnDelay = 800;
    private int spawnCount = 4;
    @Nullable
    private Entity displayEntity;
    private int maxNearbyEntities = 6;
    private int requiredPlayerRange = 16;
    private int spawnRange = 4;

    @Override
    public void setEntityId(EntityType<?> pType, @Nullable Level pLevel, RandomSource pRandom, BlockPos pPos) {
        this.getOrCreateNextSpawnData(pLevel, pRandom, pPos).getEntityToSpawn().putString("id", BuiltInRegistries.ENTITY_TYPE.getKey(pType).toString());
    }

    private boolean isNearPlayer(Level pLevel, BlockPos pPos) {
        return pLevel.hasNearbyAlivePlayer((double)pPos.getX() + 0.5D, (double)pPos.getY() + 0.5D, (double)pPos.getZ() + 0.5D, this.requiredPlayerRange);
    }

    @Override
    public void clientTick(@NotNull Level pLevel, @NotNull BlockPos pPos) {
        if(!this.isNearPlayer(pLevel, pPos)) {
            this.oSpin = this.spin;
        } else if (this.displayEntity != null) {
            RandomSource randomsource = pLevel.getRandom();
            double d0 = (double)pPos.getX() + randomsource.nextDouble();
            double d1 = (double)pPos.getY() + randomsource.nextDouble();
            double d2 = (double)pPos.getZ() + randomsource.nextDouble();
            pLevel.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0, 0.0, 0.0);
            pLevel.addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0, 0.0, 0.0);
            if(this.spawnDelay > 0)
                this.spawnDelay--;

            this.oSpin = this.spin;
            this.spin = (this.spin + (double)(1000.0F / ((float)this.spawnDelay + 200.0F))) % 360.0;
        }
    }

    @Override
    public void serverTick(@NotNull ServerLevel pServerLevel, @NotNull BlockPos pPos) {
        if (this.isNearPlayer(pServerLevel, pPos)) {
            if (this.spawnDelay == -1) {
                this.delay(pServerLevel, pPos);
            }

            if (this.spawnDelay > 0) {
                --this.spawnDelay;
            } else {
                boolean flag = false;
                RandomSource randomsource = pServerLevel.getRandom();
                SpawnData spawndata = this.getOrCreateNextSpawnData(pServerLevel, randomsource, pPos);

                for(int i = 0; i < this.spawnCount; ++i) {
                    CompoundTag compoundtag = spawndata.getEntityToSpawn();
                    Optional<EntityType<?>> optional = EntityType.by(compoundtag);
                    if (optional.isEmpty()) {
                        this.delay(pServerLevel, pPos);
                        return;
                    }

                    ListTag listtag = compoundtag.getList("Pos", 6);
                    int j = listtag.size();
                    double d0 = j >= 1 ? listtag.getDouble(0) : (double)pPos.getX() + (randomsource.nextDouble() - randomsource.nextDouble()) * (double)this.spawnRange + 0.5D;
                    double d1 = j >= 2 ? listtag.getDouble(1) : (double)(pPos.getY() + randomsource.nextInt(3) - 1);
                    double d2 = j >= 3 ? listtag.getDouble(2) : (double)pPos.getZ() + (randomsource.nextDouble() - randomsource.nextDouble()) * (double)this.spawnRange + 0.5D;
                    if (pServerLevel.noCollision(optional.get().getSpawnAABB(d0, d1, d2))) {
                        BlockPos blockpos = BlockPos.containing(d0, d1, d2);
                        if (spawndata.getCustomSpawnRules().isPresent()) {
                            if (!optional.get().getCategory().isFriendly() && pServerLevel.getDifficulty() == Difficulty.PEACEFUL) {
                                continue;
                            }
                        }
                        Entity entity = EntityType.loadEntityRecursive(compoundtag, pServerLevel, EntitySpawnReason.SPAWNER, (p_151310_) -> {
                            p_151310_.moveTo(d0, d1, d2, p_151310_.getYRot(), p_151310_.getXRot());
                            return p_151310_;
                        });
                        if (entity == null) {
                            this.delay(pServerLevel, pPos);
                            return;
                        }

                        int k = pServerLevel.getEntitiesOfClass(entity.getClass(), (new AABB(pPos.getX(), pPos.getY(), pPos.getZ(), pPos.getX() + 1, pPos.getY() + 1, pPos.getZ() + 1)).inflate(this.spawnRange)).size();
                        if (k >= this.maxNearbyEntities) {
                            this.delay(pServerLevel, pPos);
                            return;
                        }

                        entity.moveTo(entity.getX(), entity.getY(), entity.getZ(), randomsource.nextFloat() * 360.0F, 0.0F);
                        if (entity instanceof Mob mob) {
                            if (!EventHooks.checkSpawnPositionSpawner(mob, pServerLevel, EntitySpawnReason.SPAWNER, spawndata, this)) {
                                continue;
                            }

                            boolean flag1 = spawndata.getEntityToSpawn().size() == 1 && spawndata.getEntityToSpawn().contains("id", 8);
                            EventHooks.finalizeMobSpawnSpawner(mob, pServerLevel, pServerLevel.getCurrentDifficultyAt(entity.blockPosition()), EntitySpawnReason.SPAWNER, null, this, flag1);

                            spawndata.getEquipment().ifPresent(mob::equip);
                        }

                        if (!pServerLevel.tryAddFreshEntityWithPassengers(entity)) {
                            this.delay(pServerLevel, pPos);
                            return;
                        }

                        pServerLevel.levelEvent(2004, pPos, 0);
                        pServerLevel.gameEvent(entity, GameEvent.ENTITY_PLACE, blockpos);
                        if(entity instanceof Mob) {
                            ((Mob)entity).spawnAnim();
                        }

                        flag = true;
                    }
                }

                if (flag) {
                    this.delay(pServerLevel, pPos);
                }

            }
        }
    }

    private void delay(Level pLevel, BlockPos pPos) {
        RandomSource randomsource = pLevel.random;
        if (this.maxSpawnDelay <= this.minSpawnDelay) {
            this.spawnDelay = this.minSpawnDelay;
        } else {
            this.spawnDelay = this.minSpawnDelay + randomsource.nextInt(this.maxSpawnDelay - this.minSpawnDelay);
        }

        this.spawnPotentials.getRandom(randomsource).ifPresent(p -> this.setNextSpawnData(pLevel, pPos, p.data()));
        this.broadcastEvent(pLevel, pPos, 1);
    }

    @Override
    public void load(@Nullable Level pLevel, BlockPos pPos, CompoundTag pTag) {
        this.spawnDelay = pTag.getShort("Delay");
        boolean flag = pTag.contains("SpawnData", 10);
        if (flag) {
            SpawnData spawndata = SpawnData.CODEC
                    .parse(NbtOps.INSTANCE, pTag.getCompound("SpawnData"))
                    .resultOrPartial(p_186391_ -> LOGGER.warn("Invalid SpawnData: {}", p_186391_))
                    .orElseGet(SpawnData::new);
            this.setNextSpawnData(pLevel, pPos, spawndata);
        }

        boolean flag1 = pTag.contains("SpawnPotentials", 9);
        if (flag1) {
            ListTag listtag = pTag.getList("SpawnPotentials", 10);
            this.spawnPotentials = SpawnData.LIST_CODEC
                    .parse(NbtOps.INSTANCE, listtag)
                    .resultOrPartial(p_186388_ -> LOGGER.warn("Invalid SpawnPotentials list: {}", p_186388_))
                    .orElseGet(SimpleWeightedRandomList::empty);
        } else {
            this.spawnPotentials = SimpleWeightedRandomList.single(this.nextSpawnData != null ? this.nextSpawnData : new SpawnData());
        }

        if (pTag.contains("MinSpawnDelay", 99)) {
            this.minSpawnDelay = pTag.getShort("MinSpawnDelay");
            this.maxSpawnDelay = pTag.getShort("MaxSpawnDelay");
            this.spawnCount = pTag.getShort("SpawnCount");
        }

        if (pTag.contains("MaxNearbyEntities", 99)) {
            this.maxNearbyEntities = pTag.getShort("MaxNearbyEntities");
            this.requiredPlayerRange = pTag.getShort("RequiredPlayerRange");
        }

        if (pTag.contains("SpawnRange", 99)) {
            this.spawnRange = pTag.getShort("SpawnRange");
        }

        this.displayEntity = null;
    }

    @Override
    public @NotNull CompoundTag save(CompoundTag pTag) {
        pTag.putShort("Delay", (short)this.spawnDelay);
        pTag.putShort("MinSpawnDelay", (short)this.minSpawnDelay);
        pTag.putShort("MaxSpawnDelay", (short)this.maxSpawnDelay);
        pTag.putShort("SpawnCount", (short)this.spawnCount);
        pTag.putShort("MaxNearbyEntities", (short)this.maxNearbyEntities);
        pTag.putShort("RequiredPlayerRange", (short)this.requiredPlayerRange);
        pTag.putShort("SpawnRange", (short)this.spawnRange);
        if (this.nextSpawnData != null) {
            pTag.put(
                    "SpawnData",
                    SpawnData.CODEC
                            .encodeStart(NbtOps.INSTANCE, this.nextSpawnData)
                            .getOrThrow(p_337966_ -> new IllegalStateException("Invalid SpawnData: " + p_337966_))
            );
        }

        pTag.put("SpawnPotentials", SpawnData.LIST_CODEC.encodeStart(NbtOps.INSTANCE, this.spawnPotentials).result().orElseThrow());
        return pTag;
    }

    @Nullable
    public Entity getOrCreateDisplayEntity(Level pLevel, BlockPos pPos) {
        if (this.displayEntity == null) {
            CompoundTag compoundtag = this.getOrCreateNextSpawnData(pLevel, pLevel.getRandom(), pPos).getEntityToSpawn();
            if (!compoundtag.contains("id", 8)) {
                return null;
            }

            this.displayEntity = EntityType.loadEntityRecursive(compoundtag, pLevel, EntitySpawnReason.SPAWNER, Function.identity());
            if (compoundtag.size() == 1 && this.displayEntity instanceof Mob) {
            }
        }

        return this.displayEntity;
    }

    @Override
    public boolean onEventTriggered(@NotNull Level pLevel, int pId) {
        if(pId == 1) {
            if(pLevel.isClientSide)
                this.spawnDelay = this.minSpawnDelay;
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void setNextSpawnData(@Nullable Level pLevel, @NotNull BlockPos pPos, @NotNull SpawnData pNextSpawnData) {
        this.nextSpawnData = pNextSpawnData;
    }

    private SpawnData getOrCreateNextSpawnData(@Nullable Level pLevel, RandomSource pRandom, BlockPos pPos) {
        if(this.nextSpawnData == null) {
            this.setNextSpawnData(pLevel, pPos, this.spawnPotentials.getRandom(pRandom).map(WeightedEntry.Wrapper::data).orElseGet(SpawnData::new));
        }
        return this.nextSpawnData;
    }

    @Override
    public abstract void broadcastEvent(@NotNull Level pLevel, @NotNull BlockPos pPos, int pEventId);

    @Override
    public double getSpin() {
        return this.spin;
    }

    @Override
    public double getoSpin() {
        return this.oSpin;
    }

    @Override
    @Nullable
    public Either<BlockEntity, Entity> getOwner() {
        return null;
    }
}
