package me.jishuna.aspectsoflife;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.bukkit.entity.Player;

public class PlayerManager {
	
	private final Map<UUID, PlayerWrapper> playerData = new HashMap<>();
	
	public void registerPlayer(Player player, PlayerWrapper wrapper) {
		this.playerData.put(player.getUniqueId(), wrapper);
	}
	
	public PlayerWrapper getPlayerData(Player player) {
		return this.playerData.get(player.getUniqueId());
	}
	
	public Optional<PlayerWrapper> getPlayerOptional(Player player) {
		return Optional.ofNullable(getPlayerData(player));
	}

}
