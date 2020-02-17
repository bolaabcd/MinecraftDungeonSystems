package com.dungeons.actor;

import java.io.IOException;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.dungeons.utils.ArquivosWritter;
import com.dungeons.utils.BlocoUtilidades;
import com.dungeons.utils.LerComandos;
import com.dungeons.utils.Readarquivos;

import net.md_5.bungee.api.ChatColor;

public class AddComandoBlocarea implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length!=12) return false;
			int[] cordsob=new int[1],cordsat1=new int[1],cordsat2=new int[1];
			try {
			cordsob=LerComandos.tocords(Arrays.copyOfRange(args, 0, 3), player);
			cordsat1=LerComandos.tocords(Arrays.copyOfRange(args, 4, 7), player);
			cordsat2=LerComandos.tocords(Arrays.copyOfRange(args, 8, 11), player);
			args[3]=LerComandos.toworld(args[3], player).getName();
			args[7]=LerComandos.toworld(args[7], player).getName();
			args[11]=LerComandos.toworld(args[11], player).getName();
			if(!Readarquivos.hascoords(cordsob[0],cordsob[1],cordsob[2],args[3])) {
				player.sendMessage(ChatColor.RED+"Coordenadas de entrada não salvas!");
				return true;
			}
			}catch(NumberFormatException n) {
				player.sendMessage(ChatColor.RED+"Coordenadas inválidas!");
				return true;
			} catch (IOException e) {
				player.sendMessage(ChatColor.DARK_RED+"ERRO INESPERADO: Erro ao ler arquivo!");
				e.printStackTrace();
				return true;
			}

			Location[][][] teste;
			try {
			teste=BlocoUtilidades.getblocos(new Location(Bukkit.getWorld(args[7]),cordsat1[0],cordsat1[1],cordsat1[2]), new Location(Bukkit.getWorld(args[11]),cordsat2[0],cordsat2[1],cordsat2[2]));
			}catch(IllegalArgumentException e) {
				player.sendMessage(ChatColor.RED+"Não dá pra selecionar uma área de blocos em mundos diferentes!");
				return true;
			}
			try {
			for(Location[][] l1:teste)
				for(Location[] l2:l1)
					for(Location l:l2) {
						if(Readarquivos.hascomando("plugins/Dungeonizator/roteiros/"+"X"+cordsob[0]+"Y"+cordsob[1]+"Z"+cordsob[2]+"W"+args[3]+".txt", "B"+"\n"+Integer.toString(l.getBlockX())+"\n"+Integer.toString(l.getBlockY())+"\n"+Integer.toString(l.getBlockZ())+"\n"+args[7]+"\n"+l.getWorld().getBlockAt(l.getBlockX(), l.getBlockY(), l.getBlockZ()).getBlockData().getAsString()+"\n")) {
							player.sendMessage(ChatColor.YELLOW+"Comando já salvo! Ignorando...");
							continue;
						}
						ArquivosWritter.commbloco("plugins/Dungeonizator/roteiros/"+"X"+cordsob[0]+"Y"+cordsob[1]+"Z"+cordsob[2]+"W"+args[3]+".txt", l.getBlockX(), l.getBlockY(), l.getBlockZ(), l.getWorld().getName());
					
					}
			} catch (IOException e) {
				player.sendMessage(ChatColor.DARK_RED+"ERRO INESPERADO: Erro ao ler arquivo!");
				e.printStackTrace();
				return false;
			}

			return true;
		}else {
		sender.sendMessage(ChatColor.RED+"Comando para players apenas.");
		return true;
		}

	}
}