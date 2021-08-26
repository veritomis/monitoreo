 package ar.com.noaa.api.boyas.repos;

 import org.springframework.data.jpa.repository.JpaRepository;
 
 import ar.com.noaa.api.boyas.entities.Muestra;
 
 public interface MuestraRepository extends JpaRepository<Muestra, Integer> {
     
 }