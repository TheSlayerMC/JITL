package net.jitl.core.init.compat.emi;


import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiInitRegistry;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.stack.EmiStack;
import net.jitl.core.helper.EnumSummoningRecipes;
import net.jitl.core.init.compat.emi.category.SummoningTableEmiCategory;
import net.jitl.core.init.compat.emi.recipe.SummoningTableEmiRecipe;
import net.jitl.core.init.internal.JBlocks;

@EmiEntrypoint
public class JITLEmiPlugin implements EmiPlugin {
    public static final SummoningTableEmiCategory SUMMONING_TABLE = new SummoningTableEmiCategory();

    @Override
    public void initialize(EmiInitRegistry registry) {
        EmiPlugin.super.initialize(registry);
    }

    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(SUMMONING_TABLE);
        registry.addWorkstation(SUMMONING_TABLE, EmiStack.of(JBlocks.SUMMONING_TABLE));
        for (EnumSummoningRecipes recipe : EnumSummoningRecipes.values()) {
            registry.addRecipe(new SummoningTableEmiRecipe(recipe));
        }
    }
}