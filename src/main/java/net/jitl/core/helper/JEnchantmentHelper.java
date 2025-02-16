package net.jitl.core.helper;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class JEnchantmentHelper {

    public static int getEnchantmentAmount(Player player, ServerLevel level, ResourceKey<Enchantment> enchantment) {
        return 0;//TODO EnchantmentHelper.getEnchantmentLevel(level.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolder(enchantment).get(), player);
    }

}