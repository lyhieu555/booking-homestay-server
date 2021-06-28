package com.booking.homestay.employee.service;

import com.booking.homestay.employee.dto.DetailUtilityRequest;
import com.booking.homestay.employee.dto.DetailUtilityResponse;
import com.booking.homestay.employee.dto.PostRequest;
import com.booking.homestay.employee.mapper.DetailUtilityMapper;
import com.booking.homestay.exception.SpringException;
import com.booking.homestay.model.DetailUtility;
import com.booking.homestay.model.House;
import com.booking.homestay.model.Post;
import com.booking.homestay.model.Utility;
import com.booking.homestay.repository.IDetailUtilityRepository;
import com.booking.homestay.repository.IHouseRepository;
import com.booking.homestay.repository.IUtilityRepositoty;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional
public class DetailUtilityService {

    private final IDetailUtilityRepository iDetailUtilityRepository;
    private final DetailUtilityMapper detailUtilityMapper;
    private final IHouseRepository iHouseRepository;
    private final IUtilityRepositoty iUtilityRepositoty;

    public void save(DetailUtilityRequest detailUtilityRequest) {
        House house = iHouseRepository.findById(detailUtilityRequest.getId_house()).orElseThrow(() -> new SpringException("Không tồn tại nhà ID - " + detailUtilityRequest.getId_house()));
        Utility utility = iUtilityRepositoty.findById(detailUtilityRequest.getId_utility()).orElseThrow(() -> new SpringException("Không tồn tại tiện ích ID - " + detailUtilityRequest.getId_utility()));
        List<DetailUtility> detailUtility = iDetailUtilityRepository.findByUtilityAndHouse(utility, house);
        if (detailUtility.size() != 0) {
            throw new SpringException("Tiện ích đã có trong nhà");
        }
        iDetailUtilityRepository.save(detailUtilityMapper.mapToSave(house, utility));
    }

    @Transactional(readOnly = true)
    public List<DetailUtilityResponse> getAllByHouse(Long id) {
        return iDetailUtilityRepository.findByHouse_Id(id)
                .stream()
                .map(detailUtilityMapper::mapToDto)
                .collect(toList());
    }

    public void deleteDetailUtility(Long id) {
        iDetailUtilityRepository.deleteById(id);
    }


    public void editDetailUtility(DetailUtilityRequest detailUtilityRequest) {
        DetailUtility detailUtility = iDetailUtilityRepository.findById(detailUtilityRequest.getId()).orElseThrow(() -> new SpringException("Không tồn tại loại tiện ích ID - " + detailUtilityRequest.getId()));
        iDetailUtilityRepository.save(detailUtilityMapper.mapToEdit(detailUtility, detailUtilityRequest));
    }

    public DetailUtilityResponse getById(Long id) {
        DetailUtility detailUtility = iDetailUtilityRepository.findById(id).orElseThrow(() -> new SpringException("Không tồn tại loại tiện ích ID- " + id));
        return detailUtilityMapper.mapToDto(detailUtility);
    }
}
