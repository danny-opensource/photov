<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<jsp:useBean id="person" scope="session" class="net.wgbv.photov.objects.Person" />
<bean:define id="tName" name="person" property="personName" />
<%
strHtmlTitle += " - View Photos of " + tName + " - ";
%>
<%@ include file="/common/head.jsp"%>
	
<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
		<div align="center"> 
			<h2>
				<bean:write name="person" property="personName" />
				<bean:define id="psonId" name="person" property="personId" />
				<logic:present name="user" scope="session">
					<logic:equal name="user" property="canUpdate" value="true">
						<html:link href="person.do" paramId="personId" paramName="person" paramProperty="personId">
							<bean:message key="edit" />
						</html:link><br>
						<bean:define id="editAction" value='<%=Constants.ACTION_CREATE%>' />
						<html:link href="person.do" paramId="action" paramName="editAction">
							<bean:message key="person.insert" />
						</html:link>
					</logic:equal>
				</logic:present>
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
		<!--
	<bean:write name="photoThumbs" property="photoId" />
		-->
	
		</div>
		<br>
	</td>
	<logic:equal name="index" value="2">
		</tr>
		<tr>
	</logic:equal>
	<logic:equal name="index" value="5">
		</tr>
		<tr>
	</logic:equal>
	<logic:equal name="index" value="8">
		</tr>
		<tr>
	</logic:equal>
	<logic:equal name="index" value="11">
		</tr>
		<tr>
	</logic:equal>
	<logic:equal name="index" value="14">
		</tr>
		<tr>
	</logic:equal>
	<logic:equal name="index" value="17">
		</tr>
		<tr>
	</logic:equal>
	
</nested:iterate>



</tr>
<%@ include file="/common/pagination.jsp"%>
<tr>
	<td colspan="<%=Constants.COLUMN_COUNT%>" >
		<html:form action="/peopleSearch.do">
			<hr>
			<bean:write name="person" property="personName" />
			<input type="hidden" name="personIdOne" value="<%=psonId.toString()%>">
			&amp;
			<html:select property="personIdTwo" > 
			  <html:optionsCollection name="person" property="people" label="personLName" value="personId" />
			</html:select><br>
			<html:submit/>&nbsp;
			<html:cancel/>
			
		</html:form>

	</td>
</tr>
<%@ include file="/common/foot.jsp"%>
		