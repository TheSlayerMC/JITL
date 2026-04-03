package net.jitl.common.items.curios.catalyst;

import net.jitl.common.items.curios.JCurioItem;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JAttributes;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CurioAttributeModifiers;

public class EssenceRegenCatalystItem extends JCurioItem {
    public float regenSpeed;

    public EssenceRegenCatalystItem(Item.Properties properties) {
        super(properties);
    }


    public EssenceRegenCatalystItem speed(float regenSpeed) {
        this.regenSpeed = regenSpeed;
        return this;
    }

    @Override
    public CurioAttributeModifiers getDefaultCurioAttributeModifiers(ItemStack stack) {
        return CurioAttributeModifiers.builder().addModifier(JAttributes.ESSENCE_REGEN_SPEED, new AttributeModifier(Identifier.fromNamespaceAndPath(JITL.MOD_ID, "essence_regen_speed_modifier"), regenSpeed, AttributeModifier.Operation.ADD_VALUE)).build();
    }
}
