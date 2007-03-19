<%@ page contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%-- pia-lab-begin(acegi-web) --%>
<%@ taglib prefix="authz" uri="http://acegisecurity.org/authz"%>


<authz:authorize ifAllGranted="ROLE_PROJECT_APPROVER">
<c:redirect url="/faces/protected/inbox.jsp"/>
</authz:authorize>
 
<authz:authorize ifAllGranted="ROLE_PROJECT_VIEWER">
 <c:redirect url="/faces/general/show_all.jsp"/>
</authz:authorize>

<authz:authorize ifNotGranted="ROLE_PROJECT_APPROVER,ROLE_PROJECT_VIEWER">
 <!--  On some application servers we end up here -->
 <c:redirect url="/acegilogin.jsp"/>
</authz:authorize>

<%-- pia-lab-end --%>

<%-- pia-lab-include-begin(acegi-web) 
 <c:redirect url="/faces/general/show_all.jsp"/>
     pia-lab-include-end --%>