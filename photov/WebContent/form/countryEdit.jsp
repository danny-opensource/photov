<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<%
strHtmlTitle += " - Edit Country ";
%>
<%@ include file="/common/head.jsp"%>
		<html:form action="/country.do">
<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
		<div align="center">
			<h2>
				<bean:message key="country.edit" />
			</h2>
		</div>
		<bean:define id="editAction" value='<%=Constants.ACTION_CREATE%>' />
		<html:link href="country.do" paramId="action" paramName="editAction">
			<bean:message key="insert" />
		</html:link>
		
	</td>
</tr>	
<tr>
  <td colspan="1" >
			&nbsp;
	</td>
  <td colspan="1" >
			<html:text property="countryId" readonly="true" />
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="country.name" />
	</td>
  <td colspan="1" >
			<html:hidden property="countryId"/>
			<html:text property="countryName"/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="country.abbr" />
	</td>
  <td colspan="1" >
		<html:text property="countryAbbr"/>
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
		 

