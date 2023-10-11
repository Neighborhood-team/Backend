package com.neighborhood.domain.pretest;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;

// Strong : 강한 아이
// Awkward : 어색한 아이
// Lost : 헤매는 아이
// Frozen : 얼어붙은 아이
// Thirsty : 목 마른 아이
// Confused : 혼란스러운 아이
// Hidden : 숨겨진 아이

public class ResultManager {
    private static final String STRONG = "Strong";
    private static final String AWKWARD = "Awkward";
    private static final String LOST = "Lost";
    private static final String FROZEN = "Frozen";
    private static final String THIRSTY = "Thirsty";
    private static final String CONFUSED = "Confused";
    private static final String HIDDEN = "Hidden";
    public static Map<String, Integer> getScoreMap(List<Integer> scores){
        Map<String, Integer> scoreMap = new HashMap<>();

        // 1번 질문
        scoreMap.put(FROZEN, scoreMap.getOrDefault(FROZEN, 0) + scores.get(0));
        // 2번 질문
        scoreMap.put(HIDDEN, scoreMap.getOrDefault(HIDDEN, 0) + scores.get(1));
        // 3번 질문
        scoreMap.put(AWKWARD, scoreMap.getOrDefault(AWKWARD, 0) + scores.get(2));
        // 4번 질문
        scoreMap.put(CONFUSED, scoreMap.getOrDefault(CONFUSED, 0) + scores.get(3));
        // 5번 질문
        scoreMap.put(STRONG, scoreMap.getOrDefault(STRONG, 0) + scores.get(4));
        // 6번 질문
        scoreMap.put(LOST, scoreMap.getOrDefault(LOST, 0) + scores.get(5));
        // 7번 질문
        scoreMap.put(THIRSTY, scoreMap.getOrDefault(THIRSTY, 0) + scores.get(6));
        // 8번 질문
        scoreMap.put(FROZEN, scoreMap.getOrDefault(FROZEN, 0) + scores.get(7));
        // 9번 질문
        scoreMap.put(HIDDEN, scoreMap.getOrDefault(HIDDEN, 0) + scores.get(8));
        // 10번 질문
        scoreMap.put(AWKWARD, scoreMap.getOrDefault(AWKWARD, 0) + scores.get(9));
        // 11번 질문
        scoreMap.put(CONFUSED, scoreMap.getOrDefault(CONFUSED, 0) + scores.get(10));
        // 12번 질문
        scoreMap.put(STRONG, scoreMap.getOrDefault(STRONG, 0) + scores.get(11));
        // 13번 질문
        scoreMap.put(LOST, scoreMap.getOrDefault(LOST, 0) + scores.get(12));
        // 14번 질문
        scoreMap.put(THIRSTY, scoreMap.getOrDefault(THIRSTY, 0) + scores.get(13));
        // 15번 질문
        scoreMap.put(FROZEN, scoreMap.getOrDefault(FROZEN, 0) + scores.get(14));
        // 16번 질문
        scoreMap.put(HIDDEN, scoreMap.getOrDefault(HIDDEN, 0) + scores.get(15));
        // 17번 질문
        scoreMap.put(AWKWARD, scoreMap.getOrDefault(AWKWARD, 0) + scores.get(16));
        // 18번 질문
        scoreMap.put(CONFUSED, scoreMap.getOrDefault(CONFUSED, 0) + scores.get(17));
        // 19번 질문
        scoreMap.put(STRONG, scoreMap.getOrDefault(STRONG, 0) + scores.get(18));
        // 20번 질문
        scoreMap.put(LOST, scoreMap.getOrDefault(LOST, 0) + scores.get(19));
        // 21번 질문
        scoreMap.put(THIRSTY, scoreMap.getOrDefault(THIRSTY, 0) + scores.get(20));
        // 22번 질문
        scoreMap.put(FROZEN, scoreMap.getOrDefault(FROZEN, 0) + scores.get(21));
        // 23번 질문
        scoreMap.put(HIDDEN, scoreMap.getOrDefault(HIDDEN, 0) + scores.get(22));
        // 24번 질문
        scoreMap.put(AWKWARD, scoreMap.getOrDefault(AWKWARD, 0) + scores.get(23));
        // 25번 질문
        scoreMap.put(CONFUSED, scoreMap.getOrDefault(CONFUSED, 0) + scores.get(24));
        // 26번 질문
        scoreMap.put(STRONG, scoreMap.getOrDefault(STRONG, 0) + scores.get(25));
        // 27번 질문
        scoreMap.put(LOST, scoreMap.getOrDefault(LOST, 0) + scores.get(26));
        // 28번 질문
        scoreMap.put(THIRSTY, scoreMap.getOrDefault(THIRSTY, 0) + scores.get(27));

        return scoreMap;
    }

    public static String getType(Map<String, Integer> scoreMap) {
        // 맵을 value기준으로 내림차순 정렬 -> 가장 높은 점수의 유형이 맨 위에 오게하고 맨 위에것만 리턴
        List<Map.Entry<String, Integer>> entryList = new LinkedList<>(scoreMap.entrySet());
        entryList.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        return entryList.get(0).getKey();
    }

    public static String generateResultCode(int size) {
        String randomCode = RandomStringUtils.randomAlphanumeric(size);

        return randomCode;
    }
}
