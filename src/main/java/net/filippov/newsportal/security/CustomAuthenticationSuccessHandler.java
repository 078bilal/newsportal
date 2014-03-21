package net.filippov.newsportal.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.filippov.newsportal.domain.User;
import net.filippov.newsportal.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component("AuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	UserService service;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		
		User user = service.getByLogin(auth.getName());
		request.getSession().setAttribute("loggedUser", user);
		super.onAuthenticationSuccess(request, response, auth);
	}
}