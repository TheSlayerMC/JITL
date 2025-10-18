package net.jitl.common.entity.base;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.common.entity.IJourneyBoss;
import net.jitl.common.entity.boss.BossCrystal;
import net.jitl.core.init.internal.JDataAttachments;
import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class JBossEntity extends JMonsterEntity implements IDontAttackWhenPeaceful, IJourneyBoss {

    private final ServerBossEvent BOSS_INFO = (ServerBossEvent)new ServerBossEvent(Objects.requireNonNull(getDisplayName()), BossEvent.BossBarColor.PINK, BossEvent.BossBarOverlay.NOTCHED_20).setDarkenScreen(false).setCreateWorldFog(false);

    protected JBossEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.BOSS_INFO.id = this.getUUID();
    }

//    @Override
//    protected boolean shouldDespawnInPeaceful() {
//        return false;
//    }todo

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public boolean wantsToAttack(LivingEntity target, LivingEntity living) {
        return level().getDifficulty() != Difficulty.PEACEFUL;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JSounds.BOSS_DEATH.get();
    }

    @Override
    public float getSoundVolume() {
        return 10.0F;
    }

    protected abstract BossCrystal.Type getDeathCrystalType();
    public abstract ResourceKey<LootTable> lootTable();

    @Override
    protected void readAdditionalSaveData(ValueInput compound) {
        super.readAdditionalSaveData(compound);
        if(hasCustomName())
            this.BOSS_INFO.setName(Objects.requireNonNull(getDisplayName()));

        this.BOSS_INFO.id = getUUID();
    }

    @Override
    public void setCustomName(@org.jetbrains.annotations.Nullable Component name) {
        super.setCustomName(name);
        this.BOSS_INFO.setName(Objects.requireNonNull(getDisplayName()));
    }

    @Override
    protected void customServerAiStep(ServerLevel level) {
        super.customServerAiStep(level);
        this.BOSS_INFO.setProgress(getHealth() / getMaxHealth());
    }

    @Override
    public void startSeenByPlayer(@NotNull ServerPlayer player) {
        super.startSeenByPlayer(player);

        if(showBarWhenSpawned())
            this.BOSS_INFO.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(@NotNull ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.BOSS_INFO.removePlayer(player);
    }

    public abstract boolean showBarWhenSpawned();

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource d, float f) {
        if(!showBarWhenSpawned()) {
            if (d.getEntity() instanceof Player) {
                AABB axisalignedbb = AABB.unitCubeFromLowerCorner(this.position()).inflate(10);
                for (Player player : this.level().getEntitiesOfClass(Player.class, axisalignedbb)) {
                    this.BOSS_INFO.addPlayer((ServerPlayer)player);
                }
            }
        }
        return super.hurtServer(level, d, f);
    }

    protected EnumKnowledge knowledge;
    protected int knowledgeLevel = 0;

    public void setKnowledge(EnumKnowledge knowledge, int level) {
        this.knowledge = knowledge;
        this.knowledgeLevel = level;
    }

    @Override
    public void die(@NotNull DamageSource s) {
        super.die(s);
        if(s.getEntity() instanceof Player player && this.knowledge != null) {
            player.getData(JDataAttachments.PLAYER_STATS).addLevel(this.knowledge, this.knowledgeLevel);
        }

        if(!level().isClientSide()) {
            BossCrystal crystal = new BossCrystal(JEntities.BOSS_CRYSTAL_TYPE.get(), level(), getDeathCrystalType(), lootTable());
            crystal.setPos(position().add(0, 1, 0));
            level().addFreshEntity(crystal);
        }
    }
}
