package com.dungeons.utils;

import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AssyncWritter extends BukkitRunnable{
	private final Location[][][] area;
	private final Player player;
	private final String path;
	
	public AssyncWritter(String path,Player player,Location[][][] area) {
		this.player=player;
		this.path=path;
		this.area=area;
	}
	@Override
	public void run() {
		try {
			int x=0;
			if(Variaveis.ignore_area_commands) {
				for(Location[][] l1:area)
					for(Location[] l2:l1)
						for(Location l:l2) {
							ArquivosWritter.commbloco(path, l.getBlockX(), l.getBlockY(), l.getBlockZ(), l.getWorld().getName());
							x++;
						}
				player.sendMessage(ChatColor.GREEN+"Adicionados "+String.valueOf(x)+" blocos!");
				
			}else {
				for(Location[][] l1:area)
					for(Location[] l2:l1)
						for(Location l:l2) {
							if(Readarquivos.hascomando(path, "B"+"\n"+Integer.toString(l.getBlockX())+"\n"+Integer.toString(l.getBlockY())+"\n"+Integer.toString(l.getBlockZ())+"\n"+l.getWorld().getName()+"\n"+l.getWorld().getBlockAt(l.getBlockX(), l.getBlockY(), l.getBlockZ()).getBlockData().getAsString()+"\n")) {
								//player.sendMessage(ChatColor.YELLOW+"Comando já salvo! Ignorando...");
								continue;
							}
							ArquivosWritter.commbloco(path, l.getBlockX(), l.getBlockY(), l.getBlockZ(), l.getWorld().getName());
							x++;
						}
			player.sendMessage(ChatColor.GREEN+"Adicionados "+String.valueOf(x)+" blocos!");
			}
		} catch (IOException e) {
			player.sendMessage(ChatColor.DARK_RED+"ERRO INESPERADO: Erro ao ler arquivo!");
			e.printStackTrace();
			player.sendMessage(e.getMessage());
		}
	}

}
