/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : ICIS

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 06/10/2017 19:58:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for icis_activity
-- ----------------------------
DROP TABLE IF EXISTS `icis_activity`;
CREATE TABLE `icis_activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '活动表id',
  `title` varchar(255) DEFAULT NULL COMMENT '活动名称',
  `image` varchar(500) DEFAULT NULL COMMENT '活动图片',
  `publish_time` datetime DEFAULT NULL COMMENT '活动推送时间',
  `time` varchar(255) DEFAULT NULL COMMENT '活动举行时间',
  `position` varchar(500) DEFAULT NULL COMMENT '活动举办地点',
  `content1` varchar(255) DEFAULT NULL COMMENT '活动内容1',
  `content2` varchar(255) DEFAULT NULL COMMENT '活动内容2',
  `content3` varchar(255) DEFAULT NULL COMMENT '活动内容3',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='作为ICIS的活动表使用';

-- ----------------------------
-- Records of icis_activity
-- ----------------------------
BEGIN;
INSERT INTO `icis_activity` VALUES (1, '“Merry Christmas” 圣诞大餐', '/Users/fei/Documents/Workspace/IDEAWorkspace/J2EE/ICIS/src/main/webapp/images/activity/201353115003421.jpg', '2017-10-05 18:12:09', '12月24日晚（平安夜）PM6:00-9:00', '会所西餐厅【社区活动,创意】', '精心设计圣诞精美食品。', '免费奉送圣诞礼物一份。', '现场举行大抽奖。');
INSERT INTO `icis_activity` VALUES (2, '2017寒假社会实践 活动方案', '/Users/fei/Documents/Workspace/IDEAWorkspace/J2EE/ICIS/src/main/webapp/images/activity/c03fd574882718a5f3e83e.png', '2017-10-05 18:13:30', '12月30日早 AM6:00-12:00', '圆形集合【社区活动,团结】', '集体社区清理。', '装扮自己所在区域。', '现在评比颁奖。');
INSERT INTO `icis_activity` VALUES (3, '义诊活动方案', '/Users/fei/Documents/Workspace/IDEAWorkspace/J2EE/ICIS/src/main/webapp/images/activity/175f3e42642794c59922584ddfcae08f.jpg', '2017-10-05 18:14:22', '1月09日 全天', '圆形广场【社区活动,义诊】', '免费领取就诊卷。', '排队就诊。', 'PM 5:00-6:30领取报告单。');
COMMIT;

-- ----------------------------
-- Table structure for icis_appointment_item
-- ----------------------------
DROP TABLE IF EXISTS `icis_appointment_item`;
CREATE TABLE `icis_appointment_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '预约项目id',
  `service_photo` varchar(500) DEFAULT NULL COMMENT '预约项目图片',
  `service_content` varchar(255) DEFAULT NULL COMMENT '服务内容',
  `worker_id` bigint(20) DEFAULT NULL COMMENT '社工人员id',
  `grade` double DEFAULT NULL COMMENT '预约评分',
  `state` int(11) DEFAULT NULL COMMENT '是否空闲状态',
  `company` varchar(255) DEFAULT NULL COMMENT '所属服务公司',
  `is_approved` int(11) DEFAULT NULL COMMENT '所属公司是否认证',
  `type` varchar(50) DEFAULT NULL COMMENT '所属服务类别',
  `service_abbreviation` varchar(50) DEFAULT NULL COMMENT '预约项目简称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='作为ICIS的预约项目表使用';

-- ----------------------------
-- Records of icis_appointment_item
-- ----------------------------
BEGIN;
INSERT INTO `icis_appointment_item` VALUES (1, NULL, '打扫卫生，一个月需要付1000块钱', 5, 8.7, 0, '家政服务公司', 1, '家政', '卫生打扫');
INSERT INTO `icis_appointment_item` VALUES (2, NULL, '带孩子，一个月5000块钱', 6, 9.1, 0, '家政服务公司', 1, '家政', '带孩子');
INSERT INTO `icis_appointment_item` VALUES (3, NULL, '搬运东西，一次500块钱', 7, 8.6, 0, '搬家公司', 1, '搬家', '搬运东西');
INSERT INTO `icis_appointment_item` VALUES (4, NULL, '带孩子，一次5500块钱', 8, 8.9, 0, '家政服务公司', 1, '家政', '带孩子');
INSERT INTO `icis_appointment_item` VALUES (5, NULL, '空调维修，一次400块钱', 9, 9, 0, '家电维修公司', 1, '家电维修', '空调维修');
COMMIT;

