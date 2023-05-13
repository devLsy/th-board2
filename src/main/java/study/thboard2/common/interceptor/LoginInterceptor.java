package study.thboard2.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
//로그인 인터셉터
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        log.info("interceptor_requestURI = [{}]", requestURI);

        HttpSession session = request.getSession();
        if ("none".equals(session.getAttribute("id")) || session.getAttribute("id") == null) {
            log.info("로그인을 안했네? 로그인으로 가버려~~~~~~~~~");
            response.sendRedirect("/login");
            return false;
        }
        log.info("옳지! 로그인 상태구나. 너 하고 싶은데로 가라.");
        log.info("stored_session_id = [{}]", session.getAttribute("id"));
        return true;
    }
}
