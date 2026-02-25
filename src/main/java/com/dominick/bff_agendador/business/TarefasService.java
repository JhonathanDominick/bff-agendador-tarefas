package com.dominick.bff_agendador.business;


import com.dominick.bff_agendador.business.dto.in.TarefasDTORequest;
import com.dominick.bff_agendador.business.dto.out.TarefasDTOResponse;
import com.dominick.bff_agendador.business.enums.StatusNotificacaoEnum;
import com.dominick.bff_agendador.infrastructure.client.TarefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasClient tarefasClient;

    public TarefasDTOResponse gravarTarefa(String token, TarefasDTORequest dto) {

        return tarefasClient.gravarTarefas(dto, token);
    }

    public List<TarefasDTOResponse> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial,
                                                                    LocalDateTime dataFinal,
                                                                    String token) {
        return tarefasClient.buscaListaDeTarefasPorPeriodo(dataInicial, dataFinal, token);

    }

    public List<TarefasDTOResponse> BuscarTarefasPorEmail(String token) {
       return tarefasClient.buscaTarefasPorEmail(token);
    }

    public void deletaTarefaPorId(String id, String token) {

        tarefasClient.deletaTarefaPorId(id, token);

    }

    public TarefasDTOResponse alteraStatus(StatusNotificacaoEnum staus, String id, String token) {
        return tarefasClient.alteraStatusNotificacao(staus, id, token);
    }

    public TarefasDTOResponse updateTarefas(TarefasDTORequest dto, String id, String token) {
        return tarefasClient.updateTarefas(dto, id, token);
    }

}

