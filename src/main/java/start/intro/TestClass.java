package start.intro;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import start.intro.domain.item.Item;
import start.intro.domain.item.ItemRepository;
import start.intro.domain.member.Member;
import start.intro.domain.member.MemberRepository;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class TestClass {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @PostConstruct
    public void initialize(){
        itemRepository.save(new Item("item1", 10000, 30));
        itemRepository.save(new Item("item2", 50000, 50));

        Member member = new Member();
        member.setLoginId("test");
        member.setUserName("chae");
        member.setPassword("test");
        memberRepository.save(member);
    }
}
