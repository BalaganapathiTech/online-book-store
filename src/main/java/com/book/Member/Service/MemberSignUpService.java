package com.book.Member.Service;

import com.book.Member.Entity.Member;
import com.book.Member.DTO.MemberSignupRequest;
import com.book.Member.Repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberSignUpService {

    private final MemberRepository memberRepository;
    private final MemberHelperService memberHelperService;

    public Member signUp(final MemberSignupRequest request){
        memberHelperService.verifyEmailIsDuplicated(request.getEmail());
        final Member member = memberRepository.save(request.toMember());
        return member;
    }

}