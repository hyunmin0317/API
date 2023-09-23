package com.smunity.api.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {

    UserDetails localUserByUserame(String username) throws UsernameNotFoundException;
}
