select max(BOARD_NUM) from board;

INSERT INTO web_gradle_erp.board
(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF)
VALUES(1, '짱수린', '1111', '마칠시간', '5시', 'test.txt', 0);


-- list paging
/*
 * [1] [2] [3]
 * (page - 1) * 10 >> page1은 0부터, page2는 10부터, page3은 20부터
 */
select BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE 
  from board 
 order by BOARD_RE_REF desc, BOARD_RE_SEQ asc limit 0, 10; -- desc는 최신순, 그게 안되면 답글 순을 의미. limit은 0번부터 10개를 의미 (다음 2페이지는 10부터 10개여야할것)

desc board;

select count(*) from board;

-- article read count << select article과 transaction해서 들어가야됨
select * from board;

update board
   set BOARD_READCOUNT = BOARD_READCOUNT + 1
 where BOARD_NUM = 21;
 
-- 글 삭제
select * from board where BOARD_NUM = 23;

delete from board where board_num = 23;

-- 글쓴이 확인
select 1 from board where BOARD_NUM = 23 and BOARD_PASS = '1111'; -- 정확히 두개 다 일치하면 1이 나오고, 하나라도 다르면 null 값이 넘어옴

-- 글 수정
select BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE from board;
update board set BOARD_SUBJECT = '짱짱짱수린', BOARD_CONTENT = '짱짱이다...' where BOARD_NUM = 20;

-- 21번 글에 대한 답변
update board set BOARD_RE_SEQ = BOARD_RE_SEQ + 1 where BOARD_RE_REF = 21 and BOARD_RE_SEQ > 0;
insert into board(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ) values(21, '짱수린2', '1111', '위장아파,,,', '피곤허다', '', 21, 1, 1);


select * from board where BOARD_RE_REF = 19; -- 답변글일때는 board_num 이 아니라 board_re_ref로 검색 들어가야됨

select * from board;

