//package com.example.advt.controller;
//
////import com.example.advt.service.MyContains;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
///*
// *@autor Hennadiy Voroboiv
// *@email henadiyv@gmail.com
// *27.09.2019
// */2019
//@Controller
//public class SimpleEmailExampleController {
//    @Autowired
//    public JavaMailSender emailSender;
//
//    @ResponseBody
//    @RequestMapping("/sendSimpleEmail")
//    public String sendSimpleEmail() {
//
//        // Create a Simple MailMessage.
//        SimpleMailMessage message = new SimpleMailMessage();
//
//        message.setTo(MyContains.FRIEND_EMAIL);
//        message.setSubject("Test Simple Email");
//        message.setText("Hello, Im testing Simple Email");
//
//        // Send Message!
//        this.emailSender.send(message);
//
//        return "Email Sent!";
//    }
//}
