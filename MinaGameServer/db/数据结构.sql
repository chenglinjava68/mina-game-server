/**删除表**/
DROP TABLE IF EXISTS t_player;
DROP TABLE IF EXISTS t_user;
DROP TABLE IF EXISTS t_job;


/*用户*/
CREATE  TABLE IF NOT EXISTS `minagameserver`.`t_user` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(20) NOT NULL COMMENT '用户名' ,
  `password` VARCHAR(20) NOT NULL COMMENT '密码' ,
  `email` VARCHAR(45) NULL ,
  `valid` BIT NOT NULL COMMENT '状态' ,
  `regDate` DATETIME NOT NULL COMMENT '注册日期' ,
  `modDate` DATETIME NULL COMMENT '修改日期' ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '用户表'

/*职业*/
CREATE  TABLE IF NOT EXISTS `minagameserver`.`t_job` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `type` INT NOT NULL COMMENT '类型' ,
  `hp` INT NOT NULL COMMENT '生命值' ,
  `mp` INT NOT NULL COMMENT '魔法值' ,
  `atk` INT NOT NULL COMMENT '攻击力' ,
  `def` INT NOT NULL COMMENT '防御力' ,
  `ms` INT NOT NULL COMMENT '移动速度' ,
  `regDate` DATETIME NOT NULL ,
  `modDate` DATETIME NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '职业'

/*玩家*/
CREATE  TABLE IF NOT EXISTS `minagameserver`.`t_player` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nickName` VARCHAR(10) NOT NULL ,
  `sex` BIT NOT NULL ,
  `level` INT NOT NULL ,
  `regDate` DATETIME NOT NULL ,
  `modDate` DATETIME NULL ,
  `fk_user_id` INT NOT NULL ,
  `fk_job_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `index_user_id` (`fk_user_id` ASC) ,
  INDEX `index_job_id` (`fk_job_id` ASC) ,
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`fk_user_id` )
    REFERENCES `minagameserver`.`t_user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_id`
    FOREIGN KEY (`fk_job_id` )
    REFERENCES `minagameserver`.`t_job` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '玩家'