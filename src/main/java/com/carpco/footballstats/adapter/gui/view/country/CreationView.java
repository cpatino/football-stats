package com.carpco.footballstats.adapter.gui.view.country;

import com.carpco.footballstats.domain.model.Country;
import com.carpco.footballstats.domain.service.CreationService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.vaadin.flow.component.Key.ENTER;

@Component("country_creation")
@Scope("prototype")
@Route(value = Constants.ROUTE_FOR_CREATING)
@PageTitle(Constants.PAGE_TITLE)
@Slf4j
@RequiredArgsConstructor
public class CreationView extends VerticalLayout implements AfterNavigationObserver {
  
  private final transient CreationService<Country> service;
  
  @Override
  public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
    setMargin(true);
    setSpacing(true);
    var title = new H4(Constants.FORM_TITLE_FOR_CREATING);
    
    var countryTxf = new TextField("Country:");
    countryTxf.setRequiredIndicatorVisible(true);
    countryTxf.setRequired(true);
    countryTxf.setErrorMessage("The country name is required!");
    
    var saveBtn = new Button("Save", evt -> beforeSave(countryTxf));
    saveBtn.addClickShortcut(ENTER);
    
    var formLayout = new FormLayout();
    formLayout.setSizeUndefined();
    formLayout.add(countryTxf, saveBtn);
    
    setHorizontalComponentAlignment(Alignment.CENTER, title, formLayout);
    add(title, formLayout);
  }
  
  private void beforeSave(TextField countryTxf) {
    if (countryTxf.getValue().isBlank()) {
      resetCountryTextField(countryTxf, true);
    } else {
      openConfirmationDialog(countryTxf);
    }
  }
  
  private void openConfirmationDialog(TextField countryTxf) {
    var dialog = buildDialog();
    
    var dialogButtonsLayout = new HorizontalLayout();
    var saveBtn = new NativeButton("Yes", evt -> save(countryTxf, dialog));
    var closeBtn = new NativeButton("No", evt -> dialog.close());
    dialogButtonsLayout.add(saveBtn, closeBtn);
    
    var verticalLayout = new VerticalLayout();
    verticalLayout.add(new Label("Save country!"), new Label("Do you want to save the country!"), dialogButtonsLayout);
    
    dialog.add(verticalLayout);
    dialog.open();
  }
  
  private Dialog buildDialog() {
    var dialog = new Dialog();
    dialog.setCloseOnEsc(false);
    dialog.setCloseOnOutsideClick(false);
    return dialog;
  }
  
  private void save(TextField countryTxf, Dialog dialog) {
    log.info("The admin user wants to save a Country");
    dialog.close();
    tryToSave(countryTxf);
  }
  
  private void tryToSave(TextField countryTxf) {
    try {
      service.create(Country.builder().name(countryTxf.getValue()).build());
      afterSave(countryTxf);
    } catch (IllegalStateException ex) {
      showCloseDialog("Error!", ex.getMessage());
    }
  }
  
  private void afterSave(TextField countryTxf) {
    showCloseDialog("Successfully saved", "The country was added!");
    resetCountryTextField(countryTxf, false);
  }
  
  private void resetCountryTextField(TextField countryTxf, boolean isInvalid) {
    countryTxf.setValue("");
    countryTxf.setInvalid(isInvalid);
  }
  
  private void showCloseDialog(String title, String message) {
    var dialog = buildDialog();
    
    var verticalLayout = new VerticalLayout();
    verticalLayout.add(new Label(title), new Label(message), new NativeButton("Close", event -> dialog.close()));
    
    dialog.add(verticalLayout);
    dialog.open();
  }
}
