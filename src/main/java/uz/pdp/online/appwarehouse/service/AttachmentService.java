package uz.pdp.online.appwarehouse.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.online.appwarehouse.entity.Attachment;
import uz.pdp.online.appwarehouse.entity.AttachmentContent;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.repository.AttachmentContentRepository;
import uz.pdp.online.appwarehouse.repository.AttachmentRepository;

import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {
    AttachmentRepository attachmentRepository;
    AttachmentContentRepository attachmentContentRepository;

    public AttachmentService(AttachmentRepository attachmentRepository,
                             AttachmentContentRepository attachmentContentRepository) {
        this.attachmentRepository = attachmentRepository;
        this.attachmentContentRepository = attachmentContentRepository;
    }

    @SneakyThrows
    public Result addPhoto(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file != null) {
            String name = file.getName();
            String contentType = file.getContentType();
            long size = file.getSize();
            Attachment attachment = new Attachment(null, name, size, contentType);
            Attachment savedAttachment = attachmentRepository.save(attachment);
            AttachmentContent attachmentContent = new AttachmentContent();

            attachmentContent.setBytes(file.getBytes());
            attachmentContent.setAttachment(savedAttachment);
            attachmentContentRepository.save(attachmentContent);
            return new Result("Photo is added!", true, savedAttachment.getId());


        }
        return new Result("Internal error!", false);
    }


    @SneakyThrows
    public void getPhoto(@PathVariable Integer id, HttpServletResponse response) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            Optional<AttachmentContent> optionalContent = attachmentContentRepository.findByAttachmentId(id);
            if (optionalContent.isPresent()) {
                AttachmentContent attachmentContent = optionalContent.get();
                response.setHeader("Content-Disposition", "attachment; fileName=\"" + attachment.getName() + "\"");
                response.setContentType(attachment.getContentType());
                FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());
            }
        }
    }

    @SneakyThrows
    public Result editPhoto(MultipartHttpServletRequest request, Integer id){
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if(optionalAttachment.isEmpty()) return new Result("Attachment is not found!",false);
        Attachment attachment = optionalAttachment.get();
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if(file!=null) {
            attachment.setName(file.getName());
            attachment.setContentType(file.getContentType());
            attachment.setSize(file.getSize());
            Attachment savedAttachment = attachmentRepository.save(attachment);
            Optional<AttachmentContent> optionalContent = attachmentContentRepository.getAttachmentContentByAttachmentId(id);
            if(optionalContent.isEmpty()) return new Result("AttachmentContent is not found",false);
            AttachmentContent attachmentContent = optionalContent.get();
            attachmentContent.setAttachment(savedAttachment);
            attachmentContent.setBytes(file.getBytes());
            attachmentContentRepository.save(attachmentContent);
            return new Result("Photo is successfully edited!",true);

        }
        return new Result();
    }

    public Result deletePhoto(Integer id){
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if(optionalAttachment.isEmpty()) return new Result("Photo is not found!",false);
        Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.getAttachmentContentByAttachmentId(id);
        if(optionalAttachmentContent.isEmpty()) return new Result("Attachment Content is not found!",false);
        AttachmentContent attachmentContent = optionalAttachmentContent.get();
        attachmentContentRepository.deleteById(attachmentContent.getId());
        attachmentRepository.deleteById(id);
        return new Result("Photo is deleted by id:"+id,true);
    }
}
