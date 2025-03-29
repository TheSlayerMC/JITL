package net.jitl.client.model;

import net.minecraft.client.model.BabyModelTransform;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.WolfRenderState;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.Set;

@OnlyIn(Dist.CLIENT)
public class ShiverwolfModel extends EntityModel<WolfRenderState> {

    public static final MeshTransformer BABY_TRANSFORMER = new BabyModelTransform(Set.of("head"));
    private static final String REAL_HEAD = "real_head";
    private static final String UPPER_BODY = "upper_body";
    private static final String REAL_TAIL = "real_tail";
    private final ModelPart head;
    private final ModelPart realHead;
    private final ModelPart body;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart tail;
    private final ModelPart realTail;
    private final ModelPart upperBody;
    private static final int LEG_SIZE = 8;

    public ShiverwolfModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.realHead = this.head.getChild("real_head");
        this.body = root.getChild("body");
        this.upperBody = root.getChild("upper_body");
        this.rightHindLeg = root.getChild("right_hind_leg");
        this.leftHindLeg = root.getChild("left_hind_leg");
        this.rightFrontLeg = root.getChild("right_front_leg");
        this.leftFrontLeg = root.getChild("left_front_leg");
        this.tail = root.getChild("tail");
        this.realTail = this.tail.getChild("real_tail");
    }

    public static MeshDefinition createMeshDefinition(CubeDeformation cubeDeformation) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        float f = 13.5F;
        PartDefinition partdefinition1 = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(-1.0F, 13.5F, -7.0F));
        partdefinition1.addOrReplaceChild("real_head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.0F, -2.0F, 6.0F, 6.0F, 4.0F, cubeDeformation).texOffs(16, 14).addBox(-2.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F, cubeDeformation).texOffs(16, 14).addBox(2.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F, cubeDeformation).texOffs(0, 10).addBox(-0.5F, -0.001F, -5.0F, 3.0F, 3.0F, 4.0F, cubeDeformation), PartPose.ZERO);
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(18, 14).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 9.0F, 6.0F, cubeDeformation), PartPose.offsetAndRotation(0.0F, 14.0F, 2.0F, 1.5707964F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("upper_body", CubeListBuilder.create().texOffs(21, 0).addBox(-3.0F, -3.0F, -3.0F, 8.0F, 6.0F, 7.0F, cubeDeformation), PartPose.offsetAndRotation(-1.0F, 14.0F, -3.0F, 1.5707964F, 0.0F, 0.0F));
        CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(0, 18).addBox(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, cubeDeformation);
        partdefinition.addOrReplaceChild("right_hind_leg", cubelistbuilder, PartPose.offset(-2.5F, 16.0F, 7.0F));
        partdefinition.addOrReplaceChild("left_hind_leg", cubelistbuilder, PartPose.offset(0.5F, 16.0F, 7.0F));
        partdefinition.addOrReplaceChild("right_front_leg", cubelistbuilder, PartPose.offset(-2.5F, 16.0F, -4.0F));
        partdefinition.addOrReplaceChild("left_front_leg", cubelistbuilder, PartPose.offset(0.5F, 16.0F, -4.0F));
        PartDefinition partdefinition2 = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 12.0F, 8.0F, 0.62831855F, 0.0F, 0.0F));
        partdefinition2.addOrReplaceChild("real_tail", CubeListBuilder.create().texOffs(9, 18).addBox(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, cubeDeformation), PartPose.ZERO);
        return meshdefinition;
    }

    public void setupAnim(WolfRenderState p_365164_) {
        super.setupAnim(p_365164_);
        float f = p_365164_.walkAnimationPos;
        float f1 = p_365164_.walkAnimationSpeed;
        if (p_365164_.isAngry) {
            this.tail.yRot = 0.0F;
        } else {
            this.tail.yRot = Mth.cos(f * 0.6662F) * 1.4F * f1;
        }

        if (p_365164_.isSitting) {
            float f2 = p_365164_.ageScale;
            ModelPart var10000 = this.upperBody;
            var10000.y += 2.0F * f2;
            this.upperBody.xRot = 1.2566371F;
            this.upperBody.yRot = 0.0F;
            var10000 = this.body;
            var10000.y += 4.0F * f2;
            var10000 = this.body;
            var10000.z -= 2.0F * f2;
            this.body.xRot = 0.7853982F;
            var10000 = this.tail;
            var10000.y += 9.0F * f2;
            var10000 = this.tail;
            var10000.z -= 2.0F * f2;
            var10000 = this.rightHindLeg;
            var10000.y += 6.7F * f2;
            var10000 = this.rightHindLeg;
            var10000.z -= 5.0F * f2;
            this.rightHindLeg.xRot = 4.712389F;
            var10000 = this.leftHindLeg;
            var10000.y += 6.7F * f2;
            var10000 = this.leftHindLeg;
            var10000.z -= 5.0F * f2;
            this.leftHindLeg.xRot = 4.712389F;
            this.rightFrontLeg.xRot = 5.811947F;
            var10000 = this.rightFrontLeg;
            var10000.x += 0.01F * f2;
            var10000 = this.rightFrontLeg;
            var10000.y += 1.0F * f2;
            this.leftFrontLeg.xRot = 5.811947F;
            var10000 = this.leftFrontLeg;
            var10000.x -= 0.01F * f2;
            var10000 = this.leftFrontLeg;
            var10000.y += 1.0F * f2;
        } else {
            this.rightHindLeg.xRot = Mth.cos(f * 0.6662F) * 1.4F * f1;
            this.leftHindLeg.xRot = Mth.cos(f * 0.6662F + 3.1415927F) * 1.4F * f1;
            this.rightFrontLeg.xRot = Mth.cos(f * 0.6662F + 3.1415927F) * 1.4F * f1;
            this.leftFrontLeg.xRot = Mth.cos(f * 0.6662F) * 1.4F * f1;
        }

        this.realHead.zRot = p_365164_.headRollAngle + p_365164_.getBodyRollAngle(0.0F);
        this.upperBody.zRot = p_365164_.getBodyRollAngle(-0.08F);
        this.body.zRot = p_365164_.getBodyRollAngle(-0.16F);
        this.realTail.zRot = p_365164_.getBodyRollAngle(-0.2F);
        this.head.xRot = p_365164_.xRot * 0.017453292F;
        this.head.yRot = p_365164_.yRot * 0.017453292F;
        this.tail.xRot = p_365164_.tailAngle;
    }
}
