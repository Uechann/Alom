package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LottoGenerator {

    private static final int LOTTO_PRICE=1000;
    private static final int LOTTO_NUMBER_LOWER_LIMIT=1;
    private static final int LOTTO_NUMBER_UPPER_LIMIT=45;
    private static final int LOTTO_NUMBER_QUANTITY=6;
    //Randoms 메소드를 사용할 때 매개변수로 사용할 값들은 상수화한다.
    private static final int ZERO=0;
    private static final String MONEY_SHOULD_BE_DIVIDED_BY_ONE_THOUSAND="[ERROR] 구입 금액은 1,000원 단위로만 받을 수 있습니다.";
    //에러가 났을 때 메세지를 만들어 놓는다.
    private final List<Lotto> lottos = new ArrayList<>();
    //Lotto 클래스들이 모여있는 List lottos를 생성
    private final int lottoQuantity;

    public LottoGenerator(int money) {

        lottoQuantity = money / LOTTO_PRICE;
    }//돈은 로또 가격인 1000원으로 나눈 몫을 lotto양으로 저장한다.

    private void validateMoney(int money) {
        if(isZeroOrNegativeNumber(money) || !isDivideByOneThousand(money)) {
            throw new IllegalArgumentException(MONEY_SHOULD_BE_DIVIDED_BY_ONE_THOUSAND);
        }
    }//입력된 돈의 값이 0또는 음수 이거나, 1000으로 나눠 떨어지지 않으면
     // IlleagalArgumentException을 에러 메세지와 함께 thorw한다.

    private boolean isZeroOrNegativeNumber(int money) {
        return money <= ZERO;
    }//money의 값이 0이하인지 체크하는 메소드

    private boolean isDivideByOneThousand(int money) {
        return money % LOTTO_PRICE == ZERO;
    }// money의 값이 1000원으로 나눠 떨어져서 나머지가 0이 되는지 체크하는 메소드

    public List<Lotto> generateLottos() {
        for (int i =0; i<lottoQuantity; i++) {
            Lotto lotto = generateLotto();
            lottos.add(lotto);
        }
        return lottos;
    }//Lotto 클래스들의 List를 반환하는 메소드
     //lotto의 양만큼 for문을 돌리고 generateLotto() 메소드를 이용해 lotto에 담고
     //lottos에 하나씩 추가하고 반복문이 다 돌면 lorros를 return한다.

    private Lotto generateLotto() {
        List<Integer> randomNumbers = new ArrayList<>(
                Randoms.pickUniqueNumbersInRange(LOTTO_NUMBER_LOWER_LIMIT,
                        LOTTO_NUMBER_UPPER_LIMIT,LOTTO_NUMBER_QUANTITY));
        randomNumbers.sort(Comparator.naturalOrder());
        return new Lotto(randomNumbers);
    }//randomNumbers를 Randoms함수의를 이용해서 1-45까지 6개 추출한 값을 매개변수로 ArrayList로 선언하고
     //randomNumbers를 오름차순으로 정렬하고 Lotto를 새로운 객채로 생성해 return 한다.

    public int getLottoQuantity() {
        return lottoQuantity;
    }//lottoQuantity값을 반환하는 메소드
}
