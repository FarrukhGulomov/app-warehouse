package uz.pdp.online.appwarehouse.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.service.AttachmentService;

@RestController
@RequestMapping("/upload")
public class AttachmentController {
    AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PostMapping
    public Result upload(MultipartHttpServletRequest request){
        return attachmentService.addPhoto(request);
    }

}
