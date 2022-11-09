package view;


import java.util.ArrayList;

import javax.swing.*;
import controller.*;
import model.Stage;
import model.Way;


public class IOManager extends JFrame implements CustomEventResponse {

	private static final long serialVersionUID = 1L;
	
	public Control control;
	private JPanel table;
	private JPanel result;

	public IOManager() {
		
		control = new Control();
		control.setEvent(this);
				     //x, y
		this.setSize(1200,700);
		this.setTitle("Proyecto Final");
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		table = new Table();
		table.setBounds(5,5,1175,650);
		((Table)table).setEvent(control);
		add(table); 
		
		result = new Result();
		result.setBounds(410,5,875,650);
		//((Result)result).setEvent(control);
	}

	@Override
	public void setAttributeStages(ArrayList<Stage> operateStages) {
		table.setBounds(5,5,400,650);
		add(result); 
		((Result)result).showResults(operateStages);
		
	}

	@Override
	public void setAttributeWays(ArrayList<Way> ways) {
		((Table)table).showWays(ways);
		
	}

}
