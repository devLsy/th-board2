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
            log.info("미인증 사용자입니다. 로그인 페이지로 이동 합니다.");
            response.sendRedirect("/login");
            return false;
        }
        log.info("인증 성공!");
        log.info("stored_session_id = [{}]", session.getAttribute("id"));
        return true;
    }
}
