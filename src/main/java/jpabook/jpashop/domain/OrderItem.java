package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // = protected OrderItem() {}
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; // 주문 가격
    private int count; // 주문 수량

//    protected OrderItem() {
//    }

    /**
     * 객체를 생성하는 방법은 여러가지가 있습니다.
     *
     * 1. 단순히 new 를 사용해서 생성하는 방법
     *
     * 2. 생성 메서드를 사용하는 방법
     *
     * 3. 빌더를 사용하는 방법
     *
     * 4. 별도의 생성 클래스를 사용하는 방법 등등
     *
     * (일반적으로 갈수록 복잡도가 높아집니다.)
     *
     * 단순히 new를 사용하는 방법보다는 생성 메서드를 사용하는 것이
     * 메서드 이름을 통해 생성 의도를 나타낼 수 있기 때문에 더 나은 선택일 확율이 높습니다.
     */

    /**
     *주문과 주문상품은 어느정도 복잡성을 가지고 있기 때문에, 생성 메서드를 사용했습니다.
     * 하지만 거의 사용하지 않는 정말 단순한 엔티티라면 자바가 기본으로 제공하는 new 를 사용하는게 더 좋겠지요.
     * 이렇게 한 프로젝트 안에서도 객체를 생성하는데 드는 복잡도에 따라서 다양한 선택을 해야 합니다.
     */
    //== 생성 메서드 ==//
    // OrderItem을 Item 객체로 만들지 않는 이유는
    // 추가 개발 시 할인 등등 하기 위해 orderItem으로 만듬
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;

    }

    //==비즈니스 로직==//
    public void cancel() {
        getItem().addStock(count);
    }

    //==조회 로직==//

    /**
     * 주문상품 전체 가격 조회
     */
    public int getTotallPrice() {
        return getOrderPrice() * getCount();
    }
}