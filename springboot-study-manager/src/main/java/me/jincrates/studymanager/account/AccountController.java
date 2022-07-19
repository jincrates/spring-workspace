package me.jincrates.studymanager.account;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.jincrates.studymanager.domain.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final SignUpFormValidator signUpFormValidator;
    private final AccountRepository accountRepository;
    //private final JavaMailSender javaMailSender;

    // TODO WebDataBinder의 동작 원리
    @InitBinder("signUpForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(signUpFormValidator);
    }

    @GetMapping("/sign-up")
    public String signUpForm(Model model) {
        model.addAttribute(new SignUpForm());  // 이름이 같다면 이렇게도 쓸 수 있다.
        //model.addAttribute("signUpForm", new SignUpForm());
        return "account/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUpSubmit(@Valid SignUpForm signUpForm, Errors errors) {
        if (errors.hasErrors()) {
            return "account/sign-up";
        }

        Account account = Account.builder()
                .email(signUpForm.getEmail())
                .nickname(signUpForm.getNickname())
                .password(signUpForm.getPassword())  // TODO encoding 해야함
                .studyEnrollmentResultByWeb(true)
                .studyUpdatedByWeb(true)
                .build();

        Account newAccount = accountRepository.save(account);
        // 이메일 발송
        newAccount.generateEmailCheckToken();
        System.out.println(newAccount.getEmailCheckToken());
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(newAccount.getEmail());
//        mailMessage.setSubject("지호학, 회원가입 인증");
//        mailMessage.setText("/check-email-token?token=" + newAccount.getEmailCheckToken()
//                + "&email=" + newAccount.getEmail());
//        javaMailSender.send(mailMessage);

        return "redirect:/";
    }
}
