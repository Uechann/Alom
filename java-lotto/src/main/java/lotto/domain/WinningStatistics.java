package lotto.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class WinningStatistics {

    public static final int AT_LEAST_THIRD_PLACE =5;
    public static final int PERCENTAGE =100;
    public static final int INITIAL_VALUE =0;

    public static Map<WinningRank, Integer> getWinningDetails(Lottos lottos, WinningLotto winningLotto) {
        Map<WinningRank, Integer> winningDetails = generateWinningDetails();
        for (Lotto lotto : lottos.getLottos()) {
            int matchingCount = compareNumberWithWinningNumbers(lotto, winningLotto);
            boolean containsBonusNumber = compareNumberWithBonusNumber(lotto, winningLotto, matchingCount);
            WinningRank winningRank = WinningRank.findWinningRank(matchingCount, containsBonusNumber);
            winningDetails.replace(winningRank, winningDetails.get(winningRank) + 1);
        }
        return winningDetails;
    }//key와 value값을 같은 Map 콜렉션 당첨 세부사항을 반환하는 함수로써
     //lotto 들의 배열, 당첨 lotto 클래스를 매개변수로 전달받고
     //반복문을 통해 구매한 로또들과 당첨 로또 번호를 비교, 보너스 숫자 비교 후
     //매칭 개수, 보너스 숫자 유무에 해당하는 당첨 랭크를 값을 하나씩 추가해서
     //당첨 세부 내역을 반환하다.

    public static Map<WinningRank, Integer> generateWinningDetails() {
        Map<WinningRank, Integer> winningDetails = new EnumMap<>(WinningRank.class);
        Arrays.stream(WinningRank.values()).forEach(winningRank -> winningDetails.put(winningRank, INITIAL_VALUE));
        return winningDetails;
    }//당첨 세부내역을 생성하는 메소드
     //

    private static int compareNumberWithWinningNumbers(Lotto lotto, WinningLotto winningLotto) {
        List<Integer> numbers = lotto.getNumbers();
        List<Integer> winningNumbers = winningLotto.getWinningNumbers();
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }//당첨 숫자와 비교하는 메소드로써
     //Lotto 클래스와 winningLotto 클래스를 매개변수로 전달받고
     //당첨숫자와 몇개가 맞는지 stream().filter().count()로 int형을 받환한다.

    private static boolean compareNumberWithBonusNumber(Lotto lotto, WinningLotto winninglotto, int matchingCount) {
        if(matchingCount != AT_LEAST_THIRD_PLACE) {
            return false;
        }
        List<Integer> numbers = lotto.getNumbers();
        int bonusNumber = winninglotto.getBonusNumber();
        return numbers.contains(bonusNumber);
    }//보너스 숫자와 비교하는 메소드로써
     //Lotto 클래스와 WinningLotto 클래스 매칭 개수를 매개변수로 전달받고
     //매칭 개수가 5가 아니면 false를 반환하고
     //5개 이면 보너스 숫자화 비교하여 boolean 값을 반환한다.

    public static long getWinningAmount(Map<WinningRank, Integer> winningDetails) {
        return winningDetails.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getWinningPrice() * entry.getValue() )
                .sum();
    }//당첨금액을 반환하는 메소드로써
     //당첨 세부사항 중 당첨 랭크의 당첨 금액 * 개수를 랭크별로 더해서
     //총 당첨 금액을 반환한다.

    public static double getLottoYield(long winningAmount, int money) {
        double lottoYield = PERCENTAGE + (double) (winningAmount - money) / money *PERCENTAGE;
        return Math.round(lottoYield *10) / 10.0;
    }//수익률을 반환하는 메소드로써
     //100 + ( 총 당첨금액 - 처음 입력한 돈 ) / 처음 입력한 돈 * 100으로 계산 후
     //소수점 2째자리에서 반올림 하는 계산을 적용한다.
}
