package net.crystalblackpaws.bionicraft.datagen;
import io.netty.util.Attribute;
import net.crystalblackpaws.bionicraft.Bionicraft;
import net.crystalblackpaws.bionicraft.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Bionicraft.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.LIGHTSTONE_BLOCK);
        blockWithItem(ModBlocks.PROTODERMIS_BLOCK);

        blockWithItem(ModBlocks.LIGHTSTONE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_LIGHTSTONE_ORE);
        blockWithItem(ModBlocks.NETHER_LIGHTSTONE_ORE);
        blockWithItem(ModBlocks.PROTODERMIS_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_PROTODERMIS_ORE);
        blockWithItem(ModBlocks.END_PROTODERMIS_ORE);

        blockWithItem(ModBlocks.VILLAGE_STONE);
        blockWithItem(ModBlocks.BLACK_VILLAGE_STONE);

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}