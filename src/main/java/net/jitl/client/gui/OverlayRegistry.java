package net.jitl.client.gui;

import net.jitl.client.essence.PlayerEssence;
import net.jitl.client.essence.PlayerEssenceProvider;
import net.jitl.client.gui.overlay.EssenceBar;
import net.jitl.client.knowledge.PlayerKnowledge;
import net.jitl.client.knowledge.PlayerKnowledgeProvider;
import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OverlayRegistry {

    @SubscribeEvent
    public static void registerOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("essence_bar", EssenceBar::render);
    }
}
