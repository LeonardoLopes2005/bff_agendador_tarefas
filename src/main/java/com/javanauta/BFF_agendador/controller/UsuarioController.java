package com.javanauta.BFF_agendador.controller;

import com.javanauta.BFF_agendador.business.UsuarioService;
import com.javanauta.BFF_agendador.business.dto.EnderecoDTO;
import com.javanauta.BFF_agendador.business.dto.TelefoneDTO;
import com.javanauta.BFF_agendador.business.dto.UsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
    @Tag(name = "Usuario", description = "Cadastro e login de Usuarios")
public class UsuarioController{

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salvar Usuario", description = "Cria um novo Usuario")
    @ApiResponse(responseCode = "200", description = "Usuario salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuario ja cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTO> salvaUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }

    // Spring security deixando mais seguro o login do usuario
    @PostMapping("/login")
    @Operation(summary = "Login Usuario", description = "Login do Usuario")
    @ApiResponse(responseCode = "200", description = "Usuario logado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public String login(@RequestBody UsuarioDTO usuarioDTO){
        return usuarioService.loginUsuario(usuarioDTO);
    }

    @GetMapping
    @Operation(summary = "Buscar dados de Usuario por Email", description = "Buscar dados do Usuario")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTO> buscaUsuariPorEmail(@RequestParam("email") String email,
                                                          @RequestHeader("Authorization")String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta Usuarios por Id", description = "Deleta Usuario")
    @ApiResponse(responseCode = "200", description = "Usuario Deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader("Authorization")String token){
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualiza dados de Usuario",description = "Atualizar dados de um Usuario")
    @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTO> atualizaDadosUsuario(@RequestBody UsuarioDTO dto,
                                                           @RequestHeader("Authorization")String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, dto));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza Endereco do Usuario", description = "Atualiza endereco de Usuario")
    @ApiResponse(responseCode = "200", description = "Atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTO> atualizaEndereco(@RequestBody EnderecoDTO dto,
                                                        @RequestParam ("id") Long id,
                                                        @RequestHeader("Authorization")String token){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza Telefone do Usuario", description = "Atualiza Telefone de Usuario")
    @ApiResponse(responseCode = "200", description = "Atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTO> atualizaTelefone(@RequestBody TelefoneDTO dto,
                                                        @RequestParam ("id") Long id,
                                                        @RequestHeader("Authorization")String token){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto,token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Salva Endereco do Usuario", description = "Salva Endereco de Usuario")
    @ApiResponse(responseCode = "200", description = "Endereco salvo com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTO> cadastraEndereco(@RequestBody EnderecoDTO dto,
                                                        @RequestHeader ("Authorization") String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(dto,token));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Salva Telefone do Usuario", description = "Salva Telefone de Usuario")
    @ApiResponse(responseCode = "200", description = "Telefone salvo com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTO> cadastraTelefone(@RequestBody TelefoneDTO dto,
                                                        @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.cadastroTelefone(token, dto));
    }
}
