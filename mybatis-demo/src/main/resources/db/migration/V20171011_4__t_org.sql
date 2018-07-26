CREATE TABLE IF NOT EXISTS t_org(
    id   INT(11) NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) DEFAULT NULL COMMENT'机构名称',
    code VARCHAR(20) DEFAULT NULL COMMENT'机构编码',
    address VARCHAR(100) DEFAULT NULL COMMENT'地址',
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构基础信息表';

INSERT INTO t_org(name,code,address) VALUES ('机构一','ORG_1','武汉市江夏区');
INSERT INTO t_org(name,code,address) VALUES ('机构二','ORG_2','武汉市江汉区');