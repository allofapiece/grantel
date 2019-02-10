package com.perpetum.grantel.controller;

import com.perpetum.grantel.entity.Message;
import com.perpetum.grantel.entity.User;
import com.perpetum.grantel.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping
    public String messageList(Model model) {
        model.addAttribute("messages", messageRepository.findAll());
        return "messages";
    }

    @PostMapping
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam(name = "text", defaultValue = "New message") String text,
            @RequestParam(name="id", required = false) Long id,
            @RequestParam("file") MultipartFile file,
            Map<String, Object> model) throws IOException {
        Message message;

        if (id != null) {
            message = messageRepository.findById(id).get();
            message.setText(text);
        } else {
            message = new Message(text);
            message.setAuthor(user);

            if (file != null) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "-" + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultFilename));
                message.setFilename(resultFilename);
            }
        }
        messageRepository.findAll();
        messageRepository.save(message);

        model.put("messages", messageRepository.findAll());

        return "messages";
    }
}
