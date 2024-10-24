package com.sandstrom.wigellportal.padel.services;

import com.sandstrom.wigellportal.padel.entities.PadelAddress;
import com.sandstrom.wigellportal.padel.dao.PadelAddressRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PadelAddressServiceImpl implements PadelAddressService {

    // Skapa en logger med Log4j2
    private static final Logger logger = LogManager.getLogger(PadelAddressServiceImpl.class);

    private final PadelAddressRepository addressRepository;

    @Autowired
    public PadelAddressServiceImpl(PadelAddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<PadelAddress> getAllAddresses() {
        logger.info("Hämtar alla adresser");
        return addressRepository.findAll();
    }

    @Override
    public Optional<PadelAddress> getAddressById(int id) {
        logger.info("Hämtar adress med ID {}", id);
        return addressRepository.findById(id);
    }

    @Override
    public PadelAddress createAddress(PadelAddress address) {
        PadelAddress savedAddress = addressRepository.save(address);
        logger.info("Admin skapade en ny adress med ID {}", savedAddress.getId());
        return savedAddress;
    }

    @Override
    public PadelAddress updateAddress(int id, PadelAddress addressDetails) {
        PadelAddress address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Adressen hittades inte"));

        address.setStreet(addressDetails.getStreet());
        address.setZipCode(addressDetails.getZipCode());
        address.setCity(addressDetails.getCity());
        PadelAddress updatedAddress = addressRepository.save(address);

        logger.info("Admin uppdaterade adress med ID {}", id);
        return updatedAddress;
    }

    @Override
    public void deleteAddress(int id) {
        PadelAddress address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Adressen hittades inte"));
        addressRepository.delete(address);
        logger.info("Admin tog bort adress med ID {}", id);
    }
}
