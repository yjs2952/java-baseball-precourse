package domain;

import utils.NumberUtils;

import java.util.*;

public class GameNumberGenerator implements NumberGenerator {

    private static final Random RANDOM = new Random();
    private static final int BOUND = 8;

    @Override
    public GameNumbers generate() {
        Set<Integer> set = new HashSet<>();

        while (set.size() < 3) {
            set.add(RANDOM.nextInt(BOUND) + 1); // 1 ~ 9
        }

        return GameNumbers.of(NumberUtils.toList(set));
    }
}
