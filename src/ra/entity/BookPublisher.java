package ra.entity;

public class BookPublisher {
    private String publisher;
    private int countBook;

    public BookPublisher() {
    }

    public BookPublisher(String publisher, int countBook) {
        this.publisher = publisher;
        this.countBook = countBook;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getCountBook() {
        return countBook;
    }

    public void setCountBook(int countBook) {
        this.countBook = countBook;
    }

    public void displayData() {
        System.out.printf("Nhà Xuất Bản: %s - Số lượng sách: %d\n", this.publisher, this.countBook);
    }
}
