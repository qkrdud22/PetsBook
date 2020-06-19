SELECT * FROM tab;

CREATE SEQUENCE main_idx_seq;
CREATE TABLE main(
	idx number(10) PRIMARY KEY,
	name varchar2(100) NOT NULL,
	image varchar2(400) null,
	content varchar2(2000) NOT NULL,
	regDate timestamp DEFAULT SYSDATE,
	ip varchar2(30) NOT NULL,
	hit number(10) DEFAULT 0
);

SELECT * FROM main;

INSERT INTO main VALUES 
	(main_idx_seq.nextval,'박상영', 'pet.jpg', '저는 고양이 입니다.', SYSDATE,'192.168.0.47',0);
INSERT INTO main VALUES 
	(main_idx_seq.nextval,'김삼념', 'null', '저는 강아지 입니다..', SYSDATE,'192.168.0.47',0);

CREATE SEQUENCE maincomment_idx_seq;
CREATE TABLE maincomment(
	idx number(10) PRIMARY KEY,
	ref number(10) NOT NULL,
	name varchar2(100) NOT NULL,
	content varchar2(2000) NOT NULL,
	regDate timestamp DEFAULT SYSDATE,
	ip varchar2(30) NOT NULL
);
SELECT * FROM maincomment ;
-----------------------------------------------------------------------------------------------------

CREATE SEQUENCE boardConfig_idx_seq;
CREATE TABLE boardConfig(
	idx NUMBER PRIMARY KEY,
	tableName varchar2(100) NOT null,
	boardName varchar2(100) NOT NULL,
	boardInfo varchar2(500) NOT NULL
);

SELECT * FROM boardConfig;

SELECT * FROM tab;
-----------------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------------