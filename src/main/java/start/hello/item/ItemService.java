package start.hello.item;

public interface ItemService {

    //주문
    void order(String name, String quantity);
    //조회
    Item searchItem(Integer id);
    //삭제
    void delete(Integer id);

}
