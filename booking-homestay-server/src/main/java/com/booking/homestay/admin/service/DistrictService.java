package com.booking.homestay.admin.service;

import com.booking.homestay.admin.dto.DistrictRequest;
import com.booking.homestay.admin.dto.DistrictResponse;
import com.booking.homestay.admin.mapper.DistrictMapper;
import com.booking.homestay.exception.SpringException;
import com.booking.homestay.model.*;
import com.booking.homestay.repository.ICityRepository;
import com.booking.homestay.repository.IDistrictRepository;
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
public class DistrictService {

    private final IDistrictRepository iDistrictRepository;
    private final DistrictMapper districtMapper;
    private final IVillageRepository villageRepository;
    private final ICityRepository iCityRepository;

    public void save(DistrictRequest districtRequest) {
        City city = iCityRepository.findByCityName(districtRequest.getCityName()).orElseThrow(() -> new SpringException("Không tìm thấy thành phố có tên - " + districtRequest.getCityName()));
        List<District> districtName = iDistrictRepository.findByDistrictNameAndCity_Id(districtRequest.getDistrictName(), city.getId());
        if (districtName.size()!=0) {
            throw new SpringException("Quận đã tồn tại");
        } else {
            iDistrictRepository.save(districtMapper.map(districtRequest, city));
        }
    }

    @Transactional(readOnly = true)
    public List<DistrictResponse> getAllDistrict() {
        return iDistrictRepository.findAll()
                .stream()
                .map(districtMapper::mapToDto)
                .collect(toList());
    }

    public void deleteDistrict(Long id) {
        Optional<List<Village>> village = villageRepository.findByDistrict_Id(id);
        if (village.isPresent()) {
            throw new SpringException("Trong quận đã có phường");
        } else {
            iDistrictRepository.deleteById(id);
        }
    }


    public void editDistrict(DistrictRequest districtRequest) {
        City city = iCityRepository.findByCityName(districtRequest.getCityName()).orElseThrow(() -> new SpringException("Không tìm thấy thành phố có tên - " + districtRequest.getCityName()));
        List<District> districtName = iDistrictRepository.findByDistrictNameAndCity_Id(districtRequest.getDistrictName(), city.getId());
        if (districtName.size()==0) {
            iDistrictRepository.save(districtMapper.mapEditToDtoById(districtRequest, city));
        } else if(districtName.size()!=0 && districtName.get(0).getDistrictName().equals(districtRequest.getDistrictName())&& districtName.get(0).getId().equals(districtRequest.getId())){
            iDistrictRepository.save(districtMapper.mapEditToDtoById(districtRequest, city));
        } else {
            throw new SpringException("Quận đã tồn tại");
        }
    }

    @Transactional(readOnly = true)
    public DistrictResponse getDistrictById(Long id) {
        District district = iDistrictRepository.findById(id).orElseThrow(() -> new SpringException("Không có quận nào có ID - " + id));
        return districtMapper.mapToDto(district);
    }

    @Transactional(readOnly = true)
    public List<DistrictResponse> getAllDistrictbyCity(String cityName) {
        return iDistrictRepository.findByCity_CityName(cityName)
                .stream()
                .map(districtMapper::mapToDto)
                .collect(toList());
    }
}
