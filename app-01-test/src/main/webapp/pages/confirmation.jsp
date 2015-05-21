<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ page import="web.Resource" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/spring.tld" prefix="spring" %>
<%@ page isELIgnored="false" %>

<% final String prefix = request.getContextPath();
   final String action= (String) session.getAttribute("action"); 
   final String nextAction= (String) session.getAttribute("next_action"); 
   final Resource s =  (Resource) session.getAttribute("resource"); 
   final String lang = (String) session.getAttribute("lang");   

%>
   

<html>
  <head>
  	<title><%=s.msg("form.title")%></title>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  	<style type="text/css"></style>
	<link rel="stylesheet" type="text/css"  href="css/styles.css" />
	    <style>
		body {
        font: normal 10pt arial; 
		background:#333333;
		}
		
		table{
		padding:10px;
		background:white; 
		}
		</style>
  </head>  
  <body bgcolor="#222222">
  <table width=1070 border=0 cellspacing=0>
  <tr><td>  
   <table width=1040 border=0 cellpadding=0 cellspacing=0>
  	<tr>
		<td><img class="logo" width="40" height="40" src="images/logo_orange.png"/></td>
  		<td class="title" width=960><%=s.msg("form.title")%></td>
  		<td width=40><% 
  		   if(lang.equals("fr"))
  			   out.print("<span style='position:absolute;margin-top:-15px' title='" + s.msg("form.msg.click_to_change_lang") + "'><a href='" + prefix + "/" + nextAction +  ".do?lang=en'><img border='0' src='images/drapeau-en.png'/></a></span>"); 
  		   else 
  			   out.print("<span style='position:absolute;margin-top:-15px' title='" + s.msg("form.msg.click_to_change_lang") + "'><a href='" + prefix + "/" + nextAction +  ".do?lang=fr'><img border='0' src='images/drapeau-fr.png'/></a></span>"); 
   		%>
		</td>
  	</tr>
  	</table>
	<br><br><br><br>
	    <span style='position:absolute;margin-left:10px;margin-top:-10px'><img  src="images/green_tick.png"/></span>
<p class="info_msg"><%=s.msg("form.conf." + action)%></p>
  <br><br>
   <%
   out.print("<div class=button style='width:80px'><img border=0 style='position:absolute;margin: 8px 5px' src='images/back_arrow.png'><a style='position:absolute;text-decoration:none;font-size:10pt;padding-left:25px;margin-top:5px' href='" + prefix + "/" + nextAction + ".do"); 
   if(lang.equals("fr"))
	   out.append("?lang=fr'>retour</a></div>");
   else 
	   out.append("?lang=en'>back</a></div>");
   %>

<br><br><br><br>
<table width=1050 border=0 cellpadding=0 cellspacing=0>
<tr>
<td width=550 height=30 style="font-family:arial;font-size:10pt;color:#222222;vertical-align:top;padding-top:5px;padding-left:5px;border-top: 1px solid #FF6600">
<b><%=s.msg("form.footer.msg")%></b><br><%=s.msg("form.footer.url")%></td>
<td class=footer style="border-top: 1px solid #FF6600"><%=s.msg("form.version")%></td>
</tr>
</table>
</td></tr></table>
 </body>
</html>
