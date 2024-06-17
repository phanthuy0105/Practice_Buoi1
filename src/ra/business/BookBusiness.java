package ra.business;

import ra.entity.Book;
import ra.entity.BookPublisher;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class BookBusiness {
    public static List<Book> findAll() {
        //1. Tạo kết nối đến CSDL --> tạo đối tượng callableStatement
        Connection conn = null;
        CallableStatement callSt = null;
        List<Book> listBook = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call Find_all_book()}");
            //2. set giá trị các tham số vào và đăng ký kiểu dữ liệu cho tham số trả ra
            //3. Thực hiện procedure và nhận kết quả --> listBook
            ResultSet rs = callSt.executeQuery();
            listBook = new ArrayList<>();
            while (rs.next()) {
                //Duyệt 1 dòng dữ liệu trong resultset
                Book book = new Book();
                book.setBookId(rs.getString("book_id"));
                book.setBookName(rs.getString("book_name"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPages(rs.getInt("pages"));
                book.setContent(rs.getString("content"));
                book.setPublisher(rs.getString("publisher"));
                book.setPrice(rs.getFloat("price"));
                book.setTypeId(rs.getInt("type_id"));
                book.setBookStatus(rs.getBoolean("book_status"));
                listBook.add(book);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //Đóng kết nối khi làm việc xong
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listBook;
    }

    public static Book getBookById(String bookId) {
        Connection conn = null;
        CallableStatement callSt = null;
        Book book = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call Find_book_by_id(?)}");
            callSt.setString(1, bookId);
            ResultSet rs = callSt.executeQuery();
            book = new Book();
            if (rs.next()) {
                book.setBookId(rs.getString("book_id"));
                book.setBookName(rs.getString("book_name"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPages(rs.getInt("pages"));
                book.setContent(rs.getString("content"));
                book.setPublisher(rs.getString("publisher"));
                book.setPrice(rs.getFloat("price"));
                book.setTypeId(rs.getInt("type_id"));
                book.setBookStatus(rs.getBoolean("book_status"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return book;
    }

    public static boolean isExistBook(String bookId) {
        //false: Mã sách chưa tồn tại
        //true: mã sách đã tồn tại
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call is_exist_book(?,?)}");
            //set giá trị tham số vào
            callSt.setString(1, bookId);
            //Đăng ký kiểu dữ liệu cho tham số trả ra
            callSt.registerOutParameter(2, Types.INTEGER);
            //Thực hiện procedure
            callSt.execute();
            //Lấy giá trị tham số trả ra
            boolean result = callSt.getBoolean(2);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    public static boolean create(Book book) {
        //1. Tạo đối tượng Connection và CallableStatement
        //2. Gọi procedure
        //3. Thực hiện procedure và nhận kết quả
        //4. Đóng Connection và CallableStatement
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call Create_book(?,?,?,?,?,?,?,?,?,?)}");
            //Set giá trị cho tham số vào và đăng ký kiểu dữ liệu cho tham số ra
            callSt.setString(1, book.getBookId());
            callSt.setString(2, book.getBookName());
            callSt.setString(3, book.getTitle());
            callSt.setString(4, book.getAuthor());
            callSt.setInt(5, book.getPages());
            callSt.setString(6, book.getContent());
            callSt.setString(7, book.getPublisher());
            callSt.setFloat(8, book.getPrice());
            callSt.setInt(9, book.getTypeId());
            callSt.setBoolean(10, book.isBookStatus());
            //Thực hiện procedure
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static boolean updateBook(Book book) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call Update_book(?,?,?,?,?,?,?,?,?,?)}");
            callSt.setString(1, book.getBookId());
            callSt.setString(2, book.getBookName());
            callSt.setString(3, book.getTitle());
            callSt.setString(4, book.getAuthor());
            callSt.setInt(5, book.getPages());
            callSt.setString(6, book.getContent());
            callSt.setString(7, book.getPublisher());
            callSt.setFloat(8, book.getPrice());
            callSt.setInt(9, book.getTypeId());
            callSt.setBoolean(10, book.isBookStatus());
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static boolean delete(String bookId) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call Delete_book(?)}");
            callSt.setString(1, bookId);
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static List<Book> sortBookByPrice() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Book> listBook = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call Sort_Book_By_Price()}");
            callSt.executeQuery();
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getString("book_id"));
                book.setBookName(rs.getString("book_name"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPages(rs.getInt("pages"));
                book.setContent(rs.getString("content"));
                book.setPublisher(rs.getString("publisher"));
                book.setPrice(rs.getFloat("price"));
                book.setTypeId(rs.getInt("type_id"));
                book.setBookStatus(rs.getBoolean("book_status"));
                listBook.add(book);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listBook;
    }

    public static List<Book> searchBookByNameOrTitle(String searchStr) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Book> listBook = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call Find_book_by_name_title(?)}");
            callSt.setString(1, searchStr);
            callSt.executeQuery();
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getString("book_id"));
                book.setBookName(rs.getString("book_name"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPages(rs.getInt("pages"));
                book.setContent(rs.getString("content"));
                book.setPublisher(rs.getString("publisher"));
                book.setPrice(rs.getFloat("price"));
                book.setTypeId(rs.getInt("type_id"));
                book.setBookStatus(rs.getBoolean("book_status"));
                listBook.add(book);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listBook;
    }

    public static List<BookPublisher> countBooksByPublisher() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<BookPublisher> listBookPublisher = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call Book_statistics_by_publisher()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                BookPublisher bookPublisher = new BookPublisher();
                bookPublisher.setPublisher(rs.getString("publisher"));
                bookPublisher.setCountBook(rs.getInt("countBook"));
                listBookPublisher.add(bookPublisher);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listBookPublisher;
    }
}