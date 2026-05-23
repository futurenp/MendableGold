package com.naterbobber;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MendableGold implements ModInitializer {
	public static final String MOD_ID = "mendablegold";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Thanks for using Mendable Gold!");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MendableGold.MOD_ID, path);
	}
}