package com.sjs.jsvill.repository;

import com.sjs.jsvill.dto.member.MemberLoginDTO;
import com.sjs.jsvill.entity.MemberAdmin;
import com.sjs.jsvill.entity.MemberUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberUserRepository extends JpaRepository<MemberUser, Long> {
    MemberUser findByPhone(String phone);
    //phone과 pin으로 MemberUser가 있으면 가져오고, 없으면 가져오지 않는다
    MemberUser findByPhoneAndPin(String phone, String pin);
}
