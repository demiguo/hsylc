package gpa.onlineSubmit;

import gpa.common.DbUtil;
import gpa.login.AcountBean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OnlineSubmit extends HttpServlet {
	
	 private HashMap<String, Double> score =null;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
	     HttpSession session = request.getSession();
	     AcountBean account = new AcountBean();
	     String username = request.getParameter("username");
	     String pwd = request.getParameter("pwd");
	     account.setUsername(username);
	     account.setPassword(pwd);
	     System.out.println("ST_ID1："+request.getParameter("ST_ID1"));
	     System.out.println("ST_ID2："+request.getParameter("ST_ID2"));
	     System.out.println("ST_ID3："+request.getParameter("ST_ID3"));
	     System.out.println("ST_ID4："+request.getParameter("ST_ID4"));
	     System.out.println("======================================");
	     System.out.println("SM_ID1："+request.getParameter("SM_ID1"));
	     System.out.println("SM_ID2："+request.getParameter("SM_ID2"));
	     System.out.println("SM_ID3："+request.getParameter("SM_ID3"));
	     System.out.println("SM_ID4："+request.getParameter("SM_ID4"));
	     System.out.println("======================================");
	     score = this.getScore();
	     int size = this.conutnum();
	     for(int j=1;j<=size;j++){
	    	 System.out.println("输出序号："+j);
		     this.calculateGPA(request.getParameter("ST_ID"+j+""),request.getParameter("ST_ENAME_DESC"+j+""),
		    		 request.getParameter("SM_ID"+j+""),request.getParameter("FSTDAY_CHECK"+j+""),
		    		 request.getParameter("FSTDAY_SCORE"+j+""),request.getParameter("SECTDAY_CHECK"+j+""),
		    		 request.getParameter("SECDAY_SCORE"+j+""),request.getParameter("THRDAY_CHECK"+j+""),
		    		 request.getParameter("THRDAY_SCORE"+j+""),request.getParameter("CALCULATE_GPA"+j+""),
		    		 request.getParameter("OVER_GPA"+j+""),request.getParameter("FINAL_GPA"+j+""),request.getParameter("PAPER_SCORE"+j+""));
	     }
	    
	     
	     String logon_fail = "online.jsp";
	     response.sendRedirect(logon_fail);
	     return;
}
	
     //计算gpa
     public void calculateGPA(String ST_ID,String ST_ENAME_DESC,String SM_ID,
    		 String FSTDAY_CHECK,String FSTDAY_SCORE,String SECTDAY_CHECK,String SECDAY_SCORE,
    		 String THRDAY_CHECK,String THRDAY_SCORE,String CALCULATE_GPA,String OVER_GPA,String FINAL_GPA,String PAPER_SCORE){
    	 
    	 //计算出勤
    	 int fst =0;
    	 try{
    	 fst=Integer.valueOf(FSTDAY_CHECK);
    	 }catch (Exception e) {
			// TODO: handle exception
		}
    	 int sec =0;
    	 try{
    		 sec=Integer.valueOf(SECTDAY_CHECK);
    	 }catch (Exception e) {
			// TODO: handle exception
		}
    	 int thr =0;
    	 try{
    		 thr=Integer.valueOf(THRDAY_CHECK);
    	 }catch (Exception e) {
			// TODO: handle exception
		}
    	 
    	 int W=fst+sec+thr;
    	 //计算CALCULATE_GPA
    	 if(W<2){
    		 //出勤天数不足两天
    		 CALCULATE_GPA="F";
    	 }else{
    		 double score_num = (score.get(FSTDAY_SCORE)*fst+score.get(SECDAY_SCORE)*sec+score.get(THRDAY_SCORE)*thr)/3;
    		 CALCULATE_GPA = this.matchScore(score_num);
    	 }
    	 //计算FINAL_GPA
    	 if(OVER_GPA==null||"".equals(OVER_GPA)||"null".equalsIgnoreCase(OVER_GPA)){
    		 FINAL_GPA= CALCULATE_GPA;
    	 }else{
    		 FINAL_GPA= OVER_GPA ;
    	 }
    	 //更新成绩
    	 this.updateScore(ST_ID, ST_ENAME_DESC, SM_ID, FSTDAY_CHECK, FSTDAY_SCORE, 
    			 SECTDAY_CHECK, SECDAY_SCORE, THRDAY_CHECK, 
    			 THRDAY_SCORE, CALCULATE_GPA, OVER_GPA, FINAL_GPA, PAPER_SCORE);
    	 
    	 
     }
    //查询分数
     public  HashMap<String, Double> getScore(){
		 HashMap<String, Double> score = new HashMap<String, Double>();
		 DbUtil util = new DbUtil();
	     String sql="SELECT * FROM SCOREINFO";
	     ArrayList<HashMap> list = null;
	     try {
	    	 list=util.select(sql);
	    	 if(list!=null&&list.size()>0){
	    		for(int i=0;i<list.size();i++){
	    			score.put(list.get(i).get("SC_LEVL").toString(),Double.valueOf(list.get(i).get("SC_NUM").toString()));
	    		}
	    	 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return score;
	 }
     
     //查询分数
     public  String matchScore( double score_num ){
		 HashMap<String, Double> score = new HashMap<String, Double>();
		 DbUtil util = new DbUtil();
		 String CALCULATE_GPA = "";
	     String sql="SELECT * FROM SCOREINFO where SC_NUM<='"+String.valueOf(score_num)+"' order by sc_num desc";
	     ArrayList<HashMap> list = null;
	     try {
	    	 list=util.select(sql);
	    	 if(list!=null&&list.size()>0){
	    		 CALCULATE_GPA= list.get(0).get("SC_LEVL").toString();
	    	 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return CALCULATE_GPA;
	 }
     
     //查询表单记录数
     public  int conutnum(){
		 DbUtil util = new DbUtil();
		 int num=0;
	     String sql="SELECT count(*) as num FROM gpainfo";
	     ArrayList<HashMap> list = null;
	     try {
	    	 list=util.select(sql);
	    	 if(list!=null&&list.size()>0){
	    		 num= Integer.valueOf(list.get(0).get("num").toString());
	    	 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return num;
	 }
     
//更新数据库
     public  void updateScore(String ST_ID,String ST_ENAME_DESC,String SM_ID,
    		 String FSTDAY_CHECK,String FSTDAY_SCORE,String SECTDAY_CHECK,String SECDAY_SCORE,
    		 String THRDAY_CHECK,String THRDAY_SCORE,String CALCULATE_GPA,String OVER_GPA,String FINAL_GPA,String PAPER_SCORE){
		
		 DbUtil util = new DbUtil();
		 
	     String sql="UPDATE gpainfo " +
	     		"SET  ST_ENAME_DESC = '"+ST_ENAME_DESC+"'," +
	     		" FSTDAY_CHECK = '"+FSTDAY_CHECK+"', FSTDAY_SCORE = '"+FSTDAY_SCORE+"'," +
	     		" SECTDAY_CHECK = '"+SECTDAY_CHECK+"', SECDAY_SCORE = '"+SECDAY_SCORE+"', " +
	     		"THRDAY_CHECK = '"+THRDAY_CHECK+"', THRDAY_SCORE = '"+THRDAY_SCORE+"', " +
	     		"CALCULATE_GPA = '"+CALCULATE_GPA+"', OVER_GPA = '"+OVER_GPA+"'," +
	     		" FINAL_GPA = '"+FINAL_GPA+"', PAPER_SCORE = '"+PAPER_SCORE+"' " +
	     				"WHERE ST_ID = '"+ST_ID+"' and SM_ID = '"+SM_ID+"' ";
	    
	     try {
	    	 util.execute(sql);
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
	 }	

public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

   doGet(request, response);
}
	
}
