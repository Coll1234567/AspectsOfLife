package me.jishuna.aspectsoflife;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;

public class TempCommand implements CommandExecutor {

	private final AspectsOfLife plugin;

	public TempCommand(AspectsOfLife plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		if (!(sender instanceof Player player))
			return true;

		this.plugin.getPlayerManager().getPlayerOptional(player).ifPresent(wrapper -> {
			ComponentBuilder builder = new ComponentBuilder();
			
			TextComponent text = new TextComponent("Active Aspects: ");
			text.setColor(ChatColor.GRAY);
			text.setBold(true);
			builder.append(text);
			
			Set<Aspect> aspects = new HashSet<>(wrapper.getAspects());

			for (Aspect aspect : aspects) {
				builder.append(aspect.getAsComponenet());
			}

			player.spigot().sendMessage(builder.create());
		});
		return true;
	}
}
