package com.icicle.masterdb.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author liumingming
 * @version 2017-12-29 11:58.
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 20 * 60)
public class HttpSessionConfigurer {
}