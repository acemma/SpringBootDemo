CREATE TABLE IF NOT EXISTS t_user(
    id   INT(11) NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) DEFAULT NULL COMMENT'用户名',
    password VARCHAR(50) DEFAULT NULL COMMENT'密码',
    age INT(11) DEFAULT NULL COMMENT'年龄',
    phone VARCHAR(11) DEFAULT NULL COMMENT'手机号',
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基础信息表';

INSERT INTO t_user(username,password,age,phone) VALUES ('张三','123456',22,'13312345678');
INSERT INTO t_user(username,password,age,phone) VALUES ('李四','123456',23,'13412345678');
INSERT INTO t_user(username,password,age,phone) VALUES ('王五','123456',24,'13512345678');