package com.dominick.bff_agendador.infrastructure.client;


import com.dominick.bff_agendador.business.dto.in.EnderecoDTORequest;
import com.dominick.bff_agendador.business.dto.in.LoginRequestDTO;
import com.dominick.bff_agendador.business.dto.in.TelefoneDTORequest;
import com.dominick.bff_agendador.business.dto.in.UsuarioDTORequest;
import com.dominick.bff_agendador.business.dto.out.EnderecoDTOResponse;
import com.dominick.bff_agendador.business.dto.out.TarefasDTOResponse;
import com.dominick.bff_agendador.business.dto.out.TelefoneDTOResponse;
import com.dominick.bff_agendador.business.dto.out.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface EmailClient {

    void enviarEmail(@RequestBody TarefasDTOResponse dto);
}
