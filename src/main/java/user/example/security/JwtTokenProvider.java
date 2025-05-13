package user.example.security;


import java.security.Key;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

	
	@Value("${app.jwt-secret}")
	private String secret;
	
	
	@Value("${ app.jwt.expiration-milliseconds}")
	private long jwtExpirationDate;
	
	
	
	// Create token 
	public String generateToken(Authentication authentication )
	{
		
		String username =authentication.getName();
		
		Date dateGet=new Date();
		
		 Date expiration = new Date( dateGet.getTime()+ jwtExpirationDate);
		 
		 String  token=Jwts.builder()
                      .subject(username)
                      .issuedAt(new Date())
                      .expiration(expiration)
                      .signWith(key2())
                      .compact();
		 
		 return token ;
		
	}
	
	private Key key2()
	{
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)); 
 	}
	
	
	// extract username from token 
	 public String getUSerNamefromToken(String token )
	 {
		 
		  return Jwts.parser()
		 .verifyWith((SecretKey) key2())
		 .build()
		 .parseSignedClaims(token)
		 .getPayload()
		 .getSubject();
		 
	 }
	
	 
	 
	 // Validate token 
	 
	 
}
