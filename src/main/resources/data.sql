-- 유형 테스트 결과 이미지 --
INSERT INTO type_image (image_id, image_name, image_url) VALUES(1, 'Strong.png', 'https://capstone-project-bucket.s3.ap-northeast-2.amazonaws.com/type-images/Strong.png')
INSERT INTO type_image (image_id, image_name, image_url) VALUES(2, 'Awkward.png', 'https://capstone-project-bucket.s3.ap-northeast-2.amazonaws.com/type-images/Awkward.png')
INSERT INTO type_image (image_id, image_name, image_url) VALUES(3, 'Lost.png', 'https://capstone-project-bucket.s3.ap-northeast-2.amazonaws.com/type-images/Lost.png')
INSERT INTO type_image (image_id, image_name, image_url) VALUES(4, 'Frozen.png', 'https://capstone-project-bucket.s3.ap-northeast-2.amazonaws.com/type-images/Frozen.png')
INSERT INTO type_image (image_id, image_name, image_url) VALUES(5, 'Thirsty.png', 'https://capstone-project-bucket.s3.ap-northeast-2.amazonaws.com/type-images/Thirsty.png')
INSERT INTO type_image (image_id, image_name, image_url) VALUES(6, 'Confused.png', 'https://capstone-project-bucket.s3.ap-northeast-2.amazonaws.com/type-images/Confused.png')
INSERT INTO type_image (image_id, image_name, image_url) VALUES(7, 'Hidden.png', 'https://capstone-project-bucket.s3.ap-northeast-2.amazonaws.com/type-images/Hidden.png')

-- 가족 --
INSERT INTO family (family_id, family_code, question_num, question_updated_time, today_question_type) VALUES (1, 'ABC123', null, null, null)
-- 가족 유형 점수 총합 --
INSERT INTO family_type_score (score_id, score, test_type, family_id) VALUES (1, 0, 'STRONG', 1)
INSERT INTO family_type_score (score_id, score, test_type, family_id) VALUES (2, 0, 'AWKWARD', 1)
INSERT INTO family_type_score (score_id, score, test_type, family_id) VALUES (3, 0, 'LOST', 1)
INSERT INTO family_type_score (score_id, score, test_type, family_id) VALUES (4, 0, 'FROZEN', 1)
INSERT INTO family_type_score (score_id, score, test_type, family_id) VALUES (5, 0, 'THIRSTY', 1)
INSERT INTO family_type_score (score_id, score, test_type, family_id) VALUES (6, 0, 'CONFUSED', 1)
INSERT INTO family_type_score (score_id, score, test_type, family_id) VALUES (7, 0, 'HIDDEN', 1)


-- 회원 --
INSERT INTO member (member_id, birth_date, created_date, modified_date, phone, family_id, name, family_role) VALUES (1, '1972-03-14', '2022-09-17 12:52:30.000000', null, '01093401333', 1, "이동환", "MOM")
INSERT INTO member (member_id, birth_date, created_date, modified_date, phone, family_id, name, family_role) VALUES (2, '1971-06-21','2022-09-18 12:52:30.000000', null, '01023452345', 1, "정세창", "DAD")
INSERT INTO member (member_id, birth_date, created_date, modified_date, phone, family_id, name, family_role) VALUES (3, '1999-06-21', '2022-09-19 12:52:30.000000', null, '01012345678', 1, "한승준", "SON")

-- 유형 테스트 결과 --
INSERT INTO result (created_date, result_code, result_type, type_number, member_id) VALUES ('2022-11-17 12:42:13.000000', 'D1F2G3', 'STRONG', 0, 1)
INSERT INTO result_type_scores (result_result_id, type_scores, type_scores_key) VALUES(1, 8, 0)
INSERT INTO result_type_scores (result_result_id, type_scores, type_scores_key) VALUES(1, 8, 1)
INSERT INTO result_type_scores (result_result_id, type_scores, type_scores_key) VALUES(1, 8, 2)
INSERT INTO result_type_scores (result_result_id, type_scores, type_scores_key) VALUES(1, 8, 3)
INSERT INTO result_type_scores (result_result_id, type_scores, type_scores_key) VALUES(1, 8, 4)
INSERT INTO result_type_scores (result_result_id, type_scores, type_scores_key) VALUES(1, 8, 5)
INSERT INTO result_type_scores (result_result_id, type_scores, type_scores_key) VALUES(1, 8, 6)

INSERT INTO result (created_date, result_code, result_type, type_number, member_id) VALUES ('2022-11-17 12:42:13.000000', 'F4G5GH', 'FROZEN', 3, 2)
INSERT INTO result_type_scores (result_result_id, type_scores, type_scores_key) VALUES(2, 11, 0)
INSERT INTO result_type_scores (result_result_id, type_scores, type_scores_key) VALUES(2, 11, 1)
INSERT INTO result_type_scores (result_result_id, type_scores, type_scores_key) VALUES(2, 12, 2)
INSERT INTO result_type_scores (result_result_id, type_scores, type_scores_key) VALUES(2, 14, 3)
INSERT INTO result_type_scores (result_result_id, type_scores, type_scores_key) VALUES(2, 13, 4)
INSERT INTO result_type_scores (result_result_id, type_scores, type_scores_key) VALUES(2, 10, 5)
INSERT INTO result_type_scores (result_result_id, type_scores, type_scores_key) VALUES(2, 11, 6)

