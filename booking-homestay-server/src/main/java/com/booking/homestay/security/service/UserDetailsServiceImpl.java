package com.booking.homestay.security.service;


import com.booking.homestay.model.User;
import com.booking.homestay.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

import static java.util.Collections.singletonList;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserRepository IUserRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        Optional<User> userOptional = IUserRepository.findByUserName(username);
        User user = userOptional
                .orElseThrow(() -> new UsernameNotFoundException("Không tài khoản nào " +
                        "Tìm thấy với tên người dùng : " + username));

        return new org.springframework.security
                .core.userdetails.User(user.getUserName(), user.getPassword(),
                user.isEnabled(), true, true,
                user.isStatus(), getAuthorities(user.getRole()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return singletonList(new SimpleGrantedAuthority(role));
    }
}
