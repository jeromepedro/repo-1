<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ page import="web.Resource" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/spring.tld" prefix="spring" %>
<% final Resource s =  (Resource) session.getAttribute("resource"); 
   final String lang = (String) session.getAttribute("lang");
   final String username = (String) session.getAttribute("username");%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>${title}</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<style type="text/css"></style>
<link rel="stylesheet" type="text/css" href="css/styles.css" />
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<style>
body {
	font: normal 10pt arial; 
background:#333333;
}

li{
	list-style:none;
}

.menu {
	float:left; 
	min-width:329px;
	max-width:329px;
 	color: black;
    height:20px;
	padding-top:5px;
	padding-left:10px;
	font-family:Arial;
	font-size:10pt;
    border:1px solid #BBB; 
	background: url('images/white-gray.png'); 
	
	 }
	 
.menu_selected {
	  float:left; 
	  min-width:329px;
	  max-width:329px;
	  color:black;
	  height:20px;
	  padding-top:5px;
	  padding-left:10px;
	  font-family:Arial;
	  font-size:10pt;
	  font-weight:bold;
	  background:white;
	  border-left: 1px solid #BBB; 
	  border-top: 1px solid #BBB; 
	  border-right: 1px solid #BBB; 
	 }
	 
	 .menu:hover {
	  color: #FF6600; 
	  font-weight:bold; 
	}


#addProject {
	width: 800px;
height: 20px;
margin-left:15px;

}


#projectMsg {
width:400px;
height:10px;
color:red; 
margin-left:15px;
}

#projectTitle {
display:block; 
width:650px;
height:20px;
color:#333;
font-size:11pt;
margin-left:15px;
}

#navBtn {
	width: 580px;
	height: 10px;
	margin-left:15px;
}

	
	 div#projectScrollTableContainer {
        width: 680px;
        margin: 15px;
        border: 1px solid #888;
    }
	
    table#projectTHead {
        border-collapse: collapse;
        table-layout: fixed;
		min-width: 663px; /* Firefox needs min- and max-widths */
        max-width: 663px;    
		}

    table#projectTBody {
        border-collapse: collapse;
        table-layout: fixed;
		min-width: 663px; /* Firefox needs min- and max-widths */
        max-width: 663px;    
		}
		
	 table#projectTBody td{
	 		border-right: 1px solid #888; 

    }
	
	table#projectTBody td+td+td{
	 		border-right: none;  

    }

	
	table#projectTHead td{
	 		border-right: 1px solid #888; 

    }
	
	table#projectTHead td+td+td{
	 		border-right: none;  
    }
	
	#projectTHeadContainer {
       /* background: #333;*/
		background: url('images/header_black.png'); 
        color: white;
        font-weight: bold;
    }
    #projectTBodyContainer {
        height: 150px;
        overflow-y: scroll;
    }
	
	
    #tBody tr.lastRow td {
        border-bottom: none;
    }
	

	.odd_row {
	   background: #FDB; 
	}

	.even_row {
	   background: white; 
	}	
	
	.selected_row {
	   background: #999;
	   color:white;
	   font-weight:bold;
	}	

</style>
</head>  
<body>

<% final String prefix = request.getContextPath(); %><%-- pour construire des liens absolus --%>
<table width=1070 border=0 cellspacing=0 bgcolor=white  style="padding:10px"><tr><td> 	
<table width=1040 border=0 cellpadding=0 cellspacing=0>
<tr>
<td><img class="logo" width="40" height="40" src="images/logo_orange.png"/></td>
<td class="title" width=940>${title}</td>
<td width=40><%
if(lang.equals("fr"))
	out.print("<span style='position:absolute;margin-top:-15px' title='" + s.msg("form.msg.click_to_change_lang") + "'><a href='"+prefix+"/adm_a.do?lang=en'><img border='0' src='images/drapeau-en.png'/></a></span>"); 
