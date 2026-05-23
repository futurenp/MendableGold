package com.naterbobber.data;

import com.naterbobber.MendableGold;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class MGDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        var pack = fabricDataGenerator.createPack();
        pack.addProvider(MGItemTagProvider::new);
    }

    @Override
    public @Nullable String getEffectiveModId() {
        return MendableGold.MOD_ID;
    }

    public static class MGItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

        public MGItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
            super(output, registryLookupFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            tagVanillaItems(MGTags.MENDABLE,
                    Items.GOLDEN_HELMET,
                    Items.GOLDEN_CHESTPLATE,
                    Items.GOLDEN_LEGGINGS,
                    Items.GOLDEN_BOOTS,
                    Items.GOLDEN_SWORD,
                    Items.GOLDEN_PICKAXE,
                    Items.GOLDEN_AXE,
                    Items.GOLDEN_HOE,
                    Items.GOLDEN_SHOVEL
            );
        }

        private void tagVanillaItems(TagKey<Item> tag, Item... items) {
            for(var item : items) {
                getOrCreateRawBuilder(tag).addElement(Identifier.parse(item.toString()));
            }
        }
    }
}
