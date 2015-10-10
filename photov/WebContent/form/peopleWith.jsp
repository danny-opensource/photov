<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<jsp:useBean id="personOne" scope="session" class="net.wgbv.photov.objects.Person" />
<jsp:useBean id="personTwo" scope="session" class="net.wgbv.photov.objects.Person" />
<bean:define id="tName" name="personOne" property="personName" />
<bean:define id="tlName" name="personTwo" property="personName" />
<%
strHtmlTitle += " - View Photos of " + tName + " & " + tlName + " - ";
%>
<%@ include file="/common/head.jsp"%>
	
<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
		<div align="center"> 
			<h2>
				<bean:write name="personOne" property="personName" /> &amp; 
				<bean:write name="personTwo" property="personName" />
			</h2>
		</div>
	</td>
</tr>
<%@ include file="/common/pagination.jsp"%>
<tr>
<nested:iterate id="photoThumbs" name="photo" indexId="index">
	<td>
		<div align="center">
			<%@ include file="/common/tags.jsp"%>
			<html:link href="list.do" paramId="photoId" paramName="photoThumbs" paramProperty="photoId">
				<img height="110" width="100" src="<bean:write name="photoThumbs" property="photoThumbUrl"/>">
			</html:link>
		</div>
		<br>
	</td>
	<% if ((index.intValue()+1)%3 == 0){ %>
		</tr>
		<tr>
	<% } %>
</nested:iterate>
</tr>
<tr>
	<td colspan="<%=Constants.COLUMN_COUNT%>" >
		<hr>
		<html:form action="/peopleSearch.do">
			<bean:write name="personOne" property="personName" />
			<html:hidden property="personIdOne" />
			&amp;
			<html:select property="personIdTwo" > 
			  <html:optionsCollection name="personTwo" property="people" label="personLName" value="personId" />
			</html:select><br>
			<html:submit/>&nbsp;
			<html:cancel/>
			
		</html:form>

	</td>
</tr>
<%@ include file="/common/pagination.jsp"%>
<%@ include file="/common/foot.jsp"%>
		