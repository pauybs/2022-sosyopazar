package com.mcavlak.sosyobazaar.utils;

import com.mcavlak.sosyobazaar.repositories.ProvinceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final ProvinceRepository provinceRepository;

    public DatabaseLoader(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //todo iller eklenecek.

    }
}
