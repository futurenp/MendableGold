package com.naterbobber.mixin;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Inject(method = "addExperience", at = @At("HEAD"), cancellable = true)
    private void implementCustomMending(int experience, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        List<ItemStack> eligibleItems = new ArrayList<>();
        EquipmentSlot[] equipmentSlotList = EquipmentSlot.values();

        for(EquipmentSlot equipment : equipmentSlotList) {
            ItemStack itemStack = player.getEquippedStack(equipment);
            if (itemStack == null) continue;
            Item item = itemStack.getItem();
            if (itemStack.isDamaged() && (item == Items.GOLDEN_HELMET ||
                    item == Items.GOLDEN_CHESTPLATE ||
                    item == Items.GOLDEN_LEGGINGS ||
                    item == Items.GOLDEN_BOOTS ||
                    item == Items.GOLDEN_SWORD ||
                    item == Items.GOLDEN_PICKAXE ||
                    item == Items.GOLDEN_AXE ||
                    item == Items.GOLDEN_SHOVEL ||
                    item == Items.GOLDEN_HOE)) {
                eligibleItems.add(itemStack);
            }
        }

        if (eligibleItems.isEmpty()) {
            return;
        }

        Collections.shuffle(eligibleItems);
        ItemStack itemToRepair = eligibleItems.getFirst();

        int currentDamage = itemToRepair.getDamage();
        int repairedDamage = Math.min(experience * 2, currentDamage);

        itemToRepair.setDamage(currentDamage - repairedDamage);

        ci.cancel();
    }
}