package com.buleocean_health.springboot.service;

import com.buleocean_health.springboot.domain.Test;

public interface TestService {
	
	public Test selectUserByUsername(String name);
	
}
