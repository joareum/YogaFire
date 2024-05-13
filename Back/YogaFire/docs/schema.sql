
-- drop database yogafire;
-- create database yogafire;
        

CREATE TABLE diet
(
  diet_id   INT          NOT NULL AUTO_INCREMENT COMMENT '식단 테이블 고유 번호',
  rec_kcal  VARCHAR(30)  NULL     COMMENT '하루 권장 칼로리',
  eat_kcal  INT          NULL     COMMENT '섭취 칼로리',
  rest_kcal INT          NULL     COMMENT '남은 칼로리',
  user_id   VARCHAR(30)  NOT NULL COMMENT '회원 아이디',
  food_name VARCHAR(100) NOT NULL COMMENT '식품',
  PRIMARY KEY (diet_id)
) COMMENT '식단 테이블';

ALTER TABLE diet
  ADD CONSTRAINT UQ_diet_id UNIQUE (diet_id);

CREATE TABLE food_list
(
  food_name     VARCHAR(100) NOT NULL COMMENT '식품',
  food_kcal     INT          NOT NULL COMMENT '식품 칼로리',
  food_gram     INT          NOT NULL COMMENT '식품 중량',
  food_chech_yn VARCHAR(1)   NOT NULL DEFAULT 'N' COMMENT '선택 여부',
  PRIMARY KEY (food_name)
) COMMENT '식품 영양 성분 리스트 테이블';

ALTER TABLE food_list
  ADD CONSTRAINT UQ_food_name UNIQUE (food_name);

CREATE TABLE profile
(
  center_name    VARCHAR(100)   NOT NULL COMMENT '요가원 이름',
  center_detail  VARCHAR(10000) NOT NULL COMMENT '요가원 설명',
  center_address VARCHAR(1000)  NOT NULL COMMENT '요가원 주소',
  center_img     VARCHAR(4000)  NOT NULL COMMENT '요가원 프로필 사진',
  center_price   INT            NOT NULL COMMENT '요가원 가격',
  video_key      INT            NOT NULL COMMENT '영상 고유 번호',
  PRIMARY KEY (center_name)
) COMMENT '요가원 상세 페이지 테이블';

ALTER TABLE profile
  ADD CONSTRAINT UQ_centerreview_name UNIQUE (center_name);

CREATE TABLE user
(
  user_id     VARCHAR(30) NOT NULL COMMENT '회원 아이디',
  password    VARCHAR(30) NOT NULL COMMENT '회원 비밀번호',
  name        VARCHAR(30) NOT NULL COMMENT '회원 이름',
  birthday    VARCHAR(8)  NOT NULL COMMENT '회원 생년월일',
  email       VARCHAR(50) NOT NULL COMMENT '회원 이메일',
  phone       VARCHAR(20) NOT NULL COMMENT '전화번호',
  nickname    VARCHAR(30) NOT NULL COMMENT '닉네임',
  create_date DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자',
  delete_yn   VARCHAR(1)  NOT NULL DEFAULT 'N' COMMENT '탈퇴여부',
  weight      INT         NOT NULL COMMENT '체중',
  sex         INT         NOT NULL COMMENT '성별',
  PRIMARY KEY (user_id)
) COMMENT '회원 테이블';

ALTER TABLE user
  ADD CONSTRAINT UQ_user_id UNIQUE (user_id);

ALTER TABLE user
  ADD CONSTRAINT UQ_email UNIQUE (email);

ALTER TABLE user
  ADD CONSTRAINT UQ_nickname UNIQUE (nickname);

CREATE TABLE video
(
  video_key    INT           NOT NULL AUTO_INCREMENT COMMENT '영상 고유 번호',
  video_title  VARCHAR(1000) NOT NULL COMMENT '영상 제목',
  area         VARCHAR(1000) NOT NULL COMMENT '운동 부위',
  center_name  VARCHAR(100)  NOT NULL COMMENT '요가원 이름(채널명과 동일)',
  view_cnt     INT           NOT NULL DEFAULT 0 COMMENT '조회수',
  reg_date     DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '영상 등록일',
  v_comment_id INT           NOT NULL DEFAULT 0 COMMENT '영상 댓글 고유 번호',

  PRIMARY KEY (video_key)
) COMMENT '영상 테이블';

ALTER TABLE video
  ADD CONSTRAINT UQ_video_key UNIQUE (video_key);

ALTER TABLE video
  ADD CONSTRAINT UQ_v_comment_id UNIQUE (v_comment_id);

CREATE TABLE video_comment
(
  v_comment_id      INT           NOT NULL AUTO_INCREMENT COMMENT '영상 댓글 고유 번호',
  v_comment_content VARCHAR(1000) NULL     COMMENT '영상 댓글 내용',
  v_comment_reg     DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '영상 댓글 등록일',
  delete_yn         VARCHAR(1)    NOT NULL DEFAULT 'N' COMMENT '삭제 여부',
  user_id           VARCHAR(30)   NOT NULL COMMENT '회원 아이디',
  video_key         INT           NOT NULL COMMENT '영상 고유 번호',
  PRIMARY KEY (v_comment_id)
) COMMENT '영상 댓글 테이블';

ALTER TABLE video_comment
  ADD CONSTRAINT UQ_v_comment_id UNIQUE (v_comment_id);

CREATE TABLE video_like
(
  like_id   INT         NOT NULL AUTO_INCREMENT,
  like_yn   VARCHAR(1)  NOT NULL,
  user_id   VARCHAR(30) NOT NULL COMMENT '회원 아이디',
  video_key INT         NOT NULL COMMENT '영상 고유 번호',
  PRIMARY KEY (like_id)
) COMMENT '찜 테이블';

ALTER TABLE video_like
  ADD CONSTRAINT UQ_like_id UNIQUE (like_id);

CREATE TABLE yoga_into
(
  center_location VARCHAR(100) NOT NULL COMMENT '요가원 지역',
  center_number   VARCHAR(13)  NOT NULL COMMENT '요가원 전화번호',
  center_time     TIME         NOT NULL COMMENT '요가원 운영 시간',
  center_name     VARCHAR(100) NOT NULL COMMENT '요가원 이름',
  PRIMARY KEY (center_name)
) COMMENT '요가원 리스트 테이블';

ALTER TABLE yoga_into
  ADD CONSTRAINT UQ_center_name UNIQUE (center_name);

ALTER TABLE video_like
  ADD CONSTRAINT FK_user_TO_video_like
    FOREIGN KEY (user_id)
    REFERENCES user (user_id);

ALTER TABLE diet
  ADD CONSTRAINT FK_user_TO_diet
    FOREIGN KEY (user_id)
    REFERENCES user (user_id);

ALTER TABLE video_comment
  ADD CONSTRAINT FK_video_TO_video_comment
    FOREIGN KEY (video_key)
    REFERENCES video (video_key)
    ON DELETE CASCADE;

ALTER TABLE profile
  ADD CONSTRAINT FK_video_TO_profile
    FOREIGN KEY (video_key)
    REFERENCES video (video_key);

ALTER TABLE diet
  ADD CONSTRAINT FK_food_list_TO_diet
    FOREIGN KEY (food_name)
    REFERENCES food_list (food_name);

ALTER TABLE video_like
  ADD CONSTRAINT FK_video_TO_video_like
    FOREIGN KEY (video_key)
    REFERENCES video (video_key)
    ON DELETE CASCADE;

