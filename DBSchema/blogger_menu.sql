-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: blogger
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.10-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `menu_id` varchar(5) CHARACTER SET latin1 NOT NULL,
  `menu_name` varchar(50) NOT NULL,
  `menu_command` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `menu_head` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `sort_order` int(11) DEFAULT NULL,
  `security_code` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `status` char(1) CHARACTER SET latin1 DEFAULT NULL,
  `create_id` varchar(8) CHARACTER SET latin1 NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES ('1','View Category','/blogger/admin/category','5',10,'user, legco,ROLE_ADMIN','A','johnnyt','2012-04-21 17:08:01'),('6','系統管理','',NULL,20,'user, legco,ROLE_ADMIN,central','A','johnnyt','2012-04-22 12:39:04'),('5','Category','',NULL,10,'user, legco,ROLE_ADMIN','A','johnnyt','2012-04-22 12:39:04'),('7','更改密碼','UserManagementAction','6',10,'user, legco,ROLE_ADMIN,central','A','johnnyt','2012-04-22 12:39:04'),('12','新增登入戶口','RegisterAction','6',20,'ROLE_ADMIN,central','A','johnnyt','2013-07-20 00:00:00'),('14','View Article','/blogger/admin/article','13',30,'user, legco,ROLE_ADMIN','A','johnnyt','2013-07-20 00:00:00'),('13','Article',NULL,NULL,30,'user, legco,ROLE_ADMIN','A','johnnyt','2013-07-20 00:00:00');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-13 23:27:05
