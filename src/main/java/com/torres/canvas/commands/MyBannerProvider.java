package com.torres.canvas.commands;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultBannerProvider;
import org.springframework.shell.support.util.OsUtils;
import org.springframework.stereotype.Component;

/**
 * @author Javier Torres
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyBannerProvider extends DefaultBannerProvider  {

	public String getBanner() {
		StringBuffer buf = new StringBuffer();
		buf.append("=======================================" + OsUtils.LINE_SEPARATOR);
		buf.append("*                                     *"+ OsUtils.LINE_SEPARATOR);
		buf.append("*            My Canvas                *" +OsUtils.LINE_SEPARATOR);
		buf.append("*                                     *" +OsUtils.LINE_SEPARATOR);
		buf.append("*          Javier Torres              *"+ OsUtils.LINE_SEPARATOR);
		buf.append("*                                     *" +OsUtils.LINE_SEPARATOR);
		buf.append("=======================================" + OsUtils.LINE_SEPARATOR);
		buf.append("Version:" + this.getVersion());
		return buf.toString();
	}

	public String getVersion() {
		return "0.1.0";
	}

	public String getWelcomeMessage() {
		return "Welcome to My Canvas CLI";
	}
	
	@Override
	public String getProviderName() {
		return "Welcome to My Canvas CLI";
	}
}