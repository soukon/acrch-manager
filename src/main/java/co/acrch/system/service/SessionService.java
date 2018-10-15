package co.acrch.system.service;

import java.util.List;

import co.acrch.system.domain.UserOnline;
import co.acrch.system.domain.UserOnline;

public interface SessionService {

	List<UserOnline> list();

	boolean forceLogout(String sessionId);
}
