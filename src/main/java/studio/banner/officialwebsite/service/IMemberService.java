package studio.banner.officialwebsite.service;

import studio.banner.officialwebsite.entity.Member;

import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/7 13:16
 */
public interface IMemberService {
    /**
     * 添加成员
     * @param member
     * @return
     */
    boolean insertMember(Member member);

    /**
     * 根据id删除成员信息
     * @param id
     * @return
     */
    boolean delete(Integer id);

    /**
     * 更改成员信息
     * @param member
     * @return
     */
    boolean update(Member member);

    /**
     * 根据id查询成员信息
     * @param id
     * @return
     */
    Member select(Integer id);

    /**
     * 查询所有用户
     * @return
     */
    List<Member> selectAll();

    /**
     * 根据职务查询人员
     * @param responsibility
     * @return
     */
    List<Member> selectResponsibility(String responsibility);

    /**
     * 根据年级查询人员
     * @param grade
     * @return
     */
    List<Member> selectGrade(Integer grade);

    /**
     * 根据方向查询用户
     * @param direction
     * @return
     */
    List<Member> selectDirection(String direction);

    /**
     * 根据方向和年级查询用户
     * @param direction
     * @param grade
     * @return
     */
    List<Member> selectDirectionAddGrade(String direction,Integer grade);

    /**
     * 根据职务和年级查询用户
     * @param responsibility
     * @param grade
     * @return
     */
    List<Member> selectResponsibilityAddGrade(String responsibility,Integer grade);
}
