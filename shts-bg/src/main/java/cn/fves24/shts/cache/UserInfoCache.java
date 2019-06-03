package cn.fves24.shts.cache;

import cn.fves24.shts.entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户信息Cache
 *
 * @author fves
 */
public class UserInfoCache {

    private Map<String, User> userCache = new HashMap<>();

    private void addUser(User user) {
        userCache.put(user.getEmail(), user);
    }

    private void updateUser(User user) {
        addUser(user);
    }

    private void remoteUser(User user) {
        userCache.remove(user.getEmail());
    }

    private User getUser(String email) {
        return userCache.get(email);
    }
}
