package view;

import java.awt.*;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import model.Stage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Table extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	//Atributos propios
	private Stage stg;
	private int nodeName;
	private CustomEvent event;
	private Font font;
	private ArrayList<Stage> graph;
	private int k;
	private int l;
	private int numStages;             //Número de etapas
	private int stage;                 //Etapa
	private int numNodesByS;           //Número de nodos por etapa
	//Modelos
	private SpinnerNumberModel nStagesModel;
	//Elementos de la vista
	private Image jdcIcon;
	private JLabel[] nodeId;
	private JTextField[][] matAd;
	private JSpinner nStages;
	private JButton getNstages;
	private JLabel nStageT;
	private JLabel jpTitle;
	private JLabel iconFrame;
	
	public Table() {
		graph = new ArrayList<Stage>();
		numNodesByS = 1;
		nodeName = 1;
		stage = 0;
		

		this.setLayout(null);
		this.setBackground(new Color(255,255,255));
		font=new Font("Roboto", Font.PLAIN, 17);
		this.setFont(font);
		
		iconFrame = new JLabel("William Fernando Roa Vargas - Jonatan Fernando Franco Cárdenas");
		iconFrame.setBounds(20,720,500,30);
		iconFrame.setFont(new Font("Roboto", Font.PLAIN, 15));
		add(iconFrame);


		buildGrid();

		jpTitle = new JLabel("1. Determinar la cantidad de etapas");
		jpTitle.setBounds(400,40,800,35);
		jpTitle.setFont(new Font("Roboto", Font.PLAIN, 35));
		add(jpTitle);

		//Instrucción para resolver la cantidad de etapas
		nStageT = new JLabel("Elija la cantidad de etapas");
		nStageT.setBounds(120,110,420,30);
		nStageT.setFont(font);
		add(nStageT);

		//Botón para obtener la cantidad de etapas
		getNstages = new JButton("Continuar");
		getNstages.setBounds(120,190,200,35);
		setButtonModel(getNstages);

		//Modelo de spinner
		nStagesModel = new SpinnerNumberModel();
		nStagesModel.setMinimum(1);
		nStagesModel.setMaximum(10);
		nStagesModel.setValue(2);

		//JSpinner para seleccionar la cantidad de etapas
		nStages = new JSpinner(nStagesModel);
		nStages.setBounds(120,150,200,30);
		nStages.setFont(font);
		add(nStages);

	}
	//Asignar modelo a botones
	public void setButtonModel(JButton b) {
		b.setBorderPainted(false);
		b.setBackground(new Color(36,113,163));
		b.setForeground(new Color(255,255,255));
		b.addActionListener(this);
		b.setFont(font);
		this.add(b);
	}
	//Acciones del botón principal
	@Override
	public void actionPerformed(ActionEvent e) {
		AbstractButton s = (AbstractButton) e.getSource();
		if(s.getText().equals("Continuar")) {
			getNumStages();
		}else if(s.getText().equals("Aceptar")){
			getNumNodes();
		}else if(s.getText().equals("Siguiente")) {
			getCosts();
			hideAndRestartGird();
			nStages.setVisible(true);
		}else if(s.getText().equals("Calcular")) {
			sendGraph();
			
		}
	}
	public void sendGraph() {
		nStageT.setText("Los mejores resultados son: ");
		getCosts();
		hideAndRestartGird();
		getNstages.setText("Volver");
		event.calculateWay(graph);
	}

	//Obtener los datos de la matriz de campos de texto
	private void getCosts() {
		if(stage != (numStages+1)) {		
			nStageT.setText("Digite la cantidad de nodos en la etapa: "+stage);
			getNstages.setText("Aceptar");
		}
		System.out.println("l:"+l+"k:"+k);
		int[] nodeName = new int[l];
		int[][] dumpp = new int [k][l];
		for (int i=0;i<l;i++) {  //filas
			for (int j = 0;j<k;j++) {//columnas
				dumpp[j][i]= Integer.parseInt(matAd[i][j].getText());
			}
		}
		for(int i=0;i<l;i++) {			
			nodeName[i] = Integer.parseInt(nodeId[i].getText());
		}
		Stage s =new Stage(k,l);
		s.setAdY(dumpp);
		s.setNodeName(nodeName);
		graph.add(s);
	}
	//Obtener el número de nodos por etapa
	private void getNumNodes() {
		k = numNodesByS;
		l = (int) nStages.getValue();
		stage++;
		showGrid(numNodesByS, (int) nStages.getValue());
		if(stage == (numStages+1)) {
			getNstages.setText("Calcular");
			jpTitle.setText("3. Obtener rutas más eficientes");
			nStageT.setText("Presione cálcular para determinar las mejores rutas");
		}else {
			nStageT.setText("Pulse \"Siguiente\" para la etapa: "+stage);
			getNstages.setText("Siguiente");
		}

		numNodesByS = (int) nStages.getValue();
		nStages.setVisible(false);
	}


	//Obtener el número de estados
	private void getNumStages() {
		numStages = (int) nStages.getValue();
		getNstages.setText("Aceptar");
		jpTitle.setText("2. Cantidad de nodos por etapas");
		nStageT.setText("Digite la cantidad de nodos en la etapa: "+"1");
		stage++;
	}

	//Construir matriz de campos de texto----
	private void buildGrid() {
		int boxes = 10;
		int size = (int) 450/boxes;
		int y = 150;
		int x = 520;
		nodeId = new JLabel[10];
		matAd = new JTextField[10][10];
		for (int i=0;i<10;i++) {  //filas
			nodeId[i]= new JLabel("0");
			nodeId[i].setBounds(x+5,120,size,size-15);
			nodeId[i].setFont(font);
			this.add(nodeId[i]);
			for (int j = 0;j<10;j++) {//columnas
				matAd[i][j]= new JTextField();
				matAd[i][j].setBounds(x,y,size,size-15);
				matAd[i][j].setFont(font);
				this.add(matAd[i][j]);
				y+=size;
			}
			y = 150; 
			x += size+15;
		}
		hideAndRestartGird();
	}
	//Operaciones sobre la la malla de textos
	//Ocultar y reiniciar grilla
	private void hideAndRestartGird() {
		for (int i=0;i<10;i++) {  //filas
			for (int j = 0;j<10;j++) {//columnas
				nodeId[i].setVisible(false);
				matAd[i][j].setVisible(false);
			}
		}
	}
	//Mostrar grilla
	private void showGrid(int k, int l) {
		for (int i=0;i<l;i++) {  //filas
			for (int j = 0;j<k;j++) {//columnas	
				matAd[i][j].setVisible(true);
				matAd[i][j].setText("0");
			}
		}
		for (int i=0;i<l;i++) {  //filas
			nodeName++;
			nodeId[i].setVisible(true);
			nodeId[i].setText(""+nodeName);
		}
	}
	public CustomEvent getEvent() {
		return event;
	}
	public void setEvent(CustomEvent event) {
		this.event = event;
	}
}
