/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaadin.cdi.example.common;

import com.vaadin.cdi.example.view.AbstractView;
import com.vaadin.cdi.example.view.AdminView;
import com.vaadin.cdi.example.view.ComplexView;
import com.vaadin.cdi.example.view.EventLogView;
import com.vaadin.cdi.example.view.logging.LoggingView;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import javax.annotation.PostConstruct;

/**
 *
 * @author felix
 */
public class LayoutHeader extends VerticalLayout{
    
    @PostConstruct
    private void postInit() {
        setWidth(100, Unit.PERCENTAGE);
        setHeight(100, Unit.PIXELS);
        Label headerLabel = new Label("<h1>This is a header<h1>", ContentMode.HTML);
        headerLabel.setSizeUndefined();
        addComponent(headerLabel);
        addComponent(buildNavigationBar());
        setComponentAlignment(headerLabel, Alignment.MIDDLE_CENTER);
    }
    
    private HorizontalLayout buildNavigationBar() {
        HorizontalLayout navBar = new HorizontalLayout();
        navBar.addComponent(new LayoutHeader.NavigationButton("Root view", null));
        navBar.addComponent(new LayoutHeader.NavigationButton("Complex view",ComplexView.VIEW_ID));
        navBar.addComponent(new LayoutHeader.NavigationButton("Event log view",EventLogView.VIEW_ID));
        navBar.addComponent(new LayoutHeader.NavigationButton("Admin view", AdminView.VIEW_ID));
        navBar.addComponent(new LayoutHeader.NavigationButton("Logging view", LoggingView.VIEW_ID));
        return navBar;
    } 
    
    protected static class NavigationButton extends Button implements
            Button.ClickListener {
        private String fragment;

        public NavigationButton(String caption, String fragment) {
            super(caption);
            this.fragment = fragment;
            addClickListener(this);
        }

        @Override
        public void buttonClick(Button.ClickEvent event) {
            // go to #!fragment, or remove fragment if null
            if (fragment != null) {
                Page.getCurrent().setUriFragment("!" + fragment);
            } else {
                // using dummy fragment because of #11312
                Page.getCurrent().setUriFragment("!");
            }
        }
    }
    
}
