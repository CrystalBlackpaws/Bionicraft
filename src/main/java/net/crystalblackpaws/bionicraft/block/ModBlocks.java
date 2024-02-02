package net.crystalblackpaws.bionicraft.block;

import net.crystalblackpaws.bionicraft.Bionicraft;
import net.crystalblackpaws.bionicraft.block.custom.Lightstone;
import net.crystalblackpaws.bionicraft.block.custom.MaskForge;
import net.crystalblackpaws.bionicraft.block.custom.ModFlammableRotatedPillarBlock;
import net.crystalblackpaws.bionicraft.block.custom.WallLightStone;
import net.crystalblackpaws.bionicraft.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Bionicraft.MOD_ID);

    public static final RegistryObject<MaskForge> MASK_FORGE = registerBlock("mask_forge",
            () -> new MaskForge(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE).noOcclusion()));
    public static final RegistryObject<Lightstone> LIGHTSTONE = registerBlock("lightstone_placed",
            () -> new Lightstone(BlockBehaviour.Properties.copy(Blocks.GLOWSTONE)
                    .sound(SoundType.AMETHYST).noOcclusion()));
    public static final RegistryObject<WallLightStone> WALL_LIGHTSTONE = registerBlock("wall_lightstone_placed",
            () -> new WallLightStone(BlockBehaviour.Properties.copy(Blocks.GLOWSTONE)
                    .sound(SoundType.AMETHYST).noOcclusion()));

    public static final RegistryObject<Block> LIGHTSTONE_ORE = registerBlock("lightstone_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.GLOWSTONE)
                    .sound(SoundType.AMETHYST).requiresCorrectToolForDrops(), UniformInt.of(1,3)));
    public static final RegistryObject<Block> DEEPSLATE_LIGHTSTONE_ORE = registerBlock("deepslate_lightstone_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.GLOWSTONE)
                    .sound(SoundType.AMETHYST).requiresCorrectToolForDrops(), UniformInt.of(1,3)));
    public static final RegistryObject<Block> NETHER_LIGHTSTONE_ORE = registerBlock("nether_lightstone_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.GLOWSTONE)
                    .sound(SoundType.AMETHYST).requiresCorrectToolForDrops(), UniformInt.of(1,3)));
    public static final RegistryObject<Block> PROTODERMIS_ORE = registerBlock("protodermis_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.GOLD_ORE)
                    .requiresCorrectToolForDrops(), UniformInt.of(3,7)));
    public static final RegistryObject<Block> DEEPSLATE_PROTODERMIS_ORE = registerBlock("deepslate_protodermis_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_GOLD_ORE)
                    .requiresCorrectToolForDrops(), UniformInt.of(3,7)));
    public static final RegistryObject<Block> END_PROTODERMIS_ORE = registerBlock("end_protodermis_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE)
                    .requiresCorrectToolForDrops(), UniformInt.of(3,7)));
    public static final RegistryObject<Block> VILLAGE_STONE = registerBlock("village_stone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRANITE)));
    public static final RegistryObject<Block> BLACK_VILLAGE_STONE = registerBlock("black_village_stone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRANITE)));
    public static final RegistryObject<Block> LIGHTSTONE_BLOCK = registerBlock("lightstone_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GLOWSTONE).sound(SoundType.AMETHYST)));
    public static final RegistryObject<Block> RAW_PROTODERMIS_BLOCK = registerBlock("raw_protodermis_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_GOLD_BLOCK)));
    public static final RegistryObject<Block> PROTODERMIS_BLOCK = registerBlock("protodermis_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK)));

    public static final RegistryObject<Block> BASALT_PILLAR = registerBlock("basalt_pillar",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.POLISHED_BASALT)));
    public static final RegistryObject<Block> CHISELED_BASALT = registerBlock("chiseled_basalt",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.POLISHED_BASALT)));

    public static final RegistryObject<Block> PALM_LOG = registerBlock("palm_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> PALM_WOOD = registerBlock("palm_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
    public static final RegistryObject<Block> STRIPPED_PALM_LOG = registerBlock("stripped_palm_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<Block> STRIPPED_PALM_WOOD = registerBlock("stripped_palm_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));

    public static final RegistryObject<Block> PALM_PLANKS = registerBlock("palm_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> PALM_LEAVES = registerBlock("palm_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));



    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
