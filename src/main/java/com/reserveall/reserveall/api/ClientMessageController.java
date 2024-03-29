package com.reserveall.reserveall.api;

import com.reserveall.reserveall.dto.request.ClientMessageRequestDto;
import com.reserveall.reserveall.dto.response.ClientMessageResponseDto;
import com.reserveall.reserveall.service.ClientMessageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientMessageController")
public class ClientMessageController {
    private final ClientMessageService clientMessageService;

    public ClientMessageController(ClientMessageService clientMessageService) {
        this.clientMessageService = clientMessageService;
    }

    @PostMapping("/createMessage")
    public ResponseEntity<ClientMessageResponseDto> createMessage(@Valid @RequestBody ClientMessageRequestDto request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clientMessageService.createMessage(request));
    }

    @PostMapping("/admin/removeMessage")
    public ResponseEntity<ClientMessageResponseDto> removeMessage(String messageId) {
        return ResponseEntity.ok(clientMessageService.removeMessage(messageId));
    }

    @GetMapping("/admin/getAllMessages")
    public ResponseEntity<List<ClientMessageResponseDto>> getAllMessages(){
        return ResponseEntity.ok(clientMessageService.getAllMessages());
    }
}
