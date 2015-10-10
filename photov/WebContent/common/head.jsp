<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<html>

<head>
	<title><bean:message key="wgbv.head.title" /><%=strHtmlTitle%></title>
	<style type="text/css">
		<!--    A {
			color: blue;
		} -->
	</STYLE>
	<script language="javascript">
	  var submitTime = 0;
	  function formSubmit(form)
	  {
	        // wait 5 seconds from form submission to resubmit
	        var d = new Date().valueOf();   // date in milliseconds
	    if ( (d - submitTime) > 5000) {
	        // allow the form submit.
	      submitTime = d;
	      form.submit();
	    }
	    return false;
	  }
	
	function carSubmit(theForm) {
	  return formSubmit(theForm);
	}
	</script>
	
</head>

<body bgcolor="white" onLoad="<%=strOnLoad%>">

<table border="0" cellspacing="0" cellpadding="0" align="left">
	<tr>
		<td colspan="<%=Constants.COLUMN_COUNT%>" >
			<div align="center">
				<html:link href="list.do">
					<img border=0 height=75 src="/photov/images/site/wgbv_thin.jpg" width=600>
				</html:link>
			</div>
		</td>
	</tr>

	<tr>
		<td colspan="<%=Constants.COLUMN_COUNT%>" >
			<bean:message key="error.header" /> 
				<P>
					<html:errors property="<%=ActionMessages.GLOBAL_MESSAGE%>"/>
					<html:errors property="<%=ActionErrors.GLOBAL_ERROR%>"/>
				</P> 
			<bean:message key="error.footer" /> 
		</td>
	</tr>
	
<logic:present name="photo" scope="session">
	<jsp:useBean id="photo" scope="session" class="java.util.ArrayList" />
</logic:present>

<logic:present name="user" scope="session">
	<jsp:useBean id="user" scope="session" class="net.wgbv.photov.objects.User" />
</logic:present>
	
	