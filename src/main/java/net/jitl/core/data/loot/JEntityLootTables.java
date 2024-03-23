package net.jitl.core.data.loot;

import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JItems;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class JEntityLootTables extends EntityLootSubProvider {

    protected JEntityLootTables() {
        super(FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    public void generate() {

        this.add(JEntities.BOOM_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(Items.GUNPOWDER)).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(Blocks.TNT)).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.MAGE_TYPE.get(), empty());

        this.add(JEntities.WITHERSPINE_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(JItems.WITHIC_DUST.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.SKULL_OF_DECAY.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.WITHICSPINE.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));


        this.add(JEntities.FLORO_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(JItems.FLORO_PEDAL.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.FLORO_SEEDS.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.BROWN_HONGO_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(JItems.HONGOSHROOM.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.ILLAGER_MECH_TYPE.get(), empty());

        this.add(JEntities.ESKIMO_TYPE.get(), empty());
        this.add(JEntities.FROZEN_GUARDIAN_TYPE.get(), empty());

        this.add(JEntities.TOWER_GUARDIAN_TYPE.get(), empty());
        this.add(JEntities.ROCKITE_SMASHER_TYPE.get(), empty());

        this.add(JEntities.CRYPIAN_TYPE.get(), empty());
        this.add(JEntities.ALLOY_MENDER_TYPE.get(), empty());
        this.add(JEntities.ROYAL_KING_TYPE.get(), empty());
        this.add(JEntities.BOSS_CRYSTAL_TYPE.get(), empty());

        this.add(JEntities.HELLBOT_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(JItems.FLAMING_SPROCKET.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(-2.0F, 1.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.FLAMING_SPRING.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.MINI_GHAST_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(JItems.FLAMING_GHAST_TENTACLE.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.BALMY_TEARDROP.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.HELL_TURTLE_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(JItems.BLOOD.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.HELL_TURTLE_SHELL.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.REAPER_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(JItems.WITHIC_DUST.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.LOST_SOUL.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.INFERNO_BLAZE_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(Items.BLAZE_POWDER)).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(Items.BLAZE_ROD)).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.HELL_SERPENT_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(JItems.SNAKE_SKIN.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.BLOOD.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.HELL_COW_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(JItems.BLOOD.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.FLAMING_HIDE.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.SPYCLOPSE_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(JItems.SPYCLOPSE_EYE.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.DARKENER_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(JItems.DARK_CRYSTAL.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.DARK_ORB.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.DARKNESS_CRAWLER_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(JItems.DARK_CRYSTAL.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.DARK_SORCERER_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(JItems.DARK_CRYSTAL.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.DARK_ORB.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.DEPTHS_BEAST_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(JItems.DARK_CRYSTAL.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.BEASTLY_STOMACH.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.STARING_GUARDIAN_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(JItems.DARK_CRYSTAL.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.DARK_ORB.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.SPIKED_BEAST_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(JItems.DARK_CRYSTAL.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.DEPTHS_HUNTER_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(JItems.DARK_CRYSTAL.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.ROC_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(JItems.DARK_CRYSTAL.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(Items.FEATHER)).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.BLIZZARD_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(Items.SNOWBALL)).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.BIG_HONGO_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(JItems.HONGOSHROOM.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.MEDIUM_HONGO_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(JItems.HONGOSHROOM.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.SMALL_HONGO_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(JItems.HONGOSHROOM.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.SAND_CRAWLER_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(Blocks.SAND)).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.JUNGLE_TURTLE_TYPE.get(), empty());
        this.add(JEntities.JUNGLE_GOLEM_TYPE.get(), empty());
        this.add(JEntities.ROCKITE_GOLEM_TYPE.get(), empty());

        this.add(JEntities.CAVELING_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(JItems.STONE_CLUMP.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.CAVE_DUST.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.CAVE_CRYSTAL.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.CAVURN_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(JItems.CAVE_DUST.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.CAVE_CRYSTAL.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.STONEWALKER_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(JItems.CAVE_DUST.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.CAVE_CRYSTAL.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.STONE_CLUMP.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.SHADIUM_INGOT.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.LUNIUM_INGOT.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.SAPPHIRE.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.FLAME_LOTUS_TYPE.get(), empty());

        this.add(JEntities.BURNING_LIGHT_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(JItems.BLAZING_FIREBALL.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(-2.0F, 1.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.BOIL_POWDER.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.HELLWING_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(JItems.BOILING_SKULL.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(Items.IRON_SWORD)).apply(SetItemCountFunction.setCount(UniformGenerator.between(-2.0F, 1.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.FRIGHTENER_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(Blocks.SAND)).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.MAGMA_BLAZE_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(Items.BLAZE_POWDER)).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.BOIL_POWDER.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.OBSERVER_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(JItems.SIZZLING_EYE.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.BOIL_POWDER.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.SCREAMER_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(JItems.SIZZLING_EYE.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.BOIL_POWDER.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.BOIL_TRADER_TYPE.get(), empty());
        this.add(JEntities.ESCAPED_CONVICT_TYPE.get(), empty());

        this.add(JEntities.SHIMMERER_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(JItems.ROYAL_DISK.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.SHIMMERER_DUST.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.EUCA_CHARGER_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(JItems.GATE_KEYS.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.SHIMMERER_DUST.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.GOLDBOT_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(JItems.GATE_KEYS.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.METAL_DISK.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.GOLDER_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(JItems.GOLDER_DUST.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.SHIMMERER_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(JItems.GATE_KEYS.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.SHIMMERER_DUST.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.DYNASTER_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(JItems.ROYAL_DISK.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.SHIMMERER_DUST.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.SHIVERING_SHRIEKER_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(JItems.CRYSTAL_FLAKE.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 3.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.SHIVERING_BUSHWALKER_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(JItems.CRYSTAL_FLAKE.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 3.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.FROZEN_FROSTBITER_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(JItems.CRYSTAL_FLAKE.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.PERMAFRAUST_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(JItems.CRYSTAL_FLAKE.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.SHATTERER_TYPE.get(), empty());
        this.add(JEntities.CRYSTAL_CLUSTER_TYPE.get(), empty());
        this.add(JEntities.ICE_GOLEM_TYPE.get(), empty());

        this.add(JEntities.FROZEN_TROLL_TYPE.get(), empty());
        this.add(JEntities.CAPYBARA_TYPE.get(), empty());

        this.add(JEntities.OKOLOO_TYPE.get(), empty());
        this.add(JEntities.BLAZIER_TYPE.get(), empty());
        this.add(JEntities.CALCIA_TYPE.get(), empty());
        this.add(JEntities.SOUL_WATCHER_TYPE.get(), empty());
        this.add(JEntities.WITHERING_BEAST_TYPE.get(), empty());
        this.add(JEntities.EUDOR_TYPE.get(), empty());
        this.add(JEntities.CORALLATOR_TYPE.get(), empty());
        this.add(JEntities.THUNDER_BIRD_TYPE.get(), empty());
        this.add(JEntities.SCALE_TYPE.get(), empty());
        this.add(JEntities.LOGGER_TYPE.get(), empty());
        this.add(JEntities.TERRANIAN_PROTECTOR_TYPE.get(), empty());
        this.add(JEntities.SENTRY_KING_TYPE.get(), empty());
        this.add(JEntities.SKY_STALKER_TYPE.get(), empty());

        this.add(JEntities.AURON_TYPE.get(), empty());

        this.add(JEntities.CORBANIAN_MOLLUSK_TYPE.get(), empty());
        this.add(JEntities.SMELLY_TYPE.get(), empty());
        this.add(JEntities.STINKY_TYPE.get(), empty());
        this.add(JEntities.GREEN_TORDO_TYPE.get(), empty());
        this.add(JEntities.RED_TORDO_TYPE.get(), empty());
        this.add(JEntities.WOOD_CREATURE_TYPE.get(), empty());
        this.add(JEntities.TREE_GOLEM.get(), empty());
    }

    private LootTable.Builder empty() {
        return LootTable.lootTable();
    }


    @Override
    protected @NotNull Stream<EntityType<?>> getKnownEntityTypes() {
        return JEntities.REGISTRY.getEntries().stream().map(RegistryObject::get);
    }
}
