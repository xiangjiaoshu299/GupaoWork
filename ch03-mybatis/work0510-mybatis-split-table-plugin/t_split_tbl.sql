/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : t_split_tbl

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 21/05/2020 22:33:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_fee_202001
-- ----------------------------
DROP TABLE IF EXISTS `t_fee_202001`;
CREATE TABLE `t_fee_202001`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fee_amount` double NULL DEFAULT NULL,
  `fee_date` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_fee_202001
-- ----------------------------
INSERT INTO `t_fee_202001` VALUES (3, 100, '2020-01-15 00:00:00');
INSERT INTO `t_fee_202001` VALUES (4, 100, '2020-01-15 08:00:00');

-- ----------------------------
-- Table structure for t_fee_202002
-- ----------------------------
DROP TABLE IF EXISTS `t_fee_202002`;
CREATE TABLE `t_fee_202002`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fee_amount` double NULL DEFAULT NULL,
  `fee_date` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_fee_202002
-- ----------------------------
INSERT INTO `t_fee_202002` VALUES (2, 100, '2020-02-15 00:00:00');
INSERT INTO `t_fee_202002` VALUES (3, 100, '2020-02-15 08:00:00');

-- ----------------------------
-- Table structure for t_fee_202003
-- ----------------------------
DROP TABLE IF EXISTS `t_fee_202003`;
CREATE TABLE `t_fee_202003`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fee_amount` double NULL DEFAULT NULL,
  `fee_date` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_fee_202003
-- ----------------------------
INSERT INTO `t_fee_202003` VALUES (2, 100, '2020-03-15 00:00:00');
INSERT INTO `t_fee_202003` VALUES (3, 100, '2020-03-15 08:00:00');

-- ----------------------------
-- Table structure for t_fee_202004
-- ----------------------------
DROP TABLE IF EXISTS `t_fee_202004`;
CREATE TABLE `t_fee_202004`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fee_amount` double NULL DEFAULT NULL,
  `fee_date` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_fee_202004
-- ----------------------------
INSERT INTO `t_fee_202004` VALUES (2, 100, '2020-04-15 00:00:00');
INSERT INTO `t_fee_202004` VALUES (3, 100, '2020-04-15 08:00:00');

SET FOREIGN_KEY_CHECKS = 1;
