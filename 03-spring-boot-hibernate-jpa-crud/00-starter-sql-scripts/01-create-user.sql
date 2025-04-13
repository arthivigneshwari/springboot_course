-- Drop user first if they exist
DROP USER if exists 'springstudent'@'%' ;

-- Now create user with prop privileges
CREATE USER 'springstudent'@'%' IDENTIFIED BY 'springstudent';

GRANT ALL PRIVILEGES ON * . * TO 'springstudent'@'%';

CREATE USER 'springstudent'@'127.0.0.1' IDENTIFIED BY 'springstudent';#worked..create specifically for 127.0.0.1 and password is 'root'