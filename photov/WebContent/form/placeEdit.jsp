<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<%
strHtmlTitle += " - Edit Place ";
%>
<%@ include file="/common/head.jsp"%>
		<html:form action="/place.do">
<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
		<div align="center">
			<h2>
				<bean:message key="place.edit" />
			</h2>
		</div>
		<bean:define id="editAction" value='<%=Constants.ACTION_CREATE%>' />
		<html:link href="place.do" paramId="action" paramName="editAction">
			<bean:message key="insert" />
		</html:link>&nbsp;
		<html:link href="list.do" paramId="placeId" paramName="placeForm" paramProperty="placeId">
			<bean:message key="view" />
		</html:link>
	</td>
</tr>	
<tr>
  <td colspan="1" >
			&nbsp;
	</td>
  <td colspan="1" >
			<html:text property="placeId" readonly="true" size="8" />
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="place.name" />
	</td>
  <td colspan="1" >
			<html:hidden property="placeId"/>
			<html:text property="placeName"/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="place.city" />
	</td>
  <td colspan="1" >
		<html:text property="city"/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="place.state" />
	</td>
  <td colspan="1" >
		<html:select property="stateId" > 
		  <html:optionsCollection name="placeForm" property="states" label="stateName" value="stateId" />
		</html:select>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
		<html:link href="state.do" paramId="action" paramName="editAction">
			<bean:message key="state.insert" />
		</html:link>
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="place.country" />
	</td>
  <td colspan="1" >
		<html:select property="countryId" onchange="carSubmit(this.form)" > 
		  <html:optionsCollection name="placeForm" property="countries" label="countryName" value="countryId" />
		</html:select>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
		<html:link href="country.do" paramId="action" paramName="editAction">
			<bean:message key="country.insert" />
		</html:link>
	</td>
</tr>
<tr>
  <td colspan="1" >
		<html:submit/>
	</td>
  <td colspan="1" >
	  <html:cancel/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
		</html:form>
<%@ include file="/common/foot.jsp"%>
		 

