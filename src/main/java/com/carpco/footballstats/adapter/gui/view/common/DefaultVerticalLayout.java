package com.carpco.footballstats.adapter.gui.view.common;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasValidation;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public abstract class DefaultVerticalLayout extends VerticalLayout {
  
  protected final transient CommonDialog dialog;
  
  protected DefaultVerticalLayout(CommonDialog dialog) {
    this.dialog = dialog;
  }
  
  protected boolean isComponentEmpty(AbstractField<?, ?> component) {
    if (component.isEmpty() && component instanceof HasValidation hasValidation) {
      hasValidation.setInvalid(true);
    }
    return !component.isEmpty();
  }
}
