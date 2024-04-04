package com.unimagdalena.productStore.dto.cliente;

import com.unimagdalena.productStore.entity.Cliente;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClienteMapper {

    @Mapping(target = "pedidos", expression = "java(java.util.Collections.emptyList())")
    Cliente clienteToSaveToCliente(ClienteToSaveDto cliente);

    ClienteDto clienteToClienteDto(Cliente cliente);

    @IterableMapping(elementTargetType = ClienteDto.class)
    List<ClienteDto> clientesToClientesDto(List<Cliente> clientes);
}
