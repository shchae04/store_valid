package start.intro.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    /**
     * AfterEach
     */
    @AfterEach
    public void clear() {
        itemRepository.clearStore();
    }

    /**
     * Given : 주어지는 값
     * When : 언제
     * Then : 결과
     * 사용
     */

    @Test
    void save() {
        Item item = new Item("item1", 10000, 30);

        Item saveItem = itemRepository.save(item);

        Item findRepoItem = itemRepository.findById(item.getId());

        Assertions.assertThat(findRepoItem).isEqualTo(saveItem);

    }

    @Test
    void findById() {

        Item item = new Item("itemOne", 10000, 10);

        Item saveItem = itemRepository.save(item);

        Item findItem = itemRepository.findById(saveItem.getId());

        Assertions.assertThat(saveItem).isEqualTo(findItem);
    }

    @Test
    void update() {

        Item item = new Item("item1", 10000, 10);

        Item saveItem = itemRepository.save(item);
        Long id = saveItem.getId();

        Item updateItem = new Item("item22", 14000, 30);
        itemRepository.update(id, updateItem);

        Item findItem = itemRepository.findById(id);

        Assertions.assertThat(findItem.getItemName()).isEqualTo(updateItem.getItemName());
    }

    @Test
    void allItem() {

        Item item = itemRepository.save(new Item("itemDel", 10000, 10));

        itemRepository.clearStore();

        List<Item> items = itemRepository.allItem();

        Assertions.assertThat(items).size().isEqualTo(0);
    }
}