CREATE DATABASE BOOKSTORE


--Base

CREATE TABLE Admin(
	admin_id varchar(10) PRIMARY KEY NOT NULL,
	username varchar(30),
	admin_password varchar(30)
)

CREATE TABLE Customers (
	customer_id varchar(10) PRIMARY KEY NOT NULL,
	[name] nvarchar(100),
	email varchar(40),
	customers_password varchar(30)
)
CREATE TABLE Author(
	author_id varchar(10) PRIMARY KEY NOT NULL,
	Author_Name nvarchar(50),
	DoB date,
	dsc Ntext
)
CREATE TABLE Product(
	product_id varchar(10) PRIMARY KEY NOT NULL,
	product_name nvarchar(30),
	product_type int not null,
	product_Quantity int,
	product_price money,
	Title nvarchar(50),
	author_id varchar(10),
	Publisher nvarchar(30),
	Publication_date date,
	manufacturer_name nvarchar(30),
	FOREIGN KEY (author_id) REFERENCES Author(author_id),
)

CREATE TABLE Orders(
	order_id varchar(10) PRIMARY KEY NOT NULL, 
	Order_date date,
	total_price char(10),
	customer_id varchar(10) FOREIGN KEY REFERENCES Customers(customer_id)
)

CREATE TABLE detailOrder(
	detail_order_id char(10) PRIMARY KEY NOT NULL,
	number varchar(10),
	order_id varchar(10) FOREIGN KEY REFERENCES Orders(order_id),
	product_id varchar(10) FOREIGN KEY REFERENCES Product(product_id),
)

CREATE TABLE Reviews (
	review_id varchar(10) PRIMARY KEY NOT NULL,
	Rating int,
	Comment nvarchar(100),
	product_id varchar(10) FOREIGN KEY REFERENCES Product(product_id),
	customer_id varchar(10) FOREIGN KEY REFERENCES Customers(customer_id)
)


CREATE TABLE Categories(
	category_id varchar(10) PRIMARY KEY NOT NULL,
	category_name nvarchar(20),
	category_type int,
)

CREATE TABLE Product_Categories(
	product_id varchar(10) NOT NULL,
	category_id varchar(10) NOT NULL,
	PRIMARY KEY (product_id,category_id),
	FOREIGN KEY (product_id) REFERENCES Product(product_id),
	FOREIGN KEY (category_id) REFERENCES Categories(category_id)
)


INSERT INTO Admin 
VALUES
('Ad01','admin','admin');
go

INSERT INTO Customers
VALUES 
('CUST001', 'John Smith', 'john.smith@example.com', 'password123'),
('CUST002', 'Jane Doe', 'jane.doe@example.com', 'password456'),
('CUST003', 'Bob Johnson', 'bob.johnson@example.com', 'password789'),
('CUST004', 'Samantha Lee', 'samantha.lee@example.com', 'password123'),
('CUST005', 'David Brown', 'david.brown@example.com', 'password456'),
('CUST006', 'Emily Kim', 'emily.kim@example.com', 'password789'),
('CUST007', 'Kevin Chen', 'kevin.chen@example.com', 'password123'),
('CUST008', 'Jessica Park', 'jessica.park@example.com', 'password456'),
('CUST009', 'Andrew Wong', 'andrew.wong@example.com', 'password789'),
('CUST010', 'Melissa Davis', 'melissa.davis@example.com', 'password123'),
('CUST011', 'Ryan Garcia', 'ryan.garcia@example.com', 'password456'),
('CUST012', 'Karen Rodriguez', 'karen.rodriguez@example.com', 'password789'),
('CUST013', 'James Kim', 'james.kim@example.com', 'password123'),
('CUST014', 'Sarah Lee', 'sarah.lee@example.com', 'password456'),
('CUST015', 'Chris Smith', 'chris.smith@example.com', 'password789'),
('CUST016', 'Megan Chen', 'megan.chen@example.com', 'password123'),
('CUST017', 'Ethan Davis', 'ethan.davis@example.com', 'password456'),
('CUST018', 'Ashley Wong', 'ashley.wong@example.com', 'password789'),
('CUST019', 'Michael Brown', 'michael.brown@example.com', 'password123'),
('CUST020', 'Emily Rodriguez', 'emily.rodriguez@example.com', 'password456'),
('CUST021', 'Steven Kim', 'steven.kim@example.com', 'password789'),
('CUST022', 'Amanda Lee', 'amanda.lee@example.com', 'password123'),
('CUST023', 'Justin Smith', 'justin.smith@example.com', 'password456'),
('CUST024', 'Grace Chen', 'grace.chen@example.com', 'password789'),
('CUST025', 'Daniel Davis', 'daniel.davis@example.com', 'password123'),
('CUST026', 'Sophia Park', 'sophia.park@example.com', 'password456'),
('CUST027', 'Brandon Wong', 'brandon.wong@example.com', 'password789'),
('CUST028', 'Rachel Kim', 'rachel.kim@example.com', 'password123'),
('CUST029', 'Jason Lee', 'jason.lee@example.com', 'password456'),
('CUST030', 'Olivia Rodriguez', 'olivia.rodriguez@example.com', 'password789'),
('CUST031', 'Jonathan Smith', 'jonathan.smith@example.com', 'password123'),
('CUST032', 'Emma Chen', 'emma.chen@example.com', 'password456'),
('CUST033', 'Tyler Davis', 'tyler.davis@example.com', 'password789');
go

