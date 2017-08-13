/*
Navicat MySQL Data Transfer

Source Server         : ding
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : javatribemanagesystempc

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2017-08-13 09:51:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` varchar(32) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL DEFAULT '',
  `headImage` varchar(255) DEFAULT '',
  `sex` varchar(255) DEFAULT '',
  `grade` varchar(255) DEFAULT '',
  `department` varchar(255) DEFAULT '',
  `phone` varchar(255) DEFAULT '',
  `QQ` varchar(255) DEFAULT '',
  `sign` varchar(255) DEFAULT '',
  `birthday` varchar(255) DEFAULT '',
  `address` varchar(255) DEFAULT NULL,
  `isManager` varchar(255) NOT NULL DEFAULT '否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('141545341', '141545341', '丁永辉', '141545341.jpg', '男', '大一', '计算机科学与技术系', '18826077893', '1214014477', '啦啦啦', '1995年7月', '乌托邦', '是');
INSERT INTO `tb_user` VALUES ('141545342', null, '刘德华', '141545342.jpg', '男', '大二', '金融系', '18826077893', '1214914477', '呜呜呜', '1977年8月', '1977年8月', '否');
