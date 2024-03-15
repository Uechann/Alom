package lotto.domain;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }


    //로또 번호들이 유효한지 검사하는 메소드
    private void validate(List<Integer> numbers) {
        if (!isSizeSix(numbers) || isDuplicate(numbers)) {
            throw new IllegalArgumentException();
        }
    }// numbers List가 밑에 두개 메소드를 통해서 둘중에 하나라도 return값이 0이 나오면
     //IllegalArgumentException 객체를 throw 한다.

    private boolean isSizeSix(List<Integer> numbers) {
        return numbers.size() ==6;
    }//로또 번호들의 사이즈가 6인지 확인하는 메소드 반환값은 boolean

    private boolean isDuplicate(List<Integer> numbers) {
        Set<Integer> duplicateChecker = new HashSet<>(numbers);
        return duplicateChecker.size() != 6;
    }//순서 없는 HashSet 집합을 선언해서 6개의 숫자가 모두 다른지 확인하는 메소드

    public List<Integer> getNumbers() {
        return numbers;
    }//로또 번호들을 return하는 메소드
}