/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50719
Source Host           : 127.0.0.1:3306
Source Database       : net_openapi

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2019-11-25 18:05:26
*/

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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4;

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
INSERT INTO `open_api` VALUES ('11', '支付宝开放平台', '支付', '阿里巴巴', 'https://open.alipay.com/platform/home.htm', 'https://docs.open.alipay.com/catalog', '2019-08-23 09:30:55');
INSERT INTO `open_api` VALUES ('12', '微信支付', '支付', '腾讯', 'https://pay.weixin.qq.com/index.php/core/home/login?return_url=%2F', 'https://pay.weixin.qq.com/wiki/doc/api/index.html', '2019-08-23 09:37:38');
INSERT INTO `open_api` VALUES ('13', '银联支付', '支付', '中国银联', 'https://open.unionpay.com/tjweb/index', 'https://open.unionpay.com/tjweb/api/list', '2019-08-23 09:58:19');
INSERT INTO `open_api` VALUES ('14', '菜鸟物流', '物流', '阿里巴巴', 'https://www.cainiao.com/', 'https://global.link.cainiao.com/?spm=a21da.44372.0.0.1f5d3045xrn4y3#/homepage/api?_k=i9khfg', '2019-08-26 14:38:32');
INSERT INTO `open_api` VALUES ('15', '顺丰开放平台', '物流', '顺丰', 'https://open.sf-express.com/', 'https://open.sf-express.com/document/apinorm.html', '2019-08-28 09:56:39');
INSERT INTO `open_api` VALUES ('16', '微信公众平台', '内容', '腾讯', 'https://mp.weixin.qq.com/', 'https://developers.weixin.qq.com/doc/offiaccount/Getting_Started/Overview.html', '2019-08-29 12:32:58');
INSERT INTO `open_api` VALUES ('17', '头条号开放平台', '内容', '字节跳动', 'https://open.mp.toutiao.com', 'https://open.mp.toutiao.com/#/resource?_k=e3wcij', '2019-08-29 12:38:56');
INSERT INTO `open_api` VALUES ('18', '阿里创作平台', '内容', '阿里巴巴', 'https://weitao.taobao.com/', null, '2019-08-29 13:52:07');
INSERT INTO `open_api` VALUES ('19', '腾讯内容开放平台', '内容', '腾讯', 'https://om.qq.com/', 'https://open.om.qq.com/', '2019-08-29 13:57:15');
INSERT INTO `open_api` VALUES ('20', '微博开放平台', '内容', '微博', 'https://open.weibo.com/', 'https://open.weibo.com/wiki/%E9%A6%96%E9%A1%B5', '2019-08-29 14:07:44');
INSERT INTO `open_api` VALUES ('21', '京东内容开放平台', '内容', '京东', 'https://dr.jd.com/', 'https://dr.jd.com/page/app.html#/manual', '2019-08-29 14:54:15');
INSERT INTO `open_api` VALUES ('22', '七牛云', 'IAAS', '七牛', 'https://www.qiniu.com/', 'https://developer.qiniu.com/', '2019-08-29 17:16:34');
INSERT INTO `open_api` VALUES ('23', 'UCloud', 'IAAS', '优刻得', 'https://www.ucloud.cn/', 'https://docs.ucloud.cn', '2019-08-29 17:25:55');
INSERT INTO `open_api` VALUES ('24', '腾讯云', 'IAAS', '腾讯', 'https://cloud.tencent.com/', 'https://cloud.tencent.com/document/product', '2019-08-29 17:51:03');
INSERT INTO `open_api` VALUES ('25', '阿里云', 'IAAS', '阿里巴巴', 'https://www.aliyun.com/', 'https://help.aliyun.com/', '2019-08-29 17:29:11');
INSERT INTO `open_api` VALUES ('26', 'AWS', 'IAAS', '亚马逊', 'https://aws.amazon.com/cn/', 'https://docs.aws.amazon.com/index.html', '2019-08-29 17:44:34');
INSERT INTO `open_api` VALUES ('27', 'Azure', 'IAAS', '微软', 'https://azure.microsoft.com/zh-cn/', 'https://docs.microsoft.com/zh-cn/azure/', '2019-08-29 18:00:57');
INSERT INTO `open_api` VALUES ('28', '华为云', 'IAAS', '华为', 'https://www.huaweicloud.com/', 'https://support.huaweicloud.com/index.html', '2019-08-29 18:15:09');
INSERT INTO `open_api` VALUES ('29', '神策数据', 'PAAS', '神策数据', 'https://www.sensorsdata.cn/', 'https://www.sensorsdata.cn/manual/data_import.html', '2019-08-29 18:38:25');
INSERT INTO `open_api` VALUES ('30', '腾讯大数据', '搜索', '腾讯', 'https://data.qq.com/', 'https://data.qq.com/product/ta', '2019-08-30 10:08:15');
INSERT INTO `open_api` VALUES ('31', '企业微信', 'SAAS', '腾讯', 'https://work.weixin.qq.com/', 'https://work.weixin.qq.com/api/doc#90000/90135/90664', '2019-09-04 16:48:26');
