package org.dominokit.samples.library.client.views.ui;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;
import org.dominokit.domino.ui.grid.flex.FlexItem;
import org.dominokit.domino.ui.grid.flex.FlexLayout;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.modals.ModalDialog;
import org.dominokit.domino.ui.notifications.Notification;
import org.dominokit.domino.view.BaseElementView;

import org.dominokit.domino.api.client.annotations.UiView;
import org.dominokit.domino.ui.utils.DominoElement;

import org.dominokit.domino.view.BaseModalView;
import org.dominokit.samples.library.client.presenters.NewBookProxy;
import org.dominokit.samples.library.client.views.NewBookView;
import org.dominokit.samples.library.shared.model.Book;

import static org.jboss.elemento.Elements.h;

@UiView(presentable = NewBookProxy.class)
public class NewBookViewImpl extends BaseModalView<ModalDialog> implements NewBookView{

    private NewBookUiHandlers uiHandlers;
    private ModalDialog modal;
    BookComponent bookComponent;

    @Override
    public ModalDialog getModal(){
        return modal;
    }

    @Override
    public HTMLDivElement init() {
        bookComponent = new BookComponent();
        modal = ModalDialog.create("New Book")
                .appendChild(bookComponent)
                .appendFooterChild(Row.create()
                        .appendChild(Column.span12()
                                .appendChild(FlexLayout.create()
                                        .setGap("10px")
                                        .appendChild(FlexItem.create()
                                                .appendChild(Button.createPrimary(Icons.ALL.floppy_mdi())
                                                        .setContent("Save")
                                                        .setMinWidth("120px")
                                                        .addClickListener(evt -> uiHandlers.onSaveBook())
                                                )
                                        )
                                        .appendChild(FlexItem.create()
                                                .appendChild(Button.create(Icons.ALL.floppy_mdi())
                                                        .setContent("Cancel")
                                                        .linkify()
                                                        .setMinWidth("120px")
                                                        .addClickListener(evt -> uiHandlers.onCancel())
                                                ))
                                )
                        )
                );
        return modal.element();
    }

    @Override
    public void setUiHandlers(NewBookUiHandlers uiHandlers) {
        this.uiHandlers = uiHandlers;
    }


    @Override
    public void edit(Book book) {
        bookComponent.edit(book);

    }

    @Override
    public Book save() {
        return bookComponent.save();
    }


    @Override
    public boolean isValid() {
        return bookComponent.isValid();
    }

    @Override
    public void onError(String message) {
        Notification.createDanger(message).show();

    }

}