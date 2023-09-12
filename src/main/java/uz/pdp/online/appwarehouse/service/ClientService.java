package uz.pdp.online.appwarehouse.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.online.appwarehouse.entity.Client;
import uz.pdp.online.appwarehouse.entity.Supplier;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.repository.ClientRepository;

import java.util.Optional;

@Service
public class ClientService {
    ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Result addClient(Client client){
        boolean existsByPhoneNumberAndName = clientRepository.existsByPhoneNumberAndName(client.getPhoneNumber(), client.getName());
        if(existsByPhoneNumberAndName) return new Result("This client is already exist!",false);
        clientRepository.save(client);
        return new Result("Client is successfully added!",true);
    }

    public Page<Client> getClientPage(Integer page){
        int size=10;
        Pageable pageable= PageRequest.of(page,size);
        return clientRepository.findAll(pageable);
    }

    public Result getClientById(Integer id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.map(client ->
                new Result("Successfully", true, client)).orElseGet(()
                -> new Result("Client is not found by this id!", false));

//        if(optionalClient.isEmpty()) return new Result("Client is not found by this id!",false);
//        return new Result("Successfully",true,optionalClient.get());   yuqoridagi bn bir xil
    }

    public Result editClient(Integer id,Client client){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if(optionalClient.isEmpty()) return new Result("Client is not found by this id",false);
        Client editingClient = optionalClient.get();
        editingClient.setName(client.getName());
        editingClient.setPhoneNumber(client.getPhoneNumber());
        Client savedClient = clientRepository.save(editingClient);
        return new Result("Client is successfully edited",true,savedClient);
    }

    public Result deleteClient(Integer id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if(optionalClient.isEmpty()) return new Result("Client is not found by this id",false);
        boolean existsClientInOutput = clientRepository.existsClientInOutputId(id);
        if(existsClientInOutput) return new Result("You can't delete this client just now,because of relationship to output entity!",false);
        clientRepository.deleteById(id);
        return new Result("Client is successfully deleted by this id",true);
    }

}
