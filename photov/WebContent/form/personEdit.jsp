<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<%
strHtmlTitle += " - Edit Person ";
%>
<%@ include file="/common/head.jsp"%>
		<html:form action="/person.do">
<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
		<div align="center">
			<h2>
				<bean:message key="person.title.edit" />
			</h2>
		</div>
		<bean:define id="editAction" value='<%=Constants.ACTION_CREATE%>' />
		<html:link href="person.do" paramId="action" paramName="editAction">
			<bean:message key="insert" />
		</html:link>
		
	</td>
</tr>	
<tr>
  <td colspan="1" >
			<bean:message key="person.fname" />
	</td>
  <td colspan="1" >
			<html:hidden property="personId"/><html:errors property="personId"/>
			<html:text property="firstName"/><html:errors property="firstName"/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="person.mname" />
	</td>
  <td colspan="1" >
			<html:text property="middleName"/><html:errors property="middleName"/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="person.lname" />
	</td>
  <td colspan="1" >
			<html:text property="lastName"/><html:errors property="lastName"/>
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
			<html:text property="email"/><html:errors property="email"/>
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
		</html:form>
<%@ include file="/common/foot.jsp"%>
		 

