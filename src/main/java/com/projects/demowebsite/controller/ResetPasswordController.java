package com.projects.demowebsite.controller;


import com.projects.demowebsite.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResetPasswordController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/reset-password")
    public String showResetPasswordForm(Model model) {
        model.addAttribute("email", "");
        return "reset_password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String email, @RequestParam String newPassword, Model model) {
        String message = memberService.resetPassword(email, newPassword);
        model.addAttribute("message", message);
        if (message.equals("Password reset successful")) {
            return "redirect:/login";
        }
        return "reset_password";
    }
}
