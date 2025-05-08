package com.example.demo.githubmodels.domain;

import com.azure.ai.inference.ChatCompletionsClient;
import com.azure.ai.inference.ChatCompletionsClientBuilder;
import com.azure.ai.inference.models.*;
import com.azure.core.credential.AzureKeyCredential;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ChatService {
    private final ChatCompletionsClient client;

    public ChatService() {
        String key = System.getenv("AZURE_KEY");
        String endpoint = "https://models.github.ai/inference";

        this.client = new ChatCompletionsClientBuilder()
                .credential(new AzureKeyCredential(key))
                .endpoint(endpoint)
                .buildClient();
    }

    private String callModel(String model, String userPrompt) {
        List<ChatRequestMessage> messages = Arrays.asList(
                new ChatRequestSystemMessage("You are a helpful assistant."),
                new ChatRequestUserMessage(userPrompt)
        );

        ChatCompletionsOptions options = new ChatCompletionsOptions(messages);
        options.setModel(model);

        ChatCompletions completions = client.complete(options);
        if (completions.getChoices() != null && !completions.getChoices().isEmpty()) {
            return completions.getChoices().get(0).getMessage().getContent();
        } else {
            return "No se recibieron respuestas del modelo.";
        }
    }

    public String chatWithOpenAI(String prompt) {
        return callModel("openai/gpt-4.1-nano", prompt);
    }

    public String chatWithMeta(String prompt) {
        return callModel("meta-llama/Meta-Llama-3-8B-Instruct", prompt);
    }

    public String chatWithDeepSpeak(String prompt) {
        return callModel("deepspeak/tts-v1", prompt);
    }

    public String chatWithMultimodal(String prompt) {
        return callModel("llava-hf/llava-1.5-7b-hf", prompt);
    }
}
