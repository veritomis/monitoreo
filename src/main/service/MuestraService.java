package ar.com.noaa.api.boyas.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.noaa.api.boyas.entities.Boya;
import ar.com.noaa.api.boyas.entities.Muestra;
import ar.com.noaa.api.boyas.repos.MuestraRepository;

@Service
public class MuestraService {
    @Autowired
    MuestraRepository repoMuestra;

    @Autowired
    BoyaService boyaService;

    public Muestra regristroMuestra(Integer boyaId, Date horario, String matricula, double latitud, double longitud,
            double nivelMar) {

        Boya boya = boyaService.buscarPorId(boyaId);
        Muestra muestra = new Muestra();
        muestra.setBoya(boya);
        muestra.setHorarioMuestra(horario);
        muestra.setMatriculaEmbarcacion(matricula);
        muestra.setLatitud(latitud);
        muestra.setLongitud(longitud);
        muestra.setNivelMar(nivelMar);
        boya.setColorLuz(boya.obtenerColor(nivelMar));
        repoMuestra.save(muestra);
        return muestra;
    }

    public Muestra obtenerPorId(Integer id) {
        Optional<Muestra> opMuestra = repoMuestra.findById(id);

        if (opMuestra.isPresent())
            return opMuestra.get();
        else
            return null;
    }

    public List<Muestra> listarMuestrasPorBoya(Integer idBoya) {
        Boya boya = boyaService.buscarPorId(idBoya);
        return boya.getMuestras();
    }

    public void grabar(Muestra muestra) {
        repoMuestra.save(muestra);
    }

    public Muestra resetearColorPorMuestra(Integer id) {
        Muestra muestra = obtenerPorId(id);
        Boya boya = boyaService.buscarPorId(muestra.getBoya().getBoyaId());
        boya.setColorLuz("AZUL");
        boyaService.guardarBoya(boya);
        grabar(muestra);
        return muestra;
    }
}