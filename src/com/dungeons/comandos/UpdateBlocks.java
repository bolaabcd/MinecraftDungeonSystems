package com.dungeons.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.dungeons.utils.Variaveis;

import net.md_5.bungee.api.ChatColor;

public class UpdateBlocks implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(args.length!=0)return false;
		Variaveis.updateblocks=!Variaveis.updateblocks;
		sender.sendMessage(ChatColor.YELLOW+"Ativar atualizações de blocos (estado após edição): "+String.valueOf(Variaveis.updateblocks));
		return true;
	}

}
