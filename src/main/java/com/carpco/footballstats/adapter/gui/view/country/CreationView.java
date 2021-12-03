package com.carpco.footballstats.adapter.gui.view.country;

import com.carpco.footballstats.adapter.gui.view.common.CommonDialog;
import com.carpco.footballstats.domain.model.Country;
import com.carpco.footballstats.domain.service.Creator;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.vaadin.flow.component.Key.ENTER;

@Component("country_creation")
@Scope("prototype")
@Route(value = Constants.ROUTE_FOR_CREATING)
@PageTitle(Constants.PAGE_TITLE)
@Slf4j
public class CreationView extends VerticalLayout {
  
  private final transient Creator<Country> service;
  private final transient CommonDialog dialog;
  
  private TextField countryTxf;
  
  public CreationView(Creator<Country> service, CommonDialog dialog) {
    this.service = service;
    this.dialog = dialog;
    initUI();
  }
  
  public void initUI() {
    setMargin(true);
    setSpacing(true);
    var title = new H4(Constants.FORM_TITLE_FOR_CREATING);
    
    countryTxf = new TextField("Country:");
    countryTxf.setRequiredIndicatorVisible(true);
    countryTxf.setRequired(true);
    countryTxf.setErrorMessage("The country name is required!");
    
    var saveBtn = new Button("Save", evt -> beforeSave());
    saveBtn.addClickShortcut(ENTER);
    
    var formLayout = new FormLayout();
    formLayout.setSizeUndefined();
    formLayout.add(countryTxf);
    
    setHorizontalComponentAlignment(Alignment.CENTER, title, formLayout, saveBtn);
    add(title, formLayout, saveBtn);
  }
  
  private void beforeSave() {
    if (countryTxf.getValue().isBlank()) {
      resetCountryTextField(true);
    } else {
      dialog.openConfirmationDialog(this::save);
    }
  }
  
  private void save() {
    log.info("The admin user wants to save a Country");
    try {
      tryToSave();
    } catch (IllegalStateException ex) {
      dialog.openClosableDialog("Error!", ex.getMessage());
    }
  }
  
  private void tryToSave() {
    service.create(Country.builder().name(countryTxf.getValue()).build());
    afterSave();
  }
  
  private void afterSave() {
    dialog.openClosableDialog("Successfully saved", "The country was added!");
    resetCountryTextField(false);
  }
  
  private void resetCountryTextField(boolean isInvalid) {
    countryTxf.setValue("");
    countryTxf.setInvalid(isInvalid);
  }
}
