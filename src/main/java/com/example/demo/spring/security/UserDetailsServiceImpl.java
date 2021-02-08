package com.example.demo.spring.security;

import com.example.demo.db.AuthorityDao;
import com.example.demo.db.AuthorityDto;
import com.example.demo.db.CustomerDao;
import com.example.demo.db.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private AuthorityDao authorityDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomerDto customerDto = customerDao.findByUsername(username);
        AuthorityDto authorityDto = authorityDao.findByUsername(username);
        if(customerDto == null || authorityDto == null){
            throw new UsernameNotFoundException(username);
        }
        return new UserDetailsImpl(customerDto, authorityDto);
    }
}
