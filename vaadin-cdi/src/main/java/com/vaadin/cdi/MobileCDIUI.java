/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaadin.cdi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.enterprise.inject.Stereotype;

/**
 * Use this annotation for Mobile UIs
 * @author felix
 */
@Stereotype
@UIScoped
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Inherited
public @interface MobileCDIUI {
    /**
     * An optional URI mapping. If not specified, the mapping is going to be
     * derived from the simple name of the class. A class WelcomeVaadin is going
     * to be bound to "/welcomeVaadin" uri.
     * 
     * @return the URI mapping of this Mobile UI
     */
    public String value() default "";
}
