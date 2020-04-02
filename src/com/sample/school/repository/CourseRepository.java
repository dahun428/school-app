package com.sample.school.repository;

import java.util.Arrays;

import com.sample.school.vo.Course;
import com.sample.school.vo.Subject;

public class CourseRepository {

	private Course[] db = new Course[100];
	private int position = 0;
	private int sequence = 40000;
	
	public void insertCourse(Course course) {
		db[position] = course;
		course.setNo(sequence);

		position++;
		sequence++;
	}
	
	public Course getCourseByNo(int courseNo) {

		Course course = new Course();
		for(int i = 0; i < position; i++) {
			if(db[i].getNo() == courseNo) {
				course = db[i];
				break;
			}
		}
		return course;
	}
	
	public Course[] getAllCourse() {
		return Arrays.copyOfRange(db, 0, position);
	}
	
	public Course[] getAllCourseByProfessorNo(int professorNo) {
		Course[] course = new Course[position];
		int count = 0;
		for(int i = 0; i < position; i++) {
			if(professorNo == db[i].getProfessorNo()) {
				course[count] = db[i];
				count++;
			}
		}
		return Arrays.copyOfRange(course, 0, count);
	
	}
	
}
