package com.example.springdb.controller;

import com.example.springdb.pojo.User;
import com.example.springdb.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Configuration
public class UserControl {
    @Resource
    private UserService userService;

    @RequestMapping("/")
    public String hello(){

        return "login.html";
    }
    @RequestMapping("/login")
    public String login(){

        return "login";
    }
    @GetMapping("/result")
    public String result(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        boolean flag = userService.login(user);

        if (flag==true){
            return "redirect:/index";
        }else {
            return "loginerror";
        }
    }
    @GetMapping("/loginerror")
    public String loginerror() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/registerend")
    public String registerend(HttpServletRequest req,HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        boolean flag = userService.register(user);

        if (flag==true){
            return "registersuccess";
        }else {
            return "registererror";
        }
    }
    @RequestMapping("/index")
    public String userList(Model model){
        List<User> user = userService.findAll();
        model.addAttribute("user",user);
        return "index";
    }
    @RequestMapping("/add")
    public String save(User user){
        userService.save(user);
        return "redirect:/index";
    }
    @RequestMapping("/useradd")
    public String add(){
        return "add";
    }
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, HttpServletResponse servletResponse)throws IOException {
        int count = userService.delete(id);
        if (count==1){
            servletResponse.sendRedirect("/index");
        }
        return "error";
    }
    @GetMapping("/updatePage/{id}")
    public String updatePage(Model model,@PathVariable int id){
        User users = userService.get(id);
        model.addAttribute("users",users);
        return "update";
    }
    @PostMapping("/updatePage/{id}")
    public String updateUser(Model model, User user, HttpServletRequest request){
        String id = request.getParameter("id");
        User userById = userService.get(Integer.parseInt(id));
        userService.update(user);
        System.out.println(user);
        return "redirect:/index";
    }
}
