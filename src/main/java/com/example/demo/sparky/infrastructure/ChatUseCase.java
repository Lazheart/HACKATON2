package com.example.demo.sparky.infrastructure;

public interface ChatUseCase {
    String chatWithOpenAI(String prompt);
    String chatWithMeta(String prompt);
    String chatWithDeepSpeak(String prompt);
    String chatWithMultimodal(String prompt);
}
