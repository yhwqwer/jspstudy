-- 목록
SELECT stu_no, name, kor, eng, math, ave, mark
  FROM student_t
 ORDER BY stu_no;

-- 전체 학생 수
SELECT COUNT(*)
  FROM student_t;

-- 전체 점수 평균
SELECT NVL(AVG(ave), 0)
  FROM student_t;

-- 학생 등록
INSERT INTO student_t (
    stu_no
  , name
  , kor
  , eng
  , math
  , ave
  , mark
) VALUES (
    STUDENT_SEQ.NEXTVAL
  , '이름'
  , 100
  , 90
  , 80
  , (100 + 90 + 80) / 3
  , CASE
      WHEN (100 + 90 + 80) / 3 >= 90 THEN 'A'
      WHEN (100 + 90 + 80) / 3 >= 80 THEN 'B'
      WHEN (100 + 90 + 80) / 3 >= 70 THEN 'C'
      WHEN (100 + 90 + 80) / 3 >= 60 THEN 'D'
      ELSE 'F'
    END
 );

-- 평균 범위 조회 (학생 정보)
SELECT stu_no, name, kor, eng, math, ave, mark
  FROM student_t
 WHERE ave BETWEEN 50 AND 100;

-- 평균 범위 조회 (학생 수)
SELECT COUNT(*)
  FROM student_t
 WHERE ave BETWEEN 50 AND 100;

-- 평균 범위 조회 (점수 평균)
SELECT NVL(AVG(ave), 0)
  FROM student_t
 WHERE ave BETWEEN 50 AND 100;

-- 상세 보기
SELECT stu_no, name, kor, eng, math, ave, mark
  FROM student_t
 WHERE stu_no = 1;

-- 학생 정보 수정
UPDATE student_t
   SET name = '수정이름'
     , kor = 100
     , eng = 90
     , math = 80
     , ave = (100 + 90 + 80) / 3
     , mark = 
        (CASE
           WHEN (100 + 90 + 80) / 3 >= 90 THEN 'A'
           WHEN (100 + 90 + 80) / 3 >= 80 THEN 'B'
           WHEN (100 + 90 + 80) / 3 >= 70 THEN 'C'
           WHEN (100 + 90 + 80) / 3 >= 60 THEN 'D'
           ELSE 'F'
         END)
 WHERE stu_no = 1;

-- TOP3
SELECT stu_no, name, kor, eng, math, ave, mark
  FROM (SELECT stu_no, name, kor, eng, math, ave, mark, RANK() OVER(ORDER BY ave DESC) AS rnk
          FROM student_t)
 WHERE rnk <= 3;