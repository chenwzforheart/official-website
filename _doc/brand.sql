/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50719
Source Host           : 127.0.0.1:3306
Source Database       : net_openapi

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2019-11-25 18:05:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(255) DEFAULT NULL,
  `chinese_name` varchar(255) DEFAULT NULL,
  `portal` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `quote` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of brand
-- ----------------------------
INSERT INTO `brand` VALUES ('1', 'Adidas', null, 'https://www.adidas.com', '服饰', null, null, null, '2019-11-25 10:50:10');
INSERT INTO `brand` VALUES ('2', 'Nike', null, 'https://www.nike.com/', '服饰', null, null, null, '2019-11-25 10:51:31');
INSERT INTO `brand` VALUES ('3', 'Gucci', '古驰', 'https://www.gucci.cn/zh/', '奢侈品', null, null, null, '2019-11-25 11:09:40');
INSERT INTO `brand` VALUES ('4', 'Coach', '蔻驰', 'https://coach.com', '奢侈品', null, '美国', null, '2019-11-25 11:26:29');
INSERT INTO `brand` VALUES ('5', 'VALENTINO', '华伦天奴', 'https://www.valentinodaydream.cn/', '奢侈品', null, null, null, '2019-11-25 12:23:45');
INSERT INTO `brand` VALUES ('6', 'Dior', '迪奥', 'https://www.dior.com', '服饰', null, null, null, '2019-11-25 12:27:36');
INSERT INTO `brand` VALUES ('7', 'victoria\'s secret', '维多利亚的秘密', 'https://www.victoriassecret.cn', '内衣', null, null, null, '2019-11-25 16:20:52');
INSERT INTO `brand` VALUES ('8', 'Gap', null, 'https://www.gap.cn/', '服饰', null, null, null, '2019-11-25 16:22:29');
INSERT INTO `brand` VALUES ('9', 'LANCOME', '兰蔻', 'https://www.lancome.com.cn/', '彩妆', null, null, null, '2019-11-25 16:25:07');
INSERT INTO `brand` VALUES ('10', 'puma', '彪马', 'https://cn.puma.com/', '运动服饰', null, null, null, '2019-11-25 16:33:38');
INSERT INTO `brand` VALUES ('11', 'Reebok', '锐步', 'https://www.reebok.com.cn/', '运动服饰', null, null, null, '2019-11-25 16:35:19');
INSERT INTO `brand` VALUES ('12', 'ROGER&GALLET', '香邂格蕾', 'http://www.roger-gallet.com.cn/', '香氛', null, null, null, '2019-11-25 16:40:08');
INSERT INTO `brand` VALUES ('13', 'RALPH LAUREN', '拉夫劳伦', 'https://www.ralphlauren.cn/', '服饰', null, null, null, '2019-11-25 16:44:02');
INSERT INTO `brand` VALUES ('14', 'ZWILLING', '双立人', 'https://www.zwilling.com.cn/', '厨具', null, null, null, '2019-11-25 16:46:33');
INSERT INTO `brand` VALUES ('15', 'ESTEE LAUDER', '雅诗兰黛', 'https://www.esteelauder.com.cn/', '彩妆', null, null, null, '2019-11-25 16:48:51');
INSERT INTO `brand` VALUES ('16', 'MAC', '魅可', 'https://www.maccosmetics.com.cn/', '彩妆', null, null, null, '2019-11-25 16:51:55');
INSERT INTO `brand` VALUES ('17', 'LAB SERIES', '朗仕', 'https://www.labseries.com.cn/', '男士护肤', null, null, null, '2019-11-25 16:54:53');
INSERT INTO `brand` VALUES ('18', 'ORIGINS', '悦木之源', 'https://www.origins.com.cn/', null, null, null, null, '2019-11-25 17:04:17');
INSERT INTO `brand` VALUES ('19', 'LA MER', '海蓝之谜', 'https://www.lamer.com.cn/', '护肤', null, null, null, '2019-11-25 17:05:17');
INSERT INTO `brand` VALUES ('20', 'LEVI\'S', '李维斯', 'https://www.levi.com.cn/', '服饰', null, null, null, '2019-11-25 17:10:39');
INSERT INTO `brand` VALUES ('21', 'MICHAEL KORS', null, 'https://www.michaelkors.cn/', '奢侈品', null, null, null, '2019-11-25 17:19:39');
INSERT INTO `brand` VALUES ('22', 'ESPRIT', null, 'https://www.esprit.cn/', '服饰', null, null, null, '2019-11-25 17:23:12');
INSERT INTO `brand` VALUES ('23', 'Rothy\'s', null, 'https://www.rothys.com.cn/', '女鞋', null, null, null, '2019-11-25 17:30:25');
INSERT INTO `brand` VALUES ('24', 'Speedo', null, 'https://www.speedo.com.cn/', '运动服饰', null, null, null, '2019-11-25 17:33:01');
INSERT INTO `brand` VALUES ('25', 'Abercrombie & Fitch', null, 'https://www.abercrombie.cn/', '服饰', null, null, null, '2019-11-25 17:36:21');
INSERT INTO `brand` VALUES ('26', 'GODIVA', '歌帝梵', 'https://www.godiva.cn/', '食品', null, null, null, '2019-11-25 17:38:10');
INSERT INTO `brand` VALUES ('27', 'Hollister Co.', 'HCO', 'https://www.hollisterco.cn/', '服饰', null, null, null, '2019-11-25 17:41:07');
INSERT INTO `brand` VALUES ('28', 'SKECHERS', '斯凯奇', 'https://store.skechers.cn/', '鞋服', null, null, null, '2019-11-25 17:45:37');
