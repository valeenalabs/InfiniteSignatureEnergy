package dev.valeena.infinitesignature;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class InfiniteSignature extends JavaPlugin {
	public InfiniteSignature(@NonNullDecl JavaPluginInit init) {
		super(init);
	}

	final static HytaleLogger LOGGER = HytaleLogger.get("InfiniteSignatureEnergy");

	@Override
	protected void setup() {
		super.setup();
		final InfiniteSignatureEnergySystem energySystem = new InfiniteSignatureEnergySystem();
		this.getEntityStoreRegistry().registerSystem(energySystem);
	}

	@Override
	protected void start() {
		LOGGER.atInfo().log("InfiniteSignature by Valeena started! Thanks for using my mod! <3");
	}
}
