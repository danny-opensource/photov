<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<%
strHtmlTitle += " - New Photo Queue ";
%>
<%@ include file="/common/head.jsp"%>
<jsp:useBean id="pagination" scope="session" class="java.util.ArrayList" />
		<tr align="left" valign="top">
			<td colspan="<%=Constants.COLUMN_COUNT%>" >
				<table border="0" cellspacing="0" cellpadding="0" align="left" width="100%">

<nested:iterate id="photoThumbs" name="photo" indexId="index">
	<logic:equal name="index" value="0">
		<tr>
		  <td colspan="6" >
		  		<div align="center">
					<h2>
							<bean:message key="queue.new.photos" /><br>
							<bean:define id="editAction" value='<%=Constants.ACTION_CREATE%>' />
							<html:link href="photo.do" paramId="action" paramName="editAction">
								<bean:message key="photo.insert" />
							</html:link>
					</h2>
					</div>
			</td>
		</tr>
		<%@ include file="/common/npqPagination.jsp"%>
		<tr>
			<th>
				&nbsp; <!-- Photo -->
			</th>
			<th>
				<bean:message key="photo.photoid" />
			</th>
			<th>
				&nbsp; <!-- Tags -->
			</th>
			<th>
				&nbsp;
			</th>
			<th>
				<bean:message key="photo.title" />
			</th>
			<th>
				<bean:message key="photo.date" />
			</th>
		</tr>
	</logic:equal>

<tr>
	<td>
		<html:link href="photo.do" paramId="photoId" paramName="photoThumbs" paramProperty="photoId">
			<img height="55" width="50" src="<bean:write name="photoThumbs" property="photoThumbUrl"/>">
		</html:link><br>
	</td>
	<td>
		<bean:write name="photoThumbs" property="photoId" />
	</td>
	<td>
		<%@ include file="/common/tags.jsp"%>
	</td>
	<td>
		<html:link href="newPhotoQueue.do?action=remove" paramId="photoId" paramName="photoThumbs" paramProperty="photoId">
			<bean:message key="queue.remove"/>
		</html:link><br>
		<!-- html:checkbox name="photoThumbs" property="vertical" / -->
	</td>
	<td>
		<html:text readonly="true" name="photoThumbs" property="title" />
	</td>
	<td>
		<html:text readonly="true" name="photoThumbs" property="date" />
	</td>
</tr>
</nested:iterate>

				</table>
			</td>
		</tr>

<%@ include file="/common/npqPagination.jsp"%>
<%@ include file="/common/foot.jsp"%>
		