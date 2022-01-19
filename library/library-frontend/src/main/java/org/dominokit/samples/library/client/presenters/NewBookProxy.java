package org.dominokit.samples.library.client.presenters;

import org.dominokit.domino.api.client.annotations.presenter.*;
import org.dominokit.domino.api.client.mvp.presenter.ViewBaseClientPresenter;
import org.dominokit.domino.api.shared.extension.PredefinedSlots;
import org.dominokit.samples.library.client.views.NewBookView;
import org.dominokit.samples.library.shared.model.Book;
import org.dominokit.samples.library.shared.services.BooksServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PresenterProxy(name = "NewBook", parent = "books")
@AutoRoute(token = "new-book")
@Slot(PredefinedSlots.MODAL_SLOT)
@AutoReveal
public class NewBookProxy extends ViewBaseClientPresenter<NewBookView> implements NewBookView.NewBookUiHandlers {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewBookProxy.class);



    @OnReveal
    public void initBook() {
        view.edit(new Book());
    }

    @Override
    public void onCancel() {
        history().fireState("books");
        view.close();
    }

    @Override
    public void onSaveBook() {
        if(view.isValid()){
            BooksServiceFactory.INSTANCE
                    .create(view.save())
                    .onSuccess(book -> {
                        history().fireState("books/" + book.getTitle());
                        view.close();
                    })
                    .onFailed(failedResponseBean -> view.onError(failedResponseBean.getBody()))
                    .send();
        }

    }
}
