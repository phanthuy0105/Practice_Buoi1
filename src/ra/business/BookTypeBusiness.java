package ra.business;

import ra.entity.BookType;
import ra.entity.BookTypeBooks;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class BookTypeBusiness {
    public static List<BookType> findAll() {
        //1. Tạo kết nối đến CSDL --> tạo đối tượng callableStatement
        Connection conn = null;
        CallableStatement callSt = null;
        List<BookType> listBookType = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call Find_all_book_type()}");
            //2. set giá trị các tham số vào và đăng ký kiểu dữ liệu cho tham số trả ra
            //3. Thực hiện procedure và nhận kết quả --> listBook
            ResultSet rs = callSt.executeQuery();
            listBookType = new ArrayList<>();
            while (rs.next()) {
                //Duyệt 1 dòng dữ liệu trong resultset
                BookType bookType = new BookType();
                bookType.setTypeId(rs.getInt("type_id"));
                bookType.setTypeName(rs.getString("type_name"));
                bookType.setDescriptions(rs.getString("descriptions"));
                bookType.setTypeStatus(rs.getBoolean("type_status"));
                listBookType.add(bookType);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //Đóng kết nối khi làm việc xong
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listBookType;
    }

    public static BookType getBookTypeById(int typeId) {
        Connection conn = null;
        CallableStatement callSt = null;
        BookType bookType = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call Find_book_type_by_id(?)}");
            callSt.setInt(1, typeId);
            ResultSet rs = callSt.executeQuery();
            bookType = new BookType();
            if (rs.next()) {
                bookType.setTypeId(rs.getInt("type_id"));
                bookType.setTypeName(rs.getString("type_name"));
                bookType.setDescriptions(rs.getString("descriptions"));
                bookType.setTypeStatus(rs.getBoolean("type_status"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return bookType;
    }

    public static boolean isExistBookType(int typeId) {
        //false: Mã loại sách chưa tồn tại
        //true: mã loại sách đã tồn tại
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call is_exist_book_type(?,?)}");
            //set giá trị tham số vào
            callSt.setInt(1, typeId);
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

    public static boolean create(BookType bookType) {
        //1. Tạo đối tượng Connection và CallableStatement
        //2. Gọi procedure
        //3. Thực hiện procedure và nhận kết quả
        //4. Đóng Connection và CallableStatement
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call Create_book_type(?,?,?)}");
            //Set giá trị cho tham số vào và đăng ký kiểu dữ liệu cho tham số ra
            callSt.setString(1, bookType.getTypeName());
            callSt.setString(2, bookType.getDescriptions());
            callSt.setBoolean(3, bookType.isTypeStatus());
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

    public static boolean updateBookType(BookType bookType) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call Update_book_type(?,?,?,?)}");
            callSt.setInt(1, bookType.getTypeId());
            callSt.setString(2, bookType.getTypeName());
            callSt.setString(3, bookType.getDescriptions());
            callSt.setBoolean(4, bookType.isTypeStatus());
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static boolean delete(int typeId) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call Delete_book_type(?)}");
            callSt.setInt(1, typeId);
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static BookTypeBooks countBooksByTypeId(int typeId) {
        Connection conn = null;
        CallableStatement callSt = null;
        BookTypeBooks bookTypeBooks = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call Count_book_by_type_id(?)}");
            callSt.setInt(1, typeId);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                bookTypeBooks = new BookTypeBooks();
                bookTypeBooks.setBookTypeId(rs.getInt("type_id"));
                bookTypeBooks.setBookTypeName(rs.getString("type_name"));
                bookTypeBooks.setCountBooks(rs.getInt("countBooks"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return bookTypeBooks;
    }
}
