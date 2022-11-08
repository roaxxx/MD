package view;


import javax.swing.*;
import controller.*;


public class IOManager extends JFrame implements CustomEventResponse {

	private static final long serialVersionUID = 1L;
	
	public Control control;
	private JPanel table;

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
		table.setBounds(5,5,1400,800);
		((Table)table).setEvent(control);
		add(table); 
		
	}

}
