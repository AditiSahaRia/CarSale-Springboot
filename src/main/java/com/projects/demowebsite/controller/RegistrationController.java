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
public class RegistrationController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("member", new Member());
        return "registration";
    }

    @PostMapping("/register")
    public String registerMember(@ModelAttribute Member member, Model model) {

        String message = memberService.registerMember(member);
        model.addAttribute("message", message);
        if (message.equals("Registration Successful")) {
            return "redirect:/products";
        }
        return "registration";
    }
}
