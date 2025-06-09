package net.jitl.common.items;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.common.capability.stats.PlayerStats;
import net.jitl.common.items.base.JItem;
import net.jitl.core.helper.IEssenceItem;
import net.jitl.core.init.internal.JDataAttachments;
import net.jitl.core.init.internal.JItems;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.function.Consumer;

public class ObstructorItem extends JItem implements IEssenceItem {

    public final ObstructorItem.Type type;

    public ObstructorItem(Properties pProperties, ObstructorItem.Type type) {
        super(pProperties);
        this.type = type;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if(level instanceof ServerLevel server) {
            if(player.getData(JDataAttachments.ESSENCE).consumeEssence(player, 5)) {
                int playerArea = 10;
                AABB axisalignedbb = AABB.ofSize(player.position(), playerArea, playerArea, playerArea);
                for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, axisalignedbb)) {
                    if(!(entity instanceof Player)) {
                        int duration = 60;
                        if(type == Type.NETHER) {
                            entity.setRemainingFireTicks(duration);
                        }
                        if(type == Type.WITHIC) {
                            entity.addEffect(new MobEffectInstance(MobEffects.WITHER, duration));
                        }
                    }
                }
            }
        }
        return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext pContext, TooltipDisplay display, Consumer<Component> comp, TooltipFlag pTooltipFlag) {

        if(this == JItems.WITHIC_OBSTRUCTOR.asItem())
            comp.accept(Component.translatable("jitl.item.desc.withic_obstructor"));
        if(this == JItems.NETHIC_OBSTRUCTOR.asItem())
            comp.accept(Component.translatable("jitl.item.desc.nethic_obstructor"));

        comp.accept(Component.translatable("jitl.tooltip.essence_usage", 5));
        comp.accept(Component.translatable("jitl.uses_remaining", (stack.getMaxDamage() - stack.getDamageValue())));
    }

    public enum Type {
        WITHIC,
        NETHER
    }
}
