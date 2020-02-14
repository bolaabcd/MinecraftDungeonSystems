package com.dungeons.actor;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

import com.dungeons.utils.Readarquivos;

import net.md_5.bungee.api.ChatColor;

public class RedstoneListener implements Listener{
    @EventHandler
    public void mudaredstone(BlockRedstoneEvent event)
    {
    	if(event.getOldCurrent()!=0)return;
    	boolean tem=false;
    	try {
			tem=Readarquivos.hascoords(event.getBlock().getLocation().getBlockX(),event.getBlock().getLocation().getBlockY(),event.getBlock().getLocation().getBlockZ(),event.getBlock().getLocation().getWorld().getName());
			} catch (IOException e) {
			return;
		}
    	if(tem) {//CancelEvent
    		try {
				Readarquivos.sendcommands("plugins/Dungeonizator/roteiros/"+"X"+event.getBlock().getLocation().getBlockX()+"Y"+event.getBlock().getLocation().getBlockY()+"Z"+event.getBlock().getLocation().getBlockZ()+"W"+event.getBlock().getLocation().getWorld().getName()+".txt");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Bukkit.broadcastMessage(ChatColor.RED+"Erro Inesperado!");
				e.printStackTrace();
			}
    		
    	}
    }
   
}//    			if(Readarquivos.cancelar("plugins/Dungeonizator/roteiros/"+"X"+event.getBlock().getLocation().getBlockX()+"Y"+event.getBlock().getLocation().getBlockY()+"Z"+event.getBlock().getLocation().getBlockZ()+"W"+event.getBlock().getLocation().getWorld().getName()+".txt"));

