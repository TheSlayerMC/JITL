package net.jitl.common.block.entity;

import net.jitl.common.block.SenterianAltar;
import net.jitl.common.entity.senterian.MiniSentryLord;
import net.jitl.common.entity.senterian.MiniSentryStalker;
import net.jitl.common.entity.senterian.MiniSentryWalker;
import net.jitl.core.init.internal.JBlockEntities;
import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animatable.processing.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class SenterianAltarTile extends BlockEntity implements GeoBlockEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public int spawnTimer;
    public int spawnCount;

    public SenterianAltarTile(BlockPos pPos, BlockState pBlockState) {
        super(JBlockEntities.SENTERIAN_ALTAR.get(), pPos, pBlockState);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(tag, pRegistries);
        tag.putInt("spawnTimer", 0);
        tag.putInt("spawnCount", 0);
    }

    @Override
    public void loadAdditional(@NotNull CompoundTag tag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(tag, pRegistries);
        spawnTimer = tag.getIntOr("spawnTimer", 0);
        spawnCount = tag.getIntOr("spawnCount", 0);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return this.saveWithoutMetadata(pRegistries);
    }

    private final RawAnimation ROTATE = RawAnimation.begin().thenLoop("animation.senterian_altar.active");
    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.senterian_altar.idle");

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(state -> {
            if(state.getData(SenterianAltar.IS_ACTIVE_TICKET)) {//todo
                return state.setAndContinue(this.ROTATE);
            } else {
                return state.setAndContinue(this.IDLE);
            }
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, SenterianAltarTile entity) {
        boolean isFull = state.getValue(SenterianAltar.IS_ACTIVE);
        boolean isSpawning = state.getValue(SenterianAltar.IS_SPAWNING);

        if(isFull) {
            if(entity.spawnTimer == 0) {
                entity.spawnTimer = 50;
                level.playSound(null, pos, JSounds.SENTRY_ALTAR_ACTIVATE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
            }

            if(entity.spawnTimer == 5) {
                level.playSound(null, pos, JSounds.SENTRY_ALTAR_DEACTIVATE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
            }

            if(entity.spawnTimer >= 0)
                entity.spawnTimer--;

            if(entity.spawnTimer <= 0)
                entity.spawnTimer = 0;

            if(entity.spawnTimer == 0) {
                entity.spawnMob(pos, level);
                level.setBlock(pos, state.setValue(SenterianAltar.IS_SPAWNING, true), 2);
                entity.spawnCount++;
            }

            if (entity.spawnCount == 5) {
                entity.spawnCount = 0;
                level.setBlock(pos, state.setValue(SenterianAltar.IS_ACTIVE, false).setValue(SenterianAltar.IS_SPAWNING, false), 2);
            }
        }
    }

    private void spawnMob(BlockPos pos, Level level) {
        int x = pos.getX(), y = pos.getY(), z = pos.getZ();
        LivingEntity mob = null;
        RandomSource random = RandomSource.create();
        mob = switch (random.nextInt(3)) {
            case 0 -> new MiniSentryLord(JEntities.MINI_SENTRY_LORD_TYPE.get(), this.getLevel());
            case 1 -> new MiniSentryStalker(JEntities.MINI_SENTRY_STALKER_TYPE.get(), this.getLevel());
            case 2 -> new MiniSentryWalker(JEntities.MINI_SENTRY_WALKER_TYPE.get(), this.getLevel());
            default -> new MiniSentryLord(JEntities.MINI_SENTRY_LORD_TYPE.get(), getLevel());
        };

        if(!level.isClientSide) {
            mob.setPos(x + 0.5F, y + 1F, z + 0.5F);
            level.addFreshEntity(mob);
        }
    }
}