CREATE DATABASE yber;

CREATE USER 'admin'@'localhost' IDENTIFIED BY 'password';
CREATE USER 'admin'@'%' IDENTIFIED BY 'password';

GRANT ALL ON yber.* to  'admin'@'localhost';
GRANT ALL ON yber.* to  'admin'@'%';

