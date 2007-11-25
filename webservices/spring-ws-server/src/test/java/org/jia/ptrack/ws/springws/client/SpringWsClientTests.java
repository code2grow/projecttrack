package org.jia.ptrack.ws.springws.client;

import java.io.IOException;
import java.util.List;

import javax.xml.soap.Name;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.TransformerException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.transform.JDOMResult;
import org.jdom.transform.JDOMSource;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.springframework.ws.soap.security.xwss.XwsSecuritySecurementException;

import com.sun.xml.wss.ProcessingContext;
import com.sun.xml.wss.XWSSProcessor;
import com.sun.xml.wss.XWSSecurityException;

public class SpringWsClientTests extends AbstractDependencyInjectionSpringContextTests {
  
  private final class Version2Callback implements WebServiceMessageCallback {

    private static final String WSEE_URI = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
    private static final String WSSE_PREFIX = "wsse";

    public void doWithMessage(WebServiceMessage message) throws IOException,
        TransformerException {
      try {
        SaajSoapMessage saajSoapMessage = (SaajSoapMessage) message;

        SOAPMessage soapMessage = saajSoapMessage.getSaajMessage();

        SOAPPart soapPart = soapMessage.getSOAPPart();

        SOAPEnvelope soapEnvelope = soapPart.getEnvelope();

        SOAPHeader soapHeader = soapEnvelope.getHeader();

        Name headerElementName = soapEnvelope.createName("Security",
            WSSE_PREFIX, WSEE_URI);

        // Add "Security" soapHeaderElement to soapHeader
        SOAPHeaderElement soapHeaderElement = soapHeader
            .addHeaderElement(headerElementName);

        // This may be important for some portals!
        soapHeaderElement.setActor(null);

        // Add usernameToken to "Security" soapHeaderElement
        Name usernameTokenName = soapEnvelope.createName("UsernameToken",
            WSSE_PREFIX, WSEE_URI);

        SOAPElement usernameTokenSOAPElement = soapHeaderElement
            .addChildElement(usernameTokenName);

        Name usernameName = soapEnvelope.createName("Username", WSSE_PREFIX,
            WSEE_URI);

        // Add username to usernameToken
        SOAPElement userNameSOAPElement = usernameTokenSOAPElement
            .addChildElement(usernameName);

        userNameSOAPElement.addTextNode("proj_mgr");

        // Add password to usernameToken
        Name passwordName = soapEnvelope.createName("Password", WSSE_PREFIX,
            WSEE_URI);

        SOAPElement passwordSOAPElement = usernameTokenSOAPElement
            .addChildElement(passwordName);

        passwordSOAPElement.addTextNode("faces");
      } catch (SOAPException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private final class Version1Callback implements WebServiceMessageCallback {
    public void doWithMessage(WebServiceMessage message) throws IOException {
      SaajSoapMessage saajSoapMessage = (SaajSoapMessage) message;
      SOAPMessage saajMessage = saajSoapMessage.getSaajMessage();
      try {
        processingContext.setSOAPMessage(saajMessage);
        SOAPMessage securedMessage = cprocessor
            .secureOutboundMessage(processingContext);
        saajSoapMessage.setSaajMessage(securedMessage);
      } catch (XWSSecurityException e) {
        throw new XwsSecuritySecurementException(e.getMessage());
      }
    }
  }

  private ServerLauncher server;
  private WebServiceTemplate wsTemplate;
  private XWSSProcessor cprocessor;
  private ProcessingContext processingContext;

  @Override
  protected String[] getConfigLocations() {
    return new String[]{"/appCtxClient/client.xml"};
  }
  
  public void setWsTemplate(WebServiceTemplate wsTemplate) {
    this.wsTemplate = wsTemplate;
  }
  
  @Override
  protected void onSetUp() throws Exception {
    super.onSetUp();

    server = new ServerLauncher();
    server.start();

 /*
    ClassPathResource xwssConfig = new ClassPathResource(
        "clientSecurityPolicy.xml");
    if (!xwssConfig.exists()) {
      throw new FileNotFoundException();
    }

    XWSSProcessorFactory factory = XWSSProcessorFactory.newInstance();
    cprocessor = factory.createProcessorForSecurityConfiguration(xwssConfig
        .getInputStream(), null);
    processingContext = new ProcessingContext();
*/    
  }

  @Override
  protected void onTearDown() throws Exception {
    server.stop();
    super.onTearDown();
  }

  public void testGetProjectsWaitingForApproval() {
    Element element = new Element("ProjectsWaitingForApprovalRequest");
    Namespace ns = Namespace.getNamespace("pt",
        "http://www.chrisrichardson.net/schemas/ptrack-spring-ws");
    element.setNamespace(ns);

    Document document = new Document(element);

    JDOMSource source = new JDOMSource(document);
    JDOMResult result = new JDOMResult();

    wsTemplate.sendSourceAndReceiveToResult(source, new Version2Callback(),
        result);

    Document resultDoc = result.getDocument();
    Element resultElement = resultDoc.getRootElement();
    System.out.println("children: " + resultElement.getChildren().size());

    for (Element projectElement : (List<Element>) resultElement.getChildren()) {
      Element idElement = projectElement.getChild("Id", ns);
      Element nameElement = projectElement.getChild("Name", ns);

      System.out.println(idElement.getText());
      System.out.println(nameElement.getText());

    }

  }

}
