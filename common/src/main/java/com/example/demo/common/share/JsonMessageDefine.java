package com.example.demo.common.share;/**
 * Created by fanbeibei on 2017/9/19.
 */

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Fan Beibei
 * @Description: 描述
 * @date 2017/9/19 15:45
 */
public class JsonMessageDefine {

    /**
     * 正常情况
     */
    public final static String SUCCESS = "SUCCESS";

    /**
     * 传入参数错误
     */
    public final static String PARAM_ERROR = "PARAM_ERROR";//,"传入错误参数或参数不全");

    /**
     * 没有符合条件的数据
     */
    public final static String NO_DATA = "NO_DATA";//,"没有符合条件的数据");

    /**
     * 执行出未知异常（正常情况不出现，调试时出现）
     */
    public final static String OP_FAILED = "OP_FAILED";

    /**
     * 无访问权限
     */
    public final static String AUTH_DENIED = "AUTH_DENIED";//,"无访问权限此资源");

    /**
     * 未登录
     */
    public static final String NO_LOGIN = "NO_LOGIN";

    /**
     * 会话过期
     */
    public static final String SESSION_TIMEOUT = "SESSION_TIMEOUT";

    /**
     * 账号过期
     */
    public static final String USER_TIMEOUT = "USER_TIMEOUT";

    /**
     * 用户名或密码错误
     */
    public static final String LOGIN_ERROR = "LOGIN_ERROR";

    /**
     * 用户名已存在
     */
    public static final String USER_EXIST = "USER_EXIST";

    /**
     * 服务到期
     */
    public static final String SERVICE_TIMEOUT = "SERVICE_TIMEOUT";

    /**
     * 用户已被禁用
     */
    public static final String USER_DISABLED = "USER_DISABLED";

    /**
     * 旧密码错误
     */
    public static final String OLD_PASSWORD_ERROR = "OLD_PASSWORD_ERROR";

    /**
     * 名称已存在
     */
    public static final String UNIQUE_NAME_EXIST = "UNIQUE_NAME_EXIST";

    /**
     * 编码已存在
     */
    public static final String UNIQUE_CODE_EXIST = "UNIQUE_CODE_EXIST";

    /**
     * 批量删除错误
     */
    public static final String BATCH_DELETE_ERROR = "BATCH_DELETE_ERROR";

    /**
     * 已关联数据，不能删除
     */
    public static final String USED_DELETE_ERROR = "USED_DELETE_ERROR";

    /**
     * 机构下无人，将该用户作为负责人
     */
    public static final String NO_USER = "NO_USER";

    /**
     * 机构负责人，不能删除
     */
    public static final String ORG_ADMIN_DELETE_ERROR = "ORG_ADMIN_DELETE_ERROR";


    /**
     * 4S店用户不能分配到车厂
     */
    public static final String SHOP_USER_TO_FACTORY = "SHOP_USER_FACTORY";


    /**
     * 车厂用户不能分配到4S店
     */
    public static final String FACTORY_USER_TO_SHOP = "FACTORY_USER_SHOP";

    /**
     * 首次登录
     */
    public static final String FIRST_TIME_LOGIN = "FIRST_TIME_LOGIN";

    /**
     * app登录设备ID错误
     */
    public static final String DEVICE_ID_ERROR = "DEVICE_ID_ERROR";



    /**
     * 上传文件最大限制10M
     */
    public static final String UPLOAD_FILE_MAX_SIZE_LIMIT_ERROR = "UPLOAD_FILE_MAX_SIZE_LIMIT_ERROR";

    private static Map<String, String> messageMap = new ConcurrentHashMap<>();

    static {
        messageMap.put(SUCCESS, "");
        messageMap.put(PARAM_ERROR, "传入错误参数或参数不全");
        messageMap.put(NO_DATA, "没有符合条件的数据");
        messageMap.put(AUTH_DENIED, "无访问权限此资源，有疑问请联系管理员");
        messageMap.put(NO_LOGIN, "您未登录系统，请登录");
        messageMap.put(SESSION_TIMEOUT, "会话超时，请重新登录");
        messageMap.put(LOGIN_ERROR, "用户名或密码错误");
        messageMap.put(USER_TIMEOUT, "该账号是临时账号，已过期不能再使用");
        messageMap.put(USER_EXIST, "用户名已存在");
        messageMap.put(OLD_PASSWORD_ERROR, "旧密码错误");
        messageMap.put(SERVICE_TIMEOUT, "该账号服务已到期，请续期");
        messageMap.put(USER_DISABLED, "该账号被禁用，有疑问请联系管理员");
        messageMap.put(UNIQUE_NAME_EXIST, "名称已存在");
        messageMap.put(UNIQUE_CODE_EXIST, "编码已存在");
        messageMap.put(UPLOAD_FILE_MAX_SIZE_LIMIT_ERROR, "上传文件最大限制10M");
        messageMap.put(BATCH_DELETE_ERROR, "不支持批量删除");
        messageMap.put(USED_DELETE_ERROR, "已关联数据，不能删除");
        messageMap.put(NO_USER, "机构下无人，将该用户作为负责人");
        messageMap.put(ORG_ADMIN_DELETE_ERROR, "机构负责人，不能删除");
        messageMap.put(SHOP_USER_TO_FACTORY, "4S店用户不能分配到车厂");
        messageMap.put(FACTORY_USER_TO_SHOP, "车厂用户不能分配到4S店");

    }

    public static String getMessage(String code) {
        return messageMap.get(code);
    }

}
