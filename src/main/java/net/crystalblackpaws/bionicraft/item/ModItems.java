package net.crystalblackpaws.bionicraft.item;

import net.crystalblackpaws.bionicraft.Bionicraft;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Bionicraft.MOD_ID);

    public static final RegistryObject<Item> LIGHTSTONE = ITEMS.register("lightstone",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_PROTODERMIS = ITEMS.register("raw_protodermis",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PROTODERMIS_INGOT = ITEMS.register("protodermis_ingot",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
