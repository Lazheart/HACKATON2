package com.example.demo.sparky.application.web;

import com.example.demo.sparky.infrastructure.ChatUseCase;
import com.example.demo.sparky.domain.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatUseCase chatUseCase;

    @Autowired
    public ChatController(ChatUseCase chatUseCase) {
        this.chatUseCase = chatUseCase;
    }

    @PostMapping("/openai")
    public Response openai(@RequestBody String prompt) {
        return new Response(chatUseCase.chatWithOpenAI(prompt));
    }

    @PostMapping("/meta")
    public Response meta(@RequestBody String prompt) {
        return new Response(chatUseCase.chatWithMeta(prompt));
    }

    @PostMapping("/deepspeak")
    public Response deepspeak(@RequestBody String prompt) {
        return new Response(chatUseCase.chatWithDeepSpeak(prompt));
    }

    @PostMapping("/multimodal")
    public Response multimodal(@RequestBody String prompt) {
        return new Response(chatUseCase.chatWithMultimodal(prompt));
    }
}
