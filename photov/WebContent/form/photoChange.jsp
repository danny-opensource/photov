<%@ include file="/common/version.jsp"%>
<%@ include file="/common/preHead.jsp"%>
<bean:define id="tName" name="photoChangeForm" property="photoId" />
<%
strHtmlTitle += " - Hide Photo - " + tName;
%>
<%@ include file="/common/head.jsp"%>
		<html:form action="/photoChange.do">
<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
		<div align="center">
			<h2>
				<bean:message key="photo.hide" />
			</h2>
		</div>
	</td>
</tr>	
<tr>
  <td colspan="1" >
			&nbsp;
	</td>
  <td colspan="1" >
			<html:text property="photoId" readonly="true" />
			
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
	</td>
</tr>
<tr>
  <td colspan="1" >
			<bean:message key="photo.hide.reason" />
	</td>
  <td colspan="1" >
			<html:hidden property="photoId"/>
			<html:textarea rows="8" cols="40" property="reason"/>
	</td>
  <td colspan="<%=Constants.COLUMN_COUNT-2%>" >
  	&nbsp;
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
		 

