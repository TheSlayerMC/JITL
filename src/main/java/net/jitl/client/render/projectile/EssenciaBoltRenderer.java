package net.jitl.client.render.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jitl.client.render.projectile.state.EssentiaLightningBoltRenderState;
import net.jitl.common.entity.projectile.EssenciaBoltEntity;
import net.jitl.core.helper.internal.DrawHelper;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

public class EssenciaBoltRenderer extends EntityRenderer<EssenciaBoltEntity, EssentiaLightningBoltRenderState> {

    public EssenciaBoltRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    public void submit(EssentiaLightningBoltRenderState entityIn, PoseStack p_435280_, SubmitNodeCollector p_433826_, CameraRenderState p_451052_) {
        float[] afloat = new float[8];
        float[] afloat1 = new float[8];
        float f = 0.0F;
        float f1 = 0.0F;
        RandomSource randomsource = RandomSource.create(entityIn.seed);

        for(int i = 7; i >= 0; --i) {
            afloat[i] = f;
            afloat1[i] = f1;
            f += (float)(randomsource.nextInt(11) - 5);
            f1 += (float)(randomsource.nextInt(11) - 5);
        }

        float finalF = f;
        float finalF1 = f1;
        p_433826_.submitCustomGeometry(p_435280_, RenderType.lightning(), (p_434636_, ivertexbuilder) -> {
            Matrix4f matrix4f = p_434636_.pose();

            for(int j = 0; j < 4; ++j) {
                RandomSource randomsource1 = RandomSource.create(entityIn.seed);

                for(int k = 0; k < 3; ++k) {
                    int l = 7;
                    int i1 = 0;
                    if (k > 0) {
                        l = 7 - k;
                    }

                    if (k > 0) {
                        i1 = l - 2;
                    }

                    float f4 = afloat[l] - finalF;
                    float f5 = afloat1[l] - finalF1;

                    for(int j1 = l; j1 >= i1; --j1) {
                        float f6 = f4;
                        float f7 = f5;
                        if (k == 0) {
                            f4 += (float)(randomsource1.nextInt(11) - 5);
                            f5 += (float)(randomsource1.nextInt(11) - 5);
                        } else {
                            f4 += (float)(randomsource1.nextInt(31) - 15);
                            f5 += (float)(randomsource1.nextInt(31) - 15);
                        }

                        float f12 = 0.1F + (float)j * 0.2F;
                        if (k == 0) {
                            f12 *= (float)j1 * 0.1F + 1.0F;
                        }

                        float f13 = 0.1F + (float)j * 0.2F;
                        if (k == 0) {
                            f13 *= ((float)j1 - 1.0F) * 0.1F + 1.0F;
                        }

                        int argb = entityIn.argb;
                        float alpha = 0.3F;

                        float red = DrawHelper.getRed(argb) / 255F;
                        float green = DrawHelper.getGreen(argb) / 255F;
                        float blue = DrawHelper.getBlue(argb) / 255F;

                        quad(matrix4f, ivertexbuilder, f4, f5, j1, f6, f7, red, green, blue, alpha, f12, f13, false, false, true, false);
                        quad(matrix4f, ivertexbuilder, f4, f5, j1, f6, f7, red, green, blue, alpha, f12, f13, true, false, true, true);
                        quad(matrix4f, ivertexbuilder, f4, f5, j1, f6, f7, red, green, blue, alpha, f12, f13, true, true, false, true);
                        quad(matrix4f, ivertexbuilder, f4, f5, j1, f6, f7, red, green, blue, alpha, f12, f13, false, true, false, false);
                    }
                }
            }

        });
    }

    private static void quad(Matrix4f matrix, VertexConsumer builder, float startCenterX, float startCenterZ, int startY, float endCenterX, float endCenterZ, float r, float g, float b, float alpha, float oneDirectionExpansion, float anotherDirectionExpansion, boolean boolean_, boolean boolean1_, boolean boolean2_, boolean boolean3_) {
        builder.addVertex(matrix, startCenterX + (boolean_ ? anotherDirectionExpansion : -anotherDirectionExpansion) + 0.5F, (float) (startY * 16), startCenterZ + (boolean1_ ? anotherDirectionExpansion : -anotherDirectionExpansion) + 0.5F).setColor(r, g, b, alpha);
        builder.addVertex(matrix, endCenterX + (boolean_ ? oneDirectionExpansion : -oneDirectionExpansion) + 0.5F, (float) ((startY + 1) * 16), endCenterZ + (boolean1_ ? oneDirectionExpansion : -oneDirectionExpansion) + 0.5F).setColor(r, g, b, alpha);
        builder.addVertex(matrix, endCenterX + (boolean2_ ? oneDirectionExpansion : -oneDirectionExpansion) + 0.5F, (float) ((startY + 1) * 16), endCenterZ + (boolean3_ ? oneDirectionExpansion : -oneDirectionExpansion) + 0.5F).setColor(r, g, b, alpha);
        builder.addVertex(matrix, startCenterX + (boolean2_ ? anotherDirectionExpansion : -anotherDirectionExpansion) + 0.5F, (float) (startY * 16), startCenterZ + (boolean3_ ? anotherDirectionExpansion : -anotherDirectionExpansion) + 0.5F).setColor(r, g, b, alpha);
    }

    public void extractRenderState(EssenciaBoltEntity e, EssentiaLightningBoltRenderState s, float f) {
        super.extractRenderState(e, s, f);
        s.seed = e.seed;
        s.argb = e.getARGB();
    }

    @Override
    public @NotNull EssentiaLightningBoltRenderState createRenderState() {
        return new EssentiaLightningBoltRenderState();
    }
}