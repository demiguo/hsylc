<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
	<title>GPA</title>
	<meta charset="UTF-8">

    <style type="text/css">
		* html,* html body{background-image:url(about:blank);background-attachment:fixed;}
		html{color:#000;background-color:#F2F2F2;}
		body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,form,fieldset,input,textarea,p,blockquote,th,td{margin:0;padding:0;}
		table {border-collapse:collapse;border-spacing:0;}
		img,button,input {vertical-align:middle;border:none;}
		label{font-size:12px;color:#666;cursor:pointer;}
		a,span{color:#666;font-size:12px;text-decoration:none;}
		a:hover{text-decoration:underline;}
		address,caption,cite,code,dfn,em,strong,th,var{font-style:normal;font-weight:normal;}
		ol,ul,li{list-style:none;list-style-type: none;}
		h1,h2,h3,h4,h5,h6{font-size:100%;overflow:hidden;}
		
    	header {width:660px;margin:0 auto;font-size:28px;font-weight:bold;margin-top:20px;color:#ccc;text-shadow:1px 1px 1px #fff;font-family: 'revolt',georgia,'Microsoft yahei';}
		.content {width:350px;margin:90px auto auto;padding:30px 40px;border:2px solid #ccc;border-radius:10px;box-shadow:0 0 30px #ccc;}
		.ipt {border:1px solid #ddd;padding:10px 5px;width: 240px;outline:none;border-radius:3px;background-color:#fff;font-family: 'revolt',georgia,'Microsoft yahei';}
		.ipt:focus {background-color:#fefefe;box-shadow:0 0 3px #aaa;}
		.thead {font-weight:bold;font-size:20px;padding-bottom:20px!important;text-shadow:1px 1px 1px #fff;}
		.content th {color:#666;font-size:12px;font-weight:bold;text-shadow: 1px 1px 1px #fff;}
		.content td {padding:5px 0;}
		#submit_btn {background-color:#4797ED;border:none;border-radius:10px;box-shadow:0 0 3px #aaa;width:98px;height:41px;line-height:41px;color:#fff;font-size:18px;font-weight:bold;cursor:pointer;margin-right:20px;display:block;outline:none;text-shadow:0 1px 1px #777;float:left;}
		#submit_btn:hover {background-color:#d8d8d8;color:#666;text-shadow:1px 1px 1px #fff;}
		.line {float:left;margin-top:10px;}
		.pd15 {padding-top:15px!important;}
		.red {color:red;}
		.tooltip {cursor:help;position:relative;text-shadow:2px 2px 2px #999;}
		.tooltip span {font: normal 14px verdana;color: #fff;visibility: hidden;position: absolute;bottom: 30px;left: 50%;z-index: 20000;width: 250px;margin-left: -127px;padding: 10px;border: 2px solid #422A20;background: -moz-linear-gradient(top, #B4784C 0%, #422A20 100%);
background: -webkit-gradient(linear, center top, center bottom, from(#B4784C), to(#422A20));background: -o-gradient(top, #B4784C, #422A20); border-bottom: solid 1px #422A20;-moz-border-radius: 4px;border-radius: 4px;-moz-box-shadow: 0 0 5px 5px #B1A59D;-webkit-box-shadow: 0 0 5px 5px #B1A59D;box-shadow: 0 0 5px 5px #B1A59D;}
		.tooltip:hover span {visibility: visible;} 
    </style>
</head>
<body>
	<header> </header>
 	<div class="content">
    	<form action="login"   id="login_form">
    	<table width="100%">
        	<thead>
            	<td colspan="2" class="thead">GPA用户登录</td>
            </thead>
            <tbody>
                <tr>
                  <th><em class="red">*</em> 用户：</th>
                  <td><input type="text" value="" name="username" class="ipt"> <span class="tooltip">(说明) <span>*表示必填</span></span></td>
                </tr>
                <tr>
                  <th><em class="red">*</em> 密码：</th>
                  <td><input type="password" value="" name="pwd" class="ipt"></td>
                </tr>
                <tr>
                  <th class="pd15"></th>
                  <td class="pd15">
                  	<button id="submit_btn" type="submit">登录</button>
                    <p class="line"></p>
                  </td>
                </tr>
            </tbody>
        </table>
        </form>
    </div><br><br><br><br>
	<div style=" width:600px;margin:0 auto; text-align:center; font-size:12px;">
<p></p>
<p></p>
<p></p>
</div>
</body>
</html>


 