-- ----------------------------
-- Table structure for icis_appointment_record
-- ----------------------------
DROP TABLE IF EXISTS `icis_appointment_record`;
CREATE TABLE `icis_appointment_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '预约记录id',
  `service_photo` varchar(500) DEFAULT NULL COMMENT '预约项目图片',
  `service_content` varchar(255) DEFAULT NULL COMMENT '服务内容',
  `worker_id` bigint(20) DEFAULT NULL COMMENT '社工人员id',
  `company` varchar(255) DEFAULT NULL COMMENT '服务公司',
  `is_approved` int(11) DEFAULT NULL COMMENT '公司是否认证',
  `resident_id` bigint(20) DEFAULT NULL COMMENT '预约业主id',
  `is_completed` int(11) DEFAULT NULL COMMENT '预约是否完成',
  `service_grade` int(11) DEFAULT NULL COMMENT '服务评分',
  `service_comment` varchar(300) DEFAULT NULL COMMENT '服务评价',
  `create_time` datetime DEFAULT NULL COMMENT '预约开始时间',
  `final_time` datetime DEFAULT NULL COMMENT '预约结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='作为ICIS的预约记录表使用';

-- ----------------------------
-- Table structure for icis_contacts
-- ----------------------------
DROP TABLE IF EXISTS `icis_contacts`;
CREATE TABLE `icis_contacts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '通讯录id',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `telephone` bigint(20) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `headImg` varchar(500) DEFAULT NULL COMMENT '头像',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `account` varchar(50) DEFAULT NULL COMMENT '账户名',
  `address` varchar(500) DEFAULT NULL COMMENT '住址',
  `noteInfomation` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='作为ICIS的通讯录表使用';

-- ----------------------------
-- Records of icis_contacts
-- ----------------------------
BEGIN;
INSERT INTO `icis_contacts` VALUES (1, 'Retala', 17826805984, '474205755@qq.com', 'renwutouxiang1.jpg', '女', 'znsh10102', '浙江省杭州市石马社区10幢102单元', '暂无备注信息');
INSERT INTO `icis_contacts` VALUES (2, 'Alpha', 17826805432, '17823639@qq.com', 'renwutouxiang2.jpg', '女', 'znsh10101', '浙江省杭州市石马社区10幢101单元', '暂无备注信息');
INSERT INTO `icis_contacts` VALUES (3, 'Tila', 15323340192, '15323340192@163.com', 'renwutouxiang3.jpg', '女', 'znsh10107', '浙江省杭州市石马社区10幢107单元', '暂无备注信息');
INSERT INTO `icis_contacts` VALUES (4, '李宁', 17826808781, '2834781933@qq.com', 'renwutouxiang4.jpg', '男', 'znsh07402', '浙江省杭州市石马社区7幢402单元', '暂无备注信息');
INSERT INTO `icis_contacts` VALUES (5, 'Jalatimy', 17826892370, '17826892370@163.com', 'renwutouxiang5.jpg', '女', 'znsh12306', '浙江省杭州市石马社区12幢306单元', '暂无备注信息');
INSERT INTO `icis_contacts` VALUES (6, 'Cotana', 17826823930, '17826823930@163.com', 'renwutouxiang6.jpg', '女', 'znsh01203', '浙江省杭州市石马社区1幢203单元', '暂无备注信息');
INSERT INTO `icis_contacts` VALUES (7, 'Espero', 17326829170, '17326829170@163.com', 'renwutouxiang7.jpg', '男', 'znsh10302', '浙江省杭州市石马社区10幢302单元', '暂无备注信息');
INSERT INTO `icis_contacts` VALUES (8, 'Liber', 13137853830, '13137853830@163.com', 'renwutouxiang8.jpg', '男', 'znsh07102', '浙江省杭州市石马社区7幢102单元', '暂无备注信息');
INSERT INTO `icis_contacts` VALUES (9, 'ZetMitty', 17834986309, '17834986309@163.com', 'renwutouxiang9.jpg', '女', 'znsh09103', '浙江省杭州市石马社区9幢103单元', '暂无备注信息');
INSERT INTO `icis_contacts` VALUES (14, 'sam199510', 17826807795, '17826807795@163.com', 'fafda.png', '男', 'sam', '西和公寓10#606', '暂无');
COMMIT;

