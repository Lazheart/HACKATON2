package com.example.demo.usuario.dto;

public class UsuarioDto {
    private Long usuarioId;
    private int totalSolicitudes;
    private int totalTokens;

    public UsuarioDto(Long usuarioId, int totalSolicitudes, int totalTokens) {
        this.usuarioId = usuarioId;
        this.totalSolicitudes = totalSolicitudes;
        this.totalTokens = totalTokens;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getTotalSolicitudes() {
        return totalSolicitudes;
    }

    public void setTotalSolicitudes(int totalSolicitudes) {
        this.totalSolicitudes = totalSolicitudes;
    }

    public int getTotalTokens() {
        return totalTokens;
    }

    public void setTotalTokens(int totalTokens) {
        this.totalTokens = totalTokens;
    }
}
