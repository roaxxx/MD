package view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Stage;

public class Result extends JPanel {
	private Font font;
	private JTable[] tStages;
	private JLabel[] nStages;
	public Result() {
		this.setLayout(null);
		this.setBackground(new Color(174, 214, 241));
		font=new Font("Fira Code", Font.PLAIN, 17);
		this.setFont(font);
	}

	public void showResults(ArrayList<Stage> stg) {
		int numStages= stg.size();
		tStages = new JTable [numStages];
		nStages = new JLabel [numStages];
		int y = 20;
		numStages--;
		for(int i = numStages; i>=0;i--) {
			int nColmns= (stg.get(i).getAdY().length)+3;
			String [] columns = transformColumns(stg.get(i),i+1);
			Object [][] rows;
			if(i==0) {
				rows = transformRows(stg.get(i),stg.get(i),true);
			}else {
				rows = transformRows(stg.get(i),stg.get(i-1),false);	
			}
			tStages[i] = new JTable(rows,columns);
			nStages[i] = new JLabel("Step: "+(i+1));
			tStages[i].setFont(font);		
			JScrollPane scroll =new JScrollPane(tStages[i]);
			scroll.setBounds(110,y,600,100);
			nStages[i].setBounds(10,y,90,20);
			nStages[i].setFont(font);
			add(scroll);
			add(nStages[i]);
			y = y + 130;
		}

	}
	private Object[][] transformRows(Stage stage, Stage stage2, boolean b) {
		int numRows = stage.getAdY().length;
		int numCols = stage.getAdY()[0].length+3;
		Object[][] row = new Object [numRows][numCols];
		for (int i =0; i<row.length;i++) {
			for (int j =0; j<row[i].length;j++) {
				if(j>0 && j<(row[i].length-2)) row[i][j] = stage.getFs()[i][j-1];
				if(j==0 && !b) {
					row[i][0] = stage2.getNodeName()[i]+"";
				}else if(b){
					row[i][0] = "1";
				}
				if(j==row[i].length-1) row[i][j] = stage.getfOfS()[i];
				if(j==row[i].length-2) row[i][j] = getBestNodes(stage.getxSubN(),i);			}
		}
		return row;
	}

	private String getBestNodes(int[][] xSubN, int i) {
		String bestNodes = "";
		for(int j =0; j<xSubN[i].length;j++) {
			if(xSubN[i][j]!=0) {
				if(bestNodes.equals("")) {
					bestNodes = bestNodes+xSubN[i][j];
				}else {
					bestNodes = bestNodes+","+xSubN[i][j];
				}
				
			}
		}
		return bestNodes;
	}

	private String[] transformColumns(Stage stg, int nStg) {
		String [] column = new String [(stg.getNodeName().length)+3];
		column[0]="S"+nStg+"/"+"X"+nStg;
		column[stg.getNodeName().length+1]="f"+nStg;
		column[stg.getNodeName().length+2]="x"+nStg;
		for (int i = 0; i<stg.getNodeName().length;i++) {
			column[i+1]= stg.getNodeName()[i]+"";
		}
		return column;
	}

}
