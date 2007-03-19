<%-- Application header fragment. Intended to be included in another page.
     That page should have a reference to the stylesheet. --%>

<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="jsf-in-action-components" prefix="jia"%>

<f:loadBundle basename="ptrackResources" var="bundle"/>

<f:subview id="header">

  <h:form>

    <h:panelGrid columns="4" cellspacing="0" cellpadding="0"
                 styleClass="header" width="100%">

      <jia:navigatorToolbar id="toolbar" layout="horizontal"
           headerClass="toolbar-header" itemClass="toolbar-command"
           selectedItemClass="toolbar-command" iconClass="toolbar-icon"
           immediate="false">  <!>

        <f:facet name="header">
          <h:outputText value="#{bundle.AppNameHeader}:"/>
        </f:facet>
        <jia:navigatorItem name="inbox" label="#{bundle.InboxToolbarButton}" icon="/images/inbox.gif"
                           action="inbox" disabled="#{!visit.inboxAuthorized}"/>  <!>
        <jia:navigatorItem name="showAll" label="#{bundle.ShowAllToolbarButton}" icon="/images/show_all.gif"
                           action="show_all"/>
        <jia:navigatorItem name="createNew" label="#{bundle.CreateNewToolbarButton}" icon="/images/create.gif"
                           action="#{createProjectBean.create}" disabled="#{!visit.createNewAuthorized}"/> <!>
        <jia:navigatorItem name="logout" label="#{bundle.LogoutToolbarButton}" icon="/images/logout.gif"
                           direct="true" link='<%= request.getContextPath() + "/j_acegi_logout"%>'/>
      </jia:navigatorToolbar>

      <h:panelGroup>
        <h:outputLabel for="languageSelect">
          <h:outputText value="#{bundle.LanguageCaption}:"  styleClass="language-select"/>
        </h:outputLabel>
        <h:selectOneListbox id="languageSelect" size="1"
                            styleClass="language-select" value="#{visit.locale}">
          <f:selectItems value="#{visit.supportedtLocaleItems}"/>
<%-- For example only          <f:selectItems value="#{visit.extraLocaleItem}"/>  --%>
        </h:selectOneListbox>
        <h:commandButton value="#{bundle.LanguageButton}" styleClass="language-select-button"/>
      </h:panelGroup>

      <h:outputText value="(#{facesContext.externalContext.remoteUser})" styleClass="user-name"/> <!>

    </h:panelGrid>
  </h:form>
</f:subview>


