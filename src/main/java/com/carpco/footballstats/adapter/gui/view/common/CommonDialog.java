package com.carpco.footballstats.adapter.gui.view.common;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.stereotype.Component;

@Component
public class CommonDialog {
  
  public void openConfirmationDialog(Runnable runnable) {
    var dialog = buildDialog();
    
    var dialogButtonsLayout = new HorizontalLayout();
    var saveBtn = new NativeButton("Yes", evt -> confirmationEvent(runnable, dialog));
    var closeBtn = new NativeButton("No", evt -> dialog.close());
    dialogButtonsLayout.add(saveBtn, closeBtn);
    
    var verticalLayout = new VerticalLayout();
    verticalLayout.add(new Label("Unsaved data!"), new Label("Do you want to save the data?"), dialogButtonsLayout);
    
    dialog.add(verticalLayout);
    dialog.open();
  }
  
  public void openClosableDialog(String title, String message) {
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
  
  private void confirmationEvent(Runnable runnable, Dialog dialog) {
    dialog.close();
    runnable.run();
  }
}
