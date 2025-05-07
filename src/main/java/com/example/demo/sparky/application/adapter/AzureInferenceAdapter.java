package com.example.demo.sparky.application.adapter;

import com.azure.ai.inference.ChatCompletionsClient;
import com.azure.ai.inference.ChatCompletionsClientBuilder;
import com.azure.ai.inference.models.*;
import com.azure.core.credential.AzureKeyCredential;
import com.example.demo.sparky.infrastructure.ChatUseCase;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AzureInferenceAdapter implements ChatUseCase {

    private final ChatCompletionsClient client;

    public AzureInferenceAdapter() {
        String key = System.getenv("AZURE_KEY");
        String endpoint = "https://models.github.ai/inference";

        this.client = new ChatCompletionsClientBuilder()
                .credential(new AzureKeyCredential(key))
                .endpoint(endpoint)
                .buildClient();
    }

    private String callModel(String model, String prompt) {
        List<ChatRequestMessage> messages = Arrays.asList(
                new ChatRequestSystemMessage("You are a helpful assistant."),
                new ChatRequestUserMessage(prompt)
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

    @Override
    public String chatWithOpenAI(String prompt) {
        return callModel("openai/gpt-4.1-nano", prompt);
    }

    @Override
    public String chatWithMeta(String prompt) {
        return callModel("meta-llama/Meta-Llama-3-8B-Instruct", prompt);
    }

    @Override
    public String chatWithDeepSpeak(String prompt) {
        return callModel("deepspeak/tts-v1", prompt);
    }

    @Override
    public String chatWithMultimodal(String prompt) {
        return callModel("llava-hf/llava-1.5-7b-hf", prompt);
    }
}
