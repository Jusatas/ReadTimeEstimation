package lt.jusatas.readtimeestimation;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class ResultViewController {

    private int readSpeed;
    private ObservableList<Book> bookList;

    public void setData(int readSpeed, ObservableList<Book> bookList) {
        this.readSpeed = readSpeed;
        this.bookList = bookList;
    }
}
