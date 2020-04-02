package com.sample.school.repository;

import java.util.Arrays;

import com.sample.school.vo.Subject;

public class SubjectRepository {

	private Subject[] db = new Subject[100];
	private int position = 0;
	private int sequence = 30000;
	
	public void insertSubject(Subject subject)  {
		db[position] = subject;
		subject.setNo(sequence);
		
		position++;
		sequence++;
	}
	
	public Subject getSubjectByNo(int subjectNo) {
		Subject subject = new Subject();
		for(int i = 0; i < position; i++) {
			if(db[i].getNo() == subjectNo) {
				subject = db[i];
				break;
			}
		}
		return subject;
	}
	
	public Subject[] getAllSubject() {
		return Arrays.copyOfRange(db, 0, position);
	}
	
	public boolean isExistSubjectByTitle(Subject subject) {
		boolean isExist = false;
		for (int i = 0; i < position; i++) {
			if (db[i].getTitle() == subject.getTitle()) {
				isExist = true;
				break;
			}
		}
		return isExist;
	}
	
	public Subject[] getAllSubjectByProfessorNo(int professorNo) {
	
		Subject[] subject = new Subject[position];
		int count = 0;
		for(int i = 0; i < position; i++) {
			if(professorNo == db[i].getNo()) {
				subject[count] = db[i];
				count++;
			}
		}
		return Arrays.copyOfRange(subject, 0, count);
	}
	
}
