package gpa.login;

public class AcountBean {
private String username="";
private String password="";
private String usertype="";

private String msg ="";

public String getPassword() {
     return password;
}
public void setPassword(String password) {
     this.password = password;
}
public String getUsername() {
     return username;
}
public void setUsername(String username) {
     this.username = username;
}
public void setUsertype(String usertype) {
	this.usertype = usertype;
}

public String getUsertype() {
	return usertype;
}

public String getMsg() {
	return msg;
}

public void setMsg(String msg) {
	this.msg = msg;
}

}