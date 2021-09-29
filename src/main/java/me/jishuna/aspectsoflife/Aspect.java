package me.jishuna.aspectsoflife;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.jishuna.commonlib.utils.FileUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

public class Aspect {

	private final String key;
	private final JavaPlugin owningPlugin;

	private YamlConfiguration config;
	private String name;
	private String displayString;
	private List<String> description = new ArrayList<>();

	public Aspect(String key, JavaPlugin plugin) {
		this.key = key;
		this.owningPlugin = plugin;
		reload();
	}

	private void reload() {
		this.config = FileUtils.loadResource(this.owningPlugin, "aspects/" + this.key + ".yml").orElseGet(() -> null);
		this.displayString = ChatColor.of(this.config.getString("display-color", "#000000"))
				+ StringEscapeUtils.unescapeJava(this.config.getString("display-text", ""));
		this.name = ChatColor.translateAlternateColorCodes('&', this.config.getString("name", this.key));

		this.description.clear();
		for (String line : this.config.getStringList("description")) {
			this.description.addAll(me.jishuna.commonlib.utils.StringUtils
					.splitString(ChatColor.translateAlternateColorCodes('&', line), 30));
		}
	}

	public BaseComponent getAsComponenet() {
		TextComponent component = new TextComponent(this.name + " ");
		ComponentBuilder builder = new ComponentBuilder();

		for (String string : this.description) {
			builder.append(TextComponent.fromLegacyText(string));
			builder.append(new TextComponent("\n"));
		}

		Text text = new Text(Arrays.copyOf(builder.create(), builder.getCursor()));

		component.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, text));
		return component;
	}

	public String getKey() {
		return key;
	}

	public JavaPlugin getOwningPlugin() {
		return owningPlugin;
	}

	public String getDisplayString() {
		return displayString;
	}

}
