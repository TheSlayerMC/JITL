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

    //TODO
//    @Override
//    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
//        Multimap<Attribute, AttributeModifier> modifierMultimap = LinkedHashMultimap.create();
//        modifierMultimap.put(JAttributes.MAX_ESSENCE.get(), new AttributeModifier(uuid, JITL.MODID + ":max_essence_modifier", maxEssence, AttributeModifier.Operation.ADDITION));
//        return modifierMultimap;
//    }
}
