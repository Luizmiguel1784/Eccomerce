package com.list.ecommerce.service;

import com.list.ecommerce.DTOs.UsuarioRequest;
import com.list.ecommerce.DTOs.UsuarioResponse;
import com.list.ecommerce.config.EcommerceConfig;
import com.list.ecommerce.entity.Usuario;
import com.list.ecommerce.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

@Service
public class UsuarioService {


    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder  passwordEncoder;
    private final EcommerceConfig ecommerceConfig;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, EcommerceConfig eccomerceConfig, PasswordEncoder passwordEncoder1, EcommerceConfig ecommerceConfig) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder1;
        this.ecommerceConfig = ecommerceConfig;
    }

    //Post
    public UsuarioResponse criarUsuario(UsuarioRequest usuarioRequest) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuarioRequest.getEmail());
        if (usuarioExistente.isPresent()) {
            throw new RuntimeException("Email ja registrado");
        }
        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setNome(usuarioRequest.getNome());
        usuario.setTelefone(usuarioRequest.getTelefone());
        usuario.setSenha(passwordEncoder.encode(usuarioRequest.getSenha()));
        usuario.setRoles(usuarioRequest.getRoles());
        usuario.setPhoto(path);
        usuarioRepository.save(usuario);

        UsuarioResponse usuarioResponse = new UsuarioResponse(
                usuario.getId(),
                usuario.getTelefone(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPedidos(),
                usuario.getPhoto()
        );
        return usuarioResponse;


    }

    public List<UsuarioResponse> listarTodosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(usuario -> new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getPedidos(),
                usuario.getPhoto()
        )).toList();
    }

    public UsuarioResponse listarUsuarios(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getPedidos(),
                usuario.getPhoto()
        );
    }

    public void deletarUsuario(Integer id) {

     Usuario usuario =  usuarioRepository.findById(id).orElseThrow(()-> new RuntimeException("Usuario não encontrado"));
     usuarioRepository.delete(usuario);
    }

    public UsuarioResponse atualizarUsuario(Integer id,UsuarioRequest usuarioRequest) {

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(()-> new RuntimeException("Usuario não encontrado"));
        usuario.setNome(usuarioRequest.getNome());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setTelefone(usuarioRequest.getTelefone());
        usuario.setSenha(usuarioRequest.getSenha());
        UsuarioResponse usuarioResponse = new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getTelefone(),
                usuario.getEmail(),
                usuario.getPedidos(),
                usuario.getPhoto()
        );
          return usuarioResponse;
    }

}
