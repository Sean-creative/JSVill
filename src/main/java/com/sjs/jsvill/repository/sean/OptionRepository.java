package com.sjs.jsvill.repository.sean;

import com.sjs.jsvill.entity.sean.Contract;
import com.sjs.jsvill.entity.sean.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {

    //옵션은 계약이 등록될 때 무조건 등록되기 때문에 없을 수가 없다.
    Option findByContract(Contract contract);
}
