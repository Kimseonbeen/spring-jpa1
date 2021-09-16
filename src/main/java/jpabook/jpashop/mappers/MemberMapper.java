package jpabook.jpashop.mappers;

import jpabook.jpashop.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {

    @Select("SELECT * FROM member")
    Member findAll();
}
