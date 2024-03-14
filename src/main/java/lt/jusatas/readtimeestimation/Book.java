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
    public Book(String name, int pageCount) {
        this.name = name;
        this.pageCount = pageCount;
        this.wordCount = calculateWordCount(pageCount);
    }
    public Book(String name, int wordCount, boolean isWordCount) {
        this.name = name;
        this.pageCount = calculatePageCount(wordCount);
        this.wordCount = wordCount;
    }

    private int calculateWordCount(int pageCount) {
        return pageCount * 450;
    }

    private int calculatePageCount(int wordCount) {
        return (wordCount / 450) + 1;
    }

    @Override
    public String toString() {
        return String.format("%-40sWord Count: %-10d Page Count: %-10d",
                name, wordCount, pageCount);
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

    public int getPageCount() {
        return pageCount;
    }
}
