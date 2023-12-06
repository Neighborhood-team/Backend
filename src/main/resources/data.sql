-- 유형 테스트 결과 이미지 --
INSERT INTO type_image (image_id, image_name, image_url) VALUES(1, 'Strong.png', 'https://capstone-project-bucket.s3.ap-northeast-2.amazonaws.com/type-images/Strong.png')
INSERT INTO type_image (image_id, image_name, image_url) VALUES(2, 'Awkward.png', 'https://capstone-project-bucket.s3.ap-northeast-2.amazonaws.com/type-images/Awkward.png')
INSERT INTO type_image (image_id, image_name, image_url) VALUES(3, 'Lost.png', 'https://capstone-project-bucket.s3.ap-northeast-2.amazonaws.com/type-images/Lost.png')
INSERT INTO type_image (image_id, image_name, image_url) VALUES(4, 'Frozen.png', 'https://capstone-project-bucket.s3.ap-northeast-2.amazonaws.com/type-images/Frozen.png')
INSERT INTO type_image (image_id, image_name, image_url) VALUES(5, 'Thirsty.png', 'https://capstone-project-bucket.s3.ap-northeast-2.amazonaws.com/type-images/Thirsty.png')
INSERT INTO type_image (image_id, image_name, image_url) VALUES(6, 'Confused.png', 'https://capstone-project-bucket.s3.ap-northeast-2.amazonaws.com/type-images/Confused.png')
INSERT INTO type_image (image_id, image_name, image_url) VALUES(7, 'Hidden.png', 'https://capstone-project-bucket.s3.ap-northeast-2.amazonaws.com/type-images/Hidden.png')

-- 가족 --
INSERT INTO family (family_id, family_code, question_num, question_updated_time, today_question_type) VALUES (1, 'ABC123', 0, '2023-11-26', 'FROZEN')
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

