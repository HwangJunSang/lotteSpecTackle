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
	
	// ������
	public DAO_Login() {
		System.out.println("------------ DAO ------------");
		
		try {
			// 1. JDBC ����̹� �ε�
			System.out.println("JDBC Load Waiting...");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("JDBC Driver loaded Success!");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Cannot find the driver in the classpath!", e);
		}
	}


	/* 
	 * [ Select SQL - ���̺� �� : employeeNumber ] 
	 * ȸ������ - ��ȿ�� �˻�
	 * */
	public Boolean ValidationCheck(String bId) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean check = false;
		
		try {
			// 2. Connection ��ü ����
			connection = DriverManager.getConnection("jdbc:oracle:thin:@//aa1h12j2tg4nk6a.ci76um4jhgso.us-east-2.rds.amazonaws.com:1521/EBDB", "HJS" , "googlerns7");
			System.out.println("DB Connection Success");
			
			// 3. ���� ����
			String query = "SELECT employeeNumber FROM Employee";
			preparedStatement = connection.prepareStatement(query);
			
			// 4. ���� ����
			resultSet = preparedStatement.executeQuery();
			
			// 5. DB ������ �ҷ�����
			while (resultSet.next()) {
				long EmployeeNumber = resultSet.getLong("employeeNumber");

				
				
				// ���� �ߺ� ID�� �ִٸ� true ��ȯ
				if (Integer.parseInt(bId) == EmployeeNumber) {
					check = true;
				}
				
				System.out.println(check);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6. ���� ����
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
	 * [ Select SQL - ���̺� �� : EmployeeInfo ] 
	 * �α��� Ȯ��
	 * */
	public Boolean LoginValidationCheck(String lotte_id, String lotte_password) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean check = false;
		
		try {
			// 2. Connection ��ü ����
			connection = DriverManager.getConnection("jdbc:oracle:thin:@//aa1h12j2tg4nk6a.ci76um4jhgso.us-east-2.rds.amazonaws.com:1521/EBDB", "HJS" , "googlerns7");
			System.out.println("DB Connection Success");
			
			// 3. ���� ����
			String query = "SELECT employeeNumber, lotte_pw FROM EmployeeInfo";
			preparedStatement = connection.prepareStatement(query);
			
			// 4. ���� ����
			resultSet = preparedStatement.executeQuery();
			
			// 5. DB ������ �ҷ�����
			while (resultSet.next()) {
				long EmployeeNumber = resultSet.getLong("employeeNumber");
				String Lotte_pw = resultSet.getString("lotte_pw");

				// ���� �ߺ� ID�� �ִٸ� true ��ȯ
				if (Integer.parseInt(lotte_id) == EmployeeNumber && lotte_password == Lotte_pw) {
					check = true;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6. ���� ����
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
	 * [ Insert SQL - ���̺� �� : EmployeeInfo ] 
	 * ȸ������
	 * */
	public void Join_u0(int lotte_id, String lotte_pw, String lotte_email, String lotte_name) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			// 2. Connection ��ü ����
			connection = DriverManager.getConnection("jdbc:oracle:thin:@//aa1h12j2tg4nk6a.ci76um4jhgso.us-east-2.rds.amazonaws.com:1521/EBDB", "HJS" , "googlerns7");
			System.out.println("DB Connection Success");
			
			// 3. ���� ����
			String query = "insert into EmployeeInfo (employeeNumber, lotte_pw, lotte_email, employeeName) values (?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, lotte_id);
			preparedStatement.setString(2, lotte_pw);
			preparedStatement.setString(3, lotte_email);
			preparedStatement.setString(4, lotte_name);
			
			// 4. ���� ����
			int rn = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5. �ݱ�
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}



	

}
