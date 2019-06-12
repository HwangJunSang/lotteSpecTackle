package Command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BCommand.BCommand;
import DAO.DAO_Login;

public class Login_ValidationCheck implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("------------ BMain_i1 ------------");
		
		String lotte_id = request.getParameter("lotte_id");
		String lotte_password = request.getParameter("lotte_password");
		
		// �α�
		System.out.println("lotte_id : " + lotte_id);
		System.out.println("lotte_password : " + lotte_password);
		
		// ��ü ���� �� ��ȿ �˻�
		DAO_Login dao = new DAO_Login();
		Boolean dtos = dao.LoginValidationCheck(lotte_id, lotte_password);
	
		// ������ �ǵ��ư���
		try {
			// �ߺ� ���̵� ���� ��� ��ȿ�� �˻� ���
			if(dtos == true) {
				response.sendRedirect(request.getContextPath() + "/view/lotte_Login.jsp?message=OK");
			}else {
				response.sendRedirect(request.getContextPath() + "/view/lotte_Login.jsp?message=NO");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}