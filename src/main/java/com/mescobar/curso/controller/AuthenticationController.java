package com.mescobar.curso.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
	 private final TokenProvider tokenProvider;
	    private final AuthenticationManagerBuilder authenticationManagerBuilder;

	    public AuthenticationController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
	        this.tokenProvider = tokenProvider;
	        this.authenticationManagerBuilder = authenticationManagerBuilder;
	    }

	    
	    @PostMapping("/login")
	    public ResponseEntity<JWTToken> login(@RequestBody AuthDTO authDTO) {
	        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
	            authDTO.getUsername(),
	            authDTO.getPassword()
	        );

	        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        final String jwt = tokenProvider.createToken(authentication, authDTO.isRememberMe());

	        HttpHeaders httpHeaders = new HttpHeaders();
	        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

	        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
	    }
	    
}
