package gpa.importandexport;



import gpa.common.DbUtil;
import gpa.login.AcountBean;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * 导出数据
 * @author Administrator
 *
 */
public class ExportData extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
	     HttpSession session = request.getSession();
	     AcountBean account = new AcountBean();
	     String username = request.getParameter("username");
	     String pwd = request.getParameter("pwd");
	     String downloadfile=request.getParameter("downloadfile");
	     account.setUsername(username);
	     account.setPassword(pwd);
	     System.out.println("下载文件："+downloadfile);
	     
	     String msg = "";
	     if(download(request)){
	    	 msg = "下载成功";
	    	 
	     }else{
	    	 msg = "下载失败";
	     }
	     String logon_fail = "importAndExportInfo.jsp";
	     response.sendRedirect(logon_fail);
	     return;
}
 

	
	
	public boolean download(HttpServletRequest request){
		WritableWorkbook wwb = null;
		boolean flag = false;
        try {
        	String serverName = request.getServerName();
        	//取得互联网程序的绝对地址
        	String realPath = request.getRealPath(serverName);
        	System.out.println("服务端绝对路径："+realPath);
        	
        	realPath = realPath.substring(0,realPath.lastIndexOf("\\"));
        	//创建文件的保存目录
        	String rootPath = realPath + "\\download\\";
             
               String downloadfile=rootPath+"download.xls";
			// 创建可写入的Excel工作簿
//               String fileName = "C:/download.xls";
               File file=new File(downloadfile);
               if (!file.exists()) {
                   file.createNewFile();
               }
               //以fileName为文件名来创建一个Workbook
               wwb = Workbook.createWorkbook(file);

               // 创建工作表
               WritableSheet ws = wwb.createSheet("教师输出表", 0);
                
               String[] labels = {"学生 ID","中文姓名","英文名","英文名备注","House","研讨课 A ID","研讨课 A 天 1 出勤","研讨课 A天 1 字母成绩等级","研讨课 A 天 2 出勤",
            		   "研讨课 A 天 2 字母成绩等级","研讨课 A 天 3 出勤","研讨课 A 天 3 字母成绩等级","研讨课 A 计算GPA",
            		   "研讨课 A 覆盖GPA","研讨课 A 最终 GPA","研讨课 A 研讨会论文成绩","研讨课 B ID","研讨课B 天 1 出勤","研讨课 B天 1 字母成绩等级","研讨课 B 天 2 出勤",
            		   " 研讨课 B 天 2 字母成绩等级","研讨课 B 天 3 出勤","研讨课 B 天 3 字母成绩等级","研讨课 B 计算GPA","研讨课 B 覆盖GPA","研讨课 B 最终 GPA",
            		   "研讨课 B 研讨会论文成绩","研讨课 C ID","研讨课C 天 1 出勤","研讨课 C天 1 字母成绩等级","研讨课 C 天 2 出勤","研讨课 C 天 2 字母成绩等级",
            		   "研讨课 C 天 3 出勤","研讨课 C 天 3 字母成绩等级","研讨课 C 计算GPA","研讨课C 覆盖GPA","研讨课 C 最终 GPA","研讨课 C 研讨会论文成绩","研讨课 D ID",
            		   "研讨课D 天 1 出勤","研讨课D天 1 字母成绩等级","研讨课 D 天 2 出勤","研讨课 D 天 2 字母成绩等级","研讨课 D 天 3 出勤","研讨课D 天 3 字母成绩等级",
            		   "研讨课D 计算GPA"," 研讨课D 覆盖GPA","研讨课D 最终 GPA","研讨课D 研讨会论文成绩"};
               //要插入到的Excel表格的行号，默认从0开始
               for(int i=0;i<labels.length;i++){
            	   Label label= new Label(i, 0, labels[i]);
            	   ws.addCell(label);
            	 }
//               
               
      		 HashMap<String, HashMap> stu = this.getSTUDENTINFO();
      		 int number = 0;
      		 if(stu!=null&&stu.size()>0){
      			for(String key : stu.keySet())   { 
      				System.out.println("输出学号："+key);
      				System.out.println("输出课程："+stu.get(key).toString());
      				ArrayList<HashMap>  list = this.getgpainfo(key,stu.get(key));
      				number++;
                    for (int i = 0; i < list.size(); i++) {
                    	System.out.println("序号："+number+"输出内容："+list.get(i).toString());
                    	 HashMap<String,String> map = list.get(i);
                    	 for(int j=0;j<map.size();j++){
                    		 String cell = "label"+String.valueOf(j+1);
                    		 Label label = new Label(j, number, list.get(i).get(cell).toString());
                    		 ws.addCell(label);
                    	 }
                    	
          
                    }
                         
      			}  
      		 }
      		 
      		
      	      //写进文档
             wwb.write();      
            
               flag= true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            flag= false;
        } finally{
        	if(wwb!=null){
        		  try {
					wwb.close();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					wwb=null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					wwb=null;
				}
        	}
        	return flag;
        }
    
	}
	
	
	 //查询学生课程
    public  HashMap<String, HashMap> getSTUDENTINFO(){
		 
		 HashMap<String, HashMap> stu = new HashMap<String, HashMap>();
		 DbUtil util = new DbUtil();
	     String sql="SELECT * FROM STUDENTINFO";
	     ArrayList<HashMap> list = null;
	     try {
	    	 list=util.select(sql);
	    	 if(list!=null&&list.size()>0){
	    		for(int i=0;i<list.size();i++){
	    			HashMap<String, String> sem = new HashMap<String, String>();
	    			sem.put("SMA_ID", list.get(i).get("SMA_ID").toString());
	    			sem.put("SMB_ID", list.get(i).get("SMB_ID").toString());
	    			sem.put("SMC_ID", list.get(i).get("SMC_ID").toString());
	    			sem.put("SMD_ID", list.get(i).get("SMD_ID").toString());
	    			stu.put(list.get(i).get("ST_ID").toString(), sem);
	    			
	    		}
	    	 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stu;
	 }
    
    public ArrayList<HashMap> getgpainfo(String st_id,HashMap<String, String> sem){
    	 DbUtil util = new DbUtil();
    	 String sqla =" select ga.ST_ID,ga.ST_NAME,ga.ST_ENAME ,ga.ST_ENAME_DESC,ga.HOUSE,ga.SM_ID,ga.FSTDAY_CHECK ,ga.FSTDAY_SCORE ,ga.SECTDAY_CHECK  ,ga.SECDAY_SCORE  ,ga.THRDAY_CHECK ,ga.THRDAY_SCORE ,ga.CALCULATE_GPA ,ga.OVER_GPA ,ga.FINAL_GPA ,ga.PAPER_SCORE  fROM gpainfo ga  where ga.st_id='"+st_id+"' and ga.sm_id = '"+sem.get("SMA_ID")+"'";
    	 String sqlb ="select gb.SM_ID ,gb.FSTDAY_CHECK ,gb.FSTDAY_SCORE  ,gb.SECTDAY_CHECK ,gb.SECDAY_SCORE ,gb.THRDAY_CHECK  ,gb.THRDAY_SCORE ,gb.CALCULATE_GPA  ,gb.OVER_GPA  ,gb.FINAL_GPA  ,gb.PAPER_SCORE  fROM gpainfo gb  where gb.st_id='"+st_id+"' and gb.sm_id = '"+sem.get("SMB_ID")+"'";
    	 String sqlc ="select gb.SM_ID ,gb.FSTDAY_CHECK ,gb.FSTDAY_SCORE  ,gb.SECTDAY_CHECK ,gb.SECDAY_SCORE ,gb.THRDAY_CHECK  ,gb.THRDAY_SCORE ,gb.CALCULATE_GPA  ,gb.OVER_GPA  ,gb.FINAL_GPA  ,gb.PAPER_SCORE  fROM gpainfo gb  where gb.st_id='"+st_id+"' and gb.sm_id = '"+sem.get("SMC_ID")+"'";
    	 String sqld =" select gb.SM_ID ,gb.FSTDAY_CHECK ,gb.FSTDAY_SCORE  ,gb.SECTDAY_CHECK ,gb.SECDAY_SCORE ,gb.THRDAY_CHECK  ,gb.THRDAY_SCORE ,gb.CALCULATE_GPA  ,gb.OVER_GPA  ,gb.FINAL_GPA  ,gb.PAPER_SCORE  fROM gpainfo gb  where gb.st_id='"+st_id+"' and gb.sm_id = '"+sem.get("SMD_ID")+"'";
         ArrayList<HashMap> list =new ArrayList<HashMap>();
         HashMap map = new HashMap();
		try {
			ArrayList<HashMap> lista =(util.select(sqla));
			if(lista!=null&&lista.size()>0){
				map.put("label1",lista.get(0).get("ST_ID").toString());
				map.put("label2",lista.get(0).get("ST_NAME").toString());
				map.put("label3",lista.get(0).get("ST_ENAME").toString());
				map.put("label4",lista.get(0).get("ST_ENAME_DESC").toString());
				map.put("label5",lista.get(0).get("HOUSE").toString());
				map.put("label6",lista.get(0).get("SM_ID").toString());
				map.put("label7",lista.get(0).get("FSTDAY_CHECK").toString());
				map.put("label8",lista.get(0).get("FSTDAY_SCORE").toString());
				map.put("label9",lista.get(0).get("SECTDAY_CHECK").toString());
				map.put("label10",lista.get(0).get("SECDAY_SCORE").toString());
				map.put("label11",lista.get(0).get("THRDAY_CHECK").toString());
				map.put("label12",lista.get(0).get("THRDAY_SCORE").toString());
				map.put("label13",lista.get(0).get("CALCULATE_GPA").toString());
				map.put("label14",lista.get(0).get("OVER_GPA").toString());
				map.put("label15",lista.get(0).get("FINAL_GPA").toString());
				map.put("label16",lista.get(0).get("PAPER_SCORE").toString());
			}
			lista = null;
			lista =(util.select(sqlb));
			if(lista!=null&&lista.size()>0){
				map.put("label17",lista.get(0).get("SM_ID").toString());
				map.put("label18",lista.get(0).get("FSTDAY_CHECK").toString());
				map.put("label19",lista.get(0).get("FSTDAY_SCORE").toString());
				map.put("label20",lista.get(0).get("SECTDAY_CHECK").toString());
				map.put("label21",lista.get(0).get("SECDAY_SCORE").toString());
				map.put("label22",lista.get(0).get("THRDAY_CHECK").toString());
				map.put("label23",lista.get(0).get("THRDAY_SCORE").toString());
				map.put("label24",lista.get(0).get("CALCULATE_GPA").toString());
				map.put("label25",lista.get(0).get("OVER_GPA").toString());
				map.put("label26",lista.get(0).get("FINAL_GPA").toString());
				map.put("label27",lista.get(0).get("PAPER_SCORE").toString());
			}
			lista = null;
			lista =(util.select(sqlc));
			if(lista!=null&&lista.size()>0){
				map.put("label28",lista.get(0).get("SM_ID").toString());
				map.put("label29",lista.get(0).get("FSTDAY_CHECK").toString());
				map.put("label30",lista.get(0).get("FSTDAY_SCORE").toString());
				map.put("label31",lista.get(0).get("SECTDAY_CHECK").toString());
				map.put("label32",lista.get(0).get("SECDAY_SCORE").toString());
				map.put("label33",lista.get(0).get("THRDAY_CHECK").toString());
				map.put("label34",lista.get(0).get("THRDAY_SCORE").toString());
				map.put("label35",lista.get(0).get("CALCULATE_GPA").toString());
				map.put("label36",lista.get(0).get("OVER_GPA").toString());
				map.put("label37",lista.get(0).get("FINAL_GPA").toString());
				map.put("label38",lista.get(0).get("PAPER_SCORE").toString());
			}
			lista = null;
			lista =(util.select(sqld));
			if(lista!=null&&lista.size()>0){
				map.put("label39",lista.get(0).get("SM_ID").toString());
				map.put("label40",lista.get(0).get("FSTDAY_CHECK").toString());
				map.put("label41",lista.get(0).get("FSTDAY_SCORE").toString());
				map.put("label42",lista.get(0).get("SECTDAY_CHECK").toString());
				map.put("label43",lista.get(0).get("SECDAY_SCORE").toString());
				map.put("label44",lista.get(0).get("THRDAY_CHECK").toString());
				map.put("label45",lista.get(0).get("THRDAY_SCORE").toString());
				map.put("label46",lista.get(0).get("CALCULATE_GPA").toString());
				map.put("label47",lista.get(0).get("OVER_GPA").toString());
				map.put("label48",lista.get(0).get("FINAL_GPA").toString());
				map.put("label49",lista.get(0).get("PAPER_SCORE").toString());
			}
			list.add(map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return list;
    }
	
public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

   doGet(request, response);
}
	
}
