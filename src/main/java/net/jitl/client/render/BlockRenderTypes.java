package net.jitl.client.render;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockRenderTypes {

    @SubscribeEvent
    public static void registerRenderTypes(FMLClientSetupEvent event) {
        ArrayList<Block> cutout = new ArrayList<>();
        ArrayList<Block> translucent = new ArrayList<>();

        cutout.add(JBlocks.EUCA_BROWN_DOOR.get());
        cutout.add(JBlocks.EUCA_GOLD_DOOR.get());
        cutout.add(JBlocks.EUCA_BROWN_TRAP_DOOR.get());
        cutout.add(JBlocks.EUCA_GOLD_TRAP_DOOR.get());
        cutout.add(JBlocks.EUCA_GOLD_LEAVES.get());
        cutout.add(JBlocks.EUCA_GREEN_LEAVES.get());
        cutout.add(JBlocks.EUCA_GOLD_SAPLING.get());
        cutout.add(JBlocks.EUCA_GREEN_SAPLING.get());
        cutout.add(JBlocks.EUCA_TALL_GRASS.get());
        cutout.add(JBlocks.EUCA_TALL_FLOWERS.get());
        cutout.add(JBlocks.EUCA_SILVER_FLOWER.get());
        cutout.add(JBlocks.EUCA_BLUE_FLOWER.get());
        cutout.add(JBlocks.FROZEN_LEAVES.get());
        cutout.add(JBlocks.FROSTWOOD_SAPLING.get());
        cutout.add(JBlocks.FROZEN_DOOR.get());
        cutout.add(JBlocks.FROZEN_TRAP_DOOR.get());
        cutout.add(JBlocks.FROST_CRYSTAL_LARGE.get());
        cutout.add(JBlocks.FROST_CRYSTAL_MEDIUM.get());
        cutout.add(JBlocks.FROST_CRYSTAL_SMALL.get());
        cutout.add(JBlocks.FROST_CRYSTAL_TINY.get());
        cutout.add(JBlocks.FROSTBERRY_THORN.get());
        cutout.add(JBlocks.FROZEN_BLOOM.get());
        cutout.add(JBlocks.FROZEN_FLOWER.get());
        cutout.add(JBlocks.SULPHUR_CRYSTAL.get());
        cutout.add(JBlocks.SCORCHED_CACTUS.get());
        cutout.add(JBlocks.CHARRED_LEAVES.get());
        cutout.add(JBlocks.BURNED_SAPLING.get());
        cutout.add(JBlocks.CHARRED_SAPLING.get());
        cutout.add(JBlocks.CHARRED_BRUSH.get());
        cutout.add(JBlocks.CHARRED_SHORT_GRASS.get());
        cutout.add(JBlocks.CHARRED_WEEDS.get());
        cutout.add(JBlocks.CRUMBLING_PINE.get());
        cutout.add(JBlocks.CRISP_GRASS.get());
        cutout.add(JBlocks.FLAME_POD.get());
        cutout.add(JBlocks.LAVA_BLOOM.get());
        cutout.add(JBlocks.INFERNO_BUSH.get());
        cutout.add(JBlocks.GOLDITE_FLOWER.get());
        cutout.add(JBlocks.GOLDITE_STALKS.get());
        cutout.add(JBlocks.GOLDITE_BULB.get());
        cutout.add(JBlocks.GOLDITE_TALL_GRASS.get());
        cutout.add(JBlocks.BITTERWOOD_SAPLING.get());
        cutout.add(JBlocks.ICE_BUSH.get());
        cutout.add(JBlocks.ICE_BUD.get());
        cutout.add(JBlocks.GOLD_BOT_SPAWNER.get());
        cutout.add(JBlocks.MINI_GHAST_SPAWNER.get());
        cutout.add(JBlocks.BITTERWOOD_CAMPFIRE.get());
        cutout.add(JBlocks.DEPTHS_LEAVES.get());
        cutout.add(JBlocks.DEPTHS_CRYSTAL.get());
        cutout.add(JBlocks.DEPTHS_SAPLING.get());
        cutout.add(JBlocks.DEPTHS_DOOR.get());
        cutout.add(JBlocks.DEPTHS_TRAP_DOOR.get());
        cutout.add(JBlocks.DEPTHS_GLASS.get());
        cutout.add(JBlocks.DEPTHS_BLUE_FLOWER.get());
        cutout.add(JBlocks.DEPTHS_FLOWER.get());
        cutout.add(JBlocks.BURNED_DOOR.get());
        cutout.add(JBlocks.BURNED_TRAP_DOOR.get());
        cutout.add(JBlocks.BOILING_BARS.get());
        cutout.add(JBlocks.GLIMMER_ROOT.get());
        cutout.add(JBlocks.TALL_GREEN_GLOWSHROOM.get());
        cutout.add(JBlocks.TALL_BLUE_GLOWSHROOM.get());
        cutout.add(JBlocks.TALL_RED_GLOWSHROOM.get());
        cutout.add(JBlocks.GREEN_GLOWSHROOM.get());
        cutout.add(JBlocks.BLUE_GLOWSHROOM.get());
        cutout.add(JBlocks.RED_GLOWSHROOM.get());
        cutout.add(JBlocks.TALL_CHARRED_GRASS.get());
        cutout.add(JBlocks.SIZZLESHROOM.get());
        cutout.add(JBlocks.TALL_SIZZLESHROOM.get());
        cutout.add(JBlocks.TALL_MOLTEN_PLANT.get());
        cutout.add(JBlocks.TALL_CRUMBLING_PINE.get());

        cutout.add(JBlocks.CORBA_LEAVES.get());
        cutout.add(JBlocks.CORBA_SAPLING.get());
        cutout.add(JBlocks.CORBA_DOOR.get());
        cutout.add(JBlocks.CORBA_TRAP_DOOR.get());
        cutout.add(JBlocks.BOGWOOD_LEAVES.get());
        cutout.add(JBlocks.CORBA_BLUE_FLOWER.get());
        cutout.add(JBlocks.CORBA_RED_FLOWER.get());
        cutout.add(JBlocks.CORBA_SPECKLED_FLOWER.get());
        cutout.add(JBlocks.CORBA_PURPLE_FLOWER.get());
        cutout.add(JBlocks.CORBA_LIGHT_PURPLE_FLOWER.get());
        cutout.add(JBlocks.CORBA_DARK_PURPLE_FLOWER.get());
        cutout.add(JBlocks.CORBA_FLOWER.get());
        cutout.add(JBlocks.CORBA_TALL_GRASS.get());
        cutout.add(JBlocks.CORBA_LADDER.get());
        cutout.add(JBlocks.FLORO_PEDAL_CROP.get());
        cutout.add(JBlocks.CORVEGGIES_CROP.get());
        cutout.add(JBlocks.CRACKENCANE_CROP.get());
        cutout.add(JBlocks.CRAKEBULB_CROP.get());
        cutout.add(JBlocks.GLOWA_CROP.get());
        cutout.add(JBlocks.SPINEBERRY_CROP.get());
        cutout.add(JBlocks.TOMATO_CROP.get());
        cutout.add(JBlocks.ZATPEDAL_CROP.get());
        cutout.add(JBlocks.BRADBERRY_BUSH.get());
        cutout.add(JBlocks.REDCURRANT_BUSH.get());
        cutout.add(JBlocks.BOGWEED.get());

        translucent.add(JBlocks.EUCA_PORTAL.get());
        translucent.add(JBlocks.FROZEN_PORTAL.get());
        translucent.add(JBlocks.BOIL_PORTAL.get());
        translucent.add(JBlocks.DEPTHS_PORTAL.get());

        for(Block b : cutout) {
            ItemBlockRenderTypes.setRenderLayer(b, RenderType.cutout());
        }

        for(Block b : translucent) {
            ItemBlockRenderTypes.setRenderLayer(b, RenderType.translucent());
        }
    }
}