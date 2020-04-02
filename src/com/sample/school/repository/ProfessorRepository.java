package com.sample.school.repository;

import java.util.Arrays;

import com.sample.school.vo.Professor;

public class ProfessorRepository {

	private Professor[] db = new Professor[100];
	private int position = 0;
	private int sequence = 20000;
	
	public void insertProfessor(Professor professor) {
		db[position] = professor;
		professor.setNo(sequence);
		
		position++;
		sequence++;
	}
	
	public Professor getProfessorByNo(int professorNo) {
		Professor professor = new Professor();
		for(int i = 0; i < position; i++) {
			if(db[i].getNo() == professorNo) {
				professor = db[i];
				break;
			}
		}
		
		return professor;
	}
	
	public Professor[] getAllProfessor() {
		return Arrays.copyOfRange(db, 0, position);
	}
	
	public boolean isExistByEmail(Professor professor) {
		
		boolean isExist = false;
		for(int i = 0; i < position; i ++) {
			if(db[i].getEmail() == professor.getEmail()) {
				isExist = true;
				break;
			}
		}
		return isExist;
	}
	
}
