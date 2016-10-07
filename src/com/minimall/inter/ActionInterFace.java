package com.minimall.inter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.forward.ActionForward;

public interface ActionInterFace {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
