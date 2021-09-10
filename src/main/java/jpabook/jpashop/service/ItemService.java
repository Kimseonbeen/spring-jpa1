package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {

        // update()이런 메소드를 엔티티에 만들어서 호출해서 사용 하는 방식이 더 유지보수하기 좋음
        Item findItem = itemRepository.findOne(itemId);

        /**
         * 이렇게 set 해서 사용 하는 방식 지양
          */
//        findItem.setName(name);
//        findItem.setPrice(price);
//        findItem.setStockQuantity(stockQuantity);

        findItem.change(name, price, stockQuantity);
        
        // 변경감지를 사용하는 것이므로 반환타입 void 여기서 set로 변경해주는것만으로도 JPA가 업데이트를 해준다.
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
