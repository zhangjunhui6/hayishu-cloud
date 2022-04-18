/*
 Navicat Premium Data Transfer

 Source Server         : tencent.docker
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : 82.157.149.211:3306
 Source Schema         : hayishu_order

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 22/12/2021 16:27:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一id',
  `oid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `bid` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '书本id',
  `isResale` int(10) NULL DEFAULT 0 COMMENT '是否转售，1:表示已转售，0:未转售',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `bid`(`bid`) USING BTREE,
  INDEX `oid`(`oid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES (34, 'DD202010061651490003', 11, 0);
INSERT INTO `orderitem` VALUES (38, 'DD202111291633460001', 12, 0);
INSERT INTO `orderitem` VALUES (39, 'DD202111291648220003', 3, 0);
INSERT INTO `orderitem` VALUES (40, 'DD202112201509130001', 24, 0);
INSERT INTO `orderitem` VALUES (41, 'DD202112201530590004', 13, 0);
INSERT INTO `orderitem` VALUES (42, 'DD202112201557240007', 3, 0);
INSERT INTO `orderitem` VALUES (43, 'DD202112201559220009', 2, 0);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `buyerId` bigint(11) NULL DEFAULT NULL,
  `sellerId` bigint(11) NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(10) NULL DEFAULT NULL COMMENT '0:待支付；1:已支付(都未确认)；2:只有卖家确定；3:只有买家确定  4:已完成(双方都已确定，此时买家可转售)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `buyerId`(`buyerId`) USING BTREE,
  INDEX `sellerId`(`sellerId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('DD202010061651490003', 15634376596, 13187654321, '2020-10-06 16:53:12', '您好', 1);
INSERT INTO `orders` VALUES ('DD202111291633460001', 15634376596, 13187654321, '2021-11-29 16:33:46', '', 0);
INSERT INTO `orders` VALUES ('DD202111291648220003', 15634376596, 18663184965, '2021-11-29 16:48:22', '', 0);
INSERT INTO `orders` VALUES ('DD202112201509130001', 15634376596, 16601553008, '2021-12-20 15:09:13', '', 0);
INSERT INTO `orders` VALUES ('DD202112201530590004', 18811083021, 18663184965, '2021-12-20 15:30:59', '1', 0);
INSERT INTO `orders` VALUES ('DD202112201557240007', 15694410811, 18663184965, '2021-12-20 15:57:24', '', 0);
INSERT INTO `orders` VALUES ('DD202112201559220009', 15694410811, 15634376596, '2021-12-20 15:59:22', '', 0);

SET FOREIGN_KEY_CHECKS = 1;
