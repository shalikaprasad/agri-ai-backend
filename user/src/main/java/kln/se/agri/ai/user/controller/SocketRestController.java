package kln.se.agri.ai.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import kln.se.agri.ai.commons.model.Message;
import kln.se.agri.ai.persistence.dto.MessageDto;
import kln.se.agri.ai.persistence.service.MessageService;
import kln.se.agri.ai.pub.rest.response.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/socket")
@Api(value = "socket", tags = "Socket Controller")
@CrossOrigin("*")
public class SocketRestController {

    private final static Logger logger = LoggerFactory.getLogger(SocketRestController.class);

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private MessageService messageService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> sendMessage(@RequestBody Map<String, String> message){
        if(message.containsKey("message")){
            if(message.containsKey("toId") && message.get("toId")!=null && !message.get("toId").equals("")){
                this.simpMessagingTemplate.convertAndSend("/socket-publisher/"+message.get("toId"),message);
                this.simpMessagingTemplate.convertAndSend("/socket-publisher/"+message.get("fromId"),message);
                Message saveMessage = new Message();
                saveMessage.setMessage(message.get("message"));
                saveMessage.setFromId(message.get("fromId"));
                saveMessage.setToId(message.get("toId"));
                saveMessage.setTime(message.get("time"));
                saveMessage.setIsDeleted(false);
                messageService.saveMessage(saveMessage);
            }else{
                this.simpMessagingTemplate.convertAndSend("/socket-publisher",message);
            }
            return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @MessageMapping("/send/message")
    public Map<String, String> broadcastNotification(String message){
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> messageConverted = null;
        try {
            messageConverted = mapper.readValue(message, Map.class);
        } catch (IOException e) {
            messageConverted = null;
        }
        if(messageConverted!=null){
            if(messageConverted.containsKey("toId") && messageConverted.get("toId")!=null && !messageConverted.get("toId").equals("")){
                this.simpMessagingTemplate.convertAndSend("/socket-publisher/"+messageConverted.get("toId"),messageConverted);
                this.simpMessagingTemplate.convertAndSend("/socket-publisher/"+messageConverted.get("fromId"),message);
            }else{
                this.simpMessagingTemplate.convertAndSend("/socket-publisher",messageConverted);
            }
        }
        return messageConverted;
    }

    @RequestMapping(value = "/messageList", method = RequestMethod.POST)
    public ResponseWrapper getMessageList(@RequestBody MessageDto messageDto) {
        logger.info(">> Getting Message List");
        List<Message> messageList = messageService.getMessageList(messageDto.getFromId(), messageDto.getToId());
        return ResponseWrapper.success(messageList);
    }

}
