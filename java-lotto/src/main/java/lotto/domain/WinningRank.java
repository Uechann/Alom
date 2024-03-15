package lotto.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public enum WinningRank {

    LAST_PLACE(0, false, 0),
    FIFTH_PLACE(3, false, 5_000),
    FOURTH_PLACE(4, false, 50_000),
    THIRD_PLACE(5, false, 1_500_000),
    SECOND_PLACE(5, true, 30_000_000),
    FIRST_PLACE(6, false, 2_000_000_000);

    private final int matchingCount;
    private final boolean containsBonusNumber;
    private final int winningPrice;

    WinningRank(int matchingCount, boolean containsBonusNumber, int winningPrice) {
        this.matchingCount = matchingCount;
        this.containsBonusNumber = containsBonusNumber;
        this.winningPrice = winningPrice;
    }//생성자로써 매칭 개수, 보너스 숫자의 유무, 당첨 금액을 저장

    public static WinningRank findWinningRank(int matchingCount, boolean containsBonusNumber) {
        return Arrays.stream(values())
                .filter(winningRank -> winningRank.matchingCount == matchingCount)
                .filter(winningRank -> winningRank.containsBonusNumber == containsBonusNumber)
                .findFirst()
                .orElse(WinningRank.LAST_PLACE);
    }//당첨 순위를 반환해주는 메소드
     //매개변수로 매칭 개수와, 보너스 숫자의 유무를 받고
     //당첨 랭크 중에 strea()함수를 이용해서
     //매개변수로 전달 받은 매칭 개수가 같고
     //보너스 숫자의 유무가 같은 당첨 랭크를 반환하고
     //그 이외에는 LAST_PLACE 랭크를 반환한다.

    public int getMatchingCount() {
        return matchingCount;
    }//매칭 개수를 반환하는 메소드

    public int getWinningPrice() {
        return winningPrice;
    }//당첨 금액을 반환하는 메소드
}
