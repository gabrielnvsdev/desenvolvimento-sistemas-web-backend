package com.example.desenvolvimento_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.desenvolvimento_web.dto.CadastroDTO;
import com.example.desenvolvimento_web.dto.LoginDTO;
import com.example.desenvolvimento_web.dto.UsuarioDTO;
import com.example.desenvolvimento_web.model.Usuario;
import com.example.desenvolvimento_web.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:4200")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO dto) {
        return usuarioRepository.findByEmail(dto.email())
                .filter(u -> u.getSenha().equals(dto.senha()))
                .map(u -> ResponseEntity.ok((Object) new UsuarioDTO(u.getId(), u.getEmail(), u.getNome())))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos!"));
    }

    @PostMapping("/cadastrar")
public ResponseEntity<Object> cadastrar(@RequestBody CadastroDTO dto) {

    if (usuarioRepository.findByEmail(dto.email()).isPresent()) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("E-mail já cadastrado!");
    }

    Usuario novoUsuario = new Usuario();
    novoUsuario.setNome(dto.nome()); 
    novoUsuario.setEmail(dto.email());
    novoUsuario.setSenha(dto.senha());

    usuarioRepository.save(novoUsuario);

    return ResponseEntity.ok((Object) new UsuarioDTO(
            novoUsuario.getId(),
            novoUsuario.getEmail(),
            novoUsuario.getNome()
    ));
}

}
