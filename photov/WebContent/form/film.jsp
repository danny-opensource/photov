<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<%
strHtmlTitle += " - Film List ";
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
			  <td colspan="<%=Constants.COLUMN_COUNT%>" >
					<div align="center">
						<h2>
							<bean:message key="film.title"  />
						</h2>
					</div>
					<bean:define id="editAction" value='<%=Constants.ACTION_CREATE%>' />
					<html:link href="film.do" paramId="action" paramName="editAction">
						<bean:message key="film.insert" />
					</html:link>
				</td>
			</tr>
			<tr>
				<th colspan="1">
					&nbsp;
				</th>
				<th colspan="1">
					<bean:message key="th.film.filmid" />
				</th>
				<th colspan="1">
					<bean:message key="th.name" />
				</th>
			</tr>
		</logic:equal>
	<tr>
		<td colspan="1">
			<html:link href="film.do" paramId="filmId" paramName="photoThumbs" paramProperty="filmId">
				<bean:message key="edit" />
			</html:link>
		</td>
		<td colspan="1">
			<bean:write name="photoThumbs" property="filmId" /> &nbsp;
		</td>
		<td colspan="<%=Constants.COLUMN_COUNT-2%>">
			<bean:write name="photoThumbs" property="filmName" /><br>
		</td>
	</tr>
	</nested:iterate>
	</table>
	</td>
	</tr>
	</logic:equal>
</logic:present>
<%@ include file="/common/foot.jsp"%>
		