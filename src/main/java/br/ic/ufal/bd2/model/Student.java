package br.ic.ufal.bd2.model;

public class Student {
	private String name; 
	private int id;
	private double nota;
	
	public Student() {/* TODO Auto-generated constructor stub*/}
	
	public Student(String name, int id, double nota) {
		super();
		this.name = name;
		this.id = id;
		this.nota = nota;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}
		
}
