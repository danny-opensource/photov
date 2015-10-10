<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<%
strHtmlTitle += " - Edit Group ";
%>
<%@ include file="/common/head.jsp"%>
		<html:form action="/group.do">
<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
		<div align="center">
			<h2>
				<bean:message key="group.edit" />
			</h2>
		</div>
		<bean:define id="editAction" value='<%=Constants.ACTION_CREATE%>' />
		<html:link href="group.do" paramId="action" paramName="editAction">
			<bean:message key="insert" />
		</html:link>&nbsp;
		<html:link href="list.do?groupId=0" >
			<bean:message key="view" />
		</html:link>
	</td>
</tr>	
<tr>
  <td colspan="1" >
			&nbsp;
	</td>
  <td colspan="1" >
			<html:text property="groupId" readonly="true" size="8" />
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="group.name" />
	</td>
  <td colspan="1" >
			<html:hidden property="groupId"/>
			<html:text property="groupName"/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="group.desc" />
	</td>
  <td colspan="1" >
		<html:text property="groupDesc"/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
		<html:submit/>
	</td>
  <td colspan="1" >
	  <html:cancel/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1">
			<bean:message key="user.ingrp" />
	</td>
  <td colspan="1" >
		&nbsp;
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>">
		<bean:message key="user.outgrp" />
	</td>
</tr>
<tr>
  <td colspan="1" >
		<html:select property="selectedUserId" multiple="false" size="10"  > 
		  <html:optionsCollection name="groupForm" property="selectedUsers" label="username" value="userId" />
		</html:select>
	</td>
  <td colspan="1" >
		<div align="center" valign="center" >
			<html:submit property="action" value="<%=Constants.ACTION_GRP_REMOVE%>"  /><br>
			<html:submit property="action" value="<%=Constants.ACTION_GRP_ADD%>"  /><br>
		</div>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
		<html:select property="allUserId" multiple="false" size="10"  > 
		  <html:optionsCollection name="groupForm" property="allUsers" label="username" value="userId" />
		</html:select>
	</td>
</tr>
		</html:form>
<%@ include file="/common/foot.jsp"%>
		 
