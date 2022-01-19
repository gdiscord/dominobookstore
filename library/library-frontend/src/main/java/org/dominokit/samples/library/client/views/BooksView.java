package org.dominokit.samples.library.client.views;

import org.dominokit.domino.api.client.mvp.view.ContentView;
import org.dominokit.domino.api.client.mvp.view.HasUiHandlers;
import org.dominokit.domino.api.client.mvp.view.UiHandlers;
import org.dominokit.samples.library.shared.model.Book;

import java.util.List;

public interface BooksView extends ContentView, HasUiHandlers<BooksView.BooksUiHandlers> {


    void setBooks(List<Book> books);

    void showError(String message);


    interface BooksUiHandlers extends UiHandlers {
        void deleteBook(Book book);

        void onBookSelected(Book record);

        void onCreate();
    }
}