package me.jishuna.aspectsoflife;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

	private final PlayerManager manager;
	
	public DamageListener(PlayerManager manager) {
		this.manager = manager;
	}

	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		if (event.getEntity()instanceof Player player) {
			this.manager.getPlayerOptional(player).ifPresent(wrapper -> wrapper.handleDamageEvent(event, player));
		}
	}

}
