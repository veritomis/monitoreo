package ar.com.noaa.api.boyas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.noaa.api.boyas.entities.Boya;
import ar.com.noaa.api.boyas.repos.BoyaRepository;

@Service
public class BoyaService {
    @Autowired
    BoyaRepository repoBoya;

    public Boya crearBoya(double longitudInstalacion, double latitudInstalacion) {
        Boya boya = new Boya();
        boya.setLongitudInstalacion(longitudInstalacion);
        boya.setLatitudInstalacion(latitudInstalacion);
        guardarBoya(boya);
        return boya;
    }

    public void guardarBoya(Boya boya){
        repoBoya.save(boya);
    }

    public List<Boya> obtenerBoyas() {
        return repoBoya.findAll();
    }

    public Boya buscarPorId(Integer id) {
        Optional<Boya> opBoya = repoBoya.findById(id);

        if (opBoya.isPresent())
            return opBoya.get();
        else
            return null;
    }

    public Boya actualizarBoya(Integer id, String colorLuz) {
        Boya boya = buscarPorId(id);
        boya.setColorLuz(colorLuz);
        repoBoya.save(boya);
        return boya;
    }
}