-- ----------------------------
-- Table structure for icis_dynamic
-- ----------------------------
DROP TABLE IF EXISTS `icis_dynamic`;
CREATE TABLE `icis_dynamic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '朋友圈id',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '朋友圈发布者id',
  `publish_time` datetime DEFAULT NULL COMMENT '朋友圈发布时间',
  `publish_content` varchar(500) DEFAULT NULL COMMENT '朋友圈文字内容',
  `publish_photo` varchar(500) DEFAULT NULL COMMENT '朋友圈照片',
  `publish_position` varchar(500) DEFAULT NULL COMMENT '朋友圈动态定位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='作为ICIS的朋友圈动态表使用';

-- ----------------------------
-- Records of icis_dynamic
-- ----------------------------
BEGIN;
INSERT INTO `icis_dynamic` VALUES (1, 1, '2017-10-02 16:17:48', '小区的治安是越来越好了( ⊙ o ⊙ )啊！', '/Users/fei/Documents/Workspace/IDEAWorkspace/J2EE/ICIS/src/main/webapp/images/dynamic/1.png', '长安镇华府社区');
COMMIT;

-- ----------------------------
-- Table structure for icis_dynamic_comment
-- ----------------------------
DROP TABLE IF EXISTS `icis_dynamic_comment`;
CREATE TABLE `icis_dynamic_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `dynamic_id` bigint(20) DEFAULT NULL COMMENT '评论朋友圈id',
  `commentor_id` bigint(20) DEFAULT NULL COMMENT '评论者id',
  `content` varchar(500) DEFAULT NULL COMMENT '评论内容',
  `comment_time` datetime DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='作为ICIS的朋友圈动态评论表使用';

-- ----------------------------
-- Records of icis_dynamic_comment
-- ----------------------------
BEGIN;
INSERT INTO `icis_dynamic_comment` VALUES (1, 1, 1, '治安的确变好了', '2017-10-02 19:20:48');
INSERT INTO `icis_dynamic_comment` VALUES (2, 1, 1, '保安大叔的态度也变好了', '2017-10-02 19:21:15');
INSERT INTO `icis_dynamic_comment` VALUES (3, 1, 3, '环境更加绿色化发展了', '2017-10-02 19:21:59');
INSERT INTO `icis_dynamic_comment` VALUES (4, 1, 4, '小区会有美好的未来', '2017-10-02 19:22:30');
INSERT INTO `icis_dynamic_comment` VALUES (5, 1, 5, '小区是我家，安全靠大家！', '2017-10-02 19:23:03');
COMMIT;

-- ----------------------------
-- Table structure for icis_dynamic_support
-- ----------------------------
DROP TABLE IF EXISTS `icis_dynamic_support`;
CREATE TABLE `icis_dynamic_support` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '点赞id',
  `dynamic_id` bigint(20) DEFAULT NULL COMMENT '点赞朋友圈id',
  `supportor_id` bigint(20) DEFAULT NULL COMMENT '点赞者id',
  `supporter_time` datetime DEFAULT NULL COMMENT '点赞时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='作为ICIS的朋友圈动态点赞表使用';

-- ----------------------------
-- Records of icis_dynamic_support
-- ----------------------------
BEGIN;
INSERT INTO `icis_dynamic_support` VALUES (1, 1, 1, '2017-10-02 18:41:20');
INSERT INTO `icis_dynamic_support` VALUES (3, 1, 3, '2017-10-02 18:41:29');
INSERT INTO `icis_dynamic_support` VALUES (4, 1, 5, '2017-10-02 18:41:37');
COMMIT;

-- ----------------------------
-- Table structure for icis_feedback
-- ----------------------------
DROP TABLE IF EXISTS `icis_feedback`;
CREATE TABLE `icis_feedback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '意见反馈id',
  `content` varchar(500) DEFAULT NULL COMMENT '意见反馈内容',
  `photo1` varchar(500) DEFAULT NULL COMMENT '意见反馈图片1',
  `photo2` varchar(500) DEFAULT NULL COMMENT '意见反馈2',
  `photo3` varchar(500) DEFAULT NULL COMMENT '意见反馈图片3',
  `time` datetime DEFAULT NULL COMMENT '意见反馈时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='作为ICIS的反馈表使用';

-- ----------------------------
-- Table structure for icis_notification
-- ----------------------------
DROP TABLE IF EXISTS `icis_notification`;
CREATE TABLE `icis_notification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '通知id',
  `extent` bigint(20) DEFAULT NULL COMMENT '通知紧急程度',
  `content` varchar(255) DEFAULT NULL COMMENT '通知内容',
  `unit` varchar(50) DEFAULT NULL COMMENT '通知单位',
  `date` date DEFAULT NULL COMMENT '通知日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='作为ICIS的通知表使用';

