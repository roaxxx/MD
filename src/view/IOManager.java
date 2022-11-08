package view;


import java.util.ArrayList;

import javax.swing.*;
import controller.*;
import model.Stage;


public class IOManager extends JFrame implements CustomEventResponse {

	private static final long serialVersionUID = 1L;
	
	public Control control;
	private JPanel table;
	private JPanel result;

	public IOManager() {
		
		control = new Control();
		
		control.setEvent(this);
				     //x, y
		this.setSize(1300,800);
		this.setTitle("Proyecto Final");
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		table = new Table();
		table.setBounds(5,5,1275,750);
		((Table)table).setEvent(control);
		add(table); 
		
		result = new Result();
		result.setBounds(410,5,975,750);
		//((Result)result).setEvent(control);
	}

	@Override
	public void setAttributeStages(ArrayList<Stage> operateStages) {
		table.setBounds(5,5,400,750);
		add(result); 
		((Result)result).showResults(operateStages);
		
	}

}
