package com.abarham97.codefellowship;

import com.abarham97.codefellowship.Repositry.UserSiteRepositry;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class UserSiteController {
@Autowired
    UserSiteRepositry UserSiteRepositry;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;



    @GetMapping("/")
    public String splash(Principal p, Model m) {
        if(p !=null)
        {
            String username = p.getName();
            UserSite usersite=UserSiteRepositry.findByUserName(username);
            m.addAttribute("username", username);
            m.addAttribute("firstname", usersite.getFirstname());
            m.addAttribute("lastname", usersite.getLastname());

        }

        return "Home";
    }
@GetMapping("/login")
public String getLoginPage(){

    return "/login.html";

    }

    @GetMapping("/singup")
    public String getSignupPage(){
        return "/singup.html";
    }

    @GetMapping("/logout")
    public String getLogoutPage(){
        return "Home.html";
    }





    @PostMapping("/singup")
    public RedirectView createUser(String username, String password,String dateOfBirth,String  lastname,String bio, String firstname){
       UserSite userSite=new UserSite();

       userSite.setUserName(username);
       userSite.setDateOfBirth(dateOfBirth);
        userSite.setBio(bio);
        userSite.setLastname(lastname);
        userSite.setFirstname(firstname);
        String encryptedPassword = passwordEncoder.encode(password);
        userSite.setPassword(encryptedPassword);

        UserSiteRepositry.save(userSite);
        authWithHttpServletRequest(username, password);

        return new RedirectView("/");

    }

    public void authWithHttpServletRequest(String username, String password){

        try {
            request.login(username, password);
        }catch (ServletException e){
            e.printStackTrace();
        }
    }

}
