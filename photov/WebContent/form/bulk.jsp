<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<%
strHtmlTitle += " - Bulk Load ";
%>
<%@ include file="/common/head.jsp"%>
				<logic:present name="user" scope="session">
					<logic:equal name="user" property="canUpdate" value="true">
		<html:form action="bulk.do" >
<%@ include file="/common/photoLinks.jsp"%>
<tr>
	<td colspan="1">
		<bean:message key="photo.url" />	
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-1%>" >
			<html:textarea name="bulkForm" property="urls" cols="40" rows="20" /><br>
	</td>
</tr>			
<tr>
  <td colspan="1" >
			<bean:message key="photo.place" />
	</td>
  <td colspan="1" >
		<html:select property="placeId" > 
		  <html:optionsCollection name="bulkForm" property="places" label="placeFullName" value="placeId" />
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
		  <html:optionsCollection name="bulkForm" property="sites" label="siteName" value="siteId" />
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
	</td>
  <td colspan="1" >
		<html:select property="topicId" onchange="carSubmit(this.form)" > 
		  <html:optionsCollection name="bulkForm" property="topics" label="topicName" value="topicId" />
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
	</td>
  <td colspan="1" >
		<html:select property="categoryId" onchange="carSubmit(this.form)" > 
		  <html:optionsCollection name="bulkForm" property="categories" label="categoryName" value="categoryId" />
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
	</td>
  <td colspan="1" >
		<html:select property="headingId"> 
		  <html:optionsCollection name="bulkForm" property="headings" label="headingName" value="headingId" />
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
		  <html:optionsCollection name="bulkForm" property="photographers" label="personLName" value="personId" />
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
		  <html:optionsCollection name="bulkForm" property="cameras" label="cameraName" value="cameraId" />
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
		  <html:optionsCollection name="bulkForm" property="films" label="filmName" value="filmId" />
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
		<bean:message key="bulk.submit.check" />
	</td>
  <td colspan="1" >
  		<p>
			<html:checkbox property="submitCheck" />
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
					</logic:equal>
				</logic:present>		
<%@ include file="/common/foot.jsp"%>
		 

