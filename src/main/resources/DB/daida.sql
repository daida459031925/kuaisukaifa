/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50717
Source Host           : 127.0.0.1:3306
Source Database       : daida

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-06-01 18:30:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for check-in-table
-- ----------------------------
DROP TABLE IF EXISTS `check-in-table`;
CREATE TABLE `check-in-table` (
  `detailed_information` varchar(255) DEFAULT '' COMMENT '详细地址一般会说是神什么附近',
  `latitude` varchar(255) DEFAULT '' COMMENT '维度这个是谷歌地图的',
  `longitude` varchar(255) DEFAULT '' COMMENT '精度这个是谷歌地图的',
  `street_number` varchar(255) DEFAULT '' COMMENT '地理位置xx街道xx号',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `openid` varchar(255) NOT NULL DEFAULT '' COMMENT 'openid和user关联对应',
  `add_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of check-in-table
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `Date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `dianhua` varchar(11) NOT NULL,
  `xingbei` varchar(1) NOT NULL,
  `shenfenzheng` varchar(255) NOT NULL,
  `pws` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('6', '6', '2018-01-03 13:54:06', '6', '6', '6', '6');

-- ----------------------------
-- Table structure for weuser
-- ----------------------------
DROP TABLE IF EXISTS `weuser`;
CREATE TABLE `weuser` (
  `openid` varchar(255) NOT NULL DEFAULT '' COMMENT '微信对应的openid  唯一值',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '微信名字',
  `gender` varchar(1) DEFAULT NULL COMMENT '1男   0女 性别',
  `language` varchar(255) DEFAULT NULL COMMENT '语言',
  `city` varchar(255) DEFAULT NULL COMMENT '市区',
  `province` varchar(255) DEFAULT NULL COMMENT '省份',
  `country` varchar(255) DEFAULT NULL COMMENT '国家',
  `avatar_url` longtext COMMENT '头像',
  `phone` varchar(11) DEFAULT NULL COMMENT '电话(手机)',
  `add_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `up_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weuser
-- ----------------------------
INSERT INTO `weuser` VALUES ('or6Xl5XRfpcYL0lqHRqpnx_2iA20', 'HSP', '1', 'zh_CN', '黄石', '湖北', '中国', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIMXibIkdO2qQuwzcH6pmax6C5zctReY4Qhqfmbbq9A88Av4RcianREREsBDNWnuXKTOfSPibqicricjHw/132', '18062284509', '2018-06-01 14:54:41', '2018-06-01 14:54:57');
