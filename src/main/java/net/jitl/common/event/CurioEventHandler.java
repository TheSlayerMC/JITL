package net.jitl.common.event;

import net.jitl.core.init.JITL;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber(modid = JITL.MOD_ID)
public class CurioEventHandler {

    @SubscribeEvent
    public static void onPlayerAttacked(LivingDamageEvent.Pre event) {
       LivingEntity entity = event.getEntity();
        if(!entity.level().isClientSide()) {
            if(entity instanceof Player player) {
//TODO                Optional<SlotResult> SKULL = getEquippedCurio(player, JItems.SKULL_OF_DECAY.get());
//                Optional<SlotResult> DEATH_CAP = getEquippedCurio(player, JItems.DEATH_CAP.get());
//
//                if(event.getSource().getEntity() instanceof LivingEntity attacker) {
//                    if(SKULL.isPresent()) {
//                        attacker.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 1));
//                        SKULL.get().stack().hurtAndBreak(1, (ServerLevel)player.level(), player, item -> {});
//                    }
//
//                    if(DEATH_CAP.isPresent()) {
//                        attacker.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 1));
//                        DEATH_CAP.get().stack().hurtAndBreak(1, (ServerLevel)player.level(), player, item -> {});
//                    }
//                }
            }
        }
    }

//TODO    @SubscribeEvent
//    public static void onBlockHarvested(BlockDropsEvent event) {
//        ServerLevel world = event.getLevel();
//        RandomSource rand = RandomSource.create();
//        if(!event.getLevel().isClientSide()) {
//            if (event.getBreaker() instanceof Player player) {
//                Optional<SlotResult> LUCKY_CHARM = getEquippedCurio(player, JItems.LUCKY_CHARM.get());
//                if(LUCKY_CHARM.isPresent()) {
//                    LootTable table = Objects.requireNonNull(world.getServer()).reloadableRegistries().getLootTable(JLootTables.LOOT_BASIC);
//                    List<ItemStack> itemList = table.getRandomItems(new LootParams.Builder(world).withParameter(LootContextParams.THIS_ENTITY, player).withParameter(LootContextParams.ORIGIN, player.position()).create(LootContextParamSets.GIFT));
//                    ItemStack spawn = itemList.get(rand.nextInt(itemList.size()));
//                    ItemEntity item = new ItemEntity(world, player.position().x, player.position().y, player.position().z, spawn);
//                    event.getDrops().add(item);
//                };
//            }
//        }
//    }
//
//    private static Optional<SlotResult> getEquippedCurio(LivingEntity entity, @NotNull Item curio) {
//        ICuriosItemHandler curios = CuriosApi.getCuriosInventory(entity).get();
//        return curios.findFirstCurio(curio);
//    }
}
