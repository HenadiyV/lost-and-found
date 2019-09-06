package com.example.advt.service;



import com.example.advt.domain.Role;
import com.example.advt.domain.User;
import com.example.advt.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *05.06.2019
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User userFindByUsername = userRepository.findByEmail(username);
//        User userFindByName = userRepository.findByName(username);
//        User userFindByGoogleUsername = userRepository.findByGoogleUserName(username);
//        User userFindByGoogleName = userRepository.findByGoogleName(username);
//
//        if(userFindByUsername != null)
//        {
//            return userFindByUsername;
//        }
//
//        if(userFindByName != null)
//        {
//            return userFindByName;
//        }
//
//        if(userFindByGoogleUsername != null)
//        {
//            return userFindByGoogleUsername;
//        }
//
//        if(userFindByGoogleName != null)
//        {
//            return userFindByGoogleName;
//        }
//
//        return null;
//
//    }
@Override
public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    return userRepository.findByName(name);
}

//    public boolean addUser(User user) {
//        User userFromDb = userRepository.findByName(user.getUsername());
//
//        if (userFromDb != null) {
//            return false;
//        }
//        user.setRoles(Collections.singleton(Role.USER));
////        Role rl = new Role();
////        rl.setId(2);
////        rl.setName("user");
////        user.setRole(rl);
//        userRepository.save(user);
//        return true;
//    }

}
