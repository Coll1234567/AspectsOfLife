package me.jishuna.aspectsoflife.aspects;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.jishuna.aspectsoflife.Aspect;
import me.jishuna.aspectsoflife.BreakingAspect;
import me.jishuna.aspectsoflife.PlayerWrapper;

public class UnstableAspect extends Aspect implements BreakingAspect {

	public UnstableAspect(JavaPlugin plugin) {
		super("unstable", plugin);
	}

	@Override
	public double onBreak(EntityDamageEvent event, Player player, PlayerWrapper wrapper, double damage,
			int index) {
		player.setNoDamageTicks(5);
		player.getWorld().createExplosion(player.getLocation(), 3f);

		return damage;
	}

}
