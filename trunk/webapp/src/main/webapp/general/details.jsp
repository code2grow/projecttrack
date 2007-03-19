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
      <h:outputText value="ProjectTrack - Project details"/>
    </title>
    <link rel="stylesheet" type="text/css"
          href="<%= request.getContextPath() %>/stylesheet.css"/>
  </head>

<body class="page-background">

<jsp:include page="/includes/header.jsp"/>

<h:form>

  <h:panelGrid id="projectPanel" columns="2" cellpadding="5"
      footerClass="project-background"
      columnClasses=",project-data"
      styleClass="project-background"
      rowClasses="project-row">

    <%-- Header --%>

    <f:facet name="header">
      <h:panelGrid columns="1" width="100%" cellpadding="3"
                    styleClass="project-background" rowClasses="page-header">
          <h:outputText value="Project details"/>
      </h:panelGrid>
    </f:facet>

    <%-- Panel data elements --%>

    <%@ include file="/includes/project_info.jsp" %>  <!>

    <h:outputText value="Completed artifacts:"/>
<%--    <h:panelGrid columns="1" rowClasses="project-data"
                 cellpadding="0" cellspacing="0">--%>
      <h:dataTable value="#{visit.currentProject.artifacts}" var="artifact" rowClasses="project-data"
                 cellpadding="0" cellspacing="0">  <!>
        <h:column>
          <h:outputText value="#{artifact}"/>
        </h:column>
      </h:dataTable>

<%--    </h:panelGrid> --%>

    <%-- Footer - history --%>

    <f:facet name="footer">

    <h:panelGroup>
        <h:dataTable cellpadding="5" styleClass="table-background"
                   value="#{showHistoryBean.currentProjectHistory}"
                   var="operation" binding="#{showHistoryBean.historyDataTable}"
                   rows="#{showHistoryBean.rowsPerPage}">

         <f:facet name="header">
           <h:outputText value="History" styleClass="table-header"/>
         </f:facet>

         <h:column>
           <h:panelGrid columns="1" width="100%" border="1"
                        styleClass="table-even-row">

            <h:panelGrid columns="3" cellpadding="7"
                         styleClass="table-even-row">
              <h:outputText value="#{operation.timestamp}">           <!>
                <f:convertDateTime dateStyle="full" timeStyle="short"/>
              </h:outputText>
              <h:outputText value="#{operation.fromStatus} -> #{operation.toStatus}"/>    <!>
              <h:outputText value="(#{operation.user.role})"/>           	              <!>
            </h:panelGrid>

            <h:panelGrid columns="1" cellpadding="3"
                         styleClass="table-odd-row" width="100%">
              <h:outputText value="Comments:"/>
              <h:outputText value="#{operation.comments}"
                            styleClass="project-data"/>             <!>
            </h:panelGrid>
           </h:panelGrid>
         </h:column>

         <f:facet name="footer">
           <h:panelGroup>
             <h:commandLink actionListener="#{showHistoryBean.previous}"
                            rendered="#{showHistoryBean.showPrevious}"
                            style="padding-right: 5px;">
               <h:outputText value="Previous"/>
             </h:commandLink>
             <h:commandLink actionListener="#{showHistoryBean.next}"
                            rendered="#{showHistoryBean.showNext}">
               <h:outputText value="Next"/>
             </h:commandLink>
           </h:panelGroup>
         </f:facet>

        </h:dataTable>

       <h:commandButton value="Ok" action="#{showHistoryBean.cancel}"
                        immediate="true" style="margin-top: 5px"/>

    </h:panelGroup>
    </f:facet>

  </h:panelGrid>

</h:form>

</body>
</html>
</f:view>
