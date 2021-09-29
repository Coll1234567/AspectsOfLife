package me.jishuna.aspectsoflife;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TickingRunnable extends BukkitRunnable {

	private final PlayerManager manager;
	
	public TickingRunnable(PlayerManager manager) {
		this.manager = manager;
	}

	@Override
	public void run() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			manager.getPlayerOptional(player).ifPresent(wrapper -> wrapper.tick(player));
		}
	}

}
