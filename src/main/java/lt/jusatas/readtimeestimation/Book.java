package lt.jusatas.readtimeestimation;

public class Book {
    private String name;
    private int wordCount;
    private int pageCount;

    public Book(String name, int wordCount, int pageCount) {
        this.name = name;
        this.wordCount = wordCount;
        this.pageCount = pageCount;
    }

    private int calculateWordcount(int pageCount) {
        return pageCount * 450;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
