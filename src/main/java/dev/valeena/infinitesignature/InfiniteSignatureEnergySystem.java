package dev.valeena.infinitesignature;

import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.EntityTickingSystem;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.modules.entitystats.EntityStatMap;
import com.hypixel.hytale.server.core.modules.entitystats.asset.DefaultEntityStatTypes;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class InfiniteSignatureEnergySystem extends EntityTickingSystem<EntityStore> {
	@Nullable
	@Override
	public Query<EntityStore> getQuery() {
		return Player.getComponentType();
	}

	public void tick(final float dt, final int index, @Nonnull final ArchetypeChunk<EntityStore> archetypeChunk, @Nonnull final Store<EntityStore> store, @Nonnull final CommandBuffer<EntityStore> commandBuffer) {
		final Ref<EntityStore> ref = archetypeChunk.getReferenceTo(index);
		if (!ref.isValid()) {
			InfiniteSignature.LOGGER.atSevere().log("Ref is not valid");
			return;
		}

		final Player player = archetypeChunk.getComponent(index, Player.getComponentType());
		if (player == null) {
			InfiniteSignature.LOGGER.atSevere().log("Player is null");
			return;
		}


		var signatureEnergyIndex = DefaultEntityStatTypes.getSignatureEnergy();
		var statMap = store.getComponent(ref, EntityStatMap.getComponentType());

		if (statMap == null) {
			InfiniteSignature.LOGGER.atSevere().log("StatMap is null");
			return;
		}

		statMap.maximizeStatValue(signatureEnergyIndex);
		statMap.update();
	}
}
