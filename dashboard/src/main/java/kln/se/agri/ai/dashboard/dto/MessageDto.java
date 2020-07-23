package kln.se.agri.ai.dashboard.dto;

import lombok.Data;

@Data
public class MessageDto {
    private String message;
    private String fromId;
    private String toId;
    private String time;
}
