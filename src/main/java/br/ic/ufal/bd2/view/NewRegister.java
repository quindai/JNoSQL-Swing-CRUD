package br.ic.ufal.bd2.view;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.ic.ufal.bd2.model.EnumGenres;
import br.ic.ufal.bd2.model.Student;

public class NewRegister extends JDialog{
	String[] labels = {"Nome: ", "Email: ", "G�nero:"};
	JComboBox genre;
	JTextField name, email;
	JPanel panel;
	public NewRegister(JFrame j) {
		super(j, j.getTitle(), true);
		
		init();
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(j);
	}
	
	public NewRegister(JFrame j, Object[] data){
		//assuming data size fixed
		super(j, j.getTitle(), true);
		init();
		pack();
		name.setText( (String)data[0] );
		email.setText((String)data[1]);
		setLocationRelativeTo(j);
	}
	
	public NewRegister(JFrame j, Student data){
		//assuming data size fixed
		super(j, j.getTitle(), true);
		init();
		pack();
		name.setText( data.getName() );
		email.setText(""+ data.getId());
		setLocationRelativeTo(j);
	}
	
	private void init(){
		//setResizable(false);
		//setSize(new Dimension(450, 300));
		
		panel = new JPanel();
		name = new JTextField(50);
		email = new JTextField();
		genre = new JComboBox( EnumGenres.values() );
		
		//setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		//add(email);
		//add(genre);
		
	     panel.add(name);
	     panel.add(email);
	     panel.add(genre);
	     
	     getContentPane().add(panel);
	}
}
