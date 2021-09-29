package me.jishuna.aspectsoflife;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

public interface BreakingAspect {
	
	public double onBreak(EntityDamageEvent event, Player player, PlayerWrapper wrapper, double damage, int index);

}
