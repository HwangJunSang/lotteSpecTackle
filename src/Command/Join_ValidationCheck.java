package Command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BCommand.BCommand;
import DAO.DAO_Login;
import DTO.DTO_Login;

public class Join_ValidationCheck implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("------------ BMain_i1 ------------");
		
		String bId = request.getParameter("lotte_id");
		
		// 로그
		System.out.println("bId : " + bId);
		
		// 객체 생성 후 유효 검사
		DAO_Login dao = new DAO_Login();
		Boolean dtos = dao.ValidationCheck(bId);
	
		// 폼으로 되돌아가기
		try {
			// 중복 아이디가 없는 경우 유효성 검사 통과
			if(dtos == true) {
				response.sendRedirect(request.getContextPath() + "/view/lotte_Join.jsp?message=OK");
			}else {
				response.sendRedirect(request.getContextPath() + "/view/lotte_Join.jsp?message=NO");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
