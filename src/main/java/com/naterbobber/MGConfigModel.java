package com.naterbobber;

import io.wispforest.owo.config.annotation.Config;

import java.util.List;
import java.util.Set;

@Config(name = "mendable_gold_config", wrapperName = "MGConfig")
public class MGConfigModel {
    public float repairMultiplier = 2F;
    public boolean ironLevelMinable = true;
    public List<String> goldItems = List.of(
            "minecraft:golden_sword",
            "minecraft:golden_pickaxe",
            "minecraft:golden_axe",
            "minecraft:golden_shovel",
            "minecraft:golden_hoe",
            "minecraft:golden_helmet",
            "minecraft:golden_chestplate",
            "minecraft:golden_leggings",
            "minecraft:golden_boots"
    );

}
