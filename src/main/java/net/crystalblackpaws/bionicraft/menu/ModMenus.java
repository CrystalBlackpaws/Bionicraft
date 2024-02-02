package net.crystalblackpaws.bionicraft.menu;

import net.crystalblackpaws.bionicraft.Bionicraft;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, Bionicraft.MOD_ID);

    public static final RegistryObject<MenuType<MaskForgeMenu>> MASK_FORGE_MENU = MENU_TYPES.register("mask_forge_menu",
            () -> IForgeMenuType.create(MaskForgeMenu::new));

    public static void register(IEventBus eventBus) {
        MENU_TYPES.register(eventBus);
    }

}
