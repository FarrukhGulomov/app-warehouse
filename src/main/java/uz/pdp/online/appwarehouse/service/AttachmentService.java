package uz.pdp.online.appwarehouse.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.online.appwarehouse.entity.Attachment;
import uz.pdp.online.appwarehouse.entity.AttachmentContent;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.repository.AttachmentContentRepository;
import uz.pdp.online.appwarehouse.repository.AttachmentRepository;

import java.io.IOException;
import java.util.Iterator;

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
}
