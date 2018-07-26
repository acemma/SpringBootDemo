CREATE TABLE IF NOT EXISTS t_user_role(
    id   INT(11) NOT NULL AUTO_INCREMENT,
    user_id INT(11) NOT NULL COMMENT'用户id',
    role_id INT(11) NOT NULL COMMENT'角色id',
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

INSERT INTO t_user_role(user_id,role_id) VALUES (1,1);
INSERT INTO t_user_role(user_id,role_id) VALUES (1,2);
INSERT INTO t_user_role(user_id,role_id) VALUES (2,1);