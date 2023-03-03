package start.hello.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {


    @Autowired
    ItemRepository itemRepository = new ItemRepository();

    @Override
    public void order(String name, String quantity) {
        Item item = new Item(name, quantity);
        itemRepository.order(item);
    }

    @Override
    public Item searchItem(Integer id) {
        return itemRepository.search(id);
    }

    @Override
    public void delete(Integer id) {
        itemRepository.clear();
    }
}
