/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : 504

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2018-06-05 14:52:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for project_info
-- ----------------------------
DROP TABLE IF EXISTS `project_info`;
CREATE TABLE `project_info` (
  `project_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长ID',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `contract_number` varchar(255) NOT NULL COMMENT '合同编号',
  `project_status` int(255) NOT NULL COMMENT '项目状态 0:正常,1暂停 2超期3结项',
  `project_phases` int(255) DEFAULT NULL COMMENT '评审阶段0 需求阶段，1设计阶段,2测试阶段,3验收阶段',
  `project_phasesstauts` int(255) DEFAULT NULL COMMENT '阶段状态 0：未评审，1.评审通过，待修改，3.评审通过，已修改',
  `update_time` date DEFAULT NULL COMMENT '更新时间',
  `partyA_unit` varchar(255) DEFAULT NULL COMMENT '甲方单位',
  `partyA_infpeople` varchar(255) DEFAULT NULL COMMENT '甲方接口人',
  `partyB_unit` varchar(255) DEFAULT NULL COMMENT '乙方单位',
  `partyB_infpeople` varchar(255) DEFAULT NULL COMMENT '乙方接口人',
  `project_planstarttime` date DEFAULT NULL COMMENT '项目计划开始时间',
  `project_planendtime` date DEFAULT NULL COMMENT '项目计划结束时间',
  `project_realendtime` date DEFAULT NULL COMMENT '项目结项时间',
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
