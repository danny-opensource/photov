<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<%
strHtmlTitle += " - Edit Heading ";
%>
<%@ include file="/common/head.jsp"%>
		<html:form action="/heading.do">
<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
		<div align="center">
			<h2>
				<bean:message key="heading.title.edit" />
			</h2>
		</div>
		<bean:define id="editAction" value='<%=Constants.ACTION_CREATE%>' />
		<html:link href="heading.do" paramId="action" paramName="editAction">
			<bean:message key="insert" />
		</html:link>&nbsp;
		<html:link href="list.do" paramId="headingId" paramName="headingForm" paramProperty="headingId">
			<bean:message key="view" />
		</html:link>
		
	</td>
</tr>	
<tr>
  <td colspan="1" >
			&nbsp;
	</td>
  <td colspan="1" >
			<html:text property="headingId" readonly="true" size="8"/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="heading.headingName" />
	</td>
  <td colspan="1" >
			<html:hidden property="headingId"/>
			<html:text property="headingName"/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="heading.headingUrl" />
	</td>
  <td colspan="1" >
		<html:text property="headingUrl"/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="heading.headingDate" />
	</td>
  <td colspan="1" >
			<html:text property="headingDate"/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="heading.topicId" />
	</td>
  <td colspan="1" >
		<html:select property="topicId" onchange="carSubmit(this.form)" > 
		  <html:optionsCollection name="headingForm" property="topics" label="topicName" value="topicId" />
		</html:select>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
		<html:link href="topic.do" paramId="action" paramName="editAction">
			<bean:message key="topic.insert" />
		</html:link>
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="heading.categoryId" />
	</td>
  <td colspan="1" >
		<html:select property="categoryId"> 
		  <html:optionsCollection name="headingForm" property="categories" label="categoryName" value="categoryId" />
		</html:select>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
		<html:link href="category.do" paramId="action" paramName="editAction">
			<bean:message key="category.insert" />
		</html:link>
	</td>
</tr>
<!-- GROUP -->

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
		<html:select property="groupId" multiple="false" size="10"  > 
		  <html:optionsCollection name="headingForm" property="groups" label="groupName" value="groupId" />
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
		  <html:optionsCollection name="headingForm" property="allGroups" label="groupName" value="groupId" />
		</html:select>
	</td>
</tr>
		</html:form>
<%@ include file="/common/foot.jsp"%>
		 

