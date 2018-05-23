/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : school_market

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2018-05-23 09:31:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_msg
-- ----------------------------
DROP TABLE IF EXISTS `tb_msg`;
CREATE TABLE `tb_msg` (
  `M_Id` int(11) NOT NULL,
  `M_TradeId` int(11) DEFAULT NULL,
  `M_AuthorId` int(11) DEFAULT NULL,
  `M_Title` varchar(255) DEFAULT NULL,
  `M_Content` varchar(255) DEFAULT NULL,
  `M_CreateTime` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`M_Id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_msg
-- ----------------------------
INSERT INTO `tb_msg` VALUES ('1', '2', '1', '下单消息', '恭喜您成功下单，订单商品为：\"羽毛球拍\"', '1497247502647');
INSERT INTO `tb_msg` VALUES ('2', '2', '2', '订单消息', '恭喜您的商品：\"羽毛球拍\"被下单。', '1497247502656');

-- ----------------------------
-- Table structure for tb_notice
-- ----------------------------
DROP TABLE IF EXISTS `tb_notice`;
CREATE TABLE `tb_notice` (
  `N_Id` int(11) NOT NULL,
  `N_AuthorId` int(11) DEFAULT NULL,
  `N_Title` varchar(255) DEFAULT NULL,
  `N_content` varchar(500) DEFAULT NULL,
  `N_CreateTime` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`N_Id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_notice
-- ----------------------------
INSERT INTO `tb_notice` VALUES ('1', '1', '失物招领', '有同学于4月5号在食堂拾到钱包一个，里面有物品若干，请失主尽快到校广播站认领。', '1494922350006');
INSERT INTO `tb_notice` VALUES ('2', '2', '考试通知', '计算机专业的数据结构于5月7号下午2点在建筑学馆527教室进行考试，请相关同学做好考试准备。', '1494922349006');
INSERT INTO `tb_notice` VALUES ('3', '2', '征集看电影小伙伴', '征集两个小伙伴于5月7号一起去本部电影城观看《速度与激情7》，感兴趣的请联系：18202429136', '1494922348006');
INSERT INTO `tb_notice` VALUES ('4', '3', '考试通知', '计算机专业的数据结构于5月7号下午2点在建筑学馆527教室进行考试，请相关同学做好考试准备。', '1494922347006');
INSERT INTO `tb_notice` VALUES ('5', '1', '征集看电影小伙伴', '征集两个小伙伴于5月7号一起去本部电影城观看《速度与激情7》，感兴趣的请联系：18202429136', '1494922346006');
INSERT INTO `tb_notice` VALUES ('6', '1', '哈哈\ngh', '刚好回家', '1497247525172');

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `O_Id` int(11) NOT NULL,
  `O_TradeId` int(11) DEFAULT NULL,
  `O_AuthorId` int(11) DEFAULT NULL,
  `O_PayId` int(11) DEFAULT NULL,
  `O_CreateTime` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`O_Id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES ('1', '2', '2', '1', '1497247502637');

-- ----------------------------
-- Table structure for tb_trade
-- ----------------------------
DROP TABLE IF EXISTS `tb_trade`;
CREATE TABLE `tb_trade` (
  `T_Id` int(11) NOT NULL,
  `T_Title` varchar(255) DEFAULT NULL,
  `T_AuthorId` int(11) DEFAULT NULL,
  `T_PayId` int(11) DEFAULT NULL,
  `T_OriginalPrice` double DEFAULT NULL,
  `T_NowPrice` double DEFAULT NULL,
  `T_TagName` varchar(255) DEFAULT NULL,
  `T_ImgUrl` varchar(255) DEFAULT NULL,
  `T_Describe` varchar(500) DEFAULT NULL,
  `T_CreateTime` bigint(20) DEFAULT NULL,
  `T_Status` int(11) DEFAULT NULL,
  PRIMARY KEY (`T_Id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_trade
-- ----------------------------
INSERT INTO `tb_trade` VALUES ('1', '苹果手机', '1', '0', '5000', '2000', '手机相关', 'apple_phone.jpg', '八成新，还在保修期内，外观良好。', '1494863347938', '0');
INSERT INTO `tb_trade` VALUES ('2', '羽毛球拍', '2', '1', '150', '50', '体育用品', 'battledore.jpg', '上个月刚买的，最近不喜欢玩了，九成新，绝对物超所值。', '1494863337938', '4');
INSERT INTO `tb_trade` VALUES ('3', '传统民谣熟悉', '3', '0', '50', '20', '学习资料', 'book.jpg', '书本完好，无破损。', '1494863327938', '0');
INSERT INTO `tb_trade` VALUES ('4', '苹果电脑', '4', '0', '12000', '5000', '电脑相关', 'mac_computer.jpg', '七成新，配件完好，还在保修期。', '1494863326938', '0');
INSERT INTO `tb_trade` VALUES ('5', '台灯', '2', '0', '200', '50', '生活用品', 'table_lamp.jpg', '外观有点小磨损，其它方面性能都不错。', '1494863329938', '0');
INSERT INTO `tb_trade` VALUES ('6', 'gg', '1', '0', '25', '52', '学习资料', 'IMG_20170504_004027.jpg', 'gg', '1497247486162', '0');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `U_Id` int(11) NOT NULL,
  `U_Username` varchar(255) DEFAULT NULL,
  `U_Password` varchar(255) DEFAULT NULL,
  `U_Phone` varchar(255) DEFAULT NULL,
  `U_ImgUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`U_Id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'admin', 'admin', '18202429136', 'sort_avater_cluo.jpg');
INSERT INTO `tb_user` VALUES ('2', 'tom', 'admin', '18202429136', 'sort_avater_cluo.jpg');
INSERT INTO `tb_user` VALUES ('3', 'lina', 'admin', '18202429136', 'sort_clothes.jpg');
INSERT INTO `tb_user` VALUES ('4', 'sari', 'admin', '18202429136', 'sort_body.jpg');
