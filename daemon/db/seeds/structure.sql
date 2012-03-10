/**
 * Users
 */
CREATE  TABLE IF NOT EXISTS `des`.`users` (
  `identity` VARCHAR(255) NOT NULL ,
  `password` VARCHAR(64) NOT NULL ,
  `first_name` VARCHAR(255) NOT NULL ,
  `last_name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`identity`) )
ENGINE = InnoDB