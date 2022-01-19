package org.dominokit.samples.library.client.views;

import org.dominokit.domino.api.client.mvp.view.ContentView;
import org.dominokit.domino.api.client.mvp.view.HasUiHandlers;
import org.dominokit.domino.api.client.mvp.view.UiHandlers;
import org.dominokit.samples.library.shared.model.Book;

public interface BookDetailsView extends ContentView, HasUiHandlers<BookDetailsView.BookDetailsUiHandlers> {

  void onError(String message);
  void setEditable(boolean editable);
  void edit(Book book);
  Book save();

    interface BookDetailsUiHandlers extends UiHandlers {
      void onBackToList();

      void onCancel();

      void onSave();

      void onEdit();
    }
}