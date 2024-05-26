package net.jitl.common.dialogue;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public class ClientDialogueNode {
	private final String textKey;
	private final List<String> optionTextKeys;
	private final LivingEntity npc;

	public ClientDialogueNode(ResourceLocation entityKey, String textKey, List<String> optionTextKeys) {
		this.textKey = textKey;
		this.optionTextKeys = optionTextKeys;

		EntityType<?> entry = BuiltInRegistries.ENTITY_TYPE.get(entityKey);
        assert Minecraft.getInstance().level != null;
        npc = (LivingEntity)entry.create(Minecraft.getInstance().level);
	}

	public List<String> getOptionTextKeys() {
		return optionTextKeys;
	}

	public String getTextKey() {
		return textKey;
	}

	public LivingEntity getNpc() {
		return npc;
	}
}
