package org.dominokit.samples.library.client.presenters;

import org.dominokit.domino.api.client.annotations.presenter.*;
import org.dominokit.domino.api.client.mvp.presenter.ViewBaseClientPresenter;
import org.dominokit.samples.library.client.views.BooksView;
import org.dominokit.samples.library.shared.model.Book;
import org.dominokit.samples.library.shared.services.BooksServiceFactory;
import org.dominokit.samples.shell.shared.Slots;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PresenterProxy(name = "books", parent = "shell")
@AutoRoute(token = "books")
@Slot(Slots.CONTENT)
@AutoReveal
public class BooksProxy extends ViewBaseClientPresenter<BooksView> implements BooksView.BooksUiHandlers {

    private static final Logger LOGGER = LoggerFactory.getLogger(BooksProxy.class);



    @OnReveal
    public void listBooks() {
        BooksServiceFactory.INSTANCE
                .list()
                .onSuccess(books -> view.setBooks(books))
                .onFailed(failedResponse -> view.showError("Sadly no response from server.!"))
                .send();
    }

    @Override
    public void deleteBook(Book book) {
        BooksServiceFactory.INSTANCE
                .delete(book.getTitle())
                .onSuccess(books -> listBooks())
                .onFailed(failedResponse -> view.showError(failedResponse.getBody()))
                .send();


    }
    @Override
    public void onBookSelected(Book book){
        history().fireState(history().currentToken().appendPath(book.getTitle()).appendParameter("editable", "false"));
    }

    @Override
    public void onCreate() {
        history().fireState("new-book");
    }
}