-- 오늘의 질문 목록 --
INSERT INTO today_question (question_id, content, sub_text, type) VALUES(1, '우리 가족 중 가장 이성적인 사람은 누구인가요?', '이유와 함께 답변을 작성해주세요!', 'FROZEN')
INSERT INTO today_question (question_id, content, sub_text, type) VALUES(2, '오늘 가장 기뻤던 일은 무엇인가요?', '이유와 함께 답변을 작성해주세요!', 'FROZEN')
INSERT INTO today_question (question_id, content, sub_text, type) VALUES(3, '지인들에게는 말하지 못했던 나의 컴플렉스가 있다면?', '사소한 것도 상관 없어요!', 'HIDDEN')
INSERT INTO today_question (question_id, content, sub_text, type) VALUES(4, '우리 가족은 만나면 이야기를 많이 나누는 편인가요?', '이유와 함께 답변을 작성해주세요!', 'HIDDEN')
INSERT INTO today_question (question_id, content, sub_text, type) VALUES(5, '우리 가족은 돌려 말하지 않고 솔직하게 얘기하는 편인가요?', '이유와 함께 답변을 작성해주세요!', 'AWKWARD')
INSERT INTO today_question (question_id, content, sub_text, type) VALUES(6, '가장 최근에 누가, 왜 기분이 안 좋아 보였나요?', '이유와 함께 답변을 작성해주세요!', 'AWKWARD')
INSERT INTO today_question (question_id, content, sub_text, type) VALUES(7, '가족이 내 말을 들어주지 않았던 경험이 있다면 공유해주세요!', '사소한 것도 상관 없어요!', 'CONFUSED')
INSERT INTO today_question (question_id, content, sub_text, type) VALUES(8, '우리 가족에게 생겼으면 하는 규칙이 있다면?', '이유와 함께 답변을 작성해주세요!', 'CONFUSED')
INSERT INTO today_question (question_id, content, sub_text, type) VALUES(9, '우리 가족은 싸우면 잘 화해하는 편일까요?', '이유와 함께 답변을 작성해주세요!', 'STRONG')
INSERT INTO today_question (question_id, content, sub_text, type) VALUES(10, '우리 가족에게는 어떤 문제가 있을까요?', '이유와 함께 답변을 작성해주세요!', 'STRONG')
INSERT INTO today_question (question_id, content, sub_text, type) VALUES(11, '요즘 아빠의 최대 관심사는 무엇일까요?', '이유와 함께 답변을 작성해주세요!', 'LOST')
INSERT INTO today_question (question_id, content, sub_text, type) VALUES(12, '우리 집은 업무 분담이 잘 되는 편이다?', '이유와 함께 답변을 작성해주세요!', 'LOST')
INSERT INTO today_question (question_id, content, sub_text, type) VALUES(13, '오늘 가장 힘들었던 일은 무엇인가요?', '사소한 것도 상관 없어요!', 'THIRSTY')
INSERT INTO today_question (question_id, content, sub_text, type) VALUES(14, '이번 주 나의 목표 한 가지만 말하자면?', '사소한 것도 상관 없어요!', 'THIRSTY')
INSERT INTO today_question (question_id, content, sub_text, type) VALUES(15, '요즘 날 가장 힘들게 하는 것은 무엇인가요?', '사소한 것도 상관 없어요!', 'THIRSTY')

-- 오늘의 기분 목록 --
INSERT INTO today_mood (today_mood_id, message, mood, member_id) VALUES(1, '오늘은 기분이 좋은 날이에요', 'happy',1)
INSERT INTO today_mood (today_mood_id, message, mood, member_id) VALUES(2, '8시에 학원가요 연락안돼요', 'excited',2)
INSERT INTO today_mood (today_mood_id, message, mood, member_id) VALUES(3, '오늘 외식 잊지말고 들어오세요', 'anxiety',3)

-- 스케줄 --
INSERT INTO schedule (schedule_id, start_date, end_date, content, member_id) VALUES(1, '2023-01-12', '2023-01-12', '저녁 약속', 1)
INSERT INTO schedule (schedule_id, start_date, end_date, content, member_id) VALUES(2, '2023-01-17', '2023-02-01', '유럽 여행', 2)
INSERT INTO schedule (schedule_id, start_date, end_date, content, member_id) VALUES(3, '2023-02-10', '2023-02-11', '출장', 1)
INSERT INTO schedule (schedule_id, start_date, end_date, content, member_id) VALUES(4, '2023-12-14', '2023-02-14', '골프', 1)
INSERT INTO schedule (schedule_id, start_date, end_date, content, member_id) VALUES(5, '2023-03-10', '2023-03-10', '생일', 3)