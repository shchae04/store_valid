package start.intro.web.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import start.intro.domain.member.Member;
import start.intro.domain.member.MemberRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    /**
     * 사용자 추가 폼
     * form에서 Thymeleaf를 이용함.
     */
    @GetMapping("/add")
    public String addForm(@ModelAttribute("member") Member member) {
        return "members/MemberAddForm";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/MemberAddForm";
        }

        memberRepository.save(member);
        return "redirect:/";
    }

}
