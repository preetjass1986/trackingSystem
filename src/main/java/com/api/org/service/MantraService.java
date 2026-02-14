package com.api.org.service;

import com.api.org.model.Users;
import com.api.org.view.Response;

public interface MantraService {
	
	 Response start();
	 Response stop();
	 Response capture();
	 Response autoCapture();
	 Response verify();
	 Response info();

}
