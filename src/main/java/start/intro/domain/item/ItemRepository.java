package start.intro.domain.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private static final Map<Long, Item> store = new ConcurrentHashMap<>();

    private static Long sequence = 0L;

    /**
     * save Item
     *
     * @param item
     * @return
     */
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    /**
     * findById
     */
    public Item findById(Long id) {
        return store.get(id);
    }

    /**
     * update item
     *
     * @param itemId
     * @param item
     */
    public void update(Long itemId, Item item) {
        Item findItem = store.get(itemId);
        findItem.setItemName(item.getItemName());
        findItem.setPrice(item.getPrice());
        findItem.setPrice(item.getQuantity());
    }

    /**
     * find all item
     */
    public List<Item> allItem() {
        return new ArrayList<>(store.values());
    }



    /**
     * clear store
     */
    public void clearStore() {
        store.clear();
    }
}

