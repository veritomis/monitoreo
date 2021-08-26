package ar.com.noaa.api.boyas.controlles;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.noaa.api.boyas.entities.Muestra;
import ar.com.noaa.api.boyas.models.request.MuestraRequest;
import ar.com.noaa.api.boyas.models.response.GenericResponse;
import ar.com.noaa.api.boyas.models.response.MuestraResponse;
import ar.com.noaa.api.boyas.services.MuestraService;

@RestController
public class MuestraController {
    @Autowired
    MuestraService muestraService;

    @PostMapping("/muestras")
    public ResponseEntity<MuestraResponse> crearBoya(@RequestBody MuestraRequest mR) {
        Muestra muestra = muestraService.regristroMuestra(mR.boyaId, mR.horario, mR.matricula, mR.latitud, mR.longitud,
                mR.nivelMar);

        MuestraResponse mResponse = new MuestraResponse();
        mResponse.id = muestra.getMuestraId();
        mResponse.color = muestra.getBoya().getColorLuz();
        return ResponseEntity.ok(mResponse);
    }

    @GetMapping("muestras/boyas/{idBoya}")
    public ResponseEntity<List<Muestra>> listarMuestrasBoya(@PathVariable Integer idBoya) {
        List<Muestra> muestras = muestraService.listarMuestrasPorBoya(idBoya);
        return ResponseEntity.ok(muestras);
    }

    @DeleteMapping("/muestras/{id}")
    public ResponseEntity<GenericResponse> resetearColorBoya(@PathVariable Integer id) {
        Muestra muestra = muestraService.resetearColorPorMuestra(id);

        if (muestra == null) {
            return ResponseEntity.notFound().build();
        }

        GenericResponse gR = new GenericResponse();
        gR.isOk = true;
        gR.message = "Color reseteado con exito";
        gR.id = muestra.getMuestraId();
        return ResponseEntity.ok(gR);
    }
}