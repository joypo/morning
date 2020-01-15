package com.example.mapstruct.demo.mapper;

import com.example.mapstruct.demo.po.UserPo;
import com.example.mapstruct.demo.vo.UserVO;
import org.mapstruct.Mapper;

/**
 * @author sunx
 * @date 2020-01-11
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserPo toPo(UserVO vo);

    UserVO toVO(UserPo po);
}
