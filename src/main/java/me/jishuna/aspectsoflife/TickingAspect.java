package me.jishuna.aspectsoflife;

import org.bukkit.entity.Player;

public interface TickingAspect {
	
	public void onTick(Player player, PlayerWrapper wrapper);

}
