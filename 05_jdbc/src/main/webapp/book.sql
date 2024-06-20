
DROP TABLE book_t;
CREATE TABLE book_t (
  book_no NUMBER             NOT NULL,
  title   VARCHAR2(100 BYTE) NOT NULL,
  author  VARCHAR2(100 BYTE),
  price   NUMBER,
  CONSTRAINT pk_book PRIMARY KEY(book_no)
);

-- 번호표 기계 : 시퀀스
DROP SEQUENCE book_seq;
CREATE SEQUENCE book_seq;

-- 번호표 뽑기 : nextval
-- SELECT book_seq.nextval FROM dual;
-- SELECT book_seq.nextval FROM dual;
-- SELECT book_seq.nextval FROM dual;

-- 신규 책 추가하기
INSERT INTO book_t(book_no, title, author, price) VALUES(book_seq.nextval, '제목1', '저자1', 10);
INSERT INTO book_t(book_no, title, author, price) VALUES(book_seq.nextval, '제목2', '저자2', 20);
INSERT INTO book_t(book_no, title, author, price) VALUES(book_seq.nextval, '제목3', '저자3', 30);
INSERT INTO book_t(book_no, title, author, price) VALUES(book_seq.nextval, '제목4', '저자4', 40);
INSERT INTO book_t(book_no, title, author, price) VALUES(book_seq.nextval, '제목5', '저자5', 50);
COMMIT;

-- 기존 책 수정하기
-- UPDATE book_t SET title = '수정제목', author = '수정저자', price = 987 WHERE book_no = 1;
-- COMMIT;

-- 기존 책 삭제하기
-- DELETE FROM book_t WHERE book_no = 1;
-- COMMIT;

-- 전체 책 조회하기
SELECT book_no, title, author, price FROM book_t;

-- 특정 책 조회하기
SELECT book_no, title, author, price FROM book_t WHERE book_no = 3;
