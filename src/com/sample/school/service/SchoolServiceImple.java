package com.sample.school.service;

import com.sample.school.repository.CourseRepository;
import com.sample.school.repository.ProfessorRepository;
import com.sample.school.repository.RegistrationRepository;
import com.sample.school.repository.StudentRepository;
import com.sample.school.repository.SubjectRepository;
import com.sample.school.vo.Course;
import com.sample.school.vo.Professor;
import com.sample.school.vo.Registration;
import com.sample.school.vo.Student;
import com.sample.school.vo.Subject;

public class SchoolServiceImple implements SchoolService {

	private CourseRepository courseRepo = new CourseRepository();
	private ProfessorRepository professorRepo = new ProfessorRepository();
	private RegistrationRepository registrationRepo = new RegistrationRepository();
	private SubjectRepository subjectRepo = new SubjectRepository();
	private StudentRepository studentRepo = new StudentRepository();
	
	//신규교수 등록
	public void addNewProfessor(Professor professor) {

		boolean isExist = professorRepo.isExistByEmail(professor);
		if (isExist) {
			System.out.println("이미 존재하는 이메일입니다. 다른 이메일을 사용해주세요");
			return;
		}
		professorRepo.insertProfessor(professor);
		System.out.println("신규 교수 등록이 완료되었습니다.");
		System.out.println("교수 번호 : ["+professor.getNo()+"] ");
		
		
	}
	//신규과목 등록
	public void addNewSubject(Subject subject) {
		
		boolean isExist = subjectRepo.isExistSubjectByTitle(subject);
		if(isExist) {
			System.out.println("이미 존재하는 과목명입니다. 다른 과목이름을 사용해주세요");
			return;
		}
		subjectRepo.insertSubject(subject);
		
		System.out.println("신규 과목 등록이 완료되었습니다.");
		System.out.println("과목 번호 : ["+ subject.getNo()+"] ");
		
		
	}
	//과목조회
	public void retrieveSubjectByProfessorNo(int professorNo) {

		Professor professor = professorRepo.getProfessorByNo(professorNo);
		if(professor == null) {
			System.out.println("존재하지 않은 교수입니다. 다시 입력해주세요");
			return;
		}
		
		Subject[] subjects = subjectRepo.getAllSubjectByProfessorNo(professorNo);
		for(Subject subject : subjects ) {
			System.out.println(subject.getNo()+"\t"
					+subject.getTitle()+"\t"+subject.getDept()+"\t"+subject.getCredit());
		}
		
	}
	//신규 개설과정 등록
	public void addNewCourse(Course course) {

		Subject subject = subjectRepo.getSubjectByNo(course.getSubjectNo());
		int professorNo = course.getProfessorNo();
		Professor professor = professorRepo.getProfessorByNo(professorNo);
		
		if(professor.getDept() != subject.getDept()) {
			System.out.println("해당 권한이 없습니다.");
			return;
		}
		courseRepo.insertCourse(course);
		
		System.out.println("개설 과정 등록이 완료 되었습니다.");
	}
	//개설과정 조회
	public void retrieveCourseByProfessorNo(int professorNo) {
		
		Professor professor = professorRepo.getProfessorByNo(professorNo);
		if(professor == null) {
			System.out.println("존재하지 않은 교수입니다. 다시 입력해주세요");
			return;
		}
		
		Course[] courses = courseRepo.getAllCourseByProfessorNo(professorNo);
		
		for(Course course : courses) {
			Subject subject = subjectRepo.getSubjectByNo(course.getSubjectNo());
			System.out.println(course.getNo()+"\t"+course.getName()+"          "
				+"\t"+course.getSubjectNo()+"\t"+subject.getTitle()
				+"\t"+course.getQuota());
		}
		
	}
	//개설과정 신청자 조회
	public void retrieveRegistrationByNo(int professorNo, int courseNo) {
		
		Professor professor = professorRepo.getProfessorByNo(professorNo);
		Course course = courseRepo.getCourseByNo(courseNo);
	
		if(professor == null) {
			System.out.println("존재하지 않은 교수입니다. 다시 입력해주세요");
			return;
		}
		if(course == null) {
			System.out.println("존재하지 않은 과정입니다. 다시 입력해주세요");
			return;
		}
		if(professor.getNo() != course.getProfessorNo()) {
			System.out.println("교수번호가 일치하지 않습니다. 다시 입력해주세요");
			return;
		}
		
		Registration[] registrations = registrationRepo.getAllRegistrationsByCourseNo(courseNo);
		for(Registration registration : registrations) {
			Student student = studentRepo.getStudentByNo(registration.getStudentNo());
			Course courses = courseRepo.getCourseByNo(registration.getCourseNo());
			System.out.println(courses.getName()+"\t"+"          "+student.getNo()
				+"\t"+student.getName()+"\t"+student.getGrade()+"\t"+student.getDept()
				+"\t"+student.getEmail()+"\t"+registration.isCancel());
		}
		
	}
	//개설과정 신청(학생)(수강신청)
	public void registrateCourse(int studentNo, String title) {
		
		Student student = studentRepo.getStudentByNo(studentNo);
		Course course = courseRepo.getCourse(title);
		
		if(student == null) {
			System.out.println("존재하지 않은 학생입니다. 다시 입력해주세요");
			return;
		}
		if(course == null) {
			System.out.println("존재하지 않은 과정입니다. 다시 입력해주세요");
			return;
		}
		
		Registration registration = new Registration();
		registration.setStudentNo(student.getNo());
		registration.setCourseNo(course.getNo());
		registrationRepo.insertRegistration(registration);
		
	}
	//성적입력
	public void grantScore(int professorNo, int registrationNo, int score) {

		Professor professor = professorRepo.getProfessorByNo(professorNo);
		Registration registration = registrationRepo.getRegistrationByNo(registrationNo);
		
		if(registration == null) {
			System.out.println("존재하지 않은 수강신청번호 입니다.");
			return;
		}
		if(professor == null) {
			System.out.println("존재하지 않은 교수입니다.");
			return;
		}
		Course course = courseRepo.getCourseByNo(registration.getCourseNo());
		if(course == null) {
			System.out.println("해당 과정이 존재하지 않습니다.");
			return;
		}
		if(professor.getNo() != course.getProfessorNo()) {
			System.out.println("교수번호가 일치하지 않습니다. 다시 입력해주세요");
			return;
		}
		
		registration.setScore(score);
		
	}
	//학생 추가
	public void addNewStudent(Student student) {
		
		boolean isExist = studentRepo.isExistStudentEmail(student);
		
		if(isExist) {
			System.out.println("이미 존재하는 이메일입니다. 다시 입력해주세요");
			return;
		}
		studentRepo.insertStudent(student);
		System.out.println("등록이 완료 되었습니다.");
		System.out.println("나의 학번 ["+student.getNo()+"] ");
	}
	
