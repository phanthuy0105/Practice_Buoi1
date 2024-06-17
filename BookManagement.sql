create database Book_Management;
use Book_Management;

create table Book_Type(
                          type_id int primary key auto_increment,
                          type_name varchar(100) not null unique,
                          descriptions text,
                          type_status bit
);

create table Books (
                      book_id char(5) primary key,
                      book_name varchar(100) not null unique,
                      title varchar(200) not null,
                      author varchar(200) not null,
                      pages int,
                      content text,
                      publisher varchar(200) not null,
                      price float check (price > 0) not null,
                      type_id int,
                      foreign key (type_id) references Book_Type(type_id),
                      book_status bit
);

insert into Book_Type(type_name, descriptions, type_status)
VALUES('Loại sách văn học','Đây là loại sách văn học',1),
      ('Loại sách thiếu nhi','Đây là loại sách thiếu nhi',0),
      ('Loại sách văn hoá xã hội','Đây là loại sách văn hóa xã hội',1),
      ('Loại sách triết học, tâm lý','Đây là loại sách triết học, tâm lý',1),
      ('Loại sách văn hóa thông tin','Đây là loại sách văn hóa thông tin',0),
      ('Loại sách công an nhân dân','Đây là loại sách công an nhân dân',0);
select * from Book_Type;

insert into Books (book_id, book_name, title, author, pages, content, publisher, price, type_id, book_status)
VALUES('B001','Chí Phèo','Đây là tác phẩm Chí Phèo','Nam Cao',196,'- Truyện ngắn Chí Phèo có tên là Cái lò gạch cũ; khi in sách lần đầu, nhà xuất bản tự ý đổi tên là Đôi lứa xứng đôi','Văn học',100000,1,1),
      ('B002','Tự Thú Của Người Gác Rừng','Đây là tác phẩm Tự Thú Của Người Gác Rừng','Đỗ Kim Cuông',60,'Tự thú của người gác rừng là truyện dài nhất (hơn 60 trang) được chọn làm tên cho cả tập truyện. Nhân vật Hàm trải qua bao biến thiên dâu bể để đến cuối đời tự thú những lỗi lầm của mình.','Văn hóa thông tin',150000,5,1),
      ('B003','Bản Chất Của Dối Trá','Đây là tác phẩm Bản Chất Của Dối Trá','Dan Ariely',331,'Trong cuốn sách Bản Chất Của Dối Trá, cuốn sách từng đoạt giải thưởng lớn - tác giả sách bán chạy nhất, Dan Ariely cho thấy tại sao một số điều dễ nói dối hơn những điều khác, làm thế nào để gặp ít vấn đề hơn chúng ta tưởng hơn khi bị lừa dối, và cách hoạt động kinh doanh đã mở đường cho các hành vi phi đạo đức, cả cố ý lẫn vô ý như thế nào.','NXB Thế Giới',200000,4,1),
      ('B004','Gió Lạnh Đầu Mùa','Đây là tác phẩm Gió Lạnh Đầu Mùa','Thạch Lam',204,'“Ngay trong tác phẩm đầu tay (Gió đầu mùa), người ta đã thấy Thạch Lam đứng vào một phái riêng… Ông có một ngòi bút lặng lẽ, điềm tĩnh vô cùng, ngòi bút chuyên tả tỉ mỉ những cái rất nhỏ và rất đẹp… Phải là người giàu tình cảm lắm mới viết được như vậy…”','Kim Đồng',220000,1,1),
      ('B005','100 Kỹ Năng Sinh Tồn','Đây là tác phẩm 100 Kỹ Năng Sinh Tồn','Clint Emerson',272,'Được viết bởi Clint Emerson – một cựu Đặc vụ SEAL, lực lượng tác chiến đặc biệt của Hải quân Hoa Kỳ, cuốn sách 100 kỹ năng sinh tồn của Đinh Tị Books là một trong những cuốn sách bán chạy của New York Times. Rõ ràng, chi tiết và được trình bày dễ hiểu, cuốn sách phác thảo những chiến lược cơ bản nhưng thiết thực giúp bảo vệ bản thân và thoát khỏi những tình huống khó khăn và nguy cấp nhất.','Thanh niên',50000,2,0),
      ('B006','Việt Nam phong tục','Đây là tác phẩm Việt Nam phong tục','Phan Kế Bính',438,'“Việt Nam phong tục” của tác giả Phan Kế Bính là một nghiên cứu công phu, tỉ mỉ những phong tục, tập quán hàng nghìn năm của người Việt, tồn tại bảo lưu trong các quan hệ gia đình, nơi làng xã và trong cộng đồng xã hội.','Giáo dục',180000,3,0),
      ('B007','Tuổi Thơ Dữ Dội','Đây là tác phẩm Tuổi Thơ Dữ Dội','Phùng Quán',720,'“Tuổi Thơ Dữ Dội” là một câu chuyện hay, cảm động viết về tuổi thơ. Sách dày 404 trang mà người đọc không bao giờ muốn ngừng lại, bị lôi cuốn vì những nhân vật ngây thơ có, khôn ranh có, anh hùng có, vì những sự việc khi thì ly kỳ, khi thì hài hước, khi thì gây xúc động đến ứa nước mắt...','Kim Đồng',160000,2,1);
