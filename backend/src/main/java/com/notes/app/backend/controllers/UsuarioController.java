package com.notes.app.backend.controllers;

import com.notes.app.backend.auth.JwtUtils;
import com.notes.app.backend.entities.Usuario;
import com.notes.app.backend.entities.auth.JwtRequest;
import com.notes.app.backend.entities.auth.JwtResponse;
import com.notes.app.backend.services.CustomUserDetailService;
import com.notes.app.backend.services.NotaService;
import com.notes.app.backend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private NotaService notaService;

    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(path = "/register/user", consumes = { "application/json" })
    public String addUsers(@RequestBody Usuario user) { // Devuelve ResponseEntity<String>
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return service.addUser(user);
    }

    // método verificar las credenciales proporcionadas por el usuario, sies correcta generar un token JWT
    @PostMapping(path = "/login/user", consumes = { "application/json" })
    public ResponseEntity<?> checkCredentials(@RequestBody JwtRequest jwtRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor");
        }

        Usuario userDetails = customUserDetailService.loadUserByUsername(jwtRequest.getEmail());
        String token = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }


    @GetMapping(path = "/users")
    public List<Usuario> allUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/users/email/{email}")
    public Usuario getUserByEmail(@PathVariable("email") String email) {
        return service.getUserByEmail(email);
    }

    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }



}
