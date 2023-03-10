package start.intro.web.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import start.intro.domain.login.LoginService;
import start.intro.domain.member.FindMember;
import start.intro.domain.member.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request) {

        //에러가 존재하면
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "ID, PW NOT MATCH");
            return "login/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute("loginId", loginMember);

        return "redirect:/";

    }


    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        //세션은 검증하지 않고 요청들어오면 삭제.
        // todo 없을때만
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

    @PostMapping("/findPw")
    public String findPw(@ModelAttribute("form") FindMember member, HttpServletRequest request) {

        String pw = loginService.findPw(member.getLoginId(), member.getUserName());

        request.setAttribute("password", pw);

        return "login/lookUpMember";
    }
}
