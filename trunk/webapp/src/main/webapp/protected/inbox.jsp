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
      <h:outputText value="ProjectTrack - Inbox"/>
    </title>
    <link rel="stylesheet" type="text/css"
          href="<%= request.getContextPath() %>/stylesheet.css"/>
  </head>

<body class="page-background">

<jsp:include page="/includes/header.jsp"/>

<h:form id="inboxPage">

  <h:panelGrid headerClass="page-header" styleClass="table-background"
               columns="1" cellpadding="5">

    <%-- Header --%>
    <f:facet name="header">
      <h:outputText value="Inbox - approve or reject projects"/>
    </f:facet>

    <%-- Panel data --%>

    <h:messages globalOnly="true" styleClass="errors"/>

    <h:dataTable styleClass="table-background"
                 rowClasses="table-odd-row,table-even-row" cellpadding="3"
                 value="#{inboxBean.inboxProjects}"
                 var="project"
                 binding="#{inboxBean.projectTable}"
                 id="inboxTable">

      <h:column>
        <f:facet name="header">
          <h:commandLink styleClass="table-header" id="name"
                         actionListener="#{inboxBean.sort}">
            <h:outputText value="Project name"/>
    <%--        <f:param name="column" value="name"/>  --%>
          </h:commandLink>
        </f:facet>
        <h:outputText  id="projectName" value="#{project.name}"/>
      </h:column>

      <h:column>
        <f:facet name="header">
          <h:commandLink styleClass="table-header" id="type"
                         actionListener="#{inboxBean.sort}">
            <h:outputText value="Type"/>
    <%--        <f:param name="column" value="type"/> --%>
          </h:commandLink>
        </f:facet>
        <h:outputText value="#{project.type}"/>
      </h:column>

      <h:column>
        <f:facet name="header">
          <h:commandLink styleClass="table-header" id="status"
                         actionListener="#{inboxBean.sort}">
            <h:outputText value="Status"/>
    <%--        <f:param name="column" value="status"/> --%>
          </h:commandLink>
        </f:facet>
        <h:outputText value="#{project.status}"/>
      </h:column>

      <h:column>
        <h:commandLink id="approve" action="#{inboxBean.approve}">
          <h:outputText value="Approve"/>
        </h:commandLink>
      </h:column>

      <h:column>
        <h:commandLink action="#{inboxBean.reject}">
          <h:outputText value="Reject"/>
        </h:commandLink>
      </h:column>

      <h:column>
        <h:commandLink action="#{inboxBean.details}" id="details">
          <h:outputText value="Details"/>
        </h:commandLink>
      </h:column>

    </h:dataTable>

  </h:panelGrid>
</h:form>
</body>
</html>
</f:view>
