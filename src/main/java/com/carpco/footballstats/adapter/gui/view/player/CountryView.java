package com.carpco.footballstats.adapter.gui.view.player;

import com.carpco.footballstats.adapter.gui.constants.CountryConstants;
import com.carpco.footballstats.domain.model.Country;
import com.carpco.footballstats.domain.service.CountryService;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.vaadin.flow.component.Key.ENTER;

@Component
@Scope("prototype")
@Route(value = CountryConstants.ROUTE_FOR_CREATING)
@PageTitle(CountryConstants.PAGE_TITLE)
@Slf4j
public class CountryView extends VerticalLayout implements AfterNavigationObserver {
  
  private final transient CountryService service;
  
  public CountryView(CountryService service) {
    this.service = service;
    setMargin(true);
    setSpacing(true);
  }
  
  @Override
  public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
    var title = new H4(CountryConstants.FORM_TITLE_FOR_CREATING);
    
    var countryTxf = new TextField("Country:");
    countryTxf.setRequiredIndicatorVisible(true);
    countryTxf.setRequired(true);
    countryTxf.setErrorMessage("The country name is required!");
    
    var saveBtn = new Button("Save", evt -> showConfirmationDialog(countryTxf));
    saveBtn.addClickShortcut(ENTER);
    
    var formLayout = new FormLayout();
    formLayout.setSizeUndefined();
    formLayout.add(countryTxf, saveBtn);
    
    setHorizontalComponentAlignment(Alignment.CENTER, title, formLayout);
    add(title, formLayout);
  }
  
  private void save(TextField countryTxf) {
    log.info("The admin user wants to save a Country");
    try {
      service.create(Country.builder().name(countryTxf.getValue()).build());
      showCloseDialog("Successfully saved", "The country was added!");
      countryTxf.setValue("");
      countryTxf.setInvalid(false);
    } catch (IllegalStateException ex) {
      showCloseDialog("Error!", ex.getMessage());
    }
  }
  
  private void showConfirmationDialog(TextField countryTxf) {
    if (countryTxf.getValue().isBlank()) {
      countryTxf.setInvalid(true);
      countryTxf.setValue("");
    } else {
      var dialog = buildDialog();
      
      var dialogButtonsLayout = new HorizontalLayout();
      dialogButtonsLayout.add(new NativeButton("Yes", evt -> closeAndSave(countryTxf, dialog)), new NativeButton("No", evt -> dialog.close()));
      
      var verticalLayout = new VerticalLayout();
      verticalLayout.add(new Label("Save country!"), new Label("Do you want to save the country!"), dialogButtonsLayout);
      
      dialog.add(verticalLayout);
      dialog.open();
    }
  }
  
  private void closeAndSave(TextField countryTxf, Dialog dialog) {
    dialog.close();
    save(countryTxf);
  }
  
  private void showCloseDialog(String title, String message) {
    var dialog = buildDialog();
    
    var verticalLayout = new VerticalLayout();
    verticalLayout.add(new Label(title), new Label(message), new NativeButton("Close", event -> dialog.close()));
    
    dialog.add(verticalLayout);
    dialog.open();
  }
  
  private Dialog buildDialog() {
    var dialog = new Dialog();
    dialog.setCloseOnEsc(false);
    dialog.setCloseOnOutsideClick(false);
    return dialog;
  }
}