INSERT INTO Author 
VALUES
 ('TG001', 'Trần Lập', '1974-05-05', 'Trần Lập là nhạc sĩ và ca sĩ người Việt Nam.'),
 ('TG002', 'Nguyễn Trọng Tạo', '1960-01-15', 'Nguyễn Trọng Tạo là nhà văn và nhà báo người Việt Nam.'),
 ('TG003', 'Bùi Ngọc Tấn', '1972-07-20', 'Bùi Ngọc Tấn là nhà văn và nhà báo người Việt Nam.'),
 ('TG004', 'Nguyễn Khắc Thiện', '1968-09-01', 'Nguyễn Khắc Thiện là nhà văn người Việt Nam.'),
 ('TG005', 'Ngô Tất Tố', '1917-03-25', 'Ngô Tất Tố là một nhà văn, nhà báo, và chính khách Việt Nam.'),
 ('TG006', 'Phạm Nguyên Ngọc', '1950-11-09', 'Phạm Nguyên Ngọc là một nhà văn, nhà báo và giáo sư người Việt Nam.'),
 ('TG007', 'Vũ Trọng Phụng', '1912-10-21', 'Vũ Trọng Phụng là một nhà văn, nhà báo, và chính khách Việt Nam.'),
 ('TG008', 'Nguyễn Nhật Ánh', '1955-05-07', 'Nguyễn Nhật Ánh là một nhà văn và nhà giáo người Việt Nam.'),
 ('TG009', 'Hồ Biểu Chánh', '1912-12-13', 'Hồ Biểu Chánh là một nhà văn, nhà báo, và chính khách Việt Nam.'),
 ('TG010', 'Trần Thị Minh Tuyết', '1940-06-28', 'Trần Thị Minh Tuyết là một nhà văn người Việt Nam.'),
 ('TG011', 'Lê Thị Tuyết Mai', '1943-03-20', 'Lê Thị Tuyết Mai là một nhà văn người Việt Nam.'),
 ('TG012', 'Bảo Ninh', '1952-10-18', 'Bảo Ninh là một nhà văn người Việt Nam.'),
 ('TG013', 'Nguyễn Thị Minh Khai', '1910-02-1910', 'Nguyễn Thị Minh Khai là một nhà cách mạng và nhà hoạt động đấu tranh cho quyền của phụ nữ và dân tộc Việt Nam.'),
 ('TG014', 'Vương Trọng', '1946-04-12', 'Vương Trọng là một nhà văn người Việt Nam.'),
 ('TG015', 'Thạch Lam', '1901-08-16', 'Thạch Lam là một nhà văn, nhà báo, và nhà hoạt động cách mạng Việt Nam.'),
 ('TG016', 'John Grisham', '1955-02-08', 'John Ray Grisham Jr. là một nhà văn, luật sư, chính trị gia và nhà hoạt động người Mỹ, nổi tiếng với các tiểu thuyết pháp lý.'),
