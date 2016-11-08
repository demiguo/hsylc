package gpa.importandexport;

import gpa.common.DbUtil;
import gpa.login.AcountBean;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


/**
 * 导入数据
 * @author Administrator
 *
 */
public class ImportData extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
	     HttpSession session = request.getSession();
	     String state=request.getParameter("state");
	     String importfile=request.getParameter("importfile");
	     System.out.println("文件名："+importfile);
	     if("stu".equalsIgnoreCase(state)){
	    	//学生输入表
	    	 if(upfile(importfile, 0)){
	    		 System.out.println("导入成功");
	    	 }else{
	    		 System.out.println("导入失败");
	    	 }
	     }else  if("sem".equalsIgnoreCase(state)){
	    	//研讨课输入表 
	    	 if(upfile(importfile, 1)){
	    		 System.out.println("导入成功");
	    	 }else{
	    		 System.out.println("导入失败");
	    	 }
	     }
	     String logon_suc = "importAndExportInfo.jsp";
	     response.sendRedirect(logon_suc);
	     return;
}
	
	
	public boolean upfile(String name,int num){

		try{
		DbUtil util = new DbUtil();
		if(name.indexOf(":")<1){
			name =util.path+File.separator+name;
			System.out.println("文件路径："+name);
		}
			
		Workbook book=Workbook.getWorkbook(new File(name));
		//获得第一个工作表对象
		Sheet sheet =null;
		try{
			sheet=book.getSheet(num);
		}catch (Exception e) {
			// TODO: handle exception
			sheet=book.getSheet(0);
		}
		
		//得到第2行第1列的单元格
		int rows = sheet.getRows();//行
		int columns = sheet.getColumns();//列
		System.out.println("列"+columns);
		ArrayList<String> list =null;
		ArrayList<ArrayList> rowList = new ArrayList<ArrayList>();	
		for(int i =1;i<rows;i++){
			list = new ArrayList<String>();
			for(int j =0;j<columns;j++){
				Cell cell1=sheet.getCell(j,i);
				String result=cell1.getContents();
				System.out.println("行:"+i+"列:"+j+" = "+result);
				list.add(result);
			}
			rowList.add(list);
		}
		book.close();
		this.loadData(rowList, num);
		return true;
		}
		catch(Exception e){
		e.printStackTrace();
		return false;
		}
	}
	
	private boolean loadData(List<ArrayList> rowList,int num){
		 DbUtil util = new DbUtil();
		 boolean flag = false;
	    for(int i =0;i<rowList.size();i++){
	    	ArrayList<String> list = rowList.get(i);	
	    	if(num==0){
				//导入学生信息
				try {
					String sql="INSERT INTO STUDENTINFO " +
					"(ST_ID,ADRESS,ST_NAME,ST_ENAME,HOUSE,SMA_ID,SMB_ID,SMC_ID,SMD_ID)" +
					"values('"+list.get(0)+"','"+list.get(1)+"' ,'"+list.get(2)+"'" +
							",'"+list.get(3)+"','"+list.get(4)+"'" +
									",'"+list.get(5)+"','"+list.get(6)+"'" +
											",'"+list.get(7)+"','"+list.get(8)+"')";
				   flag=util.execute(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					String sql="UPDATE STUDENTINFO " +
					"SET ST_ID='"+list.get(0)+"',"+
						"ADRESS='"+list.get(1)+"',"+
						"ST_NAME='"+list.get(2)+"',"+
						"ST_ENAME='"+list.get(3)+"',"+
						"HOUSE='"+list.get(4)+"',"+
						"SMA_ID='"+list.get(5)+"',"+
						"SMB_ID='"+list.get(6)+"',"+
						"SMC_ID='"+list.get(7)+"',"+
						",SMD_ID='"+list.get(8)+"' " +
								" WHERE ST_ID='"+list.get(0)+"'";
					 try {
						flag=util.execute(sql);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				//生成成绩单初始化数据
				try {
					String sql="INSERT INTO GPAINFO " +
					"(ST_ID,ADDRESS,ST_NAME,ST_ENAME,HOUSE,SM_ID,SR_ID)" +
					"values('"+list.get(0)+"','"+list.get(1)+"' ,'"+list.get(2)+"'" +
							",'"+list.get(3)+"','"+list.get(4)+"'" +
									",'e.g."+list.get(5)+"','A')";
				   flag=util.execute(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					String sql="INSERT INTO GPAINFO " +
					"(ST_ID,ADDRESS,ST_NAME,ST_ENAME,HOUSE,SM_ID,SR_ID)" +
					"values('"+list.get(0)+"','"+list.get(1)+"' ,'"+list.get(2)+"'" +
							",'"+list.get(3)+"','"+list.get(4)+"'" +
									",'e.g."+list.get(6)+"','B')";
				   flag=util.execute(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					String sql="INSERT INTO GPAINFO " +
					"(ST_ID,ADDRESS,ST_NAME,ST_ENAME,HOUSE,SM_ID,SR_ID)" +
					"values('"+list.get(0)+"','"+list.get(1)+"' ,'"+list.get(2)+"'" +
							",'"+list.get(3)+"','"+list.get(4)+"'" +
									",'e.g."+list.get(7)+"','C')";
				   flag=util.execute(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					String sql="INSERT INTO GPAINFO " +
					"(ST_ID,ADDRESS,ST_NAME,ST_ENAME,HOUSE,SM_ID,SR_ID)" +
					"values('"+list.get(0)+"','"+list.get(1)+"' ,'"+list.get(2)+"'" +
							",'"+list.get(3)+"','"+list.get(4)+"'" +
									",'e.g."+list.get(8)+"','D')";
				   flag=util.execute(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if (num==1){
				//导入研讨课信息
				try {
					String sql="INSERT INTO SEMINARINFO " +
					"(SM_ID,SM_NAME,SL_ID,SL_NAME,SL_ENAME,TA_ID,TA_NAME,TA_ENAME,CLS_ID)" +
					"VALUES('"+list.get(0)+"','"+list.get(1)+"' ,'"+list.get(2)+"'" +
							",'','"+list.get(3)+"'" +
									",'"+list.get(4)+"','"+list.get(5)+"'" +
											",'"+list.get(6)+"','"+list.get(7)+"')";
				   flag=util.execute(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					String sql="UPDATE SEMINARINFO " +
					"SET SM_ID='"+list.get(0)+"',"+
						"SM_NAME='"+list.get(1)+"',"+
						"SL_ID='"+list.get(2)+"',"+
						"SL_NAME='"+list.get(3)+"',"+
						"SL_ENAME='',"+
						"TA_ID='"+list.get(4)+"',"+
						"TA_NAME='"+list.get(5)+"',"+
						"TA_ENAME='"+list.get(6)+"',"+
						",CLS_ID='"+list.get(7)+"' " +
								" WHERE SM_ID='"+list.get(0)+"'";
					 try {
						flag=util.execute(sql);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
	    }
		
		return flag;
	}

public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

   doGet(request, response);
}
	
}
