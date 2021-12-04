package com.carpco.footballstats.adapter.gui.view.player;

import com.carpco.footballstats.adapter.gui.view.common.CommonDialog;
import com.carpco.footballstats.adapter.gui.view.common.CreationVerticalLayout;
import com.carpco.footballstats.adapter.gui.view.common.DefaultVerticalLayout;
import com.carpco.footballstats.domain.model.Country;
import com.carpco.footballstats.domain.model.Name;
import com.carpco.footballstats.domain.model.Player;
import com.carpco.footballstats.domain.service.Creator;
import com.carpco.footballstats.domain.service.Finder;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.vaadin.flow.component.Key.ENTER;

@Component("player_creation")
@Scope("prototype")
@Route(value = Constants.ROUTE_FOR_CREATING)
@PageTitle(Constants.PAGE_TITLE)
@Slf4j
public class CreationView extends CreationVerticalLayout implements AfterNavigationObserver {
  
  private final transient Creator<Player> playerCreator;
  private final transient Finder<Country> countryFinder;
  
  private TextField firstNameTxf;
  private TextField lastNameTxf;
  private DatePicker bornDtp;
  private ComboBox<Country> nationalityCbx;
  
  public CreationView(Creator<Player> playerCreator, Finder<Country> countryFinder, CommonDialog dialog) {
    super(dialog);
    this.playerCreator = playerCreator;
    this.countryFinder = countryFinder;
    initUI();
  }
  
  public void initUI() {
    setMargin(true);
    setSpacing(true);
    var title = new H4(Constants.FORM_TITLE_FOR_CREATING);
    
    firstNameTxf = new TextField("First name:");
    firstNameTxf.setRequiredIndicatorVisible(true);
    firstNameTxf.setRequired(true);
    firstNameTxf.setErrorMessage("The first name is required!");
    
    lastNameTxf = new TextField("Family name:");
    lastNameTxf.setRequiredIndicatorVisible(true);
    lastNameTxf.setRequired(true);
    lastNameTxf.setErrorMessage("The family name is required!");
    
    bornDtp = new DatePicker("Born date");
    bornDtp.setRequiredIndicatorVisible(true);
    bornDtp.setRequired(true);
    bornDtp.setErrorMessage("The born date is required!");
    
    nationalityCbx = new ComboBox<>("Nationality");
    nationalityCbx.setRequiredIndicatorVisible(true);
    nationalityCbx.setRequired(true);
    nationalityCbx.setErrorMessage("The nationality is required!");
    
    var saveBtn = new Button("Save", evt -> executeSave());
    saveBtn.addClickShortcut(ENTER);
    
    var formLayout = new FormLayout();
    formLayout.setSizeUndefined();
    formLayout.add(firstNameTxf, lastNameTxf, bornDtp, nationalityCbx);
    
    setHorizontalComponentAlignment(Alignment.CENTER, title, formLayout, saveBtn);
    add(title, formLayout, saveBtn);
  }
  
  @Override
  public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
    nationalityCbx.setItems(countryFinder.findAllEnabled());
    nationalityCbx.setItemLabelGenerator(Country::getName);
  }
  
  @Override
  protected boolean canBeSaved() {
    return isComponentEmpty(firstNameTxf) && isComponentEmpty(lastNameTxf) && isComponentEmpty(bornDtp)
      && isComponentEmpty(nationalityCbx);
  }
  
  @Override
  protected void tryToSave() {
    log.info("Saving a new player!");
    var player = Player.builder()
      .name(new Name(firstNameTxf.getValue(), lastNameTxf.getValue()))
      .bornDate(bornDtp.getValue())
      .country(nationalityCbx.getValue())
      .build();
    playerCreator.create(player);
  }
  
  @Override
  protected void cleanFields() {
    firstNameTxf.setValue("");
    lastNameTxf.setValue("");
    bornDtp.setValue(null);
    nationalityCbx.setValue(null);
    
    firstNameTxf.setInvalid(false);
    lastNameTxf.setInvalid(false);
    bornDtp.setInvalid(false);
    nationalityCbx.setInvalid(false);
  }
}
