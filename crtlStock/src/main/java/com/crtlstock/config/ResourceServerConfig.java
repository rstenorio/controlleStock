package com.crtlstock.config;

//@Configuration
//@EnableResourceServer
public class ResourceServerConfig{
/*

	
	@Autowired
	private JwtTokenStore store;

	@Autowired
	private Environment env;
	
	private static final String[] PUBLIC = { "/oauth/token" , "/h2-console/**"}; 
	private static final String[] OPERATOR_OR_ADMIN = { "/products/**", "/categories/**" };
	private static final String[] ADMIN = { "/users/**" };
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(store);
	}
	@Override
	public void configure(HttpSecurity http) throws Exception {

		//Acesso ao H2
		if(Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		
		http
			.authorizeRequests()
			.antMatchers(PUBLIC).permitAll()
			.antMatchers(HttpMethod.GET,OPERATOR_OR_ADMIN).permitAll()
			.antMatchers(ADMIN).hasRole("ADMIN")
			.anyRequest().authenticated();
	}
 */
}