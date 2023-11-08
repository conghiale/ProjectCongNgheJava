-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 30, 2023 at 06:37 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bookstore`
--

-- --------------------------------------------------------

--
-- Table structure for table `authors`
--

CREATE TABLE `authors` (
  `id` binary(16) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `authors`
--

INSERT INTO `authors` (`id`, `description`, `dob`, `name`) VALUES
(0x886fb5aadad311edb30b2438aebfb241, 'Nhà văn nổi tiếng với nhiều tác phẩm nổi bật như \"Cho tôi xin một vé đi tuổi thơ\"', '1990-05-12', 'Nguyễn Nhật Ánh'),
(0x886fb957dad311edb30b2438aebfb241, 'Tác giả của nhiều tác phẩm nổi tiếng, trong đó có \"Tôi thấy hoa vàng trên cỏ xanh\"', '1985-09-20', 'Nguyễn Thị Minh Khai'),
(0x886fb996dad311edb30b2438aebfb241, 'Nhà thơ nổi tiếng với nhiều tác phẩm nổi bật như \"Trăm hoa đua nở\"', '1970-01-02', 'Hồ Xuân Hương'),
(0x886fb9b4dad311edb30b2438aebfb241, 'Nhà văn nổi tiếng với nhiều tác phẩm đạt giải thưởng cao như \"Dế Mèn Phiêu Lưu Ký\"', '1955-12-01', 'Tô Hoài'),
(0x886fb9d2dad311edb30b2438aebfb241, 'Tác giả của nhiều tác phẩm nổi tiếng như \"Nỗi buồn chiến tranh\" và \"Bến đò\"', '1947-03-20', 'Nhất Linh'),
(0x886fb9eddad311edb30b2438aebfb241, 'Nhà văn nổi tiếng với nhiều tác phẩm nổi bật như \"Tắt đèn\"', '1960-07-15', 'Ngô Tất Tố'),
(0x886fba06dad311edb30b2438aebfb241, 'Nhà thơ nổi tiếng với nhiều tác phẩm đạt giải thưởng cao như \"Những bài thơ trong tù\"', '1920-04-05', 'Xuân Diệu'),
(0x886fba23dad311edb30b2438aebfb241, 'Tác giả của nhiều tác phẩm nổi tiếng như \"Bến đợi chiều\" và \"Tắt đèn\"', '1952-08-10', 'Nguyễn Tuân'),
(0x886fba3ddad311edb30b2438aebfb241, 'Nhà văn nổi tiếng với nhiều tác phẩm nổi bật như \"Nhật ký trong tù\"', '1973-11-25', 'Hà Thủy Nguyên'),
(0x886fba59dad311edb30b2438aebfb241, 'Nhà thơ nổi tiếng với nhiều tác phẩm đạt giải thưởng cao như \"Những bài thơ trong tù\"', '1918-02-14', 'Tố Hữu'),
(0x886fba73dad311edb30b2438aebfb241, 'Tác giả của nhiều tác phẩm nổi tiếng như \"Bóng mát trong mưa mùa hạ\" và \"Thiên đường mùa thu\"', '1967-06-30', 'Nguyễn Quang Sáng'),
(0x886fba8fdad311edb30b2438aebfb241, 'Nhà văn nổi tiếng với nhiều tác phẩm nổi bật như \"Kính vạn hoa\" và \"Mây lang thang\"', '1982-09-18', 'Trang Ha'),
(0x886fbaa8dad311edb30b2438aebfb241, 'Nhà thơ nổi tiếng với nhiều tác phẩm đạt giải thưởng cao như \"Đêm buồn ngủ không yên\"', '1933-11-10', 'Chế Lan Viên'),
(0x886fbaefdad311edb30b2438aebfb241, 'Tác giả của nhiều tác phẩm nổi tiếng như \"Bên kia bờ vực\" và \"Đất nước\"', '1959-07-25', 'Trần Hữu Tri'),
(0x886fbb0fdad311edb30b2438aebfb241, 'Nhà văn nổi tiếng với nhiều tác phẩm nổi bật như \"Những đồi hoa sim\" và \"Làng\"', '1932-12-28', 'Hoàng Minh Tuấn'),
(0x886fbb44dad311edb30b2438aebfb241, 'Tác giả của nhiều tác phẩm nổi tiếng như \"Những ngôi sao xa xôi\" và \"Vòng tròn 12 sao\"', '1976-05-20', 'Nguyễn Ngọc Thuần'),
(0x886fbb7bdad311edb30b2438aebfb241, 'Nhà thơ nổi tiếng với nhiều tác phẩm đạt giải thưởng cao như \"Đi qua mùa hạ\"', '1929-11-20', 'Xuân Diệu'),
(0x886fbb95dad311edb30b2438aebfb241, 'Tác giả của nhiều tác phẩm nổi tiếng như \"Dòng sông không trở lại\" và \"Đất lành chim đậu\"', '1968-09-10', 'Phạm Thị Hoài'),
(0x886fbbd0dad311edb30b2438aebfb241, 'Nhà văn nổi tiếng với nhiều tác phẩm nổi bật như \"Truyện kiều\" và \"Lão Hạc\"', '1765-09-19', 'Nguyễn Du'),
(0x886fbbf9dad311edb30b2438aebfb241, 'Nhà thơ nổi tiếng với nhiều tác phẩm đạt giải thưởng cao như \"Trăm năm không quên\"', '1922-01-01', 'Xuân Quỳnh'),
(0x886fbc1edad311edb30b2438aebfb241, 'Tác giả của nhiều tác phẩm nổi tiếng như \"Những cánh hoa trên cát\" và \"Mùa thu chết\"', '1952-03-28', 'Bùi Giáng'),
(0x886fbc3ddad311edb30b2438aebfb241, 'Nhà văn nổi tiếng với nhiều tác phẩm nổi bật như \"Số đỏ\" và \"Chiếc lá cuối cùng\"', '1954-05-21', 'Vũ Trọng Phụng'),
(0x886fbc59dad311edb30b2438aebfb241, 'Nhà thơ nổi tiếng với nhiều tác phẩm đạt giải thưởng cao như \"Thơ chọn lọc\" và \"Lạc loài\"', '1925-03-04', 'Ngô Thì Nhậm'),
(0x886fbc75dad311edb30b2438aebfb241, 'Tác giả của nhiều tác phẩm nổi tiếng như \"Cánh đồng bất tận\" và \"Chí Phèo\"', '1910-01-09', 'Nam Cao'),
(0x886fbc91dad311edb30b2438aebfb241, 'Nhà văn nổi tiếng với nhiều tác phẩm nổi bật như \"Mặt trời bên kia núi\" và \"Nước sông Hồng\"', '1945-07-17', 'Bảo Ninh'),
(0x886fbcacdad311edb30b2438aebfb241, 'Nhà thơ nổi tiếng với nhiều tác phẩm đạt giải thưởng cao như \"Mưa trên phố Huế\" và \"Bài thơ cho em\"', '1945-02-22', 'Trần Đăng Khoa'),
(0x886fbccbdad311edb30b2438aebfb241, 'Tác giả của nhiều tác phẩm nổi tiếng như \"Kỹ nghệ lấy tây\" và \"Đất nước và con người Việt Nam\"', '1921-01-20', 'Hồ Biểu Chánh'),
(0x886fbceadad311edb30b2438aebfb241, 'Nhà văn nổi tiếng với nhiều tác phẩm nổi bật như \"Những ngày thứ ba với tình yêu\" và \"Tắt đèn\"', '1964-11-20', 'Nguyễn Bá Trác'),
(0x886fbd07dad311edb30b2438aebfb241, 'Nhà thơ nổi tiếng với nhiều tác phẩm đạt giải thưởng cao như \"Mưa nửa đêm\" và \"Nắng mùa hạ\"', '1934-04-15', 'Huy Cận'),
(0x886fbd23dad311edb30b2438aebfb241, 'Tác giả của nhiều tác phẩm nổi tiếng như \"Truyện ngắn\" và \"Hoa đỏ biên giới\"', '1914-12-23', 'Nguyễn Tuân'),
(0x886fbd5adad311edb30b2438aebfb241, 'Nhà văn nổi tiếng với nhiều tác phẩm nổi bật như \"Đất rừng phương Nam\" và \"Tôi thấy hoa vàng trên cỏ xanh\"', '1972-05-02', 'Nguyễn Nhật Ánh'),
(0x886fbd7adad311edb30b2438aebfb241, 'Nhà thơ nổi tiếng với nhiều tác phẩm đạt giải thưởng cao như \"Nỗi buồn chiến tranh\" và \"Tình yêu và mùa thu\"', '1920-03-19', 'Tố Hữu'),
(0x886fbd99dad311edb30b2438aebfb241, 'Tác giả của nhiều tác phẩm nổi tiếng như \"Những đồi hoa sim\" và \"Chiếc thuyền ngoài xa\"', '1920-01-10', 'Nguyễn Huy Thiệp'),
(0x886fbdb7dad311edb30b2438aebfb241, 'Nhà văn nổi tiếng với nhiều tác phẩm nổi bật như \"Đêm trong căn phòng\" và \"Thiên đường mù\"', '1970-06-14', 'Ngô Thế Vinh'),
(0x886fbdd3dad311edb30b2438aebfb241, 'Nhà thơ nổi tiếng với nhiều tác phẩm đạt giải thưởng cao như \"Tuyển tập thơ mới\" và \"Hạnh phúc dại khờ\"', '1916-11-14', 'Xuân Diệu'),
(0x886fbdf3dad311edb30b2438aebfb241, 'Tác giả của nhiều tác phẩm nổi tiếng như \"Gạo vàng\" và \"Nỗi buồn chiến tranh\"', '1922-03-12', 'Nông Quốc Chấn'),
(0x886fbe0fdad311edb30b2438aebfb241, 'Nhà văn nổi tiếng với nhiều tác phẩm nổi bật như \"Mẹ\" và \"Những ngày thơ ấu\"', '1920-06-21', 'Kim Lân'),
(0x886fbe2bdad311edb30b2438aebfb241, 'Nhà thơ nổi tiếng với nhiều tác phẩm đạt giải thưởng cao như \"Sắc tình\" và \"Rồi người thương cũng biết qua\"', '1934-12-24', 'Hoàng Cầm'),
(0x886fbe4adad311edb30b2438aebfb241, 'Tác giả của nhiều tác phẩm nổi tiếng như \"Những đồi hoa mặt trời\" và \"Những cánh chim biết bay\"', '1958-02-17', 'Nguyễn Đình Thi');

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `description`, `name`) VALUES
(1, 'Tiểu thuyết là các câu chuyện dài với các nhân vật được phát triển chi tiết, với một cốt truyện phức tạp và sự kiện nhiều tình tiết. ', 'Tiểu thuyết'),
(2, 'Truyện ngắn là các câu chuyện ngắn với độ dài thường từ 1 đến 20 trang, tập trung vào một sự kiện hoặc một nhân vật chính.', 'Truyện ngắn'),
(3, 'Kinh doanh và tài chính là các cuốn sách chuyên sâu về kinh doanh, tài chính, đầu tư và quản lý.', 'Kinh doanh và tài chính'),
(4, 'Khoa học viễn tưởng là các câu chuyện về thế giới tương lai, các nền văn minh ảo, hoặc các sự kiện khoa học viễn tưởng.', 'Khoa học viễn tưởng'),
(5, 'Lịch sử là các cuốn sách về lịch sử, bao gồm cả sử thi, tiểu sử, và các tài liệu lịch sử.', 'Lịch sử'),
(6, 'Tâm lý học là các cuốn sách về tâm lý học, giúp người đọc hiểu về bản thân và xây dựng các kỹ năng tự giúp bản thân.', 'Tâm lý học'),
(7, 'Văn học cổ điển là các tác phẩm văn học được viết từ những thế kỷ trước, thường là các tác phẩm văn học nổi tiếng, tác phẩm kinh điển.', 'Văn học cổ điển'),
(8, 'Công nghệ Thể loại sách công nghệ bao gồm các tài liệu, sách hướng dẫn, sách giáo khoa, sách báo cáo, sách tài liệu v.v. về các lĩnh vực công nghệ', 'Công nghệ'),
(9, 'Nghệ thuật bao gồm các tài liệu, sách hướng dẫn, sách giáo khoa, sách báo cáo, sách tài liệu v.v. về các lĩnh vực nghệ thuật', 'Nghệ thuật'),
(10, 'Y học bao gồm các tài liệu, sách hướng dẫn, sách giáo khoa, sách báo cáo, sách tài liệu v.v. về các lĩnh vực y học', 'Y học');

-- --------------------------------------------------------

--
-- Table structure for table `detail_orders`
--

CREATE TABLE `detail_orders` (
  `cost` double DEFAULT NULL,
  `create_at` date DEFAULT NULL,
  `is_pay` tinyint(1) NOT NULL DEFAULT 0,
  `quantity` int(11) DEFAULT NULL,
  `product_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `cost` double DEFAULT NULL,
  `create_at` date DEFAULT NULL,
  `user_id` binary(16) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `publication_date` date DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `sale` bit(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `author_id` binary(16) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `image`, `name`, `price`, `publication_date`, `publisher`, `sale`, `title`, `author_id`, `category_id`) VALUES
