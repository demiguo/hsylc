package gpa.importandexport;

import gpa.common.DbUtil;
import gpa.login.AcountBean;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
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

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


/**
 * 导入数据
 * @author Administrator
 *
 */
public class ImportData extends HttpServlet {
	
	
	private  String state="";
	private  String importfile="";
	private String rootPath="";
	
	private Workbook book = null;
	public static String UpFileFlag ="";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

	    HttpSession session = request.getSession();
	    
 
	    this.uploadfiles(request);
	   
	    System.out.println("获取state："+state);
	    if("stu".equalsIgnoreCase(state)){
	   	//学生输入表
	   	 if(upfile(book, 0)){
	   		 System.out.println("导入成功");
	   		UpFileFlag="1";
	   	 }else{
	   		 System.out.println("导入失败");
	   		UpFileFlag="2";
	   	 }
	    }else  if("sem".equalsIgnoreCase(state)){
	   	//研讨课输入表 
	   	 if(upfile(book, 1)){
	   		 System.out.println("导入成功");
	   		UpFileFlag="1";
	   		
	   	 }else{
	   		 System.out.println("导入失败");
	   		UpFileFlag="2";
	   		
	   	 }
	   	 
	    }else{
	   	 System.out.println("读取选择框出错");
	    }
	    session.setAttribute("UpFileFlag", UpFileFlag);
	    String logon_suc = "importAndExportInfo.jsp";
	    response.sendRedirect(logon_suc);
	    return;

	}
	
	
	public boolean upfile(Workbook book,int num){
		boolean flag = false;
		try{
			
		//获得第一个工作表对象
		Sheet sheet =null;
		try{
			sheet=book.getSheet(num);
		}catch (Exception e) {
			// TODO: handle exception
			sheet=book.getSheet(0);
			
		}
		
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
		
		this.loadData(rowList, num);
		flag= true;
		}
		catch(Exception e){
		e.printStackTrace();
		flag = false;
		}finally{
			if(book!=null){
				book.close();
				book =null;
			}
			return flag;
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
						System.out.println(e1.getMessage());
					}
				}
				//生成成绩单初始化数据
				try {
					String sql="INSERT INTO GPAINFO " +
					"(ST_ID,ADDRESS,ST_NAME,ST_ENAME,HOUSE,SM_ID,SR_ID)" +
					"values('"+list.get(0)+"','"+list.get(1)+"' ,'"+list.get(2)+"'" +
							",'"+list.get(3)+"','"+list.get(4)+"'" +
									",'"+list.get(5)+"','A')";
				   flag=util.execute(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				try {
					String sql="INSERT INTO GPAINFO " +
					"(ST_ID,ADDRESS,ST_NAME,ST_ENAME,HOUSE,SM_ID,SR_ID)" +
					"values('"+list.get(0)+"','"+list.get(1)+"' ,'"+list.get(2)+"'" +
							",'"+list.get(3)+"','"+list.get(4)+"'" +
									",'"+list.get(6)+"','B')";
				   flag=util.execute(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				try {
					String sql="INSERT INTO GPAINFO " +
					"(ST_ID,ADDRESS,ST_NAME,ST_ENAME,HOUSE,SM_ID,SR_ID)" +
					"values('"+list.get(0)+"','"+list.get(1)+"' ,'"+list.get(2)+"'" +
							",'"+list.get(3)+"','"+list.get(4)+"'" +
									",'"+list.get(7)+"','C')";
				   flag=util.execute(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				try {
					String sql="INSERT INTO GPAINFO " +
					"(ST_ID,ADDRESS,ST_NAME,ST_ENAME,HOUSE,SM_ID,SR_ID)" +
					"values('"+list.get(0)+"','"+list.get(1)+"' ,'"+list.get(2)+"'" +
							",'"+list.get(3)+"','"+list.get(4)+"'" +
									",'"+list.get(8)+"','D')";
				   flag=util.execute(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
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
					String sql="UPDATE SEMINARINFO " +
					"SET SM_ID='"+list.get(0)+"',"+
						"SM_NAME='"+list.get(1)+"',"+
						"SL_ID='"+list.get(2)+"',"+
						"SL_NAME='"+list.get(3)+"',"+
						"SL_ENAME='',"+
						"TA_ID='"+list.get(4)+"',"+
						"TA_NAME='"+list.get(5)+"',"+
						"TA_ENAME='"+list.get(6)+"',"+
						"CLS_ID='"+list.get(7)+"' " +
								" WHERE SM_ID='"+list.get(0)+"'";
					 try {
						flag=util.execute(sql);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						System.out.println(e1.getMessage());
					}
				}
				

				//导入讲师信息
				try {
					String sql="INSERT INTO USERINFO " +
					"(USER_ID,USER_PASSWD,USER_TYPE)" +
					"VALUES('"+list.get(2)+"','123' ,'1')";
				   flag=util.execute(sql);
				} catch (SQLException e) {
                   System.out.println(e.getMessage());
				}
				//导入助教信息
				try {
					String sql="INSERT INTO USERINFO " +
					"(USER_ID,USER_PASSWD,USER_TYPE)" +
					"VALUES('"+list.get(4)+"','123' ,'2')";
				   flag=util.execute(sql);
				} catch (SQLException e) {
                   System.out.println(e.getMessage());
				}
				
			
			
			
			}
	    }
		
		return flag;
	}


	
	
public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
	
	doGet(request, response);
}


public void uploadfiles(HttpServletRequest request){
	String tempPath = "";

	 //代表一个EXCEL文件
	  try { 
		  	String remoteAddr = request.getRemoteAddr();
			//获得服务器的名字
			String serverName = request.getServerName();
		    System.out.println("客户端："+remoteAddr);
		    System.out.println("服务端："+serverName);
			//取得互联网程序的绝对地址
			String realPath = request.getRealPath(serverName);
			System.out.println("服务端绝对路径："+realPath);
			
			realPath = realPath.substring(0,realPath.lastIndexOf("\\"));
			//创建文件的保存目录
			rootPath = realPath + "\\upload\\";
	      DiskFileUpload fu = new DiskFileUpload(); // 设置最大文件尺寸，这里是4MB

	      fu.setSizeMax(4194304); // 设置缓冲区大小，这里是4kb 
	      fu.setSizeThreshold(4096); // 设置临时目录：
	      fu.setRepositoryPath(rootPath); // 得到所有的文件：
	      List fileItems = fu.parseRequest(request);
	      Iterator i = fileItems.iterator(); // 依次处理每一个文件

	      while(i.hasNext()) {
	         FileItem fi = (FileItem)i.next(); // 获得文件名，这个文件名包括路径：
	         if(!fi.isFormField()){
	        	 String fileName = fi.getName(); // 在这里可以记录用户和文件信息        
	             System.out.println("输出文件名："+fileName);
		         book = Workbook.getWorkbook(fi.getInputStream());
		         if(book == null){
		          return;
		         }
		      } else{
		    	  System.out.println("输出表单内容"+ fi.getFieldName()+"||"+fi.getString());
		    	  if("state".equalsIgnoreCase(fi.getFieldName())){
		    		  state = fi.getString();
		    	  }
		    	
		    	 
		      }
		    
	         }
	         
	        
	  } catch(Exception e){ 
		 e.printStackTrace();
		 UpFileFlag="2";
	  }finally{
		  
	  }
}


	
}
