package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BCommand.BCommand;
import Command.Join_ValidationCheck;
import Command.Join_u0;
import Command.Login_ValidationCheck;
import DTO.DTO_Email;
import Service.Service_Email;

@WebServlet("*.do")
public class Servlet_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// 생성자
    public Servlet_Login() {
        super();
        System.out.println("------------ Login Controller ------------");
    }

    // doGet
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGET 전송 방식");
		doAction(request, response);
	}

	// doPost
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPOST 전송 방식");
		doAction(request, response);
	}

	// doAction
	public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doAction 시작");
		
		// 인코딩
		request.setCharacterEncoding("UTF-8");
		
		String viewPage = null;		// 어떤 뷰를 보여 줄지 뷰 객체
		BCommand command = null;	// 어떤 로직을 수행할지 커맨드 객체
		
		String uri = request.getRequestURI();			// 사용자가 요청한 URL
		String conPath = request.getContextPath();		// ContextPath
		String com = uri.substring(conPath.length());	// ContextPath만 잘라냄
		System.out.println("풀 경로 : " + uri);
		System.out.println("프로젝트 이름 : " + conPath);
		System.out.println("가공 ContextPath : " + com);
		

		
		// 로그인 페이지 이동
		if(com.equals("/view/mv_lotte_Login.do")) {
			System.out.println("컨트롤러 - mv_lotte_Login.do");
			viewPage = "lotte_Login.jsp";
			
			// 포워딩 기법(화면 전환)
			RequestDispatcher  dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
		
		/* 회원가입 페이지 이동 */
		else if(com.equals("/view/mv_lotte_Join.do")) {
			System.out.println("컨트롤러 - mw_lotte_Join.do");
			viewPage = "lotte_Join.jsp";
			
			// 포워딩 기법(화면 전환)
			RequestDispatcher  dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
		
		/* 메인 페이지 이동 */
		else if(com.equals("/view/mv_lotte_Main.do")) {
			System.out.println("컨트롤러 - mw_lotte_Main.do");
			viewPage = "lotte_Main.jsp";
			
			// 포워딩 기법(화면 전환)
			RequestDispatcher  dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
		
		/* ID 유효성 검사 이동 */
		else if(com.equals("/view/Validation_check.do")) {
			System.out.println("컨트롤러 - Validation_check.do");
			command = new Join_ValidationCheck();
			command.execute(request, response);
		}
		
		/* 회원 가입 && 이메일 보내기 */
		else if(com.equals("/view/s0hjscob01u0.do")) {
			System.out.println("컨트롤러 - s0hjscob01u0.do");
			
			// 이메일 보내기
			String senderMail = "황준상";									// 보내는 사람의 이름
			String senderName = "gufrus@naver.com";					// 보내는 사람의 메일 주소
			String receiveMail = request.getParameter("lotte_email");	// 받는 사람의 메일 주소
			String subject = "롯데정보통신 스펙태클 사이트 가입을 축하드립니다.";		// 메일 제목
			String message = "롯데정보통신 스펙태클 사이트 가입을 축하드립니다.";		// 메일 내용
			
			System.out.println("senderMail : " + senderMail);
			System.out.println("senderName : " + senderName);
			System.out.println("receiveMail : " + receiveMail);
			System.out.println("subject : " + subject);
			System.out.println("message : " + message);
			
			// 폼
			DTO_Email dto = new DTO_Email();
			dto.setSenderMail(senderMail);
			dto.setSenderName(senderName);
			dto.setReceiveMail(receiveMail);
			dto.setSubject(subject);
			dto.setMessage(message);
			
			// 이메일 전송
			Service_Email service = new Service_Email();
			
			try {
				System.out.println("메일 전송");
				service.mailSender(dto);
			} catch (Exception e) {
				System.out.println("메일 실패");
				e.printStackTrace();
			}
			
			
			// 유저 회원가입
			command = new Join_u0();
			command.execute(request, response);
			viewPage = "mv_lotte_Main.do";
			
			// 포워딩 기법(화면 전환)
			RequestDispatcher  dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
		
		/* 로그인 */
		else if(com.equals("/view/login.do")) {
			System.out.println("컨트롤러 - login.do");
			command = new Login_ValidationCheck();
			command.execute(request, response);
		}
		
		
		
		
		
		
		

	}
	
}