(2, 'image.jpg', 'Dế Mèn phiêu lưu ký', 26000, '1941-05-02', 'NXB Văn học', b'1', 'Dế Mèn phiêu lưu ký là một tác phẩm của nhà văn Tô Hoài. Cuốn sách kể về cuộc phiêu lưu của chú dế Mèn và những người bạn trong việc giúp đỡ con kiến đỏ. Đây là một trong những cuốn sách thiếu nhi nổi tiếng nhất của Việt Nam.', 0x886fb9b4dad311edb30b2438aebfb241, 1),
(39, 'image.jpg', 'Trang trí nội thất', 90000, '2018-06-06', 'NXB Thế giới', b'1', 'Cuốn sách này giúp độc giả hiểu cách trang trí nội thất đẹp mắt và tiện nghi. Sách cung cấp các kỹ thuật trang trí phòng khách, phòng ngủ, phòng tắm và nhà bếp.', 0x886fbdb7dad311edb30b2438aebfb241, 9),
(40, 'image.jpg', 'Sổ tay lâm sàng cận lâm sàng', 150000, '2019-06-06', 'NXB Y học', b'1', 'Cuốn sách cung cấp cho độc giả các kiến thức về lâm sàng và cận lâm sàng, giúp bác sĩ và sinh viên Y học nắm được các phương pháp chẩn đoán và điều trị các bệnh lý thông thường.', 0x886fba73dad311edb30b2438aebfb241, 10),
(5, 'image.jpg', 'Chí Phèo', 30000, '1954-02-06', 'NXB Thế giới', b'1', 'Cuốn tiểu thuyết về cuộc đời bi thảm của Chí Phèo, một nhân vật tội lỗi trong xã hội đói nghèo, với những tình tiết cảm động và sâu sắc.', 0x886fbdd3dad311edb30b2438aebfb241, 1),
(6, 'image.jpg', 'Tắt đèn', 30000, '2020-01-06', 'NXB Hội nhà văn', b'1', 'Tắt Đèn là một tác phẩm văn học lớn của nhà văn Ngô Tất Tố, được viết trong giai đoạn đầu của tác giả, khi ông vừa trở về từ Trung Quốc vào năm 1936.', 0x886fbd07dad311edb30b2438aebfb241, 1),
(7, 'image.jpg', 'Sống một phút một giây', 40000, '2018-03-06', 'Nhà xuất bản: Nhã Nam', b'1', 'Sống một phút một giây là một trong những tác phẩm nổi tiếng của nhà văn Nguyễn Nhật Ánh. Được viết dưới hình thức truyện ngắn, tác phẩm này kể về cuộc đời của một cậu bé nghèo tên là Ngọc.', 0x886fb5aadad311edb30b2438aebfb241, 2),
(38, 'image.jpg', 'Làm phim – Từ kịch bản đến sản xuất', 120000, '2019-06-06', 'NXB Thế giới', b'1', 'Cuốn sách này giúp độc giả hiểu rõ về quá trình làm phim, từ kịch bản đến sản xuất. Sách cung cấp các kỹ thuật quay phim, chỉ đạo diễn xuất, cắt ghép, âm thanh và dựng phim.', 0x886fb9d2dad311edb30b2438aebfb241, 9),
(10, 'image.jpg', 'Cánh đồng bất tận', 42000, '2017-12-06', 'Văn Việt', b'1', 'Cánh đồng bất tận là tác phẩm nổi tiếng của nhà văn Nguyễn Ngọc Tư. Đây là một câu chuyện đầy cảm xúc về tình cảm gia đình, tình bạn và tình yêu.', 0x886fb5aadad311edb30b2438aebfb241, 2),
(35, 'image.jpg', 'Nam Cao tuyển tập', 70000, '2019-06-06', 'NXB Văn học', b'1', 'Tuyển tập gồm những tác phẩm văn học nổi tiếng của tác giả Nam Cao như \"Chí Phèo\", \"Lão Hạc\", \"Tắt đèn\",... Những tác phẩm này đã trở thành những tác phẩm văn học cổ điển Việt Nam, tác động sâu sắc đến văn học Việt Nam.', 0x886fbd07dad311edb30b2438aebfb241, 7),
(34, 'image.jpg', 'Truyện Kiều', 50000, '2020-06-06', 'NXB Văn học', b'1', 'Đây là một trong những tác phẩm văn học lớn nhất trong văn học Việt Nam. Tác phẩm được xem là \"kho tàng tri thức vô tận\" của văn học cổ điển Việt Nam. Truyện Kiều đã được dịch và xuất bản bằng nhiều ngôn ngữ trên thế giới.', 0x886fbdd3dad311edb30b2438aebfb241, 7),
(13, 'image.jpg', 'Tư duy chiến lược', 175000, '2003-06-06', ' NXB Lao động Xã hội', b'1', 'Tác phẩm giúp bạn xây dựng một tư duy chiến lược đúng đắn, giúp doanh nghiệp phát triển bền vững.', 0x886fbceadad311edb30b2438aebfb241, 3),
(14, 'image.jpg', 'Từ tốn đến thành công', 108000, '2003-06-06', 'NXB Trẻ', b'1', 'Tác phẩm giúp bạn phát triển kỹ năng lãnh đạo và định hướng cho mục tiêu sự nghiệp của mình.', 0x886fbc3ddad311edb30b2438aebfb241, 3),
(41, 'image.jpg', 'Điều trị các bệnh lý huyết áp', 120000, '2018-06-06', 'NXB Y học', b'1', 'Cuốn sách cung cấp kiến thức về các bệnh lý huyết áp và các phương pháp điều trị hiệu quả nhất, giúp người đọc nắm được cách phòng ngừa và điều trị các bệnh lý này.', 0x886fba73dad311edb30b2438aebfb241, 10),
(16, 'image.jpg', 'Đọc vị bất kỳ ai', 750000, '2010-06-06', ' NXB Lao động Xã hội', b'1', 'Tác phẩm giúp bạn phân tích và đọc vị người khác để trở thành một người giao tiếp tốt hơn.', 0x886fbd7adad311edb30b2438aebfb241, 3),
(33, 'image.jpg', 'Nói lên điều bất ngờ của tâm lý con người', 700000, '2016-06-06', 'NXB Hội Nhà văn', b'1', 'Cuốn sách cung cấp cho đọc giả những hiểu biết về tâm lý con người, từ đó giúp họ hiểu rõ hơn về chính mình và những người xung quanh.', 0x886fbc3ddad311edb30b2438aebfb241, 6),
(19, 'image.jpg', 'Điệp Viên Không Gian', 100000, '2006-06-06', 'NXB Trẻ', b'1', 'Cuốn sách kể về chuyến phiêu lưu không gian của những nhà điều tra vũ trụ tới một hành tinh xa xôi.', 0x886fbdb7dad311edb30b2438aebfb241, 4),
(32, 'image.jpg', 'Tâm lý học đại cương', 100000, '2018-06-06', 'NXB Đại học Quốc gia Hà Nội', b'1', 'Cuốn sách trình bày những khái niệm cơ bản trong tâm lý học, các phương pháp và kỹ năng giúp đọc giả hiểu rõ hơn về bản chất của con người.', 0x886fbbf9dad311edb30b2438aebfb241, 6),
(21, 'image.jpg', 'Người máy', 800000, '2001-06-06', 'NXB Hội Nhà văn', b'1', 'Cuốn sách tập trung vào việc giải quyết các vấn đề đạo đức và tâm lý của các robot thông minh.', 0x886fb9d2dad311edb30b2438aebfb241, 4),
(22, 'image.jpg', 'Chiến tranh và hòa bình', 600000, '2010-06-06', 'NXB Hội Nhà văn', b'1', 'Cuốn sách nổi tiếng về viễn tưởng và xã hội học của H.G. Wells.', 0x886fbdd3dad311edb30b2438aebfb241, 4),
(23, 'image.jpg', 'Người tình của hoàng tử vũ trụ', 120000, '2019-06-06', 'NXB Tổng hợp TPHCM', b'1', 'Cuốn tiểu thuyết khoa học viễn tưởng kể về một chàng trai sống ở Tokyo bị cuốn vào một cuộc phiêu lưu đầy bí ẩn với những sinh vật ngoài hành tinh.', 0x886fbd7adad311edb30b2438aebfb241, 4),
(25, 'image.jpg', 'Việt Nam: Lịch sử từ Gia Long đến Đổi mới', 350000, '2016-06-06', 'NXB Thế giới', b'1', 'Cuốn sách tóm tắt lịch sử của Việt Nam từ thời kỳ Gia Long cho đến Đổi mới, với những sự kiện, nhân vật và tình huống quan trọng.', 0x886fbbd0dad311edb30b2438aebfb241, 5),
(37, 'image.jpg', 'Lập trình Python cơ bản', 120000, '2021-06-06', 'NXB Đại học Quốc gia Hà Nội', b'1', 'Cuốn sách này cung cấp kiến thức cơ bản về lập trình Python cho người mới bắt đầu. Sách được trình bày dễ hiểu, với nhiều ví dụ minh họa và bài tập giúp độc giả nắm vững kiến thức.', 0x886fb9d2dad311edb30b2438aebfb241, 8),
(36, 'image.jpg', 'Nhập môn Khoa học Dữ liệu', 160000, '2020-06-06', 'NXB Đại học Quốc gia Hà Nội', b'1', 'Cuốn sách giúp độc giả hiểu rõ hơn về lĩnh vực khoa học dữ liệu, cung cấp các khái niệm cơ bản, kỹ thuật, công cụ và ứng dụng của khoa học dữ liệu.', 0x886fba3ddad311edb30b2438aebfb241, 8),
(29, 'image.jpg', 'Việt Nam, một lịch sử dài', 200000, '2007-06-06', 'NXB Hồng Đức', b'1', 'Cuốn sách tập trung vào lịch sử Việt Nam từ thời kỳ sơ khai cho đến hiện đại, với những tình tiết đầy hấp dẫn và chi tiết.', 0x886fbb0fdad311edb30b2438aebfb241, 5),
(30, 'image.jpg', 'Lịch sử thế giới hiện đại', 350000, '2016-06-06', 'NXB Thế giới', b'1', 'Cuốn sách trình bày lịch sử thế giới từ thế kỷ 19 đến hiện tại, với các sự kiện quan trọng và nhân vật lịch sử.', 0x886fba8fdad311edb30b2438aebfb241, 5);

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE `reviews` (
  `id` int(11) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `user_id` binary(16) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `description` varchar(100) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `description`, `name`) VALUES
