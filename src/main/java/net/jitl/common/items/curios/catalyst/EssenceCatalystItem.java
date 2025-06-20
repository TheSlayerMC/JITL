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

public class EssenceCatalystItem extends JCurioItem {
    public float maxEssence;

    public EssenceCatalystItem(Item.Properties properties) {
        super(properties);
    }

    public EssenceCatalystItem essence(float maxEssence) {
        this.maxEssence = maxEssence;
        return this;
    }

//    @Override
//    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation uuid, ItemStack stack) {
//        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = LinkedHashMultimap.create();
//        modifierMultimap.put(JAttributes.MAX_ESSENCE, new AttributeModifier(ResourceLocation.fromNamespaceAndPath( JITL.MOD_ID, "max_essence_modifier"), maxEssence, AttributeModifier.Operation.ADD_VALUE));
//        return modifierMultimap;
//    }todo
}
