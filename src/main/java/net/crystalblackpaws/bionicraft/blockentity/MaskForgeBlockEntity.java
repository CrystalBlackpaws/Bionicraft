
package net.crystalblackpaws.bionicraft.blockentity;

import net.crystalblackpaws.bionicraft.Bionicraft;
import net.crystalblackpaws.bionicraft.menu.MaskForgeMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class MaskForgeBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer, RecipeHolder, StackedContentsCompatible {

    protected final ContainerData dataAccess;
    private static final int POTION_INPUT_SLOT = 0;
    private static final int FUEL_INPUT_SLOT = 1;
    private static final int DISC_INPUT_SLOT = 2;
    private static final int INPUT_SLOT = 4;
    private static final int OUTPUT_SLOT = 5;
    int brewTime, fuel;
    private NonNullList<ItemStack> items;
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
        this.items = NonNullList.withSize(5, ItemStack.EMPTY);
        this.dataAccess = new ContainerData() {
            public int get(int p_59038_) {
                switch (p_59038_) {
                    case 0 -> {
                        return MaskForgeBlockEntity.this.brewTime;
                    }
                    case 1 -> {
                        return MaskForgeBlockEntity.this.fuel;
                    }
                    default -> {
                        return 0;
                    }
                }
            }

            public void set(int p_59040_, int p_59041_) {
                switch (p_59040_) {
                    case 0 -> MaskForgeBlockEntity.this.brewTime = p_59041_;
                    case 1 -> MaskForgeBlockEntity.this.fuel = p_59041_;
                }

            }

            public int getCount() {
                return 2;
            }
        };

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

    @Override
    protected @NotNull Component getDefaultName() {
        return null;
    }

    protected @NotNull AbstractContainerMenu createMenu(int pId, @NotNull Inventory pPlayer) {
        return new MaskForgeMenu(pId, pPlayer, this, this.dataAccess);
    }

    @Override
    public int @NotNull [] getSlotsForFace(@NotNull Direction direction) {
        return new int[0];
    }

    @Override
    public boolean canPlaceItemThroughFace(int i, @NotNull ItemStack itemStack, @org.jetbrains.annotations.Nullable Direction direction) {
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int i, @NotNull ItemStack itemStack, @NotNull Direction direction) {
        return false;
    }

    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public @NotNull ItemStack getItem(int pIndex) {
        return pIndex >= 0 && pIndex < this.items.size() ? this.items.get(pIndex) : ItemStack.EMPTY;
    }

    public @NotNull ItemStack removeItem(int i, int pCount) {
        return ContainerHelper.removeItem(this.items, i, pCount);
    }

    @Override
    public @NotNull ItemStack removeItemNoUpdate(int i) {
        return ContainerHelper.takeItem(this.items, i);
    }

    public void setItem(int pIndex, @NotNull ItemStack pStack) {
        if (pIndex >= 0 && pIndex < this.items.size()) {
            this.items.set(pIndex, pStack);
        }

    }

    public boolean stillValid(@NotNull Player pPlayer) {
        return Container.stillValidBlockEntity(this, pPlayer);
    }

    @Override
    public void clearContent() {
        this.items.clear();
    }

    @Override
    public void setRecipeUsed(@org.jetbrains.annotations.Nullable Recipe<?> recipe) {

    }

    @org.jetbrains.annotations.Nullable
    @Override
    public Recipe<?> getRecipeUsed() {
        return null;
    }

    @Override
    public void fillStackedContents(StackedContents stackedContents) {

    }

}