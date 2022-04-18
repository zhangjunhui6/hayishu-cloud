/*
 Navicat Premium Data Transfer

 Source Server         : tencent.docker
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : 82.157.149.211:3306
 Source Schema         : hayishu_cart

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 22/12/2021 16:30:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一id',
  `uid` bigint(11) NULL DEFAULT NULL COMMENT '用户id',
  `bid` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '书本id',
  `status` int(10) NULL DEFAULT NULL COMMENT '有效性:1有效，0无效',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  INDEX `bid`(`bid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES (14, 13112345678, 12, 0);
INSERT INTO `cart` VALUES (41, 16601551599, 10, 0);
INSERT INTO `cart` VALUES (42, 16601551599, 11, 1);
INSERT INTO `cart` VALUES (43, 16601551599, 20, 1);

SET FOREIGN_KEY_CHECKS = 1;
