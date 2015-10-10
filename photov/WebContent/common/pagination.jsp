		<logic:notPresent name="peopleSearchForm" scope="session">
			<logic:notPresent name="newPhotoQueueForm" scope="session">
			<tr>
			  <td colspan="<%=Constants.COLUMN_COUNT%>" >
					<div align="left">
						<bean:message key="common.page" />
						<logic:iterate id="paginationLoop" name="pagination" indexId="idx">
							<html:link href="list.do" name="paginationLoop" property="paramMap">
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
		</logic:notPresent>
		</logic:notPresent>
		<logic:present name="peopleSearchForm" scope="session">
		<tr>
		  <td colspan="<%=Constants.COLUMN_COUNT%>" >
				<div align="left">
					<bean:message key="common.page" /> 
					<logic:iterate id="paginationLoop" name="pagination" indexId="idx">
						<html:link href="peopleSearch.do" name="paginationLoop" property="paramMap">
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
		</logic:present>