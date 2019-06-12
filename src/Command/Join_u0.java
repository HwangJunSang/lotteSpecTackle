package Command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BCommand.BCommand;
import DAO.DAO_Login;


public class Join_u0 implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("------------ Join_u0 ------------");
		
		int lotte_id = Integer.parseUnsignedInt(request.getParameter("lotte_id"));
		String lotte_pw = request.getParameter("lotte_pw");
		String lotte_email = request.getParameter("lotte_email");
		String lotte_name = request.getParameter("lotte_name");
		
		System.out.println("Inset : [ " + lotte_id + " , "+ lotte_pw + " , " + lotte_email + " , " + lotte_name + " ]");
		
		DAO_Login dao = new DAO_Login();
		dao.Join_u0(lotte_id, lotte_pw, lotte_email, lotte_name);
	}
}