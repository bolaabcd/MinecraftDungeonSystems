package com.dungeons.actor;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.block.BlockRedstoneEvent;

import com.dungeons.detector.starter;
import com.dungeons.utils.AdiaComando;
import com.dungeons.utils.Variaveis;

public class Commandos {
	public static void direcionar(String[] args) {
		if(args[0].equals("B"))bloco(Integer.valueOf(args[1]),Integer.valueOf(args[2]),Integer.valueOf(args[3]),Bukkit.getServer().getWorld(args[4]),Bukkit.getServer().createBlockData(args[5]));
			//blocoas(Integer.valueOf(args[1]),Integer.valueOf(args[2]),Integer.valueOf(args[3]),Bukkit.getServer().getWorld(args[4]),Bukkit.getServer().createBlockData(args[5]));
		else if(args[0].equals("C"))Bukkit.dispatchCommand(Bukkit.getConsoleSender(), args[1]);
		else if(args[0].equals("T"))(new AdiaComando(new BlockRedstoneEvent(Bukkit.getWorld(args[5]).getBlockAt(Integer.valueOf(args[2]),Integer.valueOf(args[3]), Integer.valueOf(args[4])),0,0),Bukkit.getPluginManager())).runTaskLater(starter.getPlugin(), Long.valueOf(args[1]));
		
		
	}
	private static void bloco(int x,int y,int z, World w, BlockData b) {
		w.getBlockAt(x, y, z).setBlockData(b,Variaveis.updateblocks);
	}
	//private static void blocoas(int x,int y,int z, World w, BlockData b) {
	//	w.getBlockAt(x, y, z).setBlockData(b,true);
	//}
	
}
