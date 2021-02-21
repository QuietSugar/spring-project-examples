package name.xu;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import name.xu.webSocket.XuPrincipal;

import java.util.concurrent.TimeUnit;

/**
 * @author xu
 */
public class CacheUtil {

    /**
     * 用户
     */
    private static final Cache<String, XuPrincipal> cacheUser = CacheBuilder.newBuilder().maximumSize(1000)
            // 访问后过期
            .expireAfterAccess(30, TimeUnit.MINUTES).build();

    public static void putUser(String key, XuPrincipal value) {
        cacheUser.put(key, value);
    }

    public static XuPrincipal getUser(String key) {
        try {
            return cacheUser.getIfPresent(key);
        } catch (Exception e) {
            return null;
        }
    }

    public static void removeUser(String key) {
        cacheUser.invalidate(key);
    }
}
