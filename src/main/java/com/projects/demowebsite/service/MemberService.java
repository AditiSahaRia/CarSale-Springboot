package com.projects.demowebsite.service;


import com.projects.demowebsite.model.Member;
import com.projects.demowebsite.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String PHONE_REGEX = "^\\+?[0-9]{10,15}$";

    public String registerMember(Member member) {
        if (member.getEmail() == null || member.getEmail().isEmpty()) {
            return "Email cannot be null or empty";
        }
        if (memberRepository.findByEmail(member.getEmail()).isPresent()) {
            return "Email Already Exists";
        }
        if (!isValidEmail(member.getEmail())) {
            return "Invalid Email";
        }
//        if (memberRepository.findByMemberId(member.getMemberId()).isPresent()) {
//            return "Username already exists";
//        }
//        if (member.getMemberId() == null) {
//            return "Username cannot be null";
//        }
        if (member.getNumber() == null || member.getNumber().isEmpty()) {
            return "Phone number cannot be null or empty";
        }
        if (!isValidPhoneNumber(member.getNumber())) {
            return "Invalid phone number format";
        }
        memberRepository.save(member);
        return "Registration Successful";
    }

    private boolean isValidEmail(String email) {
        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }
    private boolean isValidPhoneNumber(String phoneNumber) {
        return Pattern.compile(PHONE_REGEX).matcher(phoneNumber).matches();
    }


    public String login(String email, String password) {
        Optional<Member> memberOpt = memberRepository.findByEmail(email);
        if (memberOpt.isPresent()) {
            Member member = memberOpt.get();
            if (member.getPassword().equals(password)) {
                return "Login successful"; // You might want to redirect to a dashboard or home page
            } else {
                return "Email or password is wrong";
            }
        } else {
            return "Email or password is wrong";
        }
    }

    public String resetPassword(String email, String newPassword) {
        Optional<Member> memberOpt = memberRepository.findByEmail(email);
        if (memberOpt.isPresent()) {
            Member member = memberOpt.get();
            member.setPassword(newPassword);
            memberRepository.save(member);
            return "Password reset successful";
        } else {
            return "Wrong email, please enter a correct email or go to registration";
        }
    }
}
