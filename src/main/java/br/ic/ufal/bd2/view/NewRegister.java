package br.ic.ufal.bd2.view;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

import br.ic.ufal.bd2.model.EnumGenres;

public class NewRegister extends JDialog{

	JComboBox genre;
	JTextField name, email;
	public NewRegister(JFrame j) {
		super(j, j.getTitle(), true);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		init();
		setLocationRelativeTo(j);
	}
	
	public NewRegister(JFrame j, String[] data){
		//assuming data size fixed
		super(j, j.getTitle(), true);
		init();
		
		name.setText( data[0] );
		email.setText(data[1]);
		setLocationRelativeTo(j);
	}
	
	private void init(){
		name = new JTextField();
		email = new JTextField();
		genre = new JComboBox( EnumGenres.values() );
		
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		add(name);
		add(email);
		add(genre);
	}
}
