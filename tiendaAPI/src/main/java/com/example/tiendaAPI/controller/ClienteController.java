package com.example.tiendaAPI.controller;

import com.example.tiendaAPI.model.Cliente;
import com.example.tiendaAPI.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @PostMapping("/clientes/crear")
    public String saveCliente(@RequestBody Cliente cliente){
        clienteService.saveCliente(cliente);
        return "Cliente creado exitosamente";
    }

    @GetMapping("/clientes")
    public List<Cliente> getClientes(){
        return clienteService.getClientes();
    }

    @PutMapping("/clientes/editar")
    public Cliente editCliente(@RequestBody Cliente cliente){
        clienteService.editCliente(cliente);
        return clienteService.findCliente(cliente.getId_cliente());
    }

    @DeleteMapping("/clientes/eliminar/{id_cliente}")
    public String deleteCliente(@PathVariable Long id_cliente){
        clienteService.deleteCliente(id_cliente);
        return "Cliente eliminado";
    }
}
