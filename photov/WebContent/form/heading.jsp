<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<%@ include file="/common/head.jsp"%>

<tr>

<nested:iterate id="photoThumbs" name="photo" indexId="index">
	<logic:equal name="index" value="0">
	<bean:define id="pageUrl" name="photoThumbs" property="categoryId" />
	<% strPageUrl = strPageUrl + "list.do?categoryId=" + pageUrl; %>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
  		<div align="center">
			<h2>
				<bean:write name="photoThumbs" property="categoryName" filter="false"  />
				<logic:present name="user" scope="session">
					<logic:equal name="user" property="canUpdate" value="true">
						<html:link href="category.do" paramId="categoryId" paramName="photoThumbs" paramProperty="categoryId">
							<bean:message key="edit" />
						</html:link><br>
						<bean:define id="editAction" value='<%=Constants.ACTION_CREATE%>' />
						<html:link href="heading.do" paramId="action" paramName="editAction">
							<bean:message key="heading.insert" />
						</html:link>
					</logic:equal>
				</logic:present>
			</h2>
			</div>
	</td>
</tr>
<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
		<div align="left">
			<html:link href="list.do" >
				<bean:message key="topic.title" />
			</html:link> &gt; 
			<html:link href="list.do" paramId="topicId" paramName="photoThumbs" paramProperty="topicId">
				<bean:write name="photoThumbs" property="topicName" filter="false"  />
			</html:link> <p>
		</div>
	</td>
</tr>	
<tr>
	</logic:equal>

	<td>
		<div align="center">
			<b>
				<bean:write name="photoThumbs" property="headingName" filter="false"  /><br>
			</b>
<%@ include file="/common/tags.jsp"%>
			<html:link href="list.do" paramId="headingId" paramName="photoThumbs" paramProperty="headingId">
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
		