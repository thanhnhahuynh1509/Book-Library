package com.manager.booklibrary.security;

import java.util.ArrayList;
import java.util.List;

import com.manager.booklibrary.entity.AppUser;
import com.manager.booklibrary.repository.AppUserRepository;
import com.manager.booklibrary.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("not found username " + username);
        }
        List<String> roleNames = roleRepository.findAllName(user.getId());

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (roleNames != null) {
            for (String role : roleNames) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                authorities.add(authority);
            }
        }

        UserDetails userDetails = new User(user.getUsername(), user.getPassword(), authorities);
        return userDetails;
    }
    
}
