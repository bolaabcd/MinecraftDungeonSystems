package com.dungeons.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.dungeons.utils.Variaveis;

import net.md_5.bungee.api.ChatColor;

public class FastArea implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(args.length!=0)return false;
		Variaveis.ignore_area_commands=!Variaveis.ignore_area_commands;
		sender.sendMessage(ChatColor.YELLOW+"Ignorar se os comandos já estão salvos (estado após edição): "+String.valueOf(Variaveis.ignore_area_commands));
		return true;
	}

}
