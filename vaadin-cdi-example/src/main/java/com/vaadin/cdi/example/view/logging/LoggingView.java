/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaadin.cdi.example.view.logging;

/**
 *
 * @author felix
 */
public interface LoggingView {
    public static final String VIEW_ID = "logging";
    
    void setSomething(String message);
}
