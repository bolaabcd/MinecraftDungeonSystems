package com.dungeons.utils;

import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitRunnable;

public class AdiaComando extends BukkitRunnable{
	private final BlockRedstoneEvent ev;
	private final PluginManager mana;
	public AdiaComando(BlockRedstoneEvent evento,PluginManager manag) {
		this.ev=evento;
		this.mana=manag;
	}
	@Override
	public void run() {
		mana.callEvent(ev);
	}

}
