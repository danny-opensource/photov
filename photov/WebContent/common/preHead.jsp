<%@ page contentType="text/html" %>
<%@ page language="java" import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.net.*" %>
<%@ page import="net.wgbv.photov.action.Constants" %>
<%@ page import="net.wgbv.photov.objects.*" %>
<%@ page import="java.math.BigInteger" %>
<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionMessages" %>
<%@ page import="org.apache.struts.action.ActionErrors" %>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested"%>
<!-- ------------------------------------------------------------------------- -->
<!-- <%=request.getRequestURI().substring(request.getContextPath().length())%> -->
<!-- ------------------------------------------------------------------------- -->
<%
        String strPageUrl = new String();
        String strBaseUrl = new String();
        String strHtmlTitle = new String();
        String strOnLoad = new String();
        boolean blNotComplete = false;
        boolean blNewPhoto = false;
        int intItemsAPage = Constants.PER_PAGE;
        strPageUrl = "http://www.wgbv.net/";
        strBaseUrl = "http://www.wgbv.net/";
        strHtmlTitle = "Photography";
%>