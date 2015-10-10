<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<%
strHtmlTitle += " - People Ranking ";
%>
<%@ include file="/common/head.jsp"%>
<jsp:useBean id="personRanking" scope="session" class="java.util.ArrayList" />
<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
  		<div align="center">
			<h2>
				<bean:message key="people.ranking" />
				<logic:present name="user" scope="session">
					<logic:equal name="user" property="canUpdate" value="true">
						<bean:define id="editAction" value='<%=Constants.ACTION_CREATE%>' />
						<html:link href="person.do" paramId="action" paramName="editAction">
							<bean:message key="person.insert" />
						</html:link>
					</logic:equal>
				</logic:present>
			</h2>
			</div>
	</td>
</tr>
<% strPageUrl = strPageUrl + "peopleRanking.do"; %>
<tr>
	<td valign="top">
		<div align="left">
<%
	int rank = 0;		
	int curNPhotos = 0;
%>
<nested:iterate id="peoples" name="personRanking" indexId="index">
	<bean:define id="nPhotos" name="peoples" property="stringNumPhotos"  />
<%	if (curNPhotos != Integer.parseInt((String)nPhotos))
		{
			curNPhotos = Integer.parseInt((String)nPhotos);
			rank++;
			out.println(rank);
		} else {
			out.println("<font color='white'>" + rank + "</font>");
		} %>
			<html:link href="list.do" paramId="personId" paramName="peoples" paramProperty="personId">
				<bean:write name="peoples" property="personLNameCount"/>
			</html:link>
			<logic:equal name="peoples" property="newPhotos" value="true">
				<a href="/common/help.jsp" target="_blank" onclick="window.open('/common/help.jsp','_blank','directories = no, height=400, width=300, location=no, menubar=no, resizable=no, status=no, toolbar=no, top = 125, left=125','');return false;">
					<font size=-1 color=red>New</font></a></i>&nbsp;
				</a>
			</logic:equal>
			<logic:equal name="peoples" property="complete"  value="false">
<!--				<a href="/common/help.jsp" target="_blank" onclick="window.open('/common/help.jsp','_blank','directories = no, height=400, width=300, location=no, menubar=no, resizable=no, status=no, toolbar=no, top = 125, left=125','');return false;">
					<font size=-1 color=green>Help</font></a></i>&nbsp; -->
			</logic:equal>
			<logic:equal name="peoples" property="priv"  value="true">
				<a href="/common/help.jsp" target="_blank" onclick="window.open('/common/help.jsp','_blank','directories = no, height=400, width=300, location=no, menubar=no, resizable=no, status=no, toolbar=no, top = 125, left=125','');return false;">
					<font size=-1 color=blue>Priv</font></a></i>&nbsp;
				</a>
			</logic:equal>
			<br>
			<bean:define id="pgOne" name="peoples" property="pageOne"  />
			<bean:define id="pgTwo" name="peoples" property="pageTwo"  />
<%	if (index.compareTo(pgOne) == 0){ %>			
		</div>
		</td>
		<td valign="top">
		<div align="left">
<%	} else if (index.compareTo(pgTwo) == 0) { 	%>
		</div>
		</td>
		<td valign="top">
		<div align="left">
<%	}	%>
</nested:iterate>
		</div>
	</td>
</tr>

<%@ include file="/common/foot.jsp"%>
		