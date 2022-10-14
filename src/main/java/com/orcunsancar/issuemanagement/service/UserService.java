package com.orcunsancar.issuemanagement.service;

import com.orcunsancar.issuemanagement.dto.UserDto;
import com.orcunsancar.issuemanagement.util.TPage;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDto save(UserDto user);

    UserDto getById(Long id);

    TPage<UserDto> getAllPageable(Pageable pageable);

    UserDto getByUsername(String username);
}
