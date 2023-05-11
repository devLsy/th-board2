--게시판
CREATE TABLE "BOARD_INFO"
   (	"BOARD_NO" NUMBER NOT NULL ENABLE, 
	"TITLE" VARCHAR2(50) NOT NULL ENABLE, 
	"CONTENT" VARCHAR2(4000) NOT NULL ENABLE, 
	"WRITER" VARCHAR2(20) NOT NULL ENABLE, 
	"USE_YN" CHAR(1) DEFAULT 'Y' NOT NULL ENABLE, 
	"REG_DATE" DATE DEFAULT SYSDATE NOT NULL ENABLE, 
	"MOD_DATE" DATE, 
	 CONSTRAINT "BOARD_INFO_PK" PRIMARY KEY ("BOARD_NO")
); 

COMMENT ON COLUMN BOARD_INFO.BOARD_NO IS '게시글 순번(시퀀스: BOARD_NO_SEQ)';
COMMENT ON COLUMN BOARD_INFO.TITLE IS '제목';
COMMENT ON COLUMN BOARD_INFO.CONTENT IS '내용';
COMMENT ON COLUMN BOARD_INFO.WRITER IS '작성자';
COMMENT ON COLUMN BOARD_INFO.USE_YN IS '사용여부';
COMMENT ON COLUMN BOARD_INFO.REG_DATE IS '등록일';
COMMENT ON COLUMN BOARD_INFO.MOD_DATE IS '수정일';

--시퀀스
CREATE SEQUENCE BOARD_NO_SEQ INCREMENT BY 1 MINVALUE 1 MAXVALUE 99999999 CYCLE NOCACHE ORDER ;

--첨부파일
CREATE TABLE "FILE_INFO"
   ("FILE_NO" NUMBER NOT NULL ENABLE,
	"BOARD_NO" NUMBER NOT NULL ENABLE,
	"FILE_ORG_NAME" VARCHAR2(100) NOT NULL ENABLE,
	"FILE_PATH" VARCHAR2(100) NOT NULL ENABLE,
	"FILE_SIZE" NUMBER NOT NULL ENABLE,
	"DEL_YN" CHAR(1) DEFAULT 'N',
	"REG_DATE" DATE NOT NULL ENABLE,
	"MOD_DATE" DATE DEFAULT SYSDATE,
	 CONSTRAINT "FILE_INFO_PK" PRIMARY KEY ("FILE_NO", "BOARD_NO")
)
;

COMMENT ON COLUMN FILE_INFO.FILE_NO IS '첨부파일 순번(시퀀스: FILE_NO_SEQ)';
COMMENT ON COLUMN FILE_INFO.BOARD_NO IS '게시글 순번';
COMMENT ON COLUMN FILE_INFO.FILE_ORG_NAME IS '첨부파일 원본명';
COMMENT ON COLUMN FILE_INFO.FILE_PATH IS '첨부파일 경로';
COMMENT ON COLUMN FILE_INFO.FILE_SIZE IS '첨부파일 용량';
COMMENT ON COLUMN FILE_INFO.DEL_YN IS '첨부파일 삭제여부';
COMMENT ON COLUMN FILE_INFO.REG_DATE IS '등록일';
COMMENT ON COLUMN FILE_INFO.MOD_DATE IS '수정일';

--시퀀스
CREATE SEQUENCE FILE_NO_SEQ INCREMENT BY 1 MINVALUE 1 MAXVALUE 99999999 CYCLE NOCACHE ORDER ;

