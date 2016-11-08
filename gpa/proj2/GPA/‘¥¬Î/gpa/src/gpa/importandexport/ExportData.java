package gpa.importandexport;



import gpa.common.DbUtil;
import gpa.login.AcountBean;

import java.io.File;
import java.io.IOException;
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
	     download(downloadfile);
	     String logon_fail = "importAndExportInfo.jsp";
	     response.sendRedirect(logon_fail);
	     return;
}

	public void download(String downloadfile){

        try {
            WritableWorkbook wwb = null;
             
               // 创建可写入的Excel工作簿
//               String fileName = "C:/download.xls";
               File file=new File(downloadfile);
               if (!file.exists()) {
                   file.createNewFile();
               }
               //以fileName为文件名来创建一个Workbook
               wwb = Workbook.createWorkbook(file);

               // 创建工作表
               WritableSheet ws = wwb.createSheet("Test Shee 1", 0);
                
       
               //要插入到的Excel表格的行号，默认从0开始
               Label label1= new Label(0, 0, "学生 ID");//表示第
               Label label2= new Label(1, 0, "中文姓名");
               Label label3= new Label(2, 0, "英文名");
               Label label4= new Label(3, 0, "英文名备注");
               Label label5= new Label(4, 0, "House");//表示第
               Label label6= new Label(5, 0, "研讨课 A ID");
               Label label7= new Label(6, 0, "研讨课 A 天 1 出勤");
               Label label8= new Label(7, 0, "研讨课 A天 1 字母成绩等级");
               Label label9= new Label(8, 0, "研讨课 A 天 2 出勤");//表示第
               Label label10= new Label(9, 0, "研讨课 A 天 2 字母成绩等级");
               Label label11= new Label(10, 0, "研讨课 A 天 3 出勤");
               Label label12= new Label(11, 0, "研讨课 A 天 3 字母成绩等级");
               Label label13= new Label(12, 0, "研讨课 A 计算GPA");//表示第
               Label label14= new Label(13, 0, "研讨课 A 覆盖GPA");
               Label label15= new Label(14, 0, "研讨课 A 最终 GPA");
               Label label16= new Label(15, 0, "研讨课 A 研讨会论文成绩");
               
               Label label17= new Label(16, 0, "研讨课 B ID");
               Label label18= new Label(17, 0, "研讨课B 天 1 出勤");
               Label label19= new Label(18, 0, "研讨课 B天 1 字母成绩等级");
               Label label20= new Label(19, 0, "研讨课 B 天 2 出勤");//表示第
               Label label21= new Label(20, 0, "研讨课 B 天 2 字母成绩等级");
               Label label22= new Label(21, 0, "研讨课 B 天 3 出勤");
               Label label23= new Label(22, 0, "研讨课 B 天 3 字母成绩等级");
               Label label24= new Label(23, 0, "研讨课 B 计算GPA");//表示第
               Label label25= new Label(24, 0, "研讨课 B 覆盖GPA");
               Label label26= new Label(25, 0, "研讨课 B 最终 GPA");
               Label label27= new Label(26, 0, "研讨课 B 研讨会论文成绩");
               
               Label label28= new Label(27, 0, "研讨课 C ID");
               Label label29= new Label(28, 0, "研讨课C 天 1 出勤");
               Label label30= new Label(29, 0, "研讨课 C天 1 字母成绩等级");
               Label label31= new Label(30, 0, "研讨课 C 天 2 出勤");//表示第
               Label label32= new Label(31, 0, "研讨课 C 天 2 字母成绩等级");
               Label label33= new Label(32, 0, "研讨课 C 天 3 出勤");
               Label label34= new Label(33, 0, "研讨课 C 天 3 字母成绩等级");
               Label label35= new Label(34, 0, "研讨课 C 计算GPA");//表示第
               Label label36= new Label(35, 0, "研讨课C 覆盖GPA");
               Label label37= new Label(36, 0, "研讨课 C 最终 GPA");
               Label label38= new Label(37, 0, "研讨课 C 研讨会论文成绩");
               
               Label label39= new Label(38, 0, "研讨课 D ID");
               Label label40= new Label(39, 0, "研讨课D 天 1 出勤");
               Label label41= new Label(40, 0, "研讨课D天 1 字母成绩等级");
               Label label42= new Label(41, 0, "研讨课 D 天 2 出勤");//表示第
               Label label43= new Label(42, 0, "研讨课 D 天 2 字母成绩等级");
               Label label44= new Label(43, 0, "研讨课 D 天 3 出勤");
               Label label45= new Label(44, 0, "研讨课D 天 3 字母成绩等级");
               Label label46= new Label(45, 0, "研讨课D 计算GPA");//表示第
               Label label47= new Label(46, 0, "研讨课D 覆盖GPA");
               Label label48= new Label(47, 0, "研讨课D 最终 GPA");
               Label label49= new Label(48, 0, "研讨课D 研讨会论文成绩");
               
               ws.addCell(label1 );
               ws.addCell(label2 );
               ws.addCell(label3 );
               ws.addCell(label4 );
               ws.addCell(label5 );
               ws.addCell(label6 );
               ws.addCell(label7 );
               ws.addCell(label8 );
               ws.addCell(label9 );
               ws.addCell(label10);
               ws.addCell(label11);
               ws.addCell(label12);
               ws.addCell(label13);
               ws.addCell(label14);
               ws.addCell(label15);
               ws.addCell(label16);
               ws.addCell(label17);
               ws.addCell(label18);
               ws.addCell(label19);
               ws.addCell(label20);
               ws.addCell(label21);
               ws.addCell(label22);
               ws.addCell(label23);
               ws.addCell(label24);
               ws.addCell(label25);
               ws.addCell(label26);
               ws.addCell(label27);
               ws.addCell(label28);
               ws.addCell(label29);
               ws.addCell(label30);
               ws.addCell(label31);
               ws.addCell(label32);
               ws.addCell(label33);
               ws.addCell(label34);
               ws.addCell(label35);
               ws.addCell(label36);
               ws.addCell(label37);
               ws.addCell(label38);
               ws.addCell(label39);
               ws.addCell(label40);
               ws.addCell(label41);
               ws.addCell(label42);
               ws.addCell(label43);
               ws.addCell(label44);
               ws.addCell(label45);
               ws.addCell(label46);
               ws.addCell(label47);
               ws.addCell(label48);
               ws.addCell(label49);
               
      		 HashMap<String, HashMap> stu = this.getSTUDENTINFO();
      		 
      		 if(stu!=null&&stu.size()>0){
      			for(String key : stu.keySet())   { 
      				System.out.println("输出学号："+key);
      				System.out.println("输出课程："+stu.get(key).toString());
      				ArrayList<HashMap>  list = this.getgpainfo(key,stu.get(key));
                    for (int i = 0; i < list.size(); i++) {
                    	System.out.println("输出内容："+list.get(i).toString());
                 	    label1= new Label(0, i+1, list.get(i).get("label1").toString());
                         label2= new Label(1, i+1, list.get(i).get("label2").toString());
                         label3= new Label(2, i+1, list.get(i).get("label3").toString());
                         label4= new Label(3, i+1, list.get(i).get("label4").toString());
                         label5= new Label(4, i+1, list.get(i).get("label5").toString());
                         label6= new Label(5, i+1, list.get(i).get("label6").toString());
                         label7= new Label(6, i+1, list.get(i).get("label7").toString());
                         label8= new Label(7, i+1, list.get(i).get("label8").toString());
                         label9= new Label(8, i+1, list.get(i).get("label9").toString());
                         label10= new Label(9, i+1, list.get(i).get("label10").toString());
                         label11= new Label(10, i+1, list.get(i).get("label1").toString());
                         label12= new Label(11, i+1, list.get(i).get("label1").toString());
                         label13= new Label(12, i+1, list.get(i).get("label1").toString());
                         label14= new Label(13, i+1, list.get(i).get("label14").toString());
                         label15= new Label(14, i+1, list.get(i).get("label15").toString());
                         label16= new Label(15, i+1, list.get(i).get("label16").toString());                             
                         label17= new Label(16, i+1, list.get(i).get("label17").toString());
                         label18= new Label(17, i+1, list.get(i).get("label18").toString());
                         label19= new Label(18, i+1, list.get(i).get("label19").toString());
                         label20= new Label(19, i+1, list.get(i).get("label20").toString());
                         label21= new Label(20, i+1, list.get(i).get("label21").toString());
                         label22= new Label(21, i+1, list.get(i).get("label22").toString());
                         label23= new Label(22, i+1, list.get(i).get("label23").toString());
                         label24= new Label(23, i+1, list.get(i).get("label24").toString());
                         label25= new Label(24, i+1, list.get(i).get("label25").toString());
                         label26= new Label(25, i+1, list.get(i).get("label26").toString());
                         label27= new Label(26, i+1, list.get(i).get("label27").toString());                                    
                         label28= new Label(27, i+1, list.get(i).get("label28").toString());
                         label29= new Label(28, i+1, list.get(i).get("label29").toString());
                         label30= new Label(29, i+1, list.get(i).get("label30").toString());
                         label31= new Label(30, i+1, list.get(i).get("label31").toString());
                         label32= new Label(31, i+1, list.get(i).get("label32").toString());
                         label33= new Label(32, i+1, list.get(i).get("label33").toString());
                         label34= new Label(33, i+1, list.get(i).get("label34").toString());
                         label35= new Label(34, i+1, list.get(i).get("label35").toString());
                         label36= new Label(35, i+1, list.get(i).get("label36").toString());
                         label37= new Label(36, i+1, list.get(i).get("label37").toString());
                         label38= new Label(37, i+1, list.get(i).get("label38").toString());                                    
                         label39= new Label(38, i+1, list.get(i).get("label39").toString());
                         label40= new Label(39, i+1, list.get(i).get("label40").toString());
                         label41= new Label(40, i+1, list.get(i).get("label41").toString());
                         label42= new Label(41, i+1, list.get(i).get("label42").toString());
                         label43= new Label(42, i+1, list.get(i).get("label43").toString());
                         label44= new Label(43, i+1, list.get(i).get("label44").toString());
                         label45= new Label(44, i+1, list.get(i).get("label45").toString());
                         label46= new Label(45, i+1, list.get(i).get("label46").toString());
                         label47= new Label(46, i+1, list.get(i).get("label47").toString());
                         label48= new Label(47, i+1, list.get(i).get("label48").toString());
                         label49= new Label(48, i+1, list.get(i).get("label49").toString());
                        
                        ws.addCell(label1 );
                        ws.addCell(label2 );
                        ws.addCell(label3 );
                        ws.addCell(label4 );
                        ws.addCell(label5 );
                        ws.addCell(label6 );
                        ws.addCell(label7 );
                        ws.addCell(label8 );
                        ws.addCell(label9 );
                        ws.addCell(label10);
                        ws.addCell(label11);
                        ws.addCell(label12);
                        ws.addCell(label13);
                        ws.addCell(label14);
                        ws.addCell(label15);
                        ws.addCell(label16);
                        ws.addCell(label17);
                        ws.addCell(label18);
                        ws.addCell(label19);
                        ws.addCell(label20);
                        ws.addCell(label21);
                        ws.addCell(label22);
                        ws.addCell(label23);
                        ws.addCell(label24);
                        ws.addCell(label25);
                        ws.addCell(label26);
                        ws.addCell(label27);
                        ws.addCell(label28);
                        ws.addCell(label29);
                        ws.addCell(label30);
                        ws.addCell(label31);
                        ws.addCell(label32);
                        ws.addCell(label33);
                        ws.addCell(label34);
                        ws.addCell(label35);
                        ws.addCell(label36);
                        ws.addCell(label37);
                        ws.addCell(label38);
                        ws.addCell(label39);
                        ws.addCell(label40);
                        ws.addCell(label41);
                        ws.addCell(label42);
                        ws.addCell(label43);
                        ws.addCell(label44);
                        ws.addCell(label45);
                        ws.addCell(label46);
                        ws.addCell(label47);
                        ws.addCell(label48);
                        ws.addCell(label49);
                    }
      			                   
      			}  
      		 }
      		 
      		
             
              //写进文档
               wwb.write();
              // 关闭Excel工作簿对象
               wwb.close();
             
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
	    			sem.put("SMA_ID", "e.g."+list.get(i).get("SMA_ID").toString());
	    			sem.put("SMB_ID", "e.g."+list.get(i).get("SMB_ID").toString());
	    			sem.put("SMC_ID", "e.g."+list.get(i).get("SMC_ID").toString());
	    			sem.put("SMD_ID", "e.g."+list.get(i).get("SMD_ID").toString());
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