--문장복사기--
INSERT INTO context_copy (context_id, context, context_role, context_title) VALUES(1, '하루를 응원하는 한마디는 하루 전체에 활력을 불어넣어줘요! 가족의 활력을 문구 하나로 가져와 볼까요?','PARENT','오늘도 모두 힘을 내서 하루를 보내보자!')
INSERT INTO context_copy (context_id, context, context_role, context_title) VALUES(2, '가족이 건강하게 밥은 잘 먹고 다니는지 궁금하지 않나요? 무엇을 먹고 다니는지 물어보면 더 좋아요!','PARENT','아무리 바빠도 끼니는 잘 챙겨 먹도록 하자!')
INSERT INTO context_copy (context_id, context, context_role, context_title) VALUES(3, '날씨가 많이 춥더라도, 어떤 옷을 입었더라도 가족의 걱정어린 말은 몸과 마음을 따듯하게 만들어줘요!','PARENT','날씨가 많이 추우니 옷 따뜻하게 입고 다녀라.')
INSERT INTO context_copy (context_id, context, context_role, context_title) VALUES(4, '자식이 부모에게 힘든 일을 잘 못 털어놓나요? 자신이 항상 관심을 가지고 있다는 것을 표현해보세요!','PARENT','요즘 힘든 일은 없니?')
INSERT INTO context_copy (context_id, context, context_role, context_title) VALUES(5, '공부하느라, 일하느라 하고 싶은 일을 못하고 있을 자식들은 요즘 무엇을 하고 싶을까요?','PARENT','요즘 하고 싶은 일은 없니?')
INSERT INTO context_copy (context_id, context, context_role, context_title) VALUES(6, '자식들의 98%는 이 말을 들으면 흐뭇해한다고 합니다. 사소한 웃음으로 하루를 지낼 수 있도록 해보세요!','PARENT','오늘도 좋은 하루 보내렴.')
INSERT INTO context_copy (context_id, context, context_role, context_title) VALUES(7, '가족을 위해 항상 고생하는 상대방을 위해 고생했다는 말을 전해보는 것은 어떨까요?','PARENT','여보, 오늘도 고생했어요.')
INSERT INTO context_copy (context_id, context, context_role, context_title) VALUES(8, '사랑한다는 말이 낯간지러워도, 평소에 하는 말이라도 한 번 더 표현해봅시다!','PARENT','늘 변함없이 당신을 사랑해요.')
INSERT INTO context_copy (context_id, context, context_role, context_title) VALUES(9, '오늘은 삶을 함께하는 상대방에게 고마움을 표현해보세요! 어떤 관계든 작은 행복을 얻게 될 거예요.','PARENT','당신은 존재만으로도 고마운 사람이야.')
INSERT INTO context_copy (context_id, context, context_role, context_title) VALUES(10, '사랑한다는 말은 부모님이 가장 듣고싶은 말이자 좋아하는 말이에요. 부끄러워서 잘 전하지 못했다면 복사/붙여넣기로 눈 딱 감고 보내보는 건 어떨까요? ','CHILD','엄마, 아빠! 오늘도 사랑해요~')
INSERT INTO context_copy (context_id, context, context_role, context_title) VALUES(11, '자식의 이 말을 들은 98%의 부모님들이 하루가 행복했다고 답했어요. 오늘 하루가 행복해지도록 연락을 드려보세요!','CHILD','오늘도 좋은 하루 보내세요!')
INSERT INTO context_copy (context_id, context, context_role, context_title) VALUES(12, '부모님의 피로는 어떠한 것보다 자식의 응원이 효과만점! 문장을 복사하고 부모님의 피로를 풀어드리세요!','CHILD','오늘 하루도 고생하셨어요!')
INSERT INTO context_copy (context_id, context, context_role, context_title) VALUES(13, '날씨가 많이 춥더라도, 어떤 옷을 입었더라도 자식의 걱정어린 말은 몸과 마음을 따듯하게 만들어줘요!','CHILD','날씨가 많이 추우니 옷 따뜻하게 입으세요!')
INSERT INTO context_copy (context_id, context, context_role, context_title) VALUES(14, '부모님은 힘든 일이 있어도 자식에게 잘 드러내지 않아요. 오늘은 먼저 연락해 부모님의 짐을 덜어드려보는 건 어떨까요?','CHILD','엄마, 요즘 힘든 일은 없으세요?')
INSERT INTO context_copy (context_id, context, context_role, context_title) VALUES(15, '부모님은 힘든 일이 있어도 자식에게 잘 드러내지 않아요. 오늘은 먼저 연락해 부모님의 짐을 덜어드려보는 건 어떨까요?','CHILD','아빠, 요즘 힘든 일은 없으세요?')
INSERT INTO context_copy (context_id, context, context_role, context_title) VALUES(16, '자식을 키우느라 배우고 싶은 일을 미뤄뒀던 부모님들이 많다고 합니다! 혹시 우리 부모님은 지금 배우고 싶은 게 있을까요?','CHILD','엄마, 요즘 배우고 싶은 일은 없으세요?')
INSERT INTO context_copy (context_id, context, context_role, context_title) VALUES(17, '자식을 키우느라 배우고 싶은 일을 미뤄뒀던 부모님들이 많다고 합니다! 혹시 우리 부모님은 지금 배우고 싶은 게 있을까요?','CHILD','아빠, 요즘 배우고 싶은 일은 없으세요?')
INSERT INTO context_copy (context_id, context, context_role, context_title) VALUES(18, '가족의 응원은 그 무엇보다도 힘이 됩니다. 가족이 하루를 잘 보낼 수 있도록 연락을 드려보세요!','CHILD','오늘 하루도 모두 힘내세요!')

-- 개인정보 --
INSERT INTO personal_info (personal_info_id, birthday_gift, blood_type, hates, interests, likes, mbti, motto, my_sizes, place_to_trip, role_in_fam, member_id) VALUES(1, '상품권', 'A형', '바퀴벌레', '돈,집', '수박', 'ENFP', '하면 된다', '178cm 100/30 260cm', '보라카이', '분위기 메이커', 2)

-- 비상연락망 --
INSERT INTO emergency_contact (emergency_contact_id, name, phone, member_id) VALUES(1, '정세풍', '01012345678', 2)