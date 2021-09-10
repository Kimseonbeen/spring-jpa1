package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        // 신규 등록
        if(item.getId() == null) {
            em.persist(item);
        } else {
            // 디비에 있는 값을 가져 온다? update 비슷
            // 병합 방식은 모든 객체를 업데이트 하는 것이므로 좋은 방식이 아님
            // 영속성 컨테스트 변경감지를 사용하는게 좋은 방식이다.
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