select * from Books;

-- 1. Lấy tất cả các loại sách
DELIMITER &&
create procedure Find_all_book_type()
BEGIN
    select * from Book_Type;
end &&
DELIMITER &&;

-- 2. Lấy thông tin loại sách theo mã loại sách
DELIMITER &&
create procedure Find_book_type_by_id(
    type_id_in int
)
BEGIN
    select * from Book_Type where type_id = type_id_in;
end &&
DELIMITER &&

DELIMITER &&
create procedure is_exist_book_type (
    type_id_in int,
    out is_exist bit
)
begin
    declare cnt_book_type int;
    set cnt_book_type = (select count(*) from Book_Type where type_id = type_id_in);
    if cnt_book_type = 0 then
        set is_exist = 0;-- Mã loại sách không tồn tại
    else
        set is_exist = 1;-- Mã loại sách đã tồn tại
    end if;
end &&
DELIMITER &&;

-- 4. Create_book_type
DELIMITER &&
create procedure Create_book_type (
    type_name_in varchar(100),
    descriptions_in text,
    type_status_in bit
)
begin
    insert into Book_Type(type_name, descriptions, type_status)
    values (type_name_in, descriptions_in, type_status_in);
end &&
DELIMITER &&;

DELIMITER &&
create procedure Update_book_type (
    type_id_in int,
    type_name_in varchar(100),
    descriptions_in text,
    type_status_in bit
)
begin
    update Book_Type
    set type_name = type_name_in,
        descriptions = descriptions_in,
        type_status = type_status_in
    where type_id = type_id_in;
end &&
DELIMITER &&;

DELIMITER &&
create procedure Delete_book_type(type_id_in int)
begin
    delete from Book_Type where type_id = type_id_in;
end &&
DELIMITER &&;

DELIMITER &&
create procedure Count_book_by_type_id(
    type_id_in int
)
BEGIN
    select b.type_id, bt.type_name, count(b.book_id) as 'CountBooks'
    from Book_Type bt join Books b on bt.type_id = b.type_id where bt.type_id = type_id_in
    group by b.type_id;
end &&
DELIMITER &&;

-- BOOK
-- 1. Lấy tất cả sách
DELIMITER &&
create procedure Find_all_book()
BEGIN
    select * from books;
end &&
DELIMITER &&;

DELIMITER &&
create procedure Find_book_by_id(book_id_in char(5))
begin
    select * from Books where book_id like book_id_in;
end &&
DELIMITER &&;

DELIMITER &&
create procedure is_exist_book (
    book_id_in char(5),
    out is_exist bit
)
begin
    declare cnt_books int;
    set cnt_books = (select count(*) from Books where book_id like book_id_in);
    if cnt_books = 0 then
        set is_exist = 0;-- Mã sách không tồn tại
    else
        set is_exist = 1;-- Mã sách đã tồn tại
    end if;
end &&
DELIMITER &&;

DELIMITER &&
create procedure Create_book (
    book_id_in char(5),
    book_name_in varchar(100),
    title_in varchar(200),
    author_in varchar(200),
    pages_in int,
    content_in text,
    publisher_in varchar(100),
    price_in int,
    type_id_in int,
    book_status_in bit
)
begin
    insert into Books(book_id, book_name, title, author, pages, content, publisher, price, type_id, book_status)
    values (book_id_in, book_name_in, title_in, author_in, pages_in, content_in, publisher_in, price_in, type_id_in,book_status_in);
end &&
DELIMITER &&;

DELIMITER &&
create procedure Update_book (
    book_id_in char(5),
    book_name_in varchar(100),
    title_in varchar(200),
    author_in varchar(200),
    pages_in int,
    content_in text,
    publisher_in varchar(100),
    price_in int,
    type_id_in int,
    book_status_in bit
)
begin
    update Books
    set book_name = book_name_in,
        title = title_in,
        author = author_in,
        pages = pages_in,
        content = content_in,
        publisher = publisher_in,
        price = price_in,
        type_id = type_id_in,
        book_status = book_status_in
    where book_id like book_id_in;
end &&
DELIMITER &&;

DELIMITER &&
create procedure Delete_book (
    book_id_in char(5)
)
begin
    delete from Books where Books.book_id like book_id_in;
end &&
DELIMITER &&;

DELIMITER &&
create procedure Sort_Book_By_Price()
begin
    select *
    from Books b
    order by b.price DESC;
end &&
DELIMITER &&;

DELIMITER &&
create procedure Find_book_by_name_title (
    search_str varchar(100)
)
begin
    select * from Books b where b.book_name like concat('%', search_str, '%') or b.title like concat('%', search_str, '%');
end &&
DELIMITER &&;

DELIMITER &&
create procedure Book_statistics_by_publisher()
BEGIN
    select b.publisher, count(b.book_id) as 'CountBook'
    from Books b
    group by b.publisher;
end &&
DELIMITER &&;


