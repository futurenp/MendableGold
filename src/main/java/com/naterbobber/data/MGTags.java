package com.naterbobber.data;

import com.naterbobber.MendableGold;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;

public class MGTags {
    public static final TagKey<Item> MENDABLE = TagKey.of(Registries.ITEM.getKey(), MendableGold.id("mendable"));
}
