/*-------------------------------------------------------------------------------------*/
DROP DATABASE javafxdemo;
CREATE DATABASE javafxdemo;
USE javafxdemo;

/*-------------------------------------------------------------------------------------*/
DROP SCHEMA javafxdemo;
CREATE SCHEMA javafxdemo;
USE javafxdemo;

/*-------------------------------------------------------------------------------------*/
CREATE TABLE tblstaff (
	staffid INT NOT NULL AUTO_INCREMENT,
	firstname VARCHAR(50) NULL,
	lastname VARCHAR(50) NOT NULL,
	street1 VARCHAR(50) NOT NULL,
	street2 VARCHAR(50) NULL,
	city VARCHAR(50) NOT NULL,
	country VARCHAR(50) NOT NULL,
	mobilephone CHAR(14) NOT NULL,
	email VARCHAR(50) NULL,
	role VARCHAR(50) NOT NULL,
	note LONGTEXT NULL,
	active TINYINT NOT NULL,
	PRIMARY KEY (staffid),
	UNIQUE INDEX staffid_UNIQUE (staffid ASC) VISIBLE
);

/*-------------------------------------------------------------------------------------*/
INSERT INTO tblstaff (staffid, firstname, lastname, street1, street2, city, country, mobilephone, email, role, note, active)

VALUES
	(null, 'John', 'Jones', '15 Century Drive', 'Macoya Industrial Estate', 'Macoya', 'Trinidad and Tobago', '(868) 785-6247', 'johnjones@gmail.com', 'Manager', 'Approaching retirement', 1),
    (null, 'Caitlin', 'Clarke', 'St. Marys Road', 'Fanny Village', 'San Fernando', 'Trinidad and Tobago', '(868) 687-4422', 'caitlinclarke@gmail.com', 'Supervisor', 'Candidate for bonus', 1),
    (null, 'Mary', 'Pierce', 'Pennyless Avenue', 'Jordan Hill', 'Manahambre', 'Trinidad and Tobago', '(868) 625-2247', 'marypierce@gmail.com', 'Clerk', 'On vacation', 1)
;

/*-------------------------------------------------------------------------------------*/
CREATE TABLE tbluser (
	userid INT NOT NULL AUTO_INCREMENT,
	username VARCHAR(50) NOT NULL,
	password VARCHAR(50) NOT NULL,
	userrole VARCHAR(50) NOT NULL,
	staffid INT NOT NULL,
	active TINYINT NOT NULL,
	PRIMARY KEY (userid),
	UNIQUE INDEX userid_UNIQUE (userid ASC) VISIBLE,
	UNIQUE INDEX staffid_UNIQUE (staffid ASC) VISIBLE,
	CONSTRAINT staffid
	FOREIGN KEY (staffid) REFERENCES tblstaff(staffid)
	ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

/*-------------------------------------------------------------------------------------*/
INSERT INTO tbluser
	(userid,username,password,userrole,staffid,active)
VALUES
	(null,'jjones','pass','Manager', 1, 1),
	(null,'cclarke','pass','Supervisor', 2, 1),
	(null,'mpierce','pass','Clerk', 3, 1)
;

SELECT * FROM tbluser;