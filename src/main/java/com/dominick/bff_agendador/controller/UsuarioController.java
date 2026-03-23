package com.dominick.bff_agendador.controller;


import com.dominick.bff_agendador.business.UsuarioService;
import com.dominick.bff_agendador.business.dto.in.EnderecoDTORequest;
import com.dominick.bff_agendador.business.dto.in.LoginRequestDTO;
import com.dominick.bff_agendador.business.dto.in.TelefoneDTORequest;
import com.dominick.bff_agendador.business.dto.in.UsuarioDTORequest;
import com.dominick.bff_agendador.business.dto.out.EnderecoDTOResponse;
import com.dominick.bff_agendador.business.dto.out.TelefoneDTOResponse;
import com.dominick.bff_agendador.business.dto.out.UsuarioDTOResponse;
import com.dominick.bff_agendador.business.dto.out.ViaCepDTOResponse;
import com.dominick.bff_agendador.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "Cadastro e login de usuario")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salvar usuario", description = "Cria um novo usuario")
    @ApiResponse(responseCode = "200", description = "Usuario salvo com sucesso")
    @ApiResponse(responseCode = "409", description = "Usuario já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> savaUsuario(@RequestBody UsuarioDTORequest usuarioDTO){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login Usuario", description = "Login de usuario")
    @ApiResponse(responseCode = "200", description = "Usuario logado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credencias invalidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public String login(@RequestBody LoginRequestDTO usuarioDTO){
        return usuarioService.loginUsuario(usuarioDTO);
    }

    @GetMapping
    @Operation(summary = "Buscar dados de usuarios por email", description = "Buscar dados do usuario")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credencias invalidas")
    public ResponseEntity<UsuarioDTOResponse> buscaUsuarioPorEmail(@RequestParam("email") String email,
                                                                   @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta usuarios por id", description = "Deleta usuario")
    @ApiResponse(responseCode = "200", description = "Usuario deletado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credencias invalidas")
    public ResponseEntity<Void> deletaUsuarioPorEmail(
            @PathVariable String email, @RequestHeader(name = "Authorization", required = false) String token){

        usuarioService.deletaUsuarioPorEmail(email, token);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualiza dados de usuario", description = "Atualiza dados de usuario")
    @ApiResponse(responseCode = "200", description = "Usuario Atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credencias invalidas")
    public ResponseEntity<UsuarioDTOResponse> atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                                                   @RequestHeader(name = "Authorization", required = false) String token){

        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, dto));

    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza endereço de usuario", description = "Atualiza endereco de usuario")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credencias invalidas")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestBody EnderecoDTORequest dto, @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token){

        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza telefone de usuario", description = "Atualiza telefone de usuario")
    @ApiResponse(responseCode = "200", description = "telefone atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credencias invalidas")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest dto, @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token){

        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Salva endereço de usuario", description = "Salva endereco de usuario")
    @ApiResponse(responseCode = "200", description = "Endereço salvo com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credencias invalidas")
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, dto));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Salva telefone de usuario", description = "Salva telefone de usuario")
    @ApiResponse(responseCode = "200", description = "telefone salvo com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credencias invalidas")
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, dto));
    }

    @GetMapping("/endereco/{cep}")
    @Operation(summary = "Busca endereço pelo cep", description = "Busca dados de endereço recebendo um cep")
    @ApiResponse(responseCode = "200", description = "Dados de endereço retornados com sucesso")
    @ApiResponse(responseCode = "400", description = "Cep invalido")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ViaCepDTOResponse> buscarEndereco(@PathVariable("cep") String cep){
        return ResponseEntity.ok(usuarioService.buscarDadosCep(cep));
    }
}
