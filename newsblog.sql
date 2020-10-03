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
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` VALUES (1,'first','hello world','摘要',0,21,1,'2020-03-18 04:29:42',1,NULL,NULL),(2,'哈哈','你好呀','摘要',0,0,1,'2020-03-18 04:33:37',1,NULL,NULL),(3,'房东是','范德萨发','摘要',0,0,1,'2020-03-18 04:34:37',1,NULL,NULL),(4,'房东是','范德萨发','摘要',0,0,0,'2020-03-18 04:35:11',1,NULL,NULL),(5,'房东是','发士大夫但是','摘要',0,0,5,'2020-03-18 04:37:36',1,NULL,NULL),(6,'广泛大使馆','后果脚后跟','摘要',0,0,18,'2020-03-18 04:38:48',1,NULL,NULL),(7,'广泛大使馆','后果脚后跟','摘要',0,0,0,'2020-03-18 04:39:12',1,NULL,NULL),(8,'广泛大使馆广泛大概','后果脚后跟给对方鼠标 ','摘要',0,0,0,'2020-03-18 04:39:19',1,NULL,NULL),(9,'广泛大使馆的','过的法国','摘要',0,0,0,'2020-03-18 06:23:44',1,NULL,NULL),(10,'广东省','广泛的','摘要',0,0,0,'2020-03-18 06:25:57',1,NULL,NULL),(11,'广泛的','广东佛山','摘要',0,0,0,'2020-03-18 06:28:12',1,NULL,NULL),(12,'广东佛山','广泛的','摘要',0,0,0,'2020-03-18 06:28:37',1,NULL,NULL),(13,'广泛的','广东佛山','摘要',0,0,0,'2020-03-18 06:29:47',1,NULL,NULL),(14,'房东是','发士大夫','摘要',0,0,0,'2020-03-18 06:46:25',1,NULL,NULL),(15,'发生的','富士达','摘要',0,0,0,'2020-03-18 06:47:03',1,NULL,NULL),(16,'大家好','欢迎来到王者荣耀','摘要',0,0,0,'2020-03-18 08:21:38',1,NULL,NULL),(17,'你好','abcdefg','摘要',0,0,0,'2020-03-18 08:22:30',1,NULL,NULL),(18,'的萨芬还是抠脚大汉','飞洒发的房东是发生','摘要',0,0,4,'2020-03-18 08:24:49',1,NULL,NULL),(19,'retetertretr','fdsfdsfgd','摘要',0,0,1,'2020-03-18 08:27:53',1,NULL,NULL),(20,'1111111111','111111111111111111','111111111111111111',0,0,3,'2020-03-18 08:34:03',1,NULL,NULL),(21,'22222222222','222222222222222222','222222222222222222',0,0,2,'2020-03-18 08:40:28',1,NULL,NULL),(22,'啦啦啦','0：成功 1：失败\n\n1：成功 0：失败\n\n不知道你们是怎么想的，我最初想的是boolean值，1为真，0为假，所以想当然的认为1表示成功，0表示失败\n\n在项目开发过过程中，我们可能会碰到用户登录的问题，正常、注销、拉黑，锁定等情况\n\n0：正常 1：注销 2：拉黑 3：锁定\n\n0：注销 1：正常 2：拉黑 3：锁定\n\n这样一看，还是0表示成功香，后来的我才知道，大部分人都是用0表示成功的，大于0都是非成功，多好','0：成功 1：失败\n\n1：成功 0：失败\n\n不知道你们是怎么想的，我最初想的是boolean值，1为',0,0,1,'2020-03-18 08:41:36',1,NULL,NULL),(23,'hello ','你好','你好',0,1,0,'2020-03-20 01:54:31',15,NULL,NULL),(24,'粗体测试','**这里是粗体**','**这里是粗体**',0,0,1,'2020-03-23 08:39:28',1,NULL,NULL),(25,'111','lalala','lalala',0,0,0,'2020-03-24 13:16:37',16,NULL,NULL),(26,'广泛的','广泛的','广泛的',0,0,0,'2020-03-26 06:37:50',16,NULL,NULL),(27,'大家好','真的好','真的好',0,1,0,'2020-03-26 07:23:00',16,NULL,NULL),(28,'00000000000','000000000000000','000000000000000',0,0,0,'2020-03-26 07:23:56',16,NULL,NULL),(29,'范德萨发','房东是','房东是',0,1,3,'2020-03-26 07:24:34',16,NULL,NULL),(30,'房东是','范德萨','范德萨',0,1,0,'2020-03-26 07:29:02',16,NULL,NULL),(31,'范德萨','发生的\n![image](E:/迅雷下载/测试头像.jpg)','发生的\n![image](E:/迅雷下载/测试头像.jpg)',0,1,4,'2020-03-27 04:15:21',4,NULL,NULL),(32,'hgfh','![image](E:/迅雷下载/测试头像.jpg)','![image](E:/迅雷下载/测试头像.jpg)',0,1,2,'2020-03-27 04:22:17',4,NULL,NULL),(33,'第三方','aaaaaaaaa\n![](http://127.0.0.1:8080/getImage?fileName=e4ab6b55e95e4b86901591d393608195.jpg)','aaaaaaaaa\n![](http://127.0.0.1:8080/getImage?fileN',0,2,1,'2020-04-16 08:24:50',17,NULL,NULL),(34,'Linux中用户与组群管理','飞洒地方 \n 富士达\n ![](http://127.0.0.1:8080/getImage?fileName=5151e096ec4f4ba4a65be58e228f6606.jpg)','飞洒地方 \n 富士达\n ![](http://127.0.0.1:8080/getImage?fil',1,1,0,'2020-04-16 09:09:15',17,NULL,NULL),(35,'SpringBoot推荐的模板引擎thymeleaf','你好\n![](http://127.0.0.1:8080/getImage?fileName=3862aba761004e5bbac3afba228dcbc9.jpg)','你好\n![](http://127.0.0.1:8080/getImage?fileName=386',1,6,1,'2020-04-16 09:13:12',17,NULL,NULL),(36,'范德萨','范德萨','范德萨',3,45,3,'2020-04-17 00:19:49',17,NULL,NULL),(37,'广泛的','广泛的','广泛的',4,59,7,'2020-04-17 00:35:20',17,NULL,NULL),(38,'Linux中用户与组群管理','fdsaf','fdsaf',0,2,0,'2020-04-21 05:03:39',16,'0',NULL),(39,'111','111','111',0,1,0,'2020-04-21 05:05:16',16,'1',NULL),(40,'xxx','xxx','xxx',0,0,0,'2020-04-21 05:07:04',16,'1',NULL),(41,'ddd','ddd','ddd',0,1,0,'2020-05-05 04:26:46',16,'',''),(42,'她她她她她她她她她','广泛大使馆','广泛大使馆',2,18,7,'2020-05-08 11:33:00',16,'',''),(43,'gfdg','gfdg','gfdg',0,0,0,'2020-05-23 02:12:52',16,'macos','JavaScript'),(44,'gfds ','gfdsg','gfdsg',1,7,2,'2020-05-23 02:13:18',16,'eclipse,intellij idea','HTML,jquery'),(45,'法国发过','广泛的','广泛的',0,20,8,'2020-05-26 00:13:06',1,'mysql,redis','MySQL,Java'),(46,'111','111![](http://127.0.0.1:8080/getImage?fileName=356610a6bdc44c619cb0f6401fe391ca.jpg)\n','111![](http://127.0.0.1:8080/getImage?fileName=356',0,13,6,'2020-05-26 00:18:22',1,'','Java'),(47,'回复功能测试','hello','hello',0,185,11,'2020-05-26 02:08:04',1,'','MySQL'),(48,'test','test','test',0,17,9,'2020-05-27 08:24:15',16,'',''),(49,'1111','1111','1111',0,41,17,'2020-05-27 08:47:44',16,'',''),(50,'date','date','date',0,1,0,'2020-05-28 10:10:28',1,'',''),(51,'000','000','000',0,0,0,'2020-05-28 10:17:32',1,'',''),(52,'555','555','555',0,2,0,'2020-05-28 10:19:23',1,'',''),(53,'hello world','hello world','hello world',1,13,5,'2020-06-01 02:36:54',1,'mysql','Java'),(54,'12','gdfsdbfgdgbh','5645641374jk5',1,2,0,'2020-09-18 13:28:36',19,'mysql',''),(55,'dvsvgsf','**啊根据客户发**\n```\nfsdfajdksafhsjkg\n```\n\n# 发动机撒卡复活','**啊根据客户发**\n```\nfsdfajdksafhsjkg\n```\n\n# 发动机撒卡复活',0,58,0,'2020-09-22 03:26:13',1,'',''),(56,'按不出f','范德萨富士达','范德萨富士达',0,4,0,'2020-09-24 00:31:53',1,'',''),(57,'我的新闻博客','# 第一句\n**Hello World**\n你好','# 第一句\n**Hello World**\n你好',0,64,0,'2020-09-24 06:09:40',1,'','');
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `collect`
--

DROP TABLE IF EXISTS `collect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `collect` (
  `collectId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `blogId` int(11) NOT NULL,
  PRIMARY KEY (`collectId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collect`
--

LOCK TABLES `collect` WRITE;
/*!40000 ALTER TABLE `collect` DISABLE KEYS */;
INSERT INTO `collect` VALUES (1,1,1),(2,1,2);
/*!40000 ALTER TABLE `collect` ENABLE KEYS */;
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
  `parentId` int(11) NOT NULL COMMENT '指向父评论的id，如果不是对评论的回复则为-1',
  PRIMARY KEY (`commentId`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'hello','2020-03-31 02:43:02',1,1,0,-1),(2,'广泛的','2020-03-31 07:14:02',6,4,0,-1),(3,'符合规范','2020-03-31 07:27:03',6,4,0,-1),(4,'广泛的是个','2020-03-31 07:27:48',6,4,0,-1),(5,'供奉的是','2020-03-31 07:37:18',6,4,0,-1),(6,'广泛的','2020-03-31 07:39:23',6,4,0,-1),(7,'广泛的','2020-03-31 07:39:30',3,4,0,-1),(8,'高浮雕高浮雕','2020-03-31 08:03:18',6,4,0,-1),(9,'广泛的','2020-03-31 08:04:57',6,4,0,-1),(10,'广泛的','2020-03-31 08:04:59',6,4,0,-1),(11,'1111111111111111111111111111111','2020-03-31 08:05:15',6,4,0,-1),(12,'5555555555555555555555','2020-03-31 08:05:59',6,4,0,-1),(13,'你官方电话','2020-03-31 08:07:46',6,4,0,-1),(14,'666666666666666','2020-03-31 08:09:47',6,4,0,-1),(15,'0000000000000000000','2020-03-31 08:10:32',6,4,0,-1),(16,'？？？？？？？？？？？？？','2020-03-31 08:12:26',6,4,0,-1),(17,'？？？？？？？？？？？？？','2020-03-31 08:12:37',6,4,0,-1),(18,'股份大股东是','2020-03-31 08:13:17',6,4,0,-1),(19,'···················','2020-03-31 08:13:59',6,4,0,-1),(20,'官方的说法的高浮雕高浮雕','2020-03-31 08:14:07',6,4,0,-1),(21,'的高富帅','2020-03-31 08:14:19',5,4,0,-1),(22,'广泛的','2020-03-31 08:14:34',5,4,0,-1),(23,'华国锋','2020-03-31 08:15:22',5,4,0,-1),(24,'华国锋','2020-03-31 08:15:30',5,4,0,-1),(25,'444433333','2020-03-31 08:16:28',5,4,0,-1),(26,'广泛的','2020-03-31 08:23:12',20,4,0,-1),(27,'555','2020-03-31 08:25:14',20,4,0,-1),(28,'777','2020-03-31 08:27:40',20,4,0,-1),(29,'广泛大概','2020-03-31 08:28:14',21,4,0,-1),(30,'888','2020-03-31 08:29:00',21,4,0,-1),(31,'范德萨','2020-03-31 08:30:02',18,4,0,-1),(32,'风格的','2020-03-31 08:32:46',2,4,0,-1),(33,'范德萨','2020-03-31 08:33:59',19,4,0,-1),(34,'66666666666','2020-03-31 08:35:03',18,4,0,-1),(35,'55555555555','2020-03-31 08:35:11',18,4,0,-1),(36,'股份的公司','2020-03-31 08:35:32',18,4,0,-1),(37,'法国大使馆','2020-03-31 08:36:01',22,4,0,-1),(38,'范德萨','2020-03-31 08:38:02',29,4,0,-1),(39,'供奉的是','2020-03-31 08:38:06',29,4,0,-1),(40,'广泛的','2020-03-31 08:38:10',29,4,0,-1),(41,'hello','2020-04-02 06:00:03',24,4,0,-1),(42,'风格和','2020-04-06 06:41:32',31,4,0,-1),(43,'hzy第一次','2020-04-06 07:29:32',32,16,0,-1),(44,'hzy第二次','2020-04-06 07:29:38',32,16,0,-1),(45,'[code=java]\nhello\n[/code]','2020-04-15 05:21:31',31,4,0,-1),(46,'jbvn','2020-04-15 07:43:25',31,17,0,-1),(47,'hello','2020-04-15 07:43:35',31,17,0,-1),(48,'广泛的','2020-04-16 09:02:19',33,17,0,-1),(49,'发多少个','2020-04-17 00:35:28',37,17,0,-1),(50,'fdg','2020-04-19 00:55:05',37,1,0,-1),(51,'dfd','2020-04-19 00:55:12',37,1,0,-1),(52,'','2020-04-19 00:55:52',36,1,0,-1),(53,'gdf','2020-04-19 00:55:57',36,1,0,-1),(54,'bv','2020-04-19 00:58:19',37,1,0,-1),(55,'hgjghj ','2020-04-19 01:15:18',35,1,0,-1),(56,'范德萨','2020-04-19 07:15:31',37,1,0,-1),(57,'范德萨','2020-04-19 07:15:45',36,1,0,-1),(58,'范德萨','2020-04-19 07:16:02',37,17,0,-1),(59,'范德萨','2020-04-19 07:16:18',37,16,0,-1),(60,'的跌幅高达','2020-05-08 11:33:08',42,16,0,-1),(61,'111','2020-05-22 09:25:44',42,16,0,-1),(62,'华国锋','2020-05-22 09:30:05',42,16,0,-1),(63,'666','2020-05-22 09:30:08',42,16,0,-1),(64,'2452','2020-05-22 09:33:04',42,16,0,-1),(65,'tret','2020-05-22 09:33:07',42,16,0,-1),(66,'hjgh','2020-05-23 02:16:01',44,17,0,-1),(67,'k\'k\'k','2020-05-23 07:20:56',44,16,0,-1),(68,'111','2020-05-26 00:18:51',46,1,0,-1),(69,'111','2020-05-26 00:32:25',45,1,0,-1),(70,'这是回复id为1的评论','2020-05-26 09:24:27',1,1,0,1),(71,'回复1的回复','2020-05-26 09:30:18',1,1,0,1),(72,'回复70的，其中70是回复1的，所以这也是1的子回复','2020-05-26 09:31:02',1,1,0,70),(73,'评论1','2020-05-26 02:09:09',47,1,0,-1),(74,'评论2','2020-05-26 02:09:15',47,1,0,-1),(75,'回复评论1','2020-05-26 10:09:37',47,1,0,73),(76,'回复评论1的回复，即回复评论75','2020-05-26 10:10:18',47,1,0,75),(77,'回复76的，属于评论1','2020-05-26 11:55:19',47,1,0,76),(78,'回复评论2','2020-05-26 11:55:56',47,1,0,74),(79,'人发给对方','2020-05-26 07:21:35',47,1,0,-1),(80,'评论3','2020-05-26 07:21:55',47,1,0,-1),(81,'回复评论3','2020-05-26 15:22:33',47,1,0,80),(82,'回复77','2020-05-26 16:21:09',47,16,0,77),(83,'test','2020-05-27 11:19:21',47,5,0,80),(84,'范德萨发','2020-05-27 04:19:02',47,1,0,-1),(85,'你好','2020-05-27 04:19:16',47,1,0,-1),(86,'啦啦啦all','2020-05-27 04:19:38',47,1,0,-1),(87,'放大吧！！！','2020-05-27 07:59:34',47,1,0,80),(88,'啊啊啊啊','2020-05-27 08:00:42',47,1,0,-1),(89,'啊啊啊啊啊','2020-05-27 08:00:51',47,1,0,80),(90,'回复啊啊啊啊','2020-05-27 08:02:10',47,1,0,80),(91,'test','2020-05-27 08:07:20',45,1,0,-1),(92,'uuu','2020-05-27 08:14:50',45,1,0,-1),(93,'回复uuu','2020-05-27 08:16:11',45,1,0,92),(94,'回复root','2020-05-27 08:16:31',45,1,0,92),(95,'你好','2020-05-27 08:17:51',45,16,0,92),(96,'回复hzy','2020-05-27 08:18:16',45,16,0,92),(97,'222','2020-05-27 08:23:48',45,16,0,92),(98,'一级评论','2020-05-27 08:24:28',48,16,0,-1),(99,'二级评论','2020-05-27 08:24:39',48,16,0,98),(100,'再来个一级评论','2020-05-27 08:24:58',48,16,0,-1),(101,'回复再来个一级评论','2020-05-27 08:25:11',48,16,0,100),(102,'回复二级评论','2020-05-27 08:27:08',48,16,0,101),(103,'回复101','2020-05-27 08:27:37',48,16,0,101),(104,'啊啊啊','2020-05-27 08:28:07',48,16,0,101),(105,'回复104','2020-05-27 08:30:57',48,16,0,101),(106,'回复105','2020-05-27 08:47:24',48,16,0,105),(107,'1','2020-05-27 08:47:50',49,16,0,-1),(108,'2','2020-05-27 08:47:57',49,16,0,107),(109,'2','2020-05-27 08:48:04',49,16,0,107),(110,'3','2020-05-27 08:48:12',49,16,0,108),(111,'1','2020-05-27 08:48:18',49,16,0,-1),(112,'2','2020-05-27 08:48:26',49,16,0,111),(113,'3','2020-05-28 08:53:25',49,16,0,112),(114,'0','2020-05-28 08:53:40',49,16,0,113),(115,'2222','2020-05-28 08:53:51',49,16,0,112),(116,'3333','2020-05-28 08:54:06',49,16,0,113),(117,'1111','2020-05-28 08:54:15',49,16,0,107),(118,'11111111111','2020-05-28 08:54:26',49,16,0,117),(119,'111111111111','2020-05-28 08:54:34',49,16,0,117),(120,'2222222222','2020-05-28 08:54:44',49,16,0,109),(121,'333333333333','2020-05-28 08:54:51',49,16,0,110),(122,'11111111','2020-05-28 09:55:22',46,1,0,68),(123,'222','2020-05-28 09:55:36',46,1,0,68),(124,'33333','2020-05-28 09:55:47',46,1,0,122),(125,'3333','2020-05-28 09:55:58',46,1,0,-1),(126,'5555','2020-05-28 09:56:06',46,1,0,125),(127,'hello','2020-05-28 10:00:23',49,1,0,111),(128,'你好','2020-05-28 10:00:44',49,1,0,112),(129,'nihao','2020-06-01 10:37:00',53,1,0,-1),(130,'hello','2020-06-01 10:37:05',53,1,0,129),(131,'1111','2020-06-01 10:37:12',53,1,0,130),(132,'lalala','2020-06-01 10:37:21',53,1,0,-1),(133,'.....','2020-06-01 10:37:31',53,1,0,132),(134,'bcgb','2020-09-04 16:00:36',42,1,0,64);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `followId` int(255) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL COMMENT '关注者id',
  `followUserId` int(11) NOT NULL COMMENT '被关注者id',
  PRIMARY KEY (`followId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (1,1,2),(3,2,3),(4,3,1);
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `label`
--

DROP TABLE IF EXISTS `label`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `label` (
  `labelId` int(11) NOT NULL AUTO_INCREMENT,
  `labelName` varchar(255) NOT NULL,
  `userId` int(11) NOT NULL,
  `blogId` int(11) NOT NULL,
  PRIMARY KEY (`labelId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `label`
--

LOCK TABLES `label` WRITE;
/*!40000 ALTER TABLE `label` DISABLE KEYS */;
INSERT INTO `label` VALUES (1,'abc',2,1),(2,'abc0',2,1),(3,'abc1',2,1),(4,'abc2',2,1),(5,'abc3',2,1),(6,'abc4',2,1);
/*!40000 ALTER TABLE `label` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (1,'test','test',1,'2020-05-09 15:43:39','2020-05-09 15:43:39'),(2,'test1','test1',1,'2020-05-09 15:44:05','2020-05-09 15:44:05'),(3,'test2','test2',1,'2020-05-09 15:44:12','2020-05-09 15:44:12'),(4,'hello','my first notice',1,'2020-05-13 02:34:26','2020-05-20 02:34:26'),(5,'hello','my first notice...',1,'2020-05-13 03:37:47','2020-05-20 03:37:47'),(6,'111','111',1,'2020-05-15 08:40:21','2020-05-22 08:40:21'),(7,'晓雨','你好',1,'2020-05-15 09:34:36','2020-05-22 09:34:36'),(8,'啊啊啊','啊啊啊',1,'2020-05-23 06:39:36','2020-05-30 06:39:36'),(9,'哈哈哈哈哈哈哈哈','哈哈哈哈哈哈',1,'2020-05-23 07:33:41','2020-05-30 07:33:41'),(10,'各干各','各干各',1,'2020-05-23 07:38:03','2020-05-30 07:38:03'),(11,'广泛大使馆','供奉的是',1,'2020-05-25 05:13:46','2020-06-01 05:13:46'),(12,'jintian','111',1,'2020-06-01 02:37:46','2020-06-08 02:37:46'),(13,'dffd','bbbbbbbbbbbbbbbbbbbbbbbbbbbb',1,'2020-09-04 08:03:01','2020-09-11 08:03:01');
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `readnotice`
--

LOCK TABLES `readnotice` WRITE;
/*!40000 ALTER TABLE `readnotice` DISABLE KEYS */;
INSERT INTO `readnotice` VALUES (1,1,1,'2020-05-13 11:31:47'),(2,4,1,'2020-05-20 03:34:34'),(3,4,1,'2020-05-20 03:38:36'),(4,5,1,'2020-05-20 03:40:15'),(5,0,17,'2020-05-22 09:29:22'),(6,0,17,'2020-05-22 09:29:41'),(7,0,17,'2020-05-22 09:30:54'),(8,0,17,'2020-05-22 09:31:28'),(9,6,17,'2020-05-22 09:32:35'),(10,5,17,'2020-05-22 09:33:10'),(11,6,4,'2020-05-22 09:34:17'),(12,5,4,'2020-05-22 09:34:18'),(13,7,4,'2020-05-22 09:34:49'),(14,8,17,'2020-05-30 06:40:16'),(15,13,2,'2020-09-11 08:03:30');
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
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `remind`
--

LOCK TABLES `remind` WRITE;
/*!40000 ALTER TABLE `remind` DISABLE KEYS */;
INSERT INTO `remind` VALUES (1,'hello',1,2,1,'2020-05-15 10:25:49',1,0),(2,'hello',1,2,1,'2020-05-15 10:25:49',1,0),(3,'hello',1,2,1,'2020-05-15 10:25:49',1,0),(4,'hello',1,2,1,'2020-05-15 02:27:59',1,0),(5,'hello',1,2,1,'2020-05-15 02:28:35',1,0),(6,'hello',1,2,1,'2020-05-15 02:29:37',1,0),(7,'111点赞了她她她她她她她她她',17,16,42,'2020-05-22 08:45:42',2,0),(8,'111点赞了她她她她她她她她她',17,16,42,'2020-05-22 08:46:47',1,0),(9,'hzy点赞了她她她她她她她她她',16,16,42,'2020-05-22 09:10:53',2,0),(10,'hzy点赞了她她她她她她她她她',16,16,42,'2020-05-22 09:13:49',2,0),(11,'hzy评论了她她她她她她她她她',16,16,42,'2020-05-22 09:25:44',1,1),(12,'hzy点赞了她她她她她她她她她',16,16,42,'2020-05-22 09:30:00',1,0),(13,'hzy评论了她她她她她她她她她',16,16,42,'2020-05-22 09:30:05',1,1),(14,'hzy评论了她她她她她她她她她',16,16,42,'2020-05-22 09:30:08',1,1),(15,'hzy评论了她她她她她她她她她:2452',16,16,42,'2020-05-22 09:33:04',1,1),(16,'hzy评论了她她她她她她她她她:tret',16,16,42,'2020-05-22 09:33:07',1,1),(17,'111点赞了gfds ',17,16,44,'2020-05-23 02:15:42',2,0),(18,'111点赞了gfds ',17,16,44,'2020-05-23 02:15:57',2,0),(19,'111评论了gfds :hjgh',17,16,44,'2020-05-23 02:16:01',1,1),(20,'111点赞了gfds ',17,16,44,'2020-05-23 02:16:36',2,0),(21,'root点赞了gfds ',1,16,44,'2020-05-23 07:20:36',0,0),(22,'hzy评论了gfds :k\'k\'k',16,16,44,'2020-05-23 07:20:56',1,1),(23,'root评论了111:111',1,1,46,'2020-05-26 00:18:51',0,1),(24,'root评论了法国发过:111',1,1,45,'2020-05-26 00:32:25',0,1),(25,'root评论了回复功能测试:评论1',1,1,47,'2020-05-26 02:09:09',0,1),(26,'root评论了回复功能测试:评论2',1,1,47,'2020-05-26 02:09:15',0,1),(27,'root评论了回复功能测试:人发给对方',1,1,47,'2020-05-26 07:21:35',0,1),(28,'root评论了回复功能测试:评论3',1,1,47,'2020-05-26 07:21:55',0,1),(29,'root评论了回复功能测试:范德萨发',1,1,47,'2020-05-27 04:19:02',0,1),(30,'root评论了回复功能测试:你好',1,1,47,'2020-05-27 04:19:16',0,1),(31,'root评论了回复功能测试:啦啦啦all',1,1,47,'2020-05-27 04:19:38',0,1),(32,'root评论了回复功能测试:放大吧！！！',1,1,47,'2020-05-27 07:59:34',0,1),(33,'root评论了回复功能测试:啊啊啊啊',1,1,47,'2020-05-27 08:00:42',0,1),(34,'root评论了回复功能测试:啊啊啊啊啊',1,1,47,'2020-05-27 08:00:51',0,1),(35,'root评论了回复功能测试:回复啊啊啊啊',1,1,47,'2020-05-27 08:02:10',0,1),(36,'root评论了法国发过:test',1,1,45,'2020-05-27 08:07:20',0,1),(37,'root评论了法国发过:uuu',1,1,45,'2020-05-27 08:14:50',0,1),(38,'root评论了法国发过:回复uuu',1,1,45,'2020-05-27 08:16:11',0,1),(39,'root评论了法国发过:回复root',1,1,45,'2020-05-27 08:16:31',0,1),(40,'hzy评论了法国发过:你好',16,1,45,'2020-05-27 08:17:51',0,1),(41,'hzy评论了法国发过:回复hzy',16,1,45,'2020-05-27 08:18:16',0,1),(42,'hzy评论了法国发过:222',16,1,45,'2020-05-27 08:23:48',0,1),(43,'hzy评论了test:一级评论',16,16,48,'2020-05-27 08:24:28',0,1),(44,'hzy评论了test:二级评论',16,16,48,'2020-05-27 08:24:39',0,1),(45,'hzy评论了test:再来个一级评论',16,16,48,'2020-05-27 08:24:58',0,1),(46,'hzy评论了test:回复再来个一级评论',16,16,48,'2020-05-27 08:25:11',0,1),(47,'hzy评论了test:回复二级评论',16,16,48,'2020-05-27 08:27:08',0,1),(48,'hzy评论了test:回复101',16,16,48,'2020-05-27 08:27:37',0,1),(49,'hzy评论了test:啊啊啊',16,16,48,'2020-05-27 08:28:07',0,1),(50,'hzy评论了test:回复104',16,16,48,'2020-05-27 08:30:57',0,1),(51,'hzy评论了test:回复105',16,16,48,'2020-05-27 08:47:24',0,1),(52,'hzy评论了1111:1',16,16,49,'2020-05-27 08:47:50',0,1),(53,'hzy评论了1111:2',16,16,49,'2020-05-27 08:47:57',0,1),(54,'hzy评论了1111:2',16,16,49,'2020-05-27 08:48:04',0,1),(55,'hzy评论了1111:3',16,16,49,'2020-05-27 08:48:12',0,1),(56,'hzy评论了1111:1',16,16,49,'2020-05-27 08:48:18',0,1),(57,'hzy评论了1111:2',16,16,49,'2020-05-27 08:48:26',0,1),(58,'hzy评论了1111:3',16,16,49,'2020-05-28 08:53:25',0,1),(59,'hzy评论了1111:0',16,16,49,'2020-05-28 08:53:40',0,1),(60,'hzy评论了1111:2222',16,16,49,'2020-05-28 08:53:51',0,1),(61,'hzy评论了1111:3333',16,16,49,'2020-05-28 08:54:06',0,1),(62,'hzy评论了1111:1111',16,16,49,'2020-05-28 08:54:15',0,1),(63,'hzy评论了1111:11111111111',16,16,49,'2020-05-28 08:54:26',0,1),(64,'hzy评论了1111:111111111111',16,16,49,'2020-05-28 08:54:34',0,1),(65,'hzy评论了1111:2222222222',16,16,49,'2020-05-28 08:54:44',0,1),(66,'hzy评论了1111:333333333333',16,16,49,'2020-05-28 08:54:51',0,1),(67,'root评论了111:11111111',1,1,46,'2020-05-28 09:55:22',0,1),(68,'root评论了111:222',1,1,46,'2020-05-28 09:55:36',0,1),(69,'root评论了111:33333',1,1,46,'2020-05-28 09:55:47',0,1),(70,'root评论了111:3333',1,1,46,'2020-05-28 09:55:58',0,1),(71,'root评论了111:5555',1,1,46,'2020-05-28 09:56:06',0,1),(72,'root评论了1111:hello',1,16,49,'2020-05-28 10:00:24',0,1),(73,'root评论了1111:你好',1,16,49,'2020-05-28 10:00:44',0,1),(74,'root评论了hello world:nihao',1,1,53,'2020-06-01 02:37:01',0,1),(75,'root评论了hello world:hello',1,1,53,'2020-06-01 02:37:06',0,1),(76,'root评论了hello world:1111',1,1,53,'2020-06-01 02:37:13',0,1),(77,'root评论了hello world:lalala',1,1,53,'2020-06-01 02:37:22',0,1),(78,'root评论了hello world:.....',1,1,53,'2020-06-01 02:37:32',0,1),(79,'root点赞了hello world',1,1,53,'2020-06-01 02:39:20',2,0),(80,'root评论了她她她她她她她她她:bcgb',1,16,42,'2020-09-04 08:00:36',0,1),(81,'root点赞了hello world',1,1,53,'2020-09-18 14:06:45',0,0),(82,'root点赞了12',1,19,54,'2020-09-18 14:07:04',2,0),(83,'root点赞了12',1,19,54,'2020-09-20 01:01:31',2,0),(84,'root点赞了12',1,19,54,'2020-09-20 01:02:05',0,0);
/*!40000 ALTER TABLE `remind` ENABLE KEYS */;
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
  `blogId` int(11) NOT NULL COMMENT '博客id',
  PRIMARY KEY (`typeId`),
  KEY `userId` (`userId`),
  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (1,'Java',1,1),(3,'Java',1,2),(4,'MySQL',1,3),(5,'redis',1,5),(6,'redis0',1,5),(7,'redis1',1,5),(8,'redis2',1,5),(9,'redis3',1,5),(10,'redis4',1,5);
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'root','c7d4ff11a53a5a3ecde831017da293a8','hello','http://10.101.76.66:8080/getImage?fileName=4a04443c411f452883bdd39a7bf7b68e.jpg',2),(2,'aaa','ed89e4e626435b90911b15687d8954d8','096851','http://127.0.0.1:8080//getImage?fileName=0ad32e8b0b414a959707f57686b566a8.jpg',0),(3,'bbb','6358105b78320d647d8a2e43943643ae','5d681b','http://127.0.0.1:8080//getImage?fileName=0ad32e8b0b414a959707f57686b566a8.jpg',0),(4,'123','8358a5f4f53f8d786ba7bfd032d2a6cc','766bfc','http://127.0.0.1:8080/getImage?fileName=c5c0ff3cc8f249a08c5d088ddb58036f.jpg',0),(5,'asd','49d6529f772131e2de7be93262e89972','dcc0d1','http://127.0.0.1:8080//getImage?fileName=0ad32e8b0b414a959707f57686b566a8.jpg',0),(6,'dd!dd','a3f00cbccc9bd9fdb41b625544e1d57a','b7d61d','http://127.0.0.1:8080//getImage?fileName=0ad32e8b0b414a959707f57686b566a8.jpg',0),(7,'rootff','b425e2dfa4b9e838f70c63fa768d8d07','3f35b6','http://127.0.0.1:8080//getImage?fileName=0ad32e8b0b414a959707f57686b566a8.jpg',0),(8,'rearfdsa','f1cabe01807e2a4a6107a2a753242858','762c2b','http://127.0.0.1:8080//getImage?fileName=0ad32e8b0b414a959707f57686b566a8.jpg',0),(9,'sfdsafdsafds','8f03af0d56ae4e55f5b5361e173bd887','4951f1','http://127.0.0.1:8080//getImage?fileName=0ad32e8b0b414a959707f57686b566a8.jpg',0),(10,'mmm','4bae579cbcc98bb4045c276daed9598b','b4e37c','http://192.168.137.65:8080/img/default.png',0),(11,'nnn','3c03f389007e7444cca31129a6833df2','401f71','http://192.168.137.65:8080/img/default.png',0),(12,'ccc','843a4a4bcff634a1ab5142abf968e247','8325ad','http://192.168.137.65:8080/img/default.png',0),(13,'zzz','d81889096f72d9c06c6cf5913a83d2bd','c6acc6','http://192.168.137.65:8080/img/default.png',0),(14,'ttt','4a176491292262597bb1a13bfcff9155','d827a8','http://192.168.137.65:8080/img/default.png',0),(15,'ggg','9665f686167c27243cf48b426690b65b','945d5e','http://192.168.137.65:8080/img/default.png',0),(16,'hzy','5d83855ceca7b146bc77293978c88ed2','ed2aef','http://192.168.137.65:8080/img/default.png',1),(17,'111','d54a850944532d30f029a9113a056dcb','caf7c1','http://192.168.137.65:8080/img/default.png',0),(18,'564','412212db3594ddbf4467b875cb9ee8c5','ecdc62','http://192.168.137.65:8080/img/default.png',0),(19,'lbx','59aa55c10dce212906d7d7464dea8acc','d5565d','http://192.168.137.65:8080/img/default.png',0),(20,'fdsfdf','0931a12e7221dce63aba08d0fadf321c','e001e8','http://127.0.0.1:8080/img/default.png',0),(21,'root123','73bf503b9bceef57d3d077023a91ac2d','110b41','http://127.0.0.1:8080/img/default.png',0),(22,'root1','599370d92e0b391c532fed79950ce5b8','5713c3','http://127.0.0.1:8080/img/default.png',0),(23,'','1b00b3a6cd3d0e639fed006413998a89','cc15d5','http://127.0.0.1:8080/img/default.png',0);
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

-- Dump completed on 2020-09-29 17:28:18
