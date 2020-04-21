/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : eeriness

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 21/04/2020 18:27:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `date` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of school
-- ----------------------------
BEGIN;
INSERT INTO `school` VALUES (1, '北京大学', '1898');
INSERT INTO `school` VALUES (2, '清华大学', '1911');
INSERT INTO `school` VALUES (3, '中国科学院大学', '1978');
INSERT INTO `school` VALUES (4, '上海交通大学', '1896');
INSERT INTO `school` VALUES (5, '浙江大学', '1897');
INSERT INTO `school` VALUES (6, '复旦大学', '1905');
INSERT INTO `school` VALUES (7, '中国科学技术大学', '1958');
INSERT INTO `school` VALUES (8, '南京大学', '1902');
INSERT INTO `school` VALUES (9, '中山大学', '1924');
INSERT INTO `school` VALUES (10, '华中科技大学', '1952');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
