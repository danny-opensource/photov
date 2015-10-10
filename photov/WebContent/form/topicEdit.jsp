<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<%
strHtmlTitle += " - Edit Topic ";
%>
<%@ include file="/common/head.jsp"%>
		<html:form action="/topic.do">
<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
		<div align="center">
			<h2>
				<bean:message key="topic.title.edit" />
			</h2>
		</div>
		<bean:define id="editAction" value='<%=Constants.ACTION_CREATE%>' />
		<html:link href="topic.do" paramId="action" paramName="editAction">
			<bean:message key="insert" />
		</html:link>&nbsp;
		<html:link href="list.do" paramId="topicId" paramName="topicForm" paramProperty="topicId">
			<bean:message key="view" />
		</html:link>
		
		
	</td>
</tr>	
<tr>
  <td colspan="1" >
			&nbsp;
	</td>
  <td colspan="1" >
			<html:text property="topicId" readonly="true" size="8" />
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="topic.topicName" />
	</td>
  <td colspan="1" >
			<html:hidden property="topicId"/>
			<html:text property="topicName"/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="topic.topicUrl" />
	</td>
  <td colspan="1" >
		<html:text property="topicUrl"/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="topic.topicDate" />
	</td>
  <td colspan="1" >
			<html:text property="topicDate"/>
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
		 
