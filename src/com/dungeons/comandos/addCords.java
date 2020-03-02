package com.dungeons.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.dungeons.utils.LerComandos;
import com.dungeons.utils.Readarquivos;

import net.md_5.bungee.api.ChatColor;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import org.bukkit.Location;

public class addCords implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		boolean res=true;
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length==1&&args[0].equals("here")) {
				try {
					player.sendMessage(editcord(player.getLocation(),label));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if (args.length==3&&LerComandos.tudonumero(args)) {
				try {
					player.sendMessage(editcord(new Location(player.getWorld(),Double.valueOf(Integer.valueOf(args[0])),Double.valueOf(Integer.valueOf(args[1])),Double.valueOf(Integer.valueOf(args[2]))),label));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if (args.length==0) {
				try {
					player.sendMessage(editcord(player.getTargetBlockExact(5).getLocation(),label));
				} catch (Exception e) {
					player.sendMessage(ChatColor.RED+"Erro Inesperado!");
					e.printStackTrace();
					player.sendMessage(e.getMessage());
				}
			}else res=false;
		}else {
			res=true;
			sender.sendMessage(ChatColor.RED+"Comando apenas para players!");
		}
		return res;
	}


	
	private String editcord(Location loc,String labela) throws IOException {
		boolean adicionar=false;
		if(labela.equals("addcord"))adicionar=true;

		if(adicionar)return addcoords(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ(),loc.getWorld().getName());
		else return remcoords(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ(),loc.getWorld().getName());
		

	}

	private String addcoords(int x, int y, int z, String w) throws IOException {
		if(Readarquivos.hascoords(x,y,z,w))return ChatColor.RED+"Coordenada não adicionada, pois já está armazenada.";
		BufferedWriter bw=new BufferedWriter(new FileWriter(new File("plugins/Dungeonizator/locais.txt"),true));
		bw.append("X"+x+"\n");
		bw.append("Y"+y+"\n");
		bw.append("Z"+z+"\n");
		bw.append(w+"\n");
		bw.close();
		File comando=new File("plugins/Dungeonizator/roteiros/"+"X"+x+"Y"+y+"Z"+z+"W"+w+".txt");
		comando.createNewFile();
		return ChatColor.DARK_GREEN+"TUDO CERTO (supostamente)!";
	}
	private String remcoords(int x, int y, int z, String w) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(new File("plugins/Dungeonizator/locais.txt")));
		String linha;
		String saida="";
		boolean temx=false,temy=false,temz=false,pulaesse=false,naotem=true;
		int linhatual=0;
		String[] linhas= new String[4];
		while((linha=br.readLine())!=null) {
			linhatual+=1;
			linhas[((linhatual%4)+3)%4]=linha;
			if(linha.equals("X"+Integer.toString(x))) {
				temx=true;
			}else if(temz&&temy&&temx&&linha.equals(w)) {
					//br.close();
					pulaesse=true;
					naotem=false;
			}else if(temy&&temx) {
				if(linha.equals("Z"+Integer.toString(z)))temz=true;
			}else if(temx) {
				if(linha.equals("Y"+Integer.toString(y)))temy=true;
			}else {
				temx=false;
				temy=false;
				temz=false;
			}
			if(linhatual%4==0&&(!pulaesse)) {
				saida+=linhas[0]+"\n"+linhas[1]+"\n"+linhas[2]+"\n"+linha+"\n";
			} else if(pulaesse) {
				temx=false;
				temy=false;
				temz=false;
				pulaesse=false;
			}
			
		}
		br.close();
		BufferedWriter bw=new BufferedWriter(new FileWriter(new File("plugins/Dungeonizator/locais.txt"),false));
		bw.write(saida);
		bw.close();
		if(naotem)return ChatColor.RED+"Coordenada não removida, pois já não está armazenada.";		
		File comando=new File("plugins/Dungeonizator/roteiros/"+"X"+x+"Y"+y+"Z"+z+"W"+w+".txt");
		comando.delete();
		return ChatColor.DARK_GREEN+"TUDO CERTO (supostamente)!";
	}
}
