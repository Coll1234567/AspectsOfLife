package me.jishuna.aspectsoflife.aspects;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.jishuna.aspectsoflife.Aspect;
import me.jishuna.aspectsoflife.BreakingAspect;
import me.jishuna.aspectsoflife.PlayerWrapper;
import me.jishuna.aspectsoflife.TickingAspect;

public class FlameAspect extends Aspect implements BreakingAspect, TickingAspect {

	public FlameAspect(JavaPlugin plugin) {
		super("flame", plugin);
	}

	@Override
	public double onBreak(EntityDamageEvent event, Player player, PlayerWrapper wrapper, double damage, int index) {
		player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
		player.setFireTicks(20 * 30);

		return damage;
	}

	@Override
	public void onTick(Player player, PlayerWrapper wrapper) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 8, 0, true));
	}

}