('TG017', 'Agatha Christie', '1890-09-15', 'Agatha Christie là một nhà văn người Anh, tác giả của các tiểu thuyết trinh thám.'),
('TG018', 'J. K. Rowling', '1965-07-31', 'Joanne Rowling, được biết đến nhiều hơn với bút danh J. K. Rowling, là một nhà văn và nhà từ thiện người Anh.'),
('TG019', 'Stephen King', '1947-09-21', 'Stephen Edwin King là một nhà văn người Mỹ, tác giả của các tiểu thuyết kinh dị, siêu nhiên, hồi hộp, tội phạm, khoa học viễn tưởng và phù thủy.'),
('TG020', 'Dan Brown', '1964-06-22', 'Daniel Gerhard Brown là một nhà văn người Mỹ, nổi tiếng với các tiểu thuyết ly kỳ, bao gồm các tiểu thuyết về Robert Langdon như Thiên thần và Ác quỷ, Mật mã Da Vinci, Ký hiệu bị mất, Inferno và Nguyên tố.'),
('TG021', 'Paulo Coelho', '1947-08-24', 'Paulo Coelho là một nhà thơ và nhà văn người Brazil, nổi tiếng với tiểu thuyết Nhà giả kim.'),
('TG022', 'Gabriel García Márquez', '1927-03-06', 'Gabriel José de la Concordia García Márquez là một nhà văn, nhà viết truyện ngắn, nhà viết kịch và nhà báo người Colombia.'),
('TG023', 'Haruki Murakami', '1949-01-12', 'Haruki Murakami là một nhà văn người Nhật Bản.'),
('TG024', 'David Baldacci', '1960-08-05', 'David Baldacci là một nhà văn người Mỹ bán chạy nhất.'),
('TG025', 'Lee Child', '1954-10-29', 'Jim Grant, được biết đến với bút danh Lee Child, là một nhà văn người Anh viết các tiểu thuyết ly kỳ.');
go

