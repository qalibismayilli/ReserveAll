package com.reserveall.reserveall.service;

import com.reserveall.reserveall.dto.ClientMessageRequestDto;
import com.reserveall.reserveall.dto.ClientMessageResponseDto;
import com.reserveall.reserveall.model.ClientMessage;
import com.reserveall.reserveall.repository.ClientMessageRepository;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientMessageService {

    private final ClientMessageRepository clientMessageRepository;

    public ClientMessageService(ClientMessageRepository clientMessageRepository) {
        this.clientMessageRepository = clientMessageRepository;
    }

    private static ClientMessageResponseDto convertToResponse(ClientMessage request){
        return new ClientMessageResponseDto( request.getId(), request.getName(),
                request.getEmail(), request.getSubject(), request.getMessage());
    }

    @Transactional
    public ClientMessageResponseDto createMessage(@NotNull ClientMessageRequestDto request) {
        ClientMessage clientMessage = new ClientMessage(request.getName(),
                request.getEmail(),
                request.getSubject(),
                request.getMessage());

        ClientMessage fromDb = clientMessageRepository.save(clientMessage);

        return convertToResponse(fromDb);

    }

    @Transactional
    public ClientMessageResponseDto removeMessage(String id){
        ClientMessage fromDb = clientMessageRepository.findById(id).orElseThrow();
        clientMessageRepository.delete(fromDb);
        return convertToResponse(fromDb);
    }


    public List<ClientMessageResponseDto> getAllMessages(){
        List<ClientMessage> messages = clientMessageRepository.findAll();
        return messages.stream().map(m -> convertToResponse(m)).toList();
    }

}
