CREATE DATABASE  IF NOT EXISTS `blogger` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `blogger`;
-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: blogger
-- ------------------------------------------------------
-- Server version	5.6.20

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
INSERT INTO `menu` VALUES ('1','View Category','category','5',10,'user, legco,ROLE_ADMIN','A','johnnyt','2012-04-21 17:08:01'),('2','上載會員資料','ImportMemberInfoAction','5',30,'user, legco,ROLE_ADMIN','A','johnnyt','2012-04-21 17:08:01'),('6','系統管理','',NULL,20,'user, legco,ROLE_ADMIN,central','A','johnnyt','2012-04-22 12:39:04'),('4','搜查會員資料','SearchMemberInfoAction','5',20,'user, legco,ROLE_ADMIN','A','johnnyt','2012-04-21 17:08:01'),('5','Category','',NULL,10,'user, legco,ROLE_ADMIN','A','johnnyt','2012-04-22 12:39:04'),('7','更改密碼','UserManagementAction','6',10,'user, legco,ROLE_ADMIN,central','A','johnnyt','2012-04-22 12:39:04'),('8','匯入會員備註','BulkRemarkUpdateAction','5',40,'user,ROLE_ADMIN','I','johnnyt','2012-04-22 12:39:04'),('9','黨員資料','','',30,'ROLE_ADMIN','A','johnnyt','2013-06-14 00:00:00'),('10','輸入黨員資料','PartyInputMemberInfoAction','9',10,'ROLE_ADMIN','A','johnnyt','2013-06-14 00:00:00'),('11','搜查黨員資料','PartySearchMemberInfoAction','9',20,'ROLE_ADMIN','A','johnnyt','2013-06-24 00:00:00'),('12','新增登入戶口','RegisterAction','6',20,'ROLE_ADMIN,central','A','johnnyt','2013-07-20 00:00:00'),('13','活動資料',NULL,NULL,40,'user,ROLE_ADMIN,central,legco','A','johnnyt','2013-08-05 00:00:00'),('14','輸入社區主任每月工作紀錄表','CommunityOfficerRecordAction','13',10,'user,ROLE_ADMIN,central,legco','A','johnnyt','2013-08-05 00:00:00'),('15','新增活動','EventRegistrationAction','13',50,'user,ROLE_ADMIN,central,legco','I','johnnyt','2013-08-05 00:00:00'),('16','搜查社區主任每月工作紀錄表','SearchCommunityOfficerRecordAction','13',20,'ROLE_ADMIN,central','A','johnnyt','2013-08-05 00:00:00'),('17','匯入社區主任每月工作紀錄表','ImportCommunityOfficerRecordAction','13',30,'user,ROLE_ADMIN,central,legco','I','johnnyt','2013-08-05 00:00:00'),('18','檢視社區主任每月工作紀錄表','ViewCommunityOfficerRecordAction','13',40,'user,ROLE_ADMIN,legco','A','johnnyt','2013-08-05 00:00:00'),('19','遊行示威紀錄',NULL,NULL,60,'user,ROLE_ADMIN,central,legco,photo','A','johnnyt','2013-08-05 00:00:00'),('20','輸入遊行示威紀錄','ProtestRecordAction','19',10,'ROLE_ADMIN,central','A','johnnyt','2013-08-05 00:00:00'),('21','檢視遊行示威紀錄','ViewProtestRecordAction','19',20,'user,ROLE_ADMIN,central,legco,photo','A','johnnyt','2013-08-05 00:00:00'),('22','匯入會員活動資料','ImportMemberRemarkAction','5',50,'user, legco,ROLE_ADMIN','A','johnnyt','2013-08-05 00:00:00'),('23','新增SMS群組','InputSMSGroupInfoAction','5',60,'user, legco,ROLE_ADMIN','A','johnnyt','2013-08-05 00:00:00'),('24','檢視SMS群組','SearchSMSGroupInfoAction','5',70,'user, legco,ROLE_ADMIN','A','johnnyt','2013-08-05 00:00:00'),('30','資助資料',NULL,NULL,70,'user,ROLE_ADMIN,central,legco','A','johnnyt','2013-08-05 00:00:00'),('31','輸入資助項目','InputSubsidyCriteriaAction','30',10,'ROLE_ADMIN,central','A','johnnyt','2013-08-05 00:00:00'),('32','檢視資助結餘','SearchSubsidyBalanceAction','30',20,'user,ROLE_ADMIN,central,legco','A','johnnyt','2013-08-05 00:00:00'),('33','檢視資助項目','SearchSubsidyInfoAction','30',30,'ROLE_ADMIN,central','A','johnnyt','2013-08-05 00:00:00');
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

-- Dump completed on 2016-01-01 11:17:54
