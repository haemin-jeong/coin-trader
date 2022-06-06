package com.springbom.cointrader.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class StreamUtils {

    /**
     * Stream API 사용 시에 객체의 특정 필드를 기준으로 중복을 제거할 때 사용
     */
    public static <T> Predicate<T> distinctByField(Function<? super T, ?> fieldExtractor) {
        Map<Object, Boolean> seen = new HashMap<>();
        return t -> seen.putIfAbsent(fieldExtractor.apply(t), Boolean.TRUE) == null;
    }
}
