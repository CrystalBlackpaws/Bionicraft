package net.crystalblackpaws.bionicraft.item;

import net.crystalblackpaws.bionicraft.Bionicraft;
import net.crystalblackpaws.bionicraft.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Bionicraft.MOD_ID);

    public static final RegistryObject<CreativeModeTab> BIONICRAFT_RESOURCES = CREATIVE_MODE_TABS.register("bionicraft_resources",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.LIGHTSTONE.get()))
                    .title(Component.translatable("creativetab.bionicraft_resources"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.LIGHTSTONE.get());
                        pOutput.accept(ModItems.RAW_PROTODERMIS.get());
                        pOutput.accept(ModItems.PROTODERMIS_INGOT.get());

                        pOutput.accept(ModBlocks.LIGHTSTONE_BLOCK.get());
                        pOutput.accept(ModBlocks.RAW_PROTODERMIS_BLOCK.get());
                        pOutput.accept(ModBlocks.PROTODERMIS_BLOCK.get());

                        pOutput.accept(ModBlocks.LIGHTSTONE_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_LIGHTSTONE_ORE.get());
                        pOutput.accept(ModBlocks.NETHER_LIGHTSTONE_ORE.get());
                        pOutput.accept(ModBlocks.PROTODERMIS_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_PROTODERMIS_ORE.get());
                        pOutput.accept(ModBlocks.END_PROTODERMIS_ORE.get());

                        pOutput.accept(ModBlocks.VILLAGE_STONE.get());
                        pOutput.accept(ModBlocks.BLACK_VILLAGE_STONE.get());

                        pOutput.accept(ModBlocks.PALM_LOG.get());
                        pOutput.accept(ModBlocks.PALM_WOOD.get());
                        pOutput.accept(ModBlocks.PALM_LEAVES.get());


                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> BIONICRAFT_DECORATIVE_BLOCKS = CREATIVE_MODE_TABS.register("bionicraft_decorative_blocks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.CHISELED_BASALT.get()))
                    .title(Component.translatable("creativetab.bionicraft_decorative_blocks"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.LIGHTSTONE_BLOCK.get());
                        pOutput.accept(ModBlocks.PROTODERMIS_BLOCK.get());

                        pOutput.accept(ModBlocks.BASALT_PILLAR.get());
                        pOutput.accept(ModBlocks.CHISELED_BASALT.get());

                        pOutput.accept(ModBlocks.PALM_PLANKS.get());

                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
