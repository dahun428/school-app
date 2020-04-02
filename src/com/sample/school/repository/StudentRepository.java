package com.sample.school.repository;

import java.util.Arrays;

import com.sample.school.vo.Student;
import com.sample.school.vo.Subject;

public class StudentRepository {

	private Student[] db = new Student[100];
	private int position = 0;
	private int sequence = 60000;
	
	public StudentRepository() {
		insertStudent(new Student("김유신", "kim@gmail.com", "컴퓨터공학과", 2));
		insertStudent(new Student("강감찬", "kang@daum.net", "컴퓨터공학과", 3));
		insertStudent(new Student("이순신", "lee@gmail.com", "컴퓨터공학과", 1));
		insertStudent(new Student("홍길동", "hong@daum.net", "전자공학과", 2));
		insertStudent(new Student("류관순", "ryu@naver.com", "전자공학과", 4));
		insertStudent(new Student("안중근", "ahn@naver.com", "전자공학과", 3));
	}
	
	public void insertStudent(Student student) {
		student.setNo(sequence);
		db[position] = student;
		
		sequence++;
		position++;
	}
	
	public Student getStudentByNo(int studentNo) {
		Student student = new Student();
		for(int i = 0; i < position; i++) {
			if(db[i].getNo() == studentNo) {
				student = db[i];
				break;
			}
		}
		return student;
	}
	
	public Student[] getAllStudents() {
		return Arrays.copyOfRange(db, 0, position);
	}
	public Student[] getAllStudentsByRegistrationNo(int registrationNo) {

		Student[] student = new Student[position];
		int count = 0;
		for(int i = 0; i < position; i++) {
			if(registrationNo == db[i].getNo()) {
				student[count] = db[i];
				count++;
			}
		}
		return Arrays.copyOfRange(student, 0, count);
	}
	
	public boolean isExistStudentEmail(Student student) {
		boolean isExist = false;
		for(int i = 0; i < position; i++) {
			if(db[i].getEmail() == student.getEmail()) {
				isExist = true;
				break;
			}
		}
		return isExist;
	}
	
}