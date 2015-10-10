<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<%
strHtmlTitle += " - Site List ";
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
			  <td colspan="4" >
					<div align="center">
						<h2>
							<bean:message key="site.title"  />
						</h2>
					</div>
					<bean:define id="editAction" value='<%=Constants.ACTION_CREATE%>' />
					<html:link href="site.do" paramId="action" paramName="editAction">
						<bean:message key="site.insert" />
					</html:link>
					
				</td>
			</tr>
			<tr>
				<th colspan="1">
					&nbsp;
				</th>
				<th colspan="1">
					<bean:message key="th.site.siteid" />
				</th>
				<th colspan="1">
					<bean:message key="th.name" />
				</th>
				<th colspan="1">
					<bean:message key="th.url" />
				</th>
			</tr>
		</logic:equal>
	<tr>
		<td colspan="1">
			<html:link href="site.do" paramId="siteId" paramName="photoThumbs" paramProperty="siteId">
				<bean:message key="edit" />
			</html:link>
		</td>
		<td colspan="1">
			<bean:write name="photoThumbs" property="siteId" /> &nbsp;
		</td>
		<td colspan="1">
			<bean:write name="photoThumbs" property="siteName" /><br>
		</td>
		<td colspan="1">
			<bean:write name="photoThumbs" property="siteUrl" /><br>
		</td>
	</tr>
	</nested:iterate>
	</table>
	</td>
	</tr>
	</logic:equal>
</logic:present>
<%@ include file="/common/foot.jsp"%>
		