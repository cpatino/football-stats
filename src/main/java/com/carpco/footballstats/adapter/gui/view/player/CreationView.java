package com.carpco.footballstats.adapter.gui.view.player;

import com.carpco.footballstats.domain.service.PlayerService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("player_creation")
@Scope("prototype")
@Route(value = PlayerConstants.ROUTE_FOR_CREATING)
@PageTitle(PlayerConstants.PAGE_TITLE)
public class CreationView extends VerticalLayout implements AfterNavigationObserver {
  
  private final transient PlayerService playerService;
  
  public CreationView(PlayerService playerService) {
    this.playerService = playerService;
    setMargin(true);
    setSpacing(true);
  }
  
  @Override
  public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
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
