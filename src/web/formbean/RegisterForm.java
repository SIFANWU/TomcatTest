package web.formbean;

import java.util.Map;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.apache.commons.collections.map.HashedMap;


public class RegisterForm {
	
	private String username;
	private String password;
	private String password2;
	private String email;
	private String birthday;//表单全是字符串传输
	
	@SuppressWarnings("rawtypes")
	private Map errors = new HashedMap();
	
	
	public Map getErrors() {
		return errors;
	}
	public void setErrors(Map errors) {
		this.errors = errors;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	//username password 不能为空 
	//2次密码必须一致
	//email要合法
	//生日可以空，格式必须为日期
	
	@SuppressWarnings("unchecked")
	public boolean validate(){
		boolean isOK = true;
		
		if (this.username==null || this.username.trim().equals("")) {
			isOK = false;
			errors.put("username", "username can not be empty!");
		}else {
			if (!this.username.matches("\\w{3,8}")){
				isOK=false;
				errors.put("username", "username must be 3-8 characters!");
			}
			
		}
		
		if (this.password==null || this.password.trim().equals("")) {
			isOK = false;
			errors.put("password", "password can not be empty!");
		}else {
			if (!this.password.matches("\\d{3,8}")) {
				isOK=false;
				errors.put("password", "password must be between 3 to 8 numbers!");
				
			}
		}
		
		if (this.password2==null || this.password2.trim().equals("")) {
			isOK = false;
			errors.put("password2", "password can not be empty!");
		}else {
			if (!this.password.equals(this.password2)) {
				isOK=false;
				errors.put("password", "password must be the same!");
				
			}
		}
		
		if (this.email==null || this.email.trim().equals("")) {
			isOK = false;
			errors.put("email", "email can not be empty!");
		}else {
			
			if (!this.email.matches("\\w+@\\w+(\\.\\w+)+")) {
				isOK=false;
				errors.put("email", "email is not right!");
				
			}
		}
		
		if (this.birthday!=null && !this.birthday.trim().equals("")) {
			try {
				DateLocaleConverter dlc = new DateLocaleConverter();
				dlc.convert(this.birthday,"dd-MM-yyyy");
				
			} catch (Exception e) {
				isOK = false;
				errors.put("birthday", "Birthday format should be like dd-MM-yyyy format!");
			}
			
		}
		
		
		return isOK;
	}
	

}
