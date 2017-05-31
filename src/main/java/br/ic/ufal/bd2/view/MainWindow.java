package br.ic.ufal.bd2.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainWindow extends JFrame implements ActionListener{
	private JTextArea txtCode;
	private JButton btnAdd, btnDelete;	// adicionar e apagar - update noutra janela
	private JRadioButton rbRedis, rbCouchbase, rbCassandra, rbMongo;
	private JTable tabela;
	private JLabel lbltitle;
	private ButtonGroup grp;
	
	public MainWindow() {
		super("JNoSQL - CRUD on Swing");	
		init();
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//--------------------------------------
		setVisible(true);
	}

	/*public static Image getImage(final String pathAndFileName) {
	    final URL url = Thread.currentThread().getContextClassLoader().getResource(pathAndFileName);
	    return Toolkit.getDefaultToolkit().getImage(url);
	}*/
	
	private void init(){
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		JPanel panel, panelTop, panelButtons, panelRadios, panelTable, panelCode;
		btnAdd = new JButton(new ActionAdd("", new ImageIcon(getClass().getResource("/img/fileAdd.png")), "Adicionar registro", new Integer(KeyEvent.VK_A)));
		btnDelete = new JButton(new ActionDelete("", new ImageIcon(getClass().getResource("/img/fileDel.png")), "Apagar registro", new Integer(KeyEvent.VK_A)));
		rbCassandra = new JRadioButton("Cassandra");
		rbCouchbase = new JRadioButton("CouchBase");
		rbMongo = new JRadioButton("Mongo");
		rbRedis = new JRadioButton("Redis");
		lbltitle = new JLabel("Redis Database");
		grp = new ButtonGroup();	//faz a magia do radio button
		tabela = new JTable( new MyTableModel() );
		tabela.setFillsViewportHeight(true);
		
		tabela.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if (tabela.getSelectedRow() != -1){
					if (!btnDelete.isEnabled()) btnDelete.setEnabled(true);
					if (e.getClickCount() == 2){ //pega clique duplo
						int row = tabela.getSelectedRow();
						int col = tabela.getSelectedColumn();
						new NewRegister(MainWindow.this, new String []{"nada", "nepias"}).setVisible(true);
					}
				} else {
					btnDelete.setEnabled(false);
				}
			}
		});
		
		grp.add(rbRedis);
		grp.add(rbCassandra);
		grp.add(rbCouchbase);
		grp.add(rbMongo);
		
		rbRedis.setSelected(true);
		rbRedis.addActionListener(this);
		rbCassandra.addActionListener(this);
		rbCouchbase.addActionListener(this);
		rbMongo.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panelTop = new JPanel();
		panelRadios = new JPanel();
		panelButtons = new JPanel();
		panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.Y_AXIS));
		
		txtCode = new JTextArea(2,15);
		
		btnDelete.setEnabled(false);
		txtCode.setEditable(false);
		txtCode.setWrapStyleWord(true);
		
		JScrollPane spData = new JScrollPane(tabela,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		sp.add(spData);
		sp.add(new JScrollPane(txtCode,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		
		sp.setOneTouchExpandable(true);
		
		panelButtons.add(btnAdd);
		panelButtons.add(btnDelete);
		panelRadios.add(rbRedis);
		panelRadios.add(rbCassandra);
		panelRadios.add(rbCouchbase);
		panelRadios.add(rbMongo);
		
		panelTop.add(panelButtons);
		panelTop.add(panelRadios);
		panelTop.add(lbltitle);
		
		panel.add(BorderLayout.NORTH, panelTop);
		panel.add(BorderLayout.CENTER, sp);
		panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		getContentPane().add(panel);		//adiciona elementos na janela
	}
	
	private void addRegister(String database){
		
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainWindow();
			}
		});
	} // fim do metodo main
	
	/**
	 * 
	 * @author randy
	 * Inner classe
	 *
	 */	
	abstract class MyActions extends AbstractAction{
		public MyActions(String text, ImageIcon icon, String desc, Integer mnemonic) {
			super(text, icon);
			putValue(SHORT_DESCRIPTION, desc);
			putValue(MNEMONIC_KEY, mnemonic);
		}
	}
	class ActionAdd extends MyActions{
		public ActionAdd(String text, ImageIcon icon, String desc, Integer mnemonic) {
			super(text, icon, desc, mnemonic);
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
		
			new NewRegister(MainWindow.this).setVisible(true);
		}
	}//fim de ActionAdd
	
	class ActionDelete extends MyActions{
		public ActionDelete(String text, ImageIcon icon, String desc, Integer mnemonic) {
			super(text, icon, desc, mnemonic);
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
		
			JOptionPane.showMessageDialog(null, "Código para apagar registro.");
		}
	}//fim de ActionDelete

	/**
	 * Listener de MainWindow
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if ("Redis" == e.getActionCommand()){
			JOptionPane.showMessageDialog(null, "Redis");
		} else if("Cassandra" == e.getActionCommand()){
			JOptionPane.showMessageDialog(null, "Cassandra");
		} else if("CouchBase" == e.getActionCommand()){
			JOptionPane.showMessageDialog(null, "Couchbase");
		}else if ("Mongo" == e.getActionCommand()){
			JOptionPane.showMessageDialog(null, "Mongo");
		}
	}
}
