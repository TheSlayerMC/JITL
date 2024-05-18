package net.jitl.common.entity.base;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.core.init.internal.JDataAttachments;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public abstract class JPathfinderMob extends PathfinderMob implements GeoEntity {

    protected EnumKnowledge knowledge;
    protected float knowledgeAmount = 0.0F;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    protected JPathfinderMob(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType type) {
        return !(level.getBiome(blockPosition()).is(Tags.Biomes.IS_MUSHROOM) || level.getBiome(blockPosition()).is(Biomes.DEEP_DARK));
    }

    protected abstract void controller(AnimatableManager.ControllerRegistrar controllers);

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controller(controllers);
    }

    public void setKnowledge(EnumKnowledge knowledge, float amount) {
        this.knowledge = knowledge;
        this.knowledgeAmount = amount;
    }

    @Override
    public void die(@NotNull DamageSource cause) {
        super.die(cause);
        if(cause.getEntity() instanceof Player player && this.knowledge != null) {
            player.getData(JDataAttachments.PLAYER_STATS).addXP(this.knowledge, this.knowledgeAmount, player);
        }
    }
}
