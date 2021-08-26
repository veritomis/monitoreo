package ar.com.noaa.api.boyas.controlles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.noaa.api.boyas.entities.Boya;
import ar.com.noaa.api.boyas.models.request.ActualizColorLuzBoya;
import ar.com.noaa.api.boyas.models.request.BoyaRequest;
import ar.com.noaa.api.boyas.models.response.GenericResponse;
import ar.com.noaa.api.boyas.services.BoyaService;

@RestController
public class BoyaController {
    @Autowired
    BoyaService boyaService;

    @PostMapping("/boyas")
    public ResponseEntity<GenericResponse> crearBoya(@RequestBody BoyaRequest boyaRequest) {
        Boya boya = boyaService.crearBoya(boyaRequest.longitudInstalacion, boyaRequest.latitudInstalacion);

        GenericResponse r = new GenericResponse();
        r.isOk = true;
        r.message = "Boya creada con exito";
        r.id = boya.getBoyaId();
        return ResponseEntity.ok(r);
    }

    @GetMapping("/boyas")
    public ResponseEntity<List<Boya>> listarBoyas() {
        List<Boya> boyas = boyaService.obtenerBoyas();
        return ResponseEntity.ok(boyas);
    }

    @GetMapping("/boyas/{id}")
    ResponseEntity<Boya> buscarPorIdBoya(@PathVariable Integer id) {
        Boya boyas = boyaService.buscarPorId(id);
        return ResponseEntity.ok(boyas);
    }

    @PutMapping("/boyas/{id}")
    ResponseEntity<GenericResponse> actualizarBoyaPorId(@PathVariable Integer id,
            @RequestBody ActualizColorLuzBoya colorLuzAct) {

        Boya boyaActualizada = boyaService.actualizarBoya(id, colorLuzAct.color);

        if (boyaActualizada == null) {
            return ResponseEntity.notFound().build();
        }

        GenericResponse r = new GenericResponse();
        r.isOk = true;
        r.message = "Color luz actualizado con éxito";
        r.id = boyaActualizada.getBoyaId();

        return ResponseEntity.ok(r);
    }

}