package com.westfelt.wigellmcrental.services;

import com.westfelt.wigellmcrental.entities.Motorcycle;

import java.util.List;

public interface McService {

    List<Motorcycle> findAll();
    Motorcycle findById(int id);
    Motorcycle save(Motorcycle motorcycle);
    void deleteById(int id);
    Motorcycle updateMc(int id, Motorcycle motorcycle);
    List<Motorcycle> findAvailableBikes();

}
