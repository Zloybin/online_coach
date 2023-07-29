package dev.ezlobin.javarush.exercise_app.filter;

import dev.ezlobin.javarush.exercise_app.spring.provider_config.SessionFactoryProvider;
import dev.ezlobin.javarush.exercise_app.spring.provider_config.XMLPropertiesSessionFactoryProvider;
import dev.ezlobin.javarush.exercise_app.spring.repository.UserRepository;
import org.hibernate.SessionFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class GeneralFilter implements Filter {
    private static final String USER_REPOSITORY_VALUE = "user_repository";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        SessionFactoryProvider sessionFactoryProvider = new XMLPropertiesSessionFactoryProvider();
        SessionFactory sessionFactory = sessionFactoryProvider.getSessionFactory();
        UserRepository userRepository = new UserRepository(sessionFactory);
        servletContext.setAttribute(USER_REPOSITORY_VALUE, userRepository);

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        Long id = (Long) session.getAttribute("id");
        UserRepository userRepository = (UserRepository) request.getServletContext().getAttribute(USER_REPOSITORY_VALUE);
        if (id == null || userRepository.getById(id) == null){
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/authorization.html");
        }else{
            doFilter(httpRequest, httpResponse, chain);
        }
    }

    @Override
    public void destroy() {

    }
}