	//수강신청 현황 조회
	public void retrieveRegistration(int studentNo) {
		
		Student student = studentRepo.getStudent(studentNo);
		if(student == null) {
			System.out.println("존재하지 않은 학생입니다.");
			return;
		}
		
		Registration[] registrations = registrationRepo.getAllRegistrationByStudentNo(student.getNo());
		for(Registration registration : registrations) {
			Course course = courseRepo.getCourse(registration.getCourseNo());
			Professor professor = professorRepo.getProfessorByNo(course.getProfessorNo());
			System.out.println(student.getNo()+"  "+student.getName()+"  "
						+course.getName()+"         "+"\t"+professor.getName()
						+"  "+course.getNo()+"\t"+registration.isCancel());
		}
	}
	//수강신청 취소	
	public void cancelCourseByCourseNo(int studentNo, int courseNo) {
		Student student = studentRepo.getStudentByNo(studentNo);

		Course course = courseRepo.getCourseByNo(courseNo);
		
		if(student == null) {
			System.out.println("해당 학생정보가 존재하지 않습니다.");
			return;
		}
		if(course == null) {
			System.out.println("해당 강좌 정보가 존재하지 않습니다.");
			return;
		}
		Registration registration = registrationRepo.getRegistrationByNo(studentNo, courseNo);

		if(registration == null) {
			System.out.println("등록 내역이 존재하지 않습니다.");
			return;
		}
		registration.setCancel(true);
	}
	//점수조회
	public void retrieveScoreByStudentNo(int studentNo) {
		Student student = studentRepo.getStudentByNo(studentNo);
		if(student == null) {
			System.out.println("존재하지 않은 학생정보 입니다.");
			return;
		}
		Registration registration = registrationRepo.getRegistrationByStudentNo(student.getNo());
		if(registration == null) {
			System.out.println("수강신청이 등록되지 않은 학생정보 입니다.");
			return;
		}
		Course course = courseRepo.getCourseByNo(registration.getCourseNo());
		if(course == null) {
			System.out.println("존재하지 않은 강의 입니다.");
			return;
		}
		System.out.println(student.getNo()+"\t"+student.getName()+"\t"
				+student.getGrade()+"\t"+course.getName()+"\t"+registration.getScore());
		
	}
	//개인 학생정보 조회
	public void retrieveStudentByStudentNo(int studentNo) {
		
		Student student = studentRepo.getStudentByNo(studentNo);
		if(student.getName() == null) {
			System.out.println("존재하지 않은 학번입니다.");
			return;
		}
		System.out.println(student.getNo()+"\t"+student.getName()+
				"\t"+student.getDept()+"\t"+student.getGrade());
	}
	//전체 강좌조회(course)
	public void retrieveAllCourse() {
		Course[] courses = courseRepo.getAllCourse();
				
		for(Course course : courses) {
			Professor professor = professorRepo.getProfessorByNo(course.getProfessorNo());
			Subject subject = subjectRepo.getSubjectByNo(course.getSubjectNo());
			System.out.println(course.getNo()+"\t"+course.getName()+"         "
				+"\t"+course.getQuota()+"\t"+professor.getName()
				+"\t"+subject.getTitle()+"                "+"\t"+subject.getDept());
		}
	
	
	}
	//전체 개설강좌 조회(registration)
	public void retrieveAllRegistration() {
		Registration[] registrations = registrationRepo.getAllRegistrations();
		for(Registration registration : registrations) {
			Course course = courseRepo.getCourseByNo(registration.getCourseNo());
			Professor professor = professorRepo.getProfessorByNo(course.getProfessorNo());
			Subject subject = subjectRepo.getSubjectByNo(course.getSubjectNo());
			System.out.println(registration.getNo()+"\t"+course.getNo()
				+"\t"+course.getName()+"                     "+"\t"+professor.getNo()
				+"\t"+professor.getName()+"\t"+subject.getNo()+"\t"+subject.getTitle()+"                "
				+"\t"+registration.isCancel()+"\t"+course.getQuota());
		}
	}
	
}
