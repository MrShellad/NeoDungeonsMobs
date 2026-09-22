package net.firefoxsalesman.dungeonsmobs.items;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

public class MountaineerAxeItem extends PickaxeItem {
    public MountaineerAxeItem(Tier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, builder.attributes(PickaxeItem.createAttributes(tier, (float) attackDamageIn, attackSpeedIn)));
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        if (enchantment.is(Enchantments.SWEEPING_EDGE)) {
            return false;
        }
        return super.supportsEnchantment(stack, enchantment)
                || Items.IRON_SWORD.getDefaultInstance().supportsEnchantment(enchantment)
                || Items.IRON_AXE.getDefaultInstance().supportsEnchantment(enchantment);
    }

    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return true;
    }
}
