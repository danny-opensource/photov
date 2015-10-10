<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<%
strHtmlTitle += " - Edit State ";
%>
<%@ include file="/common/head.jsp"%>
		<html:form action="/state.do">
<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
		<div align="center">
			<h2>
				<bean:message key="state.edit" />
			</h2>
		</div>
		<bean:define id="editAction" value='<%=Constants.ACTION_CREATE%>' />
		<html:link href="state.do" paramId="action" paramName="editAction">
			<bean:message key="insert" />
		</html:link>
		
	</td>
</tr>	
<tr>
  <td colspan="1" >
			&nbsp;
	</td>
  <td colspan="1" >
			<html:text property="stateId" readonly="true" />
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="state.name" />
	</td>
  <td colspan="1" >
			<html:hidden property="stateId"/><html:errors property="stateId"/>
			<html:text property="stateName"/><html:errors property="stateName"/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="state.abbr" />
	</td>
  <td colspan="1" >
		<html:text property="stateAbbr"/><html:errors property="stateAbbr"/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="place.country" />
	</td>
  <td colspan="1" >
		<html:select property="countryId" onchange="carSubmit(this.form)" > 
		  <html:optionsCollection name="stateForm" property="countries" label="countryName" value="countryId" />
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
		 

