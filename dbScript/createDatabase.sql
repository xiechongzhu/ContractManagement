drop database if exists 504;
create database 504;
use 504;

CREATE DATABASE `504` /*!40100 DEFAULT CHARACTER SET utf8 */;
DROP TABLE IF EXISTS `contract_info`;
CREATE TABLE `contract_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(200) NOT NULL,
  `name` varchar(200) NOT NULL,
  `status` int(11) NOT NULL,
  `classification` int(11) NOT NULL,
  `leader` varchar(200) NOT NULL,
  `money` float NOT NULL,
  `needInvoice` int(11) NOT NULL,
  `filingTime` date DEFAULT NULL,
  `cdNumber` varchar(200) DEFAULT NULL,
  `requirementTimePlan` date NOT NULL,
  `requirementTimeReal` date DEFAULT NULL,
  `requirementPayMoney` float NOT NULL DEFAULT '0',
  `designTimePlan` date NOT NULL,
  `designTimeReal` date DEFAULT NULL,
  `designPayMoney` float DEFAULT '0',
  `testTimePlan` date NOT NULL,
  `testTimeReal` date DEFAULT NULL,
  `testPayMoney` float DEFAULT '0',
  `acceptanceTimePlan` date NOT NULL,
  `acceptanceTimeReal` date DEFAULT NULL,
  `acceptancePayMoney` float DEFAULT '0',
  `isDelay` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `contractNumber_UNIQUE` (`number`),
  UNIQUE KEY `contractName_UNIQUE` (`name`),
  KEY `idx_contract_info_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `project_info`;
CREATE TABLE `project_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增长ID',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `contract_number` varchar(255) NOT NULL COMMENT '合同编号',
  `project_status` int(255) NOT NULL COMMENT '项目状态 0:正常,1暂停 2超期3结项',
  `project_classification` int(255) DEFAULT NULL COMMENT '项目密级 0非密 1秘密',
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `fusion_info`;
CREATE TABLE `fusion_info` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `version` varchar(200) NOT NULL,
  `platform` int(11) NOT NULL,
  `uploadDate` date NOT NULL,
  `fileName` varchar(200) NOT NULL,
  `md5` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `UK_praise` (`version`,`platform`),
  KEY `FK_t_praise_version` (`version`),
  KEY `FK_t_praise_platform` (`platform`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `plugin_info`;
CREATE TABLE `plugin_info` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `version` varchar(45) DEFAULT NULL,
  `platform` int(11) DEFAULT NULL,
  `fusionVersion` varchar(45) DEFAULT NULL,
  `uploadDate` date DEFAULT NULL,
  `fileName` varchar(45) DEFAULT NULL,
  `md5` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `project_alerts`;
CREATE TABLE `project_alerts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `projectId` bigint(20) DEFAULT NULL,
  `alertNumber` varchar(200) DEFAULT NULL,
  `confirmNumber` varchar(200) DEFAULT NULL,
  `alertDate` date DEFAULT NULL,
  `confirmDate` date DEFAULT NULL,
  `alertFile` varchar(100) DEFAULT NULL,
  `confirmFile` varchar(100) DEFAULT NULL,
  `effort` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

