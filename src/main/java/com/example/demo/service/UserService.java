package com.example.demo.service;

import com.example.demo.common.Result;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserService {
    @Resource
    private UserDao userDao;

    /**
     * 新增和修改
     * @param user
     */
    public void save(User user){
        String now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        user.setCreateTime(now);
        userDao.save(user);
    }

    /**
     * 删除
     */
    public void del(Long id){
        userDao.deleteById(id);
    }

    /**
     * 通过id查找用户
     * @param id
     * @return
     */
    public User findById(Long id){
        return userDao.findById(id).orElse(null);
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Pageable findPage(Integer pageNum,Integer pageSize){
        // 创建分页请求
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return pageable;

    }

    public Result getUserLikeName(String name,Integer pageNum,Integer pageSize ){
        Pageable pageable = findPage(pageNum-1, pageSize);
        Page<User> nameLike = userDao.findNameLike(name, pageable);
        return Result.success(nameLike);
    }
}