INSERT INTO Product
VALUES
('P001', 'Tiểu thuyết Ngôi nhà ma', 1, 100, 55000, 'Ngôi nhà ma', 'TG001', 'Nhà xuất bản Văn học', '2020-05-15', 'NXB Văn học'),
('P002', 'Sách tham khảo Toán 12', 2, 50, 75000, 'Toán 12', 'TG008', 'Nhà xuất bản Giáo dục', '2021-01-05', 'NXB Giáo dục'),
('P003', 'Sách tham khảo Văn 10', 2, 80, 55000, 'Văn 10', 'TG008', 'Nhà xuất bản Giáo dục', '2019-07-15', 'NXB Giáo dục'),
('P004', 'Truyện tranh Doraemon', 3, 120, 25000, 'Doraemon', 'TG010', 'Nhà xuất bản Kim Đồng', '2018-12-30', 'NXB Kim Đồng'),
('P005', 'Tiểu thuyết Đảo mộng mơ', 1, 75, 45000, 'Đảo mộng mơ', 'TG011', 'Nhà xuất bản Văn học', '2017-06-10', 'NXB Văn học'),
('P006', 'Sách tham khảo Hóa học 11', 2, 60, 90000, 'Hóa học 11', 'TG008', 'Nhà xuất bản Giáo dục', '2019-02-12', 'NXB Giáo dục'),
('P007', 'Tiểu thuyết Kẻ trộm mộ', 1, 50, 65000, 'Kẻ trộm mộ', 'TG012', 'Nhà xuất bản Văn học', '2022-02-28', 'NXB Văn học'),
('P008', 'Truyện tranh One Piece', 3, 150, 35000, 'One Piece', 'TG013', 'Nhà xuất bản Kim Đồng', '2016-09-20', 'NXB Kim Đồng'),
('P009', 'Sách tham khảo Ngữ văn 9', 2, 100, 45000, 'Ngữ văn 9', 'TG008', 'Nhà xuất bản Giáo dục', '2020-10-01', 'NXB Giáo dục'),
('P010', 'Tiểu thuyết Đất nước đứng lên', 1, 70, 50000, 'Đất nước đứng lên', 'TG004', 'Nhà xuất bản Văn học', '2015-04-15', 'NXB Văn học'),
('P011', 'Truyện tranh Doraemon', 3, 110, 22000, 'Doraemon', 'TG010', 'Nhà xuất bản Kim Đồng', '2018-11-05', 'NXB Kim Đồng'),
('P012', 'Sách tham khảo Tiếng Anh 10', 2, 80, 65000, 'Tiếng Anh 10', 'TG008', 'Nhà xuất bản Giáo dục', '2022-01-10', 'NXB Giáo dục'),
('P013', 'Tiểu thuyết Tắt đèn', 1, 65, 42000, 'Tắt đèn', 'TG007', 'Nhà xuất bản Văn học', '2019-08-30', 'NXB Văn học'),
('P014', 'Sách tham khảo Lịch sử Việt Nam 12', 2, 40, 98000, 'Lịch sử Việt Nam 12', 'TG008', 'Nhà xuất bản Giáo dục', '2020-02-05', 'NXB Giáo dục'),
('P015', 'Tiểu thuyết Những nàng công chúa xinh đẹp', 1, 50, 55000, 'Những nàng công chúa xinh đẹp', 'TG005', 'Nhà xuất bản Văn học', '2017-12-20', 'NXB Văn học'),
('P016', 'Truyện tranh Doremon', 3, 90, 20000, 'Doremon', 'TG010', 'Nhà xuất bản Kim Đồng', '2021-05-15', 'NXB Kim Đồng'),
('P017', 'Sách tham khảo Sinh học 11', 2, 70, 85000, 'Sinh học 11', 'TG008', 'Nhà xuất bản Giáo dục', '2019-04-12', 'NXB Giáo dục'),
('P018', 'Tiểu thuyết Bến đỗ định mệnh', 1, 45, 50000, 'Bến đỗ định mệnh', 'TG003', 'Nhà xuất bản Văn học', '2018-08-10', 'NXB Văn học'),
('P019', 'Truyện tranh Conan', 3, 100, 30000, 'Conan', 'TG014', 'Nhà xuất bản Kim Đồng', '2020-10-25', 'NXB Kim Đồng'),
('P020', 'Sách tham khảo Vật lý 10', 2, 55, 75000, 'Vật lý 10', 'TG008', 'Nhà xuất bản Giáo dục', '2021-11-01', 'NXB Giáo dục'),
('P021', 'Tiểu thuyết Nơi lưu giữ kí ức', 1, 60, 48000, 'Nơi lưu giữ kí ức', 'TG006', 'Nhà xuất bản Văn học'),
('P022', 'Bút parker', 1, 50, 100, NULL, NULL, NULL, NULL, 'Parker'),
('P023', 'Bút lông chì', 1, 40, 80, NULL, NULL, NULL, NULL, 'Lông chì'),
('P024', 'Bản vẽ A3', 6, 30, 100, NULL, NULL, NULL, NULL, 'Tập vẽ'),
('P025', 'Bản vẽ A4', 6, 50, 60, NULL, NULL, NULL, NULL, 'Tập vẽ'),
('P026', 'Bộ sách Toán lớp 10', 4, 20, 200, 'Toán lớp 10', 'A001', 'NXB Giáo dục', '2020-01-01', 'NXB Giáo dục'),
('P027', 'Bộ sách Vật lý lớp 10', 4, 25, 250, 'Vật lý lớp 10', 'A002', 'NXB Giáo dục', '2020-01-01', 'NXB Giáo dục'),
('P028', 'Bộ sách Hóa học lớp 10', 4, 30, 300, 'Hóa học lớp 10', 'A003', 'NXB Giáo dục', '2020-01-01', 'NXB Giáo dục'),
('P029', 'Bộ bút chì 12 màu', 5, 20, 150, NULL, NULL, NULL, NULL, 'Bút chì'),
('P030', 'Bộ bút chì 24 màu', 5, 25, 200, NULL, NULL, NULL, NULL, 'Bút chì'),
('P031', 'Bộ giấy A4 500 tờ', 9, 50, 250, NULL, NULL, NULL, NULL, 'Giấy A4'),
('P032', 'Bộ giấy viết 100 tờ', 10, 20, 100, NULL, NULL, NULL, NULL, 'Giấy viết'),
('P033', 'Bộ giấy in 500 tờ', 11, 30, 300, NULL, NULL, NULL, NULL, 'Giấy in'),
('P034', 'Bộ giấy dán 100 tờ', 12, 20, 50, NULL, NULL, NULL, NULL, 'Giấy dán'),
('P035', 'Bộ giấy trắng 500 tờ', 13, 40, 200, NULL, NULL, NULL, NULL, 'Giấy trắng'),
('P036', 'Bộ giấy kẻ 100 tờ', 14, 15, 70, NULL, NULL, NULL, NULL, 'Giấy kẻ'),
('P037', 'Bộ giấy nhựa 100 tờ', 15, 20, 80, NULL, NULL, NULL, NULL, 'Giấy nhựa'),
('P038', 'Tập vẽ chì 24 màu', 2, 30, 200, NULL, NULL, NULL, NULL, 'Tập vẽ'),
('P039', 'Tập viết A5 100 tờ', 3, 25, 100, NULL, NULL, NULL, NULL, 'Tập viết'),
('P040', 'Tập viết A4 100 tờ', 3, 30, 120, NULL, NULL, NULL, NULL, 'Tập viết'),
('P041', 'Bộ sách Toán lớp 11', 4, 20, 200, 'Toán lớp 11', 'A001', 'NXB Giáo dục', '2021-01-01', 'NXB Giáo dục'),
('P042', 'Bộ sách Vật lý lớp 11', 4, 25, 250, 'Vật lý lớp 11', 'A002', 'NXB Giáo dục', '2021-01-01', 'NXB Giáo dục'),
('P043', 'Bộ sách Hóa học lớp 11', 4, 30, 300, 'Hóa học lớp 11', 'A003', 'NXB Giáo dục', '2021-01-01', 'NXB Giáo dục'),
('P044', 'Bộ sách Toán lớp 12', 4, 20, 200, 'Toán lớp 12', 'A001', 'NXB Giáo dục', '2022-01-01', 'NXB Giáo dục');
go

