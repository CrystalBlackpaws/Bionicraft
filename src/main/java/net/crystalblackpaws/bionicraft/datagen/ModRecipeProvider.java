package net.crystalblackpaws.bionicraft.datagen;

import net.crystalblackpaws.bionicraft.Bionicraft;
import net.crystalblackpaws.bionicraft.block.ModBlocks;
import net.crystalblackpaws.bionicraft.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> PROTODERMIS_SMELTABLES = List.of(ModItems.RAW_PROTODERMIS.get(),
            ModBlocks.PROTODERMIS_ORE.get(), ModBlocks.DEEPSLATE_PROTODERMIS_ORE.get(), ModBlocks.END_PROTODERMIS_ORE.get());

    private static final List<ItemLike> LIGHTSTONE_SMELTABLES = List.of(ModItems.LIGHTSTONE.get(),
            ModBlocks.LIGHTSTONE_ORE.get(), ModBlocks.DEEPSLATE_LIGHTSTONE_ORE.get(), ModBlocks.NETHER_LIGHTSTONE_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreSmelting(pWriter, PROTODERMIS_SMELTABLES, RecipeCategory.MISC, ModItems.PROTODERMIS_INGOT.get(), 0.25f, 200, "protodermis");
        oreBlasting(pWriter, PROTODERMIS_SMELTABLES, RecipeCategory.MISC, ModItems.PROTODERMIS_INGOT.get(), 0.25f, 100, "protodermis");
        oreSmelting(pWriter, LIGHTSTONE_SMELTABLES, RecipeCategory.MISC, ModItems.LIGHTSTONE.get(), 0.25f, 200, "lightstone");
        oreBlasting(pWriter, LIGHTSTONE_SMELTABLES, RecipeCategory.MISC, ModItems.LIGHTSTONE.get(), 0.25f, 100, "lightstone");


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PROTODERMIS_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.PROTODERMIS_INGOT.get())
                .unlockedBy(getHasName(ModItems.PROTODERMIS_INGOT.get()), has(ModItems.PROTODERMIS_INGOT.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.PROTODERMIS_INGOT.get(), 9)
                .requires(ModBlocks.PROTODERMIS_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.PROTODERMIS_BLOCK.get()), has(ModBlocks.PROTODERMIS_BLOCK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LIGHTSTONE_BLOCK.get())
                .pattern("lll")
                .pattern("lll")
                .pattern("lll")
                .define('l', ModItems.LIGHTSTONE.get())
                .unlockedBy(getHasName(ModItems.LIGHTSTONE.get()), has(ModItems.LIGHTSTONE.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LIGHTSTONE.get(), 9)
                .requires(ModBlocks.LIGHTSTONE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.LIGHTSTONE_BLOCK.get()), has(ModBlocks.LIGHTSTONE_BLOCK.get()))
                .save(pWriter);

    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                            pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer,  Bionicraft.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
