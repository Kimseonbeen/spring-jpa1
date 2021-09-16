package jpabook.jpashop.controller;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TimeMapper {

    @Select("SELECT SYSDATE")
    String getTime();

}
