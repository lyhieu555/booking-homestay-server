package com.booking.homestay.admin.service;

import com.booking.homestay.admin.dto.VillageRequest;
import com.booking.homestay.admin.dto.VillageResponse;
import com.booking.homestay.admin.mapper.VillageMapper;
import com.booking.homestay.exception.SpringException;
import com.booking.homestay.model.*;
import com.booking.homestay.repository.ICityRepository;
import com.booking.homestay.repository.IDistrictRepository;
import com.booking.homestay.repository.IHomeStayRepository;
import com.booking.homestay.repository.IVillageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional
public class VillageService {

    private final IVillageRepository iVillageRepository;
    private final IDistrictRepository iDistrictRepository;
    private final ICityRepository iCityRepository;
    private final IHomeStayRepository iHomeStayRepository;
    private final VillageMapper villageMapper;

    public void save(VillageRequest villageRequest) {
        City city = iCityRepository.findByCityName(villageRequest.getCityName()).orElseThrow(() -> new SpringException("Không tìm thấy thành phố có tên - " + villageRequest.getCityName()));
        List<District> district = iDistrictRepository.findByDistrictNameAndCity_Id(villageRequest.getDistrictName(), city.getId());
        if(district.size()==0){
            throw new SpringException("Quận không tồn tại trong thành phố");
        }
        List<Village> village = iVillageRepository.findByVillageNameAndDistrict(villageRequest.getVillageName(), district.get(0));
        if (village.size() != 0) {
            throw new SpringException("Phường đã tồn tại");
        } else {
            iVillageRepository.save(villageMapper.map(villageRequest, district.get(0)));
        }
    }

    @Transactional(readOnly = true)
    public List<VillageResponse> getAllVillage() {
        return iVillageRepository.findAll()
                .stream()
                .map(villageMapper::mapToDto)
                .collect(toList());
    }

    public void deleteVillage(Long id) {
        Optional<List<HomeStay>> homeStay = iHomeStayRepository.findByVillage_Id(id);
        if (homeStay.isPresent()) {
            throw new SpringException("Phường này đã tồn tại khu nhà");
        } else {
            iVillageRepository.deleteById(id);
        }
    }


    public void editVillage(VillageRequest villageRequest) {
        City city = iCityRepository.findByCityName(villageRequest.getCityName()).orElseThrow(() -> new SpringException("Không tìm thấy phường có tên - " + villageRequest.getCityName()));
        List<District> district = iDistrictRepository.findByDistrictNameAndCity_Id(villageRequest.getDistrictName(), city.getId());
        if(district.size()==0){
            throw new SpringException("Quận không tồn tại trong thành phố");
        }
        List<Village> village = iVillageRepository.findByVillageNameAndDistrict(villageRequest.getVillageName(), district.get(0));
        if (village.size() == 0) {
            iVillageRepository.save(villageMapper.mapEditToDtoById(villageRequest, district.get(0)));
        } else if (village.size() != 0 && village.get(0).getVillageName().equals(villageRequest.getVillageName()) && village.get(0).getId().equals(villageRequest.getId())) {
            iVillageRepository.save(villageMapper.mapEditToDtoById(villageRequest, district.get(0)));
        } else {
            throw new SpringException("Phường đã tồn tại");
        }
    }

    @Transactional(readOnly = true)
    public VillageResponse getVillageById(Long id) {
        Village village = iVillageRepository.findById(id).orElseThrow(() -> new SpringException("Không có phường nào ID - " + id));
        return villageMapper.mapToDto(village);
    }

}
