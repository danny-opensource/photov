<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<%
strHtmlTitle += " - Edit User - ";
%>
<%@ include file="/common/head.jsp"%>
		<html:form action="/user.do">
<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
		<div align="center">
			<h2>
				<bean:message key="user.edit" />
			</h2>
		</div>
		<bean:define id="editAction" value='<%=Constants.ACTION_CREATE%>' />
		<logic:equal name="user" property="canUpdate" value="true">
			<html:link href="user.do" paramId="action" paramName="editAction">
				<bean:message key="insert" />
			</html:link>&nbsp;
			<html:link href="list.do" paramId="userId" paramName="userForm" paramProperty="userId">
				<bean:message key="view" />
			</html:link>
		</logic:equal>
	</td>
</tr>	
<tr>
  <td colspan="1" >
			&nbsp;
	</td>
  <td colspan="1" >
			<html:text property="userId" readonly="true" size="8" />
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="user.person" />
	</td>
  <td colspan="1" >
		<logic:equal name="user" property="canUpdate" value="true">
			<html:select property="personId"> 
			  <html:optionsCollection name="userForm" property="people" label="personLName" value="personId" />
			</html:select>
		</logic:equal>
		<logic:equal name="user" property="canUpdate" value="false">
			<html:hidden property="personId" />
			<bean:write name="user" property="personLName" />
		</logic:equal>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
		<logic:equal name="user" property="canUpdate" value="true">
			<bean:define id="editAction1" value='<%=Constants.ACTION_CREATE%>' />
			<html:link href="person.do" paramId="action" paramName="editAction1">
				<bean:message key="person.insert" />
			</html:link>
		</logic:equal>
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="user.group" />
	</td>
  <td colspan="1" >
			<html:select property="groupId"> 
			  <html:optionsCollection name="userForm" property="groups" label="groupName" value="groupId" />
			</html:select>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
		<logic:equal name="user" property="canUpdate" value="true">
			<html:link href="group.do" paramId="action" paramName="editAction1">
				<bean:message key="group.insert" />
			</html:link>
		</logic:equal>
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="user.username" />
	</td>
  <td colspan="1" >
			<html:hidden property="userId"/>
			<html:text property="username"/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="user.password" />
	</td>
  <td colspan="1" >
			<html:password property="password"/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="user.password2" />
	</td>
  <td colspan="1" >
			<html:password property="password2"/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
		<logic:equal name="user" property="canUpdate" value="true">
<tr>
  <td colspan="1" >
			<bean:message key="user.active" />
	</td>
  <td colspan="1" >
		<html:checkbox property="active"/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="user.update" />
	</td>
  <td colspan="1" >
			<html:checkbox property="update"/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1">
			<bean:message key="photo.group" /><p>
	</td>
  <td colspan="1">
		&nbsp;
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>">
		<html:link href="group.do" paramId="action" paramName="editAction">
			<bean:message key="group.insert" /><br>
		</html:link>
		<html:link href="list.do?groupId=0">
			<bean:message key="group.view" /><br>
		</html:link>
			<p>
	</td>
</tr>
<tr>
  <td colspan="1" >
		<html:submit/><p>
	</td>
  <td colspan="1" >
	  <html:cancel/><p>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1">
			<bean:message key="photo.people.ingrp" />
	</td>
  <td colspan="1" >
		&nbsp;
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>">
		<bean:message key="photo.people.outgrp" />
	</td>
</tr>
<tr>
  <td colspan="1" >
		<html:select property="selectedGroupId" multiple="false" size="10"  > 
		  <html:optionsCollection name="userForm" property="selectedGroups" label="groupName" value="groupId" />
		</html:select>
	</td>
  <td colspan="1" >
		<div align="center" valign="center" >
			<html:submit property="action" value="<%=Constants.ACTION_GRP_REMOVE%>"  /><br>
			<html:submit property="action" value="<%=Constants.ACTION_GRP_ADD%>"  /><br>
		</div>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
		<html:select property="allGroupId" multiple="false" size="10"  > 
		  <html:optionsCollection name="userForm" property="allGroups" label="groupName" value="groupId" />
		</html:select>
	</td>
</tr>
	</logic:equal>
	<logic:equal name="user" property="canUpdate" value="false">
			<html:hidden property="active"/>
			<html:hidden property="update"/>
	</logic:equal>
	
		</html:form>
<%@ include file="/common/foot.jsp"%>
		 

