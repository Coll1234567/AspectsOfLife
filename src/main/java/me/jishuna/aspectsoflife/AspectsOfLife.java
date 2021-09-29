package me.jishuna.aspectsoflife;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.jishuna.aspectsoflife.aspects.FlameAspect;
import me.jishuna.aspectsoflife.aspects.UndyingAspect;
import me.jishuna.aspectsoflife.aspects.UnstableAspect;

public class AspectsOfLife extends JavaPlugin implements Listener {

	private AspectRegistry aspectRegistry;
	private PlayerManager playerManager;
	private TickingRunnable runnable;

	@Override
	public void onEnable() {
		this.aspectRegistry = new AspectRegistry();
		this.playerManager = new PlayerManager();

		Bukkit.getPluginManager().registerEvents(new DamageListener(this.playerManager), this);
		Bukkit.getPluginManager().registerEvents(this, this);

		this.runnable = new TickingRunnable(this.playerManager);
		this.runnable.runTaskTimer(this, 5, 5);
		
		getCommand("aspects").setExecutor(new TempCommand(this));
		
		registerDefault();
	}
	
	private void registerDefault() {
		this.aspectRegistry.registerAspect(new UndyingAspect(this));
		this.aspectRegistry.registerAspect(new UnstableAspect(this));
		this.aspectRegistry.registerAspect(new FlameAspect(this));
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		PlayerWrapper wrapper = new PlayerWrapper();
		for (int i = 0; i < 10; i++) {
			wrapper.addAspect(this.aspectRegistry.getRandomAspect());
		}
		
		this.playerManager.registerPlayer(event.getPlayer(), wrapper);
	}

	public AspectRegistry getAspectRegistry() {
		return aspectRegistry;
	}

	public PlayerManager getPlayerManager() {
		return playerManager;
	}

}
