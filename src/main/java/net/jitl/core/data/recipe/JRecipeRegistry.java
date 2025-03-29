package net.jitl.core.data.recipe;

import net.jitl.core.data.JRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.concurrent.CompletableFuture;

public class JRecipeRegistry extends RecipeProvider.Runner {

    public JRecipeRegistry(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        return new JRecipeProvider(provider, recipeOutput);
    }

    @Override
    public String getName() {
        return "Journey Recipes";
    }
}