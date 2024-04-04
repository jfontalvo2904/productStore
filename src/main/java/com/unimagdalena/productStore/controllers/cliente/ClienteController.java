package com.unimagdalena.productStore.controllers.cliente;

import com.unimagdalena.productStore.dto.cliente.ClienteDto;
import com.unimagdalena.productStore.dto.cliente.ClienteToSaveDto;
import com.unimagdalena.productStore.dto.cliente.ClienteToUpdateDto;
import com.unimagdalena.productStore.entity.Cliente;
import com.unimagdalena.productStore.services.cliente.ClienteServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class ClienteController {

    private final ClienteServiceImpl clienteService;

    public ClienteController(ClienteServiceImpl clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("")
    ResponseEntity<List<ClienteDto>> obtenerClientes(){
        List<ClienteDto> clienteDtos = this.clienteService.obtenerTodos();
        return ResponseEntity.ok().body(clienteDtos);
    }
    @GetMapping("/{id}")
    ResponseEntity<ClienteDto> buscarPorId(@PathVariable("id") Long id){
        ClienteDto clienteDto = this.clienteService.buscarClientePorId(id);
        return ResponseEntity.ok().body(clienteDto);
    }

    @GetMapping("email/{email}")
    ResponseEntity<ClienteDto> buscarPorEmail(@PathVariable("email") String email){
        ClienteDto clienteDto = this.clienteService.buscarClientePorEmail(email);
        return ResponseEntity.ok().body(clienteDto);
    }

    @GetMapping("nombre/{nombre}")
    ResponseEntity<List<ClienteDto>> buscarPorNombre(@PathVariable("nombre") String nombre){
        List<ClienteDto> clientesDto = this.clienteService.buscarClientePorNombre(nombre);
        return ResponseEntity.ok().body(clientesDto);
    }

    @GetMapping("direccion/{direccion}")
    ResponseEntity<List<ClienteDto>> buscarClientesPorDireccion(@PathVariable("direccion") String direccion) {
        List<ClienteDto> clientesDto = this.clienteService.buscarClientePorDireccion(direccion);
        return ResponseEntity.ok().body(clientesDto);
    }

    @PostMapping("")
    ResponseEntity<ClienteDto> guardarCliente(@RequestBody ClienteToSaveDto clienteToSaveDto){
        ClienteDto clienteSaved = this.clienteService.crearCliente(clienteToSaveDto);
        return ResponseEntity.ok().body(clienteSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> actualizarUsuario(@PathVariable("id") Long id, @RequestBody ClienteToUpdateDto clienteToUpdateDto) {
        return ResponseEntity.ok().body(this.clienteService.actualizarCliente(id,clienteToUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCliente( @PathVariable("id") Long id) {
        this.clienteService.eliminarCliente(id);
        return ResponseEntity.ok().body("Cliente eliminado correctamente");
    }

    
}
