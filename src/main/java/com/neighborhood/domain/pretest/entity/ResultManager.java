package com.neighborhood.domain.pretest.entity;

import java.util.*;

public class ResultManager {
    public static EnumMap<TestType, Integer> getScoreMap(List<Integer> scores){
        EnumMap<TestType, Integer> scoreMap = new EnumMap<>(TestType.class);

        // 1번 질문
        scoreMap.put(TestType.FROZEN, scoreMap.getOrDefault(TestType.FROZEN, 0) + scores.get(0));
        // 2번 질문
        scoreMap.put(TestType.HIDDEN, scoreMap.getOrDefault(TestType.HIDDEN, 0) + scores.get(1));
        // 3번 질문
        scoreMap.put(TestType.AWKWARD, scoreMap.getOrDefault(TestType.AWKWARD, 0) + scores.get(2));
        // 4번 질문
        scoreMap.put(TestType.CONFUSED, scoreMap.getOrDefault(TestType.CONFUSED, 0) + scores.get(3));
        // 5번 질문
        scoreMap.put(TestType.STRONG, scoreMap.getOrDefault(TestType.STRONG, 0) + scores.get(4));
        // 6번 질문
        scoreMap.put(TestType.LOST, scoreMap.getOrDefault(TestType.LOST, 0) + scores.get(5));
        // 7번 질문
        scoreMap.put(TestType.THIRSTY, scoreMap.getOrDefault(TestType.THIRSTY, 0) + scores.get(6));
        // 8번 질문
        scoreMap.put(TestType.FROZEN, scoreMap.getOrDefault(TestType.FROZEN, 0) + scores.get(7));
        // 9번 질문
        scoreMap.put(TestType.HIDDEN, scoreMap.getOrDefault(TestType.HIDDEN, 0) + scores.get(8));
        // 10번 질문
        scoreMap.put(TestType.AWKWARD, scoreMap.getOrDefault(TestType.AWKWARD, 0) + scores.get(9));
        // 11번 질문
        scoreMap.put(TestType.CONFUSED, scoreMap.getOrDefault(TestType.CONFUSED, 0) + scores.get(10));
        // 12번 질문
        scoreMap.put(TestType.STRONG, scoreMap.getOrDefault(TestType.STRONG, 0) + scores.get(11));
        // 13번 질문
        scoreMap.put(TestType.LOST, scoreMap.getOrDefault(TestType.LOST, 0) + scores.get(12));
        // 14번 질문
        scoreMap.put(TestType.THIRSTY, scoreMap.getOrDefault(TestType.THIRSTY, 0) + scores.get(13));
        // 15번 질문
        scoreMap.put(TestType.FROZEN, scoreMap.getOrDefault(TestType.FROZEN, 0) + scores.get(14));
        // 16번 질문
        scoreMap.put(TestType.HIDDEN, scoreMap.getOrDefault(TestType.HIDDEN, 0) + scores.get(15));
        // 17번 질문
        scoreMap.put(TestType.AWKWARD, scoreMap.getOrDefault(TestType.AWKWARD, 0) + scores.get(16));
        // 18번 질문
        scoreMap.put(TestType.CONFUSED, scoreMap.getOrDefault(TestType.CONFUSED, 0) + scores.get(17));
        // 19번 질문
        scoreMap.put(TestType.STRONG, scoreMap.getOrDefault(TestType.STRONG, 0) + scores.get(18));
        // 20번 질문
        scoreMap.put(TestType.LOST, scoreMap.getOrDefault(TestType.LOST, 0) + scores.get(19));
        // 21번 질문
        scoreMap.put(TestType.THIRSTY, scoreMap.getOrDefault(TestType.THIRSTY, 0) + scores.get(20));
        // 22번 질문
        scoreMap.put(TestType.FROZEN, scoreMap.getOrDefault(TestType.FROZEN, 0) + scores.get(21));
        // 23번 질문
        scoreMap.put(TestType.HIDDEN, scoreMap.getOrDefault(TestType.HIDDEN, 0) + scores.get(22));
        // 24번 질문
        scoreMap.put(TestType.AWKWARD, scoreMap.getOrDefault(TestType.AWKWARD, 0) + scores.get(23));
        // 25번 질문
        scoreMap.put(TestType.CONFUSED, scoreMap.getOrDefault(TestType.CONFUSED, 0) + scores.get(24));
        // 26번 질문
        scoreMap.put(TestType.STRONG, scoreMap.getOrDefault(TestType.STRONG, 0) + scores.get(25));
        // 27번 질문
        scoreMap.put(TestType.LOST, scoreMap.getOrDefault(TestType.LOST, 0) + scores.get(26));
        // 28번 질문
        scoreMap.put(TestType.THIRSTY, scoreMap.getOrDefault(TestType.THIRSTY, 0) + scores.get(27));

        return scoreMap;
    }

    public static String getType(Map<TestType, Integer> scoreMap) {
        // enumMap을 value기준으로 내림차순 정렬 -> 가장 높은 점수의 유형이 맨 위에 오게하고 맨 위에것만 리턴
        List<Map.Entry<TestType, Integer>> entryList = new LinkedList<>(scoreMap.entrySet());
        entryList.sort(new Comparator<Map.Entry<TestType, Integer>>() {
            @Override
            public int compare(Map.Entry<TestType, Integer> o1, Map.Entry<TestType, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        return entryList.get(0).getKey().toString();
    }

    public static Long matchTypeImage(String resultType) { // 리턴값으로 맞는 이미지의 pk값
        Long strongId = 1L;
        Long awkwardId = 2L;
        Long lostId = 3L;
        Long frozenId = 4L;
        Long thirstyId = 5L;
        Long confusedId = 6L;
        Long hiddenId = 7L;

        if(resultType.equals(TestType.STRONG.toString())) return strongId;
        else if(resultType.equals(TestType.AWKWARD.toString())) return awkwardId;
        else if(resultType.equals(TestType.LOST.toString())) return lostId;
        else if(resultType.equals(TestType.FROZEN.toString())) return frozenId;
        else if(resultType.equals(TestType.THIRSTY.toString())) return thirstyId;
        else if(resultType.equals(TestType.CONFUSED.toString())) return confusedId;
        else if(resultType.equals(TestType.HIDDEN.toString())) return hiddenId;

        else throw new IllegalArgumentException("유효하지 않은 유형입니다: " + resultType);
    }
}