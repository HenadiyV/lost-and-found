package com.example.advt.service;



import com.example.advt.dao.AdvtViewDAO;
import com.example.advt.domain.Role;
import com.example.advt.domain.User;
import com.example.advt.repos.UserRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.security.Principal;
import java.util.*;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *05.06.2019
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Value("${upload.path}")
    private String uploadPath;
//    @Autowired
//    private MailSender mailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;


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
// роль из social
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
    // получение ADMIN из базы
    public boolean baseAdmin(Principal principal){
        User user =userRepository.findByName(principal.getName());
        for(Role rl:user.getRoles()){
            if(rl.name().equals("ADMIN")){
                return true;
            }
        }
        return false;
    }
    // дабавление пользователя
    public boolean addUser(User user) {
        User userFromDbEmail = userRepository.findByEmail(user.getEmail());
        User userFromDbName = userRepository.findByName(user.getName());

        if (userFromDbEmail != null) {
            return false;
        }
        if (userFromDbName != null) {
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setactivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome. Please, visit next link: http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getactivationCode()
            );

          //  mailSender.send(user.getEmail(), "Activation code", message);
        }

        return true;
    }
// проверка активации
    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setactivationCode(null);

        userRepository.save(user);

        return true;
    }
    // проверить роль
    public int roleUser(Principal principal){
        if (principal != null) {
            User user = (User) loadUserByUsername(principal.getName());
            boolean bl = userAdmin();
            if (bl) {
          return 2;
            } else return 1;
        }
        return 0;
    }
    // удаляем файл
    public void deleteMyFile(String fileName) throws IOException {
        //FileUtils.touch(new File("src/test/resources/fileToDelete.txt"));
        if(!fileName.equals("noimage.png")){
        String nam=uploadPath+"\\"+fileName;
        FileUtils.forceDelete(FileUtils.getFile(nam));
        }
    }
    // делим на страницы
    public Page<AdvtViewDAO> searchUserPage(List<AdvtViewDAO> list, Pageable pageable) {
        List<AdvtViewDAO> patientsList = new ArrayList<AdvtViewDAO>();
        list.sort(Comparator.comparing(AdvtViewDAO::getAdvtId).reversed());
        patientsList.addAll(list);
        int start = (int) pageable.getOffset();
        int end = (start + pageable.getPageSize()) > patientsList.size() ? patientsList.size() : (start + pageable.getPageSize());

        return new PageImpl<AdvtViewDAO>(patientsList.subList(start, end), pageable, patientsList.size());
    }
    // возвращаем USER
    public List<User> userList(){
        List<User> userList=new ArrayList<>();
        List<User> users = userRepository.findAll();
        for(User us:users){
            boolean res=false;
            for(Role rl:us.getRoles()){

                if(rl.name()==("ADMIN"))
                {
                    res=true;
                    break;
                }
            }
            if(res) {
               // adminListTemp.add(us);
            }else{userList.add(us);}
        }
        return userList;
    }
    // возвращаем ADMIN
    public List<User> adminList(){
        List<User> userList=new ArrayList<>();
        List<User> users=userRepository.findAll();
        for(User us:users){
            boolean res=false;
            for(Role rl:us.getRoles()){

                if(rl.name()==("ADMIN"))
                {
                    res=true;
                    break;
                }
            }
            if(res) {
                userList.add(us);
            }//else{adminListTemp.add(us);}
        }
        return userList;
    }
    public int userCount(){
    return userRepository.findAll().size();
    }
}
