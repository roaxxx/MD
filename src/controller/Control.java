package controller;

import java.util.ArrayList;
import java.util.Iterator;

import model.Stage;
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
		stgs = graph.operateStages(nodeStgs);
		event.setAttributeStages(stgs);
		
		printName(graph.getWays(stgs));
		for (Stage s: nodeStgs) {
			//printName(s.getfOfS());
			printFs(s.getFs());
			//printMat(s.getAdY());
			printVO(s.getfOfS());
			System.out.println("Caminos para la solución");
			printMat(s.getxSubN());
			
		}
	}
	//Imprimir matriz de etapa
	private void printName(int []z) {
		for (int i=0;i<z.length;i++) {  //filasmnas
			System.out.print(z[i]+",");
			
		}
		System.out.println("");
	}
	private void printVO(int []z) {
		for (int i=0;i<z.length;i++) {  //filasmnas
			System.out.print(z[i]+".");
			
		}
		System.out.println("");
	}
	private void printMat(int [][]z) {
		for (int i=0;i<z.length;i++) {  //filas
			for (int j = 0;j<z[i].length;j++) {//columnas
				System.out.print(z[i][j]+" -");
			}
			System.out.println(" ");
		}
	}
	private void printFs(String [][]z) {
		for (int i=0;i<z.length;i++) {  //filas
			for (int j = 0;j<z[i].length;j++) {//columnas
				System.out.print(z[i][j]+",");
			}
			System.out.println(" ");
		}
	}

}
