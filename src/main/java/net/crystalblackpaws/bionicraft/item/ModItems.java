package net.crystalblackpaws.bionicraft.item;

import net.crystalblackpaws.bionicraft.Bionicraft;
import net.crystalblackpaws.bionicraft.block.ModBlocks;
import net.crystalblackpaws.bionicraft.item.custom.BambooDisk;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Bionicraft.MOD_ID);

    public static final RegistryObject<Item> BAMBOO_DISK = ITEMS.register("bamboo_disk",
            () -> new BambooDisk(new Item.Properties()));
    public static final RegistryObject<Item> RAW_PROTODERMIS = ITEMS.register("raw_protodermis",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PROTODERMIS_INGOT = ITEMS.register("protodermis_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<StandingAndWallBlockItem> LIGHTSTONE = ITEMS.register("lightstone", () -> new StandingAndWallBlockItem(ModBlocks.LIGHTSTONE.get(), ModBlocks.WALL_LIGHTSTONE.get(), new Item.Properties(), Direction.DOWN));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
    public static Item registerBlock(BlockItem pItem) {
        return registerBlock(pItem.getBlock(), pItem);
    }

    @SuppressWarnings("deprecation")
    public static Item registerBlock(Block pBlock, Item pItem) {
        return registerItem(BuiltInRegistries.BLOCK.getKey(pBlock), pItem);
    }
    public static Item registerItem(String pKey, Item pItem) {
        return registerItem(new ResourceLocation(pKey), pItem);
    }

    @SuppressWarnings("deprecation")
    public static Item registerItem(ResourceLocation pKey, Item pItem) {
        return registerItem(ResourceKey.create(BuiltInRegistries.ITEM.key(), pKey), pItem);
    }

    @SuppressWarnings("deprecation")
    public static Item registerItem(ResourceKey<Item> pKey, Item pItem) {
        if (pItem instanceof BlockItem) {
            ((BlockItem)pItem).registerBlocks(Item.BY_BLOCK, pItem);
        }

        return Registry.register(BuiltInRegistries.ITEM, pKey, pItem);
    }
}
