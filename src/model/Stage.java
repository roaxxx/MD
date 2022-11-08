package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Stage {
	private int [] NodeName;
	private String[][] fs;
	private int [][] adY;
	private int [] fOfS;
	private int [][] xSubN;
	
	public Stage(int k,int l) {
		fs = new String[k][l];
		adY = new int [k][l];
		xSubN = new int [k][l];
		fOfS = new int[l];
		NodeName = new int[l];
		initMatrixs();
	}
	public Stage() {
		
	}

	public int[] getNodeName() {
		return NodeName;
	}

	public void setNodeName(int[] nodeName) {
		NodeName = nodeName;
	}

	public int[][] getAdY() {
		return adY;
	}

	public void setAdY(int[][] adY) {
		this.adY = adY;
	}

	public int[] getfOfS() {
		return fOfS;
	}

	public void setfOfS(int[] fOfS) {
		this.fOfS = fOfS;
	}

	public int[][] getxSubN() {
		return xSubN;
	}

	public void setxSubN(int[][] xSubN) {
		this.xSubN = xSubN;
	}

	//Incializar la matriz de posibles caminos
	public void initMatrixs() {
		for (int i=0;i<adY.length;i++) {  //filas
			for (int j = 0;j<adY[i].length;j++) {//columnas
				NodeName[j] = 0;
				xSubN[i][j] = 0;
				adY [i][j] = 0;
				fs[i][j] = "0";
			}
		}
	}
	public int getLastNumID(Stage s) {
		return s.getNodeName()[s.getNodeName().length];
	}
}
