package net.crystalblackpaws.bionicraft.client.handler;

import net.crystalblackpaws.bionicraft.Bionicraft;
import net.crystalblackpaws.bionicraft.client.screen.*;
import net.crystalblackpaws.bionicraft.menu.ModMenus;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Bionicraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModHandler {


    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() ->
                MenuScreens.register(ModMenus.MASK_FORGE_MENU.get(), MaskForgeScreen::new));

    }
}
