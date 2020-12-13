/*
 Navicat Premium Data Transfer

 Source Server         : hzy
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : newsblog

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 13/12/2020 11:55:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
  `blogId` int(11) NOT NULL AUTO_INCREMENT COMMENT '博客id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客标题',
  `summary` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章摘要，正文前50字或全部正文',
  `article` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章内容',
  `likeCount` int(11) NOT NULL DEFAULT 0 COMMENT '博客点赞数',
  `hitCount` int(11) NOT NULL DEFAULT 0 COMMENT '博客点击量',
  `commentCount` int(11) NOT NULL DEFAULT 0 COMMENT '博客评论数',
  `createDate` datetime(0) NOT NULL COMMENT '博客创建时间',
  `userId` int(11) NOT NULL COMMENT '博客创建者',
  PRIMARY KEY (`blogId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 132 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect`  (
  `collectId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `blogId` int(11) NOT NULL,
  PRIMARY KEY (`collectId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `commentId` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
  `createDate` datetime(0) NOT NULL COMMENT '评论时间',
  `blogId` int(11) NOT NULL COMMENT '博客id',
  `userId` int(11) NOT NULL COMMENT '用户id',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '是否删除，默认为0，没删',
  `parentId` int(11) NOT NULL COMMENT '指向父评论的id，如果不是对评论的回复则为-1',
  PRIMARY KEY (`commentId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 181 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for follow
-- ----------------------------
DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow`  (
  `followId` int(255) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL COMMENT '关注者id',
  `followUserId` int(11) NOT NULL COMMENT '被关注者id',
  PRIMARY KEY (`followId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for label
-- ----------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `label`  (
  `labelId` int(11) NOT NULL AUTO_INCREMENT,
  `labelName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userId` int(11) NOT NULL,
  `blogId` int(11) NOT NULL,
  PRIMARY KEY (`labelId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for like
-- ----------------------------
DROP TABLE IF EXISTS `like`;
CREATE TABLE `like`  (
  `likeId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `blogId` int(11) NOT NULL,
  `createDate` datetime(0) NOT NULL,
  `state` int(11) NOT NULL DEFAULT 0 COMMENT '是否取消点赞，默认0未取消',
  PRIMARY KEY (`likeId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for likerecord
-- ----------------------------
DROP TABLE IF EXISTS `likerecord`;
CREATE TABLE `likerecord`  (
  `likeRecordId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `blogId` int(11) NOT NULL,
  `createDate` datetime(0) NOT NULL,
  `isLike` int(11) NOT NULL COMMENT '1表示点赞，0表示未点赞',
  PRIMARY KEY (`likeRecordId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 133 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `messageId` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息id',
  `messageFromId` int(11) NOT NULL COMMENT '消息发送方id',
  `messageToId` int(11) NOT NULL COMMENT '消息接收方id',
  `messageContent` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息内容',
  ` messageCreateDate` date NOT NULL COMMENT '消息发送的时间',
  ` messageHasRead` int(11) NOT NULL COMMENT '消息是否已经读过',
  ` messageConversationId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '会话id',
  PRIMARY KEY (`messageId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `noticeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '公告id',
  `noticeTitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告标题',
  `noticeContent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 公告内容',
  `operatorId` int(11) NOT NULL COMMENT '发布人id',
  `publishTime` datetime(0) NOT NULL COMMENT '发布时间',
  `expirationTime` datetime(0) NOT NULL COMMENT '过期时间',
  PRIMARY KEY (`noticeId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for readnotice
-- ----------------------------
DROP TABLE IF EXISTS `readnotice`;
CREATE TABLE `readnotice`  (
  `readNoticeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '已读id',
  `noticeId` int(11) NOT NULL COMMENT '公告id',
  `userId` int(11) NOT NULL COMMENT '用户id',
  `expirationTime` datetime(0) NOT NULL COMMENT '已读公告过期时间',
  PRIMARY KEY (`readNoticeId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for remind
-- ----------------------------
DROP TABLE IF EXISTS `remind`;
CREATE TABLE `remind`  (
  `remindId` int(11) NOT NULL AUTO_INCREMENT,
  `remindContent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '提醒内容',
  `fromId` int(11) NOT NULL COMMENT '发起提醒的人',
  `toId` int(11) NOT NULL COMMENT '接收提醒的人',
  `blogId` int(11) NOT NULL COMMENT '文章id',
  `createDate` datetime(0) NOT NULL COMMENT '创建时间',
  `state` int(11) NOT NULL COMMENT '0表示未读，1表示已读，2表示删除',
  `remindType` int(11) NOT NULL COMMENT '0表示点赞，1表示评论',
  PRIMARY KEY (`remindId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 328 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for token
-- ----------------------------
DROP TABLE IF EXISTS `token`;
CREATE TABLE `token`  (
  `tokenId` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `expired` datetime(0) NOT NULL COMMENT '过期时间',
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`tokenId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `typeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '类型id',
  `typeName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型名称',
  `userId` int(11) NOT NULL COMMENT '用户id',
  `blogId` int(11) NOT NULL COMMENT '博客id',
  PRIMARY KEY (`typeId`) USING BTREE,
  INDEX `userId`(`userId`) USING BTREE,
  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '随机串，用于加密密码',
  `headUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户头像，是图片的地址',
  `userType` int(11) NOT NULL DEFAULT 0 COMMENT '用户权限，0普通用户，1管理员，2超级管理员',
  PRIMARY KEY (`userId`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
