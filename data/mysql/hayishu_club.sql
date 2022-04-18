/*
 Navicat Premium Data Transfer

 Source Server         : tencent.docker
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : 82.157.149.211:3306
 Source Schema         : hayishu_club

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 22/12/2021 16:27:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for follow
-- ----------------------------
DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一id',
  `uid` bigint(11) NOT NULL COMMENT '用户',
  `followId` bigint(11) NOT NULL COMMENT '关注的人',
  `status` int(10) NULL DEFAULT NULL COMMENT '是否取消关注：1是，0否',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  INDEX `followId`(`followId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of follow
-- ----------------------------
INSERT INTO `follow` VALUES (1, 15634376596, 18663184965, 0);
INSERT INTO `follow` VALUES (2, 18663184965, 15634376596, 0);
INSERT INTO `follow` VALUES (3, 13187654321, 15634381006, 0);
INSERT INTO `follow` VALUES (4, 13187654321, 15634376596, 0);
INSERT INTO `follow` VALUES (5, 15634376596, 13187654321, 0);
INSERT INTO `follow` VALUES (7, 123, 123456, 1);
INSERT INTO `follow` VALUES (8, 18811083021, 123, 0);
INSERT INTO `follow` VALUES (9, 13718936215, 123, 0);
INSERT INTO `follow` VALUES (10, 15694410811, 123, 0);
INSERT INTO `follow` VALUES (11, 15694410811, 15634376596, 0);

SET FOREIGN_KEY_CHECKS = 1;
