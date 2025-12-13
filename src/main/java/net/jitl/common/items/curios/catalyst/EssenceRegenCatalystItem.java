package net.jitl.common.items.curios.catalyst;

import net.jitl.common.items.curios.JCurioItem;
import net.minecraft.world.item.Item;

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
//    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, Identifier uuid, ItemStack stack) {
//        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = LinkedHashMultimap.create();
//        modifierMultimap.put(JAttributes.ESSENCE_REGEN_SPEED, new AttributeModifier(Identifier.fromNamespaceAndPath(JITL.MOD_ID, "essence_regen_speed_modifier"), regenSpeed, AttributeModifier.Operation.ADD_VALUE));
//        return modifierMultimap;
//    }todo
}
