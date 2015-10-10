<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<%
strHtmlTitle += " - Place List ";
%>
<%@ include file="/common/head.jsp"%>
		<html:form action="/placeList.do">
<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
		<div align="center">
			<h2>
				<bean:message key="place.list.title" /><br>
			</h2>
		</div>
				<logic:present name="user" scope="session">
					<logic:equal name="user" property="canUpdate" value="true">
						<bean:define id="editAction" value='<%=Constants.ACTION_CREATE%>' />
						<html:link href="place.do" paramId="action" paramName="editAction">
							<bean:message key="place.insert" />
						</html:link>
						<html:link href="state.do" paramId="action" paramName="editAction">
							<bean:message key="state.insert" />
						</html:link>
						<html:link href="country.do" paramId="action" paramName="editAction">
							<bean:message key="country.insert" />
						</html:link>
					</logic:equal>
				</logic:present>
	</td>
</tr>	
			<%
				strPageUrl = strPageUrl + "placeList.do";
				String curCountryAbbrName = new String();
				String curStateAbbrName = new String();
				String curCityName = new String();			
			%>
<tr>
	<td colspan="<%=Constants.COLUMN_COUNT%>" >
		<table width="100%" border="0" cellspacing="0" cellpadding="0" align="left">
<nested:iterate id="listings" name="placeListForm" property="placeList" indexId="index">
			<bean:define id="countryAbbrName" name="listings" property="countryAbbr"/>
			<bean:define id="stateAbbrName" name="listings" property="stateAbbr"/>
			<bean:define id="cityName" name="listings" property="city"/>
		<tr>
			<%
			if (((String)countryAbbrName).equalsIgnoreCase(curCountryAbbrName)){
				out.println("<td>&nbsp;   </td>");
			} else {
			%>
			<td>
			<bean:write name="listings" property="countryAbbr"/>&nbsp;
			<logic:present name="user" scope="session">
				<logic:equal name="user" property="canUpdate" value="true">
					<html:link href="country.do" paramId="countryId" paramName="listings" paramProperty="countryId">
						<bean:message key="edit" />
					</html:link>
				</logic:equal>
			</logic:present>
			</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<%
				curCountryAbbrName = (String) countryAbbrName;
			}
			if (((String)stateAbbrName).equalsIgnoreCase(curStateAbbrName)){
				out.println("<td>&nbsp;  </td>");
			} else {
			%>
			<td>
			<bean:write name="listings" property="stateAbbr"/> &nbsp;
			<logic:present name="user" scope="session">
				<logic:equal name="user" property="canUpdate" value="true">
					<html:link href="state.do" paramId="stateId" paramName="listings" paramProperty="stateId">
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
			<td>&nbsp;</td>
			<%
					curStateAbbrName = (String) stateAbbrName;			
			}
			if (((String)cityName).equalsIgnoreCase(curCityName)){
				out.println("<td>&nbsp; </td>");
			} else {
			%>
			<td>
			<bean:write name="listings" property="city"/>
			</td>
			<%
					curCityName = (String) cityName;			
			}
			%>
				<td>
			<html:link href="list.do" paramId="placeId" paramName="listings" paramProperty="placeId">
				<bean:write name="listings" property="placeName"/>
			</html:link>&nbsp;
			<logic:present name="user" scope="session">
				<logic:equal name="user" property="canUpdate" value="true">
					<html:link href="place.do" paramId="placeId" paramName="listings" paramProperty="placeId">
						<bean:message key="edit" />
					</html:link>
				</logic:equal>
			</logic:present>
			</td>
		</tr>
</nested:iterate>

		</table>
	</td>
</tr>

		</html:form>
<%@ include file="/common/foot.jsp"%>
		 

