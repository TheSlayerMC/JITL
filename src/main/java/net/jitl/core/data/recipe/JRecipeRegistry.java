package net.jitl.core.data.recipe;

import net.jitl.core.data.JRecipeProvider;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class JRecipeRegistry extends JRecipeProvider {

    public JRecipeRegistry(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    public void buildRecipes(RecipeOutput recipeConsumer) {
        buildBlockRecipes(recipeConsumer);
        buildItemRecipes(recipeConsumer);
        buildWoodTypes(recipeConsumer);
        buildQuartzTypes(recipeConsumer);
        buildSmithingRecipes(recipeConsumer);
    }

    public void buildBlockRecipes(RecipeOutput recipeConsumer) {
        add3x3Recipe(recipeConsumer, JItems.GREEN_GEM.get(), JBlocks.GREEN_GEM_BLOCK.get());
        add3x3Recipe(recipeConsumer, JItems.PURPLE_GEM.get(), JBlocks.PURPLE_GEM_BLOCK.get());
        add3x3Recipe(recipeConsumer, JItems.BLUE_GEM.get(), JBlocks.BLUE_GEM_BLOCK.get());
        add3x3Recipe(recipeConsumer, JItems.YELLOW_GEM.get(), JBlocks.YELLOW_GEM_BLOCK.get());

        addShapelessRecipe(recipeConsumer, RecipeCategory.MISC, JBlocks.GREEN_GEM_BLOCK.get(), JItems.GREEN_GEM.get(), 9);
        addShapelessRecipe(recipeConsumer, RecipeCategory.MISC, JBlocks.PURPLE_GEM_BLOCK.get(), JItems.PURPLE_GEM.get(), 9);
        addShapelessRecipe(recipeConsumer, RecipeCategory.MISC, JBlocks.BLUE_GEM_BLOCK.get(), JItems.BLUE_GEM.get(), 9);
        addShapelessRecipe(recipeConsumer, RecipeCategory.MISC, JBlocks.YELLOW_GEM_BLOCK.get(), JItems.YELLOW_GEM.get(), 9);

        addOreBlockRecipe(recipeConsumer, JItems.IRIDIUM_NUGGET.get(), JBlocks.IRIDIUM_BLOCK.get());
        addSmeltingAndBlastingRecipe(recipeConsumer, JBlocks.IRIDIUM_ORE.get(), JItems.IRIDIUM_NUGGET.get());

        addOreBlockRecipe(recipeConsumer, JItems.ENDERILLIUM_SHARD.get(), JBlocks.ENDERILLIUM_BLOCK.get());
        addSmeltingAndBlastingRecipe(recipeConsumer, JBlocks.ENDERILLIUM_ORE.get(), JItems.ENDERILLIUM_SHARD.get());

        addOreBlockRecipe(recipeConsumer, JItems.ASH.get(), JBlocks.ASHUAL_BLOCK.get());
        addSmeltingAndBlastingRecipe(recipeConsumer, JBlocks.ASHUAL_ORE.get(), JItems.ASH.get());

        addOreBlockRecipe(recipeConsumer, JItems.PERIDOT_GEMSTONE.get(), JBlocks.PERIDOT_BLOCK.get());
        addSmeltingAndBlastingRecipe(recipeConsumer, JBlocks.PERIDOT_ORE.get(), JItems.PERIDOT_GEMSTONE.get());

        addOreBlockRecipe(recipeConsumer, JItems.RIMESTONE.get(), JBlocks.RIMESTONE_BLOCK.get());
        addSmeltingAndBlastingRecipe(recipeConsumer, JBlocks.RIMESTONE_ORE.get(), JItems.RIMESTONE.get());

        addOreBlockRecipe(recipeConsumer, JItems.VERDITE_INGOT.get(), JBlocks.VERDITE_BLOCK.get());
        addSmeltingAndBlastingRecipe(recipeConsumer, JBlocks.VERDITE_ORE.get(), JItems.VERDITE_INGOT.get());

        addOreBlockRecipe(recipeConsumer, JItems.BLAZIUM_INGOT.get(), JBlocks.BLAZIUM_BLOCK.get());
        addSmeltingAndBlastingRecipe(recipeConsumer, JItems.RAW_BLAZIUM.get(), JItems.BLAZIUM_INGOT.get());

        addSmeltingAndBlastingRecipe(recipeConsumer, JBlocks.FIRESTONE_ORE.get(), JItems.FIRESTONE_CLUMP.get());
        addOreBlockRecipe(recipeConsumer, JItems.FIRESTONE_CLUMP.get(), JBlocks.FIRESTONE_BLOCK.get());

        addOreDefaultItems(recipeConsumer, RecipePrefix.SHADIUM, JBlocks.SHADIUM_BLOCK.get(), JBlocks.SHADIUM_ORE.get(), JBlocks.DEEPSLATE_SHADIUM_ORE.get(), JItems.RAW_SHADIUM.get(), JItems.SHADIUM_INGOT.get(), Items.STICK);
        addOreDefaultItems(recipeConsumer, RecipePrefix.SAPPHIRE, JBlocks.SAPPHIRE_BLOCK.get(), JBlocks.SAPPHIRE_ORE.get(), JBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), null, JItems.SAPPHIRE.get(), Items.STICK);
        addOreDefaultItems(recipeConsumer, RecipePrefix.LUNIUM, JBlocks.LUNIUM_BLOCK.get(), JBlocks.LUNIUM_ORE.get(), JBlocks.DEEPSLATE_LUNIUM_ORE.get(), JItems.LUNIUM_POWDER.get(), JItems.LUNIUM_INGOT.get(), Items.STICK);
        addOreDefaultItems(recipeConsumer, RecipePrefix.BLOODCRUST, JBlocks.BLOODCRUST_BLOCK.get(), JBlocks.BLOODCRUST_ORE.get(), null, JItems.RAW_BLOODCRUST.get(), JItems.BLOODCRUST_INGOT.get(), Items.STICK);
        addOreNoArmorItems(recipeConsumer, RecipePrefix.CELESTIUM, JBlocks.CELESTIUM_BLOCK.get(), JBlocks.CELESTIUM_ORE.get(), null, JItems.CELESTIUM_GEMSTONE.get(), JItems.CELESTIUM_INGOT.get(), Items.STICK);
        addOreNoArmorItems(recipeConsumer, RecipePrefix.KORITE, JBlocks.KORITE_BLOCK.get(), JBlocks.KORITE_ORE.get(), null, JItems.KORITE_GEMSTONE.get(), JItems.KORITE_INGOT.get(), Items.STICK);
        addOreNoArmorItems(recipeConsumer, RecipePrefix.STORON, JBlocks.STORON_BLOCK.get(), JBlocks.STORON_ORE.get(), null, JItems.STORON_GEMSTONE.get(), JItems.STORON_INGOT.get(), Items.STICK);
        addOreNoArmorItems(recipeConsumer, RecipePrefix.MEKYUM, JBlocks.MEKYUM_BLOCK.get(), JBlocks.MEKYUM_ORE.get(), null, JItems.MEKYUM_GEMSTONE.get(), JItems.MEKYUM_INGOT.get(), Items.STICK);
        addOreDefaultItems(recipeConsumer, RecipePrefix.FLAIRIUM, JBlocks.FLAIRIUM_BLOCK.get(), JBlocks.FLAIRIUM_ORE.get(), null, null, JItems.FLAIRIUM_INGOT.get(), Items.STICK);
        addOreNoArmorItems(recipeConsumer, RecipePrefix.DES, JBlocks.DES_BLOCK.get(), JBlocks.DES_ORE.get(), null, null, JItems.DES_INGOT.get(), Items.STICK);
        addOreNoArmorItems(recipeConsumer, RecipePrefix.GORBITE, JBlocks.GORBITE_BLOCK.get(), JBlocks.GORBITE_ORE.get(), null, null, JItems.GORBITE_GEM.get(), Items.STICK);
        addOreDefaultItems(recipeConsumer, RecipePrefix.ORBADITE, JBlocks.ORBADITE_BLOCK.get(), JBlocks.ORBADITE_ORE.get(), null, JItems.RAW_ORBADITE.get(), JItems.ORBADITE_INGOT.get(), Items.STICK);
        addOreNoArmorItems(recipeConsumer, RecipePrefix.SOULSTONE, JBlocks.SOULSTONE_BLOCK.get(), null, null, null, JItems.SOULSTONE.get(), JItems.OBSIDIAN_STICK.get());

        addShapedRecipe(recipeConsumer, RecipeCategory.BUILDING_BLOCKS, "dsd", "iii", 'i', JBlocks.BLOOD_ROCK.get(), 'd', JItems.BLOODCRUST_INGOT.get(), 's', JItems.BLEEDSTONE.get(), JBlocks.SUMMONING_TABLE.get(), 1);
        addShapedRecipe(recipeConsumer, RecipeCategory.BUILDING_BLOCKS, "dd", "dd", 'd', JBlocks.BLOOD_ROCK.get(), JBlocks.BLOOD_BRICKS.get(), 4);
        addShapedRecipe(recipeConsumer, RecipeCategory.BUILDING_BLOCKS, "dd", "dd", 'd', JBlocks.BLOOD_BRICKS.get(), JBlocks.CARVED_BLOOD_ROCK.get(), 4);
        addShapedRecipe(recipeConsumer, RecipeCategory.BUILDING_BLOCKS, "ddd", "did", "ddd", 'i', Blocks.GLOWSTONE, 'd', JBlocks.BLOOD_ROCK.get(), JBlocks.BLOOD_LAMP.get(), 4);
        addShapedRecipe(recipeConsumer, RecipeCategory.BUILDING_BLOCKS, "ddd", "did", "ddd", 'i', JBlocks.BLOOD_ROCK.get(), 'd', JItems.BOIL_POWDER.get(), JBlocks.OBELISK.get(), 1);
        addShapedRecipe(recipeConsumer, RecipeCategory.BUILDING_BLOCKS, "ddd", "did", "ddd", 'd', JBlocks.BLOOD_ROCK.get(), 'i', JItems.BALMY_TEARDROP.get(), JBlocks.BLOOD_RUNE.get(), 4);
        addShapedRecipe(recipeConsumer, RecipeCategory.BUILDING_BLOCKS, "dd", "dd", 'd', JBlocks.CARVED_BLOOD_ROCK.get(), JBlocks.BLOOD_PILLAR.get(), 4);

        add3x3Recipe(recipeConsumer, JItems.EUCA_PORTAL_GEM.get(), JBlocks.EUCA_PORTAL_FRAME.get(), 10);
        add3x3Recipe(recipeConsumer, JItems.DEPTHS_PORTAL_GEM.get(), JBlocks.DEPTHS_PORTAL_FRAME.get(), 12);
        add3x3Recipe(recipeConsumer, JItems.CORBA_PORTAL_GEM.get(), JBlocks.CORBA_PORTAL_FRAME.get(), 12);
        add3x3Recipe(recipeConsumer, JItems.TERRANIA_PORTAL_GEM.get(), JBlocks.TERRANIAN_PORTAL_FRAME.get(), 10);
        add3x3Recipe(recipeConsumer, JItems.CLOUDIA_PORTAL_GEM.get(), JBlocks.CLOUDIA_PORTAL_FRAME.get(), 10);
        addShapedRecipe(recipeConsumer, RecipeCategory.BUILDING_BLOCKS, "iii", "idi", "iii", 'i', Items.SNOWBALL, 'd', Items.DIAMOND, JBlocks.FROZEN_PORTAL_FRAME.get(), 10);

        add2x2Recipe(recipeConsumer, JItems.SMITHSTONE.get(), JBlocks.SMITHSTONE_BLOCK.get(), true);
        add2x2Recipe(recipeConsumer, JItems.BLEEDSTONE.get(), JBlocks.BLEEDSTONE_BLOCK.get(), true);

        addShapedRecipe(recipeConsumer, "s s", " s ", "s s", 's', Items.STRING, Blocks.COBWEB, 1);
    }

    public void buildItemRecipes(RecipeOutput recipeConsumer) {
        addShapedRecipe(recipeConsumer, RecipeCategory.MISC, "iii", "idi", "iii", 'i', Items.GOLD_INGOT, 'd', Items.DIAMOND, JItems.FLAME_COIN.get(), 1);
        addShapedRecipe(recipeConsumer, RecipeCategory.MISC, "ddd", "did", "ddd", 'd', JItems.STONE_CLUMP.get(), 'i', Blocks.STONE, JItems.REINFORCED_STONE_INGOT.get(), 1);
        addShapedRecipe(recipeConsumer, RecipeCategory.MISC, "ddd", "did", "ddd", 'd', JItems.STONE_CLUMP.get(), 'i', JItems.CAVE_CRYSTAL.get(), JItems.REINFORCED_CRYSTAL_INGOT.get(), 1);
        addShapedRecipe(recipeConsumer, RecipeCategory.MISC, "iei", "iii",  'i', JItems.MAGIC_DUST.get(), 'e', Items.ENDER_PEARL, JItems.CRYSTAL_BALL.get(), 1);
        addShapedRecipe(recipeConsumer, RecipeCategory.COMBAT, "ddd", "did", "ddd", 'd', JItems.DEMONIC_DUST.get(), 'i', JItems.CRYSTAL_BALL.get(), JItems.DEMONIC_BOMB.get(), 16);
        addShapedRecipe(recipeConsumer, RecipeCategory.COMBAT, "ddd", "did", "ddd",  'd', JItems.MAGIC_DUST.get(), 'i', JItems.CRYSTAL_BALL.get(), JItems.MAGIC_BOMB.get(), 1);
        addShapedRecipe(recipeConsumer, RecipeCategory.MISC,"ddd", "ddd", "ddd", 'd', JItems.CAVE_DUST.get(), JItems.STONE_CLUMP.get(), 4, "_2");
        addShapedRecipe(recipeConsumer, RecipeCategory.MISC, "ddd", "ddd", "ddd", 'd', Blocks.STONE, JItems.STONE_CLUMP.get(), 16);
        addShapedRecipe(recipeConsumer, RecipeCategory.MISC, "ddd", "did", "ddd", 'd', JItems.WITHIC_DUST.get(), 'i', JItems.LOST_SOUL.get(), JItems.WITHIC_SOUL.get(), 1);
        addShapedRecipe(recipeConsumer, RecipeCategory.MISC, "ddd", "did", "ddd", 'd', JItems.BLOOD.get(), 'i', JItems.LOST_SOUL.get(), JItems.CONCENTRATED_BLOOD.get(), 1);
        addShapedRecipe(recipeConsumer, RecipeCategory.MISC, "dgd", "did", "dgd", 'd', JItems.FLAMING_SPRING.get(), 'i', JItems.FLAMING_SPROCKET.get(), 'g', Items.GOLD_INGOT, JItems.NETHER_KEY.get(), 1);
        addShapelessRecipe(recipeConsumer, RecipeCategory.MISC, JItems.DEMONIC_BONE.get(), JItems.DEMONIC_DUST.get(), 5);
        addShapelessRecipe(recipeConsumer, RecipeCategory.MISC, JItems.SMITHSTONE.get(), JItems.SMITHSTONE_DUST.get(), 4);
        addShapelessRecipe(recipeConsumer, RecipeCategory.MISC, JItems.BLEEDSTONE.get(), JItems.BLEEDSTONE_DUST.get(), 4);
        addShapelessRecipe(recipeConsumer, RecipeCategory.MISC, JItems.BLEEDSTONE_DUST.get(), JItems.SMITHSTONE_DUST.get(), JItems.SOULSTONE.get(), 1);
        addShapelessRecipe(recipeConsumer, RecipeCategory.MISC, JItems.SHADIUM_INGOT.get(), Items.DIAMOND, JItems.DARK_GEM.get(), 1);
        addShapedRecipe(recipeConsumer, RecipeCategory.MISC, "ddd", 'd', JItems.BLOODCRUST_INGOT.get(), JItems.BLOODCRUST_CLUMP.get(), 1);
        addShapedRecipe(recipeConsumer, RecipeCategory.MISC, "ddd", 'd', JItems.SHADIUM_INGOT.get(), JItems.SHADIUM_CLUMP.get(), 1);
        addShapedRecipe(recipeConsumer, RecipeCategory.MISC, "ddd", 'd', JItems.LUNIUM_INGOT.get(), JItems.LUNIUM_CLUMP.get(), 1);
        addShapelessRecipe(recipeConsumer, RecipeCategory.MISC, JItems.BLOODCRUST_CLUMP.get(), JItems.SHADIUM_CLUMP.get(), JItems.LUNIUM_CLUMP.get(), JItems.SPAWNER_CLUMP.get(), 1);
        addShapelessRecipe(recipeConsumer, RecipeCategory.MISC, JItems.EUCA_PORTAL_PIECE_1.get(), JItems.EUCA_PORTAL_PIECE_2.get(), JItems.EUCA_PORTAL_PIECE_3.get(), JItems.EUCA_PORTAL_GEM.get(), 9);

        addShapelessRecipe(recipeConsumer, RecipeCategory.MISC, Items.ENDER_EYE, JItems.DEMONIC_DUST.get(), JItems.DEMONIC_EYE.get(), 1);
        addShapelessRecipe(recipeConsumer, RecipeCategory.MISC, Items.ENDER_EYE, JItems.SENTRY_STONE.get(), JItems.SENTRY_EYE.get(), 1);
        addShapelessRecipe(recipeConsumer, RecipeCategory.MISC, JItems.FLAME_COIN.get(), Items.BOOK, JItems.RECIPE_BOOK.get(), 1);

        addSwordRecipe(recipeConsumer, JItems.BLOODCRUST_INGOT.get(), JItems.WITHIC_DUST.get(), JItems.WITHIC_BLADE.get());
        addSwordRecipe(recipeConsumer, JItems.STONE_STICK.get(), JItems.REINFORCED_STONE_INGOT.get(), JItems.REINFORCED_STONE_SWORD.get());
        addSwordRecipe(recipeConsumer, JItems.STONE_STICK.get(), JItems.REINFORCED_CRYSTAL_INGOT.get(), JItems.REINFORCED_CRYSTAL_SWORD.get());
        addSwordRecipe(recipeConsumer, JItems.STONE_CLUMP.get(), JItems.FLORO_PEDAL.get(), JItems.PEDAL_SWORD.get());
        addSwordRecipe(recipeConsumer, Blocks.STONE, JItems.CAVE_CRYSTAL.get(), JItems.CRYSTAL_BLADE.get());
        addSwordRecipe(recipeConsumer, JItems.DEMONIC_BONE.get(), JItems.DEMONIC_DUST.get(), JItems.DEMONIC_SWORD.get());

        addSmeltingAndBlastingRecipe(recipeConsumer, JItems.SPAWNER_CLUMP.get(), JItems.SPAWNER_BAR.get());

        threeByThreePacker(recipeConsumer, RecipeCategory.BUILDING_BLOCKS, JBlocks.AIRROOT_MELON.get(), JItems.AIRMELON.get());

        addSmeltingAndBlastingRecipe(recipeConsumer, Items.ROTTEN_FLESH, Items.LEATHER);
    }

    public void buildWoodTypes(RecipeOutput consumer) {
        addWoodType(consumer, JBlocks.EUCA_GOLD_LOG, JBlocks.EUCA_GOLD_PLANKS, JBlocks.EUCA_GOLD_STAIRS, JBlocks.EUCA_GOLD_SLAB, JBlocks.EUCA_GOLD_FENCE, JBlocks.EUCA_GOLD_FENCE_GATE, JBlocks.EUCA_GOLD_TRAP_DOOR, JBlocks.EUCA_GOLD_PRESSURE_PLATE, JBlocks.EUCA_GOLD_DOOR, JBlocks.EUCA_GOLD_BUTTON, JItems.GOLDEN_EUCA_BOAT);
        addWoodType(consumer, JBlocks.EUCA_BROWN_LOG, JBlocks.EUCA_BROWN_PLANKS, JBlocks.EUCA_BROWN_STAIRS, JBlocks.EUCA_BROWN_SLAB, JBlocks.EUCA_BROWN_FENCE, JBlocks.EUCA_BROWN_FENCE_GATE, JBlocks.EUCA_BROWN_TRAP_DOOR, JBlocks.EUCA_BROWN_PRESSURE_PLATE, JBlocks.EUCA_BROWN_DOOR, JBlocks.EUCA_BROWN_BUTTON, JItems.BROWN_EUCA_BOAT);
        addWoodType(consumer, JBlocks.FROZEN_LOG, JBlocks.FROZEN_PLANKS, JBlocks.FROZEN_STAIRS, JBlocks.FROZEN_SLAB, JBlocks.FROZEN_FENCE, JBlocks.FROZEN_FENCE_GATE, JBlocks.FROZEN_TRAP_DOOR, JBlocks.FROZEN_PRESSURE_PLATE, JBlocks.FROZEN_DOOR, JBlocks.FROZEN_BUTTON, JItems.FROZEN_BOAT);
        addWoodType(consumer, JBlocks.BURNED_BARK, JBlocks.BURNED_PLANKS, JBlocks.BURNED_STAIRS, JBlocks.BURNED_SLAB, JBlocks.BURNED_FENCE, JBlocks.BURNED_FENCE_GATE, JBlocks.BURNED_TRAP_DOOR, JBlocks.BURNED_PRESSURE_PLATE, JBlocks.BURNED_DOOR, JBlocks.BURNED_BUTTON, JItems.BURNED_BOAT);
        addWoodType(consumer, JBlocks.DEPTHS_LOG, JBlocks.DEPTHS_PLANKS, JBlocks.DEPTHS_STAIRS, JBlocks.DEPTHS_SLAB, JBlocks.DEPTHS_FENCE, JBlocks.DEPTHS_FENCE_GATE, JBlocks.DEPTHS_TRAP_DOOR, JBlocks.DEPTHS_PRESSURE_PLATE, JBlocks.DEPTHS_DOOR, JBlocks.DEPTHS_BUTTON, JItems.DEPTHS_BOAT);
        addWoodType(consumer, JBlocks.CORBA_LOG, JBlocks.CORBA_PLANKS, JBlocks.CORBA_STAIRS, JBlocks.CORBA_SLAB, JBlocks.CORBA_FENCE, JBlocks.CORBA_FENCE_GATE, JBlocks.CORBA_TRAP_DOOR, JBlocks.CORBA_PRESSURE_PLATE, JBlocks.CORBA_DOOR, JBlocks.CORBA_BUTTON, JItems.CORBA_BOAT);
        addWoodType(consumer, JBlocks.TERRANIAN_LOG, JBlocks.TERRANIAN_PLANKS, JBlocks.TERRANIAN_STAIRS, JBlocks.TERRANIAN_SLAB, JBlocks.TERRANIAN_POST, JBlocks.TERRANIAN_FENCE_GATE, JBlocks.TERRANIAN_TRAP_DOOR, JBlocks.TERRANIAN_PRESSURE_PLATE, JBlocks.TERRANIAN_DOOR, JBlocks.TERRANIAN_BUTTON, JItems.TERRANIAN_BOAT);
        addWoodType(consumer, JBlocks.CLOUDIA_LOG, JBlocks.CLOUDIA_PLANKS, JBlocks.CLOUDIA_STAIRS, JBlocks.CLOUDIA_SLAB, JBlocks.CLOUDIA_FENCE, JBlocks.CLOUDIA_FENCE_GATE, JBlocks.CLOUDIA_TRAP_DOOR, JBlocks.CLOUDIA_PRESSURE_PLATE, JBlocks.CLOUDIA_DOOR, JBlocks.CLOUDIA_BUTTON, JItems.CLOUDIA_BOAT);

    }

    public void buildQuartzTypes(RecipeOutput consumer) {
        addQuartzType(consumer, JItems.CRIMSON_QUARTZ, JBlocks.CRIMSON_QUARTZ_ORE, JBlocks.CRIMSON_QUARTZ_BLOCK, JBlocks.SMOOTH_CRIMSON_QUARTZ, JBlocks.CRIMSON_QUARTZ_STAIRS, JBlocks.SMOOTH_CRIMSON_QUARTZ_STAIRS, JBlocks.CRIMSON_QUARTZ_SLAB, JBlocks.SMOOTH_CRIMSON_QUARTZ_SLAB, JBlocks.CRIMSON_QUARTZ_BRICKS, JBlocks.CHISELED_CRIMSON_QUARTZ_BLOCK, JBlocks.CRIMSON_QUARTZ_PILLAR);
        addQuartzType(consumer, JItems.WARPED_QUARTZ, JBlocks.WARPED_QUARTZ_ORE, JBlocks.WARPED_QUARTZ_BLOCK, JBlocks.SMOOTH_WARPED_QUARTZ, JBlocks.WARPED_QUARTZ_STAIRS, JBlocks.SMOOTH_WARPED_QUARTZ_STAIRS, JBlocks.WARPED_QUARTZ_SLAB, JBlocks.SMOOTH_WARPED_QUARTZ_SLAB, JBlocks.WARPED_QUARTZ_BRICKS, JBlocks.CHISELED_WARPED_QUARTZ_BLOCK, JBlocks.WARPED_QUARTZ_PILLAR);
    }

    public void buildSmithingRecipes(RecipeOutput recipeConsumer) {
        addSmithingRecipe(recipeConsumer, Items.DIAMOND_HELMET, JItems.LUNIUM_INGOT.get(), JItems.LUNIUM_HELMET.get());
        addSmithingRecipe(recipeConsumer, Items.DIAMOND_CHESTPLATE, JItems.LUNIUM_INGOT.get(), JItems.LUNIUM_CHEST.get());
        addSmithingRecipe(recipeConsumer, Items.DIAMOND_LEGGINGS, JItems.LUNIUM_INGOT.get(), JItems.LUNIUM_LEGS.get());
        addSmithingRecipe(recipeConsumer, Items.DIAMOND_BOOTS, JItems.LUNIUM_INGOT.get(), JItems.LUNIUM_BOOTS.get());

        addSmithingRecipe(recipeConsumer, Items.DIAMOND_SWORD, JItems.LUNIUM_INGOT.get(), JItems.LUNIUM_SWORD.get());

        addSmithingRecipe(recipeConsumer, Items.DIAMOND_AXE, JItems.LUNIUM_INGOT.get(), JItems.LUNIUM_AXE.get());
        addSmithingRecipe(recipeConsumer, Items.DIAMOND_PICKAXE, JItems.LUNIUM_INGOT.get(), JItems.LUNIUM_PICKAXE.get());
        addSmithingRecipe(recipeConsumer, Items.DIAMOND_SHOVEL, JItems.LUNIUM_INGOT.get(), JItems.LUNIUM_SHOVEL.get());
        addSmithingRecipe(recipeConsumer, Items.DIAMOND_HOE, JItems.LUNIUM_INGOT.get(), JItems.LUNIUM_HOE.get());
    }
}