package com.booking.homestay.admin.service;

import com.booking.homestay.admin.dto.CityRequest;
import com.booking.homestay.admin.dto.CityResponse;
import com.booking.homestay.admin.mapper.CityMapper;
import com.booking.homestay.exception.SpringException;
import com.booking.homestay.model.*;
import com.booking.homestay.repository.ICityRepository;
import com.booking.homestay.repository.IDistrictRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional
public class CityService {

    private final ICityRepository iCityRepository;
    private final IDistrictRepository iDistrictRepository;
    private final CityMapper cityMapper;

    public void save(CityRequest cityRequest) {
        Optional<City> city = iCityRepository.findByCityName(cityRequest.getCityName());
        if (city.isPresent()) {
            throw new SpringException("Thành phố đã tồn tại");
        } else {
            iCityRepository.save(cityMapper.map(cityRequest));
        }
    }

    @Transactional(readOnly = true)
    public List<CityResponse> getAllCity() {
        return iCityRepository.findAll()
                .stream()
                .map(cityMapper::mapToDto)
                .collect(toList());
    }

    public void deleteCity(Long id) {
        List<District> district = iDistrictRepository.findByCity_Id(id);
        if (district.size() != 0) {
            throw new SpringException("Có quận tồn tại trong thành phố");
        } else {
            iCityRepository.deleteById(id);
        }
    }


    public void editCity(CityRequest cityRequest) {
        Optional<City> city = iCityRepository.findByCityName(cityRequest.getCityName());
        if (!city.isPresent()) {
            iCityRepository.save(cityMapper.mapEditToDtoById(cityRequest));
        } else if (city.isPresent() && city.get().getCityName().equals(cityRequest.getCityName()) && city.get().getId().equals(cityRequest.getId())) {
            iCityRepository.save(cityMapper.mapEditToDtoById(cityRequest));
        } else {
            throw new SpringException("Thành phố đã tồn tại");
        }
    }

    @Transactional(readOnly = true)
    public CityResponse getCityById(Long id) {
        City city = iCityRepository.findById(id).orElseThrow(() -> new SpringException("Không cớ thành phố có ID - " + id));
        return cityMapper.mapToDto(city);
    }

}
