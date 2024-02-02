
package net.crystalblackpaws.bionicraft.blockentity;

import net.crystalblackpaws.bionicraft.Bionicraft;
import net.crystalblackpaws.bionicraft.menu.MaskForgeMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class MaskForgeBlockEntity extends BlockEntity implements MenuProvider {

    private static final int POTION_INPUT_SLOT = 0;

    private static final int FUEL_INPUT_SLOT = 1;

    private static final int DISC_INPUT_SLOT = 2;

    private static final int INPUT_SLOT = 4;
    private static final int OUTPUT_SLOT = 5;
    private static final Component TITLE =
            Component.translatable("container." + Bionicraft.MOD_ID + ".mask_forge");

    private final ItemStackHandler inventory = new ItemStackHandler(27) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            MaskForgeBlockEntity.this.setChanged();
        }
    };

    private final LazyOptional<ItemStackHandler> optional = LazyOptional.of(() -> this.inventory);

    public MaskForgeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MASK_FORGE_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        CompoundTag BionicraftData = nbt.getCompound(Bionicraft.MOD_ID);
        this.inventory.deserializeNBT(BionicraftData.getCompound("Inventory"));
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag nbt) {
        super.saveAdditional(nbt);
        var BionicraftData = new CompoundTag();
        BionicraftData.put("Inventory", this.inventory.serializeNBT());
        nbt.put(Bionicraft.MOD_ID, BionicraftData);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        return cap == ForgeCapabilities.ITEM_HANDLER ? this.optional.cast() : super.getCapability(cap);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.optional.invalidate();
    }

    public LazyOptional<ItemStackHandler> getOptional() {
        return this.optional;
    }

    public ItemStackHandler getInventory() {
        return this.inventory;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return TITLE;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory, Player pPlayer) {
        return new MaskForgeMenu(pContainerId, pPlayerInventory, this);
    }
}