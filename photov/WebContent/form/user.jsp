<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<%
strHtmlTitle += " - User List ";
%>
<%@ include file="/common/head.jsp"%>
<logic:present name="user" scope="session">
	<logic:equal name="user" property="canUpdate" value="true">
	<tr>
		<td colspan="<%=Constants.COLUMN_COUNT%>">
		<table border="0" cellspacing="0" cellpadding="0" align="left" width="100%">
	<nested:iterate id="photoThumbs" name="photo" indexId="index">
		<logic:equal name="index" value="0">
			<tr>
			  <td colspan="6" >
					<div align="center">
						<h2>
							<bean:message key="user.title"  />
						</h2>
					</div>
					<bean:define id="editAction" value='<%=Constants.ACTION_CREATE%>' />
					<html:link href="user.do" paramId="action" paramName="editAction">
						<bean:message key="user.insert" />
					</html:link>
				</td>
			</tr>
	<tr>
		<th colspan="1">
			&nbsp;
		</th>
		<th colspan="1">
			<bean:message key="th.user.userid"  />
		</th>
		<th colspan="1">
			<bean:message key="th.user.active"  />
		</th>
		<th colspan="1">
			<bean:message key="th.user.update"  />
		</th>
		<th colspan="1">
			<bean:message key="th.user.username"  />
		</th>
		<th colspan="1">
			<bean:message key="person.name"  />
		</th>
	</tr>
		</logic:equal>
	<tr>
		<td colspan="1">
			<html:link href="user.do" paramId="userId" paramName="photoThumbs" paramProperty="userId">
				<bean:message key="edit" />
			</html:link>
		</td>
		<td colspan="1">
			<bean:write name="photoThumbs" property="userId" /> &nbsp;
		</td>
		<td colspan="1">
			<bean:write name="photoThumbs" property="active" />&nbsp;
			<html:link href="user.do?action=active" paramId="userId" paramName="photoThumbs" paramProperty="userId">
				<bean:message key="user.toggle" />
			</html:link>
		</td>
		<td colspan="1">
			<bean:write name="photoThumbs" property="canUpdate" />
			<bean:define id="toggleActive" value='<%=Constants.ACTION_CREATE%>' />
			<html:link href="user.do?action=update" paramId="userId" paramName="photoThumbs" paramProperty="userId">
				<bean:message key="user.toggle" />
			</html:link>

		</td>
		<td colspan="1">
			<bean:write name="photoThumbs" property="username" />&nbsp;
		</td>
		<td colspan="1">
			<bean:write name="photoThumbs" property="personLName" />&nbsp;
			<br>
		</td>
	</tr>
	</nested:iterate>
	</table>
		</td>
	</tr>
	</logic:equal>
</logic:present>
<%@ include file="/common/foot.jsp"%>
		