INSERT INTO Orders
VALUES
('ORDER001', '2022-01-01', '100.00', 'CUST001'),
('ORDER002', '2022-01-02', '200.00', 'CUST002'),
('ORDER003', '2022-01-03', '300.00', 'CUST003'),
('ORDER004', '2022-01-04', '400.00', 'CUST004'),
('ORDER005', '2022-01-05', '500.00', 'CUST005'),
('ORDER006', '2022-01-06', '100.00', 'CUST006'),
('ORDER007', '2022-01-07', '200.00', 'CUST007'),
('ORDER008', '2022-01-08', '300.00', 'CUST008'),
('ORDER009', '2022-01-09', '400.00', 'CUST009'),
('ORDER010', '2022-01-10', '500.00', 'CUST010'),
('ORDER011', '2022-01-11', '100.00', 'CUST011'),
('ORDER012', '2022-01-12', '200.00', 'CUST012'),
('ORDER013', '2022-01-13', '300.00', 'CUST013'),
('ORDER014', '2022-01-14', '400.00', 'CUST014'),
('ORDER015', '2022-01-15', '500.00', 'CUST015'),
('ORDER016', '2022-01-16', '100.00', 'CUST016'),
('ORDER017', '2022-01-17', '200.00', 'CUST017'),
('ORDER018', '2022-01-18', '300.00', 'CUST018'),
('ORDER019', '2022-01-19', '400.00', 'CUST019'),
('ORDER020', '2022-01-20', '500.00', 'CUST020'),
('ORDER021', '2022-01-21', '100.00', 'CUST021'),
('ORDER022', '2022-01-22', '200.00', 'CUST022'),
('ORDER023', '2022-01-23', '300.00', 'CUST023'),
('ORDER024', '2022-01-24', '400.00', 'CUST024'),
('ORDER025', '2022-01-25', '500.00', 'CUST025'),
('ORDER026', '2022-01-26', '100.00', 'CUST026'),
('ORDER027', '2022-01-27', '200.00', 'CUST027'),
('ORDER028', '2022-01-28', '300.00', 'CUST028'),
('ORDER029', '2022-01-29', '400.00', 'CUST029'),
('ORDER030', '2022-01-30', '500.00', 'CUST030');
go
INSERT INTO detailOrder
VALUES
('DETAIL001', '1', 'ORDER001', 'P001'),
('DETAIL002', '2', 'ORDER002', 'P002'),
('DETAIL003', '3', 'ORDER003', 'P003'),
('DETAIL004', '4', 'ORDER004', 'P004'),
('DETAIL005', '5', 'ORDER005', 'P005'),
('DETAIL006', '6', 'ORDER006', 'P006'),
('DETAIL007', '7', 'ORDER007', 'P007'),
('DETAIL008', '8', 'ORDER008', 'P008'),
('DETAIL009', '9', 'ORDER009', 'P009'),
('DETAIL010', '10', 'ORDER010', 'P010'),
('DETAIL011', '11', 'ORDER011', 'P011'),
('DETAIL012', '12', 'ORDER012', 'P012'),
('DETAIL013', '13', 'ORDER013', 'P013'),
('DETAIL014', '14', 'ORDER014', 'P014'),
('DETAIL015', '15', 'ORDER015', 'P015'),
('DETAIL016', '16', 'ORDER016', 'P016'),
('DETAIL017', '17', 'ORDER017', 'P017'),
('DETAIL018', '18', 'ORDER018', 'P018'),
('DETAIL019', '19', 'ORDER019', 'P019'),
('DETAIL020', '20', 'ORDER020', 'P020'),
('DETAIL021', '21', 'ORDER021', 'P021'),
('DETAIL022', '22', 'ORDER022', 'P001'),
('DETAIL023', '23', 'ORDER023', 'P002'),
('DETAIL024', '24', 'ORDER024', 'P003'),
('DETAIL025', '25', 'ORDER025', 'P004'),
('DETAIL026', '26', 'ORDER026', 'P009'),
('DETAIL027', '27', 'ORDER027', 'P010'),
('DETAIL028', '28', 'ORDER028', 'P011'),
('DETAIL029', '29', 'ORDER029', 'P012'),
('DETAIL030', '30', 'ORDER030', 'P013');
go

