<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<%
strHtmlTitle += " - People Search ";
%>
<%@ include file="/common/head.jsp"%>
		<html:form action="/peopleSearch.do">
<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
		<div align="center">
			<h2>
				<bean:message key="people.search.title" />
			</h2>
		</div>
				<logic:present name="user" scope="session">
					<logic:equal name="user" property="canUpdate" value="true">
						<bean:define id="editAction" value='<%=Constants.ACTION_CREATE%>' />
						<html:link href="person.do" paramId="action" paramName="editAction">
							<bean:message key="person.insert" />
						</html:link>
					</logic:equal>
				</logic:present>
<bean:define id="pageUrl" name="peopleSearchForm" property="searchString" />
<% strPageUrl = strPageUrl + "peopleSearch.do?searchString=" + pageUrl; %>
	</td>
</tr>	
<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" align="center">
<bean:define id="allLt" name="peopleSearchForm" property="allLetters" type="java.util.ArrayList"/>
<bean:define id="lt" name="peopleSearchForm" property="letters" type="java.util.ArrayList"/>
<%
	for (int i = 0 ; i < allLt.size(); i++) {
		if (lt.contains(allLt.get(i))){
%>		<a href="peopleSearch.do?searchString=<%=allLt.get(i)%>" ><%=allLt.get(i)%></a>&nbsp; <%
		} else {
			out.println(allLt.get(i)+ "&nbsp;");
		}
	
	}
%>
		<hr>
	</td>
</tr>
<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" align="center">
  <table>
		<tr>
			<td colspan="1" >
				<html:text property="searchString" size="32" /><html:errors property="searchString"/></br>
			</td>
			<td colspan="1" >
				<html:submit  property="action" value="<%=Constants.ACTION_LASTNAME%>"  />
				<html:submit  property="action" value="<%=Constants.ACTION_FIRSTNAME%>"  />
			</td>
		</tr>
	</table>
	</td>
</tr>
<tr>
	<td valign="top">
		<div align="left">
<nested:iterate id="peoples" name="peopleSearchForm" property="peopleRanking" indexId="index">
			<logic:present name="user" scope="session">
				<logic:equal name="user" property="canUpdate" value="true">
					<html:link href="person.do" paramId="personId" paramName="peoples" paramProperty="personId">
					        <bean:message key="edit" />
					</html:link>
				</logic:equal>
			</logic:present>
			<html:link href="list.do" paramId="personId" paramName="peoples" paramProperty="personId">
				<bean:write name="peoples" property="personLName"/>
			</html:link><br>
			<bean:define id="pgOne" name="peoples" property="pageOne"  />
			<bean:define id="pgTwo" name="peoples" property="pageTwo"  />
<%	if (index.compareTo((Integer) pgOne) == 0){ %>			
		</div>
		</td>
		<td valign="top">
		<div align="left">
<%	} else if (index.compareTo((Integer) pgTwo) == 0) { 	%>
		</div>
		</td>
		<td valign="top">
		<div align="left">
<%	}	%>
</nested:iterate>
		</div>
	</td>
</tr>
		</html:form>
<%@ include file="/common/foot.jsp"%>
		 

