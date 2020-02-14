package com.dungeons.actor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.dungeons.utils.LerComandos;

import net.md_5.bungee.api.ChatColor;
//CancelEvent
public class SetarCancelar implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			String path;
			Location loc;
			if(args.length==1&&args[0].equals("here")) {
				loc=player.getLocation();
				path="plugins/Dungeonizator/roteiros/"+"X"+loc.getBlockX()+"Y"+player.getLocation().getBlockY()+"Z"+player.getLocation().getBlockZ()+"W"+player.getLocation().getWorld().getName()+".txt";
			}else if (args.length==3&&LerComandos.tudonumero(args)) {
				loc=new Location(player.getWorld(),Double.valueOf(Integer.valueOf(args[0])),Double.valueOf(Integer.valueOf(args[1])),Double.valueOf(Integer.valueOf(args[2])));
				path="plugins/Dungeonizator/roteiros/"+"X"+loc.getBlockX()+"Y"+loc.getBlockY()+"Z"+loc.getBlockZ()+"W"+loc.getWorld().getName()+".txt";
			}else if (args.length==0) {
				loc=player.getTargetBlockExact(5).getLocation();
				path="plugins/Dungeonizator/roteiros/"+"X"+loc.getBlockX()+"Y"+loc.getBlockY()+"Z"+loc.getBlockZ()+"W"+loc.getWorld().getName()+".txt";
			}else return false;
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
				if(Arrays.asList(validos).contains(loc.getBlock().getBlockData().getMaterial())) {
					player.sendMessage(ChatColor.RED+"O bloco especificado não é válido para os propósitos desse comando");
					return false;
				}
				String tudo=new String(Files.readAllBytes(Paths.get(path)));
				BufferedWriter bw =new BufferedWriter(new FileWriter(new File(path),false));
				bw.write(("CancelEvent"+"\n"+tudo));
				bw.close();
			} catch (IOException e) {
				player.sendMessage(ChatColor.RED+"Erro ao ler o arquivo de comando para o bloco especificado! Talvez o bloco não esteja registrado...");
				e.printStackTrace();
				return false;
			}

			
		}else {
			sender.sendMessage(ChatColor.RED+"Comando para players apenas.");
			return false;
		}
		return true;
	}

}
