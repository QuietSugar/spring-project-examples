CREATE TABLE `sbm_area`
(
    `id`          bigint(20)  NOT NULL AUTO_INCREMENT,
    `code`        varchar(20) NOT NULL COMMENT '地区代码',
    `name`        varchar(50) NOT NULL COMMENT '地区名称',
    `parent_code` varchar(20) DEFAULT NULL COMMENT '父级地区代码',
    `level`       tinyint(4)  DEFAULT NULL COMMENT '父级地区代码',
    PRIMARY KEY (`id`),
    UNIQUE KEY `code` (`code`) USING BTREE
)
    DEFAULT CHARSET = utf8 COMMENT ='省市及对应编码表';

