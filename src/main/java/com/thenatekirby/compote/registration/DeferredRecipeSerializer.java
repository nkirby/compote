package com.thenatekirby.compote.registration;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

// ====---------------------------------------------------------------------------====

class DeferredRecipeSerializer <T extends IRecipeSerializer<?>> {
    private RegistryObject<T> registryObject;

    DeferredRecipeSerializer(String name, Supplier<T> supplier, DeferredRegister<IRecipeSerializer<?>> recipeRegister) {
        this.registryObject = recipeRegister.register(name, supplier);
    }

    T getAsRecipeSerializer() {
        return registryObject.get();
    }
}
