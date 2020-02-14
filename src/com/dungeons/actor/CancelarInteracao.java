package com.dungeons.actor;

import java.io.IOException;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.dungeons.utils.Readarquivos;

public class CancelarInteracao implements Listener{
@EventHandler
public void devocancelar(PlayerInteractEvent event) {
	try {
		Material[] validos= {
				Material.LEVER,
				Material.ACACIA_BUTTON,
				Material.BIRCH_BUTTON,
				Material.DARK_OAK_BUTTON,
				Material.JUNGLE_BUTTON,
				Material.OAK_BUTTON,
				Material.SPRUCE_BUTTON,
				Material.STONE_BUTTON,
				Material.DAYLIGHT_DETECTOR
				};
		if(event.getAction()==Action.LEFT_CLICK_BLOCK||!Arrays.asList(validos).contains(event.getClickedBlock().getBlockData().getMaterial()))return;
		if(Readarquivos.cancelar("plugins/Dungeonizator/roteiros/"+"X"+event.getClickedBlock().getLocation().getBlockX()+"Y"+event.getClickedBlock().getLocation().getBlockY()+"Z"+event.getClickedBlock().getLocation().getBlockZ()+"W"+event.getClickedBlock().getLocation().getWorld().getName()+".txt")) {
			event.setCancelled(true);
			Bukkit.getPluginManager().callEvent(new BlockRedstoneEvent(event.getClickedBlock(),0,0));
			}
	} catch (IOException e) {
		return;
	} catch(NullPointerException n) {
		return;
	}

}

}
