<tr>
  <td colspan="6" >
		<div align="left">
			<bean:message key="common.page" />
			<logic:iterate id="paginationLoop" name="pagination" indexId="idx">
				<html:link href="auditQueue.do" name="paginationLoop" property="paramMap">
					<% if (((PageNumber) paginationLoop).getPageNumber() == ((PageNumber) paginationLoop).getCurrentPageNumber()) {
					%><b><bean:write name="paginationLoop" property="pageDisplayNumber" /></b><%
					} else {
					%><bean:write name="paginationLoop" property="pageDisplayNumber" /><% }	%>
				</html:link>
			</logic:iterate>		
			<p>
		</div>
	</td>
</tr>
