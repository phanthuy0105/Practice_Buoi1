package ra.entity;

public class BookTypeBooks {
    private int bookTypeId;
    private String bookTypeName;
    private int countBooks;

    public BookTypeBooks() {
    }

    public BookTypeBooks(int bookTypeId, String bookTypeName, int countBooks) {
        this.bookTypeId = bookTypeId;
        this.bookTypeName = bookTypeName;
        this.countBooks = countBooks;
    }

    public int getBookTypeId() {
        return bookTypeId;
    }

    public void setBookTypeId(int bookTypeId) {
        this.bookTypeId = bookTypeId;
    }

    public String getBookTypeName() {
        return bookTypeName;
    }

    public void setBookTypeName(String bookTypeName) {
        this.bookTypeName = bookTypeName;
    }

    public int getCountBooks() {
        return countBooks;
    }

    public void setCountBooks(int countBooks) {
        this.countBooks = countBooks;
    }

    public void displayBookTypeBooks() {
        System.out.printf("%s: - %d sách\n", this.bookTypeName, this.countBooks);
    }
}
