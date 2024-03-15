package lt.jusatas.readtimeestimation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.Iterator;

public class ResultViewController {

    @FXML
    private TableView<Book> bookTableView;

    private int readSpeed;
    private ObservableList<Book> bookList;
    private BookTableViewManager bookTableViewManager;

    public void setData(int readSpeed, ObservableList<Book> bookList) {
        this.readSpeed = readSpeed;
        this.bookList = bookList;
    }

    @FXML
    public void initialize() {
        if (bookList != null) {
            bookTableViewManager = new BookTableViewManager(bookTableView);
            bookList.forEach(book -> book.calculateReadingTimeInHours(readSpeed));
            bookTableView.setItems(bookList);
            bookTableViewManager.addReadingTimeColumn();
        }
    }
}