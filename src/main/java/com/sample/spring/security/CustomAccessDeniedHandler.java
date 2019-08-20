package com.sample.spring.security;

import com.google.gson.JsonObject;
import net.minidev.json.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exc)
			throws IOException {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("message", "Access Denied!");
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.getWriter().write(jsonObject.toString());
	}
}