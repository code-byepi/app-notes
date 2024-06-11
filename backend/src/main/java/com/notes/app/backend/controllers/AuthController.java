package com.notes.app.backend.controllers;

import com.notes.app.backend.auth.JwtUtils;
import com.notes.app.backend.entities.Usuario;
import com.notes.app.backend.entities.auth.JwtRequest;
import com.notes.app.backend.entities.auth.JwtResponse;
import com.notes.app.backend.services.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private JwtUtils jwtUtil;


    @PostMapping(value = "/api/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            authenticationManager.authenticate( // Usa el authenticationManager para autenticar
                    new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }
        Usuario userDetails = customUserDetailService.loadUserByUsername(jwtRequest.getEmail());
        System.out.println(userDetails.getPassword());
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            throw new Exception("User Not Found");
        }
    }

    @GetMapping("/api/currentuser")
    public Usuario getCurrentUser(Principal principal) {
        return ((this.customUserDetailService.loadUserByUsername(principal.getName())));
    }
}
