package com.thenatekirby.compote.util;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;

import javax.annotation.Nullable;

// ====---------------------------------------------------------------------------====

public class ItemStackWithChance {
    private ItemStack itemStack;
    private float chance;

    private ItemStackWithChance(ItemStack itemStack, float chance) {
        this.itemStack = itemStack;
        this.chance = chance;
    }

    @Nullable
    public static ItemStackWithChance from(JsonObject jsonObject) {
        ItemStack itemStack;
        float chance;

        if (jsonObject.has("item")) {
            if (jsonObject.get("item").isJsonObject()) {
                itemStack = ShapedRecipe.deserializeItem(jsonObject.get("item").getAsJsonObject());
            } else {
                itemStack = ShapedRecipe.deserializeItem(jsonObject);
            }

        } else {
            return null;
        }

        if (jsonObject.has("chance")) {
            chance = jsonObject.getAsJsonPrimitive("chance").getAsFloat();
        } else {
            chance = 1.0f;
        }

        return new ItemStackWithChance(itemStack, chance);
    }

    // ====---------------------------------------------------------------------------====
    // Getters

    public ItemStack getItemStack() {
        return itemStack;
    }

    public float getChance() {
        return chance;
    }

    // ====---------------------------------------------------------------------------====
    // Serialization

    public void write(PacketBuffer buffer) {
        buffer.writeItemStack(itemStack);
        buffer.writeFloat(chance);
    }

    public static ItemStackWithChance read(PacketBuffer buffer) {
        ItemStack itemStack = buffer.readItemStack();
        float chance = buffer.readFloat();
        return new ItemStackWithChance(itemStack, chance);
    }
}
