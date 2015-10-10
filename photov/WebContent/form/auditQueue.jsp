<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<%
strHtmlTitle += " - Audit Queue ";
%>
<%@ include file="/common/head.jsp"%>
<logic:present name="user" scope="session">
	<logic:equal name="user" property="canUpdate" value="true">
	<tr>
	<td>
	<table border="5" cellspacing="0" cellpadding="" align="left" width="100%">
	<nested:iterate id="photoThumbs" name="photo" indexId="index">
		<logic:equal name="index" value="0">
			<tr>
			  <td colspan="7" >
					<div align="center">
						<h2>
							<bean:message key="queue.audit"  />
						</h2>
					</div>
				</td>
			</tr>
			<tr>
				<th colspan="1">
<bean:message key="th.audit.queue.auditid" />
				</th>
				<th colspan="1">
<bean:message key="th.audit.queue.userid" />
				</th>
				<th colspan="1">
<bean:message key="th.audit.queue.user" />
				</th>
				<th colspan="1">
<bean:message key="th.audit.queue.photoid" />
				</th>
				<th colspan="1">
<bean:message key="th.audit.queue.completed" />
				</th>
				<th colspan="1">
<bean:message key="th.audit.queue.reviewed" />
				</th>
				<th colspan="1">
<bean:message key="th.audit.queue.approved" />
				</th>
			</tr>
		</logic:equal>
	<tr>
				<td colspan="1">
<bean:write name="photoThumbs" property="auditId" />
				</td>
				<td colspan="1">
<bean:write name="photoThumbs" property="userId" />
				</td>
				<td colspan="1">
<!-- bean:write name="photoThumbs" property="user" / --> &nbsp;
				</td>
				<td colspan="1">
		<html:link href="list.do" paramId="photoId" paramName="photoThumbs" paramProperty="photoId">
			<bean:write name="photoThumbs" property="photoId" />
		</html:link><br>
		
				</td>
				<td colspan="1">
<bean:write name="photoThumbs" property="completed" />
				</td>
				<td colspan="1">
			<html:link href="auditQueue.do?action=active" paramId="auditId" paramName="photoThumbs" paramProperty="auditId">
				<bean:message key="queue.audit.toggle" />
			</html:link>
			<bean:write name="photoThumbs" property="reviewed" />
				</td>
				<td colspan="1">
<bean:write name="photoThumbs" property="approved" />
				</td>
	</tr>
	</nested:iterate>
	</table>
	</td>
	</tr>
	</logic:equal>
</logic:present>
<%@ include file="/common/foot.jsp"%>
		