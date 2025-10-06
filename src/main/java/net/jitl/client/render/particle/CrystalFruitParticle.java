package net.jitl.client.render.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.NotNull;

public class CrystalFruitParticle extends SingleQuadParticle {

    private final SpriteSet sprites;

    protected CrystalFruitParticle(ClientLevel worldIn, double x, double y, double z, double motionX, double motionY, double motionZ, SpriteSet spriteWithAge) {
        super(worldIn, x, y, z, motionX, motionY, motionZ, spriteWithAge.first());
        this.sprites = spriteWithAge;
        int i = (int) (32.0D / (Math.random() * 0.8D + 0.2D));
        this.lifetime = (int) Math.max((float) i * 0.9F, 1.0F);
        this.gravity = 0.003F;
        this.setSpriteFromAge(spriteWithAge);
    }

    @Override
    public SingleQuadParticle.Layer getLayer() {
        return SingleQuadParticle.Layer.OPAQUE;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            this.setSpriteFromAge(this.sprites);
            this.oRoll = this.roll;
            this.yd -= this.gravity;
            this.move(this.xd, this.yd, this.zd);
            if (!this.removed) {
                this.xd *= 0.01F;
                this.yd *= 0.01F;
                this.zd *= 0.01F;
            }
        }
    }

    @Override
    public void move(double x, double y, double z) {
        this.setBoundingBox(this.getBoundingBox().move(x, y, z));
        this.setLocationFromBoundingbox();
    }

    @Override
    public float getQuadSize(float scaleFactor) {
        float f = 1 + ((float) this.age + scaleFactor) / (float) this.lifetime;
        return this.quadSize * (1.0F - f * f * 0.5F);
    }

    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Factory(SpriteSet spriteSet) {
            this.sprite = spriteSet;
        }

        @Override
        public Particle createParticle(@NotNull SimpleParticleType typeIn, @NotNull ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
            return new CrystalFruitParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.sprite);
        }
    }
}
