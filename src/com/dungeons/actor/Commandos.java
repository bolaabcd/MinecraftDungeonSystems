package com.dungeons.actor;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;

public class Commandos {
	public static void direcionar(String[] args) {
		if(args[0].equals("B"))bloco(Integer.valueOf(args[1]),Integer.valueOf(args[2]),Integer.valueOf(args[3]),Bukkit.getServer().getWorld(args[4]),Bukkit.getServer().createBlockData(args[5]));
		else if(args[0].equals("C"))Bukkit.dispatchCommand(Bukkit.getConsoleSender(), args[1]);

		
	}
	private static void bloco(int x,int y,int z, World w, BlockData b) {
		w.getBlockAt(x, y, z).setBlockData(b);
	}
	
	
}
