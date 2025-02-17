package com.javanauta.BFF_agendador.business;

import com.javanauta.BFF_agendador.business.dto.EnderecoDTO;
import com.javanauta.BFF_agendador.business.dto.TelefoneDTO;
import com.javanauta.BFF_agendador.business.dto.UsuarioDTO;
import com.javanauta.BFF_agendador.infraestructure.cliente.UsuarioCliente;
import com.javanauta.BFF_agendador.infraestructure.cliente.UsuarioCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UsuarioService{

    private final UsuarioCliente client;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        return client.salvaUsuario(usuarioDTO);
    }

    public String loginUsuario(UsuarioDTO usuarioDTO){
        return client.login(usuarioDTO);
    }

    //busca o usuario por email
    public UsuarioDTO buscarUsuarioPorEmail(String email, String token){
       return client.buscaUsuariPorEmail(email, token);
    }

    // Deleta usuario pro email
    public void deletaUsuarioPorEmail(String email, String token){
         client.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTO atualizaDadosUsuario(String token, UsuarioDTO dto){
        return client.atualizaDadosUsuario(dto, token);
    }

    public EnderecoDTO atualizaEndereco(Long idEndereco, EnderecoDTO enderecoDTO, String token){
        return client.atualizaEndereco(enderecoDTO,idEndereco, token);
    }

    public TelefoneDTO atualizaTelefone(Long idTelefone, TelefoneDTO dto, String token){
        return client.atualizaTelefone(dto,idTelefone,token);
    }

    public EnderecoDTO cadastraEndereco( EnderecoDTO dto,String token) {
       return client.cadastraEndereco(dto,token);
    }

    public TelefoneDTO cadastroTelefone(String token, TelefoneDTO dto) {
        return client.cadastraTelefone(dto, token);
    }

}
