package net.crystalblackpaws.bionicraft.item.custom;

import net.crystalblackpaws.bionicraft.entity.custom.BambooDiskThrowable;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class BambooDisk extends Item {
    public BambooDisk(Properties pProperties) {
        super(pProperties);
    }


    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, @NotNull InteractionHand hand) {
        ItemStack itemstack = pPlayer.getItemInHand(hand);
        pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                SoundEvents.EGG_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
        //Basically a cooldown for item use. there's no issue with removing it as well (technically disables rapid fire)
        pPlayer.getCooldowns().addCooldown(this, 10);
        if (!pLevel.isClientSide) {
            BambooDiskThrowable BambooDisk = new BambooDiskThrowable(pLevel, pPlayer);
            BambooDisk.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 1.0F);
            pLevel.addFreshEntity(BambooDisk);
        }

        pPlayer.awardStat(Stats.ITEM_USED.get(this));
        if (!pPlayer.getAbilities().instabuild) {
            itemstack.shrink(1);
        }
        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }


}
