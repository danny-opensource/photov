<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<%
strHtmlTitle += " - All Topics";
%>
<%@ include file="/common/head.jsp"%>

<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
  		<div align="center">
			<h2><bean:message key="topic.title" /> <br>
				<logic:present name="user" scope="session">
					<logic:equal name="user" property="canUpdate" value="true">
						<bean:define id="editAction" value='<%=Constants.ACTION_CREATE%>' />
						<html:link href="topic.do" paramId="action" paramName="editAction">
							<bean:message key="topic.insert" />
						</html:link>
					</logic:equal>
				</logic:present>
				</h2>
			</div>
	</td>
</tr>
<tr>
<% strPageUrl = strPageUrl + "list.do"; %>

<nested:iterate id="photoThumbs" name="photo" indexId="index">
	<td>
		<div align="center">
			<b>
				<bean:write name="photoThumbs" property="topicName" filter="false"  /><br>
			</b>
<%@ include file="/common/tags.jsp"%>
			<html:link href="list.do" paramId="topicId" paramName="photoThumbs" paramProperty="topicId">
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
<%@ include file="/common/foot.jsp"%>
		