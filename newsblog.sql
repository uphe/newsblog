-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: newsblog
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `blog`
--

DROP TABLE IF EXISTS `blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog` (
  `blogId` int(11) NOT NULL AUTO_INCREMENT COMMENT '博客id',
  `title` varchar(255) NOT NULL COMMENT '博客标题',
  `article` text NOT NULL COMMENT '文章内容',
  `summary` varchar(255) NOT NULL COMMENT '文章摘要，正文前50字或全部正文',
  `likeCount` int(11) NOT NULL DEFAULT '0' COMMENT '博客点赞数',
  `commentCount` int(11) NOT NULL DEFAULT '0' COMMENT '博客评论数',
  `createDate` datetime NOT NULL COMMENT '博客创建时间',
  `userId` int(11) NOT NULL COMMENT '博客创建者',
  PRIMARY KEY (`blogId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `ticketId` int(11) NOT NULL AUTO_INCREMENT COMMENT '登陆票id',
  `randomTicket` varchar(255) NOT NULL COMMENT '随机串',
  `expired` datetime NOT NULL COMMENT '票过期时间',
  `userId` int(11) NOT NULL COMMENT '拥有该票的用户id',
  PRIMARY KEY (`ticketId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (1,'d01adb98c9c84c85968688ba8430056a','1970-01-01 00:10:05',1),(2,'dc94a4680297437ab7caa8273f89ba47','2020-03-15 03:12:14',1),(3,'e2ba8a94a8f2474bb63a6f74e62e4ead','2020-03-15 03:17:57',1),(4,'00d68d60f75b4cd9b6071a01ab151866','2020-03-22 07:33:25',1),(5,'fba8ece3bcbe4865a8646a09a908d3ed','2020-03-22 07:35:13',4),(6,'48cf1a39f2104531bc564a96fd418d32','2020-03-22 07:37:11',1),(7,'b0223799d6bd406eb7001501fe66a24d','2020-03-23 06:54:38',1),(8,'3ce54e8fc9154165b4374d897d8d6b23','2020-03-23 06:58:03',1);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  `salt` varchar(255) NOT NULL COMMENT '随机串，用于加密密码',
  `headUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户头像，是图片的地址',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'root','c7d4ff11a53a5a3ecde831017da293a8','hello','http://127.0.0.1:8080//getImage?fileName=0ad32e8b0b414a959707f57686b566a8.jpg'),(2,'aaa','ed89e4e626435b90911b15687d8954d8','096851','http://127.0.0.1:8080//getImage?fileName=0ad32e8b0b414a959707f57686b566a8.jpg'),(3,'bbb','6358105b78320d647d8a2e43943643ae','5d681b','http://127.0.0.1:8080//getImage?fileName=0ad32e8b0b414a959707f57686b566a8.jpg'),(4,'123','8358a5f4f53f8d786ba7bfd032d2a6cc','766bfc','http://127.0.0.1:8080//getImage?fileName=0ad32e8b0b414a959707f57686b566a8.jpg'),(5,'asd','49d6529f772131e2de7be93262e89972','dcc0d1','http://127.0.0.1:8080//getImage?fileName=0ad32e8b0b414a959707f57686b566a8.jpg'),(6,'dd!dd','a3f00cbccc9bd9fdb41b625544e1d57a','b7d61d','http://127.0.0.1:8080//getImage?fileName=0ad32e8b0b414a959707f57686b566a8.jpg'),(7,'rootff','b425e2dfa4b9e838f70c63fa768d8d07','3f35b6','http://127.0.0.1:8080//getImage?fileName=0ad32e8b0b414a959707f57686b566a8.jpg');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-17 10:22:34
