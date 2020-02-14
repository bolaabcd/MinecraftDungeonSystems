package com.dungeons.actor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.dungeons.utils.LerComandos;
import com.dungeons.utils.Readarquivos;

import net.md_5.bungee.api.ChatColor;

public class AdicionarTrueComando implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			int[] cordsob=new int[1],cordsat=new int[1];
			if(args.length<6)return false;
			else if(args[4].equals("run")) {
				try {
					cordsob=LerComandos.tocords(Arrays.copyOfRange(args, 0, 3), player);
					args[3]=LerComandos.toworld(args[3], player).getName();}
				catch(NumberFormatException n) {
					player.sendMessage(ChatColor.RED+"Coordenadas inválidas!");
					return false;
					}
				try {
					if(!Readarquivos.hascoords(cordsob[0],cordsob[1],cordsob[2],args[3])) {
						player.sendMessage(ChatColor.RED+"O bloco obserbado não está salvo como bloco observável!");
						return false;
					}
					String finali="";
					for(String parte:Arrays.copyOfRange(args, 5, args.length)) {
						finali+=(parte+" ");
					}
					if(Readarquivos.hascomando("plugins/Dungeonizator/roteiros/"+"X"+cordsob[0]+"Y"+cordsob[1]+"Z"+cordsob[2]+"W"+args[3]+".txt", "C"+"\n"+finali+"\n")) {
						player.sendMessage(ChatColor.RED+"Comando já salvo!");
						return false;
					}
					BufferedWriter bw=new BufferedWriter(new FileWriter(new File("plugins/Dungeonizator/roteiros/"+"X"+cordsob[0]+"Y"+cordsob[1]+"Z"+cordsob[2]+"W"+args[3]+".txt"),true));
					bw.append("C"+"\n");
					bw.append(finali+"\n");
					bw.close();
				} catch (IOException e) {
					player.sendMessage(ChatColor.RED+"ERRO INESPERADO: Erro ao ler os arquivos de observação de blocos ou o arquivo de comando para o bloco especificado!");
					e.printStackTrace();
					return false;
				}
				
			}else if(args.length!=8)return false;
			else {
				try {
					cordsob=LerComandos.tocords(Arrays.copyOfRange(args, 0, 3), player);
					cordsat=LerComandos.tocords(Arrays.copyOfRange(args, 4, 7), player);
					args[3]=LerComandos.toworld(args[3], player).getName();
					args[7]=LerComandos.toworld(args[7], player).getName();}
				catch(NumberFormatException n) {
					player.sendMessage(ChatColor.RED+"Coordenadas inválidas!");
					return false;
					}
				BlockState data=Bukkit.getWorld(args[7]).getBlockAt(cordsat[0], cordsat[1], cordsat[2]).getState();
				if(Bukkit.getWorld(args[7]).getBlockAt(cordsat[0], cordsat[1], cordsat[2]).getBlockData().getMaterial().equals(Material.COMMAND_BLOCK)) {
					String comandante=((CommandBlock) data).getCommand();
					
					BufferedWriter bw;
					try {
						if(Readarquivos.hascomando("plugins/Dungeonizator/roteiros/"+"X"+cordsob[0]+"Y"+cordsob[1]+"Z"+cordsob[2]+"W"+args[3]+".txt", "C"+"\n"+comandante+"\n")) {
							player.sendMessage(ChatColor.RED+"Comando já salvo!");
							return false;
						}
						bw = new BufferedWriter(new FileWriter(new File("plugins/Dungeonizator/roteiros/"+"X"+cordsob[0]+"Y"+cordsob[1]+"Z"+cordsob[2]+"W"+args[3]+".txt"),true));
						bw.append("C"+"\n");
						bw.append(comandante+"\n");
						bw.close();
					} catch (IOException e) {
						player.sendMessage(ChatColor.RED+"ERRO INESPERADO: Erro ao ler os arquivos de observação de blocos ou o arquivo de comando para o bloco especificado!");
						e.printStackTrace();
						return false;
					}
				}else {
					player.sendMessage(ChatColor.RED+"O bloco Selecionado não é um bloco de comando comum!");
					return false;
				}
		}
		return true;
		}else {
			sender.sendMessage(ChatColor.RED+"Comando para players apenas.");
			return false;
		}
	}
}
