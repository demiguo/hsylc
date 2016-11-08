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
    	body{width:100%;hight:100%;margin:0;padding:0;background-image:url(./image/back.jpg);background-size:100% 100%;}
    	p{
    		font-size:12px;
		font-weight:bold;
		cursor:pointer;
    	        display:block;
    	        outline:none;
	        text-shadow:0 1px 1px #777;
	     }
    	#lb1{
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
		font-size:12px;
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
    		font-size:12px;
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
    		font-size:12px;
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



<script type="text/javascript"> 
	function FormLoad(){ 
         
         <%
          String strUpFile = (String)session.getAttribute("UpFileFlag");
		if( strUpFile == "1" ) {
         %>
			alert("�ļ��ϴ��ɹ�!"); 
         
         <%
              session.setAttribute("UpFileFlag","0");
             
		}else if( strUpFile == "2"){
         %>
			alert("�ļ��ϴ�ʧ��!"); 
          <%
		session.setAttribute("UpFileFlag","0");
               }
          %>
	} 

	
	 	
	
	function downform(){
		window.open('download.jsp');
	}	
</script>

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<!-- ������ -->
<link rel="stylesheet" type="text/css" href="./menu/css/menu.css" />


	<div id="nav">	
<div id="more">
<ul id="menu" class="topmenu">
	<li class="topfirst"><a href="#" title="�û���Ϣ"><span><img src="./menu/image/bnews.png" alt="�û���Ϣ"/>�û���Ϣ</span></a>
	<ul>
		<li id="userinfo"><a  href="#" title="������Ϣ">������Ϣ</a></li>
		<li><a href="#" title="������Ϣ">������Ϣ</a></li>
		<li><a href="#" title="�ǳ�">�ǳ�</a></li>
	</ul>

	</li>
</ul>
</div>
</div>

</head>
<body onload="FormLoad()">
	<div class="stuInfo">
		<form name=upForm action="import" method="post" enctype="multipart/form-data" >
		<br><br> 
		<p>
                <input type="radio" name="state" value="stu" checked/> <label>ѧ�������</label> <input type="radio" name="state" value="sem" /> <label >���ֿ������</label>
                </p>
  	 	<br>
     		<input  type="file" id="upfile" name="importfile" ></input>
     		<button id="read" class="btn" type="submit" >�ϴ�</button>
     		<br>
   		</form>
     	</div>
	<div class="semInfo">
		<form  action="export">
                <p>
     		
                <button id="download" class="btn" type="submit"  onclick="downform();"  title="��ʦ�����">����</button>
                </p>
   
      		</form>
	  
	</div>


</body>
</html>

<html>


