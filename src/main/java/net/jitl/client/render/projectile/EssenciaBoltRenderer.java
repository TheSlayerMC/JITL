package net.jitl.client.render.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jitl.client.render.projectile.state.EssentiaLightningBoltRenderState;
import net.jitl.common.entity.projectile.EssenciaBoltEntity;
import net.jitl.core.helper.internal.DrawHelper;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

import java.util.Random;

public class EssenciaBoltRenderer extends EntityRenderer<EssenciaBoltEntity, EssentiaLightningBoltRenderState> {

    public EssenciaBoltRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(EssentiaLightningBoltRenderState entityIn, @NotNull PoseStack matrixStackIn, @NotNull MultiBufferSource bufferIn, int packedLightIn) {
        float[] afloat = new float[8];
        float[] afloat1 = new float[8];
        float f = 0.0F;
        float f1 = 0.0F;
        Random random = new Random(entityIn.seed);

        for (int i = 7; i >= 0; --i) {
            afloat[i] = f;
            afloat1[i] = f1;
            f += (float) (random.nextInt(11) - 5);
            f1 += (float) (random.nextInt(11) - 5);
        }

        VertexConsumer ivertexbuilder = bufferIn.getBuffer(RenderType.lightning());
        Matrix4f matrix4f = matrixStackIn.last().pose();

        for (int layer = 0; layer < 4; ++layer) {
            Random random1 = new Random(entityIn.seed);

            for (int rayIndex = 0; rayIndex < 3; ++rayIndex) {
                int l = 7;
                int i1 = 0;
                if (rayIndex > 0) {
                    l = 7 - rayIndex;
                }

                if (rayIndex > 0) {
                    i1 = l - 2;
                }

                float f2 = afloat[l] - f;
                float f3 = afloat1[l] - f1;

                for (int j1 = l; j1 >= i1; --j1) {
                    boolean mainRay = rayIndex == 0;

                    float f4 = f2;
                    float f5 = f3;
                    if (mainRay) {
                        f2 += (float) (random1.nextInt(11) - 5);
                        f3 += (float) (random1.nextInt(11) - 5);
                    } else {
                        f2 += (float) (random1.nextInt(31) - 15);
                        f3 += (float) (random1.nextInt(31) - 15);
                    }

                    float oneDirectionExpansion = 0.1F + (float) layer * 0.2F;
                    if (mainRay) {
                        oneDirectionExpansion = (float) ((double) oneDirectionExpansion * ((double) j1 * 0.1D + 1.0D));
                    }

                    float anotherDirectionExpansion = 0.1F + (float) layer * 0.2F;
                    if (mainRay) {
                        anotherDirectionExpansion *= (float) (j1 - 1) * 0.1F + 1.0F;
                    }

                    oneDirectionExpansion *= 1F / 3;
                    anotherDirectionExpansion *= 1F / 3;

                    int argb = entityIn.argb;
                    float alpha = 0.3F;

                    float red = DrawHelper.getRed(argb) / 255F;
                    float green = DrawHelper.getGreen(argb) / 255F;
                    float blue = DrawHelper.getBlue(argb) / 255F;

                    quad(matrix4f, ivertexbuilder, f2, f3, j1, f4, f5, red, green, blue, alpha, oneDirectionExpansion, anotherDirectionExpansion, false, false, true, false);
                    quad(matrix4f, ivertexbuilder, f2, f3, j1, f4, f5, red, green, blue, alpha, oneDirectionExpansion, anotherDirectionExpansion, true, false, true, true);
                    quad(matrix4f, ivertexbuilder, f2, f3, j1, f4, f5, red, green, blue, alpha, oneDirectionExpansion, anotherDirectionExpansion, true, true, false, true);
                    quad(matrix4f, ivertexbuilder, f2, f3, j1, f4, f5, red, green, blue, alpha, oneDirectionExpansion, anotherDirectionExpansion, false, true, false, false);
                }
            }
        }

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