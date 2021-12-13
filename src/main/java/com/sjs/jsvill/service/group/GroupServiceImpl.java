package com.sjs.jsvill.service.group;

import com.sjs.jsvill.dto.GroupDTO;
import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.repository.group.GroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor //의존성 자동 주입 -> repository가 자동 주입
public class GroupServiceImpl implements GroupService {

    private final GroupRepository repository; //반드시 final로 선언
    @Override
    public Long register(GroupDTO dto) {
        log.info("DTO-------------");
        log.info(dto);

        Group entity = dtoToEntity(dto);
        log.info(entity);

        repository.save(entity);
        return entity.getGroup_rowid();
    }
}