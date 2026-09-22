package net.firefoxsalesman.dungeonsmobs.items;

import net.neoforged.fml.common.EventBusSubscriber;

import java.util.ArrayList;
import java.util.List;

import net.firefoxsalesman.dungeonslibs.capabilities.builtinenchantments.BuiltInEnchantments;
import net.firefoxsalesman.dungeonslibs.capabilities.builtinenchantments.BuiltInEnchantmentsHelper;
import net.firefoxsalesman.dungeonsmobs.DungeonsMobs;
import net.firefoxsalesman.dungeonsmobs.utils.GeneralHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.Util;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.bus.api.SubscribeEvent;

@EventBusSubscriber(modid = DungeonsMobs.MOD_ID, value = Dist.CLIENT)
public class GildedItemHelper {

	public static final ResourceLocation GILDED_ITEM_RESOURCELOCATION = GeneralHelper.modLoc("gilded_item");

	public static ItemStack getGildedItem(RandomSource random, ItemStack itemStack, RegistryAccess registryAccess) {
		BuiltInEnchantments cap = BuiltInEnchantmentsHelper.getBuiltInEnchantmentsCapability(itemStack);
		Registry<Enchantment> enchantmentRegistry = registryAccess.registryOrThrow(Registries.ENCHANTMENT);
		List<BuiltInEnchantments.Entry> available = new ArrayList<>();
		for (Holder.Reference<Enchantment> ref : enchantmentRegistry.holders().toList()) {
			Enchantment ench = ref.value();
			if (ench.isSupportedItem(itemStack)) {
				for (int lvl = ench.getMinLevel(); lvl <= ench.getMaxLevel(); lvl++) {
					available.add(new BuiltInEnchantments.Entry(ref.key().location(), lvl));
				}
			}
		}
		if (!available.isEmpty()) {
			BuiltInEnchantments.Entry chosen = available.get(random.nextInt(available.size()));
			cap.addBuiltInEnchantment(GILDED_ITEM_RESOURCELOCATION, chosen);
			itemStack.set(DataComponents.CUSTOM_NAME,
					Component.translatable("dungeonsmobs.gilded").append(" ").append(itemStack.getHoverName()));
		}
		return itemStack;
	}

	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		BuiltInEnchantments cap = BuiltInEnchantmentsHelper
				.getBuiltInEnchantmentsCapability(event.getItemStack());
		List<BuiltInEnchantments.Entry> builtInEnchantments = cap
				.getBuiltInEnchantments(GILDED_ITEM_RESOURCELOCATION);
		builtInEnchantments.forEach(entry -> {
			event.getToolTip().add(Component.translatable(Util.makeDescriptionId("enchantment", entry.id()))
					.append(" ").append(Component.translatable("enchantment.level." + entry.level()))
					.withStyle(ChatFormatting.GOLD));
		});
	}

	@SubscribeEvent
	public static void onRenderTooltip(RenderTooltipEvent.Color event) {
		BuiltInEnchantments cap = BuiltInEnchantmentsHelper
				.getBuiltInEnchantmentsCapability(event.getItemStack());
		List<BuiltInEnchantments.Entry> builtInEnchantments = cap
				.getBuiltInEnchantments(GILDED_ITEM_RESOURCELOCATION);
		if (!builtInEnchantments.isEmpty()) {
			event.setBorderStart(0xF0FFD700);
			event.setBorderEnd(0x50F5CC27);
			event.setBackground(0xF0AF7923);
		}
	}
}
