package start.intro.domain.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import start.intro.domain.member.Member;
import start.intro.domain.member.MemberRepository;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository repository;

    public Member login(String id, String password) {
        return repository.findByLoginId(id).filter(M -> M.getPassword().equals(password)).orElse(null);
    }
}
