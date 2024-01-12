package net.crystalblackpaws.bionicraft.worldgen;

import net.crystalblackpaws.bionicraft.Bionicraft;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlaceFeatures {
    public static final ResourceKey<PlacedFeature> PROTODERMIS_ORE_PLACED_KEY =  registerKey("protodermis_ore_placed");
    public static final ResourceKey<PlacedFeature> END_PROTODERMIS_ORE_PLACED_KEY =  registerKey("end_protodermis_ore_placed");
    public static final ResourceKey<PlacedFeature> LIGHTSTONE_ORE_PLACED_KEY =  registerKey("lightstone_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_LIGHTSTONE_ORE_PLACED_KEY =  registerKey("nether_lightstone_ore_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, PROTODERMIS_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_PROTODERMIS_ORE_KEY),
                ModOrePlacement.rareOrePlacement( 4,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(10))));

        register(context, END_PROTODERMIS_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_PROTODERMIS_ORE_KEY),
                ModOrePlacement.rareOrePlacement( 8,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(10))));

        register(context, LIGHTSTONE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_LIGHTSTONE_ORE_KEY),
                ModOrePlacement.rareOrePlacement( 12,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(62))));

        register(context, NETHER_LIGHTSTONE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_LIGHTSTONE_ORE_KEY),
                ModOrePlacement.rareOrePlacement( 2,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(256))));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Bionicraft.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
