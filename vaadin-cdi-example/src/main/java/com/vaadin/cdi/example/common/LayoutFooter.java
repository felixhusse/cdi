/*
 * The contents of this file are copyright (c) 2011 by medavis GmbH, Karlsruhe, Germany
 */
package com.vaadin.cdi.example.common;

import com.vaadin.cdi.UIScoped;
import javax.annotation.PostConstruct;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.BaseTheme;

/**
 * Footer for the {@code MedavisLayout}
 * @author georg.roever
 *
 */
@UIScoped
public class LayoutFooter extends HorizontalLayout {
    private static final String DEFAULT_PAGE_TITLE = "Page Title";
    private static final String DEFAULT_YEAR = "2012";
    private static final String COMPANY = "medavis ";
    private static final String VERSION = "Version ";
    private static final long serialVersionUID = -2059961467132687861L;
    private Label titleLabel;
    private Label versionYearLabel;

    @SuppressWarnings("unused")
    @PostConstruct
    private void postInit() {
        this.setWidth(100, Unit.PERCENTAGE);
        this.setHeight(1, Unit.EM);
        this.setStyleName("header");
        this.setMargin(false);
        this.setSpacing(false);

        createLeftLayout();
        createRightLayout();
    }

    private void createLeftLayout() {
        HorizontalLayout leftLayout = new HorizontalLayout();
//        leftLayout.setWidth(100, UNITS_PERCENTAGE);

        titleLabel = new Label(COMPANY + DEFAULT_PAGE_TITLE);
        titleLabel.setSizeUndefined();
        
        Label spacer = new Label();
        spacer.setWidth(13, Unit.PIXELS);

        leftLayout.addComponent(spacer);
        leftLayout.addComponent(titleLabel);
        addComponent(leftLayout);
        setComponentAlignment(leftLayout, Alignment.TOP_LEFT);
    }

   

    private void createRightLayout() {
        HorizontalLayout rightLayout = new HorizontalLayout();
        rightLayout.setSizeUndefined();

        versionYearLabel = new Label(DEFAULT_YEAR);
        versionYearLabel.setSizeUndefined();
        
        Label spacer = new Label();
        spacer.setWidth(13, Unit.PIXELS);

        rightLayout.addComponent(versionYearLabel);
        rightLayout.addComponent(spacer);
        addComponent(rightLayout);
        setComponentAlignment(rightLayout, Alignment.TOP_RIGHT);
    }

    public void setApplicationTitle(final String title, String version, String year) {
        StringBuffer footerTitle = new StringBuffer();
        if(title!=null&& !title.startsWith(COMPANY)) {
            footerTitle.append(COMPANY + " ");
        }
        footerTitle.append(title);
        titleLabel.setValue(footerTitle.toString());
        if(version!=null && year!=null) {
            versionYearLabel.setValue(VERSION + version + ", " + year);
        }else if (version!=null) {
            versionYearLabel.setValue(VERSION + version + ", " + DEFAULT_YEAR);
        }else if (year!=null) {
            versionYearLabel.setValue(year);
        }
    }

}
