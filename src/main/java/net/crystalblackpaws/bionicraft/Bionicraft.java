package net.crystalblackpaws.bionicraft;

import com.mojang.logging.LogUtils;
import net.crystalblackpaws.bionicraft.block.ModBlocks;
import net.crystalblackpaws.bionicraft.blockentity.ModBlockEntities;
import net.crystalblackpaws.bionicraft.entity.ModEntities;
import net.crystalblackpaws.bionicraft.item.ModCreativeModTabs;
import net.crystalblackpaws.bionicraft.item.ModItems;
import net.crystalblackpaws.bionicraft.menu.ModMenus;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(Bionicraft.MOD_ID)
public class Bionicraft {
    public static final String MOD_ID = "bionicraft";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Bionicraft() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModTabs.register(modEventBus);

        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlockEntities.BLOCKS_ENTITIES.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);
        ModMenus.MENU_TYPES.register(modEventBus);



        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(ModItems.LIGHTSTONE);
        }
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.RAW_PROTODERMIS);
            event.accept(ModItems.PROTODERMIS_INGOT);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.BAMBOO_DISK_THROWABLE.get(), ThrownItemRenderer::new);
        }
    }
}