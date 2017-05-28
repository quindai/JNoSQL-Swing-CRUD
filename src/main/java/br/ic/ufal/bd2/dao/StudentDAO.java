package br.ic.ufal.bd2.dao;

import java.util.HashMap;
import java.util.Map;

import br.ic.ufal.bd2.model.Student;

public class StudentDAO {

	public Map<Integer,Student> getAll(){
		Map<Integer, Student> s = new HashMap<Integer, Student>();
		s.put(12343, new Student());
		
		return s;
	}
}
