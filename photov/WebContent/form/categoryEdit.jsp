<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<%
strHtmlTitle += " - Edit Category ";
%>
<%@ include file="/common/head.jsp"%>
		<html:form action="/category.do">
<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
		<div align="center">
			<h2>
				<bean:message key="category.title.edit" />
			</h2>
		</div>
		<bean:define id="editAction" value='<%=Constants.ACTION_CREATE%>' />
		<html:link href="category.do" paramId="action" paramName="editAction">
			<bean:message key="insert" />
		</html:link>&nbsp;
		<html:link href="list.do" paramId="categoryId" paramName="categoryForm" paramProperty="categoryId">
			<bean:message key="view" />
		</html:link>
		
		
	</td>
</tr>	
<tr>
  <td colspan="1" >
			&nbsp;
	</td>
  <td colspan="1" >
			<html:text property="categoryId" readonly="true" size="8" />
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="category.categoryName" />
	</td>
  <td colspan="1" >
			<html:hidden property="categoryId"/>
			<html:text property="categoryName"/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="category.categoryUrl" />
	</td>
  <td colspan="1" >
		<html:text property="categoryUrl"/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="category.categoryDate" />
	</td>
  <td colspan="1" >
			<html:text property="categoryDate"/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="category.topicId" />
	</td>
  <td colspan="1" >
		<html:select property="topicId"> 
		  <html:optionsCollection name="categoryForm" property="topics" label="topicName" value="topicId" />
		</html:select>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
		<bean:define id="editAction1" value='<%=Constants.ACTION_CREATE%>' />
		<html:link href="topic.do" paramId="action" paramName="editAction1">
			<bean:message key="topic.insert" />
		</html:link>
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
		 

