package me.jishuna.aspectsoflife;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class PlayerWrapper {

	private LinkedList<Aspect> aspects = new LinkedList<>();

	public boolean addAspect(Aspect aspect) {
		if (this.aspects.size() >= 10)
			return false;
		this.aspects.add(aspect);
		return true;
	}

	public void handleDamageEvent(EntityDamageEvent event, Player player) {
		double health = player.getHealth() - event.getFinalDamage();
		while ((health / 2) < this.aspects.size()) {
			int size = this.aspects.size();
			if (size <= 0)
				break;
			
			Aspect aspect = this.aspects.pollLast();

			if (aspect instanceof BreakingAspect damageAspect) {
				health = damageAspect.onBreak(event, player, this, health, size);
			}
		}
	}

	public void tick(Player player) {
		StringBuilder display = new StringBuilder();
		for (Aspect aspect : this.aspects) {
			if (aspect instanceof TickingAspect tickingAspect) {
				((TickingAspect) aspect).onTick(player, this);
			}
			display.append(aspect.getDisplayString());
		}
		player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(display.toString()));
	}
	
	public List<Aspect> getAspects() {
		return this.aspects;
	}

}
