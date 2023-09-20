package com.abarham97.codefellowship;

import com.abarham97.codefellowship.Repositry.PostRepositry;
import com.abarham97.codefellowship.Repositry.UserSiteRepositry;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    PostRepositry PostRepositry;



    @GetMapping("/")
    public String splash(Principal p, Model m) {
        if(p !=null)
        {
            String username = p.getName();
            UserSite usersite=UserSiteRepositry.findByUserName(username);
            m.addAttribute("username", username);
            m.addAttribute("firstname", usersite.getFirstname());
            m.addAttribute("lastname", usersite.getLastname());
            m.addAttribute("dateOfBirth", usersite.getDateOfBirth());
            m.addAttribute("bio", usersite.getBio());

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
    @GetMapping("/users/{id}")
    public String viewUserProfile(@PathVariable Long id, Model model) {

        UserSite usersite = UserSiteRepositry.findById(id).orElse(null);

        if (usersite != null) {
            model.addAttribute("usersite", usersite);
            return "user-profile";
        } else {

            return "/";
        }
    }
    @GetMapping("/profile")
    public String userProfile(Principal principal, Model model) {
        String username = principal.getName();
        UserSite userSite = UserSiteRepositry.findByUserName(username);

        if (userSite != null) {
            model.addAttribute("username", username);
            model.addAttribute("firstname", userSite.getFirstname());
            model.addAttribute("lastname", userSite.getLastname());
            model.addAttribute("dateOfBirth", userSite.getDateOfBirth());
            model.addAttribute("bio", userSite.getBio());


            List<Post> userPosts = PostRepositry.findAllByUser(userSite);
            model.addAttribute("userPosts", userPosts);

            return "profile";
        } else {
            return "Home";
        }
    }
    @PostMapping("/create-post")
    public RedirectView createPost(String body, Principal principal) {
        UserSite loggedInUser = UserSiteRepositry.findByUserName(principal.getName());


        Post post = new Post(body, new Date());
        post.setUser(loggedInUser);


        PostRepositry.save(post);

        return new RedirectView("/profile");
    }




}
