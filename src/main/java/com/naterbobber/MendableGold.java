package com.naterbobber;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.naterbobber.MGConfig;

public class MendableGold implements ModInitializer {
	public static final String MOD_ID = "mendablegold";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final MGConfig CONFIG = MGConfig.createAndLoad();

	@Override
	public void onInitialize() {
		LOGGER.info("Thanks for using Mendable Gold!");
		MGGoldSet.parseToSet();
	}
}