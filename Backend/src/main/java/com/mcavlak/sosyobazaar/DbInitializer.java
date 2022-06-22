package com.mcavlak.sosyobazaar;

import com.mcavlak.sosyobazaar.models.entities.Industry;
import com.mcavlak.sosyobazaar.models.entities.Province;
import com.mcavlak.sosyobazaar.repositories.IndustryRepository;
import com.mcavlak.sosyobazaar.repositories.ProvinceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbInitializer implements CommandLineRunner {

    private final ProvinceRepository provinceRepository;
    private final IndustryRepository industryRepository;

    public DbInitializer(ProvinceRepository provinceRepository, IndustryRepository industryRepository) {
        this.provinceRepository = provinceRepository;
        this.industryRepository = industryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if(provinceRepository.count() == 0){
            initProvinces();
        }

        if(industryRepository.count() == 0){
            initIndustries();
        }

    }

    private void initIndustries() {
        industryRepository.save(Industry.create("Butik"));
        industryRepository.save(Industry.create("Market"));
        industryRepository.save(Industry.create("Kuaför"));
        industryRepository.save(Industry.create("Sarrafiye"));
        industryRepository.save(Industry.create("Züccaciye"));
        industryRepository.save(Industry.create("Manav"));
        industryRepository.save(Industry.create("Kafe"));
        industryRepository.save(Industry.create("Kasap"));
        industryRepository.save(Industry.create("Şarküteri"));

        //TODO bura dolduralım
    }

    private void initProvinces() {
        provinceRepository.save(Province.create(1L,"Adana"));
        provinceRepository.save(Province.create(2L,"Adıyaman"));
        provinceRepository.save(Province.create(3L,"Afyonkarahisar"));
        provinceRepository.save(Province.create(4L,"Ağrı"));
        provinceRepository.save(Province.create(5L,"Amasya"));
        provinceRepository.save(Province.create(6L,"Ankara"));
        provinceRepository.save(Province.create(7L,"Antalya"));
        provinceRepository.save(Province.create(8L,"Artvin"));
        provinceRepository.save(Province.create(9L,"Aydın"));
        provinceRepository.save(Province.create(10L,"Balıkesir"));
        provinceRepository.save(Province.create(11L,"Bilecik"));
        provinceRepository.save(Province.create(12L,"Bingöl"));
        provinceRepository.save(Province.create(13L,"Bitlis"));
        provinceRepository.save(Province.create(14L,"Bolu"));
        provinceRepository.save(Province.create(15L,"Burdur"));
        provinceRepository.save(Province.create(16L,"Bursa"));
        provinceRepository.save(Province.create(17L,"Çanakkale"));
        provinceRepository.save(Province.create(18L,"Çankırı"));
        provinceRepository.save(Province.create(19L,"Çorum"));
        provinceRepository.save(Province.create(20L,"Denizli"));
        provinceRepository.save(Province.create(21L,"Diyarbakır"));
        provinceRepository.save(Province.create(22L,"Edirne"));
        provinceRepository.save(Province.create(23L,"Elazığ"));
        provinceRepository.save(Province.create(24L,"Erzincan"));
        provinceRepository.save(Province.create(25L,"Erzurum"));
        provinceRepository.save(Province.create(26L,"Eskişehir"));
        provinceRepository.save(Province.create(27L,"Gaziantep"));
        provinceRepository.save(Province.create(28L,"Giresun"));
        provinceRepository.save(Province.create(29L,"Gümüşhane"));
        provinceRepository.save(Province.create(30L,"Hakkari"));
        provinceRepository.save(Province.create(31L,"Hatay"));
        provinceRepository.save(Province.create(32L,"Isparta"));
        provinceRepository.save(Province.create(33L,"Mersin"));
        provinceRepository.save(Province.create(34L,"İstanbul"));
        provinceRepository.save(Province.create(35L,"İzmir"));
        provinceRepository.save(Province.create(36L,"Kars"));
        provinceRepository.save(Province.create(37L,"Kastamonu"));
        provinceRepository.save(Province.create(38L,"Kayseri"));
        provinceRepository.save(Province.create(39L,"Kırklareli"));
        provinceRepository.save(Province.create(40L,"Kırşehir"));
        provinceRepository.save(Province.create(41L,"Kocaeli"));
        provinceRepository.save(Province.create(42L,"Konya"));
        provinceRepository.save(Province.create(43L,"Kütahya"));
        provinceRepository.save(Province.create(44L,"Malatya"));
        provinceRepository.save(Province.create(45L,"Manisa"));
        provinceRepository.save(Province.create(46L,"Kahramanmaraş"));
        provinceRepository.save(Province.create(47L,"Mardin"));
        provinceRepository.save(Province.create(48L,"Muğla"));
        provinceRepository.save(Province.create(49L,"Muş"));
        provinceRepository.save(Province.create(50L,"Nevşehir"));
        provinceRepository.save(Province.create(51L,"Niğde"));
        provinceRepository.save(Province.create(52L,"Ordu"));
        provinceRepository.save(Province.create(53L,"Rize"));
        provinceRepository.save(Province.create(54L,"Sakarya"));
        provinceRepository.save(Province.create(55L,"Siirt"));
        provinceRepository.save(Province.create(56L,"Sinop"));
        provinceRepository.save(Province.create(57L,"Samsun"));
        provinceRepository.save(Province.create(58L,"Sivas"));
        provinceRepository.save(Province.create(59L,"Tekirdağ"));
        provinceRepository.save(Province.create(60L,"Tokat"));
        provinceRepository.save(Province.create(61L,"Trabzon"));
        provinceRepository.save(Province.create(62L,"Tunceli"));
        provinceRepository.save(Province.create(63L,"Şanlıurfa"));
        provinceRepository.save(Province.create(64L,"Uşak"));
        provinceRepository.save(Province.create(65L,"Van"));
        provinceRepository.save(Province.create(66L,"Yozgat"));
        provinceRepository.save(Province.create(67L,"Zonguldak"));
        provinceRepository.save(Province.create(68L,"Aksaray"));
        provinceRepository.save(Province.create(69L,"Bayburt"));
        provinceRepository.save(Province.create(70L,"Karaman"));
        provinceRepository.save(Province.create(71L,"Kırıkkale"));
        provinceRepository.save(Province.create(72L,"Batman"));
        provinceRepository.save(Province.create(73L,"Şırnak"));
        provinceRepository.save(Province.create(74L,"Bartın"));
        provinceRepository.save(Province.create(75L,"Ardahan"));
        provinceRepository.save(Province.create(76L,"Iğdır"));
        provinceRepository.save(Province.create(77L,"Yalova"));
        provinceRepository.save(Province.create(78L,"Karabük"));
        provinceRepository.save(Province.create(79L,"Kilis"));
        provinceRepository.save(Province.create(80L,"Osmaniye"));
        provinceRepository.save(Province.create(81L,"Düzce"));
        //TODO bura dolduralım
    }
}
