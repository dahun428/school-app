package com.sample.school.service;

import com.sample.school.vo.Course;
import com.sample.school.vo.Professor;
import com.sample.school.vo.Student;
import com.sample.school.vo.Subject;

/**
 * 수강신청과 관련된 서비스를 제공하는 구현객체가 반드시 구현해야 하는 기능을 정의한 인터페이스다.<br />
 * <h3>교수관련 기능</h3>
 * <ul>
 * 	<li>교수등록</li>
 * 	<li>과목등록</li>
 * 	<li>과목조회</li>
 * 	<li>개설과정등록</li>
 * 	<li>개설과정 조회</li>
 * 	<li>과정 신청자 조회</li>
 * 	<li>성적 부여</li>
 * </ul>
 * 
 * <h3>학생관련 기능</h3>
 * 	<li>학생등록</li>
 * 	<li>과목조회</li>
 * 	<li>개설과정 조회</li>
 * 	<li>수강신청</li>
 * 	<li>수강신청 현황 조회</li>
 * 	<li>수강신청 취소</li>
 * 	<li>성적 조회</li>
 * </ul>

 * @author JHTA
 *
 */
public interface SchoolService {
	//<h3>교수관련 기능</h3>
	//<li>교수등록</li>	
	void addNewProfessor(Professor professor);
	//<li>과목등록</li>
	void addNewSubject(Subject subject);
	//<li>과목조회</li>
	void retrieveSubjectByProfessorNo(int professorNo);
	//<li>과목조회</li>
//	void retrieveSubjectByTitle(String title);

	//<li>개설과정등록</li>
	void addNewCourse(Course course);
	//<li>개설과정 조회</li>
//	void retrieveCourseByNo(int no);
//	void retrieveCourseByName(String name);
	void retrieveCourseByProfessorNo(int professorNo);
	//<li>과정 신청자 조회</li>
	void retrieveRegistrationByNo(int professorNo, int CourseNo);
	//<li>성적 부여</li> --> resigtration
	void grantScore(int professorNo, int registrationNo, int score);

	//	<h3>학생관련 기능</h3>
	//	<li>학생등록</li>
	void addNewStudent(Student student);
	//	<li>수강신청</li>
	void registrateCourse(int studentNo, int courseNo);
	//	<li>수강신청 현황 조회</li>
	void retrieveRegistration(int studentNo);
	
	//	<li>수강신청 취소</li>
	void cancelCourseByCourseNo(int studentNo, int courseNo);
	//	<li>성적 조회</li>
	void retrieveScoreByStudentNo(int studentNo);

	// <li> 개인 학생정보 조회</li>
	void retrieveStudentByStudentNo(int studentNo);
	// 전체 강좌 조회
	void retrieveAllCourse();
	//전체 개설 강좌 조회
	void retrieveAllRegistration();
	//과목조회(student)
	void retrieveSubjectByStudentNo(int studentNo);
	//신청가능한 개설강좌(학생)
	public void retrieveCourseByStudent(int studentNo);
	//해당 교수 개설강좌 신청자 조회(교수)
	public void retrieveRegistrationStudentByNo(int professorNo, int registrationNo);
}
