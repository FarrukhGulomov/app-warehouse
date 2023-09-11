package uz.pdp.online.appwarehouse.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
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
    public Result upload(MultipartHttpServletRequest request) {
        return attachmentService.addPhoto(request);
    }

    @GetMapping("/byAttachmentId/{id}")
    public void getPhoto(@PathVariable Integer id, HttpServletResponse response) {
        attachmentService.getPhoto(id, response);
    }

    @PutMapping("/editPhotoById/{id}")
    public Result editPhoto(MultipartHttpServletRequest request, @PathVariable Integer id) {
        return attachmentService.editPhoto(request, id);
    }

    @DeleteMapping("/deletePhoto/{id}")
    public Result deletePhoto(@PathVariable Integer id){
        return attachmentService.deletePhoto(id);
    }

}
