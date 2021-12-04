package com.carpco.footballstats.adapter.gui.view.country;

import com.carpco.footballstats.adapter.gui.view.common.CommonDialog;
import com.carpco.footballstats.adapter.gui.view.common.CreationVerticalLayout;
import com.carpco.footballstats.domain.model.Country;
import com.carpco.footballstats.domain.service.Creator;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H4;
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
public class CreationView extends CreationVerticalLayout {
  
  private final transient Creator<Country> countryCreator;
  
  private TextField countryTxf;
  
  public CreationView(Creator<Country> countryCreator, CommonDialog dialog) {
    super(dialog);
    this.countryCreator = countryCreator;
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
    
    var saveBtn = new Button("Save", evt -> executeSave());
    saveBtn.addClickShortcut(ENTER);
    
    var formLayout = new FormLayout();
    formLayout.setSizeUndefined();
    formLayout.add(countryTxf);
    
    setHorizontalComponentAlignment(Alignment.CENTER, title, formLayout, saveBtn);
    add(title, formLayout, saveBtn);
  }
  
  @Override
  protected boolean canBeSaved() {
    return isComponentEmpty(countryTxf);
  }
  
  @Override
  protected void tryToSave() {
    log.info("Saving a new country!");
    var country = Country.builder()
      .name(countryTxf.getValue())
      .build();
    countryCreator.create(country);
  }
  
  @Override
  protected void cleanFields() {
    countryTxf.setValue("");
    countryTxf.setInvalid(false);
  }
}