else 
	out.print("<span style='position:absolute;margin-top:-15px' title='" + s.msg("form.msg.click_to_change_lang") + "'><a href='"+prefix+"/adm_a.do?lang=fr'><img border='0' src='images/drapeau-fr.png'/></a></span>"); 

%>
</td>
</tr>
</table>

<p style="color:#FF6600;font-style:arial;font-size:9pt;margin-left:15px"><%=s.msg("form.label.welcome")%> <%=username %></p>

<table width=360 border=0 cellpadding=0 cellspacing=0>
<tr><td height=60 width=10><img border='0' style="position:relative;margin-top:5px;margin-left:10px" src='images/arrow.png'/></td><td class=subtitle>
${subtitle}
</td></tr>
</table>


<div id="projectScrollTableContainer">
        <div id="projectTHeadContainer">
            <table  id="projectTHead">				
            </table>
        </div><!-- tHeadContainer -->
        <div id="projectTBodyContainer">
            <table id="projectTBody">
            </table>
        </div><!-- tBodyContainer -->
 </div><!-- scrollTableContainer -->
<div id="addProject"></div>
<br>
<div id="projectMsg"></div>
<br>

<br>
<table width=1050 border=0 cellpadding=0 cellspacing=0>
<tr>
<td width=550 height=30 style="font-family:arial;font-size:10pt;color:#222222;vertical-align:top;padding-top:5px;padding-left:5px;border-top: 1px solid #FF6600">
<b>${footer_msg}</b><br>${footer_url}
</td>
<td class=footer style="border-top: 1px solid #FF6600">${version}</td>
</tr>
</table> 
</td></tr></table>

<script>


/*if(navigator.userAgent.search("MSIE")>=0)
			alert("internet explorer"); 
	if(navigator.userAgent.search("Firefox")>=0)
			alert(" firefox"); */

/* Suppression du cache pour  le support IE8 */
	$.ajaxSetup({
		cache: false
	});

var projectData=[]; 
var userData=[]; 
var selectedPrjItem,selectedUserItem; 
var maxUser="${maxUser}";
var begin=1; 
var currentPrjId="${defaultPrjId}";
var currentRoleId='mgr'; 
var currentUser = "<%=username %>"; 

$('#fetchUser').html("<%=s.msg("form.label.filter.id")%> : <input id='fetchCuidInputText'type='text' size='10'>&nbsp;&nbsp;<%=s.msg("form.label.filter.name")%> : <input id='fetchNameInputText'type='text' size='10'>&nbsp;&nbsp;<input id='validateBtn' type='button' onclick=\"fetchUser();\"  value='<%=s.msg("form.btn.filter")%>'>");  				



	<c:forEach items="${prjdata}" var="d" varStatus="d_cpt">   
	 var deleteId=${d.id}>1 ? ${d.id} : "";
     projectData[${d_cpt.index}]={"id" : "${d.id}", "name" : "${d.name}" , "deleteId" : deleteId};
 
	</c:forEach> 
var projectDataLength=projectData.length;
var currentPrjName=projectData.length>0 ? projectData[0].name : "N/A";  

$('#projectTitle').html("<%=s.msg("form.label.project_title")%> : " + currentPrjName); 

$('#navBtn').html("<img src='images/rew-end.png' onclick=\"begin=1;reloadUserData(begin);\"/>&nbsp;<img src='images/rew.png' onclick=\"begin=(begin>10) ? begin=begin-10 : 1;reloadUserData(begin);\"/>&nbsp;&nbsp;<img src='images/fwd.png'/ onclick=\"begin=(begin<=maxUser-10) ? begin+10 : begin;reloadUserData(begin);\">&nbsp;<img src='images/fwd-end.png' onclick=\"begin=maxUser - maxUser % 10 + 1; reloadUserData(begin);\"/>");
	
$('#addProject').html("<input id='addPrjBtn' type='button' onclick=\"showAddProjectInput();\"  value='<%=s.msg("form.btn.add")%>'>"); 

