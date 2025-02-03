package com.bs.spring.security.model.service;

import com.bs.spring.member.model.dao.MemberDao;
import com.bs.spring.member.model.dto.Member;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class SecurityLoginService implements UserDetailsService {
    private final MemberDao memberDao;
    private final SqlSession session;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member findMember=memberDao.findMemberById(session,username);
        return findMember;
    }
}
