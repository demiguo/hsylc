<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   <meta charset="GBK"> 
    <title>GPA</title>
    <style type="text/css">
    	
    	html{color:#000;}
    	body{width:100%;hight:100%;margin:0;padding:0;background-color:#F2F2F2;}
    	p{
    		font-size:18px;
		    font-weight:bold;
		    cursor:pointer;
    	  display:block;
    	  outline:none;
	      text-shadow:0 1px 1px #777;
	     }
		.stuInfo {
			width:700px;
			margin:100px auto auto;
			padding:30px 40px;
		
		}
			.semInfo {
			width:700px;
			margin:10px auto auto;
			padding:30px 40px;
		
		}
		
		
		#upfile {
			
		width:500px;height:30px;
		float:left;
		border:none;
		border-radius:10px;
		box-shadow:0 0 3px #aaa;
		line-height:30px;color:#F2F2F2;
		font-size:18px;
		font-weight:bold;
		cursor:pointer;
    	margin-right:20px;
    	display:block;
    	outline:none;
	    text-shadow:0 1px 1px #777;
		}
		#upfile:hover {
			background-color:#d8d8d8;
			color:#666;
			text-shadow:1px 1px 1px #fff;
		}
    
    #read {
    	width:100px;height:30px;
    	left:auto;
    	background-color:#4797ED;
    	border:none;
    	border-radius:10px;
    	box-shadow:0 0 3px #aaa; 	
    	line-height:30px;color:#fff;
    	font-size:18px;
    	font-weight:bold;
    	cursor:pointer;
    	margin-right:20px;
    	display:block;
    	outline:none;
	    text-shadow:0 1px 1px #777;
	    }
		 #read :hover {
			background-color:#d8d8d8;
			color:#666;
			text-shadow:1px 1px 1px #fff;
			}
			
			#download {
    	width:100px;height:30px;
    	float:right;
    	background-color:#4797ED;
    	border:none;
    	border-radius:10px;
    	box-shadow:0 0 3px #aaa; 	
    	line-height:30px;color:#fff;
    	font-size:18px;
    	font-weight:bold;
    	cursor:pointer;
    	margin-right:80px;
    	display:block;
    	outline:none;
	    text-shadow:0 1px 1px #777;
	    }
		 #download :hover {
			background-color:#d8d8d8;
			color:#666;
			text-shadow:1px 1px 1px #fff;
			}
			
		.ipt {border:1px solid #ddd;padding:10px 5px;width: 500px;outline:none;border-radius:3px;background-color:#fff;font-family: 'revolt',georgia,'Microsoft yahei';}
		.ipt:focus {background-color:#fefefe;box-shadow:0 0 3px #aaa;}
		
			
    </style>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<!-- 导航栏 -->
<link rel="stylesheet" type="text/css" href="./menu/css/menu.css" />

 

<body>
	<div id="nav">	
<div id="more">
<ul id="menu" class="topmenu">
	<li class="topfirst"><a href="#" title="用户信息"><span><img src="./menu/image/21-img2ico.net.ico" alt="用户信息"/>用户信息</span></a>
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
	<div class="stuInfo">
		<form action="import">
		<p><input type="radio" name="state" value="stu" 〈%if（state.equal("stu")） out.print("checked")%〉/><label >学生输入表</label><input type="radio" name="state" value="sem" 〈%if（state.equal("sem")） out.print("checked")%〉/><label >研讨课输入表</label></p>
  	 <br>
  	 <br>
     <input  type="file" id="upfile" name="importfile"></input>
     <button id="read" class="btn" type="submit">上传</button>
     <br>
   </form>
     </div>
	<div class="semInfo">
		<form action="export">
     <p><label>教师输出表</label><p></p><input type="text" value="C:\download.xls" name="downloadfile" class="ipt"> <button id="download" class="btn" type="submit" title="教师输出表">下载</button></p>
      </form>
</div>
</body>
</html>

<html>


