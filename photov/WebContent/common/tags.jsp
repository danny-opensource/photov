<logic:present name="photo" scope="session">
		<logic:equal name="photoThumbs" property="contNew" value="true">
		<a href="/common/help.jsp" target="_blank" onclick="window.open('/common/help.jsp','_blank','directories = no, height=400, width=300, location=no, menubar=no, resizable=no, status=no, toolbar=no, top = 125, left=125','');return false;">
			<font size=-1 color=red><bean:message key="common.new" /></font></a></i>&nbsp;
		</a>
		</logic:equal>
		<logic:equal name="photoThumbs" property="contComplete"  value="false">
		<a href="/common/help.jsp" target="_blank" onclick="window.open('/common/help.jsp','_blank','directories = no, height=400, width=300, location=no, menubar=no, resizable=no, status=no, toolbar=no, top = 125, left=125','');return false;">
			<font size=-1 color=green><bean:message key="common.help" /></font></a></i>&nbsp;
		</logic:equal>
		<logic:equal name="photoThumbs" property="contPrivate"  value="true">
		<a href="/common/help.jsp" target="_blank" onclick="window.open('/common/help.jsp','_blank','directories = no, height=400, width=300, location=no, menubar=no, resizable=no, status=no, toolbar=no, top = 125, left=125','');return false;">
			<font size=-1 color=blue><bean:message key="common.private" /></font></a></i>&nbsp;
		</a>
		</logic:equal><br>
</logic:present>
