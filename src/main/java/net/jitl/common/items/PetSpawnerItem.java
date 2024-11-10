package net.jitl.common.items;

import net.jitl.common.entity.overworld.PetRobot;
import net.jitl.common.items.base.JItem;
import net.jitl.core.helper.LangHelper;
import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class PetSpawnerItem extends JItem {

    public PetSpawnerItem(Properties props) {
        super(props.stacksTo(1).rarity(Rarity.RARE));
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        if(player != null) {
            if(!level.isClientSide()) {
                ServerLevel serverLevel = (ServerLevel) level;

                bindSpawner(player, serverLevel, pos, JItems.PET_ROBOT_SPAWNER.get(), new PetRobot(JEntities.PET_ROBOT_TYPE.get(), level, player));
            }
        }
        return InteractionResult.SUCCESS_SERVER;
    }

    public void bindSpawner(Player player, ServerLevel level, BlockPos pos, Item spawner, LivingEntity pet) {
        if(player.getMainHandItem().getItem() == spawner && pet != null) {
            pet.setPos(pos.getX(), pos.getY() + 1, pos.getZ());
            level.addFreshEntity(pet);
            if(!player.isCreative()) player.getMainHandItem().shrink(1);
        }
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable TooltipContext con, @NotNull List<Component> tip, @NotNull TooltipFlag pFlag) {
        if(this == JItems.PET_ROBOT_SPAWNER.get()) tip.add(LangHelper.setPetSpawner("Robot"));
    }
}
