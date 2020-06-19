CREATE  TABLE users (
  username VARCHAR2(45) PRIMARY KEY ,
  password VARCHAR2(45) NOT NULL ,
  enabled  NUMBER DEFAULT 1 
);

CREATE SEQUENCE user_roles_idx_seq;
CREATE TABLE user_roles (
  user_role_id NUMBER(11)  PRIMARY KEY,
  username varchar2(45) NOT NULL,
  role varchar2(45) NOT NULL,
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username)
);

INSERT INTO users(username,password,enabled)
VALUES ('wls','123456', 1);
INSERT INTO users(id,username,password,enabled)
VALUES ('qkr', 'alex','123456', 1);
INSERT INTO users(username,password,enabled)
VALUES ('kimc','1234', 1);

SELECT * FROM user_roles;

INSERT INTO user_roles (user_role_id, username, role)
VALUES (user_roles_idx_seq.nextval, 'wls', 'ROLE_USER');
INSERT INTO user_roles (user_role_id, username, role)
VALUES (user_roles_idx_seq.nextval, 'wls', 'ROLE_ADMIN');
INSERT INTO user_roles (user_role_id, username, role)
VALUES (user_roles_idx_seq.nextval, 'kimc', 'ROLE_USER');
INSERT INTO user_roles (user_role_id, username, role)
VALUES (user_roles_idx_seq.nextval, 'kimc', 'ROLE_ADMIN');
INSERT INTO user_roles (user_role_id, username, role)
VALUES (user_roles_idx_seq.nextval, 'alex', 'ROLE_USER');

COMMIT;
SELECT * FROM USERS u ;

SELECT * FROM MEMBER;

SELECT * FROM mem;

DROP TABLE mem;

CREATE TABLE mem(
	userid varchar2(100) NOT NULL,
	pwd varchar2(100) NOT NULL,
	name varchar2(100) NOT NULL,
	email varchar2(100) NOT null
);

SELECT * FROM tab;


INSERT INTO mem VALUES ('박상영', '8524679', '운영자', 'sy_222@naver.com');
INSERT INTO mem VALUES ('관리자1', '1234', '1번회원', 'leec@naver.com');
INSERT INTO mem VALUES ('방문자1', '1234', '1번회원', 'leec@naver.com');
COMMIT;
SELECT * FROM mem;


CREATE TABLE mem_role(
	username varchar(45) NOT NULL,
	role varchar(45) NOT NULL
);
INSERT INTO mem_role VALUES ('박상영', 'ROLE_ADMIN');
INSERT INTO mem_role VALUES ('관리자1', 'ROLE_ADMIN');
INSERT INTO mem_role VALUES ('방문자1', 'ROLE_USER');
COMMIT;

SELECT * FROM mem_role;

UPDATE mem_role SET "ROLE" ='ROLE_ADMIN' WHERE username = '박상영';



