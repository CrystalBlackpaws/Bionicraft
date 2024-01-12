package net.crystalblackpaws.bionicraft.datagen;

import net.crystalblackpaws.bionicraft.Bionicraft;
import net.crystalblackpaws.bionicraft.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Bionicraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.PROTODERMIS_BLOCK.get(),
                        ModBlocks.RAW_PROTODERMIS_BLOCK.get(),
                        ModBlocks.PROTODERMIS_ORE.get(),
                        ModBlocks.DEEPSLATE_PROTODERMIS_ORE.get(),
                        ModBlocks.END_PROTODERMIS_ORE.get(),
                        ModBlocks.NETHER_LIGHTSTONE_ORE.get(),
                        ModBlocks.LIGHTSTONE_ORE.get(),
                        ModBlocks.DEEPSLATE_LIGHTSTONE_ORE.get(),
                        ModBlocks.LIGHTSTONE_BLOCK.get(),
                        ModBlocks.BLACK_VILLAGE_STONE.get(),
                        ModBlocks.VILLAGE_STONE.get(),
                        ModBlocks.BASALT_PILLAR.get(),
                        ModBlocks.CHISELED_BASALT.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.PROTODERMIS_BLOCK.get(),
                        ModBlocks.RAW_PROTODERMIS_BLOCK.get(),
                        ModBlocks.PROTODERMIS_ORE.get(),
                        ModBlocks.DEEPSLATE_PROTODERMIS_ORE.get(),
                        ModBlocks.END_PROTODERMIS_ORE.get());

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                ;

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.NETHER_LIGHTSTONE_ORE.get(),
                        ModBlocks.LIGHTSTONE_ORE.get(),
                        ModBlocks.DEEPSLATE_LIGHTSTONE_ORE.get(),
                        ModBlocks.LIGHTSTONE_BLOCK.get(),
                        ModBlocks.BLACK_VILLAGE_STONE.get(),
                        ModBlocks.VILLAGE_STONE.get(),
                        ModBlocks.BASALT_PILLAR.get(),
                        ModBlocks.CHISELED_BASALT.get());

        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                ;


    }
}