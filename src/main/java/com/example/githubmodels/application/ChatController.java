package com.example.githubmodels.application;

import com.example.githubmodels.domain.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/openai")
    public String openai(@RequestBody String prompt) {
        return chatService.chatWithOpenAI(prompt);
    }

    @PostMapping("/meta")
    public String meta(@RequestBody String prompt) {
        return chatService.chatWithMeta(prompt);
    }

    @PostMapping("/deepspeak")
    public String deepspeak(@RequestBody String prompt) {
        return chatService.chatWithDeepSpeak(prompt);
    }

    @PostMapping("/multimodal")
    public String multimodal(@RequestBody String prompt) {
        return chatService.chatWithMultimodal(prompt);
    }
}
