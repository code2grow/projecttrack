<%-- Edit the artifact list.
     Must be included in a panel grid with two columns. --%>

<h:outputLabel for="artifactSelect">
  <h:outputText value="Completed artifacts:"/>
</h:outputLabel>
<h:selectManyCheckbox id="artifactSelect" layout="pageDirection"
                      styleClass="project-input"
                      value="#{visit.currentProject.artifacts}"
                      converter="ArtifactType">
  <f:selectItems value="#{selectItems.artifacts}"/>
</h:selectManyCheckbox>
