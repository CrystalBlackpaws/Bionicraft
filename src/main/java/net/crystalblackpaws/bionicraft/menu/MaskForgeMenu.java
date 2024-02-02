package net.crystalblackpaws.bionicraft.menu;

import net.crystalblackpaws.bionicraft.block.ModBlocks;
import net.crystalblackpaws.bionicraft.blockentity.MaskForgeBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MaskForgeMenu extends AbstractContainerMenu {
    private final MaskForgeBlockEntity blockEntity;
    private final ContainerLevelAccess levelAccess;
    public MaskForgeMenu(int containerId, Inventory playerInv, FriendlyByteBuf additionalData) {
        this(containerId,
                playerInv,
                playerInv.player.level().getBlockEntity(additionalData.readBlockPos()));
    }

    // Server Constructor
    public MaskForgeMenu(int containerId, Inventory playerInv, BlockEntity blockEntity) {
        super(ModMenus.MASK_FORGE_MENU.get(), containerId);
        if(blockEntity instanceof MaskForgeBlockEntity be) {
            this.blockEntity = be;
        } else {
            throw new IllegalStateException("Incorrect block entity class (%s) passed into MaskForgeMenu!"
                    .formatted(blockEntity.getClass().getCanonicalName()));
        }

        this.levelAccess = ContainerLevelAccess.create(Objects.requireNonNull(blockEntity.getLevel()), blockEntity.getBlockPos());

        createPlayerHotbar(playerInv);
        createPlayerInventory(playerInv);
        createBlockEntityInventory(be);
    }

    private void createBlockEntityInventory(MaskForgeBlockEntity be) {
        be.getOptional().ifPresent(inventory -> {
             addSlot(new SlotItemHandler(inventory,
                     40, 40 + 16, 27 + 16
                     ));
            addSlot(new SlotItemHandler(inventory,
                    65, 65 + 16, 9 + 16
            ));
            addSlot(new SlotItemHandler(inventory,
                    65, 65 + 16, 52 + 16
            ));
            addSlot(new SlotItemHandler(inventory,
                    120, 120 + 16, 9 + 16
            ));
            addSlot(new SlotItemHandler(inventory,
                    89, 89 + 24, 44 + 24
            ));
        });
    }

    private void createPlayerInventory(Inventory playerInv) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new Slot(playerInv,
                        9 + column + (row * 9),
                        8 + (column * 18),
                        84 + (row * 18)));
            }
        }
    }

    private void createPlayerHotbar(Inventory playerInv) {
        for (int column = 0; column < 9; column++) {
            addSlot(new Slot(playerInv,
                    column,
                    8 + (column * 18),
                    142));
        }
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player pPlayer, int pIndex) {
        Slot fromSlot = getSlot(pIndex);
        ItemStack fromStack = fromSlot.getItem();

        if(fromStack.getCount() <= 0)
            fromSlot.set(ItemStack.EMPTY);

        if(!fromSlot.hasItem())
            return ItemStack.EMPTY;

        ItemStack copyFromStack = fromStack.copy();

        if(pIndex < 36) {
            if(!moveItemStackTo(fromStack, 36, 63, false))
                return ItemStack.EMPTY;
        } else if (pIndex < 63) {
            if(!moveItemStackTo(fromStack, 0, 36, false))
                return ItemStack.EMPTY;
        } else {
            System.err.println("Invalid slot index: " + pIndex);
            return ItemStack.EMPTY;
        }

        fromSlot.setChanged();
        fromSlot.onTake(pPlayer, fromStack);

        return copyFromStack;
    }

    @Override
    public boolean stillValid(@NotNull Player pPlayer) {
        return stillValid(this.levelAccess, pPlayer, ModBlocks.MASK_FORGE.get());
    }

    public MaskForgeBlockEntity getBlockEntity() {
        return this.blockEntity;
    }
}
