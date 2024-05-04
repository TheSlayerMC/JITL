package net.jitl.core.init.internal;

import net.jitl.common.capability.essence.PlayerEssence;
import net.jitl.common.capability.gear.PlayerArmor;
import net.jitl.common.capability.keypressed.PressedKeyCap;
import net.jitl.common.capability.stats.PlayerStats;
import net.jitl.core.init.JITL;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class JDataAttachments {

    public static final DeferredRegister<AttachmentType<?>> REGISTRY = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, JITL.MODID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<PlayerStats>> PLAYER_STATS = REGISTRY.register(
            "player_stats", () -> AttachmentType.serializable(PlayerStats::new).build());

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<PlayerEssence>> ESSENCE = REGISTRY.register(
            "player_essence", () -> AttachmentType.serializable(PlayerEssence::new).build());

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<PressedKeyCap>> KEY_PRESSED = REGISTRY.register(
            "key_pressed", () -> AttachmentType.serializable(PressedKeyCap::new).build());

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<PlayerArmor>> PLAYER_ARMOR = REGISTRY.register(
            "player_armor", () -> AttachmentType.serializable(PlayerArmor::new).build());
}

