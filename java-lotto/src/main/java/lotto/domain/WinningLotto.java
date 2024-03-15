package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningLotto {

    private static final String WINNING_NUMBERS_ARE_BETWEEN_ONE_AND_FORTY_FIVE = "[ERROR] 당첨 번호는 1부터 45 사이의 숫자여야 합니다.";
    private static final String WINNING_NUMBERS_MUST_BE_SIX_DIFFERENT_NUMBERS = "[ERROR] 당첨 번호는 서로 다른 6개의 수여야 합니다.";
    private static final String BONUS_NUMBER_IS_BETWEEN_ONE_AND_FORTY_FIVE = "[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.";
    private static final String WINNING_NUMBERS_CONTAIN_BONUS_NUMBER = "[ERROR] 당첨 번호와 보너스 번호가 중복됩니다.";
    //당첨숫자가 1-45사이에 있어야하는 메세지와 6개의 다른 숫자여야한다는 메시지
    //보너스 숫자가 1-45 사이에 있어야하는 메세지와
    //당첨숫자가 보너스 숫자를 포함하고 있는 때 필요한 메세지

    private static final int LOTTO_NUMBER_LOWER_LIMIT = 1;
    private static final int LOTTO_NUMBER_UPPER_LIMIT =45;
    private static final int LOTTO_NUMBERS_SIZE = 6;

    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        validateWinningNumbers(winningNumbers);
        validateBonusNumber(bonusNumber);
        validateDuplicate(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }//생성자 매개변수로 당첨 숫자와 보너스 숫자를 받아와서
     //당첨 숫자와 보너스 숫자가 유효한지 메소드를 적용하고
     //중복되는 숫자가 있는지 메소드를 적용하고
     //클래스 변수에 저장한다.

    private void validateWinningNumbers(List<Integer> winningNumbers) {
        if (!isSixDifferentNumbers(winningNumbers)) {
            throw new IllegalArgumentException(WINNING_NUMBERS_MUST_BE_SIX_DIFFERENT_NUMBERS);
        }
        if (!isBetweenOneAndFortyFive(winningNumbers)) {
            throw new IllegalArgumentException(WINNING_NUMBERS_ARE_BETWEEN_ONE_AND_FORTY_FIVE);
        }
    }//당첨 숫자가 1-45 사이에 있고 6개 다른 숫자인지 확인하는 메소드
     //밑의 두 메소드를 포함한 상위 메소드

    private boolean isSixDifferentNumbers(List<Integer> winningNumbers) {
        Set<Integer> duplicateChecker = new HashSet<>(winningNumbers);
        return duplicateChecker.size() == LOTTO_NUMBERS_SIZE;
    }//당첨 숫자가 6개의 다른 숫자인지 확인하는 메소드
     //해쉬셋으로 순서없는 집합을 만들어 해쉬셋의 사이즈가 6개가 맞는 확인한다.

    private boolean isBetweenOneAndFortyFive(List<Integer> winningNumbers) {
        for (int winningNumber : winningNumbers) {
            if (winningNumber < LOTTO_NUMBER_LOWER_LIMIT || winningNumber > LOTTO_NUMBER_UPPER_LIMIT) {
                return false;
            }
        }
        return true;
    }//당첨숫자가 1-45 사이에 있느면 true, 아니면 false를 반환하는 메소드

    //여기서부터는 보너스 숫자 확인하는 메소드
    private void validateBonusNumber(int bonusNumber) {
        if(!isBetweenOneAndFortyFive(bonusNumber)) {
            throw new IllegalArgumentException(BONUS_NUMBER_IS_BETWEEN_ONE_AND_FORTY_FIVE);
        }
    }//보너스 숫자가 1-45 사이에 있는지 확인후
     //false이면 error 메시지 throw하는 메소드

    private boolean isBetweenOneAndFortyFive(int bonusNumber) {
        return bonusNumber >= LOTTO_NUMBER_LOWER_LIMIT && bonusNumber <= LOTTO_NUMBER_UPPER_LIMIT;
    }//보너스 숫자가 1-45 사이에 있는지 확인하는 메소드

    private void validateDuplicate(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(WINNING_NUMBERS_CONTAIN_BONUS_NUMBER);
        }
    }//최종적으로 당첨숫자에 보너스 숫자가 포함되어 있는지 확인하고
     //포함하면 error 메시지 throw 하는 메시지

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }//당첨숫자를 List로 반환하는 메소드

    public int getBonusNumber() {
        return bonusNumber;
    }// 보너스 숫자를 반환하는 메소드
}
