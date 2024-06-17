package ra.presentation;

import ra.business.BookBusiness;
import ra.business.BookTypeBusiness;
import ra.entity.Book;
import ra.entity.BookPublisher;
import ra.entity.BookType;
import ra.entity.BookTypeBooks;

import java.util.List;
import java.util.Scanner;

public class BookManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("*************BOOK-MANAGEMENT***********");
            System.out.println("1. Quản lý loại sách");
            System.out.println("2. Quản lý sách");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    BookManagement.displayBookTypeMenu(scanner);
                    break;
                case 2:
                    BookManagement.displayBookMenu(scanner);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1-3");
            }
        } while (true);
    }

    public static void displayBookTypeMenu(Scanner scanner) {
        boolean isExist = true;
        do {
            System.out.println("**********BOOK-TYPE-MENU**********");
            System.out.println("1. Danh sách loại sách");
            System.out.println("2. Tạo mới loại sách");
            System.out.println("3. Cập nhật loại sách");
            System.out.println("4. Xóa loại sách");
            System.out.println("5. Thống kê số lượng sách theo mã loại sách");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    BookManagement.displayListBookTypeMenu();
                    break;
                case 2:
                    BookManagement.createBookType(scanner);
                    break;
                case 3:
                    BookManagement.updateBookType(scanner);
                    break;
                case 4:
                    BookManagement.deleteBookType(scanner);
                    break;
                case 5:
                    BookManagement.countBooksByBookTypeId(scanner);
                    break;
                case 6:
                    isExist = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-6");
            }
        } while (isExist);
    }

    public static void displayListBookTypeMenu() {
        List<BookType> listBookType = BookTypeBusiness.findAll();
        listBookType.stream().forEach(BookType::displayData);
    }

    public static void createBookType(Scanner scanner) {
        BookType bookTypeNew = new BookType();
        bookTypeNew.inputData(scanner);
        boolean result = BookTypeBusiness.create(bookTypeNew);
        if (result) {
            System.out.println("Thêm mới loại sách thành công");
        } else {
            System.err.println("Có lỗi trong quá trình thêm mới loại sách");
        }
    }

    public static void updateBookType(Scanner scanner) {
        System.out.println("Nhập vào mã loại sách cần cập nhật:");
        int typeId = Integer.parseInt(scanner.nextLine());
        if (BookTypeBusiness.isExistBookType(typeId)) {
            //Đã tồn tại loại sách --> cập nhật loại sách
            //- Lấy thông tin loại sách cần cập nhật
            BookType bookTypeUpdate = BookTypeBusiness.getBookTypeById(typeId);
            boolean isExist = true;
            do {
                System.out.println("Chọn thông tin cần cập nhật:");
                System.out.println("1. Cập nhật tên loại sách");
                System.out.println("2. Cập nhật mô tả loại sách");
                System.out.println("3. Cập nhật trạng thái loại sách");
                System.out.println("4. Thoát");
                System.out.println("Lựa chọn của bạn:");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Nhập vào tên loại sách cần cập nhật:");
                        bookTypeUpdate.setTypeName(scanner.nextLine());
                        break;
                    case 2:
                        System.out.println("Nhập vào mô tả loại sách cần cập nhật:");
                        bookTypeUpdate.setDescriptions(scanner.nextLine());
                        break;
                    case 3:
                        System.out.println("Nhập vào trạng thái loại sách cần cập nhật:");
                        bookTypeUpdate.setTypeStatus(Boolean.parseBoolean(scanner.nextLine()));
                        break;
                    default:
                        isExist = false;

                }
            } while (isExist);
            //Cập nhật loại sách
            boolean result = BookTypeBusiness.updateBookType(bookTypeUpdate);
            if (result){
                System.out.println("Cập nhật loại sách thành công");
            }else{
                System.out.println("Có lỗi trong quá trình cập nhật loại sách");
            }
        } else {
            //Không tồn tại mã loại sách
            System.err.println("Mã loại sách không tồn tại");
        }
    }

    public static void deleteBookType(Scanner scanner) {
        System.out.println("Nhập vào mã loại sách cần xóa:");
        int typeId = Integer.parseInt(scanner.nextLine());
        //1. Kiểm tra loại sách có tồn tại không
        boolean isExist = BookTypeBusiness.isExistBookType(typeId);
        //2. Nếu tồn tại thực hiện xóa
        if (isExist){
            boolean result = BookTypeBusiness.delete(typeId);
            if (result){
                System.out.println("Xóa loại sách thành công");
            }else{
                System.err.println("Có lỗi trong quá trình xóa loại sách");
            }
        }else{
            System.err.println("Mã loại sách không tồn tại");
        }
    }

    public static void countBooksByBookTypeId(Scanner scanner) {
        System.out.println("Nhập vào mã loại sách cần thống kê: ");
        int typeId = Integer.parseInt(scanner.nextLine());
        System.out.println("KẾT QUẢ THỐNG KÊ:");
        BookTypeBooks bookTypeBooks = BookTypeBusiness.countBooksByTypeId(typeId);
        bookTypeBooks.displayBookTypeBooks();
    }

    public static void displayBookMenu(Scanner scanner) {
        boolean isExist = true;
        do {
            System.out.println("**********BOOK-MENU***********");
            System.out.println("1. Danh sách sách");
            System.out.println("2. Tạo mới sách");
            System.out.println("3. Cập nhật sách");
            System.out.println("4. Xóa sách");
            System.out.println("5. Sắp xếp sách theo giá sách giảm dần");
            System.out.println("6. Tìm kiếm sách theo tên sách hoặc tiêu đề sách");
            System.out.println("7. Thống kê sách theo nhà xuất bản");
            System.out.println("8. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    BookManagement.displayListBookMenu();
                    break;
                case 2:
                    BookManagement.createBook(scanner);
                    break;
                case 3:
                    BookManagement.updateBook(scanner);
                    break;
                case 4:
                    BookManagement.deleteBook(scanner);
                    break;
                case 5:
                    BookManagement.sortBookByPrice();
                    break;
                case 6:
                    BookManagement.searchBookByNameOrTitle(scanner);
                    break;
                case 7:
                    BookManagement.countBooksByPublisher();
                    break;
                case 8:
                    isExist = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-8");
            }
        } while (isExist);
    }

    public static void displayListBookMenu() {
        List<Book> listBook = BookBusiness.findAll();
        listBook.stream().forEach(Book::displayData);
    }

    public static void createBook(Scanner scanner) {
        Book bookNew = new Book();
        bookNew.inputData(scanner);
        boolean result = BookBusiness.create(bookNew);
        if (result) {
            System.out.println("Thêm mới sách thành công");
        } else {
            System.err.println("Có lỗi trong quá trình thêm mới sách");
        }
    }

    public static void updateBook(Scanner scanner) {
        System.out.println("Nhập vào mã loại sách cần cập nhật:");
        String bookId = scanner.nextLine();
        if (BookBusiness.isExistBook(bookId)) {
            //Đã tồn tại loại sách --> cập nhật loại sách
            //- Lấy thông tin loại sách cần cập nhật
            Book bookUpdate = BookBusiness.getBookById(bookId);
            boolean isExist = true;
            do {
                System.out.println("Chọn thông tin cần cập nhật:");
                System.out.println("1. Cập nhật mã sách");
                System.out.println("2. Cập nhật tên sách");
                System.out.println("3. Cập nhật tiêu đề sách");
                System.out.println("4. Cập nhật tác giả sách");
                System.out.println("5. Cập nhật số trang sách");
                System.out.println("6. Cập nhật nội dung sách");
                System.out.println("7. Cập nhật nhà xuất bản");
                System.out.println("8. Cập nhật giá sách");
                System.out.println("9. Cập nhật mã loại sách sách");
                System.out.println("10. Cập nhật trạng thái sách");
                System.out.println("11. Thoát");
                System.out.println("Lựa chọn của bạn:");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Nhập vào mã sách cần cập nhật:");
                        bookUpdate.setBookId(scanner.nextLine());
                        break;
                    case 2:
                        System.out.println("Nhập vào tên sách cần cập nhật:");
                        bookUpdate.setBookName(scanner.nextLine());
                        break;
                    case 3:
                        System.out.println("Nhập vào tiêu đề sách cần cập nhật:");
                        bookUpdate.setTitle(scanner.nextLine());
                        break;
                    case 4:
                        System.out.println("Nhập vào tác giả sách cần cập nhật:");
                        bookUpdate.setAuthor(scanner.nextLine());
                        break;
                    case 5:
                        System.out.println("Nhập vào số trang sách cần cập nhật:");
                        bookUpdate.setPages(Integer.parseInt(scanner.nextLine()));
                        break;
                    case 6:
                        System.out.println("Nhập vào nội dung sách cần cập nhật:");
                        bookUpdate.setContent(scanner.nextLine());
                        break;
                    case 7:
                        System.out.println("Nhập vào nhà xuất bản cần cập nhật:");
                        bookUpdate.setPublisher(scanner.nextLine());
                        break;
                    case 8:
                        System.out.println("Nhập vào giá sách cần cập nhật:");
                        bookUpdate.setPrice(Float.parseFloat(scanner.nextLine()));
                        break;
                    case 9:
                        System.out.println("Nhập vào mã loại sách của sách cần cập nhật:");
                        bookUpdate.setTypeId(Integer.parseInt(scanner.nextLine()));
                        break;
                    case 10:
                        System.out.println("Nhập vào trạng thái loại sách cần cập nhật:");
                        bookUpdate.setBookStatus(Boolean.parseBoolean(scanner.nextLine()));
                        break;
                    default:
                        isExist = false;

                }
            } while (isExist);
            //Cập nhật loại sách
            boolean result = BookBusiness.updateBook(bookUpdate);
            if (result){
                System.out.println("Cập nhật sách thành công");
            }else{
                System.out.println("Có lỗi trong quá trình cập nhật sách");
            }
        } else {
            //Không tồn tại mã loại sách
            System.err.println("Mã sách không tồn tại");
        }
    }

    public static void deleteBook(Scanner scanner) {
        System.out.println("Nhập vào mã sách cần xóa:");
        String bookId = scanner.nextLine();
        //1. Kiểm tra sách có tồn tại không
        boolean isExist = BookBusiness.isExistBook(bookId);
        //2. Nếu tồn tại thực hiện xóa
        if (isExist){
            boolean result = BookBusiness.delete(bookId);
            if (result){
                System.out.println("Xóa sách thành công");
            }else{
                System.err.println("Có lỗi trong quá trình xóa sách");
            }
        }else{
            System.err.println("Mã sách không tồn tại");
        }
    }

    public static void sortBookByPrice() {
        System.out.println("Sắp xếp sách theo giá giảm dần: ");
        List<Book> listBook =  BookBusiness.sortBookByPrice();
        listBook.stream().forEach(Book::displayData);
    }

    public static void searchBookByNameOrTitle(Scanner scanner) {
        System.out.println("Nhập vào tên hoặc tiêu đề sách cần tìm:");
        String nameOrTitleSearch = scanner.nextLine();

        System.out.println("KẾT QUẢ TÌM KIẾM:");
        List<Book> listBook =  BookBusiness.searchBookByNameOrTitle(nameOrTitleSearch);
        listBook.stream().forEach(Book::displayData);
    }

    public static void countBooksByPublisher() {
        System.out.println("KẾT QUẢ THỐNG KÊ:");
        List<BookPublisher> listBookPublisher = BookBusiness.countBooksByPublisher();
        listBookPublisher.stream().forEach(bookPublisher -> bookPublisher.displayData());
    }
}