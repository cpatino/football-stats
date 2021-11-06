package com.carpco.footballstats.adapter.gui.view.player;

import com.carpco.footballstats.adapter.gui.constants.PlayerConstants;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Route(value = PlayerConstants.ROUTE)
@PageTitle(PlayerConstants.PAGE_TITLE)
public class PlayerView extends VerticalLayout implements AfterNavigationObserver {
  
  public PlayerView() {
    setMargin(true);
    setSpacing(true);
  }
  
  @Override
  public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
    addComponents();
  }
  
  private void addComponents() {
    add(buildNameTextField(), buildBornDateTextField(), buildAgeTextField(), buildNationalityTextField(), buildShirtNumberTextField());
  }
  
  private TextField buildNameTextField() {
    return new TextField();
  }
  
  private TextField buildShirtNumberTextField() {
    return new TextField("nro.");
  }
  
  private TextField buildAgeTextField() {
    return new TextField("age");
  }
  
  private TextField buildBornDateTextField() {
    return new TextField();
  }
  
  private TextField buildNationalityTextField() {
    return new TextField();
  }
}
