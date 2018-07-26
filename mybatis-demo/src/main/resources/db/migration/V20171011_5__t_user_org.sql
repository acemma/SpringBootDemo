CREATE TABLE IF NOT EXISTS t_user_org(
    id   INT(11) NOT NULL AUTO_INCREMENT,
    user_id INT(11) NOT NULL COMMENT'用户id',
    org_id INT(11) NOT NULL COMMENT'机构id',
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户机构关联表';

INSERT INTO t_user_org(user_id,org_id) VALUES (1,1);
INSERT INTO t_user_org(user_id,org_id) VALUES (2,2);