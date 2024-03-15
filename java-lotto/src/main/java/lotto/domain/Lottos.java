package lotto.domain;

import java.util.List;

public class Lottos {

    private List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }// Lottos 클래스의 생성자

    public List<Lotto> getLottos() {
        return lottos;
    }
}
