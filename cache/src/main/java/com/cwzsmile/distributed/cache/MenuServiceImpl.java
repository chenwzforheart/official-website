package com.cwzsmile.distributed.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author csh9016
 * @date 2019/8/1
 */
@Service
public class MenuServiceImpl implements MenuService {

    /**
     * 缓存项的key是方法参数id
     *
     * @param id
     * @return
     */
    @Cacheable({"menu", "menuExt"})
    public Menu getMenu(Long id) {
        return null;
    }


    //@Cacheable("user_function")
    @Cacheable(cacheNames = "org", key = "#orgId")
    public boolean canAccessFunction(Long userId, Long orgId, String functionCode) {
        return false;
    }

    //@Cacheable(cacheNames = "org",keyGenerator = "myKeyGenerator")
    @Cacheable(cacheNames = "org", key = "#user.orgId")
    public Org findOrg(User user) {
        return null;
    }

    //@Cacheable(cacheNames = "org",condition = "#orgId>10000")
    @Cacheable(cacheNames = "org", unless = "#result.status==0")
    public Org findOrg(Long orgId) {
        return null;
    }
}
