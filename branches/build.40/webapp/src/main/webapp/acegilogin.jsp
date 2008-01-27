<%--
   JavaServer Faces in Action example code, Copyright (C) 2004 Kito D. Mann.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core'%>
<%@ page import="org.acegisecurity.ui.AbstractProcessingFilter"%>
<%@ page
	import="org.acegisecurity.ui.webapp.AuthenticationProcessingFilter"%>
<%@ page import="org.acegisecurity.AuthenticationException"%>

<html>
<head>
<title>Login</title>
<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/stylesheet.css" />
</head>

<body>

<table align="center">
	<tr>
	<td><img src="<%= request.getContextPath() %>/images/logo.gif" /></td>
	<td>
	<h1>Welcome to Project Track</h1>
	<c:if test="${not empty param.login_error}">
			<font color="red"> Your login attempt was not successful, try
			again.<BR>
			<BR>
			Reason: <%=((AuthenticationException) session
										.getAttribute(AbstractProcessingFilter.ACEGI_SECURITY_LAST_EXCEPTION_KEY))
										.getMessage()%>
			</font>
		</c:if>

		<form action="<c:url value='j_acegi_security_check'/>" method="POST">
		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='j_username'
					<c:if test="${not empty param.login_error}">value='<%= session.getAttribute(AuthenticationProcessingFilter.ACEGI_SECURITY_LAST_USERNAME_KEY) %>'</c:if>></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='j_password'></td>
			</tr>
			<tr>
				<td colspan='2'><input name="Login" type="submit" value="Login"></td>
			</tr>
		</table>

		</form>

		<p>Login accounts: upper_mgr, proj_mgr, analyst, dev_mgr, sys_mgr,
		qa_mgr; password is always "faces"</p>
	</td>
	</tr>
</table>


</body>
</html>