-- ----------------------------
-- Records of icis_notification
-- ----------------------------
BEGIN;
INSERT INTO `icis_notification` VALUES (1, 3, '今天小区晚上7点到8点将停电一小时', '物业', '2017-09-28');
INSERT INTO `icis_notification` VALUES (2, 1, '由于水管需要定期维修，明天小区停水半天', '居委会', '2017-09-27');
INSERT INTO `icis_notification` VALUES (3, 1, '后天县政府领导将莅临小区查看楼道安全通道等紧急设备安全状况', '业委会', '2017-09-26');
INSERT INTO `icis_notification` VALUES (4, 2, '发生当今法拉盛减肥的；阿萨附近；打扫房间啊都；撒娇发的老师；', '业委会', '2017-10-04');
COMMIT;

-- ----------------------------
-- Table structure for icis_order
-- ----------------------------
DROP TABLE IF EXISTS `icis_order`;
CREATE TABLE `icis_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `serial_no` varchar(50) DEFAULT NULL COMMENT '订单编号',
  `price` double DEFAULT NULL COMMENT '价格',
  `content` varchar(255) DEFAULT NULL COMMENT '订单内容',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `state` int(11) DEFAULT NULL COMMENT '订单状态',
  `pay_style` varchar(50) DEFAULT NULL COMMENT '支付方式',
  `pay_unit` varchar(100) DEFAULT NULL COMMENT '收费单位',
  `payor_id` bigint(20) DEFAULT NULL COMMENT '付款者id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='作为ICIS的订单表使用';

-- ----------------------------
-- Table structure for icis_repairs
-- ----------------------------
DROP TABLE IF EXISTS `icis_repairs`;
CREATE TABLE `icis_repairs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '报修id',
  `address` varchar(255) DEFAULT NULL COMMENT '报修地址',
  `content` varchar(500) DEFAULT NULL COMMENT '报修内容',
  `photo1` varchar(500) DEFAULT NULL COMMENT '报修图1',
  `photo2` varchar(500) DEFAULT NULL COMMENT '报修图2',
  `photo3` varchar(500) DEFAULT NULL COMMENT '报修图3',
  `photo4` varchar(500) DEFAULT NULL COMMENT '报修图4',
  `resident_id` bigint(20) DEFAULT NULL COMMENT '报修居民用户id',
  `time` datetime DEFAULT NULL COMMENT '报修时间',
  `is_complete` int(11) DEFAULT NULL COMMENT '是否已经修复',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='作为ICIS的报修表使用';

-- ----------------------------
-- Records of icis_repairs
-- ----------------------------
BEGIN;
INSERT INTO `icis_repairs` VALUES (1, '10幢105单元', '厕所灯坏道了', '/Users/fei/Documents/Workspace/IDEAWorkspace/J2EE/ICIS/src/main/webapp/images/repairs/3.png', NULL, NULL, NULL, 1, '2017-10-05 22:49:06', 1);
INSERT INTO `icis_repairs` VALUES (2, '10606', '厕所灯坏道了发范德萨发', '/Users/fei/Documents/Workspace/IDEAWorkspace/J2EE/ICIS/src/main/webapp/images/repairs/4.png', NULL, NULL, NULL, 1, '2017-10-05 22:50:54', 0);
COMMIT;

-- ----------------------------
-- Table structure for icis_resident
-- ----------------------------
DROP TABLE IF EXISTS `icis_resident`;
CREATE TABLE `icis_resident` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '业主id',
  `username` varchar(255) DEFAULT NULL COMMENT '业主用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '业主密码',
  `tel` bigint(20) DEFAULT NULL COMMENT '业主电话',
  `sex` varchar(10) DEFAULT NULL COMMENT '业主性别',
  `head_photo` varchar(500) DEFAULT NULL COMMENT '业主头像',
  `nickname` varchar(50) DEFAULT NULL COMMENT '业主昵称',
  `address` varchar(100) DEFAULT NULL COMMENT '业主所在地址',
  `live_age` int(11) DEFAULT NULL COMMENT '业主住宅龄',
  `signature` varchar(255) DEFAULT NULL COMMENT '业主个性签名',
  `pay_passcode` varchar(255) DEFAULT NULL COMMENT '业主支付码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='作为ICIS的居民用户表使用';

