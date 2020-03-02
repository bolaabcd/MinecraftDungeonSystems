package com.dungeons.detector;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.dungeons.actor.CancelarInteracao;
import com.dungeons.actor.RedstoneListener;
import com.dungeons.comandos.AddComandoBlocarea;
import com.dungeons.comandos.Addtemporizator;
import com.dungeons.comandos.AdicionarComandoBloco;
import com.dungeons.comandos.AdicionarTrueComando;
import com.dungeons.comandos.FastArea;
import com.dungeons.comandos.GetStado;
import com.dungeons.comandos.SetarCancelar;
import com.dungeons.comandos.UpdateBlocks;
import com.dungeons.comandos.addCords;

public class starter extends JavaPlugin{
	private static Plugin instancia;
	@Override
    public void onEnable() {
    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"PLUGIN DE DUNGEONS ATIVADO!");
    
    setcomandos();
    
    setlisteners();
	
    criarquivo();
    
    instancia=this;
	}
    @Override
    public void onDisable() {
    Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"PLUGIN DE DUNGEONS DESATIVADO!");

    }
    private void criarquivo() {
    	File armazem = new File("plugins/Dungeonizator/locais.txt");
    	File comandos=new File("plugins/Dungeonizator/roteiros");
    	if (!armazem.exists()) {
    	try {
    		new File("plugins/Dungeonizator").mkdir();
    		armazem.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	}
    	if(!comandos.exists())comandos.mkdir();

    }
    private void setlisteners() {
    	getServer().getPluginManager().registerEvents(new RedstoneListener(), this);
    	getServer().getPluginManager().registerEvents(new CancelarInteracao(),this);
    }
    private void setcomandos() {
    	this.getCommand("state").setExecutor(new GetStado());
    	this.getCommand("addcord").setExecutor(new addCords());
    	this.getCommand("remcord").setExecutor(new addCords());
    	this.getCommand("addcomb").setExecutor(new AdicionarComandoBloco());
    	this.getCommand("addtruc").setExecutor(new AdicionarTrueComando());
    	this.getCommand("setcanc").setExecutor(new SetarCancelar());
    	this.getCommand("addcombs").setExecutor(new AddComandoBlocarea());
    	this.getCommand("addtemp").setExecutor(new Addtemporizator());
    	this.getCommand("fastarea").setExecutor(new FastArea());
    	this.getCommand("upblocks").setExecutor(new UpdateBlocks());
    }
    public static Plugin getPlugin() {
    	return instancia;
    }
    
}