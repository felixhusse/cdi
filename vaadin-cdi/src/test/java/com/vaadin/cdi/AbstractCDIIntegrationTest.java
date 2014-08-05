package com.vaadin.cdi;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.MalformedURLException;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployer;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.vaadin.cdi.uis.DependentCDIEventListener;
import com.vaadin.cdi.uis.InstrumentedUI;
import com.vaadin.cdi.uis.InstrumentedView;
import com.vaadin.cdi.uis.NoViewProviderNavigationUI;
import com.vaadin.cdi.uis.ParameterizedNavigationUI;
import com.vaadin.cdi.uis.PlainAlternativeUI;
import com.vaadin.cdi.uis.PlainColidingAlternativeUI;
import com.vaadin.cdi.uis.PlainUI;
import com.vaadin.cdi.uis.RootUI;
import com.vaadin.cdi.uis.RootWithCustomMappingUI;
import com.vaadin.cdi.uis.ScopedInstrumentedView;
import com.vaadin.cdi.uis.SecondUI;
import com.vaadin.cdi.uis.UIWithCDIDependentListener;
import com.vaadin.cdi.uis.UIWithCDISelfListener;
import com.vaadin.cdi.uis.ViewWithoutAnnotation;
import com.vaadin.cdi.uis.WithAnnotationRegisteredView;

@RunWith(Arquillian.class)
abstract public class AbstractCDIIntegrationTest {

    @Drone
    WebDriver firstWindow;

    @ArquillianResource
    Deployer deployer;
    
    protected static final By LABEL = By.id("label");
    protected static final By VIEW_LABEL = By.id("view");
    protected static final By BUTTON = By.id("button");
    protected static final By NAVIGATE_BUTTON = By.id("navigate");
    protected static final String INSTRUMENTED_UI_URI = "instrumentedUI";
    private static final String SECOND_UI_URI = "secondUI";
    protected static final String INSTRUMENTED_VIEW_URI = INSTRUMENTED_UI_URI
                + "/#!instrumentedView";
    protected static final String DANGLING_VIEW_URI = SECOND_UI_URI
                + "/#!danglingView";

    @Before
    public void resetCounter() {
        PlainUI.resetCounter();
        PlainAlternativeUI.resetCounter();
        PlainColidingAlternativeUI.resetCounter();
        InstrumentedUI.resetCounter();
        InstrumentedView.resetCounter();
        ScopedInstrumentedView.resetCounter();
        ViewWithoutAnnotation.resetCounter();
        WithAnnotationRegisteredView.resetCounter();
        SecondUI.resetCounter();
        RootUI.resetCounter();
        RootWithCustomMappingUI.resetCounter();
        UIWithCDIDependentListener.resetCounter();
        UIWithCDISelfListener.resetCounter();
        DependentCDIEventListener.resetCounter();
        DependentCDIEventListener.resetEventCounter();
        ParameterizedNavigationUI.reset();
        NoViewProviderNavigationUI.resetCounter();
    }

    public void cleanup() {
        firstWindow.close();
    }

    public void openWindowNoWait(WebDriver window, String uri, URL contextPath)
            throws MalformedURLException {
        URL url = new URL(contextPath.toString() + uri);
        window.navigate().to(url);
    }
    
    public void assertDefaultRootNotInstantiated() {
        assertThat(RootUI.getNumberOfInstances(), is(0));
    }

    public int number(String txt) {
        return Integer.parseInt(txt);
    }
}