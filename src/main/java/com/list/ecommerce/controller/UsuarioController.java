package com.list.ecommerce.controller;

import com.list.ecommerce.DTOs.UsuarioRequest;
import com.list.ecommerce.DTOs.UsuarioResponse;
import com.list.ecommerce.service.PhotoService;
import com.list.ecommerce.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PhotoService photoService;


    public UsuarioController(UsuarioService usuarioService, PhotoService photoService) {
        this.usuarioService = usuarioService;
        this.photoService = photoService;
    }
    @PostMapping("/criar")
    public UsuarioResponse criarUsuario(@RequestBody UsuarioRequest usuarioRequest ){

        return usuarioService.criarUsuario(usuarioRequest);
    }
    @PutMapping("/atualizar/{id}")
    public UsuarioResponse atualizarUsuario(@RequestBody UsuarioRequest usuarioRequest,@PathVariable Integer id){

        return usuarioService.atualizarUsuario(id, usuarioRequest);
    }
    @GetMapping("/buscar/{id}")
    public UsuarioResponse listarUsuarios(@PathVariable Integer id){

        return usuarioService.listarUsuarios(id);
    }
    @GetMapping("/buscar")
    public List<UsuarioResponse> listarTodosUsuarios(@PathVariable Integer id){
        return usuarioService.listarTodosUsuarios();
    }
    @DeleteMapping("/deletar/{id}")
    public void deletarUsuario(@PathVariable Integer id){
        usuarioService.deletarUsuario(id);
    }

}
