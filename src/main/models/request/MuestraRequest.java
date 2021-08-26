package ar.com.noaa.api.boyas.models.request;

import java.util.Date;

public class MuestraRequest {
    public Integer boyaId;
    public Date horario;
    public String matricula;
    public Double latitud;
    public Double longitud;
    public Double nivelMar;
}