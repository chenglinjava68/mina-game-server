/**删除表**/
DROP TABLE IF EXISTS gs_user;

/**创建表**/
CREATE TABLE gs_user(
	id INT NOT NULL AUTO_INCREMENT COMMENT 'ID',
	username VARCHAR(32) NOT NULL COMMENT '用户名',
	password VARCHAR(32) NOT NULL COMMENT '密码',
	createDate datetime NOT NULL COMMENT '创建日期',
	updateDate datetime COMMENT '更新日期',
	PRIMARY KEY(id),
	INDEX index_user(username,password)
) COMMENT '用户表';

