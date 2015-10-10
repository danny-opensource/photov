<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<%@ include file="/common/head.jsp"%>
<nested:iterate id="photoThumbs" name="photo" indexId="index" length="1">
<tr>
	<td colspan="<%=Constants.COLUMN_COUNT%>" >
		<BR>
		<div align="center">
			<font size="+2">
				<b> 
					<bean:write name="photoThumbs" property="topicName" filter="false"   /> - 
					<bean:write name="photoThumbs" property="categoryName" filter="false"  /> - 
					<bean:write name="photoThumbs" property="headingName" filter="false"  />
				</b>
			</font>
		</div>
		<BR>
	</td>
</tr>
<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
		<div align="left">
			<html:link href="list.do" >
				<bean:message key="topic.title" />
			</html:link> &gt; 
			<html:link href="list.do" paramId="topicId" paramName="photoThumbs" paramProperty="topicId">
				<bean:write name="photoThumbs" property="topicName" filter="false"   />
			</html:link> &gt; 
			<html:link href="list.do" paramId="categoryId" paramName="photoThumbs" paramProperty="categoryId">
				<bean:write name="photoThumbs" property="categoryName" filter="false"  />
			</html:link> &gt;
			<html:link href="list.do" paramId="headingId" paramName="photoThumbs" paramProperty="headingId">
				<bean:write name="photoThumbs" property="headingName" filter="false"  /><p>
			</html:link>
		</div>
	</td>
</tr>	
				<logic:present name="user" scope="session">
					<logic:equal name="user" property="canUpdate" value="true">
<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
		<div align="left">
			<bean:define id="editAction" value='<%=Constants.ACTION_CREATE%>' />
			<html:link href="photo.do" paramId="action" paramName="editAction">
				<bean:message key="photo.insert" />
			</html:link>
			<html:link href="photo.do" paramId="photoId" paramName="photoThumbs" paramProperty="photoId">
				<bean:message key="photo.edit" />
			</html:link>
		</div>
	</td>
</tr>	
					</logic:equal>
				</logic:present>
<bean:define id="pageUrl" name="photoThumbs" property="photoId" />
<% strPageUrl = strPageUrl + "list.do?photoId=" + pageUrl; %>
<tr>
	<td  valign="top" align="left">
		<div valign="top" align="left">
			<font size=-1>
				<b>
					People:<br>
				</b>
			</font>	
				<logic:iterate id="photoPeople" name="photoThumbs" property="people" indexId="index2" >
					<html:link href="list.do" paramId="personId" paramName="photoPeople" paramProperty="personId">
						<bean:write name="photoPeople" property="personName"/>
					</html:link>
					<br>
				</logic:iterate>
			<p>
			<font size=-1>
				<b>
					Place:<br>
				</b>
			</font>	
				<html:link href="list.do" paramId="placeId" paramName="photoThumbs" paramProperty="placeId">
					<bean:write name="photoThumbs" property="placeName"/><br>
					<bean:write name="photoThumbs" property="placeCityName"/>,
					<bean:write name="photoThumbs" property="placeStateAbbr"/>
					<bean:write name="photoThumbs" property="placeCountryAbbr"/>
				</html:link>								
			<p>
			<font size=-1>
				<b>
					Topic:
				</b>
			</font>	
			<html:link href="list.do" paramId="topicId" paramName="photoThumbs" paramProperty="topicId">
				<bean:write name="photoThumbs" property="topicName"  filter="false" />
			</html:link><br>
			<font size=-1>
				<b>
					Category:
				</b>
			</font>	
			<html:link href="list.do" paramId="categoryId" paramName="photoThumbs" paramProperty="categoryId">
				<bean:write name="photoThumbs" property="categoryName"  filter="false" /> 
			</html:link><br>
			<font size=-1>
				<b>
					Heading:
				</b>
			</font>	
			<html:link href="list.do" paramId="headingId" paramName="photoThumbs" paramProperty="headingId">
				<bean:write name="photoThumbs" property="headingName"  filter="false" />
			</html:link><br>
			<p>
			<font size=-1>
				<b>
					Photographer:
				</b>
			</font>	
			<bean:write name="photoThumbs" property="photographerName" /><br>
			<font size=-1>
				<b>
					Camera:
				</b>
			</font>	
			<bean:write name="photoThumbs" property="cameraName" /><br>
			<font size=-1>
				<b>
					Film:
				</b>
			</font>	
			<bean:write name="photoThumbs" property="filmName" /><br>
			<font size=-1>
				<b>
					Date:
				</b>
			</font>	
			<bean:write name="photoThumbs" property="date" /><br>
		</div>
	</td>
	<td>
		&nbsp;
	</td>
	<td>
		<div align="center" valign="top">
			<img border="0" src="<bean:write name="photoThumbs" property="photoUrl"/>">
		<!-- <bean:write name="photoThumbs" property="photoId" /> -->
		</div>
		<div align="center" valign="top">
			<bean:write name="photoThumbs" property="title" filter="false"   />
		</div>
		<br>
	</td>
</tr>
<tr>
	<td colspan="<%=Constants.COLUMN_COUNT%> ">
		&nbsp;<br>
		<html:link href="photoChange.do?action=photoChange" paramId="photoId" paramName="photoThumbs" paramProperty="photoId">
			<bean:message key="photo.hide" /> 
		</html:link>
		<font size=-1 color=red><bean:message key="common.new" /></font></a></i>&nbsp;
		<p>
	</td>
</tr>
<tr>
	<td colspan="<%=Constants.COLUMN_COUNT%> ">
		<div align="center" valign="top">
<table border="2" frame="10"  cellspacing="0" cellpadding="0" width="400" height="150" >
<tr  align="left" valign="top">
	<td>
			<b>Notes: </b><br><bean:write name="photoThumbs" property="notes" filter="false" />
	</td>
</tr>
</table>
		</div>	
	</td>
</tr>
</nested:iterate>

<%@ include file="/common/foot.jsp"%>
		