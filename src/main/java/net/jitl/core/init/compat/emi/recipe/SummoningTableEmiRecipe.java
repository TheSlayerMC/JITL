package net.jitl.core.init.compat.emi.recipe;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.SlotWidget;
import dev.emi.emi.api.widget.WidgetHolder;
import net.jitl.core.helper.EnumSummoningRecipes;
import net.jitl.core.init.compat.emi.JITLEmiPlugin;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SummoningTableEmiRecipe implements EmiRecipe {
    private static final ResourceLocation RECIPE_BACKGROUND = ResourceLocation.fromNamespaceAndPath("jitl", "textures/gui/summoning_table_recipe.png");
    private final ResourceLocation id;
    private final List<EmiIngredient> inputs;
    private final List<EmiStack> outputs;

    public SummoningTableEmiRecipe(EnumSummoningRecipes recipe) {
        this.id = ResourceLocation.fromNamespaceAndPath("jitl", "/summoning_table/" + recipe.name().toLowerCase());

        this.inputs = Arrays.stream(recipe.getInputs())
                .map(EmiStack::of)
                .collect(Collectors.toList());

        this.outputs = List.of(EmiStack.of(recipe.getOutput()));
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return JITLEmiPlugin.SUMMONING_TABLE;
    }

    @Override
    public @Nullable ResourceLocation getId() {
        return id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return inputs;
    }

    @Override
    public List<EmiStack> getOutputs() {
        return outputs;
    }

    @Override
    public int getDisplayWidth() {
        // This refers to 112 pixels from the Summoning Table recipe pattern - 8 pixels of the default pattern from EMI
        return 104;
    }

    @Override
    public int getDisplayHeight() {
        // This refers to 62 pixels from the Summoning Table recipe pattern - 8 pixels of the default pattern from EMI
        return 54;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        // Starting in -4 to correctly place the Summoning Table background on top of EMI's one
        int xStart = -4;
        int yStart = -4;

        widgets.addTexture(new EmiTexture(RECIPE_BACKGROUND, 0, 0, 112, 62), xStart, yStart);

        int[][] slotPositions = {
                {xStart + 4, yStart + 4},    // Top-left
                {xStart + 4, yStart + 22},   // Center-left
                {xStart + 40, yStart + 4},   // Top-right
                {xStart + 22, yStart + 22},  // Center
                {xStart + 4, yStart + 40},   // Bottom-left
                {xStart + 40, yStart + 22},  // Center-right
                {xStart + 40, yStart + 40},  // Bottom-right
        };
        for (int i = 0; i < inputs.size(); i++) {
            // Create a new slot widget for each input and don't draw the default grey background of the slot
            SlotWidget slot = new SlotWidget(inputs.get(i), slotPositions[i][0], slotPositions[i][1]);
            slot.drawBack(false);
            widgets.add(slot);
        }

        SlotWidget outputSlot = new SlotWidget(outputs.getFirst(), xStart + 86, yStart + 22);
        outputSlot.drawBack(false);
        widgets.add(outputSlot).recipeContext(this);
    }
}