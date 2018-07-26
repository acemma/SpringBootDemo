CREATE TABLE IF NOT EXISTS t_role(
    id   INT(11) NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) DEFAULT NULL COMMENT'角色名称',
    code VARCHAR(20) DEFAULT NULL COMMENT'角色编码',
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色基础信息表';

INSERT INTO t_role(name,code) VALUES ('角色一','ROLE_1');
INSERT INTO t_role(name,code) VALUES ('角色二','ROLE_2');