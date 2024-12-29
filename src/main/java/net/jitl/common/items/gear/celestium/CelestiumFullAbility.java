package net.jitl.common.items.gear.celestium;

import net.jitl.common.capability.player.CelestiumArmorAbility;
import net.jitl.common.items.gear.FullArmorAbility;
import net.jitl.core.init.internal.JDataAttachments;
import net.jitl.core.init.internal.JDataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class CelestiumFullAbility  extends FullArmorAbility {

    public CelestiumFullAbility(CompoundTag nbt) {
        super(nbt);
    }

    @Override
    public void tick(Player entity) {
//        System.out.println("Dash cooldown for " + entity.getName().getString() + ": " + tag.getInt("cooldown"));
//        System.out.println("Dash for " + entity.getName().getString() + " " + (tag.getBoolean("Jump ready") ? "is" : "is not") + " ready");
        CelestiumArmorAbility ability = entity.getData(JDataAttachments.CELESTIUM_ARMOR.get());
        int cooldown = ability.getCooldown();
        if(cooldown <= 0) {
            if(entity.onGround() && !ability.getJumpReady()) {
                ability.setJumpReady(true);
                double halfSize = entity.getBbWidth() / 2;
                ((ServerLevel) entity.level()).sendParticles(ParticleTypes.POOF, entity.getX(), entity.getY(), entity.getZ(), 200, halfSize, entity.getBbHeight(), halfSize, 0.1);
            }
        } else {
            ability.setCooldown(cooldown - 1);
        }
        ability.sendPacket(entity);
    }

    @Override
    public void keyPressed(Player entity) {
        CelestiumArmorAbility ability = entity.getData(JDataAttachments.CELESTIUM_ARMOR.get());
        System.out.println("PRESSED");
        if(!entity.onGround() && ability.getJumpReady()) {
            System.out.println("Dash");
            Vec3 look = entity.getLookAngle();
            entity.setDeltaMovement(look.x() * 2.5, 0, look.z() * 2.5);
            entity.hurtMarked = true;
            ((ServerLevel) entity.level()).sendParticles(ParticleTypes.EXPLOSION, entity.getX(), entity.getY(), entity.getZ(), 1, 0, 0, 0, 1);
            ability.setJumpReady(false);
            ability.setCooldown(40);
        }
    }
}
