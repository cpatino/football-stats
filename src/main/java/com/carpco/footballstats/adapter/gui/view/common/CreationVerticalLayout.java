package com.carpco.footballstats.adapter.gui.view.common;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class CreationVerticalLayout extends DefaultVerticalLayout {
  
  protected CreationVerticalLayout(CommonDialog dialog) {
    super(dialog);
  }
  
  protected abstract boolean canBeSaved();
  protected abstract void tryToSave();
  protected abstract void cleanFields();
  
  protected void executeSave() {
    if (canBeSaved()) {
      dialog.openConfirmationDialog(this::save);
    }
  }
  
  private void save() {
    log.info("A new data will be saved!");
    try {
      tryToSave();
      afterSave();
    } catch (Exception ex) {
      dialog.openClosableDialog("Error!", ex.getMessage());
    }
  }
  
  private void afterSave() {
    dialog.openClosableDialog("Successfully saved", "The data was created successfully!");
    cleanFields();
  }
}
