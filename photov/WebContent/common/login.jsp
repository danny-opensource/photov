<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
		<hr>
	</td>
<tr>
<logic:notPresent name="user" scope="session">
		<html:form action="/login.do">
<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >

			<bean:message key="login.username" /> : 
			<html:text property="username"/> 
			<br>
	</td>
</tr>
<tr>
  <td colspan="<%=Constants.COLUMN_COUNT%>" >
			<bean:message key="login.password" /> : 
			<html:password property="password"/> 
			<br>
	</td>
</tr>
<tr>
	<td colspan="<%=Constants.COLUMN_COUNT%>" >
			<html:submit/><html:cancel/><p>
			<html:link action="registration.do" >
				<bean:message key="registration.title" />
			</html:link>&nbsp;
	</td>
</tr>
		</html:form>
</logic:notPresent>
<logic:present name="user" scope="session">
<tr>
	<td colspan="<%=Constants.COLUMN_COUNT%>" >
		Logged In As: <bean:write name="user" property="personName" /><br>
		<html:link action="login.do" >
			<bean:message key="login.logout" />
		</html:link>&nbsp;<p>
	</td>
</tr>
	<logic:equal name="user" property="canUpdate" value="true">
		<bean:define id="edit2Action" value='<%=Constants.ACTION_CREATE%>' />
		<tr align="left" valign="top">
			<td colspan="<%=Constants.COLUMN_COUNT%>" >
				<table border="0" cellspacing="0" cellpadding="0" align="left" width="100%">
					<tr align="left" valign="top">
						<td colspan="1" >
							<html:link href="photo.do" paramId="action" paramName="edit2Action">
								<bean:message key="photo.insert" />
							</html:link>&nbsp;<br>
							<html:link href="bulk.do" paramId="action" paramName="edit2Action">
								<bean:message key="bulk.insert" />
							</html:link>&nbsp;<br>
							<html:link href="place.do" paramId="action" paramName="edit2Action">
								<bean:message key="place.insert" />
							</html:link>&nbsp;<br>
							<html:link href="person.do" paramId="action" paramName="edit2Action">
								<bean:message key="person.insert" />
							</html:link>&nbsp;<br>
						</td>
						<td colspan="1" >
							<html:link href="site.do" paramId="action" paramName="edit2Action">
								<bean:message key="insert" />
							</html:link>&nbsp;
							<html:link href="list.do?siteId=0" >
								<bean:message key="view" />
							</html:link>&nbsp;
							<bean:message key="site" /><br>
							<html:link href="topic.do" paramId="action" paramName="edit2Action">
								<bean:message key="topic.insert" />
							</html:link>&nbsp;<br>
							<html:link href="category.do" paramId="action" paramName="edit2Action">
								<bean:message key="category.insert" />
							</html:link>&nbsp;<br>
							<html:link href="heading.do" paramId="action" paramName="edit2Action">
								<bean:message key="heading.insert" />
							</html:link>&nbsp;<br>

						</td>
						<td colspan="1" >
							<html:link href="group.do" paramId="action" paramName="edit2Action">
								<bean:message key="insert" />
							</html:link>&nbsp;
							<html:link href="list.do?groupId=0">
								<bean:message key="view" />
							</html:link>&nbsp;
							<bean:message key="group" /><br>
							<html:link href="user.do" paramId="action" paramName="edit2Action">
								<bean:message key="insert" />
							</html:link>&nbsp;
							<html:link href="list.do?userId=0" >
								<bean:message key="view" />
							</html:link>&nbsp;
							<bean:message key="user" /><br>
							<html:link href="camera.do" paramId="action" paramName="edit2Action">
								<bean:message key="insert" />
							</html:link>&nbsp;
							<html:link href="list.do?cameraId=0" >
								<bean:message key="view" />
							</html:link>&nbsp;
							<bean:message key="camera" /><br>
							<html:link href="film.do" paramId="action" paramName="edit2Action">
								<bean:message key="insert" />
							</html:link>&nbsp;
							<html:link href="list.do?filmId=0" >
								<bean:message key="view" />
							</html:link>&nbsp;
							<bean:message key="film" /><br>
							<html:link href="message.do" paramId="action" paramName="edit2Action">
								<bean:message key="insert" />
							</html:link>&nbsp;
							<html:link href="list.do?messageId=0" >
								<bean:message key="view" />
							</html:link>&nbsp;
							<bean:message key="message" /><br>
							<p>
						</td>
						<td colspan="1" >
							<html:link href="newPhotoQueue.do" >
								<bean:message key="queue.new.photos" />
							</html:link>&nbsp;<br>
							<html:link href="auditQueue.do" >
								<bean:message key="queue.audit" />
							</html:link>&nbsp;<br>
							<html:link href="list.do?emailId=0" >
								<bean:message key="email.test" />
							</html:link>&nbsp;<br>
							<p>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</logic:equal>
		<tr align="left" valign="top">
			<td colspan="<%=Constants.COLUMN_COUNT-2%>" >
				<html:link href="user.do" paramId="userId" paramName="user" paramProperty="userId">
					<bean:message key="user.update.profile" />
				</html:link><br>
			</td>
		</tr>
</logic:present>
	