-- 导出 chieh 的数据库结构
CREATE DATABASE IF NOT EXISTS `chieh` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `chieh`;

-- 导出  表 chieh.oauth2_authorization 结构
CREATE TABLE IF NOT EXISTS `oauth2_authorization` (
  `id` varchar(100) NOT NULL,
  `registered_client_id` varchar(100) NOT NULL,
  `principal_name` varchar(200) NOT NULL,
  `authorization_grant_type` varchar(100) NOT NULL,
  `authorized_scopes` varchar(1000) DEFAULT NULL,
  `attributes` blob,
  `state` varchar(500) DEFAULT NULL,
  `authorization_code_value` blob,
  `authorization_code_issued_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `authorization_code_expires_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `authorization_code_metadata` blob,
  `access_token_value` blob,
  `access_token_issued_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `access_token_expires_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `access_token_metadata` blob,
  `access_token_type` varchar(100) DEFAULT NULL,
  `access_token_scopes` varchar(1000) DEFAULT NULL,
  `oidc_id_token_value` blob,
  `oidc_id_token_issued_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `oidc_id_token_expires_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `oidc_id_token_metadata` blob,
  `refresh_token_value` blob,
  `refresh_token_issued_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `refresh_token_expires_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `refresh_token_metadata` blob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 数据导出被取消选择。

-- 导出  表 chieh.oauth2_authorization_consent 结构
CREATE TABLE IF NOT EXISTS `oauth2_authorization_consent` (
  `registered_client_id` varchar(100) NOT NULL,
  `principal_name` varchar(200) NOT NULL,
  `authorities` varchar(1000) NOT NULL,
  PRIMARY KEY (`registered_client_id`,`principal_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 数据导出被取消选择。

-- 导出  表 chieh.oauth2_registered_client 结构
CREATE TABLE IF NOT EXISTS `oauth2_registered_client` (
  `id` varchar(50) NOT NULL DEFAULT '' COMMENT '主键',
  `client_id` varchar(50) NOT NULL DEFAULT '',
  `client_id_issued_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `client_secret` varchar(50) NOT NULL DEFAULT '',
  `client_secret_expires_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `client_name` varchar(50) NOT NULL DEFAULT '',
  `client_authentication_methods` varchar(50) NOT NULL DEFAULT '',
  `authorization_grant_types` varchar(500) NOT NULL DEFAULT '',
  `redirect_uris` varchar(500) NOT NULL DEFAULT '',
  `scopes` varchar(50) NOT NULL DEFAULT '',
  `client_settings` varchar(500) NOT NULL DEFAULT '',
  `token_settings` varchar(1000) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='oauth2.0客户端信息';

-- 数据导出被取消选择。

-- 导出  表 chieh.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) NOT NULL DEFAULT '' COMMENT '手机号',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
  `nickname` varchar(50) NOT NULL DEFAULT '' COMMENT '昵称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 数据导出被取消选择。

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
