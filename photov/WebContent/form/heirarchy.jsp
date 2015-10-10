<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<%
strHtmlTitle += " - Heirarchy ";
%>
<%@ include file="/common/head.jsp"%>
		<html:form action="/peopleSearch.do">
<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
		<div align="center">
			<h2>
				<bean:message key="heirarchy.title" />
			</h2>
		</div>
				<logic:present name="user" scope="session">
					<logic:equal name="user" property="canUpdate" value="true">
						<bean:define id="editAction" value='<%=Constants.ACTION_CREATE%>' />
						<html:link href="heading.do" paramId="action" paramName="editAction">
							<bean:message key="heading.insert" />
						</html:link>
					</logic:equal>
				</logic:present>
<% strPageUrl = strPageUrl + "heirarchy.do"; %>
		
	</td>
</tr>	
			<%
				String curTopicName = new String();
				String curHeadingName = new String();
				String curCategoryName = new String();			
			%>

<nested:iterate id="listings" name="heirarchyForm" property="heirarchy" indexId="index">
			<bean:define id="tName" name="listings" property="topicName"/>
			<bean:define id="cName" name="listings" property="categoryName"/>
			<bean:define id="hName" name="listings" property="headingName"/>
		<tr>
			<%
			if (((String)tName).equalsIgnoreCase(curTopicName)){
				out.println("<td>&nbsp;   </td>");
			} else {
			%>
			<td>
				<html:link href="list.do" paramId="topicId" paramName="listings" paramProperty="topicId">
					<bean:write name="listings" property="topicName" filter="false" />
				</html:link>&nbsp;
				<logic:equal name="listings" property="topicNew" value="true">
				<a href="/common/help.jsp" target="_blank" onclick="window.open('/common/help.jsp','_blank','directories = no, height=400, width=300, location=no, menubar=no, resizable=no, status=no, toolbar=no, top = 125, left=125','');return false;">
					<i><font size=-1 color=red><bean:message key="common.new" /></font></i></a>&nbsp;
				</logic:equal>
				<logic:equal name="listings" property="topicComplete"  value="false">
				<a href="/common/help.jsp" target="_blank" onclick="window.open('/common/help.jsp','_blank','directories = no, height=400, width=300, location=no, menubar=no, resizable=no, status=no, toolbar=no, top = 125, left=125','');return false;">
					<i><font size=-1 color=green><bean:message key="common.help" /></font></i></a>&nbsp;
				</logic:equal>
				<logic:equal name="listings" property="topicPriv"  value="true">
				<a href="/common/help.jsp" target="_blank" onclick="window.open('/common/help.jsp','_blank','directories = no, height=400, width=300, location=no, menubar=no, resizable=no, status=no, toolbar=no, top = 125, left=125','');return false;">
					<i><font size=-1 color=blue><bean:message key="common.private" /></font></i></a>&nbsp;
				</logic:equal>
				&nbsp;
				<logic:present name="user" scope="session">
					<logic:equal name="user" property="canUpdate" value="true">
						<html:link href="topic.do" paramId="topicId" paramName="listings" paramProperty="topicId">
							<bean:message key="edit" />
						</html:link>												
					</logic:equal>
				</logic:present>
			</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<%
				curTopicName = (String) tName;
			}
			if (((String)cName).equalsIgnoreCase(curCategoryName)){
				out.println("<td>&nbsp;  </td>");
			} else {
			%>
			<td>
			<html:link href="list.do" paramId="categoryId" paramName="listings" paramProperty="categoryId">
				<bean:write name="listings" property="categoryName" filter="false" />
			</html:link>&nbsp;
			<logic:equal name="listings" property="categoryNew" value="true">
			<a href="/common/help.jsp" target="_blank" onclick="window.open('/common/help.jsp','_blank','directories = no, height=400, width=300, location=no, menubar=no, resizable=no, status=no, toolbar=no, top = 125, left=125','');return false;">
				<i><font size=-1 color=red><bean:message key="common.new" /></font></i></a>&nbsp;
			</logic:equal>
			<logic:equal name="listings" property="categoryComplete"  value="false">
			<a href="/common/help.jsp" target="_blank" onclick="window.open('/common/help.jsp','_blank','directories = no, height=400, width=300, location=no, menubar=no, resizable=no, status=no, toolbar=no, top = 125, left=125','');return false;">
				<i><font size=-1 color=green><bean:message key="common.help" /></font></i></a>&nbsp;
			</logic:equal>
			<logic:equal name="listings" property="categoryPriv"  value="true">
			<a href="/common/help.jsp" target="_blank" onclick="window.open('/common/help.jsp','_blank','directories = no, height=400, width=300, location=no, menubar=no, resizable=no, status=no, toolbar=no, top = 125, left=125','');return false;">
				<i><font size=-1 color=blue><bean:message key="common.private" /></font></i></a>&nbsp;
			</logic:equal>
			&nbsp;
				<logic:present name="user" scope="session">
					<logic:equal name="user" property="canUpdate" value="true">
						<html:link href="category.do" paramId="categoryId" paramName="listings" paramProperty="categoryId">
							<bean:message key="edit" />
						</html:link>												
					</logic:equal>
				</logic:present>
			</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<%
					curCategoryName = (String) cName;			
			}
			if (((String)hName).equalsIgnoreCase(curHeadingName)){
				out.println("<td>&nbsp; </td>");
			} else {
			%>
				<td>
			<html:link href="list.do" paramId="headingId" paramName="listings" paramProperty="headingId">
				<bean:write name="listings" property="headingName" filter="false" />
			</html:link>&nbsp;
			<logic:equal name="listings" property="headingNew" value="true">
			<a href="/common/help.jsp" target="_blank" onclick="window.open('/common/help.jsp','_blank','directories = no, height=400, width=300, location=no, menubar=no, resizable=no, status=no, toolbar=no, top = 125, left=125','');return false;">
				<i><font size=-1 color=red><bean:message key="common.new" /></font></i></a>&nbsp;
			</logic:equal>
			<logic:equal name="listings" property="headingComplete"  value="false">
			<a href="/common/help.jsp" target="_blank" onclick="window.open('/common/help.jsp','_blank','directories = no, height=400, width=300, location=no, menubar=no, resizable=no, status=no, toolbar=no, top = 125, left=125','');return false;">
				<i><font size=-1 color=green><bean:message key="common.help" /></font></i></a>&nbsp;
			</logic:equal>
			<logic:equal name="listings" property="headingPriv"  value="true">
			<a href="/common/help.jsp" target="_blank" onclick="window.open('/common/help.jsp','_blank','directories = no, height=400, width=300, location=no, menubar=no, resizable=no, status=no, toolbar=no, top = 125, left=125','');return false;">
				<i><font size=-1 color=blue><bean:message key="common.private" /></font></i></a>&nbsp;
			</logic:equal>
			&nbsp;
				<logic:present name="user" scope="session">
					<logic:equal name="user" property="canUpdate" value="true">
						<html:link href="heading.do" paramId="headingId" paramName="listings" paramProperty="headingId">
							<bean:message key="edit" />
						</html:link>												
					</logic:equal>
				</logic:present>
			</td>
			<%
					curHeadingName = (String) hName;			
			}
			
			%>
		</tr>
</nested:iterate>
		</html:form>
<%@ include file="/common/foot.jsp"%>
		 

