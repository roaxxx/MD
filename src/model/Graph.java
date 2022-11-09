package model;

import java.util.ArrayList;

public class Graph {

	public Graph() {

	}
	//Evaluar cada etapa
	public ArrayList<Stage> operateStages(ArrayList<Stage> graph){
		int s = (graph.size()-1);
		for(int i=s; i>=0;i--) {
			Stage stage = new Stage();
			if(i>=s) {
				getBetterLastValue(graph.get(i));
			}else{
				getBetterValue(graph.get(i),graph.get(i+1));
			}
			getXn(graph.get(i));
		}
		return graph;
	}
	//Asigna los mejores valores para la última etapa
	public void getBetterLastValue(Stage stage) {
		int[]fnSn = new int [stage.getAdY().length];
		for(int i =0; i< stage.getAdY().length;i++) {
			for(int j =0;j< stage.getAdY()[i].length;j++) {
				fnSn[i] = stage.getAdY()[i][j];  
				stage.getFs()[i][j]=stage.getAdY()[i][j]+"+0="+fnSn[i];
			}
		}
		stage.setfOfS(fnSn);
	}
	//Asigna los mejores valores para las etapas intermedias
	public void getBetterValue(Stage stage, Stage stage2) {
		String[][] fss = stage.getFs();
		int[][] nAdy= stage.getAdY();
		int[]fnSn = new int [stage.getAdY().length];
		for(int i =0; i< stage.getAdY().length;i++) {
			for(int j =0;j< stage.getAdY()[i].length;j++) {
				int fofs = stage.getAdY()[i][j]+stage2.getfOfS()[j];  	
				fss[i][j]= stage.getAdY()[i][j]+"+"+stage2.getfOfS()[j]+"="+fofs;
				nAdy[i][j]=fofs;
				if(fnSn[i]>fofs || fnSn[i]==0) {
					fnSn[i]=fofs;
				}
			}
		}
		stage.setAdY(nAdy);
		stage.setfOfS(fnSn);
	}
	//Asigna los mejores valores a las rutas
	public void getXn(Stage s){
		int [][] way = s.getxSubN();
		for(int i =0; i< s.getAdY().length;i++) {
			for(int j =0; j< s.getAdY()[i].length;j++) {
				if(s.getAdY()[i][j]==s.getfOfS()[i]) {
					way[i][j]=s.getNodeName()[j];
				}
			}
		}
		s.setxSubN(way);
	}
	public int[] getWay(ArrayList<Way> ways2) {
		int[] way = new int [ways2.size()+1];
		way[0] = 1;
		int postj;
		int s = 0;
		for(int i=0 ; i<ways2.size() ; i++) {
			Way w = ways2.get(i);
			postj = getBestNodePos(w.getfOsf(),s);
			way[i+1]= w.getfOsf()[s][postj];
			if(i==1) {
				w.getfOsf()[s][postj]=0;
			}else if(i==0) {
				if(deleteFirts(ways2.get(1).getfOsf(), postj)<2) w.getfOsf()[s][postj]=0;
			}
			s = postj;
		}
		return way;
	}
	private int getBestNodePos(int[][] adY, int s) {
		for(int i =0; i<adY[s].length;i++) {
			if(adY[s][i]!=0) return i;
		}
		return 0;
	}
	private int deleteFirts(int[][] adY, int s) {
		int count = 0;
		for(int i =0; i<adY[s].length;i++) {
			if(adY[s][i]!=0) count++;
		}
		return count;
	}
}
