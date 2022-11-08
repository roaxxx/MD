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
				stage.getFs()[i][j]=stage.getAdY()[i][j]+"+0 ="+fnSn[i];
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
	public int[] getWays(ArrayList<Stage> graph) {
		int[] way = new int [graph.size()+1];
		way[0] = 1;
		int postj;
		int s = 0;
		for(int i=0 ; i<graph.size() ; i++) {
			Stage stg = graph.get(i);
			postj = getBestNodePos(stg.getxSubN(),s);
			way[i+1]= stg.getxSubN()[s][postj];
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
}
