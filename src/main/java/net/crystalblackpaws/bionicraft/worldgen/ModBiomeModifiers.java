package net.crystalblackpaws.bionicraft.worldgen;

import net.crystalblackpaws.bionicraft.Bionicraft;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_PROTODERMIS_ORE = registerKey("add_protodermis_ore");
    public static final ResourceKey<BiomeModifier> ADD_END_PROTODERMIS_ORE = registerKey("add_end_protodermis_ore");
    public static final ResourceKey<BiomeModifier> ADD_LIGHTSTONE_ORE = registerKey("add_lightstone_ore");
    public static final ResourceKey<BiomeModifier> ADD_NETHER_LIGHTSTONE_ORE = registerKey("add_nether_lightstone_ore");

    public static void boostrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_PROTODERMIS_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlaceFeatures.PROTODERMIS_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_END_PROTODERMIS_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_END),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlaceFeatures.END_PROTODERMIS_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_LIGHTSTONE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlaceFeatures.LIGHTSTONE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_NETHER_LIGHTSTONE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlaceFeatures.NETHER_LIGHTSTONE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));


    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Bionicraft.MOD_ID, name));
    }
}
