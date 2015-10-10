<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<jsp:useBean id="place" scope="session" class="net.wgbv.photov.objects.PlaceDetails" />
<bean:define id="tName" name="place" property="placeFullName" />
<%
strHtmlTitle += " - View Photos taken at " + tName;
%>
<%@ include file="/common/head.jsp"%>
<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
 		<div align="center">
			<h2>
				<bean:write name="place" property="placeFullName" />
				<logic:present name="user" scope="session">
					<logic:equal name="user" property="canUpdate" value="true">
						<html:link href="place.do" paramId="placeId" paramName="place" paramProperty="placeId">
							<bean:message key="edit" />
						</html:link><br>
						<bean:define id="editAction" value='<%=Constants.ACTION_CREATE%>' />
						<html:link href="place.do" paramId="action" paramName="editAction">
							<bean:message key="place.insert" />
						</html:link>
					</logic:equal>
				</logic:present>
			</h2>
		</div>
	</td>
</tr>
<%@ include file="/common/pagination.jsp"%>
<tr>
<bean:define id="pageUrl" name="place" property="placeId" />
<% strPageUrl = strPageUrl + "list.do?placeId=" + pageUrl; %>

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
<%@ include file="/common/foot.jsp"%>
		