<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<%
strHtmlTitle += " - Edit Photo ";
%>
<%@ include file="/common/head.jsp"%>
				<logic:present name="user" scope="session">
					<logic:equal name="user" property="canUpdate" value="true">
		<html:form action="photo.do" >

<%@ include file="/common/photoLinks.jsp"%>
<tr>
  <td colspan="1">
			<bean:message key="photo.people" /><p>
	</td>
  <td colspan="1">
		&nbsp;<a name="person"></a>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>">
			<html:link href="person.do" paramId="action" paramName="editAction">
				<bean:message key="person.insert" /><br>
			</html:link>
			<html:link href="photo.do?action=actionPeopleAll" paramId="photoId" paramName="photoForm" paramProperty="photoId">
				<bean:message key="photo.all" />
			</html:link>
			<html:link href="photo.do?action=actionPeopleCategory" paramId="photoId" paramName="photoForm" paramProperty="photoId">
				<bean:message key="photo.cat" />
			</html:link>
			<html:link href="photo.do?action=actionPeopleHeading" paramId="photoId" paramName="photoForm" paramProperty="photoId">
				<bean:message key="photo.head" />
			</html:link>
			<p>
	</td>
</tr>
<tr>
  <td colspan="1">
			<bean:message key="photo.people.inphoto" />
	</td>
  <td colspan="1" >
		&nbsp;
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>">
		<bean:message key="photo.people.outphoto" />
	</td>
</tr>
<tr valign="top" >
  <td colspan="1" >
		<html:select property="peopleId" multiple="false" size="10"  > 
		  <html:optionsCollection name="photoForm" property="people" label="personLName" value="personId" />
		</html:select>
	</td>
  <td colspan="1" >
		<div align="center">
			<html:submit property="action" value="<%=Constants.ACTION_REMOVE%>"  /><br>
			<html:submit property="action" value="<%=Constants.ACTION_ADD%>"  /><br>
		</div>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
		<html:select property="allPeopleId" multiple="false" size="10"  > 
		  <html:optionsCollection name="photoForm" property="allPeople" label="personLName" value="personId" />
		</html:select>
	</td>
</tr>
<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
		<div align="center" >
			&nbsp;<br>
				<img border="0" src="<bean:write name="photoForm" property="photoUrl"/>">
			<br>
		</div>
		<br>
	</td>
</tr>
<tr>
	<td colspan="1">
		<bean:message key="photo.photoid" />	
	</td>
  <td colspan="1" >
			<bean:write name="photoForm" property="photoId" />
			<br>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
		&nbsp;
	</td>
</tr>			
<tr>
	<td colspan="1">
		<bean:message key="photo.url" />	
	</td>
  <td colspan="1" >
			<html:hidden property="photoId" />
			<html:text property="url" size="32" maxlength="64"  /><br>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
		&nbsp;
	</td>
</tr>			
<tr>
	<td colspan="1">
		<bean:message key="photo.thumburl" />	
	</td>
  <td colspan="1" >
			<html:text property="thumbUrl" size="32" maxlength="64"  /><br>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
		&nbsp;
	</td>
</tr>			


<!-- PEOPLE -->
<tr>
  <td colspan="1" >
			<bean:message key="photo.place" />
		<html:link href="list.do" paramId="placeId" paramName="photoForm" paramProperty="placeId">
			<bean:message key="view" />
		</html:link>
	</td>
  <td colspan="1" >
		<html:select property="placeId" > 
		  <html:optionsCollection name="photoForm" property="places" label="placeFullName" value="placeId" />
		</html:select>
		<bean:message key="error.header" /> 
			<html:errors property="placeId"/>
		<bean:message key="error.footer" /> 
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
		<html:link href="place.do" paramId="action" paramName="editAction">
			<bean:message key="place.insert" />
		</html:link>
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="photo.site" />
	</td>
  <td colspan="1" >
		<html:select property="siteId" > 
		  <html:optionsCollection name="photoForm" property="sites" label="siteName" value="siteId" />
		</html:select>
		<bean:message key="error.header" /> 
			<html:errors property="siteId"/>
		<bean:message key="error.footer" /> 
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
		<html:link href="site.do" paramId="action" paramName="editAction">
			<bean:message key="site.insert" />
		</html:link>
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="photo.topic" />
		<html:link href="list.do" paramId="topicId" paramName="photoForm" paramProperty="topicId">
			<bean:message key="view" />
		</html:link>
	</td>
  <td colspan="1" >
		<html:select property="topicId" onchange="carSubmit(this.form)" > 
		  <html:optionsCollection name="photoForm" property="topics" label="topicName" value="topicId" />
		</html:select>
		<bean:message key="error.header" /> 
			<html:errors property="topicId"/>
		<bean:message key="error.footer" /> 
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
		<html:link href="topic.do" paramId="action" paramName="editAction">
			<bean:message key="topic.insert" />
		</html:link>
	</td>
