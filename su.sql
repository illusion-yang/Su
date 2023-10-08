/*
Navicat MySQL Data Transfer

Source Server         : 223
Source Server Version : 80026
Source Host           : 127.0.0.1:3306
Source Database       : su

Target Server Type    : MYSQL
Target Server Version : 80026
File Encoding         : 65001

Date: 2023-10-08 22:36:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '123');

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `type` int DEFAULT NULL,
  `file_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES ('1', '8', '1', '1');
INSERT INTO `collect` VALUES ('3', '8', '1', '3');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `entity_id` int DEFAULT NULL,
  `target_id` int DEFAULT NULL,
  `pid` int DEFAULT NULL,
  `count` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` int DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '8', '3', null, null, '她也曾', null, '2023-05-13 23:04:59');
INSERT INTO `comment` VALUES ('2', '8', '3', null, null, '她在我身边', null, '2023-05-13 23:08:35');
INSERT INTO `comment` VALUES ('3', '8', '3', null, null, '她根本不存在', null, '2023-05-13 23:09:37');
INSERT INTO `comment` VALUES ('4', '8', '3', null, null, '她在吗', null, '2023-05-13 23:11:40');
INSERT INTO `comment` VALUES ('5', '8', '2', null, null, '她', null, '2023-05-13 23:52:58');
INSERT INTO `comment` VALUES ('6', '8', '1', null, null, '忘了吧', null, '2023-05-13 23:53:29');
INSERT INTO `comment` VALUES ('7', '8', '3', null, null, '她是公主', null, '2023-05-13 23:56:43');
INSERT INTO `comment` VALUES ('8', '9', '3', null, null, '她吗', null, '2023-05-14 00:02:54');
INSERT INTO `comment` VALUES ('10', '8', '1', null, null, '啊啊啊', null, '2023-05-18 00:41:58');
INSERT INTO `comment` VALUES ('11', '8', '4', null, null, '身无一物', null, '2023-05-18 19:41:44');
INSERT INTO `comment` VALUES ('12', '9', '5', null, null, '', null, '2023-05-26 23:18:42');
INSERT INTO `comment` VALUES ('13', '9', '6', null, null, '别这么消极兄弟，往前看！', null, '2023-05-27 02:42:27');
INSERT INTO `comment` VALUES ('14', '13', '6', null, null, '如果你觉得你不幸的话，你可以多看看其他人，看远一点。\n', null, '2023-05-27 02:45:26');
INSERT INTO `comment` VALUES ('15', '8', '6', null, null, '人生就像一个烂透的游戏，可我除了玩下去，我别无选择。', null, '2023-05-27 02:47:01');

-- ----------------------------
-- Table structure for discuss
-- ----------------------------
DROP TABLE IF EXISTS `discuss`;
CREATE TABLE `discuss` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `content` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `type` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `commentCounts` int DEFAULT NULL,
  `score` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of discuss
-- ----------------------------
INSERT INTO `discuss` VALUES ('1', '8', '看我', '我是谁？', '2', '1', '2023-05-13 16:02:31', '3', '11');
INSERT INTO `discuss` VALUES ('2', '8', '我', '123', '1', '1', '2023-05-13 16:04:00', '1', '8');
INSERT INTO `discuss` VALUES ('3', '9', '君莫笑', '曾经', '1', '1', '2023-05-13 18:53:30', '6', '4');
INSERT INTO `discuss` VALUES ('4', '8', '菩提本无树，明镜亦非台', '。。。', '1', '1', '2023-05-18 15:50:09', '1', '0');
INSERT INTO `discuss` VALUES ('5', '9', '曲尽人终散', '沉舟侧畔千帆过，病树前头万木春', '1', '1', '2023-05-26 23:18:34', '1', '0');
INSERT INTO `discuss` VALUES ('6', '8', '这个世界它很无趣啊！', '人活着的意义是什么？', '1', '1', '2023-05-27 02:41:15', '3', '0');

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `oldFileName` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `newFileName` varchar(300) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `ext` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `path` varchar(300) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `size` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `type` varchar(120) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `isImg` varchar(8) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `downcounts` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES ('14', '8', 'preview.jpg', '260e0f9c-915c-4cc5-968a-febb02bcaa53.jpg', '.jpg', '/img/260e0f9c-915c-4cc5-968a-febb02bcaa53.jpg', '797830', 'image/jpeg', '是', '0');
INSERT INTO `file` VALUES ('15', '8', 'preview.jpg', '9b7dc07c-4c4f-4a0f-8b92-976a397996f4.jpg', '.jpg', '/img/9b7dc07c-4c4f-4a0f-8b92-976a397996f4.jpg', '797830', 'image/jpeg', '是', '0');
INSERT INTO `file` VALUES ('16', '8', '冰冷带有至于色彩的光－YS070511_爱给网_aigei_com.mp3', '46c6de36-619e-4e2e-acb7-492b84352831.mp3', '.mp3', '/img/46c6de36-619e-4e2e-acb7-492b84352831.mp3', '187649', 'audio/mpeg', '否', '0');
INSERT INTO `file` VALUES ('17', '9', '美好之光-游戏技能-治愈治疗恢复_爱给网_aigei_com.mp3', '178c9c91-9aa3-4851-8485-03696c031d7a.mp3', '.mp3', '/img/178c9c91-9aa3-4851-8485-03696c031d7a.mp3', '61220', 'audio/mpeg', '否', '0');
INSERT INTO `file` VALUES ('18', '10', '3.jpg', '01bd6f33-f4fc-4e75-8772-6c469f520cd9.jpg', '.jpg', '/img/01bd6f33-f4fc-4e75-8772-6c469f520cd9.jpg', '654045', 'image/jpeg', '是', '0');
INSERT INTO `file` VALUES ('19', '8', '1.png', 'aeb00c01-1381-4bef-b1a7-e787628e15b9.png', '.png', '/img/aeb00c01-1381-4bef-b1a7-e787628e15b9.png', '717449', 'image/png', '是', '0');
INSERT INTO `file` VALUES ('20', '8', '3.jpg', '2d92103d-e086-4e2d-b27b-80f980131a06.jpg', '.jpg', '/img/2d92103d-e086-4e2d-b27b-80f980131a06.jpg', '654045', 'image/jpeg', '是', '0');
INSERT INTO `file` VALUES ('21', '8', '2.jpg', 'ce7a21df-bce7-455f-8d57-983dee68fa45.jpg', '.jpg', '/img/ce7a21df-bce7-455f-8d57-983dee68fa45.jpg', '797830', 'image/jpeg', '是', '0');
INSERT INTO `file` VALUES ('22', '8', '3.jpg', '8428ee36-0559-4616-9467-4523e71bbac5.jpg', '.jpg', '/img/8428ee36-0559-4616-9467-4523e71bbac5.jpg', '654045', 'image/jpeg', '是', '0');
INSERT INTO `file` VALUES ('23', '8', '22.jpg', 'b36903a6-e73f-47b0-9304-df0789e8ab8b.jpg', '.jpg', '/img/b36903a6-e73f-47b0-9304-df0789e8ab8b.jpg', '237578', 'image/jpeg', '是', '0');
INSERT INTO `file` VALUES ('24', '8', '1.mp4', '46e7a479-75fd-464f-82f7-7fa703930471.mp4', '.mp4', '/img/46e7a479-75fd-464f-82f7-7fa703930471.mp4', '0', 'video/mp4', '否', '0');
INSERT INTO `file` VALUES ('25', '8', '1.zip', 'e208dbea-a9eb-4505-98bf-531d5bb2e02a.zip', '.zip', '/img/e208dbea-a9eb-4505-98bf-531d5bb2e02a.zip', '144', 'application/x-zip-compressed', '否', '0');
INSERT INTO `file` VALUES ('26', '8', '日出 _爱给网_aigei_com.mp4', 'cc68c130-2b6a-4a3b-ad2d-1b906231bb1e.mp4', '.mp4', '/img/cc68c130-2b6a-4a3b-ad2d-1b906231bb1e.mp4', '3755034', 'video/mp4', '否', '0');
INSERT INTO `file` VALUES ('27', '8', 'spritesaurus作品01.mp4', '78d647b9-846b-4476-9442-aaa9a0770425.mp4', '.mp4', '/img/78d647b9-846b-4476-9442-aaa9a0770425.mp4', '95838029', 'video/mp4', '否', '0');
INSERT INTO `file` VALUES ('28', '8', '烟 蓝色烟雾 云_爱给网_aigei_com.mp4', '7c8d4f31-105d-420c-ace3-a4c2f6cfeed7.mp4', '.mp4', '/img/7c8d4f31-105d-420c-ace3-a4c2f6cfeed7.mp4', '12736433', 'video/mp4', '否', '0');
INSERT INTO `file` VALUES ('29', '8', '星空银光粒子_爱给网_aigei_com.mp4', '6239fdbf-2706-4d6d-83ca-efd5db9a4a56.mp4', '.mp4', '/img/6239fdbf-2706-4d6d-83ca-efd5db9a4a56.mp4', '35485211', 'video/mp4', '否', '0');
INSERT INTO `file` VALUES ('30', '8', '日出 _爱给网_aigei_com.mp4', '1b38c6da-3891-4ce4-b755-ef804890b967.mp4', '.mp4', '/img/1b38c6da-3891-4ce4-b755-ef804890b967.mp4', '3755034', 'video/mp4', '否', '0');

-- ----------------------------
-- Table structure for game
-- ----------------------------
DROP TABLE IF EXISTS `game`;
CREATE TABLE `game` (
  `id` int NOT NULL AUTO_INCREMENT,
  `FileName` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `pic` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `size` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `downcounts` int DEFAULT NULL,
  `path` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of game
-- ----------------------------
INSERT INTO `game` VALUES ('1', '美少女', '/aimg/16851088165431.png', '797830', '2d素材', '0', '/game/260e0f9c-915c-4cc5-968a-febb02bcaa53.jpg', '2023-05-26 21:46:57');

-- ----------------------------
-- Table structure for model
-- ----------------------------
DROP TABLE IF EXISTS `model`;
CREATE TABLE `model` (
  `id` int NOT NULL AUTO_INCREMENT,
  `FileName` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `pic` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `size` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `downcounts` int DEFAULT NULL,
  `path` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of model
-- ----------------------------

-- ----------------------------
-- Table structure for music
-- ----------------------------
DROP TABLE IF EXISTS `music`;
CREATE TABLE `music` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fileName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `size` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `downcounts` int DEFAULT NULL,
  `path` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of music
-- ----------------------------
INSERT INTO `music` VALUES ('1', '冰', '187649', '影视音效', '2', '/img/46c6de36-619e-4e2e-acb7-492b84352831.mp3', '2023-05-07 23:50:35');
INSERT INTO `music` VALUES ('2', '曾经', '61220', '免费商用', '1', '/img/178c9c91-9aa3-4851-8485-03696c031d7a.mp3', '2023-05-07 23:50:35');
INSERT INTO `music` VALUES ('3', '幻昼', '61220', '影视音效', '0', '/img/178c9c91-9aa3-4851-8485-03696c031d7a.mp3', '2023-05-07 23:53:43');
INSERT INTO `music` VALUES ('4', '光', '61220', '实时音效', '3', '/img/178c9c91-9aa3-4851-8485-03696c031d7a.mp3', '2023-05-08 00:00:49');
INSERT INTO `music` VALUES ('5', '影', '187649', '免费商用', '0', '/img/46c6de36-619e-4e2e-acb7-492b84352831.mp3', '2023-05-08 00:05:44');
INSERT INTO `music` VALUES ('10', '冷', '187649', '影视音效', '0', '/img/46c6de36-619e-4e2e-acb7-492b84352831.mp3', '2023-05-16 03:21:50');
INSERT INTO `music` VALUES ('11', '冰冷的光', '187649', '音效作品', '10', '/mp3/46c6de36-619e-4e2e-acb7-492b84352831.mp3', '2023-05-18 13:45:47');
INSERT INTO `music` VALUES ('12', '治愈之光', '61220', '音效作品', '2', '/mp3/178c9c91-9aa3-4851-8485-03696c031d7a.mp3', '2023-05-18 13:48:19');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` int NOT NULL DEFAULT '1',
  `creatTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('8', 'justlie', '123', '123456789', '123@qq.com', '/avatar/16841552275433.jpg', '1', '2023-05-06 20:34:53');
INSERT INTO `user` VALUES ('9', 'admin', '123', '123', '123', '/img/user.jpg', '1', '2023-05-07 13:38:42');
INSERT INTO `user` VALUES ('11', 'dawnl', '123', '1234567', '456@qq.com', '/avatar/16845167197052.jpg', '1', '2023-05-07 22:05:08');
INSERT INTO `user` VALUES ('13', 'forget', '123', '1234567', '456@qq.com', '/img/user.jpg', '1', '2023-05-07 22:05:28');
INSERT INTO `user` VALUES ('14', 'illusion', '123', '1234567', '456@qq.com', '/img/user.jpg', '1', '2023-05-07 22:05:36');

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fileName` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `size` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `downcounts` int DEFAULT NULL,
  `path` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of video
-- ----------------------------
INSERT INTO `video` VALUES ('1', '日出', '3755034', '高清实拍', '0', '/img/cc68c130-2b6a-4a3b-ad2d-1b906231bb1e.mp4', '2023-05-16 03:29:29');
INSERT INTO `video` VALUES ('2', '烟', '12736433', '后期特效', '0', '/img/7c8d4f31-105d-420c-ace3-a4c2f6cfeed7.mp4', '2023-05-16 03:39:45');
INSERT INTO `video` VALUES ('3', '宝可梦', '95838029', '免费商用', '1', '/mp4/78d647b9-846b-4476-9442-aaa9a0770425.mp4', '2023-05-26 19:10:38');
INSERT INTO `video` VALUES ('4', '光粒子', '35485211', '后期特效', '0', '/mp4/6239fdbf-2706-4d6d-83ca-efd5db9a4a56.mp4', '2023-06-04 08:59:57');
