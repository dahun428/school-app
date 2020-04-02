package com.sample.school.repository;

import java.util.Arrays;

import com.sample.school.vo.Course;
import com.sample.school.vo.Registration;

public class RegistrationRepository {

	private Registration[] db = new Registration[100];
	private int position = 0;
	private int sequence = 50000;
	
	public RegistrationRepository() {
		insertRegistration(new Registration(60003, 40001, false, 0));
		insertRegistration(new Registration(60003, 40002, false, 0));
		insertRegistration(new Registration(60003, 40003, true, 0));
		
		insertRegistration(new Registration(60004, 40001, false, 0));
		insertRegistration(new Registration(60004, 40003, false, 0));
		insertRegistration(new Registration(60004, 40005, false, 0));

		insertRegistration(new Registration(60001, 40008, false, 0));
		insertRegistration(new Registration(60001, 40009, false, 0));
		insertRegistration(new Registration(60001, 40010, true, 0));
		insertRegistration(new Registration(60001, 40011, true, 0));
		
	}
	
	public void insertRegistration(Registration registration) {
		registration.setNo(sequence);
		registration.setCancel(false);
		registration.setScore(0);
		db[position] = registration;
		
		position++;
		sequence++;
	}
 
	public Registration getRegistrationByNo(int studentNo, int courseNo) {
		Registration registration = new Registration();
		for(int i = 0; i < position; i++) {
			if(db[i].getStudentNo() == studentNo && db[i].getCourseNo() == courseNo) {
				registration = db[i];
				break;
			}
		}
		return registration;
	}
	
	public Registration getRegistrationByNo(int registrationNo) {
		Registration registration = new Registration();
		for(int i = 0; i < position; i++) {
			if(db[i].getNo() == registrationNo) {
				registration = db[i];
				break;
			}
		}
		return registration;
	}
	
	public Registration getRegistrationByCourseNo (int courseNo) {
		Registration registration = new Registration();
		for(int i = 0; i < position; i++) {
			if(db[i].getCourseNo() == courseNo) {
				registration = db[i];
				break;
			}
		}
		return registration;
	}
	
	public Registration[] getAllRegistrations() {
		return Arrays.copyOfRange(db, 0, position);
	}
	public Registration[] getAllRegistrationsByCourseNo(int courseNo) {
		Registration[] registrations = new Registration[position];
		int count = 0;
		for(int i = 0; i < position; i++) {
			if(courseNo == db[i].getCourseNo()) {
				registrations[count] = db[i];
				count++;
			}
		}
		return Arrays.copyOfRange(registrations, 0, count);
	}
	public Registration getRegistrationByStudentNo(int studentNo) {
		Registration registration = new Registration();
		for(int i = 0; i < position; i++) {
			if(studentNo == db[i].getStudentNo()) {
				registration = db[i];
				break;
			}
		}
		return registration;
	}
	public Registration[] getAllRegistrationByStudentNo(int studentNo) {
		Registration[] registrations = new Registration[position];
		int count = 0;
		for(int i = 0; i < position; i++) {
			if(studentNo == db[i].getStudentNo()) {
				registrations[count] = db[i];
				count++;
			}
		}
		return Arrays.copyOfRange(registrations, 0, count);
	}
	
	
}