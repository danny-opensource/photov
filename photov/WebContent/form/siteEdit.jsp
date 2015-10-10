<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<%
strHtmlTitle += " - Edit Site ";
%>
<%@ include file="/common/head.jsp"%>
		<html:form action="/site.do">
<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
		<div align="center">
			<h2>
				<bean:message key="site.edit" />
			</h2>
		</div>
		<bean:define id="editAction" value='<%=Constants.ACTION_CREATE%>' />
		<html:link href="site.do" paramId="action" paramName="editAction">
			<bean:message key="insert" />
		</html:link>&nbsp;
		<html:link href="list.do" paramId="siteId" paramName="siteForm" paramProperty="siteId">
			<bean:message key="view" />
		</html:link>
	</td>
</tr>	
<tr>
  <td colspan="1" >
			&nbsp;
	</td>
  <td colspan="1" >
			<html:text property="siteId" readonly="true" size="8" />
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="site.name" />
	</td>
  <td colspan="1" >
			<html:hidden property="siteId"/>
			<html:text property="siteName"/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="site.url" />
	</td>
  <td colspan="1" >
		<html:text property="siteUrl"/>
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
		 