INSERT INTO Reviews
VALUES
('R001', 5, 'Tuyệt vời! Tôi rất thích cuốn sách này', 'P001', 'CUST001'),
('R002', 4, 'Tốt, nhưng cần cải thiện thêm', 'P002', 'CUST002'),
('R003', 5, 'Sách hay, giá cả cũng phải chăng', 'P003', 'CUST003'),
('R004', 3, 'Không tốt lắm, tôi không thích', 'P004', 'CUST004'),
('R005', 4, 'Sách trung bình, tôi sẽ tiếp tục mua', 'P005', 'CUST005'),
('R006', 2, 'Tôi không thích sách này', 'P006', 'CUST006'),
('R007', 5, 'Tuyệt vời, tôi rất thích', 'P007', 'CUST007'),
('R008', 4, 'Tốt, nhưng cần cải thiện thêm', 'P008', 'CUST008'),
('R009', 5, 'Sách hay, giá cả cũng phải chăng', 'P009', 'CUST009'),
('R010', 3, 'Không tốt lắm, tôi không thích', 'P010', 'CUST010'),
('R011', 4, 'Sách trung bình, tôi sẽ tiếp tục mua', 'P011', 'CUST011'),
('R012', 2, 'Tôi không thích sách này', 'P012', 'CUST012'),
('R013', 5, 'Tuyệt vời, tôi rất thích', 'P013', 'CUST013'),
('R014', 4, 'Tốt, nhưng cần cải thiện thêm', 'P014', 'CUST014'),
('R015', 5, 'Sách hay, giá cả cũng phải chăng', 'P015', 'CUST015'),
('R016', 3, 'Không tốt lắm, tôi không thích', 'P016', 'CUST016'),
('R017', 4, 'Sách trung bình, tôi sẽ tiếp tục mua', 'P017', 'CUST017'),
('R018', 2, 'Tôi không thích sách này', 'P018', 'CUST018'),
('R019', 5, 'Tuyệt vời, tôi rất thích', 'P019', 'CUST019'),
('R020', 4, 'Tốt, nhưng cần cải thiện thêm', 'P020', 'CUST020'),
('R021', 5, 'Sách hay, giá cả cũng phải chăng', 'P021', 'CUST021'),
('R022', 3, 'Không tốt lắm, tôi không thích', 'P022', 'CUST022'),
('R023', 4, 'Sách trung bình, tôi sẽ tiếp tục mua', 'P023', 'CUST023'),
('R024', 2, 'Tôi không thích sách này', 'P024', 'CUST024'),
('R025', 5, 'Tuyệt vời, tôi rất thích', 'P025', 'CUST025'),
('R026', 4, 'Tốt, nhưng cần cải thiện thêm', 'P026', 'CUST026'),
('R027', 5, 'Sách hay, giá cả cũng phải chăng', 'P027', 'CUST027'),
('R028', 3, 'Không tốt lắm, tôi không thích', 'P028', 'CUST028'),
('R029', 4, 'Sách trung bình, tôi sẽ tiếp tục mua', 'P029', 'CUST029'),
('R030', 2, 'Tôi không thích sách này', 'P030', 'CUST030');

INSERT INTO Categories
VALUES
('CAT001', 'Bút', 1),
('CAT002', 'Tập vẽ', 2),
('CAT003', 'Tập viết', 3),
('CAT004', 'Bộ sách', 4),
('CAT005', 'Bộ bút chì', 5),
('CAT006', 'Bộ bản vẽ', 6),
('CAT007', 'Bộ dụng cụ học sinh', 7),
('CAT008', 'Bộ bản phác thảo', 8),
('CAT009', 'Bộ giấy A4', 9),
('CAT010', 'Bộ giấy viết', 10),
('CAT011', 'Bộ giấy in', 11),
('CAT012', 'Bộ giấy dán', 12),
('CAT013', 'Bộ giấy trắng', 13),
('CAT014', 'Bộ giấy kẻ', 14),
('CAT015', 'Bộ giấy nhựa', 15);
go