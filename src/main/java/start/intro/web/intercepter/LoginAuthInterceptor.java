package start.intro.web.intercepter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        String requestURI = request.getRequestURI();

        log.info("Login preHandle Auth check {}",requestURI);

        if (session == null || request.getAttribute("loginMember") == null){

            log.info("허용되지 않은 요청");
            response.sendRedirect("/intro?redirectURL=" + requestURI);

            return false;
        }

        return true;

    }
}
