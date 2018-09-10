package com.hellokoding.account.web;

import com.hellokoding.account.model.Order;
import com.hellokoding.account.model.Product;
import com.hellokoding.account.model.Role;
import com.hellokoding.account.model.User;
import com.hellokoding.account.repository.RoleRepository;
import com.hellokoding.account.service.OrderService;
import com.hellokoding.account.service.ProductService;
import com.hellokoding.account.service.SecurityService;
import com.hellokoding.account.service.UserService;
import com.hellokoding.account.validator.ProductValidator;
import com.hellokoding.account.validator.UserValidator;
import com.mysql.jdbc.Connection;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/")
public class UserController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    private List<Order> filtr (List<Order> listOfOrders,Long id){
        List<Order> filtredList = new ArrayList<>();
        for(Order order : listOfOrders){
            if(order.getUser().getId()==id){
                filtredList.add(order);
            }
        }
        return filtredList;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model, @RequestParam("role") String role) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        System.out.println("asfsdfvsdfgsdg " + role);

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(role));
        userForm.setRoles(roles);

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/"; //welcome
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid.");
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }

        return "login";
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String welcome(@ModelAttribute("userForm") User userForm, Model model) {

        //SecurityContextHolderAwareRequestWrapper q = new SecurityContextHolderAwareRequestWrapper();
        model.addAttribute("name", userForm.getName());
        model.addAttribute("surname", userForm.getSurname());
        //User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        System.out.println("Takie imie" + SecurityContextHolder.getContext().getAuthentication().getName());

        Set<Role> roles = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getRoles();
        for (Role role : roles) {
            System.out.println(role.getName());
            if (role.getName().equalsIgnoreCase("ROLE_ADMIN")) {
                model.addAttribute("People",userService.findAll());
                return "welcomeAdmin";
            }

        }
        //System.out.println(user.getUsername());
        //System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Order> orderList = orderService.findAll();
        model.addAttribute("ownOrders", filtr(orderList,user.getId()));
        return "welcome";

    }
    
    
    
}
