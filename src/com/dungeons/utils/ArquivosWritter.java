package com.dungeons.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.Bukkit;

public class ArquivosWritter {
public static void commbloco(String arquivo, int x,int y,int z,String w) throws IOException {
	BufferedWriter bw=new BufferedWriter(new FileWriter(new File(arquivo),true));
	bw.append("B"+"\n");
	bw.append(Integer.toString(x)+"\n");
	bw.append(Integer.toString(y)+"\n");
	bw.append(Integer.toString(z)+"\n");
	bw.append(w+"\n");
	bw.append(Bukkit.getWorld(w).getBlockAt(x, y, z).getBlockData().getAsString()+"\n");
	bw.close();
	}
}
