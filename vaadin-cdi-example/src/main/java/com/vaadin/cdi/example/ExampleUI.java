package com.vaadin.cdi.example;

import javax.inject.Inject;

import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.cdi.example.common.LayoutFooter;
import com.vaadin.cdi.example.common.LayoutHeader;
import com.vaadin.cdi.example.util.CounterService;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@CDIUI
public class ExampleUI extends UI {

    @Inject
    private CDIViewProvider viewProvider;

    @Inject
    private CounterService counter;
    
    @Inject
    private LayoutHeader layoutHeader;
    
    @Inject
    private LayoutFooter layoutFooter;
    
    @Override
    public void init(VaadinRequest request) {
        setSizeFull();
        
        VerticalLayout navigatorLayout = new VerticalLayout();
        navigatorLayout.setSizeFull();
        navigatorLayout.setSpacing(true);
        
        Navigator navigator = new Navigator(ExampleUI.this, navigatorLayout);
        navigator.addProvider(viewProvider);
        
        VerticalLayout baseLayout = new VerticalLayout();
        baseLayout.addComponent(layoutHeader);
        baseLayout.addComponent(navigatorLayout);
        baseLayout.addComponent(layoutFooter);
        
        baseLayout.setExpandRatio(navigatorLayout, 1.0f);
        
        setContent(baseLayout);
    }
    
    public void setBreadCrumb() {
        
    }

}
