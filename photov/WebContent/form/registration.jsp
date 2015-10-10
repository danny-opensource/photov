<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<%
strHtmlTitle += " - Register ";
%>
<%@ include file="/common/head.jsp"%>
		<html:form action="/registration.do">
<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
		<div align="center">
			<h2>
				<bean:message key="registration.title" />
			</h2>
		</div>
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
  <!-- <bean:write property="personId" name="registrationForm" /> -->
			<html:select property="personId" onchange="carSubmit(this.form)" > 
			  <html:optionsCollection name="registrationForm" property="people" label="personLName" value="personId" />
			</html:select>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
		&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="user.group" />
	</td>
  <td colspan="1" >
			<html:text property="groupId" readonly="true" /> 
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
		&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="user.username" />
	</td>
  <td colspan="1" >
			<html:hidden property="userId"/>
			<html:text property="username"/>
			<bean:message key="error.header"/>
			<html:errors property="username"/>
			<bean:message key="error.footer"/>
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
			<bean:message key="error.header"/>
			<html:errors property="password"/>
			<bean:message key="error.footer"/>
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
			<bean:message key="error.header"/>
			<html:errors property="password2"/>
			<bean:message key="error.footer"/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="person.email" />
	</td>
  <td colspan="1" >
			<html:text property="email" size="40"  />
			<bean:message key="error.header"/>
			<html:errors property="email"/>
			<bean:message key="error.footer"/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
		<html:submit property="action" value="<%=Constants.ACTION_SUBMIT%>"  />
	</td>
  <td colspan="1" >
	  <html:cancel/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
		</html:form>
<%@ include file="/common/foot.jsp"%>
		 

