package com.crtlstock.config;

//@Configuration
//@EnableAuthorizationServer
public class AuthorizationServerConfig {
/*

	@Value("${security.oauth2.client.client-id}")
	private String clientId;
	
	@Value("${security.oauth2.client.client-secret}")
	private String clientSecret;
	
	@Value("${jwt.duration}")
	private Integer jwtDuration;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private JwtAccessTokenConverter converter;
	
	@Autowired
	private JwtTokenStore store;
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private JwtTokenEnhancer enhancer;
	
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()"); 
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		.withClient(clientId)
		.secret(encoder.encode(clientSecret))
		.scopes("read","write")
		.authorizedGrantTypes("password")
		.accessTokenValiditySeconds(jwtDuration);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		TokenEnhancerChain chain = new TokenEnhancerChain();
		chain.setTokenEnhancers(Arrays.asList(converter, enhancer));
	
		endpoints.authenticationManager(manager)
		.tokenStore(store)
		.accessTokenConverter(converter)
		.tokenEnhancer(chain);
	}
 * */
	
}
