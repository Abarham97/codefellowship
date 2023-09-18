package com.abarham97.codefellowship.Repositry;

import com.abarham97.codefellowship.UserSite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSiteRepositry extends JpaRepository<UserSite,Long> {
    UserSite findByUserName(String userName);
}
