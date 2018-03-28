package com.prosegur.guvnor.negocio.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.prosegur.guvnor.negocio.controller.MappingsController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// handle 403 page
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	private static Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

	@Override
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AccessDeniedException e) throws IOException, ServletException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			logger.info("El usuario '" + auth.getName() + "' está intentando acceder sin permisos a la url: "
					+ httpServletRequest.getRequestURI());
		}

		httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + MappingsController.ERROR_403);

	}
}
