package cn.com.gmmc.monitor.common.share;

/**
 * Created by Kong on 2018/5/17.
 */
public interface I18nMessageSource {

    /**
     * 获得国际化字符串
     *
     * @param code i18n编码
     * @return
     */
    String getMessage(String code);
}
