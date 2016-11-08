package gpa.login;

import gpa.common.DbUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.*;
/**
 * 登陆验证/页面跳转
 * @author Administrator
 *
 */
public class CheckAccount extends HttpServlet {

public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
     HttpSession session = request.getSession();
     AcountBean account = new AcountBean();
     String username = request.getParameter("username");
     String pwd = request.getParameter("pwd");
     account.setUsername(username);
     account.setPassword(pwd);
     DbUtil util = new DbUtil();
     String sql="select * from userinfo where USER_ID= '"+username+"' and USER_PASSWD = '"+pwd+"' ";
     ArrayList<HashMap> list = null;
     String user_type ="";
     try {
    	 list=util.select(sql);
    	 if(list!=null&&list.size()>0){
    		 HashMap map = list.get(0);
    		 user_type = map.get("USER_TYPE").toString();
    		 account.setUsertype(user_type);
    	 }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	if("0".equals(user_type)){
		   session.setAttribute("account", account);
		   session.setAttribute("LoginFlag", "1");
		   String logon_suc = "importAndExportInfo.jsp";
	       response.sendRedirect(logon_suc);
	}else if ("".equals(user_type)){
			session.setAttribute("LoginFlag", "-1");
		     String logon_fail = "login.jsp";
		     response.sendRedirect(logon_fail);
	}else{
		   session.setAttribute("account", account);
		   session.setAttribute("LoginFlag", "1");
	       String logon_suc = "online.jsp";
	       response.sendRedirect(logon_suc);
	}
	
     
   
     return;
}


public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

     doGet(request, response);
}

}