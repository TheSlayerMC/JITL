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

        this.add(JEntities.FLORO_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(JItems.FLORO_PEDAL.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.FLORO_SEEDS.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.BROWN_HONGO_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(JItems.HONGOSHROOM.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.ILLAGER_MECH_TYPE.get(), empty());

        this.add(JEntities.WITHERSPINE_TYPE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(JItems.WITHIC_DUST.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.SKULL_OF_DECAY.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                .add(LootItem.lootTableItem(JItems.WITHICSPINE.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(JEntities.ESKIMO_TYPE.get(), empty());
        this.add(JEntities.FROZEN_GUARDIAN_TYPE.get(), empty());

        this.add(JEntities.TOWER_GUARDIAN_TYPE.get(), empty());
        this.add(JEntities.ROCKITE_SMASHER_TYPE.get(), empty());

        this.add(JEntities.CRYPIAN_TYPE.get(), empty());
        this.add(JEntities.ROYAL_KING_TYPE.get(), empty());

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
    }

    private LootTable.Builder empty() {
        return LootTable.lootTable();
    }


    @Override
    protected @NotNull Stream<EntityType<?>> getKnownEntityTypes() {
        return JEntities.REGISTRY.getEntries().stream().map(RegistryObject::get);
    }
}
