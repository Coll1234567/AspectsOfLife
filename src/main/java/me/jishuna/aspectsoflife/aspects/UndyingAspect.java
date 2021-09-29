package me.jishuna.aspectsoflife.aspects;

import org.bukkit.EntityEffect;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.jishuna.aspectsoflife.Aspect;
import me.jishuna.aspectsoflife.BreakingAspect;
import me.jishuna.aspectsoflife.PlayerWrapper;

public class UndyingAspect extends Aspect implements BreakingAspect {

	public UndyingAspect(JavaPlugin plugin) {
		super("undying", plugin);
	}

	@Override
	public double onBreak(EntityDamageEvent event, Player player, PlayerWrapper wrapper, double damage, int index) {
		event.setDamage(player.getHealth() - index * 2);
		player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 1, true));
		player.playEffect(EntityEffect.TOTEM_RESURRECT);

		return Double.MAX_VALUE;
	}

}