function addProject(){
	var prjName= $('#addProjInputText').val(); 			
	$.post("./addproject.do",
			{project:prjName,submit:true},
			function(data, textStatus, jqXHR){	
				if(data!="null" && data!="0"){
					projectData[projectDataLength]={"id":data, "name":prjName, "deleteId":data};
					projectDataLength++;
					currentPrjId=data; 
					currentPrjName=prjName; 
					displayProjectData(); 
					setProjectEvents(); 
					showAddProjectButton(); 
					setProjectFocus(currentPrjId);
					$('#projectTitle').html("<%=s.msg("form.label.project_title")%> : " + currentPrjName); 
					showAddUserButton();
				}
				
				else if(data=="0")
					$('#projectMsg').html("<%=s.msg("form.error.project_name_exists")%>"); 
				
				else 
					$('#projectMsg').html("<%=s.msg("form.error.project_invalid_user")%> <%=username %>"); 

			});	
}

function removeProject(value){
	$.post("./removeproject.do",
			{delete_id:value,submit:true},
			function(data, textStatus, jqXHR){
				if(data=='ok'){
					deleteProjectDataItem(value); 
					if(currentPrjId)
						$('#projectTitle').html("<%=s.msg("form.label.project_title")%> : " + currentPrjName); 
					else 
						$('#projectTitle').html(""); 
						
					showAddProjectButton(); 
					showAddUserButton();
				}
				else 
					$('#projectMsg').html("Le projet ne peut &ecirc;tre supprim&eacute;."); 	
			});	
}



function showAddProjectInput(){
	$('#addProject').html("<%=s.msg("form.label.input.project_name")%> : <input id='addProjInputText'type='text' size='25'>&nbsp;<input id='validateBtn' type='button' onclick=\"addProject();\"  value='<%=s.msg("form.btn.validate")%>'>&nbsp;<input id='cancelBtn' type='button' onclick=\"showAddProjectButton();\"  value='<%=s.msg("form.btn.cancel")%>'>");  
	$('#projectMsg').html(""); 				

}

function showAddProjectButton(){
	$('#addProject').html("<input id='addPrjBtn' type='button' onclick=\"showAddProjectInput();\"  value='<%=s.msg("form.btn.add_project")%>'>");  
	$('#projectMsg').html(""); 				
}

