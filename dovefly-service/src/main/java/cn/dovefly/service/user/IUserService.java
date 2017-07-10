package cn.dovefly.service.user;

import cn.dovefly.orm.user.entity.User;

/**
 * Created by fengchunming on 2017/4/24.
 */
public interface IUserService {
    int add(User info);
    User getUserById(int id);
}
