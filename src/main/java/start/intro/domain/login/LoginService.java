package start.intro.domain.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import start.intro.domain.member.Member;
import start.intro.domain.member.MemberRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository repository;

    public Member login(String loginId, String password) {

        return repository.findByLoginId(loginId)
                .filter(member -> member.getPassword().equals(password))
                .orElse(null);
    }

    public String findPw(String loginId, String userName) {
        return repository.findPw(loginId,userName);
    }
}
