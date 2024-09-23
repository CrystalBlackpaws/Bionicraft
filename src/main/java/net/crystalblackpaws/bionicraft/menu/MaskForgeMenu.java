package net.crystalblackpaws.bionicraft.menu;

import net.crystalblackpaws.bionicraft.item.ModItems;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import org.jetbrains.annotations.NotNull;

public class MaskForgeMenu extends AbstractContainerMenu {
    private final Container MaskForge;
    //private final Slot ingredientSlot;

    public MaskForgeMenu(int pContainerId, Inventory pPlayerInventory) {
        this(pContainerId, pPlayerInventory, new SimpleContainer(5), new SimpleContainerData(2));
    }

    public MaskForgeMenu(int pContainerId, Inventory pPlayerInventory, Container MaskForgeContainer, ContainerData MaskForgeData) {
        super(ModMenus.MASK_FORGE_MENU.get(), pContainerId);
        checkContainerSize(MaskForgeContainer, 5);
        checkContainerDataCount(MaskForgeData, 2);
        this.MaskForge = MaskForgeContainer;
        this.addSlot(new MaskForgeMenu.PotionSlot(MaskForgeContainer, 0, 40, 27));
        this.addSlot(new MaskForgeMenu.IngredientsSlot(MaskForgeContainer, 1, 120, 9));
        this.addSlot(new MaskForgeMenu.DiscSlot(MaskForgeContainer, 2, 65, 9));
        this.addSlot(new MaskForgeMenu.ResultSlot(MaskForgeContainer, 3, 93, 48));
        this.addSlot(new MaskForgeMenu.FuelSlot(MaskForgeContainer, 4, 65, 52));
        this.addDataSlots(MaskForgeData);

        int k;
        for(k = 0; k < 3; ++k) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(pPlayerInventory, j + k * 9 + 9, 8 + j * 18, 84 + k * 18));
            }
        }

        for(k = 0; k < 9; ++k) {
            this.addSlot(new Slot(pPlayerInventory, k, 8 + k * 18, 142));
        }

    }

    public @NotNull ItemStack quickMoveStack(@NotNull Player pPlayer, int pIndex) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(pIndex);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if ((pIndex < 0 || pIndex > 2) && pIndex != 3 && pIndex != 4) {
                if (MaskForgeMenu.FuelSlot.mayPlaceItem(itemstack)) {
                    if (this.moveItemStackTo(itemstack1, 4, 5, false) && !this.moveItemStackTo(itemstack1, 3, 4, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (MaskForgeMenu.PotionSlot.mayPlaceItem(itemstack)) {
                    if (!this.moveItemStackTo(itemstack1, 0, 3, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (pIndex >= 5 && pIndex < 32) {
                    if (!this.moveItemStackTo(itemstack1, 32, 41, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (pIndex >= 32 && pIndex < 41) {
                    if (!this.moveItemStackTo(itemstack1, 5, 32, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.moveItemStackTo(itemstack1, 5, 41, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.moveItemStackTo(itemstack1, 5, 41, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(pPlayer, itemstack1);
        }

        return itemstack;
    }

    public boolean stillValid(@NotNull Player pPlayer) {
        return this.MaskForge.stillValid(pPlayer);
    }


    static class IngredientsSlot extends Slot {
        public IngredientsSlot(Container pContainer, int pSlot, int pX, int pY) {
            super(pContainer, pSlot, pX, pY);
        }

        public boolean mayPlace(@NotNull ItemStack stack) {
            return mayPlaceItem(stack);
        }

        public static boolean mayPlaceItem(ItemStack stack) {
            return stack.is(ModItems.PROTODERMIS_INGOT.get());
        }
    }

    static class ResultSlot extends Slot {
        public ResultSlot(Container pContainer, int pSlot, int pX, int pY) {
            super(pContainer, pSlot, pX, pY);
        }

        public int getMaxStackSize() {
            return 64;
        }

    }

    static class DiscSlot extends Slot {
        public DiscSlot(Container pContainer, int pSlot, int pX, int pY) {
            super(pContainer, pSlot, pX, pY);
        }

        public boolean mayPlace(@NotNull ItemStack stack) {
            return mayPlaceItem(stack);
        }

        public int getMaxStackSize() {
            return 64;
        }

        public static boolean mayPlaceItem(ItemStack stack) {
            return stack.is(ModItems.BAMBOO_DISK.get());
        }
    }

    static class PotionSlot extends Slot {
        public PotionSlot(Container pContainer, int pSlot, int pX, int pY) {
            super(pContainer, pSlot, pX, pY);
        }

        public boolean mayPlace(@NotNull ItemStack pStack) {
            return mayPlaceItem(pStack);
        }

        public int getMaxStackSize() {
            return 1;
        }

        public static boolean mayPlaceItem(ItemStack pStack) {
            return BrewingRecipeRegistry.isValidInput(pStack);
        }
    }

    static class FuelSlot extends Slot {
        public FuelSlot(Container pContainer, int pSlot, int pX, int pY) {
            super(pContainer, pSlot, pX, pY);
        }

        public boolean mayPlace(@NotNull ItemStack pStack) {
            return mayPlaceItem(pStack);
        }

        public static boolean mayPlaceItem(ItemStack pItemStack) {
            return pItemStack.is(Items.BLAZE_POWDER);
        }

        public int getMaxStackSize() {
            return 64;
        }
    }
}
