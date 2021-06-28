package com.booking.homestay.admin.service;

import com.booking.homestay.admin.dto.TypeUtilityRequest;
import com.booking.homestay.admin.dto.TypeUtilityResponse;
import com.booking.homestay.admin.mapper.TypeUtilityMapper;
import com.booking.homestay.exception.SpringException;
import com.booking.homestay.model.*;
import com.booking.homestay.repository.ITypeUtilityRepository;
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
public class TypeUtilityService {

    private final ITypeUtilityRepository iTypeUtilityRepository;
    private final IUtilityRepositoty iUtilityRepositoty;
    private final TypeUtilityMapper typeUtilityMapper;

    public void save(TypeUtilityRequest typeUtilityRequest) {
        Optional<TypeUtility> typeUtility = iTypeUtilityRepository.findByTypeName(typeUtilityRequest.getTypeName());
        if (typeUtility.isPresent()) {
            throw new SpringException("Loại tiện ích đã tồn tại");
        } else {
            iTypeUtilityRepository.save(typeUtilityMapper.map(typeUtilityRequest));
        }
    }

    @Transactional(readOnly = true)
    public List<TypeUtilityResponse> getAllTypeUtility() {
        return iTypeUtilityRepository.findAll()
                .stream()
                .map(typeUtilityMapper::mapToDto)
                .collect(toList());
    }

    public void deleteTypeUtility(Long id) {
        List<Utility> utility = iUtilityRepositoty.findByTypeUtility_Id(id);
        if (utility.size()!=0) {
            throw new SpringException("Loại tiện tích đã tồn tại tiện ích");
        } else {
            iTypeUtilityRepository.deleteById(id);
        }
    }


    public void editTypeUtility(TypeUtilityRequest typeUtilityRequest) {
        Optional<TypeUtility> typeUtilityName = iTypeUtilityRepository.findByTypeName(typeUtilityRequest.getTypeName());
        TypeUtility typeUtility = iTypeUtilityRepository.findById(typeUtilityRequest.getId()).orElseThrow(() -> new SpringException("Không tồn tại loại tiện ích ID - " + typeUtilityRequest.getId()));
        if (!typeUtilityName.isPresent()) {
            iTypeUtilityRepository.save(typeUtilityMapper.mapEditToDtoById(typeUtilityRequest, typeUtility));
        } else if (typeUtilityName.isPresent() && typeUtilityName.get().getTypeName().equals(typeUtilityRequest.getTypeName()) && typeUtilityName.get().getId().equals(typeUtilityRequest.getId())) {
            iTypeUtilityRepository.save(typeUtilityMapper.mapEditToDtoById(typeUtilityRequest, typeUtility));
        } else {
            throw new SpringException("Loại tiện ích đã tồn tại");
        }
    }

    @Transactional(readOnly = true)
    public TypeUtilityResponse getTypeUtilityById(Long id) {
        TypeUtility typeUtility = iTypeUtilityRepository.findById(id).orElseThrow(() -> new SpringException("Không tồn tại loại tiện ích ID - " + id));
        return typeUtilityMapper.mapToDto(typeUtility);
    }

}
