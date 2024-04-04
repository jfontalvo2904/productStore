package com.unimagdalena.productStore.services.detalleEnvio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unimagdalena.productStore.dto.detalleEnvio.DetalleEnvioDto;
import com.unimagdalena.productStore.dto.detalleEnvio.DetalleEnvioMapperImpl;
import com.unimagdalena.productStore.dto.detalleEnvio.DetalleEnvioToSaveDto;
import com.unimagdalena.productStore.entity.DetalleEnvio;
import com.unimagdalena.productStore.exceptions.DetalleEnvioNotFoundException;
import com.unimagdalena.productStore.repository.DetalleEnvioRepository;

@Service
public class DetalleEnvioServicesImpl implements DetalleEnvioServices {
    @Autowired
    private DetalleEnvioRepository detalleEnvioRepository;
    @Autowired
    private DetalleEnvioMapperImpl detalleEnvioMapper;

    @Override
    public List<DetalleEnvioDto> getAll() {
        List<DetalleEnvio> data = this.detalleEnvioRepository.findAll();
        return this.detalleEnvioMapper.detallesEnvioToDto(data);
    }

    @Override
    public DetalleEnvioDto guardar(DetalleEnvioToSaveDto data) {
        DetalleEnvio detalleEnvio = this.detalleEnvioMapper.detalleEnvioToSaveToDetalleEnvio(data);
        DetalleEnvio detalleEnvioSaved = this.detalleEnvioRepository.save(detalleEnvio);
        return this.detalleEnvioMapper.detalleEnvioToDto(detalleEnvioSaved);
    }

    @Override
    public DetalleEnvioDto actualizar(Long id, DetalleEnvioDto data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }

    @Override
    public DetalleEnvioDto buscarPorId(Long id) throws DetalleEnvioNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
    }

    @Override
    public void eliminarPorId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarPorId'");
    }
}
