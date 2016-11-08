<%@ page language="java" import="java.util.*,java.sql.*,java.io.*,javax.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<base href="<%=basePath%>">
	<meta charset="UTF-8"> 
	<title>GPA</title>
 	<style type="text/css">
    	
    	html{color:#000;}
    	body{width:100%;hight:100%;margin:0;padding:0;background-image:url(./image/back.jpg);background-size:100% 100%;}
    	p{
    		font-size:12px;
		font-weight:bold;
		cursor:pointer;
    	  	display:block;
    	  	outline:none;
	        text-shadow:0 1px 1px #777;
	}
			
		.ipt {border:1px solid #ddd;padding:10px 5px;width: 500px;outline:none;border-radius:3px;background-color:#fff;font-family: 'revolt',georgia,'Microsoft yahei';}
		.ipt:focus {background-color:#fefefe;box-shadow:0 0 3px #aaa;}
		.semInfo {
			width:1200px;
			margin:10px auto auto;
			padding:30px 40px;
		
		}
		#download {
    	width:100px;height:30px;
    	float:left;
    	background-color:#4797ED;
    	border:none;
    	border-radius:10px;
    	box-shadow:0 0 3px #aaa; 	
    	line-height:30px;color:#fff;
    	font-size:12px;
    	font-weight:bold;
    	cursor:pointer;
    	margin-left:90px;
    	display:block;
    	outline:none;
	    text-shadow:0 1px 1px #777;
	    }
		 #download :hover {
			background-color:#d8d8d8;
			color:#666;
			text-shadow:1px 1px 1px #fff;
			}
    </style>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<!-- 导航栏 -->
<link rel="stylesheet" type="text/css" href="./menu/css/menu.css" />

<!--表单样式-->


<link rel="stylesheet" type="text/css" href="./from/css/component.css" />
 <script src="js/jquery.min.js"></script>
<script src="js/jquery.ba-throttle-debounce.min.js"></script>
<script src="js/jquery.stickyheader.js"></script>
<script>
	
  function send(){

      alert("提交成功");
       
     
      
   }	
	
</script>

<div id="nav">	
<div id="more">
<ul id="menu" class="topmenu">
	<li class="topfirst"><a href="#" title="用户信息"><span><img src="./menu/image/bnews.png" alt="用户信息"/>用户信息</span></a>
	<ul>
		<li id="userinfo"><a  href="#" title="个人信息">个人信息</a></li>
		<li><a href="#" title="其他信息">其他信息</a></li>
		<li><a href="#" title="登出">登出</a></li>
	</ul>

	</li>
</ul>
</div>
</div>

</head>
<body>

</body>
<div class="container">
	
	<div class="component">
		<div class="semInfo">
		<form id ="olsub" action="online">
		<table id="tb">
			<thead>
				<tr>
					<th>研讨课 ID</th>
					<th>学期</th>
					<th>学生 ID</th>
					<th>中文名</th>
					<th>英文名</th>
					<th>英文名备注</th>
					<th>House</th>
					<th>第一天出勤</th>
					<th>第一天成绩等级</th>
					<th>第二天出勤</th>
					<th>第二天成绩等级</th>
					<th>第三天出勤</th>
					<th>第三天成绩等级</th>
					<th>计算出的 GPA</th>
					<th>覆盖的 GPA</th>
					<th>最终的 GPA</th>
					<th>研讨课论文成绩</th>
				</tr>
			</thead>
	
	<% 

   try 
   { 
   
   
   request.getParameter("username"); 
  
   Class.forName(gpa.common.DbUtil.name).newInstance(); 
   
  gpa.common.DbUtil util = new gpa.common.DbUtil();
	     gpa.login.AcountBean account =(gpa.login.AcountBean) session.getAttribute("account");
	     String username = account.getUsername();
	     String user_type = account.getUsertype();
	     System.out.println("用户类型："+user_type+"用户名："+username);

   Connection con  = java.sql.DriverManager.getConnection(util.url+"?useUnicode=true" + "&characterEncoding=gbk&user="+util.user+"&password="+util.password); 
Statement  stmt=con.createStatement(); 
String sql = "select g.* from gpainfo g,seminarinfo s where  g.sm_id=s.sm_id and (s.sl_id = '"+username+"' or s.ta_id='"+username+"') order by sm_id asc"; 

ResultSet  rst=stmt.executeQuery(sql); 

         



int i=0;
while (rst.next()) 
{
i++;
 %>

<tr>
  <td><input name="SM_ID<%=i%>"  type="text" value="<%=rst.getString("SM_ID") %>" readonly ></td>
  <td name="SR_ID<%=i%>" ><%=rst.getString("SR_ID") %></td>
  <td><input name="ST_ID<%=i%>"  type="text" value="<%=rst.getString("ST_ID") %>" readonly></td>
   <td name="ST_NAME<%=i%>" ><%=rst.getString("ST_NAME") %></td>
  <td name="ST_ENAME<%=i%>" ><%=rst.getString("ST_ENAME") %></td>
  <td><input name="ST_ENAME_DESC<%=i%>"  type="text" value="<%=rst.getString("ST_ENAME_DESC") %>"></td>

  <td name="HOUSE<%=i%>" ><%=rst.getString("HOUSE") %></td>
  <td><select id="sel" name="FSTDAY_CHECK<%=i%>" ><option selected="selected"><%=rst.getString("FSTDAY_CHECK") %></option><option  value="yes">yes</option><option value="no">no</option></select></td>
  <td><select id="sel" name="FSTDAY_SCORE<%=i%>" ><option selected="selected"><%=rst.getString("FSTDAY_SCORE") %></option><option  value="A+">A+</option><option value="A">A</option><option value="A-">A-</option><option value="B+">B+</option><option value="B">B</option><option value="B-">B-</option><option value='C+'>C+</option><option value='C'>C</option><option value='F'>F</option></select></td>
   <td><select id="sel" name="SECTDAY_CHECK<%=i%>" ><option selected="selected"><%=rst.getString("SECTDAY_CHECK") %></option><option  value="yes">yes</option><option value="no">no</option></select></td>
  <td><select id="sel" name="SECDAY_SCORE<%=i%>" ><option selected="selected"><%=rst.getString("SECDAY_SCORE") %></option><option  value="A+">A+</option><option value="A">A</option><option value="A-">A-</option><option value="B+">B+</option><option value="B">B</option><option value="B-">B-</option><option value='C+'>C+</option><option value='C'>C</option><option value='F'>F</option></select></td>
   <td><select id="sel" name="THRDAY_CHECK<%=i%>" ><option selected="selected"><%=rst.getString("THRDAY_CHECK") %></option><option  value="yes">yes</option><option value="no">no</option></select></td>
  <td><select id="sel" name="THRDAY_SCORE<%=i%>" ><option selected="selected"><%=rst.getString("THRDAY_SCORE") %></option><option  value="A+">A+</option><option value="A">A</option><option value="A-">A-</option><option value="B+">B+</option><option value="B">B</option><option value="B-">B-</option><option value='C+'>C+</option><option value='C'>C</option><option value='F'>F</option></select></td>
  <td name="CALCULATE_GPA<%=i%>" ><%=rst.getString("CALCULATE_GPA") %></td>
  <td  ><select id="sel" name="OVER_GPA<%=i%>" ><option selected="selected"><%=rst.getString("OVER_GPA") %></option><option  value="A+">A+</option><option value="A">A</option><option value="A-">A-</option><option value="B+">B+</option><option value="B">B</option><option value="B-">B-</option><option value='C+'>C+</option><option value='C'>C</option><option value='F'>F</option></select></td> 
  <td name="FINAL_GPA<%=i%>" ><%=rst.getString("FINAL_GPA") %></td>
  <td><select id="sel" name="PAPER_SCORE<%=i%>" ><option selected="selected"><%=rst.getString("PAPER_SCORE") %></option><option  value="A+">A+</option><option value="A">A</option><option value="A-">A-</option><option value="B+">B+</option><option value="B">B</option><option value="B-">B-</option><option value='C+'>C+</option><option value='C'>C</option><option value='F'>F</option></select></td> 
</tr> 

<%
} 
rst.close(); 
stmt.close(); 
con.close(); 
} 
catch(Exception  e ) 
{ 
e.printStackTrace(); 
}


%> 
</table>
</div>
</div>
     <p><button id="download" class="btn" type="submit"  onclick="send();" >提交</button>
   
     </p>
     
     
 </form>
</div>
</html>

<html>


