/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaadin.cdi.example.view.logging;

import com.vaadin.cdi.CDIView;
import com.vaadin.cdi.example.logging.LoggableEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

/**
 *
 * @author felix
 */
@CDIView(value = "logging")
public class LoggingViewImpl extends CustomComponent implements View,LoggingView{
    
    @Inject
    private javax.enterprise.event.Event<LoggingView> events;
    
    private VerticalLayout layout;
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        layout = new VerticalLayout();
        layout.addComponent(new Label("Ho ho ho ho logging"));
        events.select(new AnnotationLiteral<LoggingViewPresenter.InitView>() {}).fire(this);
        setCompositionRoot(layout);
    }

    @Override
    public void setSomething(String message) {
        layout.addComponent(new Label(message));
    }
    
}
