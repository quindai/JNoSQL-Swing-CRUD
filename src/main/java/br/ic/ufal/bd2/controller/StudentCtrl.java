package br.ic.ufal.bd2.controller;

import java.util.Map;

import br.ic.ufal.bd2.dao.StudentDAO;
import br.ic.ufal.bd2.model.Student;

public class StudentCtrl {
private StudentDAO studentDAO;
	
	public StudentCtrl() {
		studentDAO = new StudentDAO();
	}
	public Map<Integer, Student> getAll() {
		return studentDAO.getAll();
	}
}
