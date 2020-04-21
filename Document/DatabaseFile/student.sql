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

 Date: 21/04/2020 18:27:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` decimal(10,0) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `school_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
BEGIN;
INSERT INTO `student` VALUES (1, '李克强', '男', 1);
INSERT INTO `student` VALUES (2, '朱自清', '男', 1);
INSERT INTO `student` VALUES (3, '屠呦呦', '女', 1);
INSERT INTO `student` VALUES (4, '胡锦涛', '男', 2);
INSERT INTO `student` VALUES (5, '邓稼先', '男', 2);
INSERT INTO `student` VALUES (6, '谷超豪', '男', 3);
INSERT INTO `student` VALUES (7, '白春礼', '男', 3);
INSERT INTO `student` VALUES (8, '江泽民', '男', 4);
INSERT INTO `student` VALUES (9, '钱学森', '男', 4);
INSERT INTO `student` VALUES (10, '陈独秀', '男', 5);
INSERT INTO `student` VALUES (11, '竺可桢', '男', 5);
INSERT INTO `student` VALUES (12, '李岚清', '男', 6);
INSERT INTO `student` VALUES (13, '王沪宁', '男', 6);
INSERT INTO `student` VALUES (14, '汪洋', '男', 7);
INSERT INTO `student` VALUES (15, '朱清时', '男', 7);
INSERT INTO `student` VALUES (16, '潘建伟', '男', 7);
INSERT INTO `student` VALUES (17, '厉声教', '男', 8);
INSERT INTO `student` VALUES (18, '吴健雄', '男', 8);
INSERT INTO `student` VALUES (19, '丁颖', '男', 9);
INSERT INTO `student` VALUES (20, '许宁生', '男', 9);
INSERT INTO `student` VALUES (21, '林若', '男', 9);
INSERT INTO `student` VALUES (22, '周济', '男', 10);
INSERT INTO `student` VALUES (23, '李娜', '女', 10);
INSERT INTO `student` VALUES (24, '贝时璋', '男', 10);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
