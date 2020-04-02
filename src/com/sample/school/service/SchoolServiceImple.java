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
	}
	//신규과목 등록
	public void addNewSubject(Subject subject) {
		
		boolean isExist = subjectRepo.isExistSubjectByTitle(subject);
		if(isExist) {
			System.out.println("이미 존재하는 과목명입니다. 다른 과목이름을 사용해주세요");
			return;
		}
		subjectRepo.insertSubject(subject);
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
					+subject.getTitle()+"\t"+subject.getDept());
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
			System.out.println(course.getNo() + "\t"+course.getSubjectNo()
							+"\t"+course.getName()+"\t"+course.getQuota());
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
		Registration[] registrations = registrationRepo.getAllRegistrationsByCourseNo(course.getNo());
		for(Registration registration : registrations) {
			Student[] students = studentRepo.getAllStudentsByRegistrationNo(registration.getStudentNo());
			for(Student student : students) {
				System.out.println(registration.getCourseNo()+"\t"+student.getNo()
				+"\t"+student.getName()+"\t"+student.getGrade()+"\t"+student.getDept()+"\t"
						+student.getEmail()+"\t"+registration.isCancel());
			}
		}
	
	}
	//개설과정 신청(학생)(수강신청)
	public void registrationByCourseNo(int studentNo, int courseNo) {
		
		Student student = studentRepo.getStudentByNo(studentNo);
		Course course = courseRepo.getCourseByNo(courseNo);
		
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
	}
	
	//수강신청 현황 조회
	public void retrieveRegistrationByCourseNo(int studentNo) {
		
		Student student = studentRepo.getStudentByNo(studentNo);
		if(student == null) {
			System.out.println("존재하지 않은 학생입니다.");
			return;
		}
		Registration[] registrations = registrationRepo.getAllRegistrationByStudentNo(student.getNo());
		for(Registration registration : registrations) {
			Course course = courseRepo.getCourseByNo(registration.getCourseNo());
			System.out.println(student.getNo()+"\t"+student.getName()+"\t"
						+course.getProfessorNo()+"\t"+course.getName()+"\t"
					+course.getQuota());
			
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
		Registration registration = registrationRepo.getRegistrationByNo(course.getNo());
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
	
}
