<%@ include file="/common/login.jsp"%>


<tr>
        <td colspan="<%=Constants.COLUMN_COUNT%>" >
                <p><br>
                <div align="left">
                        <hr>
                        <html:link href="peopleRanking.do">
                        	<bean:message key="people.ranking.title" />
                        </html:link><br>
												<bean:define id="edit3Action" value="<%=Constants.ACTION_CREATE%>" />
                        <html:link href="peopleSearch.do" paramId="action" paramName="edit3Action">
                        	<bean:message key="people.search.title" />
                        </html:link><p>
                        <html:link href="heirarchy.do">
                        	<bean:message key="heirarchy.title" />
                        </html:link><br>
                        <html:link href="placeList.do">
                        	<bean:message key="place.list.title" />
                        </html:link><br>
                        <hr>
                </div>
        </td>
</tr>
<tr>
        <td colspan="<%=Constants.COLUMN_COUNT%>" >
                <br>

                <a href="mailto:brownlow@wgbv.com?subject=<%=strPageUrl%>">
                        <img alt="" border=0 src="/photov/images/site/wgbv.net.help.jpg" >
                </a>
                <hr>
                <a href="mailto:brownlow@wgbv.com?subject=Copyright">
                        <img alt="" border=0 src="/photov/images/site/copy.jpg" >
                </a>
        </td>
</tr>
</table>&nbsp;

</body>
</html>
