<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<%@ include file="/common/head.jsp"%>
<jsp:useBean id="pagination" scope="session" class="java.util.ArrayList" />
<tr>
<nested:iterate id="photoThumbs" name="photo" indexId="index">
	<logic:equal name="index" value="0">
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
  		<div align="center">
			<h2>
				<bean:write name="photoThumbs" property="headingName" filter="false"  />
				<logic:present name="user" scope="session">
					<logic:equal name="user" property="canUpdate" value="true">
						<html:link href="heading.do" paramId="headingId" paramName="photoThumbs" paramProperty="headingId">
							<bean:message key="edit" />
						</html:link><br>
						<bean:define id="editAction" value='<%=Constants.ACTION_CREATE%>' />
						<html:link href="photo.do" paramId="action" paramName="editAction">
							<bean:message key="photo.insert" />
						</html:link>
					</logic:equal>
				</logic:present>
			</h2>
			</div>
	</td>
</tr>
<bean:define id="pageUrl" name="photoThumbs" property="headingId" />
<% strPageUrl = strPageUrl + "list.do?headingId=" + pageUrl; %>
<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
		<div align="left">
			<html:link href="list.do" >
				<bean:message key="topic.title" />
			</html:link> &gt; 
			<html:link href="list.do" paramId="topicId" paramName="photoThumbs" paramProperty="topicId">
				<bean:write name="photoThumbs" property="topicName" filter="false"  />
			</html:link> &gt; 
			<html:link href="list.do" paramId="categoryId" paramName="photoThumbs" paramProperty="categoryId">
				<bean:write name="photoThumbs" property="categoryName" filter="false"  /><p>
			</html:link>
		</div>
	</td>
</tr>	
<%@ include file="/common/pagination.jsp"%>
<tr>
	</logic:equal>

	<td>
		<div align="center">
<%@ include file="/common/tags.jsp"%>
				<logic:present name="user" scope="session">
					<logic:equal name="user" property="canUpdate" value="true">
						<html:link href="photo.do" paramId="photoId" paramName="photoThumbs" paramProperty="photoId">
							<bean:message key="edit" />
						</html:link><br>
					</logic:equal>
				</logic:present>
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
</nested:iterate>
</tr>
<%@ include file="/common/pagination.jsp"%>
<%@ include file="/common/foot.jsp"%>
		