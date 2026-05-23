package com.naterbobber.mixin;

import com.naterbobber.data.MGTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mixin(Player.class)
public abstract class PlayerMixin {

    @ModifyVariable(method = "giveExperiencePoints", at = @At("HEAD"), argsOnly = true)
    private int mendableGold$modifyExperience(int experience) {
        Player player = (Player) (Object) this;

        List<ItemStack> eligibleItems = new ArrayList<>();
        EquipmentSlot[] equipmentSlotList = EquipmentSlot.values();

        for(EquipmentSlot equipment : equipmentSlotList) {
            ItemStack itemStack = player.getItemBySlot(equipment);
            if (itemStack == null) continue;

            if (itemStack.is(MGTags.MENDABLE) && itemStack.isDamaged()){
                eligibleItems.add(itemStack);
            }
        }

        if (eligibleItems.isEmpty()) {
            return experience;
        }

        Collections.shuffle(eligibleItems);
        ItemStack itemToRepair = eligibleItems.getFirst();

        float repairMultiplier = 2F;
        int currentDamage = itemToRepair.getDamageValue();
        int repairAmount = (int) (experience * repairMultiplier);
        int repairedDamage = Math.min(repairAmount, currentDamage);

        if(repairAmount > currentDamage) {
            experience = (int) ((repairAmount - currentDamage) / repairMultiplier);
        } else {
            experience = 0;
        }

        itemToRepair.setDamageValue(currentDamage - repairedDamage);

        return experience;
    }
}