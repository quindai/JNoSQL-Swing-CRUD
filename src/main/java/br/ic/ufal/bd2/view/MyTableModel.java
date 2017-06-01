package br.ic.ufal.bd2.view;

import java.util.Map;

import javax.swing.table.AbstractTableModel;

import br.ic.ufal.bd2.model.Student;

public class MyTableModel extends AbstractTableModel {
	private String [] cols = {"Coluna 1", "Coluna 2", "Coluna 3"};
	//private List<Student> data;
	private Object[][] data = {
		    {"Kathy", new Integer(5), new Double(2.6)},
		    {"John", new Integer(3), new Double(2.6)},
		    {"Sue", new Integer(2), new Double(2.6)},
		    {"Jane", new Integer(20), new Double(2.6)},
		    {"Joe", new Integer(10), new Double(2.6)}
		};
	
	public MyTableModel() {
		//data = new ArrayList<Student>();
	}
	
	public MyTableModel(String[] cols) {
		this();
		this.cols = cols;
	}
	
	public MyTableModel(Map<Integer, Student> data){
		initData(data);
	}
	
	public MyTableModel(String[] cols, Map<Integer, Student> data){
		this(cols);
		initData(data);
	}
	
	private void initData(Map<Integer, Student> data){
		/*for(Student o : data.values())
			this.data.add(o);*/
	}
	public String getColumnName(int col){
		return cols[col];
	}
	
	public int getColumnCount() {
		return cols.length;
	}
	
	public int getRowCount() {
		// o numero de linhas da tabela eh o mesmo dos dados
		return data.length;
	}
	
	public Student getRowValues(int row){
		Student s = new Student();
		s.setName((String)data[row][0]);
		s.setId((int)data[row][1]);
		s.setNota((double)data[row][2]);
		return s;
	}

	@Override
	public Class<? extends Object> getColumnClass(int col){
		return getValueAt(0, col).getClass();
	}
	
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

}
