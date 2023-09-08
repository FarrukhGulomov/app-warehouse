package uz.pdp.online.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.online.appwarehouse.entity.Client;
import uz.pdp.online.appwarehouse.entity.Supplier;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.repository.ClientRepository;

@Service
public class ClientService {
    ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Result addClient(Client client){
        boolean existsByPhoneNumberAndName = clientRepository.existsByPhoneNumberAndName(client.getPhoneNumber(), client.getName());
        if(existsByPhoneNumberAndName) return new Result("This client is alreadt exist!",false);
        clientRepository.save(client);
        return new Result("Client is successfully added!",true);
    }
}
