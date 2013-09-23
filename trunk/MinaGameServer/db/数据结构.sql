/**gs_user**/
DROP TABLE IF EXISTS gs_user;
CREATE TABLE gs_user(
	id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	username varchar(32),
	password varchar(32),
	createDate datetime
);

