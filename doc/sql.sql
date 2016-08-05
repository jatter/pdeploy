CREATE TABLE `java_deploy` (
  `uuid` varchar(36) NOT NULL COMMENT 'UUID',
  `name` varchar(255) NOT NULL COMMENT '项目名称',
  `profile` varchar(255) NOT NULL COMMENT 'Maven profile',
  `type` tinyint(4) NOT NULL COMMENT '版本控制工具类型(1.SVN;2.GIT)',
  `url` varchar(255) NOT NULL COMMENT 'svn/git地址',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `java_web_deploy` (
  `uuid` varchar(36) NOT NULL COMMENT 'UUID',
  `name` varchar(255) NOT NULL COMMENT '项目名称',
  `profile` varchar(255) NOT NULL COMMENT 'Maven profile',
  `type` tinyint(4) NOT NULL COMMENT '版本控制工具类型(1.SVN;2.GIT)',
  `url` varchar(255) NOT NULL COMMENT 'svn/git地址',
  `context_path` varchar(255) NOT NULL COMMENT 'Web项目contextPath',
  `port` int(11) NOT NULL COMMENT '端口号',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `parameter_management` (
  `parameter_id`  VARCHAR(36) NOT null COMMENT '参数ID',
  `uuid`  VARCHAR(36) NOT null COMMENT 'UUID',
  `parameter_name` VARCHAR(255) NOT null COMMENT '参数名称',
  `parameter_value` VARCHAR(255) NOT null COMMENT '参数值',
  `parameter_path` VARCHAR(255) NOT null COMMENT '参数路径',
  `regular` VARCHAR(255) NOT null COMMENT '正则匹配',
  PRIMARY KEY (`parameter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;