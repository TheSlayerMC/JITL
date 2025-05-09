package net.jitl.common.entity.base;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.core.init.internal.JDataAttachments;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public abstract class JAnimalEntity extends Animal implements GeoEntity {

    protected EnumKnowledge knowledge;
    protected float knowledgeAmount = 0.0F;

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    protected JAnimalEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected abstract void controller(AnimatableManager.ControllerRegistrar controllers);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controller(controllers);
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, @NotNull EntitySpawnReason type) {
        return !(level.getBiome(blockPosition()).is(Tags.Biomes.IS_MUSHROOM) || level.getBiome(blockPosition()).is(Biomes.DEEP_DARK));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
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