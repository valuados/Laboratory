package com.netcracker.laboratory.hooks;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.struts.LastPath;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class LandingRedirectAction extends Action {

    protected static final Log log = LogFactory.getLog(LandingRedirectAction.class);

    public void run(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        try {
            User user = (User) request.getSession().getAttribute(WebKeys.USER);

            List<Role> roles = user.getRoles();
            Resource resource = new ClassPathResource("/redirect.properties");
            Properties props = PropertiesLoaderUtils.loadProperties(resource);

            for (Role role : roles) {
                String canonicalName = role.getName().toLowerCase().replaceAll(" ", ".");
                String url = props.getProperty("portal.redirect.role." + canonicalName);
                if (StringUtils.isNotEmpty(url)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("LAST_PATH", new LastPath(StringPool.BLANK, url));
                    break;
                }
            }

        } catch (SystemException | IOException e) {
            log.error("Error in LandingRedirectAction", e);
            throw new ActionException(e);
        }
    }

}