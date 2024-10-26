create table IF NOT EXISTS admin (
                       id varchar(45) primary key not null,
                       password char(68) not null,
                       active tinyint not null);

INSERT INTO admin (id, password, active)
SELECT 'alfa', '{bcrypt}$2a$12$fuoRGqj.3vO3.dyQ2Mu4wes3/vwMZoMmPrYbxzx78LAkLlJDCtfne', 1
WHERE NOT EXISTS (SELECT 1 FROM admin WHERE id = 'alfa');

INSERT INTO admin (id, password, active)
SELECT 'hugo', '{bcrypt}$2a$12$y6vUb2nfIJMRndv31r4Br.9YIZ.iN2OZYudvlnsmu1lQ8vAH5j16q', 1
WHERE NOT EXISTS (SELECT 1 FROM admin WHERE id = 'hugo');

INSERT INTO admin (id, password, active)
SELECT 'bastian', '{bcrypt}$2a$12$8tzTmpf1fwFEuScYm2kO0eCCIqUcD4RWxqPmenlp63qWg668QtDSC', 1
WHERE NOT EXISTS (SELECT 1 FROM admin WHERE id = 'bastian');


create table IF NOT EXISTS roles(
                      id varchar(45) not null,
                      role varchar(45) not null,
                      constraint authorities5_idx_1 unique (id, role),
                      constraint authorities5_ibfk_1 foreign key(id) references admin (id));

INSERT INTO roles (id, role)
SELECT 'alfa', 'ROLE_ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE id = 'alfa' AND role = 'ROLE_ADMIN');

INSERT INTO roles (id, role)
SELECT 'alfa', 'ROLE_USER'
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE id = 'alfa' AND role = 'ROLE_USER');

INSERT INTO roles (id, role)
SELECT 'hugo', 'ROLE_USER'
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE id = 'hugo' AND role = 'ROLE_USER');

INSERT INTO roles (id, role)
SELECT 'bastian', 'ROLE_USER'
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE id = 'bastian' AND role = 'ROLE_USER');


