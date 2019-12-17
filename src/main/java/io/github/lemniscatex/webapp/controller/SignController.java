package io.github.lemniscatex.webapp.controller;

import io.github.lemniscatex.webapp.model.User;
import io.github.lemniscatex.webapp.repository.BillRepository;
import io.github.lemniscatex.webapp.repository.ProductRepository;
import io.github.lemniscatex.webapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BillRepository billRepository;

    User user = new User();

    @GetMapping("/admin")
    public String showAdminLoginPage() {
        return "admin-login";
    }

    @GetMapping("/admin/index")
    public String showAdminIndexPage() {
        return "admin-index";
    }

    @PostMapping("/admin/login")
    public String adminLogin(Model model,
                             @RequestParam(name = "account") String account,
                             @RequestParam(name = "password") String password) {
        if (account.equals("admin") && password.equals("admin"))
            return "redirect:/admin/index";
        else {
            model.addAttribute("error", "账号或密码错误，请重新输入！");
            return "admin-login";
        }
    }

    @GetMapping("/admin/logout")
    public String adminLogout() {
//        System.out.println("退出登录" + " " + "管理员");
        return "redirect:/admin";
    }

    @GetMapping("/")
    public String showUserAuthPage() {
        return "user-auth";
    }

    @GetMapping("/user/register")
    public String showUserRegisterPage() {
        return "user-register";
    }

    @GetMapping("/user/login")
    public String showUserLoginPage() {
        return "user-login";
    }

    @GetMapping("/user/index")
    public String showUserIndexPage() {
        return "user-index";
    }

    @PostMapping("/user/login")
    public String userLogin(Model model,
                            @RequestParam(name = "email") String email,
                            @RequestParam(name = "password") String password) {
        if (userRepository.existsByEmail(email)) {
            if (userRepository.getUserByEmail(email).password.equals(password)) {
                user = userRepository.getUserByEmail(email);
//                System.out.println("成功登录" + " " + user.email + " " + user.password);
                return "redirect:/user/index";
            } else {
                model.addAttribute("error", "密码错误，请重新输入！");
//                System.out.println("密码错误" + " " + user.email + " " + user.password);
                return "user-login";
            }
        } else {
            model.addAttribute("error", "账号不存在，请重新输入！");
//            System.out.println("账号不存在" + " " + user.email + " " + user.password);
            return "user-login";
        }
    }

    @PostMapping("/user/register")
    public String userRegister(Model model,
                               @RequestParam(name = "email") String email,
                               @RequestParam(name = "password") String password) {
        if (userRepository.existsByEmail(email)) {
            model.addAttribute("error", "账号已存在，注册失败！");
//            System.out.println("账号已存在" + " " + user.email + " " + user.password);
            return "user-register";
        } else {
            User newUser = new User(email, password);
            userRepository.save(newUser);
            user = newUser;
//            System.out.println("注册成功" + " " + user.email + " " + user.password);
            return "user-index";
        }
    }

    @GetMapping("/user/products")
    public String getAllProducts(Model model) {
        model.addAttribute("kitchenProducts", productRepository.findAllByCategory("kitchen"));
        model.addAttribute("bathroomProducts", productRepository.findAllByCategory("bathroom"));
        model.addAttribute("bedroomProducts", productRepository.findAllByCategory("bedroom"));
        model.addAttribute("personalProducts", productRepository.findAllByCategory("personal"));
        model.addAttribute("userId", user.id);
        return "user-products";
    }

    @GetMapping("/user/bills")
    public String getAllPBills(Model model) {
        model.addAttribute("bills", billRepository.findAllByUserId(user.id));
        return "user-bills";
    }

    @GetMapping("/user/logout")
    public String userLogout() {
//        System.out.println("退出登录" + " " + user.email + " " + user.password);
        user = new User();
        return "redirect:/";
    }
}
