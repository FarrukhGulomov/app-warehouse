package uz.pdp.online.appwarehouse.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.appwarehouse.entity.Client;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {
    ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public Result addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @GetMapping
    public Page<Client> getClientPage(@RequestParam Integer page) {
        return clientService.getClientPage(page);
    }

    @GetMapping("/getClientById/{id}")
    public Result getClientById(@PathVariable Integer id){
       return clientService.getClientById(id);
    }

    @PutMapping("/editClient/{id}")

    public Result editClient(@PathVariable Integer id,@RequestBody Client client){
        return clientService.editClient(id,client);
    }

    @DeleteMapping("/deleteClient/{id}")
    public Result deleteClient(@PathVariable Integer id){
        return clientService.deleteClient(id);
    }
}
