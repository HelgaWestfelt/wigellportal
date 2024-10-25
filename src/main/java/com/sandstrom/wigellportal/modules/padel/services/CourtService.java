package com.sandstrom.wigellportal.modules.padel.services;

import com.sandstrom.wigellportal.modules.padel.entities.Court;

import java.util.List;

public interface CourtService {

    List<Court> getAllCourts();

    Court getCourtById(int id);

    Court createCourt(Court court);

    Court updateCourt(int id, Court courtDetails);

    void deleteCourt(int id);
}
