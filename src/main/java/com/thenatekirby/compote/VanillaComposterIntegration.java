package com.thenatekirby.compote;

import com.thenatekirby.compote.registration.CompoteRecipe;
import com.thenatekirby.compote.util.ItemStackWithChance;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

// ====---------------------------------------------------------------------------====

class VanillaComposterIntegration {

    // ====---------------------------------------------------------------------------====
    // Helpers

    private static void addComposting(ItemStackWithChance itemStackWithChance) {
        if (!ComposterBlock.CHANCES.containsKey(itemStackWithChance.getItemStack().getItem())) {
            ComposterBlock.CHANCES.put(itemStackWithChance.getItemStack().getItem(), itemStackWithChance.getChance());
        }
    }

    private static void removeComposting(ItemStackWithChance itemStackWithChance) {
        if (ComposterBlock.CHANCES.containsKey(itemStackWithChance.getItemStack().getItem())) {
            ComposterBlock.CHANCES.removeFloat(itemStackWithChance.getItemStack().getItem());
        }
    }

    // ====---------------------------------------------------------------------------====

    static void registerVanillaComposterIntegration(@Nonnull RecipeManager recipeManager) {
        List<CompoteRecipe> recipes = new ArrayList<>();
        for (IRecipe<?> iRecipe : recipeManager.getRecipes()) {
            if (iRecipe instanceof CompoteRecipe) {
                recipes.add((CompoteRecipe) iRecipe);
            }
        }

        for (CompoteRecipe recipe: recipes) {
            for (ItemStackWithChance addition : recipe.getAdditions()) {
                addComposting(addition);
            }

            for (ItemStackWithChance removal : recipe.getRemovals()) {
                removeComposting(removal);
            }

            for (ItemStackWithChance change : recipe.getChanges()) {
                removeComposting(change);
                addComposting(change);
            }
        }
    }
}
