package com.youlai.boot.core.security.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.youlai.boot.common.constant.SystemConstants;
import com.youlai.boot.core.security.model.SysUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Spring Security 工具类
 *
 * @author Ray
 * @since 2021/1/10
 */
public class SecurityUtils {

    /**
     * 获取当前登录人信息
     *
     * @return Optional<SysUserDetails>
     */
    public static Optional<SysUserDetails> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof SysUserDetails) {
                return Optional.of((SysUserDetails) principal);
            }
        }
        return Optional.empty();
    }


    /**
     * 获取用户ID
     *
     * @return Long
     */
    public static Long getUserId() {
        return getUser().map(SysUserDetails::getUserId).orElse(null);
    }


    /**
     * 获取用户账号
     *
     * @return String 用户账号
     */
    public static String getUsername() {
        return getUser().map(SysUserDetails::getUsername).orElse(null);
    }


    /**
     * 获取部门ID
     *
     * @return Long
     */
    public static Long getDeptId() {
        return getUser().map(SysUserDetails::getDeptId).orElse(null);
    }

    /**
     * 获取数据权限范围
     *
     * @return Integer
     */
    public static Integer getDataScope() {
        return getUser().map(SysUserDetails::getDataScope).orElse(null);
    }


    /**
     * 获取用户角色集合
     *
     * @return 角色集合
     */
    public static Set<String> getRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            if (CollectionUtil.isNotEmpty(authorities)) {
                return authorities.stream().filter(item -> item.getAuthority().startsWith("ROLE_"))
                        .map(item -> StrUtil.removePrefix(item.getAuthority(), "ROLE_"))
                        .collect(Collectors.toSet());
            }
        }
        return Collections.EMPTY_SET;
    }

    /**
     * 是否超级管理员
     * <p>
     * 超级管理员忽视任何权限判断
     */
    public static boolean isRoot() {
        Set<String> roles = getRoles();
        return roles.contains(SystemConstants.ROOT_ROLE_CODE);
    }

    /**
     * 获取请求中的 Token
     *
     * @return Token 字符串
     */
    public static String getTokenFromRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getHeader(HttpHeaders.AUTHORIZATION);
    }

    /**
     * 将 Token 加入黑名单并清空 Spring Security 上下文
     *
     * @param token 要失效的 Token
     */
    public static void invalidateToken(String token) {
        JwtUtils.addTokenToBlacklist(token);
        SecurityContextHolder.clearContext();
    }

}
