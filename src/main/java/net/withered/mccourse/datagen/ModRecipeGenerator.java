package net.withered.mccourse.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.withered.mccourse.MCCourseMod;
import net.withered.mccourse.block.ModBlocks;
import net.withered.mccourse.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        List<ItemConvertible> FLUORITE_SMELTABLES = List.of(
                ModItems.RAW_FLUORITE,
                ModBlocks.FLUORITE_ORE,
                ModBlocks.FLUORITE_DEEPSLATE_ORE,
                ModBlocks.FLUORITE_NETHER_ORE,
                ModBlocks.FLUORITE_END_ORE
                );

        offerSmelting(exporter, FLUORITE_SMELTABLES, RecipeCategory.MISC, ModItems.FLUORITE, 0.2f, 200, "fluorite");
        offerBlasting(exporter, FLUORITE_SMELTABLES, RecipeCategory.MISC, ModItems.FLUORITE, 0.2f, 100, "fluorite");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.FLUORITE, RecipeCategory.DECORATIONS, ModBlocks.FLUORITE_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_FLUORITE)
                .pattern("SSS")
                .pattern("FFF")
                .pattern("SSS")
                .input('S', Blocks.STONE)
                .input('F', ModItems.FLUORITE)
                .criterion(hasItem(Blocks.STONE), conditionsFromItem(Blocks.STONE))
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(exporter, Identifier.of(MCCourseMod.MOD_ID, "raw_fluorite_test"));
    }
}
