package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TenantRepository extends JpaRepository<Tenant, Long> {

    @Query("select t from ContractTenant ct inner join ct.tenant t where ct.contract.contract_rowid=:contract_rowid")
    List<Tenant> findByContract(@Param("contract_rowid") Long contract_rowid);
}