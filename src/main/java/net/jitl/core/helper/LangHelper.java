package net.jitl.core.helper;

import net.jitl.core.init.JITL;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class LangHelper {

	public static MutableComponent getFormattedText(String format) {
		return i18n(format);
	}

	public static MutableComponent i18n(String text, Object... args) {
		MutableComponent result = Component.translatable(text, args);
		result.withStyle(ChatFormatting.GRAY);
		return result;
	}

	public static MutableComponent getEfficiency() {
		return getFormattedText("jitl.efficiency");
	}

	public static MutableComponent getUsesRemaining() {
		return getFormattedText("jitl.uses_remaining");
	}

	public static MutableComponent getInfiniteUses() {
		return getFormattedText("jitl.infinite");
	}

	public static MutableComponent useEssence(int amount) {
		return Component.translatable(getFormattedText("journey.uses") + " " + amount + " " + getFormattedText("jitl.essence"));
	}

	public static MutableComponent rangedDamage(int damage) {
		return Component.translatable(damage + " " + getFormattedText("jitl.ranged_damage"));
	}

	public static MutableComponent unbreakable() {
		return getFormattedText("jitl.unbreakable");
	}

	public static MutableComponent setWitherSword(int time) {
		return Component.translatable(getFormattedText("jitl.hit") + " " + getFormattedText("jitl.wither") + " " + time + " " + getFormattedText("jitl.seconds"));
	}

	public static MutableComponent setBossSpawner(String boss) {
		return Component.translatable("jitl.boss.spawn", boss);
	}

	public static MutableComponent setPetSpawner(String pet) {
		return Component.translatable("jitl.pet.spawn", pet);
	}

	public static MutableComponent nameToKey(String name) {
		return Component.translatable(name.trim().replace(" ", "_").toLowerCase());
	}

	public static MutableComponent withModPrefix(String str) {
		return Component.translatable(JITL.MODID + "." + str);
	}
}