package com.unimagdalena.productStore.services;

import java.util.List;

import com.unimagdalena.productStore.dto.ClienteDto;
import com.unimagdalena.productStore.exceptions.ClienteNotFoundException;

public interface ClienteServices {

    List<ClienteDto> getAll();

    ClienteDto guardar(ClienteDto data);

    ClienteDto actualizar(Long id, ClienteDto data);

    ClienteDto buscarPorId(Long id) throws ClienteNotFoundException;

    void eliminarPorId(Long id);
}
