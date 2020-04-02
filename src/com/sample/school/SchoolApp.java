package com.sample.school;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.sample.school.service.SchoolService;
import com.sample.school.service.SchoolServiceImple;
import com.sample.school.vo.Student;

public class SchoolApp {
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	private static int nextInt() {
		try {
			String text = in.readLine();
			return Integer.parseInt(text);
		} catch (IOException e) {
			return 0;
		}
	}
	private static String next() {
		try {
			return in.readLine();
		} catch (IOException e) {
			return "";
		}
	}
	
	public static void main(String[] args) {

		SchoolService service = new SchoolServiceImple();
		
		
		
		while (true) {
			
			System.out.println();
			System.out.println();
			System.out.println("##### 수강신청 #####");
			System.out.println("=================================================================================");
			System.out.println("1.학생관리 2.교수관리 0. 종료");
			System.out.println("=================================================================================");
			System.out.print("메뉴 입력 : ");
			int menuNum = SchoolApp.nextInt();
			
			if(menuNum == 1) {
				System.out.println("##### 학생관리 PAGE #####");
				System.out.println("=================================================================================");
				System.out.println("1.신규등록 2.수강신청 3.수강신청조회 4.수강신청취소 5.점수조회 6.개인정보조회 7.현재열린강좌");
				System.out.println("=================================================================================");
				System.out.print("메뉴 입력 : ");
				int StudentMenuNum = SchoolApp.nextInt();
				if(StudentMenuNum == 1) {
					
					
					System.out.println();
					System.out.println("[신 규 등 록]");
					System.out.print("이름을 입력하세요 : ");
					String name = SchoolApp.next();
					System.out.print("이메일을 입력하세요 : ");
					String email = SchoolApp.next();
					System.out.print("학과를 입력하세요 : ");
					String dept = SchoolApp.next();
					System.out.print("학년을 입력하세요 : ");
					int grade = SchoolApp.nextInt();
					Student student = new Student(name,email,dept,grade);
					service.addNewStudent(student);
					
				}else if(StudentMenuNum == 2) {
					
					System.out.println();
					System.out.println("[ 수 강 신 청 ]");
					System.out.print("학생번호를 입력하세요 : ");
					int studentNo = SchoolApp.nextInt();
					System.out.print("개설 강좌 번호를 입력하세요 :  ");
					int courseNo = SchoolApp.nextInt();
					
					service.registrationByCourseNo(studentNo, courseNo);
					
					System.out.println("수강신청이 완료되었습니다.");
				
				}else if(StudentMenuNum == 3) {
					
					System.out.println();
					System.out.println("[수강 신청 조회]");
					System.out.print("학생 번호를 입력하세요 : ");
					int studentNo = SchoolApp.nextInt();
					System.out.println("=================================================================================");
					System.out.println("학생번호  학생이름  수강과목   	       교수이름   수강과목 번호	수강취소여부");
					System.out.println("=================================================================================");
					
					
					service.retrieveRegistrationByCourseNo(studentNo);
					
				}else if(StudentMenuNum == 4) {
					
					System.out.println();
					System.out.println("[수강 신청 취소]");
					System.out.print("학생 번호를 입력하세요  : ");
					int studentNo = SchoolApp.nextInt();
					System.out.print("취소할 강좌 번호를 입력하세요 : ");
					int courseNo = SchoolApp.nextInt();
					
					service.cancelCourseByCourseNo(studentNo, courseNo);
					
					System.out.println("수강 취소가 완료 되었습니다.");
				}else if(StudentMenuNum == 5) {
					
					System.out.println();
					System.out.println("[점수조회]");
					System.out.print("학생 번호를 입력하세요 : ");
					int studentNo = SchoolApp.nextInt();
					System.out.println("=================================================================================");
					System.out.println("학생번호  학생이름	학년	수강강의명	점수");
					System.out.println("=================================================================================");
					
					service.retrieveScoreByStudentNo(studentNo);
					
					
				}else if(StudentMenuNum == 6) {
					System.out.println();
					System.out.println("[학생 정보 조회]");
					System.out.print("학생 번호를 입력하세요 : ");
					int studentNo = SchoolApp.nextInt();
					System.out.println("=================================================================================");
					System.out.println("학생번호	이름	학과		학년");
					System.out.println("=================================================================================");
					service.retrieveStudentByStudentNo(studentNo);
					
				}else if(StudentMenuNum == 7) {
					System.out.println();
					System.out.println("[현재 개설된 강좌]");
				
					System.out.println("=================================================================================");
					System.out.println("과정번호	과정명         		정원	교수이름	과목명             		학과");
					System.out.println("=================================================================================");
					
					service.retrieveAllCourse();
				
				
				}
				
			}else if(menuNum == 2) {
				
				
				
				
			}else if(menuNum == 0) {
				System.out.println();
				System.out.println("[프로그램 종료]");
				break;
			}
			
			
		
		
		
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
