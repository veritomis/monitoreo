package ar.com.noaa.api.boyas.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.noaa.api.boyas.entities.Boya;

public interface BoyaRepository extends JpaRepository<Boya, Integer> {

}