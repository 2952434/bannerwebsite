package studio.banner.officialwebsite.service.Impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.banner.officialwebsite.entity.Member;
import studio.banner.officialwebsite.mapper.MemberMapper;
import studio.banner.officialwebsite.service.IMemberService;

import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/7 13:18
 */
@Service
public class MemberServiceImpl implements IMemberService {
    @Autowired
    protected MemberMapper memberMapper;
    @Override
    public boolean insertMember(Member member) {
        List<Member> members = new LambdaQueryChainWrapper<Member>(memberMapper)
                .eq(Member::getMemberName,member.getMemberName()).list();
        if (members.size() == 0) {
            if (memberMapper.insert(member) != 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        return memberMapper.deleteById(id) != 0;
    }

    @Override
    public boolean update(Member member) {
        return memberMapper.updateById(member) != 0;
    }

    @Override
    public Member select(Integer id) {
        return memberMapper.selectById(id);
    }

    @Override
    public List<Member> selectAll() {
        return new LambdaQueryChainWrapper<Member>(memberMapper)
                .orderByDesc(Member::getMemberGrade)
                .list();
    }

    @Override
    public List<Member> selectResponsibility(String responsibility) {
        return new LambdaQueryChainWrapper<Member>(memberMapper)
                .eq(Member::getMemberResponsibility,responsibility)
                .orderByDesc(Member::getMemberGrade)
                .list();
    }

    @Override
    public List<Member> selectGrade(Integer grade) {
        return new LambdaQueryChainWrapper<Member>(memberMapper)
                .eq(Member::getMemberGrade,grade).list();
    }

    @Override
    public List<Member> selectDirection(String direction) {
        return new LambdaQueryChainWrapper<Member>(memberMapper)
                .eq(Member::getMemberDirection,direction).list();
    }

    @Override
    public List<Member> selectDirectionAddGrade(String direction, Integer grade) {
        return new LambdaQueryChainWrapper<Member>(memberMapper)
                .eq(Member::getMemberGrade,grade)
                .eq(Member::getMemberDirection,direction)
                .list();
    }

    @Override
    public List<Member> selectResponsibilityAddGrade(String responsibility, Integer grade) {
        return new LambdaQueryChainWrapper<Member>(memberMapper)
                .eq(Member::getMemberGrade,grade)
                .eq(Member::getMemberResponsibility,responsibility)
                .list();
    }
}
