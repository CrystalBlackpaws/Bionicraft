package net.crystalblackpaws.bionicraft.entity.custom;

import net.crystalblackpaws.bionicraft.entity.ModEntities;
import net.crystalblackpaws.bionicraft.item.ModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

public class BambooDiskThrowable extends ThrowableItemProjectile {
    public BambooDiskThrowable(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public BambooDiskThrowable(Level pLevel) {
        super(ModEntities.BAMBOO_DISK_THROWABLE.get(), pLevel);
    }

    public BambooDiskThrowable(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.BAMBOO_DISK_THROWABLE.get(), livingEntity, pLevel);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected Item getDefaultItem() {
        return ModItems.BAMBOO_DISK.get();
    }

    protected void onHitEntity(@NotNull EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        int i = entity instanceof Blaze ? 3 : 0;
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), (float)i);
    }

    protected void onHit(@NotNull HitResult pResult) {
        super.onHit(pResult);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }

    }

}
