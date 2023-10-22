package net.jitl.common.items.curios;

public class HeartContainerItem extends JCurioItem {
    public int hearts;

    public HeartContainerItem(Properties properties) {
        super(properties);
    }

    public HeartContainerItem health(int hearts) {
        this.hearts = hearts;
        return this;
    }

    /*@Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = LinkedHashMultimap.create();
        modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, JITL.MODID + ":health_modifier", hearts, AttributeModifier.Operation.ADDITION));
        return modifierMultimap;
    }*/
}