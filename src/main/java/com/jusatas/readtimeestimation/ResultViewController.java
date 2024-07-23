package com.jusatas.readtimeestimation;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResultViewController {

    @FXML
    private TableView<Book> bookTableView;
    @FXML
    private Label avgReadingLabel;

    private int readSpeed;
    private ObservableList<Book> bookList;
    private BookTableViewManager bookTableViewManager;
    private Locale locale;
    private ResourceBundle bundle;

    public void setData(int readSpeed, ObservableList<Book> bookList, Locale locale, ResourceBundle bundle) {
        this.bundle = bundle;
        this.locale = locale;
        this.readSpeed = readSpeed;
        this.bookList = bookList;
    }

    @FXML
    public void initialize() {



        if (bookList != null) {
            avgReadingLabel.setText(bundle.getString("averageReadingSpeeds"));
            bookTableViewManager = new BookTableViewManager(bookTableView, locale);
            bookList.forEach(book -> book.calculateReadingTimeInHours(readSpeed));
            bookTableView.setItems(bookList);
            bookTableViewManager.addReadingTimeColumn();
        }
    }
}