package uz.pdp.online.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.appwarehouse.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {
}
