package net.jitl.common.block.entity.logic;

import com.mojang.datafixers.util.Either;
import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ProblemReporter;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Function;

public abstract class DarkNotNeededSpawner extends BaseSpawner {

    private static final Logger LOGGER = LogUtils.getLogger();
    private int spawnDelay = 20;
    private WeightedList<SpawnData> spawnPotentials = WeightedList.of();
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
    public void serverTick(ServerLevel serverLevel, BlockPos pos) {
        if (this.isNearPlayer(serverLevel, pos)) {
            if (this.spawnDelay == -1) {
                this.delay(serverLevel, pos);
            }

            if (this.spawnDelay > 0) {
                --this.spawnDelay;
            } else {
                boolean flag = false;
                RandomSource randomsource = serverLevel.getRandom();
                SpawnData spawndata = this.getOrCreateNextSpawnData(serverLevel, randomsource, pos);

                for (int i = 0; i < this.spawnCount; ++i) {
                    try (ProblemReporter.ScopedCollector problemreporter$scopedcollector = new ProblemReporter.ScopedCollector(this::toString, LOGGER)) {
                        ValueInput compoundtag = TagValueInput.create(problemreporter$scopedcollector, serverLevel.registryAccess(), spawndata.getEntityToSpawn());
                        Optional<EntityType<?>> optional = EntityType.by(compoundtag);
                        if (optional.isEmpty()) {
                            this.delay(serverLevel, pos);
                            return;
                        }

                        Vec3 vec3 = (Vec3) compoundtag.read("Pos", Vec3.CODEC).orElseGet(() -> new Vec3((double) pos.getX() + (randomsource.nextDouble() - randomsource.nextDouble()) * (double) this.spawnRange + (double) 0.5F, (double) (pos.getY() + randomsource.nextInt(3) - 1), (double) pos.getZ() + (randomsource.nextDouble() - randomsource.nextDouble()) * (double) this.spawnRange + (double) 0.5F));
                        if (serverLevel.noCollision(((EntityType) optional.get()).getSpawnAABB(vec3.x, vec3.y, vec3.z))) {
                            BlockPos blockpos = BlockPos.containing(vec3);
                            if (spawndata.getCustomSpawnRules().isPresent()) {
                                if (!((EntityType) optional.get()).getCategory().isFriendly() && serverLevel.getDifficulty() == Difficulty.PEACEFUL) {
                                    continue;
                                }

                                SpawnData.CustomSpawnRules spawndata$customspawnrules = (SpawnData.CustomSpawnRules) spawndata.getCustomSpawnRules().get();
                                if (!spawndata$customspawnrules.isValidPosition(blockpos, serverLevel)) {
                                    continue;
                                }
                            } else if (!SpawnPlacements.checkSpawnRules((EntityType) optional.get(), serverLevel, EntitySpawnReason.SPAWNER, blockpos, serverLevel.getRandom())) {
                                continue;
                            }

                            Entity entity = EntityType.loadEntityRecursive(compoundtag, serverLevel, EntitySpawnReason.SPAWNER, (p_404552_) -> {
                                p_404552_.snapTo(vec3.x, vec3.y, vec3.z, p_404552_.getYRot(), p_404552_.getXRot());
                                return p_404552_;
                            });
                            if (entity == null) {
                                this.delay(serverLevel, pos);
                                return;
                            }

                            int j = serverLevel.getEntities(EntityTypeTest.forExactClass(entity.getClass()), (new AABB((double) pos.getX(), (double) pos.getY(), (double) pos.getZ(), (double) (pos.getX() + 1), (double) (pos.getY() + 1), (double) (pos.getZ() + 1))).inflate((double) this.spawnRange), EntitySelector.NO_SPECTATORS).size();
                            if (j >= this.maxNearbyEntities) {
                                this.delay(serverLevel, pos);
                                return;
                            }

                            entity.snapTo(entity.getX(), entity.getY(), entity.getZ(), randomsource.nextFloat() * 360.0F, 0.0F);
                            if (entity instanceof Mob) {
                                Mob mob = (Mob) entity;
                                if (!EventHooks.checkSpawnPositionSpawner(mob, serverLevel, EntitySpawnReason.SPAWNER, spawndata, this)) {
                                    continue;
                                }

                                boolean flag1 = spawndata.getEntityToSpawn().size() == 1 && spawndata.getEntityToSpawn().getString("id").isPresent();
                                EventHooks.finalizeMobSpawnSpawner(mob, serverLevel, serverLevel.getCurrentDifficultyAt(entity.blockPosition()), EntitySpawnReason.SPAWNER, (SpawnGroupData) null, this, flag1);
                            }

                            if (!serverLevel.tryAddFreshEntityWithPassengers(entity)) {
                                this.delay(serverLevel, pos);
                                return;
                            }

                            serverLevel.levelEvent(2004, pos, 0);
                            serverLevel.gameEvent(entity, GameEvent.ENTITY_PLACE, blockpos);
                            if (entity instanceof Mob) {
                                ((Mob) entity).spawnAnim();
                            }

                            flag = true;
                        }
                    }
                }

                if (flag) {
                    this.delay(serverLevel, pos);
                }
            }
        }
    }

