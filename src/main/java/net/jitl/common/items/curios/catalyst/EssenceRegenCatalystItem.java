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
//TODO
//    @Override
//    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
//        Multimap<Attribute, AttributeModifier> modifierMultimap = LinkedHashMultimap.create();
//        modifierMultimap.put(JAttributes.ESSENCE_REGEN_SPEED.get(), new AttributeModifier(uuid, JITL.MODID + ":essence_regen_speed_modifier", regenSpeed, AttributeModifier.Operation.ADDITION));
//        return modifierMultimap;
//    }

}
