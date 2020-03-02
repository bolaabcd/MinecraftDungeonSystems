package com.dungeons.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetStado implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		boolean sucesso=true;
		if (sender instanceof Player) {
			Player player = (Player) sender;
			player.sendMessage(
					player.getTargetBlock(null, 5).getBlockData().toString()
					);
			sucesso=true;
		}else sucesso=false;
		
		return sucesso;
	}

}
