package com.carpco.footballstats.adapter.gui.view.player;

import com.carpco.footballstats.domain.model.Player;
import com.carpco.footballstats.domain.service.CreationService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("player_creation")
@Scope("prototype")
@Route(value = Constants.ROUTE_FOR_CREATING)
@PageTitle(Constants.PAGE_TITLE)
@RequiredArgsConstructor
public class CreationView extends VerticalLayout implements AfterNavigationObserver {
  
  private final transient CreationService<Player> service;
  
  @Override
  public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
    setMargin(true);
    setSpacing(true);
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
