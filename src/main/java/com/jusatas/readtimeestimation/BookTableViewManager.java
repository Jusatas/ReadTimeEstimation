package com.jusatas.readtimeestimation;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.Locale;
import java.util.ResourceBundle;

public class BookTableViewManager {
    private TableView<Book> tableView;
    private ResourceBundle bundle;

    public BookTableViewManager(TableView<Book> tableView, Locale locale) {
        this.tableView = tableView;
        this.bundle = ResourceBundle.getBundle("bundle", locale);
        initializeColumns();
    }

    private void initializeColumns() {
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Book, String> nameColumn = new TableColumn<>(bundle.getString("name"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Book, Integer> wordCountColumn = new TableColumn<>(bundle.getString("wordCount"));
        wordCountColumn.setCellValueFactory(new PropertyValueFactory<>("wordCount"));

        TableColumn<Book, Integer> pageCountColumn = new TableColumn<>(bundle.getString("pageCount"));
        pageCountColumn.setCellValueFactory(new PropertyValueFactory<>("pageCount"));

        tableView.getColumns().addAll(nameColumn, wordCountColumn, pageCountColumn);
    }

    public void addReadingTimeColumn() {
        TableColumn<Book, Integer> readingTimeColumn = new TableColumn<>(bundle.getString("readingTime"));
        readingTimeColumn.setCellValueFactory(new PropertyValueFactory<>("readingTime"));

        tableView.getColumns().add(readingTimeColumn);
    }

    public void setItems(ObservableList<Book> items) {
        tableView.setItems(items);
    }

    public void updateLocale(Locale locale) {
        this.bundle = ResourceBundle.getBundle("bundle", locale);
        tableView.getColumns().clear();
        initializeColumns();
    }
}