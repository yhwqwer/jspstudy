DROP TABLE student_t;
CREATE TABLE student_t (
    stu_no NUMBER    NOT NULL,
    name   VARCHAR2(100 BYTE),
    kor    NUMBER(3) CHECK(kor BETWEEN 0 AND 100),
    eng    NUMBER(3) CHECK(eng BETWEEN 0 AND 100),
    math   NUMBER(3) CHECK(math BETWEEN 0 AND 100),
    ave    NUMBER(5,2),
    mark   CHAR(1 BYTE),
    CONSTRAINT ps_student PRIMARY KEY(stu_no)
);

DROP SEQUENCE student_seq;
CREATE SEQUENCE student_seq;