    private void delay(Level level, BlockPos pos) {
        RandomSource randomsource = level.random;
        if (this.maxSpawnDelay <= this.minSpawnDelay) {
            this.spawnDelay = this.minSpawnDelay;
        } else {
            this.spawnDelay = this.minSpawnDelay + randomsource.nextInt(this.maxSpawnDelay - this.minSpawnDelay);
        }

        this.spawnPotentials.getRandom(randomsource).ifPresent((p_393311_) -> this.setNextSpawnData(level, pos, p_393311_));
        this.broadcastEvent(level, pos, 1);
    }

    @Override
    public void load(@Nullable Level level, BlockPos pos, ValueInput tag) {
        this.spawnDelay = tag.getShortOr("Delay", (short)20);
        tag.read("SpawnData", SpawnData.CODEC).ifPresent((p_400944_) -> this.setNextSpawnData(level, pos, p_400944_));
        this.spawnPotentials = (WeightedList)tag.read("SpawnPotentials", SpawnData.LIST_CODEC).orElseGet(() -> WeightedList.of(this.nextSpawnData != null ? this.nextSpawnData : new SpawnData()));
        this.minSpawnDelay = tag.getIntOr("MinSpawnDelay", 200);
        this.maxSpawnDelay = tag.getIntOr("MaxSpawnDelay", 800);
        this.spawnCount = tag.getIntOr("SpawnCount", 4);
        this.maxNearbyEntities = tag.getIntOr("MaxNearbyEntities", 6);
        this.requiredPlayerRange = tag.getIntOr("RequiredPlayerRange", 16);
        this.spawnRange = tag.getIntOr("SpawnRange", 4);
        this.displayEntity = null;
    }

    @Override
    public void save(ValueOutput tag) {
        tag.putShort("Delay", (short)this.spawnDelay);
        tag.putShort("MinSpawnDelay", (short)this.minSpawnDelay);
        tag.putShort("MaxSpawnDelay", (short)this.maxSpawnDelay);
        tag.putShort("SpawnCount", (short)this.spawnCount);
        tag.putShort("MaxNearbyEntities", (short)this.maxNearbyEntities);
        tag.putShort("RequiredPlayerRange", (short)this.requiredPlayerRange);
        tag.putShort("SpawnRange", (short)this.spawnRange);
        tag.storeNullable("SpawnData", SpawnData.CODEC, this.nextSpawnData);
        tag.store("SpawnPotentials", SpawnData.LIST_CODEC, this.spawnPotentials);
    }

    @Nullable
    public Entity getOrCreateDisplayEntity(Level level, BlockPos pos) {
        if (this.displayEntity == null) {
            CompoundTag compoundtag = this.getOrCreateNextSpawnData(level, level.getRandom(), pos).getEntityToSpawn();
            if (compoundtag.getString("id").isEmpty()) {
                return null;
            }

            this.displayEntity = EntityType.loadEntityRecursive(compoundtag, level, EntitySpawnReason.SPAWNER, Function.identity());
            if (compoundtag.size() == 1 && this.displayEntity instanceof Mob) {
            }
        }

        return this.displayEntity;
    }

    @Override
    public boolean onEventTriggered(@NotNull Level pLevel, int pId) {
        if(pId == 1) {
            if(pLevel.isClientSide())
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
        if (this.nextSpawnData != null) {
            return this.nextSpawnData;
        } else {
            this.setNextSpawnData(pLevel, pPos, this.spawnPotentials.getRandom(pRandom).orElseGet(SpawnData::new));
            return this.nextSpawnData;
        }
    }

    @Override
    public abstract void broadcastEvent(@NotNull Level pLevel, @NotNull BlockPos pPos, int pEventId);

    @Override
    public double getSpin() {
        return this.spin;
    }

    @Override
    public double getOSpin() {
        return this.oSpin;
    }

    @Override
    @Nullable
    public Either<BlockEntity, Entity> getOwner() {
        return null;
    }
}
