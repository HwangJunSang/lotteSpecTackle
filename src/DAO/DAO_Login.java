package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.mail.search.IntegerComparisonTerm;
import javax.sql.DataSource;

import DTO.DTO_Login;

public class DAO_Login {
	
	DataSource dataSource;
	
	// 생성자
	public DAO_Login() {
		System.out.println("------------ DAO ------------");
		
		try {
			// 1. JDBC 드라이버 로딩
			System.out.println("JDBC Load Waiting...");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("JDBC Driver loaded Success!");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Cannot find the driver in the classpath!", e);
		}
	}


	/* 
	 * [ Select SQL - 테이블 명 : employeeNumber ] 
	 * 회원가입 - 유효성 검사
	 * */
	public Boolean ValidationCheck(String bId) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean check = false;
		
		try {
			// 2. Connection 객체 생성
			connection = DriverManager.getConnection("jdbc:oracle:thin:@//aa1h12j2tg4nk6a.ci76um4jhgso.us-east-2.rds.amazonaws.com:1521/EBDB", "HJS" , "googlerns7");
			System.out.println("DB Connection Success");
			
			// 3. 쿼리 조합
			String query = "SELECT employeeNumber FROM Employee";
			preparedStatement = connection.prepareStatement(query);
			
			// 4. 쿼리 실행
			resultSet = preparedStatement.executeQuery();
			
			// 5. DB 데이터 불러오기
			while (resultSet.next()) {
				long EmployeeNumber = resultSet.getLong("employeeNumber");

				
				
				// 만약 중복 ID가 있다면 true 반환
				if (Integer.parseInt(bId) == EmployeeNumber) {
					check = true;
				}
				
				System.out.println(check);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6. 쿼리 실행
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return check;
	}
	
	
	/* 
	 * [ Select SQL - 테이블 명 : EmployeeInfo ] 
	 * 로그인 확인
	 * */
	public Boolean LoginValidationCheck(String lotte_id, String lotte_password) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean check = false;
		
		try {
			// 2. Connection 객체 생성
			connection = DriverManager.getConnection("jdbc:oracle:thin:@//aa1h12j2tg4nk6a.ci76um4jhgso.us-east-2.rds.amazonaws.com:1521/EBDB", "HJS" , "googlerns7");
			System.out.println("DB Connection Success");
			
			// 3. 쿼리 조합
			String query = "SELECT employeeNumber, lotte_pw FROM EmployeeInfo";
			preparedStatement = connection.prepareStatement(query);
			
			// 4. 쿼리 실행
			resultSet = preparedStatement.executeQuery();
			
			// 5. DB 데이터 불러오기
			while (resultSet.next()) {
				long EmployeeNumber = resultSet.getLong("employeeNumber");
				String Lotte_pw = resultSet.getString("lotte_pw");

				// 만약 중복 ID가 있다면 true 반환
				if (Integer.parseInt(lotte_id) == EmployeeNumber && lotte_password == Lotte_pw) {
					check = true;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6. 쿼리 실행
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return check;
	}


	/* 
	 * [ Insert SQL - 테이블 명 : EmployeeInfo ] 
	 * 회원가입
	 * */
	public void Join_u0(int lotte_id, String lotte_pw, String lotte_email, String lotte_name) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			// 2. Connection 객체 생성
			connection = DriverManager.getConnection("jdbc:oracle:thin:@//aa1h12j2tg4nk6a.ci76um4jhgso.us-east-2.rds.amazonaws.com:1521/EBDB", "HJS" , "googlerns7");
			System.out.println("DB Connection Success");
			
			// 3. 쿼리 조합
			String query = "insert into EmployeeInfo (employeeNumber, lotte_pw, lotte_email, employeeName) values (?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, lotte_id);
			preparedStatement.setString(2, lotte_pw);
			preparedStatement.setString(3, lotte_email);
			preparedStatement.setString(4, lotte_name);
			
			// 4. 쿼리 실행
			int rn = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5. 닫기
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}



	

}
