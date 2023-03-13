package start.intro;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import start.intro.domain.member.Member;
import start.intro.web.session.SessionConstant;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    /**
     * 기본 main 화면
     * @return
     */
    @GetMapping("/")
    public String home(@SessionAttribute(name = SessionConstant.LOGIN_MEMBER_SESSION,required = false) Member loginMember, Model model) {

        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}
