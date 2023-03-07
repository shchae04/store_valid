package start.intro.web.item;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import start.intro.domain.item.Item;
import start.intro.domain.item.ItemRepository;
import start.intro.web.item.form.SaveItemForm;
import start.intro.web.item.form.UpdateItemForm;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/intro")
public class ItemController {


    private final ItemRepository repository;

    /**
     * items.html 전체 item객체를 뿌려줌.
     *
     * @param model
     * @return
     */
    @GetMapping
    public String items(Model model) {
        List<Item> items = repository.allItem();
        model.addAttribute("items", items);
        return "intro/items";
    }

    /**
     * item.html에 repository에서 찾은 객체를 뿌림.
     *
     * @param itemId
     * @param model
     * @return
     */
    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {

        Item findItem = repository.findById(itemId);
        model.addAttribute("item", findItem);
        log.info("item ={}", findItem);

        return "intro/item";
    }

    /**
     * itemAddForm으로 이동.
     *
     * @param model
     * @return
     */
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("item", new Item());
        return "intro/itemAddForm";
    }

    /**
     * 저장하고 저장된 아이템으로 이동.
     *
     * @param form
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/add")
    public String add(@ModelAttribute("item") SaveItemForm form, RedirectAttributes redirectAttributes) {
        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setQuantity(form.getQuantity());

        Item saveItem = repository.save(item);

        redirectAttributes.addAttribute("itemId", saveItem.getId());

        return "redirect:/intro/{itemId}";
    }

    /**
     * 아이템 업데이트 폼으로 이동
     * @param itemId
     * @param model
     * @return
     */
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = repository.findById(itemId);
        model.addAttribute("item", item);
        return "intro/itemUpdateForm";

    }

    /**
     * UpdateForm 객체를 받아서 저장.
     * @param itemId
     * @param form
     * @return
     */
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute("item") UpdateItemForm form) {
        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setQuantity(form.getQuantity());

        log.info("Update item={}", item);

        repository.update(itemId, item);

        return "redirect:/intro/{itemId}";
    }

    /**
     * Admin only clear store ( interceptor )
     */
    @PostMapping("/clearAll")
    public void clearAll() {
        repository.clearStore();
    }
}
