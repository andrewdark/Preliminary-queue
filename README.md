#Preliminary-queue - система предварительной записи на прием.
############################################
USE `preliminary-queue`;
CREATE TABLE persistent_logins (
    
	username varchar(64) not null,
    
	series varchar(64) not null,
    
	token varchar(64) not null,
    
	last_used timestamp not null,
    
	PRIMARY KEY (series)
);

############################################
https://www.thymeleaf.org/doc/articles/springsecurity.html
