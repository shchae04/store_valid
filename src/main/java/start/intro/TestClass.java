package start.intro;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import start.intro.domain.item.Item;
import start.intro.domain.item.ItemRepository;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class TestClass {

    private final ItemRepository itemRepository;

    @PostConstruct
    public void initialize(){
        itemRepository.save(new Item("item1", 10000, 30));
        itemRepository.save(new Item("item2", 50000, 50));

        System.out.println(itemRepository.findById(2L));
    }
}
