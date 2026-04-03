package net.jitl.common.items.curios;

import net.jitl.core.init.JITL;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CurioAttributeModifiers;

public class HeartContainerItem extends JCurioItem {
    public int hearts;

    public HeartContainerItem(Properties properties) {
        super(properties);
    }

    public HeartContainerItem health(int hearts) {
        this.hearts = hearts;
        return this;
    }

    @Override
    public CurioAttributeModifiers getDefaultCurioAttributeModifiers(ItemStack stack) {
        return CurioAttributeModifiers.builder().addModifier(Attributes.MAX_HEALTH, new AttributeModifier(Identifier.fromNamespaceAndPath(JITL.MOD_ID, "health_modifier"), hearts, AttributeModifier.Operation.ADD_VALUE)).build();
    }
}