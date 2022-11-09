package controller;

import java.util.ArrayList;
import java.util.Iterator;

import model.Stage;
import model.Way;
import model.Graph;
import view.*;

public class Control implements CustomEvent{
	private CustomEventResponse event;
	private Stage stage;
	private Graph graph;
	
	public Control() {
		//stage = new Stage();
		graph = new Graph();
	}
	public void init() {
		IOManager frame = new IOManager();
		frame.setVisible(true);
	}
	public CustomEventResponse getEvent() {
		return event;
	}
	public void setEvent(CustomEventResponse event) {
		this.event = event;
	}
	@Override
	public void calculateWay(ArrayList<Stage> nodeStgs) {
		ArrayList<Stage> stgs = new ArrayList<Stage>();
		ArrayList<Way> ways = new ArrayList<Way>();
		stgs = graph.operateStages(nodeStgs);
		for (Stage s: nodeStgs) {
			ways.add(new Way(getMat(s.getxSubN())));			
		}
		ways = getWays(ways);
		event.setAttributeStages(stgs);
	}
	private int[][] getMat(int[][] xSubN) {
		int[][] stgNodes = new int[xSubN.length][xSubN[0].length];
		for (int i=0;i<xSubN.length;i++) {  //filas
			for (int j = 0;j<xSubN[i].length;j++) {//columnas
				stgNodes[i][j]=xSubN[i][j]+0;
			}
		}
		return stgNodes;
	}
	private ArrayList<Way> getWays(ArrayList<Way> ways2) {
		ArrayList<Way> ways = new ArrayList<Way>();
		int count = 0;
		while(count < 5) {
			Way way = new Way();
			int[] newWay = graph.getWay(ways2);
			if(newWay[1]==0) {
				return ways;
			}else {
				way.setWayNodes(newWay);
			}
			ways.add(way);
			count++;
		}
		return ways;
	}
}
