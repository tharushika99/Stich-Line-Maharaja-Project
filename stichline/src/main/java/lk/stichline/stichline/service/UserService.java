package lk.stichline.stichline.service;

import lk.stichline.stichline.entity.User;
import lk.stichline.stichline.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundedUser = userRepo.findByUsername(username);
        if(foundedUser == null) return null;
        String name= foundedUser.getUsername();
        String pwd = foundedUser.getPassword();

        return new org.springframework.security.core.userdetails.User(name , pwd , null);

    }
}

