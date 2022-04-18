/*
 Navicat Premium Data Transfer

 Source Server         : tencent.docker
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : 82.157.149.211:3306
 Source Schema         : hayishu_user

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 22/12/2021 16:26:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(11) NOT NULL COMMENT '手机号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `identity` int(1) NOT NULL COMMENT '身份标识,0管理员,1普通用户',
  `addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `money` decimal(10, 2) NULL DEFAULT NULL COMMENT '余额',
  `sales` int(10) NULL DEFAULT NULL COMMENT '出售量',
  `fans` int(10) NULL DEFAULT NULL COMMENT '粉丝数量',
  `time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (123, 'lyl', '123456', NULL, 1, NULL, 0.00, 0, 3, '2021-12-20 15:58:39');
INSERT INTO `user` VALUES (123456, 'admin', '123456', NULL, 0, NULL, NULL, NULL, 0, '2021-12-07 19:25:17');
INSERT INTO `user` VALUES (13112345678, '清风徐来', '12345678', NULL, 1, NULL, 0.00, 0, 0, NULL);
INSERT INTO `user` VALUES (13187654321, 'Test', '12345678', '男', 1, NULL, 29.00, 2, 1, '2020-10-06 16:53:12');
INSERT INTO `user` VALUES (13718936215, 'll', '123123123', NULL, 1, NULL, 0.00, 0, 0, NULL);
INSERT INTO `user` VALUES (15634376596, 'Zjh', '123456', '男', 1, '9-530', 6.66, 2, 3, '2021-12-20 15:58:49');
INSERT INTO `user` VALUES (15634381006, 'Zzh', 'zzh1234', '男', 1, NULL, 0.00, 0, 1, '2020-06-10 10:23:00');
INSERT INTO `user` VALUES (15694410811, 'qdmxy', '12345678', '男', 1, NULL, 0.00, 0, 0, '2021-12-20 15:55:48');
INSERT INTO `user` VALUES (16601551599, 'pzx', '2222', '男', 1, 'bit', 22222.00, 0, 0, '2021-12-09 10:47:27');
INSERT INTO `user` VALUES (16601553008, 'liulu', '19981229', NULL, 1, NULL, 0.00, 0, 0, NULL);
INSERT INTO `user` VALUES (18663184965, 'Zy', '123456', '女', 1, '', 17.00, 2, 1, '2021-12-07 14:52:25');
INSERT INTO `user` VALUES (18811083021, 'lpk', '11111111', NULL, 1, NULL, 0.00, 0, 0, NULL);
INSERT INTO `user` VALUES (19936079809, 'pzx_seller', '2222', '女', 1, 'balabala', 666666.00, 1, 0, '2021-12-09 10:47:30');

SET FOREIGN_KEY_CHECKS = 1;
