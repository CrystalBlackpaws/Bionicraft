package net.crystalblackpaws.bionicraft.datagen;

import net.crystalblackpaws.bionicraft.Bionicraft;
import net.crystalblackpaws.bionicraft.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Bionicraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleWItem(ModItems.LIGHTSTONE);
        simpleItem(ModItems.RAW_PROTODERMIS);
        simpleItem(ModItems.PROTODERMIS_INGOT);
        simpleItem(ModItems.BAMBOO_DISK);

    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Bionicraft.MOD_ID,"item/" + item.getId().getPath()));
    }
    private ItemModelBuilder simpleWItem(RegistryObject<StandingAndWallBlockItem> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Bionicraft.MOD_ID,"item/" + item.getId().getPath()));
    }
}