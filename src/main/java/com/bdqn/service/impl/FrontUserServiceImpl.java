package com.bdqn.service.impl;
import com.bdqn.dao.FrontUserMapper;
import com.bdqn.pojo.FrontUser;
import com.bdqn.service.FrontUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class FrontUserServiceImpl implements FrontUserService {
    @Autowired
    FrontUserMapper frontUserMapper;
    @Override
    public List<FrontUser> selUser(FrontUser front) {
        return frontUserMapper.selUser(front);
    }
    @Override
    public int addUser(FrontUser frontUser) {
        return frontUserMapper.addUser(frontUser);
    }
    @Override
    public int delBatch(String[] ids) {
        return frontUserMapper.delBatch(ids);
    }

    @Override
    public int updateFrontUser(FrontUser user) {
        return frontUserMapper.updateFrontUser(user);
    }
    @Override
    public FrontUser findById(Integer uid) {
        return frontUserMapper.findById(uid);
    }

    @Override
    public FrontUser loginUser(String userName, String idCard, String phone) {
        return frontUserMapper.loginUser(userName,idCard,phone);
    }


}
