package com.example.collaborativecode.WebSocketController;

import java.util.Map;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.example.collaborativecode.Model.FileSynchronizerMessage;

@Controller
public class FileSynchronizerController {

    @MessageMapping("/sync")
    public void fileSync(FileSynchronizerMessage message) {
        System.out.println("hello");
        System.out.println(message.getLinesMap());
        // for (Map.Entry<Integer, String> entry : message.getLinesMap().entrySet()) {
        //     System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        // }
    }
    
}