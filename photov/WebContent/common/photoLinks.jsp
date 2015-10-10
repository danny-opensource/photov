<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
		<div align="left">
			<bean:define id="editAction" value='<%=Constants.ACTION_CREATE%>' />
			<html:link href="photo.do" paramId="action" paramName="editAction">
				<bean:message key="photo.insert" />
			</html:link>&nbsp;
			<logic:present name="photoForm" scope="session">
				<html:link href="list.do" paramId="photoId" paramName="photoForm" paramProperty="photoId">
					<bean:message key="photo.view" />
				</html:link>
			</logic:present>
			<html:link href="newPhotoQueue.do">
				<bean:message key="queue.new.photos" />
			</html:link>
		</div>
	</td>
</tr>	
