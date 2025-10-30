package com.naterbobber;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MGGoldSet {
    public static Set<Item> goldItemSet = new HashSet<>();
    public static void parseToSet(){
        List<String> goldItems = MendableGold.CONFIG.goldItems();
        for(String item : goldItems) {
            Identifier id = Identifier.of(item);
            goldItemSet.add(Registries.ITEM.get(id));
        }
    }
}
