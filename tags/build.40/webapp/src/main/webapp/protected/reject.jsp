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

<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<f:view>
<html>
  <head>
    <title>
      <h:outputText value="ProjectTrack - Reject a Project"/>
    </title>
    <link rel="stylesheet" type="text/css"
       href="<%= request.getContextPath() %>/stylesheet.css"/>
  </head>

<body class="page-background">

<jsp:include page="/includes/header.jsp"/>

<h:form>

  <h:panelGrid columns="2" cellpadding="5"
      footerClass="project-background"
      styleClass="project-background"
      rowClasses="project-row">

    <f:facet name="header">
      <h:panelGrid columns="1" width="100%" cellpadding="3"
                   styleClass="project-background" headerClass="page-header">
        <f:facet name="header">
          <h:outputText value="Reject a project"/>
        </f:facet>
        <h:messages showDetail="true" styleClass="errors"/>   <!>
      </h:panelGrid>
    </f:facet>

    <%-- Panel data elements --%>

    <%@ include file="/includes/project_info.jsp"%>          <!>
    <%@ include file="/includes/project_artifacts.jsp"%>     <!>

    <%-- Footer --%>

    <f:facet name="footer">
      <h:panelGroup>

        <%@ include file="/includes/project_comments.jsp"%>   <!>

        <%-- Button panel --%>
        <h:panelGrid columns="2" rowClasses="table-odd-row">
          <h:commandButton value="Reject" action="#{updateProjectBean.reject}"/>  <!>
          <h:commandButton value="Cancel" action="#{updateProjectBean.cancel}"
                           immediate="true"/>             <!>
        </h:panelGrid>

      </h:panelGroup>
    </f:facet>

  </h:panelGrid>

</h:form>

</body>
</html>
</f:view>
