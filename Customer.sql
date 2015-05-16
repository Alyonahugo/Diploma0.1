CREATE TABLE 'customer' (
  'id' bigint(20) NOT NULL AUTO_INCREMENT,
  'firstname' varchar(45) NOT NULL,
  'lastname' varchar(45) NOT NULL,
  'middlename' varchar(45) DEFAULT NULL,
  PRIMARY KEY ('id'),
  UNIQUE KEY 'id_UNIQUE' ('id')
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8