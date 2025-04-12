package net.jitl.common.items.curios.catalyst;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.jitl.common.items.curios.JCurioItem;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JAttributes;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class EssenceRegenCatalystItem extends JCurioItem {
    public float regenSpeed;

    public EssenceRegenCatalystItem(Item.Properties properties) {
        super(properties);
    }


    public EssenceRegenCatalystItem speed(float regenSpeed) {
        this.regenSpeed = regenSpeed;
        return this;
    }

//    @Override todo
//    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation uuid, ItemStack stack) {
//        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = LinkedHashMultimap.create();
//        modifierMultimap.put(JAttributes.ESSENCE_REGEN_SPEED, new AttributeModifier(ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "essence_regen_speed_modifier"), regenSpeed, AttributeModifier.Operation.ADD_VALUE));
//        return modifierMultimap;
//    }
}
