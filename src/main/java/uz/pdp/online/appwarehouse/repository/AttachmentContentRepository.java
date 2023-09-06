package uz.pdp.online.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.appwarehouse.entity.AttachmentContent;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent,Integer> {
}
