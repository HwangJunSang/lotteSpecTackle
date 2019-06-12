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
       
	// ������
    public Servlet_Login() {
        super();
        System.out.println("------------ Login Controller ------------");
    }

    // doGet
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGET ���� ���");
		doAction(request, response);
	}

	// doPost
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPOST ���� ���");
		doAction(request, response);
	}

	// doAction
	public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doAction ����");
		
		// ���ڵ�
		request.setCharacterEncoding("UTF-8");
		
		String viewPage = null;		// � �並 ���� ���� �� ��ü
		BCommand command = null;	// � ������ �������� Ŀ�ǵ� ��ü
		
		String uri = request.getRequestURI();			// ����ڰ� ��û�� URL
		String conPath = request.getContextPath();		// ContextPath
		String com = uri.substring(conPath.length());	// ContextPath�� �߶�
		System.out.println("Ǯ ��� : " + uri);
		System.out.println("������Ʈ �̸� : " + conPath);
		System.out.println("���� ContextPath : " + com);
		

		
		// �α��� ������ �̵�
		if(com.equals("/view/mv_lotte_Login.do")) {
			System.out.println("��Ʈ�ѷ� - mv_lotte_Login.do");
			viewPage = "lotte_Login.jsp";
			
			// ������ ���(ȭ�� ��ȯ)
			RequestDispatcher  dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
		
		/* ȸ������ ������ �̵� */
		else if(com.equals("/view/mv_lotte_Join.do")) {
			System.out.println("��Ʈ�ѷ� - mw_lotte_Join.do");
			viewPage = "lotte_Join.jsp";
			
			// ������ ���(ȭ�� ��ȯ)
			RequestDispatcher  dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
		
		/* ���� ������ �̵� */
		else if(com.equals("/view/mv_lotte_Main.do")) {
			System.out.println("��Ʈ�ѷ� - mw_lotte_Main.do");
			viewPage = "lotte_Main.jsp";
			
			// ������ ���(ȭ�� ��ȯ)
			RequestDispatcher  dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
		
		/* ID ��ȿ�� �˻� �̵� */
		else if(com.equals("/view/Validation_check.do")) {
			System.out.println("��Ʈ�ѷ� - Validation_check.do");
			command = new Join_ValidationCheck();
			command.execute(request, response);
		}
		
		/* ȸ�� ���� && �̸��� ������ */
		else if(com.equals("/view/s0hjscob01u0.do")) {
			System.out.println("��Ʈ�ѷ� - s0hjscob01u0.do");
			
			// �̸��� ������
			String senderMail = "Ȳ�ػ�";									// ������ ����� �̸�
			String senderName = "gufrus@naver.com";					// ������ ����� ���� �ּ�
			String receiveMail = request.getParameter("lotte_email");	// �޴� ����� ���� �ּ�
			String subject = "�Ե�������� ������Ŭ ����Ʈ ������ ���ϵ帳�ϴ�.";		// ���� ����
			String message = "�Ե�������� ������Ŭ ����Ʈ ������ ���ϵ帳�ϴ�.";		// ���� ����
			
			System.out.println("senderMail : " + senderMail);
			System.out.println("senderName : " + senderName);
			System.out.println("receiveMail : " + receiveMail);
			System.out.println("subject : " + subject);
			System.out.println("message : " + message);
			
			// ��
			DTO_Email dto = new DTO_Email();
			dto.setSenderMail(senderMail);
			dto.setSenderName(senderName);
			dto.setReceiveMail(receiveMail);
			dto.setSubject(subject);
			dto.setMessage(message);
			
			// �̸��� ����
			Service_Email service = new Service_Email();
			
			try {
				System.out.println("���� ����");
				service.mailSender(dto);
			} catch (Exception e) {
				System.out.println("���� ����");
				e.printStackTrace();
			}
			
			
			// ���� ȸ������
			command = new Join_u0();
			command.execute(request, response);
			viewPage = "mv_lotte_Main.do";
			
			// ������ ���(ȭ�� ��ȯ)
			RequestDispatcher  dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
		
		/* �α��� */
		else if(com.equals("/view/login.do")) {
			System.out.println("��Ʈ�ѷ� - login.do");
			command = new Login_ValidationCheck();
			command.execute(request, response);
		}
		
		
		
		
		
		
		

	}
	
}
