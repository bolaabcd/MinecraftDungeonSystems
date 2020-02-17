package com.dungeons.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.dungeons.actor.Commandos;
/*Comando: 1 argumento, que é o próprio comando!
 * Bloco: 5 argumentos; as coordenadas xyz,o mundo e a blockdata
 * 
 * 
 * 
 */
public class Readarquivos {
	static public boolean hascoords(int x, int y, int z, String w) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(new File("plugins/Dungeonizator/locais.txt")));
		String linha;
		boolean temx=false,temy=false,temz=false;
		while((linha=br.readLine())!=null) {
			if(linha.equals("X"+x)) {
				temx=true;
			}else if(linha.equals(w)) {
				if(temx&&temy&&temz) {
					br.close();
					return true;
					}
			}else if(linha.equals("Z"+z)) {
				if(temx&&temy)temz=true;
			}else if(linha.equals("Y"+y)) {
				if(temx)temy=true;
			}else {
				temx=false;
				temy=false;
				temz=false;
			}
		}
		br.close();
		return false;
	}
	static public void sendcommands(String arquivo) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(new File(arquivo)));
		String linha;
		String[] args=new String[1];
		String tipo="";
		int linhatual=0;
		while((linha=br.readLine())!=null) {
			linhatual+=1;
			if(linha.equals("B")) {
				args=new String[6];
				linhatual=0;
				tipo="B";
				}
			else if(linha.equals("C")) {
				args=new String[2];
				linhatual=0;
				tipo="C";
			}
			else if(linha.equals("T")) {
				args=new String[6];
				linhatual=0;
				tipo="T";
			}
			
			if(tipo.equals("B")) {
				args[linhatual]=linha;
				if(linhatual==5) {
					tipo="";
					Commandos.direcionar(args);
				}
			}
			else if(tipo.equals("C")) {
				args[linhatual]=linha;
				if(linhatual==1) {
					tipo="";
					Commandos.direcionar(args);
				}
			}
			else if(tipo.equals("T")) {
				args[linhatual]=linha;
				if(linhatual==5) {
					tipo="";
					Commandos.direcionar(args);
				}
			}
			
			
		}
		br.close();
	}
	static public boolean cancelar(String arquivo) throws IOException{
		BufferedReader br=new BufferedReader(new FileReader(new File(arquivo)));
		if(br.readLine().equals("CancelEvent")) {br.close();return true;}
		br.close();
		return false;
	}
	static public boolean hascomando(String arquivo,String comando) throws IOException {
		String conteudo = new String(Files.readAllBytes(Paths.get(arquivo)));
		if(conteudo.contains(comando))return true;
			
		return false;
	}
}
