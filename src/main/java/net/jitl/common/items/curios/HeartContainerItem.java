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

//    @Override
//    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, Identifier uuid, ItemStack stack) {
//        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = LinkedHashMultimap.create();
//        modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(Identifier.fromNamespaceAndPath(JITL.MOD_ID, "health_modifier"), hearts, AttributeModifier.Operation.ADD_VALUE));
//        return modifierMultimap;
//    }todo
}