(1, 'Khách hàng', 'ROLE_CUSTOMER'),
(2, 'Quản trị viên', 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` binary(16) NOT NULL,
  `dob` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `dob`, `email`, `gender`, `name`, `password`, `phone`, `username`, `enabled`) VALUES
(0x4b98023fa62a49fcaa5eb26255498995, '2002-10-28', 'conghiale@gmail.com', 'Male', 'Le Cong Nghia', '$2a$10$RBF5.DdJBlH.St5efIaIaeZ6bax8DzTWRPf4K1dTvF/2YzAooXBmW', '0826611778', 'conghiale', b'1'),
(0x875a7eeef713490e9c5ce78d56bf69f0, '2002-10-28', 'fjcfilewin28102012@gmail.com', 'Male', 'Le Cong Nghia', '$2a$10$RBF5.DdJBlH.St5efIaIaeZ6bax8DzTWRPf4K1dTvF/2YzAooXBmW', '0826611778', 'admin', b'1'),
(0xe511ba61e12042d284eae8d96f56ad3e, '2002-10-28', 'conghiale@gmail.com', 'Male', 'Le Cong Nghia', '$2a$10$/WCgHGIvc.pqBkwGFt4RUOpJEj/PQIwgavPl9P4ZG7C4C15babJ1u', '0826611778', 'conghiale2', b'1'),
(0x6c46da6cc43b4f25874e85b3bfaa71e5, '2002-10-10', 'conghiale@gmail.com', 'Male', 'Le Cong Nghia', '$2a$10$W9b2D9RHAgoEAu6LzQ7asutIEpnhlr86.nf1tU.8xENoZEqQPOHwu', '0826611778', 'conghiale3', b'1'),
(0xc197445e1b8e45d3abb3fa29a72e1a05, NULL, 'hoangducduy@gmail.com', 'male', 'Hoàng Đức Duy', '$2a$10$pRxxazEnGEoVWaqPfmSwC.O9zHqihT9Hmylzs5KazeJWSnU/hYtGG', '1153346254', 'hducduy21', b'1');

-- --------------------------------------------------------

--
-- Table structure for table `user_products`
--

CREATE TABLE `user_products` (
  `cost` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `product_id` int(11) NOT NULL,
  `user_id` binary(16) NOT NULL,
  `date_choose` date DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_products`
--

INSERT INTO `user_products` (`cost`, `quantity`, `product_id`, `user_id`, `date_choose`) VALUES
(90000, 1, 39, 0x4b98023fa62a49fcaa5eb26255498995, '2023-04-30'),
(120000, 1, 38, 0x4b98023fa62a49fcaa5eb26255498995, '2023-04-30'),
(150000, 1, 40, 0x4b98023fa62a49fcaa5eb26255498995, '2023-04-30'),
(750000, 5, 40, 0xe511ba61e12042d284eae8d96f56ad3e, '2023-04-30'),
(378000, 6, 10, 0xe511ba61e12042d284eae8d96f56ad3e, '2023-04-30'),
(600000, 5, 37, 0xe511ba61e12042d284eae8d96f56ad3e, '2023-04-30');

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` binary(16) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(0x4b98023fa62a49fcaa5eb26255498995, 1),
(0x6c46da6cc43b4f25874e85b3bfaa71e5, 1),
(0x875a7eeef713490e9c5ce78d56bf69f0, 2),
(0xacb58926a3d14538883aabc6b75b93ce, 1),
(0xc197445e1b8e45d3abb3fa29a72e1a05, 1),
(0xe511ba61e12042d284eae8d96f56ad3e, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `authors`
--
ALTER TABLE `authors`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `detail_orders`
--
ALTER TABLE `detail_orders`
  ADD PRIMARY KEY (`order_id`,`product_id`),
  ADD KEY `FKkn49v2l6kkq7bj02w9opdeow2` (`product_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKy2kver9ldog29n3mi9b12w64` (`author_id`),
  ADD KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`);

--
-- Indexes for table `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKpl51cejpw4gy5swfar8br9ngi` (`product_id`),
  ADD KEY `FKcgy7qjc1r99dp117y9en6lxye` (`user_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_products`
--
ALTER TABLE `user_products`
  ADD PRIMARY KEY (`product_id`,`user_id`),
  ADD KEY `FK8qlfpqrybtn1q3yi1v0bnmun2` (`user_id`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `reviews`
--
ALTER TABLE `reviews`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
