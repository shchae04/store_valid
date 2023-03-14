package start.intro.web.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import start.intro.domain.login.LoginService;
import start.intro.domain.member.Member;
import start.intro.web.session.SessionConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }

    //같은 url로 들어옴
    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request) {

        //에러가 존재하면
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        log.info("form id ={}", form.getLoginId());
        log.info("form pw ={}", form.getPassword());

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        log.info("loginMember ={}", loginMember);

        if (loginMember == null) {
            bindingResult.reject("loginFail", "ID, PW NOT MATCH");
            return "login/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConstant.LOGIN_MEMBER_SESSION, loginMember);

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


    @GetMapping("/foundPassword")
    public String getPw(@ModelAttribute("form") Member member, RedirectAttributes redirectAttributes) {


        return "login/foundMember";

    }



    @GetMapping("/findPw")
    public String findPw(@ModelAttribute("form") Member member) {
        return "login/lookUpMember";
    }

    /**
     * 비밀번호 찾기 로직
     *
     */
    @PostMapping("/findPw")
    public String findPw(@ModelAttribute("form") Member member, BindingResult bindingResult) {

        String pw = loginService.findPw(member.getLoginId(), member.getUserName());

        if (!member.getPassword().equals(pw)) {
            return "login/lookUpMember";
        }

        return "login/foundMember";
    }
}
