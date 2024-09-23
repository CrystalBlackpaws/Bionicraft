package net.crystalblackpaws.bionicraft.entity.custom;

import net.crystalblackpaws.bionicraft.entity.ModEntities;
import net.crystalblackpaws.bionicraft.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class BambooDiskThrowable extends ThrowableItemProjectile {

    private BlockState lastState;
    protected boolean inGround;
    protected int inGroundTime;
    private int life;
    //public Pickup pickup;


    public BambooDiskThrowable(EntityType<? extends BambooDiskThrowable> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public BambooDiskThrowable(Level pLevel, LivingEntity pShooter) {
        super(ModEntities.BAMBOO_DISK_THROWABLE.get(), pShooter, pLevel);
    }

    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            if (this.inGround && this.inGroundTime != 0 && this.inGroundTime >= 600) {
                this.level().broadcastEntityEvent(this, (byte) 0);
            }
        }
        BlockPos blockpos = this.blockPosition();
        BlockState blockstate = this.level().getBlockState(blockpos);
        Vec3 vec33;
        if (!blockstate.isAir()) {
            VoxelShape voxelshape = blockstate.getCollisionShape(this.level(), blockpos);
            if (!voxelshape.isEmpty()) {
                vec33 = this.position();

                for (AABB aabb : voxelshape.toAabbs()) {
                    if (aabb.move(blockpos).contains(vec33)) {
                        this.inGround = true;
                        break;
                    }
                }
            }
        }

        if (this.inGround) {
            if (this.lastState != blockstate && this.shouldFall()) {
                this.startFalling();
            } else if (!this.level().isClientSide) {
                this.tickDespawn();
            }

            ++this.inGroundTime;
        } else {
            this.inGroundTime = 0;
        }
    }

    protected void tickDespawn() {
        ++this.life;
        if (this.life >= 1200) {
            this.discard();
        }

    }

    private boolean shouldFall() {
            return this.inGround && this.level().noCollision((new AABB(this.position(), this.position())).inflate(0.06));
        }

    private void startFalling() {
        this.inGround = false;
        Vec3 vec3 = this.getDeltaMovement();
        this.setDeltaMovement(vec3.multiply(this.random.nextFloat() * 0.2F, this.random.nextFloat() * 0.2F, this.random.nextFloat() * 0.2F));
        this.life = 0;
    }


    public void move(@NotNull MoverType pType, @NotNull Vec3 pPos) {
        super.move(pType, pPos);
        if (pType != MoverType.SELF && this.shouldFall()) {
            this.startFalling();
        }

    }
    @Override
    protected @NotNull Item getDefaultItem() {
        return ModItems.BAMBOO_DISK.get();
    }

    @Override
    protected void onHitBlock(BlockHitResult hit) {
        this.lastState = this.level().getBlockState(hit.getBlockPos());
        super.onHitBlock(hit);
        Vec3 vec3 = hit.getLocation().subtract(this.getX(), this.getY(), this.getZ());
        this.setDeltaMovement(vec3);
        Vec3 vec31 = vec3.normalize().scale(0.05000000074505806);
        this.setPosRaw(this.getX() - vec31.x, this.getY() - vec31.y, this.getZ() - vec31.z);
        this.inGround = true;
    }

    protected void onHitEntity(@NotNull EntityHitResult hit){
        super.onHitEntity(hit);
        Entity entity = hit.getEntity();
    }

    public void addAdditionalSaveData(@NotNull CompoundTag com) {
        super.addAdditionalSaveData(com);
        com.putShort("life", (short)this.life);
        if (this.lastState != null) {
            com.put("inBlockState", NbtUtils.writeBlockState(this.lastState));
        }

        com.putBoolean("inGround", this.inGround);
    }

    public void readAdditionalSaveData(@NotNull CompoundTag com) {
        super.readAdditionalSaveData(com);
        this.life = com.getShort("life");
        if (com.contains("inBlockState", 10)) {
            this.lastState = NbtUtils.readBlockState(this.level().holderLookup(Registries.BLOCK), com.getCompound("inBlockState"));
        }

        this.inGround = com.getBoolean("inGround");
    }


    public void playerTouch(@NotNull Player player) {
        if (!this.level().isClientSide && this.inGround) {
            player.getInventory().add(this.getPickupItem());
            this.discard();
        }

    }
    public void setOwner(@Nullable Entity pEntity) {
        super.setOwner(pEntity);
    }

    protected @NotNull ItemStack getPickupItem() {
        return new ItemStack(ModItems.BAMBOO_DISK.get());
    }

}
