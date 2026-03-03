package com.book.Member.Repository;

import com.book.Member.Entity.Email;
import com.book.Member.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(Email email);

}
