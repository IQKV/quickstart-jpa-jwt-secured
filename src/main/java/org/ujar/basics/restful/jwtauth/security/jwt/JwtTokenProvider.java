package org.ujar.basics.restful.jwtauth.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.ujar.basics.restful.jwtauth.entity.Role;

@Component
public class JwtTokenProvider {

  private final UserDetailsService userDetailsService;

  private final String secret;

  private final long validityInMilliseconds;

  @Autowired
  public JwtTokenProvider(UserDetailsService userDetailsService,
                          @Value("${jwt.token.secret}") String secret,
                          @Value("${jwt.token.expired}") long validityInMilliseconds) {
    this.userDetailsService = userDetailsService;
    this.secret = Base64.getEncoder().encodeToString(secret.getBytes());
    this.validityInMilliseconds = validityInMilliseconds;
  }

  public String createToken(final String username, final List<Role> roles) {

    final var claims = Jwts.claims().setSubject(username);
    claims.put("roles", getRoleNames(roles));

    final var now = new Date();
    final var validity = new Date(now.getTime() + validityInMilliseconds);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
  }

  public Authentication getAuthentication(String token) {
    UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  public String getUsername(String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
  }

  public String resolveToken(final HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
      return bearerToken.substring(7);
    }
    return null;
  }

  public boolean validateToken(String token) {
    try {
      Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);

      return !claims.getBody().getExpiration().before(new Date());
    } catch (JwtException | IllegalArgumentException e) {
      throw new JwtAuthenticationException("JWT token is expired or invalid");
    }
  }

  private List<String> getRoleNames(final List<Role> userRoles) {
    final var result = new ArrayList<String>();

    userRoles.forEach(role -> {
      result.add(role.getName());
    });

    return result;
  }
}