function showRemoveProjectInput(value){
	$('#addProject').html("<span style='color:#FF6600'><%=s.msg("form.msg.remove_project")%></span> &nbsp;&nbsp;<input id='validateBtn' type='button' onclick=\"removeProject('" + value + "');\"  value='<%=s.msg("form.btn.validate")%>'>&nbsp;<input id='cancelBtn' type='button' onclick=\"showAddProjectButton();\"  value='<%=s.msg("form.btn.cancel")%>'>");  				
	$('#projectMsg').html(""); 				
						 
}


	
	function displayProjectData(){
					$('#projectMsg').html(""); 
					$('#projectTBody').html(""); 
					$('#projectTHead').html("<tr><td width='16%'><%=s.msg("form.label.grid.project_id")%></td><td width='80%'><%=s.msg("form.label.grid.project")%></td><td width='4%'></td></tr>");
					var i=0; 
					var classStr; 
					while(projectData[i]!=null){
						classStr = classStr=="even_row" ? "odd_row" : "even_row";
						var deleteStr=projectData[i].id>1 ? "<span title='suppression'><img src=\"images/trash.jpg\" onclick=\"removeProject('" + projectData[i].deleteId + "');\"></span>" : ""; 
							$('#projectTBody').append("<tr class='" + classStr + "'><td width='16%'>" + projectData[i].id + "</td><td width='80%'><span class='name'>" 
							+  projectData[i].name + "</span></td><td width='4%'>" + deleteStr + "</td></tr>"); 
						i++; 
					}
				
					while(i<6){
						classStr = classStr=="even_row" ? "odd_row" : "even_row";
						$('#projectTBody').append("<tr class='" + classStr + "'><td width='16%'></td><td width='80%'></td><td width='4%'></td></tr>"); 
						i++; 
					}
	   }
		
	   
	 	 function deleteProjectDataItem(projectId){
						var i=0; 
						while(projectData[i]!=null){
						  if(projectData[i].id==projectId)
							break;
						  i++; 
						}
						
						if(i>0){
							currentPrjId=projectData[i-1].id;
							currentPrjName=projectData[i-1].name;
						}
						else if(currentPrjId=projectData[i+1]!=null){
							currentPrjId=projectData[i+1].id;
							currentPrjName=projectData[i+1].name;
						}
						
						while(projectData[i+1]!=null){
						  projectData[i]=projectData[i+1]; 
						  i++; 
						}
						
						delete(projectData[i]); 
						projectDataLength--; 
						if(projectDataLength==0)
							currentPrjId=null; 
						
						displayProjectData(); 
						setProjectEvents(); 
			   }
			   
			   function setProjectEvents(){
				$('#projectTBody tr').click(function(){				
				   if($(this).attr("class")=="even_row" || $(this).attr("class")=="odd_row"){
				   
						if(selectedPrjItem!=null){
							selectedPrjItem.content.attr("class", selectedPrjItem.classStr); 
							$('#projectTBody span').each(function() { 	
								if($(this).attr("class")=="name_e"){
										
									  $.post("./editproject.do",
												{prj_id:selectedPrjItem.content.find('td').html(), name_val:$(this).children('input').val(),submit:true},
												function(data, textStatus, jqXHR){
													//void
												});	
									if($(this).children('input').val()=="")
										$(this).html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"); 
									else
										$(this).html($(this).children('input').val());
									$(this).attr("class", "name"); 
								}
							}); 

						}
						selectedPrjItem={content:$(this), classStr:$(this).attr("class")};
						currentPrjId=$(this).find('td').html(); 
						currentPrjName=$(this).find('td+td').html(); 
						$(this).attr("class", "selected_row");
						$('#projectTitle').html("<%=s.msg("form.label.project_title")%> : " + currentPrjName); 
						reloadUserData(1); 
						showAddUserButton();
					}
			   });

			$('#projectTBody span').click(function(){
					   if($(this).attr("class")=="name" && $(this).parent().parent().attr("class")=="selected_row"  && currentPrjId>1){
							$(this).html("<input type='text' size=80 name='prj_name' value='" + $(this).html() + "'/>")
							$(this).attr("class", "name_e");
					   }   
					   
				   });
				
			   
			   $('#projectTBody span').keypress(function(e){
					if ( e.keyCode==13 ){
							var i=0; 
							while(projectData[i]!=null){
							  if(projectData[i].id==$(this).parent().parent().find('td').html()){
							  projectData[i].name=$(this).children('input').val();
							  $.post("./editproject.do",
										{prj_id:projectData[i].id, name_val:projectData[i].name,submit:true},
										function(data, textStatus, jqXHR){
											//void
										});	
							  if($(this).children('input').val()=="")
									$(this).html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"); 
								else
									$(this).html($(this).children('input').val());
							  break;
							  }
							  i++; 
							}
							$(this).attr("class", "name");
						}
						
					else if (e.keyCode==27){
						
							var i=0; 
							while(projectData[i]!=null){
							  if(projectData[i].id==$(this).parent().parent().find('td').html()){
							  $(this).html(projectData[i].name);
							  $(this).attr("class", "name");
							   break;
							  }
							  i++; 
							}
										
						
					}
			   }); 
	   }
	   
	
		
		function setProjectFocus(prjId){
					$('#projectTBody tr').each(function(){
						if($(this).find('td').html()==prjId){
							selectedPrjItem={content:$(this), classStr:$(this).attr("class")};
							$(this).attr("class","selected_row"); 
							}
							}); 

			
		
		}
	   showAddProjectButton(); 
	   displayProjectData();
	   setProjectEvents();
	   //setProjectFocus(currentPrjId); 
</script>
</body>
</html>
