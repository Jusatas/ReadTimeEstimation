package lt.jusatas.readtimeestimation;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BookTableViewManager {
    private TableView<Book> tableView;

    public BookTableViewManager(TableView<Book> tableView) {
        this.tableView = tableView;
        initializeColumns();
    }

    private void initializeColumns() {
        TableColumn<Book, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Book, Integer> wordCountColumn = new TableColumn<>("Word Count");
        wordCountColumn.setCellValueFactory(new PropertyValueFactory<>("wordCount"));

        TableColumn<Book, Integer> pageCountColumn = new TableColumn<>("Page Count");
        pageCountColumn.setCellValueFactory(new PropertyValueFactory<>("pageCount"));

        tableView.getColumns().addAll(nameColumn, wordCountColumn, pageCountColumn);
    }

    public void addReadingTimeColumn() {
        TableColumn<Book, Integer> readingTimeColumn = new TableColumn<>("Reading hours");
        readingTimeColumn.setCellValueFactory(new PropertyValueFactory<>("readingTime"));

        tableView.getColumns().add(readingTimeColumn);
    }

    public void setItems(ObservableList<Book> items) {
        System.out.println("Set items is being called");
        tableView.setItems(items);
    }
}