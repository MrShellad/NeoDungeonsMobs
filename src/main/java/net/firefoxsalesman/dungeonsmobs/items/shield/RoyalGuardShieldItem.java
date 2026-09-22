package net.firefoxsalesman.dungeonsmobs.items.shield;

import net.firefoxsalesman.dungeonsmobs.items.shield.bewlr.RoyalGuardShieldBEWLR;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.block.DispenserBlock;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.common.util.Lazy;

import java.util.function.Consumer;

public class RoyalGuardShieldItem extends ShieldItem {
    public RoyalGuardShieldItem(Properties builder) {
        super(builder);
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return false;
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return ItemAbilities.DEFAULT_SHIELD_ACTIONS.contains(itemAbility);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            static final Lazy<BlockEntityWithoutLevelRenderer> renderer = Lazy.of(() -> new RoyalGuardShieldBEWLR(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels()));

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return renderer.get();
            }
        });
    }
}
