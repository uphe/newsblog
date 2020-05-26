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
  `hitCount` int(11) NOT NULL DEFAULT '0' COMMENT '博客点击量',
  `commentCount` int(11) NOT NULL DEFAULT '0' COMMENT '博客评论数',
  `createDate` datetime NOT NULL COMMENT '博客创建时间',
  `userId` int(11) NOT NULL COMMENT '博客创建者',
  `typeString` varchar(50) DEFAULT NULL COMMENT '博客所属类型',
  `labelString` varchar(50) DEFAULT NULL COMMENT '博客所属标签',
  PRIMARY KEY (`blogId`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` VALUES (1,'first','hello world','摘要',0,0,1,'2020-03-18 04:29:42',1,NULL,NULL),(2,'哈哈','你好呀','摘要',0,0,1,'2020-03-18 04:33:37',1,NULL,NULL),(3,'房东是','范德萨发','摘要',0,0,1,'2020-03-18 04:34:37',1,NULL,NULL),(4,'房东是','范德萨发','摘要',0,0,0,'2020-03-18 04:35:11',1,NULL,NULL),(5,'房东是','发士大夫但是','摘要',0,0,5,'2020-03-18 04:37:36',1,NULL,NULL),(6,'广泛大使馆','后果脚后跟','摘要',0,0,18,'2020-03-18 04:38:48',1,NULL,NULL),(7,'广泛大使馆','后果脚后跟','摘要',0,0,0,'2020-03-18 04:39:12',1,NULL,NULL),(8,'广泛大使馆广泛大概','后果脚后跟给对方鼠标 ','摘要',0,0,0,'2020-03-18 04:39:19',1,NULL,NULL),(9,'广泛大使馆的','过的法国','摘要',0,0,0,'2020-03-18 06:23:44',1,NULL,NULL),(10,'广东省','广泛的','摘要',0,0,0,'2020-03-18 06:25:57',1,NULL,NULL),(11,'广泛的','广东佛山','摘要',0,0,0,'2020-03-18 06:28:12',1,NULL,NULL),(12,'广东佛山','广泛的','摘要',0,0,0,'2020-03-18 06:28:37',1,NULL,NULL),(13,'广泛的','广东佛山','摘要',0,0,0,'2020-03-18 06:29:47',1,NULL,NULL),(14,'房东是','发士大夫','摘要',0,0,0,'2020-03-18 06:46:25',1,NULL,NULL),(15,'发生的','富士达','摘要',0,0,0,'2020-03-18 06:47:03',1,NULL,NULL),(16,'大家好','欢迎来到王者荣耀','摘要',0,0,0,'2020-03-18 08:21:38',1,NULL,NULL),(17,'你好','abcdefg','摘要',0,0,0,'2020-03-18 08:22:30',1,NULL,NULL),(18,'的萨芬还是抠脚大汉','飞洒发的房东是发生','摘要',0,0,4,'2020-03-18 08:24:49',1,NULL,NULL),(19,'retetertretr','fdsfdsfgd','摘要',0,0,1,'2020-03-18 08:27:53',1,NULL,NULL),(20,'1111111111','111111111111111111','111111111111111111',0,0,3,'2020-03-18 08:34:03',1,NULL,NULL),(21,'22222222222','222222222222222222','222222222222222222',0,0,2,'2020-03-18 08:40:28',1,NULL,NULL),(22,'啦啦啦','0：成功 1：失败\n\n1：成功 0：失败\n\n不知道你们是怎么想的，我最初想的是boolean值，1为真，0为假，所以想当然的认为1表示成功，0表示失败\n\n在项目开发过过程中，我们可能会碰到用户登录的问题，正常、注销、拉黑，锁定等情况\n\n0：正常 1：注销 2：拉黑 3：锁定\n\n0：注销 1：正常 2：拉黑 3：锁定\n\n这样一看，还是0表示成功香，后来的我才知道，大部分人都是用0表示成功的，大于0都是非成功，多好','0：成功 1：失败\n\n1：成功 0：失败\n\n不知道你们是怎么想的，我最初想的是boolean值，1为',0,0,1,'2020-03-18 08:41:36',1,NULL,NULL),(23,'hello ','你好','你好',0,1,0,'2020-03-20 01:54:31',15,NULL,NULL),(24,'粗体测试','**这里是粗体**','**这里是粗体**',0,0,1,'2020-03-23 08:39:28',1,NULL,NULL),(25,'111','lalala','lalala',0,0,0,'2020-03-24 13:16:37',16,NULL,NULL),(26,'广泛的','广泛的','广泛的',0,0,0,'2020-03-26 06:37:50',16,NULL,NULL),(27,'大家好','真的好','真的好',0,1,0,'2020-03-26 07:23:00',16,NULL,NULL),(28,'00000000000','000000000000000','000000000000000',0,0,0,'2020-03-26 07:23:56',16,NULL,NULL),(29,'范德萨发','房东是','房东是',0,1,3,'2020-03-26 07:24:34',16,NULL,NULL),(30,'房东是','范德萨','范德萨',0,1,0,'2020-03-26 07:29:02',16,NULL,NULL),(31,'范德萨','发生的\n![image](E:/迅雷下载/测试头像.jpg)','发生的\n![image](E:/迅雷下载/测试头像.jpg)',0,1,4,'2020-03-27 04:15:21',4,NULL,NULL),(32,'hgfh','![image](E:/迅雷下载/测试头像.jpg)','![image](E:/迅雷下载/测试头像.jpg)',0,0,2,'2020-03-27 04:22:17',4,NULL,NULL),(33,'第三方','aaaaaaaaa\n![](http://127.0.0.1:8080/getImage?fileName=e4ab6b55e95e4b86901591d393608195.jpg)','aaaaaaaaa\n![](http://127.0.0.1:8080/getImage?fileN',0,2,1,'2020-04-16 08:24:50',17,NULL,NULL),(34,'Linux中用户与组群管理','飞洒地方 \n 富士达\n ![](http://127.0.0.1:8080/getImage?fileName=5151e096ec4f4ba4a65be58e228f6606.jpg)','飞洒地方 \n 富士达\n ![](http://127.0.0.1:8080/getImage?fil',1,0,0,'2020-04-16 09:09:15',17,NULL,NULL),(35,'SpringBoot推荐的模板引擎thymeleaf','你好\n![](http://127.0.0.1:8080/getImage?fileName=3862aba761004e5bbac3afba228dcbc9.jpg)','你好\n![](http://127.0.0.1:8080/getImage?fileName=386',1,6,1,'2020-04-16 09:13:12',17,NULL,NULL),(36,'范德萨','范德萨','范德萨',3,45,3,'2020-04-17 00:19:49',17,NULL,NULL),(37,'广泛的','广泛的','广泛的',4,59,7,'2020-04-17 00:35:20',17,NULL,NULL),(38,'Linux中用户与组群管理','fdsaf','fdsaf',0,2,0,'2020-04-21 05:03:39',16,'0',NULL),(39,'111','111','111',0,1,0,'2020-04-21 05:05:16',16,'1',NULL),(40,'xxx','xxx','xxx',0,0,0,'2020-04-21 05:07:04',16,'1',NULL),(41,'ddd','ddd','ddd',0,1,0,'2020-05-05 04:26:46',16,'',''),(42,'她她她她她她她她她','广泛大使馆','广泛大使馆',2,14,6,'2020-05-08 11:33:00',16,'',''),(43,'gfdg','gfdg','gfdg',0,0,0,'2020-05-23 02:12:52',16,'macos','JavaScript'),(44,'gfds ','gfdsg','gfdsg',1,4,2,'2020-05-23 02:13:18',16,'eclipse,intellij idea','HTML,jquery');
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `content` text NOT NULL COMMENT '评论内容',
  `createDate` datetime NOT NULL COMMENT '评论时间',
  `blogId` int(11) NOT NULL COMMENT '博客id',
  `userId` int(11) NOT NULL COMMENT '用户id',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除，默认为0，没删',
  `parentId` int(11) DEFAULT NULL COMMENT '指向父评论的id，如果不是对评论的回复则为null',
  PRIMARY KEY (`commentId`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'hello','2020-03-31 02:43:02',1,1,0,NULL),(2,'广泛的','2020-03-31 07:14:02',6,4,0,NULL),(3,'符合规范','2020-03-31 07:27:03',6,4,0,NULL),(4,'广泛的是个','2020-03-31 07:27:48',6,4,0,NULL),(5,'供奉的是','2020-03-31 07:37:18',6,4,0,NULL),(6,'广泛的','2020-03-31 07:39:23',6,4,0,NULL),(7,'广泛的','2020-03-31 07:39:30',3,4,0,NULL),(8,'高浮雕高浮雕','2020-03-31 08:03:18',6,4,0,NULL),(9,'广泛的','2020-03-31 08:04:57',6,4,0,NULL),(10,'广泛的','2020-03-31 08:04:59',6,4,0,NULL),(11,'1111111111111111111111111111111','2020-03-31 08:05:15',6,4,0,NULL),(12,'5555555555555555555555','2020-03-31 08:05:59',6,4,0,NULL),(13,'你官方电话','2020-03-31 08:07:46',6,4,0,NULL),(14,'666666666666666','2020-03-31 08:09:47',6,4,0,NULL),(15,'0000000000000000000','2020-03-31 08:10:32',6,4,0,NULL),(16,'？？？？？？？？？？？？？','2020-03-31 08:12:26',6,4,0,NULL),(17,'？？？？？？？？？？？？？','2020-03-31 08:12:37',6,4,0,NULL),(18,'股份大股东是','2020-03-31 08:13:17',6,4,0,NULL),(19,'···················','2020-03-31 08:13:59',6,4,0,NULL),(20,'官方的说法的高浮雕高浮雕','2020-03-31 08:14:07',6,4,0,NULL),(21,'的高富帅','2020-03-31 08:14:19',5,4,0,NULL),(22,'广泛的','2020-03-31 08:14:34',5,4,0,NULL),(23,'华国锋','2020-03-31 08:15:22',5,4,0,NULL),(24,'华国锋','2020-03-31 08:15:30',5,4,0,NULL),(25,'444433333','2020-03-31 08:16:28',5,4,0,NULL),(26,'广泛的','2020-03-31 08:23:12',20,4,0,NULL),(27,'555','2020-03-31 08:25:14',20,4,0,NULL),(28,'777','2020-03-31 08:27:40',20,4,0,NULL),(29,'广泛大概','2020-03-31 08:28:14',21,4,0,NULL),(30,'888','2020-03-31 08:29:00',21,4,0,NULL),(31,'范德萨','2020-03-31 08:30:02',18,4,0,NULL),(32,'风格的','2020-03-31 08:32:46',2,4,0,NULL),(33,'范德萨','2020-03-31 08:33:59',19,4,0,NULL),(34,'66666666666','2020-03-31 08:35:03',18,4,0,NULL),(35,'55555555555','2020-03-31 08:35:11',18,4,0,NULL),(36,'股份的公司','2020-03-31 08:35:32',18,4,0,NULL),(37,'法国大使馆','2020-03-31 08:36:01',22,4,0,NULL),(38,'范德萨','2020-03-31 08:38:02',29,4,0,NULL),(39,'供奉的是','2020-03-31 08:38:06',29,4,0,NULL),(40,'广泛的','2020-03-31 08:38:10',29,4,0,NULL),(41,'hello','2020-04-02 06:00:03',24,4,0,NULL),(42,'风格和','2020-04-06 06:41:32',31,4,0,NULL),(43,'hzy第一次','2020-04-06 07:29:32',32,16,0,NULL),(44,'hzy第二次','2020-04-06 07:29:38',32,16,0,NULL),(45,'[code=java]\nhello\n[/code]','2020-04-15 05:21:31',31,4,0,NULL),(46,'jbvn','2020-04-15 07:43:25',31,17,0,NULL),(47,'hello','2020-04-15 07:43:35',31,17,0,NULL),(48,'广泛的','2020-04-16 09:02:19',33,17,0,NULL),(49,'发多少个','2020-04-17 00:35:28',37,17,0,NULL),(50,'fdg','2020-04-19 00:55:05',37,1,0,NULL),(51,'dfd','2020-04-19 00:55:12',37,1,0,NULL),(52,'','2020-04-19 00:55:52',36,1,0,NULL),(53,'gdf','2020-04-19 00:55:57',36,1,0,NULL),(54,'bv','2020-04-19 00:58:19',37,1,0,NULL),(55,'hgjghj ','2020-04-19 01:15:18',35,1,0,NULL),(56,'范德萨','2020-04-19 07:15:31',37,1,0,NULL),(57,'范德萨','2020-04-19 07:15:45',36,1,0,NULL),(58,'范德萨','2020-04-19 07:16:02',37,17,0,NULL),(59,'范德萨','2020-04-19 07:16:18',37,16,0,NULL),(60,'的跌幅高达','2020-05-08 11:33:08',42,16,0,NULL),(61,'111','2020-05-22 09:25:44',42,16,0,NULL),(62,'华国锋','2020-05-22 09:30:05',42,16,0,NULL),(63,'666','2020-05-22 09:30:08',42,16,0,NULL),(64,'2452','2020-05-22 09:33:04',42,16,0,NULL),(65,'tret','2020-05-22 09:33:07',42,16,0,NULL),(66,'hjgh','2020-05-23 02:16:01',44,17,0,NULL),(67,'k\'k\'k','2020-05-23 07:20:56',44,16,0,NULL);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `messageId` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息id',
  `messageFromId` int(11) NOT NULL COMMENT '消息发送方id',
  `messageToId` int(11) NOT NULL COMMENT '消息接收方id',
  `messageContent` text NOT NULL COMMENT '消息内容',
  ` messageCreateDate` date NOT NULL COMMENT '消息发送的时间',
  ` messageHasRead` int(11) NOT NULL COMMENT '消息是否已经读过',
  ` messageConversationId` varchar(255) NOT NULL COMMENT '会话id',
  PRIMARY KEY (`messageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `noticeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '公告id',
  `noticeTitle` varchar(255) NOT NULL COMMENT '公告标题',
  `noticeContent` varchar(255) NOT NULL COMMENT ' 公告内容',
  `operatorId` int(11) NOT NULL COMMENT '发布人id',
  `publishTime` datetime NOT NULL COMMENT '发布时间',
  `expirationTime` datetime NOT NULL COMMENT '过期时间',
  PRIMARY KEY (`noticeId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (1,'test','test',1,'2020-05-09 15:43:39','2020-05-09 15:43:39'),(2,'test1','test1',1,'2020-05-09 15:44:05','2020-05-09 15:44:05'),(3,'test2','test2',1,'2020-05-09 15:44:12','2020-05-09 15:44:12'),(4,'hello','my first notice',1,'2020-05-13 02:34:26','2020-05-20 02:34:26'),(5,'hello','my first notice...',1,'2020-05-13 03:37:47','2020-05-20 03:37:47'),(6,'111','111',1,'2020-05-15 08:40:21','2020-05-22 08:40:21'),(7,'晓雨','你好',1,'2020-05-15 09:34:36','2020-05-22 09:34:36'),(8,'啊啊啊','啊啊啊',1,'2020-05-23 06:39:36','2020-05-30 06:39:36'),(9,'哈哈哈哈哈哈哈哈','哈哈哈哈哈哈',1,'2020-05-23 07:33:41','2020-05-30 07:33:41'),(10,'各干各','各干各',1,'2020-05-23 07:38:03','2020-05-30 07:38:03');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `readnotice`
--

DROP TABLE IF EXISTS `readnotice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `readnotice` (
  `readNoticeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '已读id',
  `noticeId` int(11) NOT NULL COMMENT '公告id',
  `userId` int(11) NOT NULL COMMENT '用户id',
  `expirationTime` datetime NOT NULL COMMENT '已读公告过期时间',
  PRIMARY KEY (`readNoticeId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `readnotice`
--

LOCK TABLES `readnotice` WRITE;
/*!40000 ALTER TABLE `readnotice` DISABLE KEYS */;
INSERT INTO `readnotice` VALUES (1,1,1,'2020-05-13 11:31:47'),(2,4,1,'2020-05-20 03:34:34'),(3,4,1,'2020-05-20 03:38:36'),(4,5,1,'2020-05-20 03:40:15'),(5,0,17,'2020-05-22 09:29:22'),(6,0,17,'2020-05-22 09:29:41'),(7,0,17,'2020-05-22 09:30:54'),(8,0,17,'2020-05-22 09:31:28'),(9,6,17,'2020-05-22 09:32:35'),(10,5,17,'2020-05-22 09:33:10'),(11,6,4,'2020-05-22 09:34:17'),(12,5,4,'2020-05-22 09:34:18'),(13,7,4,'2020-05-22 09:34:49'),(14,8,17,'2020-05-30 06:40:16');
/*!40000 ALTER TABLE `readnotice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `remind`
--

DROP TABLE IF EXISTS `remind`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `remind` (
  `remindId` int(11) NOT NULL AUTO_INCREMENT,
  `remindContent` varchar(255) NOT NULL COMMENT '提醒内容',
  `fromId` int(11) NOT NULL COMMENT '发起提醒的人',
  `toId` int(11) NOT NULL COMMENT '接收提醒的人',
  `blogId` int(11) NOT NULL COMMENT '文章id',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `state` int(11) NOT NULL COMMENT '0表示未读，1表示已读，2表示删除',
  `remindType` int(11) NOT NULL COMMENT '0表示点赞，1表示评论',
  PRIMARY KEY (`remindId`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `remind`
--

LOCK TABLES `remind` WRITE;
/*!40000 ALTER TABLE `remind` DISABLE KEYS */;
INSERT INTO `remind` VALUES (1,'hello',1,2,1,'2020-05-15 10:25:49',1,0),(2,'hello',1,2,1,'2020-05-15 10:25:49',1,0),(3,'hello',1,2,1,'2020-05-15 10:25:49',1,0),(4,'hello',1,2,1,'2020-05-15 02:27:59',1,0),(5,'hello',1,2,1,'2020-05-15 02:28:35',1,0),(6,'hello',1,2,1,'2020-05-15 02:29:37',1,0),(7,'111点赞了她她她她她她她她她',17,16,42,'2020-05-22 08:45:42',2,0),(8,'111点赞了她她她她她她她她她',17,16,42,'2020-05-22 08:46:47',1,0),(9,'hzy点赞了她她她她她她她她她',16,16,42,'2020-05-22 09:10:53',2,0),(10,'hzy点赞了她她她她她她她她她',16,16,42,'2020-05-22 09:13:49',2,0),(11,'hzy评论了她她她她她她她她她',16,16,42,'2020-05-22 09:25:44',1,1),(12,'hzy点赞了她她她她她她她她她',16,16,42,'2020-05-22 09:30:00',1,0),(13,'hzy评论了她她她她她她她她她',16,16,42,'2020-05-22 09:30:05',1,1),(14,'hzy评论了她她她她她她她她她',16,16,42,'2020-05-22 09:30:08',1,1),(15,'hzy评论了她她她她她她她她她:2452',16,16,42,'2020-05-22 09:33:04',1,1),(16,'hzy评论了她她她她她她她她她:tret',16,16,42,'2020-05-22 09:33:07',1,1),(17,'111点赞了gfds ',17,16,44,'2020-05-23 02:15:42',2,0),(18,'111点赞了gfds ',17,16,44,'2020-05-23 02:15:57',2,0),(19,'111评论了gfds :hjgh',17,16,44,'2020-05-23 02:16:01',1,1),(20,'111点赞了gfds ',17,16,44,'2020-05-23 02:16:36',2,0),(21,'root点赞了gfds ',1,16,44,'2020-05-23 07:20:36',0,0),(22,'hzy评论了gfds :k\'k\'k',16,16,44,'2020-05-23 07:20:56',1,1);
/*!40000 ALTER TABLE `remind` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (1,'d01adb98c9c84c85968688ba8430056a','1970-01-01 00:10:05',1),(2,'dc94a4680297437ab7caa8273f89ba47','2020-03-15 03:12:14',1),(3,'e2ba8a94a8f2474bb63a6f74e62e4ead','2020-03-15 03:17:57',1),(4,'00d68d60f75b4cd9b6071a01ab151866','2020-03-22 07:33:25',1),(5,'fba8ece3bcbe4865a8646a09a908d3ed','2020-03-22 07:35:13',4),(6,'48cf1a39f2104531bc564a96fd418d32','2020-03-22 07:37:11',1),(7,'b0223799d6bd406eb7001501fe66a24d','2020-03-23 06:54:38',1),(8,'3ce54e8fc9154165b4374d897d8d6b23','2020-03-23 06:58:03',1),(9,'0af75a33f972463ea159b56fcb325077','2020-03-25 04:23:32',1),(10,'842e9b1ef6bd498e85c5857dc41cd610','2020-03-19 04:33:27',1),(11,'cb13be7ff0fc42e0b6bcbe7265dc5e9d','2020-03-19 06:46:16',1),(12,'419d5ebf54b9451d9074182c59c5d4b5','2020-03-19 06:46:56',1),(13,'59fec24fc90d419482da3ab7c80a4d57','2020-03-19 08:31:38',1),(14,'586b3afb4d764df89eac3afe3614955b','2020-03-19 08:40:12',1),(15,'cd640dc98dd84083a5dac9c555b639db','2020-03-19 08:46:04',1),(16,'4b75439272144266bd85174f4700321b','2020-03-19 06:34:30',1),(17,'fb098842dfdf4e5c9898ef34c0bfe930','2020-03-19 06:37:04',1),(18,'7eeb9a7fcc97469997cd49cf27ef3ec8','2020-03-19 06:37:56',1),(19,'f61bc044df3c4bb9aa37e07035fb556e','2020-03-19 06:38:52',1),(20,'1f951ec35718432f81d0b157fb17984c','2020-03-19 06:40:07',1),(21,'2fb8048de6f64203bbeeaea7d24d6720','2020-03-19 06:40:31',1),(22,'4bb081e3d45c4e9786205cba513b5f4d','2020-03-19 06:44:15',1),(23,'b55dbe68b1234e178b9ef5496d90dea3','2020-03-19 06:44:40',1),(24,'e8431cd7bf354c309256216f5fd20be6','2020-03-19 06:45:40',1),(25,'5964541ce33c4526a78105824003339b','2020-03-19 06:47:01',1),(26,'02a8202dc3cc49538d47d732b9cb4fbd','2020-03-19 06:48:09',1),(27,'14f5374d7547491791f332161b877fc8','2020-03-19 06:49:47',1),(28,'19e1cf08618a486d8d5ea0a37a1cfa2f','2020-03-19 06:52:23',1),(29,'1637a7ea242a4902a9c88203b6f92069','2020-03-19 06:59:14',1),(30,'5f994470af2942bc9ea6ef938adce634','2020-03-19 07:01:20',1),(31,'b2d937960ff24d568c056623216ae5f4','2020-03-19 07:01:56',1),(32,'ebe43fa6831549c788958adeb27b4fec','2020-03-19 07:02:17',1),(33,'0ca55186627d426480ec1a813e33f6fe','2020-03-19 07:03:08',1),(34,'7cd9407305d44e79bd43698135302725','2020-03-19 07:07:56',1),(35,'36f31a6017f549ab8dbf7095d8408139','2020-03-19 07:08:25',1),(36,'86551416cd30467f9ca238e6a9294c7f','2020-03-19 07:09:11',1),(37,'7e4f8f5b61ee457d93f3d6f260dbfa68','2020-03-19 07:10:42',1),(38,'e804fb0eeca340208c858870c9de916d','2020-03-19 07:10:51',1),(39,'51f5cd5cd07c4532a5b2e975193ca2bc','2020-03-19 07:48:11',1),(40,'e312a3fd24514828a635f4cd1a99bff3','2020-03-19 07:50:54',1),(41,'c7bd91f9a2f84fcf87f1b8e1e68ef772','2020-03-19 07:52:21',1),(42,'0985948adf054dc1ab535b6eebbb4521','2020-03-19 07:54:21',1),(43,'dab517994d3d4ec5b3762fdab17de297','2020-03-19 08:02:38',1),(44,'94f6524b597a4cf88a7c69c6b9b53b31','2020-03-19 08:02:45',1),(45,'41a9ac1df4dc4f6090b06369b0244867','2020-03-19 08:02:52',1),(46,'2d50b0a6c4e842498a06c1a5e1ddd239','2020-03-20 00:07:31',10),(47,'e20188c487f74fd9a93d2bf2ec8384cb','2020-03-20 00:17:47',11),(48,'11a2220d30df4146ae867902b94dbad1','2020-03-20 00:18:11',12),(49,'8cbdeadd5547414ab7f79d0458b9f332','2020-03-20 00:19:17',13),(50,'94eab8d0b9d14ed7a09551744604573d','2020-03-20 00:24:57',14),(51,'f2e99c4d23cc4765a74d4110c936bbef','2020-03-20 01:54:36',15),(52,'e2f8d9c405474de69983f83a0918639c','2020-03-22 04:56:25',1),(53,'2a9a5f02ce454a56bc2bce9779c7ee09','2020-03-24 06:49:35',1),(54,'472b644f68934147be33bf2f4f4b1922','2020-03-24 08:38:36',1),(55,'b1e3b1bd86b54c1e82a9ee36d4fe2e22','2020-03-25 02:09:57',1),(56,'95229fee26344c4892712c6438ac851a','2020-03-25 03:35:00',1),(57,'7bef23ec863145948712650ded2b71a7','2020-03-24 13:12:39',1),(58,'058ef0b800134dc39445f5fa7a351818','2020-03-24 13:15:58',4),(59,'2d76b592eef04b84aa2f32a1e486235f','2020-03-25 13:16:20',16),(60,'6c3fb40166d14f7c818dda4a78e21cb7','2020-03-27 06:25:01',16),(61,'d9ed847a305b4e43b9167a7a4a4ee809','2020-03-28 04:09:32',4),(62,'30ec1bb79f674d948213bfd76b5b9800','2020-03-31 03:12:20',4),(63,'761add01e1ed4161a89e2d916fe9a8e8','2020-04-01 04:02:42',4),(64,'c0c5e31426584eca8e38d6cc9e8417db','2020-04-01 04:58:57',4),(65,'2287727ab0dd4662a57f50718137f2b9','2020-04-01 05:51:16',4),(66,'9fc8fa53fa2d4095ae91005db095a0be','2020-04-01 05:57:14',4),(67,'9f46a4cad029400290632c27e19c9f4d','2020-04-01 06:00:51',4),(68,'5e5e74b22b934fb48eb0682b3cb05edf','2020-04-01 06:11:14',4),(69,'f32dba2b91d34ef3b8dae161b7e0b322','2020-04-01 06:11:41',4),(70,'4956a9c4e6f6404bb1d57c486f16e49c','2020-04-01 06:16:13',4),(71,'cfcc8aca437c4897a19f4245dac168bd','2020-04-07 06:17:47',4),(72,'3195fa4414384c2eac6ce7bf89e0f621','2020-04-07 06:22:38',4),(73,'feed74389098466590c6bc49106551bd','2020-04-01 06:24:10',4),(74,'88ca7ff8f0af4aa295a2583d5fdcda52','2020-04-01 06:25:27',4),(75,'0f5318fcc83846c38d67f51f1f0817bd','2020-04-01 06:29:09',4),(76,'faee7ce9e4444dc0b82ea6aabe532c83','2020-04-01 06:34:28',4),(77,'40f0b4b1e6ab41bfb5ae688c3cffad1a','2020-04-01 06:36:48',4),(78,'2fd5aea7a961461f8c258f8d807b7597','2020-04-03 05:59:40',4),(79,'d4597960a9b841b8904eaef39c055cbd','2020-04-06 06:41:42',4),(80,'7150c6699d6b4fdaabd83567285adbc9','2020-04-06 07:01:47',4),(81,'9807d0cd14ed4188940574f5c5e02b9a','2020-04-06 07:02:08',16),(82,'f8f12314275e4518a887cd922a8d31d1','2020-04-06 07:02:23',1),(83,'f788bbff011e4211838fede9035dfd09','2020-04-06 07:28:06',4),(84,'56f0b1a9ec0d4ae4b7f614a4a42ac7bf','2020-04-06 07:29:42',16),(85,'03f386dbda474386aad0aba6e2c60b7f','2020-04-07 07:29:47',4),(86,'d71ec2b6a8704d1abb8e6bf284840c6d','2020-04-15 04:00:02',4),(87,'3c20381642824111afdb9647c82d146f','2020-04-16 05:21:12',4),(88,'1b9c0e552f164c4d849e4b1965b38253','2020-04-16 06:25:19',4),(89,'3dff9cb34e834c54b7335d308caaf7cf','2020-04-15 07:40:27',4),(90,'77c65ccf997d42069c56ceee78e6ebc3','2020-04-16 07:40:34',17),(91,'5dc53c0074a34acda535b3a19f214e55','2020-04-17 06:30:06',17),(92,'8d0a1e5f1d2f41498d9567848f167fcc','2020-04-18 00:19:43',17),(93,'16ac102bbf33455ca32fb42c78bf50b3','2020-04-18 05:38:39',17),(94,'0a1cfd5809204916b1f34d6b0556f482','2020-04-18 07:04:13',4),(95,'c7c5f3bf046b49a6a5fbe288d9ec14f0','2020-04-19 07:04:18',1),(96,'399fc4bd5a6d48339624f6eb486c9d7d','2020-04-19 00:52:29',1),(97,'d9f0ec5f7c9d47be8631845410a01c4b','2020-04-19 00:54:55',1),(98,'5c9fc798cee74d4f8d226f3914d3334b','2020-04-20 00:55:47',1),(99,'ee3ee74f33254fccbb9dc4856bf4bd21','2020-04-20 01:05:14',1),(100,'aff13158df0640d1b85b70ec6e7b7a7a','2020-04-19 07:15:50',1),(101,'9b9c6a46604c483996859c60481cc9f0','2020-04-19 07:16:07',17),(102,'24547c66a4eb4fcdb461dbecf0282617','2020-04-20 07:16:11',16),(103,'8ed7f7226182471284dfa12f19b32249','2020-04-22 05:03:32',16),(104,'63c5197374614699a543134a9c9f8b50','2020-05-06 04:20:10',16),(105,'f7aad8162f314149ae829c4a8f9cc883','2020-05-06 10:14:43',16),(106,'136be98f391e45909f375b387ad65e06','2020-05-09 04:07:26',16),(107,'8f308f2605854a30aa3f6475191a318d','2020-05-09 10:30:43',16),(108,'5a64e821aa1047f78fab55a56c6422e7','2020-05-08 11:13:27',16),(109,'59082bff36734eb3ac35fa496b77b4a7','2020-05-08 11:13:49',16),(110,'c3173105afc54c45965793a64c82d237','2020-05-08 11:17:49',16),(111,'7500aff043a144bdb94ad2973d555d8a','2020-05-09 11:24:10',16),(112,'a2461775a8a246b1bb5273b1b6058fd4','2020-05-14 01:59:13',18),(113,'3937b8122f3a4ad6abf3e5c0efd1f467','2020-05-14 02:05:09',18),(114,'f73224c49afe4b03ad6a592ec7c65755','2020-05-15 02:05:15',1),(115,'a6c8d0b561b54507b219041a190dd894','2020-05-15 08:42:05',1),(116,'8f4e90d7b076412c94854255f6cbf1e6','2020-05-15 09:34:07',17),(117,'59620d4edf4c4e6f87058da9ecdc5d6d','2020-05-15 09:34:20',4),(118,'1a850386073d43a0ab01f61401f170fe','2020-05-15 09:34:41',1),(119,'e062c6ebb13f4e088bdbbf5047b92b8c','2020-05-16 09:34:46',4),(120,'4690e8ab50b94c0fa301bab794843ad0','2020-05-22 09:10:03',17),(121,'b32245f985ad426fbc1fe9b099dbb16a','2020-05-23 09:10:07',16),(122,'801f346c616c4b808205af1749db85be','2020-05-23 09:13:42',16),(123,'d2f9a5f20f754e2da67b36fb74da4b72','2020-05-22 09:25:47',16),(124,'07269f9b0ed4489f8aa0409a80730868','2020-05-22 09:27:34',17),(125,'00730b8bd5d64e2e99903b6ff4019605','2020-05-23 09:27:39',16),(126,'5f99f769b58949cdad884b0590d6817a','2020-05-23 02:15:24',16),(127,'9aa823c11a4143c9b9eb321092b9f1d8','2020-05-23 02:16:03',17),(128,'bdbb3b04b95a4b3097fe6dfae7cd3ba3','2020-05-23 02:16:09',17),(129,'0e186f851c3741b7a09c68b0ccc229e0','2020-05-23 02:16:26',16),(130,'2dfb240c8acd4af3883133cd83adc02e','2020-05-23 02:16:39',17),(131,'c86d62869fba4cf298dfa972a80b78b4','2020-05-23 02:16:48',16),(132,'1e26cc5e01b6464bb148d16c03d9d171','2020-05-23 02:16:58',17),(133,'d21aa49a7e8c4cfca2eef9a64ee1fa6f','2020-05-24 02:17:02',16),(134,'f4199459566347219f118c438fe7fb5a','2020-05-23 06:39:44',1),(135,'566cfeeac6dd46b28babe2cae0e5f4ec','2020-05-23 06:39:57',16),(136,'56fac56acaf14e54a1dd4560bc823b03','2020-05-23 06:41:43',17),(137,'19dcef716bcb4b73a487b94a4e17d378','2020-05-23 07:20:40',1),(138,'9a5b6c4c1254464db909a8a32768e4c9','2020-05-23 07:26:58',16),(139,'f6028af39d2847d088b942aa1db2626f','2020-05-23 07:33:46',1),(140,'00d43f3b80b04ea99913a62f51618045','2020-05-23 07:33:54',16),(141,'b667a7f2f6fa4895aa1477095d9856ac','2020-05-23 07:34:36',17),(142,'8cb805a4f3a8409e8e5e6dd4b921784d','2020-05-23 07:38:05',1),(143,'b2913b93af414886805d02072d6d35c8','2020-05-23 07:46:13',17),(144,'d48052ea54b644fba5f323655b7783ea','2020-05-24 07:46:17',16);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type` (
  `typeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '类型id',
  `typeName` varchar(255) NOT NULL COMMENT '类型名称',
  `userId` int(11) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`typeId`),
  KEY `userId` (`userId`),
  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (1,'Java',1),(3,'Java',1),(4,'MySQL',1);
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
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
  `userType` int(11) NOT NULL DEFAULT '0' COMMENT '用户权限，0普通用户，1管理员，2超级管理员',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'root','c7d4ff11a53a5a3ecde831017da293a8','hello','http://127.0.0.1:8080/getImage?fileName=dde499c20c144df19c95c1fa6d538432.jpg',2),(2,'aaa','ed89e4e626435b90911b15687d8954d8','096851','http://127.0.0.1:8080//getImage?fileName=0ad32e8b0b414a959707f57686b566a8.jpg',0),(3,'bbb','6358105b78320d647d8a2e43943643ae','5d681b','http://127.0.0.1:8080//getImage?fileName=0ad32e8b0b414a959707f57686b566a8.jpg',0),(4,'123','8358a5f4f53f8d786ba7bfd032d2a6cc','766bfc','http://127.0.0.1:8080/getImage?fileName=c5c0ff3cc8f249a08c5d088ddb58036f.jpg',0),(5,'asd','49d6529f772131e2de7be93262e89972','dcc0d1','http://127.0.0.1:8080//getImage?fileName=0ad32e8b0b414a959707f57686b566a8.jpg',0),(6,'dd!dd','a3f00cbccc9bd9fdb41b625544e1d57a','b7d61d','http://127.0.0.1:8080//getImage?fileName=0ad32e8b0b414a959707f57686b566a8.jpg',0),(7,'rootff','b425e2dfa4b9e838f70c63fa768d8d07','3f35b6','http://127.0.0.1:8080//getImage?fileName=0ad32e8b0b414a959707f57686b566a8.jpg',0),(8,'rearfdsa','f1cabe01807e2a4a6107a2a753242858','762c2b','http://127.0.0.1:8080//getImage?fileName=0ad32e8b0b414a959707f57686b566a8.jpg',0),(9,'sfdsafdsafds','8f03af0d56ae4e55f5b5361e173bd887','4951f1','http://127.0.0.1:8080//getImage?fileName=0ad32e8b0b414a959707f57686b566a8.jpg',0),(10,'mmm','4bae579cbcc98bb4045c276daed9598b','b4e37c','https://github.com/uphe/myimage/blob/master/default.png',0),(11,'nnn','3c03f389007e7444cca31129a6833df2','401f71','https://raw.githubusercontent.com/uphe/myimage/master/default.png',0),(12,'ccc','843a4a4bcff634a1ab5142abf968e247','8325ad','http://127.0.0.1:8080//getImage?fileName=0ad32e8b0b414a959707f57686b566a8.jpg',0),(13,'zzz','d81889096f72d9c06c6cf5913a83d2bd','c6acc6','https://raw.githubusercontent.com/uphe/myimage/master/default.png',0),(14,'ttt','4a176491292262597bb1a13bfcff9155','d827a8','https://raw.githubusercontent.com/uphe/myimage/master/default.png',0),(15,'ggg','9665f686167c27243cf48b426690b65b','945d5e','https://raw.githubusercontent.com/uphe/newsblog/master/src/main/resources/static/img/default.png',0),(16,'hzy','5d83855ceca7b146bc77293978c88ed2','ed2aef','http://127.0.0.1:8080/getImage?fileName=b9f348cde6774b3794cffeef658285cb.jpg',1),(17,'111','d54a850944532d30f029a9113a056dcb','caf7c1','http://127.0.0.1:8080/getImage?fileName=3862aba761004e5bbac3afba228dcbc9.jpg',0),(18,'564','412212db3594ddbf4467b875cb9ee8c5','ecdc62','https://raw.githubusercontent.com/uphe/newsblog/master/src/main/resources/static/img/default.png',0);
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

-- Dump completed on 2020-05-23 16:02:55
