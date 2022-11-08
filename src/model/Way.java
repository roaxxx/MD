package model;

import java.util.ArrayList;

public class Way {
	public Way() {

	}
	public ArrayList<Stage> operateStages(ArrayList<Stage> graph){
		int s = (graph.size()-1);

		for(int i=s; i>=0;i--) {
			Stage stage = new Stage();
			if(i>=s) {
				graph.get(i).setfOfS(getBetterLastValue(graph.get(i)));
			}else{
				stage =getBetterValue(graph.get(i),graph.get(i+1));
				graph.get(i).setfOfS(stage.getfOfS());
				graph.get(i).setAdY(stage.getAdY());
			}
			getXn(graph.get(i));
		}
		return graph;
	}
	//Asigna los mejores valores para la última etapa
	public int[] getBetterLastValue(Stage stage) {
		int[]fnSn = new int [stage.getAdY().length];
		for(int i =0; i< stage.getAdY().length;i++) {
			for(int j =0;j< stage.getAdY()[i].length;j++) {
				fnSn[i] = stage.getAdY()[i][j];  
			}
		}
		return fnSn;
	}
	//Asigna los mejores valores para las etapas intermedias
	public Stage getBetterValue(Stage stage, Stage stage2) {
		int[][] nAdy= stage.getAdY();
		int[]fnSn = new int [stage.getAdY().length];
		for(int i =0; i< stage.getAdY().length;i++) {
			for(int j =0;j< stage.getAdY()[i].length;j++) {
				int fofs = stage.getAdY()[i][j]+stage2.getfOfS()[j];  	
				nAdy[i][j]=fofs;
				if(fnSn[i]>fofs || fnSn[i]==0) fnSn[i]=fofs;
			}
		}
		Stage stg = new Stage();
		stg.setAdY(nAdy);
		stg.setfOfS(fnSn);
		return stg;
	}
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
}
