/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaadin.cdi;

import com.vaadin.ui.UI;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.enterprise.inject.Stereotype;

/**
 *
 * @author felix
 */
@Stereotype
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.TYPE })
@UIScoped
public @interface MobileCDIView {
    /**
     * 
     * The name of the CDIView can be derived from the simple class name So it
     * is optional. Also multiple views without a value may exist at the same
     * time
     */
    public String value() default "";

    /**
     * Specifies whether view parameters can be passed to the view as part of
     * the name, i.e in the form of {@code viewName/viewParameters}. Make sure
     * there are no other views that start with the same name, since the
     * ViewProvider will only check that the given {@code viewAndParameters}
     * starts with the view name.
     */
    public boolean supportsParameters() default false;

    /**
     * Specifies which UIs can show the view. {@link CDIViewProvider} only lists
     * the views that have the current UI on this list.
     * 
     * If this list contains UI.class, the view is available for all UIs.
     * 
     * This only needs to be specified if the application has multiple UIs that
     * use {@link CDIViewProvider}.
     * 
     * @return list of UIs in which the view can be shown.
     */
    public Class<? extends UI>[] uis() default { UI.class };    
}
