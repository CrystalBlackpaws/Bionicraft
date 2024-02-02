package net.crystalblackpaws.bionicraft.entity;

import net.crystalblackpaws.bionicraft.Bionicraft;
import net.crystalblackpaws.bionicraft.entity.custom.BambooDiskThrowable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModEntities {

        public static final DeferredRegister<EntityType<?>> ENTITIES =
                DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,Bionicraft.MOD_ID);

        public static final RegistryObject<EntityType<BambooDiskThrowable>> BAMBOO_DISK_THROWABLE = ENTITIES.register("bamboo_disk_throwable",
                () -> EntityType.Builder
                        .<BambooDiskThrowable>of(BambooDiskThrowable::new, MobCategory.MISC)
                        .sized(0.5f, 0.5f)
                        .build("bamboo_disk_throwable"));

        public static void register(IEventBus eventBus) {
                ENTITIES.register(eventBus);
        }
}