</tr>
<tr>
  <td colspan="1" >
		<bean:message key="photo.category" />
		<html:link href="list.do" paramId="categoryId" paramName="photoForm" paramProperty="categoryId">
			<bean:message key="view" />
		</html:link>
	</td>
  <td colspan="1" >
		<html:select property="categoryId" onchange="carSubmit(this.form)" > 
		  <html:optionsCollection name="photoForm" property="categories" label="categoryName" value="categoryId" />
		</html:select>
		
		<bean:message key="error.header" /> 
			<html:errors property="categoryId"/>
		<bean:message key="error.footer" /> 
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
		<html:link href="category.do" paramId="action" paramName="editAction">
			<bean:message key="category.insert" />
		</html:link>
	</td>
</tr>
<tr>
  <td colspan="1" >
		<bean:message key="photo.heading" />
		<html:link href="list.do" paramId="headingId" paramName="photoForm" paramProperty="headingId">
			<bean:message key="view" />
		</html:link>
	</td>
  <td colspan="1" >
		<html:select property="headingId"> 
		  <html:optionsCollection name="photoForm" property="headings" label="headingName" value="headingId" />
		</html:select>
		<bean:message key="error.header" /> 
			<html:errors property="headingId"/>
		<bean:message key="error.footer" /> 
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
		<html:link href="heading.do" paramId="action" paramName="editAction">
			<bean:message key="heading.insert" />
		</html:link>
	</td>
</tr>
<tr>
  <td colspan="1" >
		<bean:message key="photo.photographer" />
	</td>
  <td colspan="1" >
		<html:select property="photographerId"> 
		  <html:optionsCollection name="photoForm" property="photographers" label="personLName" value="personId" />
		</html:select>
		<bean:message key="error.header" /> 
			<html:errors property="photographerId"/>
		<bean:message key="error.footer" /> 
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
		&nbsp;
	</td>
</tr>			
<tr>
  <td colspan="1" >
		<bean:message key="photo.camera" />
	</td>
  <td colspan="1" >
		<html:select property="cameraId"> 
		  <html:optionsCollection name="photoForm" property="cameras" label="cameraName" value="cameraId" />
		</html:select>
		<bean:message key="error.header" /> 
			<html:errors property="cameraId"/>
		<bean:message key="error.footer" /> 
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
		<html:link href="camera.do" paramId="action" paramName="editAction">
			<bean:message key="camera.insert" />
		</html:link>
	</td>
</tr>			
<tr>
  <td colspan="1" >
		<bean:message key="photo.film" />	
	</td>
  <td colspan="1" >
		<html:select property="filmId"> 
		  <html:optionsCollection name="photoForm" property="films" label="filmName" value="filmId" />
		</html:select>
		<bean:message key="error.header" /> 
			<html:errors property="filmId"/>
		<bean:message key="error.footer" /> 
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
		<html:link href="film.do" paramId="action" paramName="editAction">
			<bean:message key="film.insert" />
		</html:link>
	</td>
</tr>			
<tr>
	<td colspan="1">
		<bean:message key="photo.date" />	
	</td>
  <td colspan="1" >
			<html:text property="date" /><br>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	<bean:message key="format.date" />
	</td>
</tr>			
<tr>
	<td colspan="1">
		<bean:message key="photo.title" />
	</td>
  <td colspan="1" >
			<html:text property="title" />
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>			
<tr>
	<td colspan="1">
		<bean:message key="photo.vertical" />
	</td>
  <td colspan="1" >
			<html:checkbox property="vertical" />
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>			
<tr>
	<td colspan="1">
		<bean:message key="photo.complete" />
	</td>
  <td colspan="1" >
			<html:checkbox property="complete" />
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>			
<tr>
	<td colspan="1">
		<bean:message key="photo.notes" />
	</td>
  <td colspan="1" >
			<html:textarea rows="4" cols="25"   property="notes" />
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
<tr valign="top" >
  <td colspan="1" >
		<html:select property="groupId" multiple="false" size="10"  > 
		  <html:optionsCollection name="photoForm" property="groups" label="groupName" value="groupId" />
		</html:select>
	</td>
  <td colspan="1" >
		<div align="center">
			<html:submit property="action" value="<%=Constants.ACTION_GRP_REMOVE%>"  /><br>
			<html:submit property="action" value="<%=Constants.ACTION_GRP_ADD%>"  /><br>
		</div>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
		<html:select property="allGroupId" multiple="false" size="10"  > 
		  <html:optionsCollection name="photoForm" property="allGroups" label="groupName" value="groupId" />
		</html:select>
	</td>
</tr>


		</html:form>
					</logic:equal>
				</logic:present>		
<%@ include file="/common/foot.jsp"%>
		 

