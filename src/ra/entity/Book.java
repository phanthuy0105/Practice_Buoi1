package ra.entity;

import java.util.Scanner;

public class Book implements IBookManagement{
    private String bookId;
    private String bookName;
    private String title;
    private String author;
    private int pages;
    private String content;
    private String publisher;
    private float price;
    private int typeId;
    private boolean bookStatus;

    public Book() {
    }

    public Book(String bookId, String bookName, String title, String author, int pages, String content, String publisher, float price, int typeId, boolean bookStatus) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.content = content;
        this.publisher = publisher;
        this.price = price;
        this.typeId = typeId;
        this.bookStatus = bookStatus;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public boolean isBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }

    @Override
    public void inputData(Scanner scanner) {
        System.out.println("Nhập vào mã sách: ");
        this.bookId = scanner.nextLine();
        System.out.println("Nhập vào tên sách: ");
        this.bookName = scanner.nextLine();
        System.out.println("Nhập vào tiêu đề sách: ");
        this.title = scanner.nextLine();
        System.out.println("Nhập vào tác giả sách: ");
        this.author = scanner.nextLine();
        System.out.println("Nhập vào số trang sách: ");
        this.pages = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập vào nội dung sách: ");
        this.content = scanner.nextLine();
        System.out.println("Nhập vào nhà xuất bản");
        this.publisher = scanner.nextLine();
        System.out.println("Nhập vào giá sách: ");
        this.price = Float.parseFloat(scanner.nextLine());
        System.out.println("Nhập vào mã loại sách của sách: ");
        this.typeId = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập vào trạng thái sách:");
        this.bookStatus = Boolean.parseBoolean(scanner.nextLine());
    }

    @Override
    public void displayData() {
        System.out.printf("BookID: %s - BookName: %s - Title: %s - Author: %s - Pages: %d\n", this.bookId, this.bookName, this.title, this.author, this.pages);
        System.out.printf("Content: %s - Publisher: %s - Price: %.2f - TypeId: %d - BookStatus: %s\n",this.content, this.publisher, this.price, this.typeId, this.bookStatus ? "Active" : "Inactive");
    }
}
