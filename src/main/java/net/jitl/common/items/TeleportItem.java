package net.jitl.common.items;

import net.jitl.common.items.base.JItem;
import net.jitl.core.helper.IEssenceItem;
import net.jitl.core.helper.MathHelper;
import net.jitl.core.init.internal.JDataAttachments;
import net.jitl.core.init.internal.JItems;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.BiFunction;

public class TeleportItem extends JItem implements IEssenceItem {

    private final int essenceUsage;

    public TeleportItem(int essence, int maxUses) {
        super(JItems.itemProps().stacksTo(1).durability(maxUses));
        this.essenceUsage = essence;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand usedHand) {
        float var4 = player.getXRot();
        float var5 = player.getYRot();
        double var6 = player.getX();
        double var8 = player.getY() + 1.62D;
        double var10 = player.getZ();
        Vec3 var12 = new Vec3(var6, var8, var10);
        float var13 = MathHelper.cos(-var5 * 0.01745329F - (float) Math.PI);
        float var14 = MathHelper.sin(-var5 * 0.01745329F - (float) Math.PI);
        float var15 = -MathHelper.cos(-var4 * 0.01745329F);
        float var16 = MathHelper.sin(-var4 * 0.01745329F);
        float var17 = var14 * var15;
        float var18 = var13 * var15;
        double var19 = 30.0D;
        Vec3 var21 = var12.add(var17 * var19, var16 * var19, var18 * var19);
        BlockHitResult var22 = level.clip(new ClipContext(var12, var21, ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, CollisionContext.empty()));
        if (var22 == null) {
            return InteractionResultHolder.fail(player.getItemInHand(usedHand));
        } else {
            int var23 = var22.getBlockPos().getX();
            int var24 = var22.getBlockPos().getY();
            int var25 = var22.getBlockPos().getZ();
            Direction var26 = var22.getDirection();

            if (var26 == Direction.DOWN) --var24;
            if (var26 == Direction.UP) ++var24;
            if (var26 == Direction.NORTH) --var25;
            if (var26 == Direction.SOUTH) ++var25;
            if (var26 == Direction.WEST) --var23;
            if (var26 == Direction.EAST) ++var23;
            if(!level.isClientSide()) {
                if(player.getData(JDataAttachments.ESSENCE).consumeEssence(player, this.essenceUsage)) {
                    this.teleportTo((ServerPlayer) player, level, var23, var24 + 1, var25);
                    player.getItemInHand(usedHand).hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                }
            }
        }
        return InteractionResultHolder.pass(player.getItemInHand(usedHand));
    }

    protected void teleportTo(ServerPlayer player, Level level, int x, int y, int z) {
        player.teleportTo(x, y, z);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext pContext, List<Component> tooltip, TooltipFlag pTooltipFlag) {
        addItemDesc(JItems.TELEPORTATION_STAFF.asItem(), tooltip, "jitl.tooltip.teleport");
        tooltip.add(Component.translatable("jitl.tooltip.essence_usage", essenceUsage));
    }
}