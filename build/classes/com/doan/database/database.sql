-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: finalprojectoop
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `baocao`
--

DROP TABLE IF EXISTS `baocao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `baocao` (
  `MaBaoCao` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `NgayLap` date DEFAULT NULL,
  `TongDoanhThu` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `MaNV` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  PRIMARY KEY (`MaBaoCao`),
  KEY `fk_nv_bc` (`MaNV`),
  CONSTRAINT `fk_nv_bc` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baocao`
--

LOCK TABLES `baocao` WRITE;
/*!40000 ALTER TABLE `baocao` DISABLE KEYS */;
INSERT INTO `baocao` VALUES ('BC3','2025-03-29','586000.0','NV0'),('BC4','2025-04-03','0.0','NV0'),('BC5','2025-05-16','0.0','NV0');
/*!40000 ALTER TABLE `baocao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hangnhap`
--

DROP TABLE IF EXISTS `hangnhap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hangnhap` (
  `MaPhieuNhap` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `MaSP` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `SoLuong` int NOT NULL,
  `TongTien` decimal(10,2) NOT NULL,
  PRIMARY KEY (`MaPhieuNhap`,`MaSP`),
  KEY `hangnhap_ibfk_2` (`MaSP`),
  CONSTRAINT `hangnhap_ibfk_1` FOREIGN KEY (`MaPhieuNhap`) REFERENCES `phieunhap` (`MaPhieuNhap`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `hangnhap_ibfk_2` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hangnhap`
--

LOCK TABLES `hangnhap` WRITE;
/*!40000 ALTER TABLE `hangnhap` DISABLE KEYS */;
INSERT INTO `hangnhap` VALUES ('PN1','SP10',1,15000.00),('PN1','SP11',1,60000.00),('PN1','SP9',4,112000.00);
/*!40000 ALTER TABLE `hangnhap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hangxuat`
--

DROP TABLE IF EXISTS `hangxuat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hangxuat` (
  `MaHD` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `MaSP` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `SoLuongBan` int NOT NULL,
  `TongTien` decimal(10,2) NOT NULL,
  PRIMARY KEY (`MaHD`,`MaSP`),
  KEY `hangxuat_ibfk_2` (`MaSP`),
  CONSTRAINT `hangxuat_ibfk_1` FOREIGN KEY (`MaHD`) REFERENCES `hoadon` (`MaHD`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `hangxuat_ibfk_2` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hangxuat`
--

LOCK TABLES `hangxuat` WRITE;
/*!40000 ALTER TABLE `hangxuat` DISABLE KEYS */;
INSERT INTO `hangxuat` VALUES ('HD10','SP1',4,128000.00),('HD10','SP4',3,60000.00),('HD10','SP6',3,60000.00),('HD2','SP4',1,50000.00),('HD4','SP1',3,96000.00),('HD4','SP5',4,280000.00),('HD4','SP7',2,140000.00),('HD5','SP3',11,110000.00),('HD6','SP1',4,128000.00),('HD6','SP3',6,60000.00),('HD6','SP7',9,1080000.00),('HD7','SP1',5,160000.00),('HD7','SP3',5,50000.00),('HD7','SP7',2,240000.00),('HD8','SP1',1,32000.00),('HD8','SP5',3,210000.00),('HD9','SP3',5,50000.00),('HD9','SP7',5,600000.00);
/*!40000 ALTER TABLE `hangxuat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadon` (
  `MaHD` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `NgayLap` datetime NOT NULL,
  `TongTien` decimal(10,2) NOT NULL,
  `MaNV` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  PRIMARY KEY (`MaHD`),
  KEY `hoadon_ibfk_1` (`MaNV`),
  CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
INSERT INTO `hoadon` VALUES ('HD10','2025-04-17 16:43:27',248000.00,'NV0'),('HD2','2025-03-16 00:00:00',85000.00,'NV2'),('HD3','2025-03-17 00:00:00',132000.00,'NV3'),('HD4','2025-03-29 21:39:48',516000.00,'NV0'),('HD5','2025-03-29 23:23:52',110000.00,'NV0'),('HD6','2025-03-31 22:11:53',1268000.00,'NV0'),('HD7','2025-04-03 10:55:29',450000.00,'NV0'),('HD8','2025-04-17 15:33:48',242000.00,'NV0'),('HD9','2025-04-17 15:34:45',650000.00,'NV0');
/*!40000 ALTER TABLE `hoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loaisanpham`
--

DROP TABLE IF EXISTS `loaisanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loaisanpham` (
  `MaLoai` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `TenLoai` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`MaLoai`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaisanpham`
--

LOCK TABLES `loaisanpham` WRITE;
/*!40000 ALTER TABLE `loaisanpham` DISABLE KEYS */;
INSERT INTO `loaisanpham` VALUES ('ML1','Đồ Ăn'),('ML2','Đồ Uống'),('ML3','Đồ Gia Dụng');
/*!40000 ALTER TABLE `loaisanpham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhacungcap`
--

DROP TABLE IF EXISTS `nhacungcap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhacungcap` (
  `MaNCC` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `TenNCC` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `DiaChi` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `SDT` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `Email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `is_delete` bit(1) DEFAULT b'0',
  PRIMARY KEY (`MaNCC`),
  UNIQUE KEY `TenNCC_UNIQUE` (`TenNCC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhacungcap`
--

LOCK TABLES `nhacungcap` WRITE;
/*!40000 ALTER TABLE `nhacungcap` DISABLE KEYS */;
INSERT INTO `nhacungcap` VALUES ('NCC1','Công ty Thực phẩm QKA','12 Lý Thường Kiệt, Q10','02838211234','abcfoods@gmail.com',_binary '\0'),('NCC2','Công ty TNHH DEF','45 Nguyễn Đình Chiểu, Q3','02839112345','xyzmilk@yahoo.com',_binary '\0'),('NCC3','Công ty Thực phẩm JQK','78 Trần Hưng Đạo, Q5','02838220010','123drink@outlook.com',_binary '\0');
/*!40000 ALTER TABLE `nhacungcap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `MaNV` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `TenNV` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `Tuoi` int NOT NULL,
  `GioiTinh` tinyint(1) DEFAULT NULL,
  `DiaChi` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `SDT` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `Luong` decimal(10,2) NOT NULL,
  `is_delete` bit(1) DEFAULT b'0',
  PRIMARY KEY (`MaNV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES ('NV0','admin',18,1,'ha noi','0981234567',9999999.00,_binary '\0'),('NV1','Nguyễn Văn A',18,1,'Ba Đình - Hà Nội','0982315476',8000000.00,_binary '\0'),('NV2','Trần Thị Bình',25,0,'Hoàng Mai - Hà Nội','0931234568',5000000.00,_binary '\0'),('NV3','Lê Văn C',30,1,'789 Điện Biên Phủ, Q3','0912345678',8200000.00,_binary '\0'),('NV4','Đặng Văn E',32,1,'654 Nguyễn Văn Cừ, Q4','0934567890',8500000.00,_binary '\0');
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phieunhap`
--

DROP TABLE IF EXISTS `phieunhap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieunhap` (
  `MaPhieuNhap` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `NgayNhap` date NOT NULL,
  `TongTien` decimal(10,2) NOT NULL,
  `MaNV` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `MaNCC` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  PRIMARY KEY (`MaPhieuNhap`),
  KEY `phieunhap_ibfk_1` (`MaNV`),
  KEY `phieunhap_ibfk_2` (`MaNCC`),
  CONSTRAINT `phieunhap_ibfk_1` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `phieunhap_ibfk_2` FOREIGN KEY (`MaNCC`) REFERENCES `nhacungcap` (`MaNCC`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieunhap`
--

LOCK TABLES `phieunhap` WRITE;
/*!40000 ALTER TABLE `phieunhap` DISABLE KEYS */;
INSERT INTO `phieunhap` VALUES ('PN1','2025-05-19',187000.00,'NV0','NCC1');
/*!40000 ALTER TABLE `phieunhap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sanpham`
--

DROP TABLE IF EXISTS `sanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanpham` (
  `MaSP` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `TenSP` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `GiaNY` decimal(10,2) NOT NULL,
  `GiaNhap` int DEFAULT NULL,
  `SoLuong` int NOT NULL,
  `NSX` date NOT NULL,
  `HSD` date NOT NULL,
  `Loai` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `is_delete` bit(1) DEFAULT b'0',
  PRIMARY KEY (`MaSP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanpham`
--

LOCK TABLES `sanpham` WRITE;
/*!40000 ALTER TABLE `sanpham` DISABLE KEYS */;
INSERT INTO `sanpham` VALUES ('SP1','Sữa tươi Vinamilk 1L',32000.00,28000,66,'2025-03-01','2025-06-01','Đồ Uống',_binary '\0'),('SP10','Táo Mỹ',20000.00,15000,1,'2025-01-01','2026-01-01','Đồ ăn',_binary '\0'),('SP11','Thịt heo sạch 1kg',120000.00,60000,1,'2025-01-01','2026-01-01','Đồ ăn',_binary '\0'),('SP3','Nước khoáng Lavie 500ml',10000.00,8000,150,'2025-03-05','2025-09-05','Đồ uống',_binary '\0'),('SP4','Táo Mỹ',20000.00,15000,24,'2025-03-15','2025-03-30','Đồ ăn',_binary '\0'),('SP5','Thịt heo sạch 500g',70000.00,60000,55,'2025-03-16','2025-03-18','Đồ ăn',_binary '\0'),('SP6','Sữa TH True Milk',20000.00,18000,147,'2025-03-01','2025-06-01','Đồ ăn',_binary '\0'),('SP7','Thịt heo sạch 1kg',120000.00,60000,79,'2025-03-25','2025-06-25','Đồ ăn',_binary '\0'),('SP8','Cơm Cháy',25000.00,20000,3,'2025-03-26','2025-03-27','Đồ ăn',_binary '\0'),('SP9','Sữa tươi Vinamilk 1L',32000.00,28000,4,'2025-01-01','2026-01-01','Đồ Uống',_binary '\0');
/*!40000 ALTER TABLE `sanpham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spbanchay`
--

DROP TABLE IF EXISTS `spbanchay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spbanchay` (
  `MaBaoCao` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `MaSP` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `SoLuongBan` int NOT NULL,
  PRIMARY KEY (`MaBaoCao`,`MaSP`),
  KEY `fk_baocao_sanphambanchay2` (`MaSP`),
  CONSTRAINT `fk_baocao_sanphambanchay2` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_baocao_sanphanbanchay` FOREIGN KEY (`MaBaoCao`) REFERENCES `baocao` (`MaBaoCao`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spbanchay`
--

LOCK TABLES `spbanchay` WRITE;
/*!40000 ALTER TABLE `spbanchay` DISABLE KEYS */;
INSERT INTO `spbanchay` VALUES ('BC3','SP3',11),('BC4','SP3',17),('BC4','SP7',11),('BC5','SP3',17),('BC5','SP7',11);
/*!40000 ALTER TABLE `spbanchay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sphuhong`
--

DROP TABLE IF EXISTS `sphuhong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sphuhong` (
  `MaBaoCao` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `MaSP` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `SoLuong` int NOT NULL,
  `ThietHai` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`MaBaoCao`,`MaSP`),
  KEY `fk_baocao_sphuhong2` (`MaSP`),
  CONSTRAINT `fk_baocao_sphuhong` FOREIGN KEY (`MaBaoCao`) REFERENCES `baocao` (`MaBaoCao`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_baocao_sphuhong2` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sphuhong`
--

LOCK TABLES `sphuhong` WRITE;
/*!40000 ALTER TABLE `sphuhong` DISABLE KEYS */;
INSERT INTO `sphuhong` VALUES ('BC3','SP3',3,30000.00),('BC4','SP4',3,60000.00),('BC4','SP7',5,600000.00);
/*!40000 ALTER TABLE `sphuhong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taikhoan` (
  `MaTK` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `TenTK` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `role` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `status` int NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `MaNV` varchar(10) COLLATE utf8mb3_bin NOT NULL,
  PRIMARY KEY (`MaTK`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `password_UNIQUE` (`password`),
  KEY `fk_tk_nv` (`MaNV`),
  CONSTRAINT `fk_tk_nv` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

LOCK TABLES `taikhoan` WRITE;
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
INSERT INTO `taikhoan` VALUES ('TK0','admin','admin','admin','Admin',1,'admin@gmail.com','NV0'),('TK1','Nguyễn Văn A','nhanvien1','123','Manager',1,'nvanh@gmail.com','NV1'),('TK2','Trần Thị B','user1','pass123','Manager',1,'thibinh@yahoo.com','NV2'),('TK3','Lê Văn C','levanc','chovinh','Employee',1,'vanc@outlook.com','NV3'),('TK4','Đặng Văn E','dangvane','passxyz','Employee',1,'vane1@gmail.com','NV4');
/*!40000 ALTER TABLE `taikhoan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-26 21:47:24
