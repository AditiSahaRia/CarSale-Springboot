package com.projects.demowebsite.controller;


import com.projects.demowebsite.model.Member;
import com.projects.demowebsite.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("member", new Member());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Member member, Model model) {
        String message = memberService.login(member.getEmail(), member.getPassword());
        model.addAttribute("message", message);
        if (message.equals("Login successful")) {
            return "redirect:/products";
        }
        return "login";
    }
}
