package com.dungeons.actor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.dungeons.utils.LerComandos;
import com.dungeons.utils.Readarquivos;

import net.md_5.bungee.api.ChatColor;

public class AdicionarComandoBloco  implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			int[] cordsob=new int[1],cordsat=new int[1];
			if(args.length!=8) return false;
			try {
			cordsob=LerComandos.tocords(Arrays.copyOfRange(args, 0, 3), player);
			cordsat=LerComandos.tocords(Arrays.copyOfRange(args, 4, 7), player);
			args[3]=LerComandos.toworld(args[3], player).getName();
			args[7]=LerComandos.toworld(args[7], player).getName();}
			catch(NumberFormatException n) {
				player.sendMessage(ChatColor.RED+"Coordenadas inválidas!");
				return false;
			}

			if(args[3]==null||args[7]==null) {
				player.sendMessage("Mundo Inválido");
				return false;
			}
			try {
				if(Readarquivos.hascoords(cordsob[0],cordsob[1],cordsob[2],args[3])) {
					if(Readarquivos.hascomando("plugins/Dungeonizator/roteiros/"+"X"+cordsob[0]+"Y"+cordsob[1]+"Z"+cordsob[2]+"W"+args[3]+".txt", "B"+"\n"+Integer.toString(cordsat[0])+"\n"+Integer.toString(cordsat[1])+"\n"+Integer.toString(cordsat[2])+"\n"+args[7]+"\n"+Bukkit.getWorld(args[7]).getBlockAt(cordsat[0], cordsat[1], cordsat[2]).getBlockData().getAsString()+"\n")) {
						player.sendMessage(ChatColor.RED+"Comando já salvo!");
						return false;
					}
					BufferedWriter bw=new BufferedWriter(new FileWriter(new File("plugins/Dungeonizator/roteiros/"+"X"+cordsob[0]+"Y"+cordsob[1]+"Z"+cordsob[2]+"W"+args[3]+".txt"),true));
					bw.append("B"+"\n");
					bw.append(Integer.toString(cordsat[0])+"\n");
					bw.append(Integer.toString(cordsat[1])+"\n");
					bw.append(Integer.toString(cordsat[2])+"\n");
					bw.append(args[7]+"\n");
					bw.append(Bukkit.getWorld(args[7]).getBlockAt(cordsat[0], cordsat[1], cordsat[2]).getBlockData().getAsString()+"\n");
					bw.close();
				}else {
					player.sendMessage(ChatColor.DARK_RED+"Coordenadas de entrada não salvas!");
					return false;
				}
			} catch (IOException e) {
				player.sendMessage(ChatColor.RED+"ERRO INESPERADO: Erro ao ler os arquivos de observação de blocos ou o arquivo de comando para o bloco especificado!");
				e.printStackTrace();
				return false;
			}
			return true;
		}else {
			sender.sendMessage(ChatColor.RED+"Comando para players apenas.");
			return false;
		}
	}

}
