package DTO;

public class DTO_Login {
	String EmployeeNumber;
	
	public DTO_Login() {
		
	}
	
	public DTO_Login(String EmployeeNumber) {
		this.EmployeeNumber =EmployeeNumber;
	}

	public String getEmployeeNumber() {
		return EmployeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		EmployeeNumber = employeeNumber;
	}
	
}
