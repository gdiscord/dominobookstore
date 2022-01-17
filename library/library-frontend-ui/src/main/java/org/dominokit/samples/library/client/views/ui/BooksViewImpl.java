package org.dominokit.samples.library.client.views.ui;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.datatable.ColumnConfig;
import org.dominokit.domino.ui.datatable.DataTable;
import org.dominokit.domino.ui.datatable.TableConfig;
import org.dominokit.domino.ui.datatable.plugins.EmptyStatePlugin;
import org.dominokit.domino.ui.datatable.store.LocalListDataStore;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.notifications.Notification;
import org.dominokit.domino.ui.utils.TextNode;
import org.dominokit.domino.view.BaseElementView;

import org.dominokit.domino.api.client.annotations.UiView;
import org.dominokit.domino.ui.utils.DominoElement;

import org.dominokit.samples.library.client.presenters.BooksProxy;
import org.dominokit.samples.library.client.views.BooksView;
import org.dominokit.samples.library.shared.model.Book;

import java.util.List;



@UiView(presentable = BooksProxy.class)
public class BooksViewImpl extends BaseElementView<HTMLDivElement> implements BooksView{

    private BooksUiHandlers uiHandlers;
    private LocalListDataStore<Book> dataStore;
    private DominoElement<HTMLDivElement> root = DominoElement.div();

    @Override
    public HTMLDivElement init() {
        TableConfig<Book> tableConfig = new TableConfig<Book>()
                .addColumn(ColumnConfig.<Book>create("title", "Title")
                        .setCellRenderer(cellInfo -> TextNode.of(cellInfo.getRecord().getTitle()))
                )
                .addColumn(ColumnConfig.<Book>create("author", "Author")
                        .setCellRenderer(cellInfo -> TextNode.of(cellInfo.getRecord().getAuthor()))
                )
                .addColumn(ColumnConfig.<Book>create("year", "Year")
                        .setCellRenderer(cellInfo -> TextNode.of(cellInfo.getRecord().getYear() + ""))
                )
                .addColumn(ColumnConfig.<Book>create("publisher", "Publisher")
                        .setCellRenderer(cellInfo -> TextNode.of(cellInfo.getRecord().getPublisher()))
                )
                .addColumn(ColumnConfig.<Book>create("price", "Price")
                        .setCellRenderer(cellInfo -> TextNode.of(cellInfo.getRecord().getCost() + ""))
                )
                .addPlugin(new EmptyStatePlugin<>(Icons.ALL.format_line_weight_mdi(), "No books found"));

        dataStore = new LocalListDataStore<>();
        DataTable<Book> dataTable = new DataTable<>(tableConfig, dataStore);

        root
                .appendChild(Row.create()
                        .appendChild(Column.span12()
                                .appendChild(Card.create("Books")
                                        .appendChild(dataTable)
                                )
                        )
                );
        return root.element();
    }


    @Override
    public void setUiHandlers(BooksUiHandlers uiHandlers) {
        this.uiHandlers = uiHandlers;
    }

    @Override
    public void setBooks(List<Book> books) {
        dataStore.setData(books);

    }

    @Override
    public void showError(String errorMessage) {
        Notification.createDanger(errorMessage).show();

    }
}