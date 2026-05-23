package com.naterbobber.data;

import com.naterbobber.MendableGold;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class MGTags {
    public static final TagKey<Item> MENDABLE = TagKey.create(Registries.ITEM, MendableGold.id("mendable"));
}
