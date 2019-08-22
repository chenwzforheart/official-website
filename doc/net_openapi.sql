/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50719
Source Host           : 127.0.0.1:3306
Source Database       : net_openapi

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2019-08-22 16:58:24
*/
-- ----------------------------
-- Create Database
-- ----------------------------
CREATE DATABASE IF NOT EXISTS net_openapi DEFAULT CHARACTER SET = utf8mb4;

Use net_openapi;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for open_api
-- ----------------------------
DROP TABLE IF EXISTS `open_api`;
CREATE TABLE `open_api` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_name` varchar(100) DEFAULT NULL,
  `industry` varchar(200) DEFAULT NULL,
  `company` varchar(200) DEFAULT NULL,
  `portal` varchar(200) DEFAULT NULL,
  `api_doc` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of open_api
-- ----------------------------
INSERT INTO `open_api` VALUES ('1', '淘宝开放平台', '电商', '阿里巴巴', 'https://open.taobao.com/', 'https://open.taobao.com/api.htm?docId=285&docType=2', '2019-08-22 09:59:42');
INSERT INTO `open_api` VALUES ('2', '聚石塔', '零售云', '阿里巴巴', 'https://lingshouyun.taobao.com/', '', '2019-08-22 11:00:08');
INSERT INTO `open_api` VALUES ('3', '腾讯开放平台', '社交', '腾讯', 'https://open.tencent.com/', null, '2019-08-22 10:20:58');
INSERT INTO `open_api` VALUES ('4', '微信开放平台', '社交', '腾讯', 'https://open.weixin.qq.com/', 'https://developers.weixin.qq.com/doc/', '2019-08-22 10:24:55');
INSERT INTO `open_api` VALUES ('5', '百度数据开放平台', '搜索', '百度', 'https://open.baidu.com/#/', null, '2019-08-22 10:30:25');
INSERT INTO `open_api` VALUES ('6', '百度统计', '搜索', '百度', 'https://tongji.baidu.com', 'https://tongji.baidu.com/open/api/more', '2019-08-22 10:49:25');
INSERT INTO `open_api` VALUES ('7', '京东宙斯开放平台', '电商', '京东', 'http://open.jd.com', 'http://open.jd.com/home/home#/doc/api?apiCateId=62&apiId=2821&apiName=jingdong.category.read.findValuesByAttrIdUnlimit', '2019-08-22 10:54:52');
INSERT INTO `open_api` VALUES ('8', '京东物流技术开放平台', '物流', '京东', 'https://open.jdwl.com/', 'https://open.jdwl.com/api/apiList.html', '2019-08-22 11:07:45');
INSERT INTO `open_api` VALUES ('9', '拼多多开放平台', '电商', '拼多多', 'https://open.pinduoduo.com/#/index', 'https://open.pinduoduo.com/#/apidocument', '2019-08-22 11:18:59');
INSERT INTO `open_api` VALUES ('10', '苏宁开放服务', '电商', '苏宁', 'https://open.suning.com/', 'https://open.suning.com/ospos/apipage/toApiListMenu.do', '2019-08-22 11:22:53');
