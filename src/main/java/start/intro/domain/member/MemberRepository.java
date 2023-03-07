package start.intro.domain.member;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Repository;

import javax.sound.midi.MetaMessage;
import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    //DB연동 전 store 선언
    private static Map<Long, Member> store = new HashMap<>();

    private static Long sequence;

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream().filter(member -> member.getLoginId().equals(loginId)).findAny();
    }
}
