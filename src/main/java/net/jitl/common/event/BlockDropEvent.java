package net.jitl.common.event;

//@EventBusSubscriber(modid = JITL.MODID)
public class BlockDropEvent {

//    @SubscribeEvent
//    public static void onBlockHarvested(BlockDropsEvent event) {
//        ItemStack heldItem = event.getTool();
//        List<ItemEntity> itemEntity = event.getDrops();
//        BlockPos pos = Objects.requireNonNull(event.getBlockEntity()).getBlockPos();
//
//        if(event.getBreaker() instanceof Player player) {
//            RecipeHolder<SmeltingRecipe> holder = RecipeManager.createCheck(RecipeType.SMELTING).getRecipeFor(new SimpleContainer(itemEntity.getFirst().getItem()), player.level()).get();
//            ItemStack smelted = holder.value().getResultItem(null);
//
//            if(smelted != null) {
//                ItemEntity entity = new ItemEntity(player.level(), pos.getX(), pos.getY(), pos.getZ(), smelted);
//                if (heldItem.is(JItems.BLOODCRUST_MULTITOOL)) {
//                    itemEntity.removeFirst();
//                    itemEntity.add(entity);
//                }
//            }
//        }
//    }
}