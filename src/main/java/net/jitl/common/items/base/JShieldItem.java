package net.jitl.common.items.base;

import net.jitl.client.render.item.JShieldRenderer;
import net.jitl.core.helper.JArmorMaterial;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class JShieldItem extends ShieldItem {

    public final ResourceLocation texture;
    private final Ingredient repairItem;

    public JShieldItem(JArmorMaterial name, int uses, Item repairItem) {
        super(JItems.itemProps().durability(uses));
        this.repairItem = Ingredient.of(repairItem);
        this.texture = JITL.rl("textures/shield/" + name.getRawName() + "_shield.png");
    }

    public JShieldItem(String name, int uses, Item repairItem) {
        super(JItems.itemProps().durability(uses));
        this.repairItem = Ingredient.of(repairItem);
        this.texture = JITL.rl("textures/shield/" + name + "_shield.png");
    }

    @Override
    public boolean isValidRepairItem(@NotNull ItemStack shield, @NotNull ItemStack repair) {
        if(repairItem != null) {
            return repairItem.test(repair) || super.isValidRepairItem(shield, repair);
        } else {
            return false;
        }
    }

    @Override
    public void initializeClient(@NotNull Consumer<IClientItemExtensions> consumer) {
        super.initializeClient(consumer);
        consumer.accept(RenderProps.INSTANCE);
    }

    static class RenderProps implements IClientItemExtensions {
        public static RenderProps INSTANCE = new RenderProps();

        @Override
        public BlockEntityWithoutLevelRenderer getCustomRenderer() {
            return new JShieldRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        }
    }
}
