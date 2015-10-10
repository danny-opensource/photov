<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<%
strHtmlTitle += " - Message List ";
%>
<%@ include file="/common/head.jsp"%>
<logic:present name="user" scope="session">
	<logic:equal name="user" property="canUpdate" value="true">
	<tr>
	<td>
	<table border="0" cellspacing="0" cellpadding="0" align="left" width="100%">
	<nested:iterate id="photoThumbs" name="photo" indexId="index">
		<logic:equal name="index" value="0">
			<tr>
			  <td colspan="5" >
					<div align="center">
						<h2>
							<bean:message key="message.title"  />
						</h2>
					</div>
					<bean:define id="editAction" value='<%=Constants.ACTION_CREATE%>' />
					<html:link href="message.do" paramId="action" paramName="editAction">
						<bean:message key="message.insert" />
					</html:link>
				</td>
			</tr>
			<tr>
				<th colspan="1">
					&nbsp;
				</th>
				<th colspan="1">
					<bean:message key="th.message.id" />
				</th>
				<th colspan="1">
					<bean:message key="th.name" />
				</th>
				<th colspan="1">
					<bean:message key="th.message.subject" />
				</th>
				<th colspan="1">
					<bean:message key="th.message.text" />
				</th>
			</tr>
		</logic:equal>
	<tr>
		<td colspan="1">
			<html:link href="message.do" paramId="messageId" paramName="photoThumbs" paramProperty="messageId">
				<bean:message key="edit" />
			</html:link>&nbsp;
		</td>
		<td colspan="1">
			<bean:write name="photoThumbs" property="messageId" /> &nbsp;
		</td>
		<td colspan="1">
			<bean:write name="photoThumbs" property="messageName" /><br>
		</td>
		<td colspan="1">
			<bean:write name="photoThumbs" property="messageSubject" /><br>
		</td>
		<td colspan="1">
			<textarea rows="3" cols="20" ><bean:write name="photoThumbs" property="messageText" /></textarea>
		</td>
	</tr>
	</nested:iterate>
	</table>
	</td>
	</tr>
	</logic:equal>
</logic:present>
<%@ include file="/common/foot.jsp"%>
		