-- ----------------------------
-- Records of icis_resident
-- ----------------------------
BEGIN;
INSERT INTO `icis_resident` VALUES (1, 'sam199510', '123456', 17826807759, '男', '/Users/fei/Documents/Workspace/IDEAWorkspace/J2EE/ICIS/src/main/webapp/images/headPhoto/icis_resident/sam199510.png', '飞向一九九五', '西和公寓10#606', 2, '世间事本无难易，为之则易！', '123456');
INSERT INTO `icis_resident` VALUES (2, 'james198510', '123456', 17829023402, '男', '/Users/fei/Documents/Workspace/IDEAWorkspace/J2EE/ICIS/src/main/webapp/images/headPhoto/icis_resident/sam199510.png', 'james', '10504', 2, '放暑假啊放假啊水电费就爱上； ', '111111');
INSERT INTO `icis_resident` VALUES (3, 'jack198808', '112233', 178321312213, '男', '/Users/fei/Documents/Workspace/IDEAWorkspace/J2EE/ICIS/src/main/webapp/images/headPhoto/icis_resident/sam199510.png', 'jack', '10509', 2, '见风使舵的房间啊', '112222');
INSERT INTO `icis_resident` VALUES (4, 'fox197808', '122333', 13804329923, '女', '/Users/fei/Documents/Workspace/IDEAWorkspace/J2EE/ICIS/src/main/webapp/images/headPhoto/icis_resident/sam199510.png', 'fox', '9608', 2, '发生的发生', NULL);
INSERT INTO `icis_resident` VALUES (5, 'marry198711', '332211', 139342132131, '女', '/Users/fei/Documents/Workspace/IDEAWorkspace/J2EE/ICIS/src/main/webapp/images/headPhoto/icis_resident/sam199510.png', 'marry', '7509', 2, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for icis_sign_in
-- ----------------------------
DROP TABLE IF EXISTS `icis_sign_in`;
CREATE TABLE `icis_sign_in` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '签到表id',
  `sign_in_time` datetime DEFAULT NULL COMMENT '签到时间',
  `sign_in_address` varchar(500) DEFAULT NULL COMMENT '签到地点',
  `sign_in_or_id` bigint(20) DEFAULT NULL COMMENT '签到者id',
  `sign_in_activity` varchar(50) DEFAULT NULL COMMENT '签到活动',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='作为ICIS的用户活动签到表使用';

-- ----------------------------
-- Table structure for icis_worker
-- ----------------------------
DROP TABLE IF EXISTS `icis_worker`;
CREATE TABLE `icis_worker` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '社工人员id',
  `username` varchar(255) DEFAULT NULL COMMENT '社工人员用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '社工人员密码',
  `name` varchar(255) DEFAULT NULL COMMENT '社工人员姓名',
  `create_date` date DEFAULT NULL COMMENT '创建日期',
  `modify_date` date DEFAULT NULL COMMENT '修改日期',
  `login_date` date DEFAULT NULL COMMENT '登录日期',
  `tel` bigint(20) DEFAULT NULL COMMENT '社工人员电话',
  `qq` bigint(20) DEFAULT NULL COMMENT '社工人员QQ',
  `sex` varchar(10) DEFAULT NULL COMMENT '社工人员性别',
  `head_photo` varchar(500) DEFAULT NULL COMMENT '社工人员头像',
  `role` varchar(50) DEFAULT NULL COMMENT '社工角色',
  `state` int(11) DEFAULT NULL COMMENT '社工状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='作为ICIS的社工人员表使用';

-- ----------------------------
-- Records of icis_worker
-- ----------------------------
BEGIN;
INSERT INTO `icis_worker` VALUES (1, 'sam', 'sam', '张工', '2017-09-25', '2017-09-25', '2017-09-25', 17826903636, 123456, '1', NULL, '管道维修工', 0);
INSERT INTO `icis_worker` VALUES (2, 'tom', 'tom', '李工', '2017-09-25', '2017-09-25', '2017-09-25', 13696369878, 321456, '0', NULL, '保姆', 0);
INSERT INTO `icis_worker` VALUES (3, 'jame', 'jame', '王工', '2017-09-25', '2017-09-27', '2017-09-28', 12345632898, 125861232, '1', NULL, '电工', 0);
INSERT INTO `icis_worker` VALUES (4, 'fox', 'fox', '赵工', '2017-09-25', '2017-09-25', '2017-09-25', 13536365252, 236523652, '1', NULL, '修补匠', 0);
INSERT INTO `icis_worker` VALUES (5, 'qay', 'qay', '巧阿姨', '2017-09-12', '2017-09-25', '2017-09-25', 13826843971, 16885885, '0', NULL, '环卫工人', 0);
INSERT INTO `icis_worker` VALUES (6, 'zay', 'zay', '张阿姨', '2017-09-25', '2017-09-25', '2017-09-25', 15631478962, 659874103, '0', NULL, '保姆', 0);
INSERT INTO `icis_worker` VALUES (7, 'lsf', 'lsf', '李师傅', '2017-09-25', '2017-09-25', '2017-09-26', 15012365214, 632569856, '1', NULL, '运送人员', 0);
INSERT INTO `icis_worker` VALUES (8, 'way', 'way', '王阿姨', '2017-09-25', '2017-09-25', '2017-09-25', 13623541236, 203652145, '0', NULL, '保姆', 0);
INSERT INTO `icis_worker` VALUES (9, 'zsf', 'zsf', '赵师傅', '2017-09-10', '2017-09-18', '2017-09-26', 15636252354, 6523632563, '1', NULL, '管道维修工', 0);
INSERT INTO `icis_worker` VALUES (10, 'qg', 'qg', '钱工', '2017-09-26', '2017-09-26', '2017-09-26', 13789098988, 3243243242, '1', NULL, '杂工', 0);
INSERT INTO `icis_worker` VALUES (11, 'sg', 'sg', '孙工', '2017-09-26', '2017-09-26', '2017-09-26', 13583848848, 283832883, '1', NULL, '杂工', 0);
INSERT INTO `icis_worker` VALUES (12, 'hg', 'hg', '何工', '2017-09-26', '2017-09-26', '2017-09-28', 13873838382, 23302943, '0', NULL, '杂工', 0);
INSERT INTO `icis_worker` VALUES (13, 'lg', 'lg', '吕工', '2017-09-26', '2017-09-26', '2017-09-26', 17874274212, 12343213, '1', NULL, '杂工', 0);
INSERT INTO `icis_worker` VALUES (14, 'sg', 'sg', '苏工', '2017-09-26', '2017-09-26', '2017-09-27', 15683121221, 142131221, '1', NULL, '杂工', 0);
INSERT INTO `icis_worker` VALUES (15, 'cg', 'cg', '曹工', '2017-09-26', '2017-09-26', '2017-09-26', 1503294893, 43243232, '1', NULL, '杂工', 0);
INSERT INTO `icis_worker` VALUES (16, 'zg', 'zg', '郑工', '2017-09-26', '2017-09-26', '2017-09-26', 1384204923, 242232131, '1', NULL, '杂工', 0);
INSERT INTO `icis_worker` VALUES (17, 'fg', 'fg', '冯工', '2017-09-17', '2017-09-24', '2017-09-26', 1349340492, 43432222, '0', NULL, '杂工', 0);
INSERT INTO `icis_worker` VALUES (18, 'yg', 'yg', '颜工', '2017-09-26', '2017-09-26', '2017-09-26', 1560934204, 42332232, '0', NULL, '杂工', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `username` varchar(255) DEFAULT NULL COMMENT '管理员用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '管理员密码',
  `name` varchar(255) DEFAULT NULL COMMENT '管理员姓名',
  `create_date` date DEFAULT NULL COMMENT '创建日期',
  `modify_date` date DEFAULT NULL COMMENT '修改日期',
  `login_date` date DEFAULT NULL COMMENT '登录日期',
  `head_photo` varchar(500) DEFAULT NULL COMMENT '管理员头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='作为ICIS的管理员表使用';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', 'admin', '管理员', '2017-09-25', '2017-09-25', '2017-09-25', NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
