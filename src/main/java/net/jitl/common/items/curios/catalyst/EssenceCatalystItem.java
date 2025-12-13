package net.jitl.common.items.curios.catalyst;

import net.jitl.common.items.curios.JCurioItem;
import net.minecraft.world.item.Item;

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
//    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, Identifier uuid, ItemStack stack) {
//        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = LinkedHashMultimap.create();
//        modifierMultimap.put(JAttributes.MAX_ESSENCE, new AttributeModifier(Identifier.fromNamespaceAndPath( JITL.MOD_ID, "max_essence_modifier"), maxEssence, AttributeModifier.Operation.ADD_VALUE));
//        return modifierMultimap;
//    }todo
}
