package com.book.Member.DTO;

import com.book.Member.Entity.Email;
import com.book.Member.Entity.Member;
import com.book.Member.Entity.Name;
import com.book.Member.Entity.Password;
import lombok.*;

import javax.validation.Valid;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSignupRequest {

    @Valid
    private Email email;
    @Valid
    private Name name;
    @Valid
    private Password password;

    @Builder
    public MemberSignupRequest(Email email, Name name, Password password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public Member toMember(){
        return new Member(email,password, name);
    }

}
