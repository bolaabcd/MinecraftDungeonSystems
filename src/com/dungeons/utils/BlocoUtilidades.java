package com.dungeons.utils;

import org.bukkit.Location;

public class BlocoUtilidades {
	public static Location[][][] getblocos (Location inicial, Location finali) throws IllegalArgumentException{
		if(!inicial.getWorld().equals(finali.getWorld()))throw new IllegalArgumentException();
		int xi=inicial.getBlockX();
		int yi=inicial.getBlockY();
		int zi=inicial.getBlockZ();
		int xf=finali.getBlockX();
		int yf=finali.getBlockY();
		int zf=finali.getBlockZ();
		int mid;
		if(xi>xf) {
			mid=xi;
			xi=xf;
			xf=mid;
		}
		if(yi>yf) {
			mid=yi;
			yi=yf;
			yf=mid;
		}
		if(zi>zf) {
			mid=zi;
			zi=zf;
			zf=mid;
		}
		Location[][][] out=new Location[1+xf-xi][1+yf-yi][1+zf-zi];
		int xat=-1,yat=-1,zat=-1;
		for(Location[][] x:out) {
			xat++;
			for(Location[] y:x) {
				yat++;
				for(@SuppressWarnings("unused") Location z:y) {
					zat++;
					out[xat][yat][zat]=new Location(inicial.getWorld(),xi+xat,yi+yat,zi+zat);
					if(zat==zf-zi)zat=-1;
				}
				if(yat==yf-yi)yat=-1;
			}
			if(xat==xf-xi)xat=-1;
		}
		
		
		return out;
	}
}
