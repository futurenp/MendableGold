//package com.naterbobber.mixin;
//
//
//import net.minecraft.component.ComponentType;
//import net.minecraft.enchantment.Enchantment;
//import net.minecraft.enchantment.Enchantments;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.item.Items;
//import net.minecraft.item.ToolMaterial;
//import net.minecraft.registry.Registries;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//
//@Mixin(Enchantment.class)
//public class EnchantmentMixin {
//
//    @Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = true)
//    private void preventMendingOnGoldItems(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
//        Enchantment enchantment = (Enchantment) (Object) this;
//
//        if (enchantment.exclusiveSet().contains(Enchantments.MENDING) != (Enchantments.MENDING)) {
//            return;
//        }
//
//        Item item = stack.getItem();
//
//        if (item == Items.GOLDEN_HELMET ||
//                item == Items.GOLDEN_CHESTPLATE ||
//                item == Items.GOLDEN_LEGGINGS ||
//                item == Items.GOLDEN_BOOTS ||
//                item == Items.GOLDEN_SWORD ||
//                item == Items.GOLDEN_PICKAXE ||
//                item == Items.GOLDEN_AXE ||
//                item == Items.GOLDEN_SHOVEL ||
//                item == Items.GOLDEN_HOE) {
//
//            cir.setReturnValue(false);
//        }
//        Enchantments
//    }
//}
