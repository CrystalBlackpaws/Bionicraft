
package net.crystalblackpaws.bionicraft.blockentity;

import net.crystalblackpaws.bionicraft.Bionicraft;
import net.crystalblackpaws.bionicraft.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCKS_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Bionicraft.MOD_ID);

    public static final RegistryObject<BlockEntityType<MaskForgeBlockEntity>> MASK_FORGE_BLOCK_ENTITY = BLOCKS_ENTITIES.register("mask_forge",
            () -> BlockEntityType.Builder.of(MaskForgeBlockEntity::new, ModBlocks.MASK_FORGE.get())
                    .build(null));

}