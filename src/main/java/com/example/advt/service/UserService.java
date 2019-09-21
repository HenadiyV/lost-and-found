package com.example.advt.service;



import com.example.advt.domain.Role;
import com.example.advt.domain.User;
import com.example.advt.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *05.06.2019
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;




@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {



    User userFindByName= userRepository.findByName(username);
    User userfindByEmail=userRepository.findByEmail(username);
    User userfindByIdSocial=userRepository.findByIdSocial(username);

    if(userFindByName!=null){
    return userFindByName;}
    if(userfindByEmail!=null){
        return userfindByEmail;
    }
    if(userfindByIdSocial!=null){
        return userfindByIdSocial;
    }

   return null;
}

    public boolean userAdmin()
    {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean hasUserRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ADMIN"));
        if(hasUserRole){
            return true;
        }
        return false;
    }
    ///////////////////////////////
//    public boolean addUser(User user) {
//        User userFromDb = userRepo.findByUsername(user.getUsername());
//
//        if (userFromDb != null) {
//            return false;
//        }
//
//        user.setActive(true);
//        user.setRoles(Collections.singleton(Role.USER));
//        user.setActivationCode(UUID.randomUUID().toString());
//
//        userRepo.save(user);
//
//        if (!StringUtils.isEmpty(user.getEmail())) {
//            String message = String.format(
//                    "Hello, %s! \n" +
//                            "Welcome to Sweater. Please, visit next link: http://localhost:8080/activate/%s",
//                    user.getUsername(),
//                    user.getActivationCode()
//            );
//
//            mailSender.send(user.getEmail(), "Activation code", message);
//        }
//
//        return true;
//    }
//
//    public boolean activateUser(String code) {
//        User user = userRepo.findByActivationCode(code);
//
//        if (user == null) {
//            return false;
//        }
//
//        user.setActivationCode(null);
//
//        userRepo.save(user);
//
//        return true;
//    }

}
