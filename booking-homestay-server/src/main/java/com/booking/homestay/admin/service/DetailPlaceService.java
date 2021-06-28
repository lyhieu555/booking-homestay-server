package com.booking.homestay.admin.service;

import com.booking.homestay.admin.dto.DetailPlaceRequest;
import com.booking.homestay.admin.dto.DetailPlaceResponse;
import com.booking.homestay.admin.mapper.DetailPlaceMapper;
import com.booking.homestay.employee.dto.DetailViewRequest;
import com.booking.homestay.employee.dto.DetailViewResponse;
import com.booking.homestay.employee.mapper.DetailViewMapper;
import com.booking.homestay.exception.SpringException;
import com.booking.homestay.model.*;
import com.booking.homestay.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional
public class DetailPlaceService {

    private final IDetailPlaceRepository iDetailPlaceRepository;
    private final DetailPlaceMapper detailPlaceMapper;
    private final IHomeStayRepository iHomeStayRepository;
    private final IPlaceRepository iPlaceRepository;

    public void save(DetailPlaceRequest detailPlaceRequest) {
        HomeStay homeStay = iHomeStayRepository.findById(detailPlaceRequest.getId_homeStay()).orElseThrow(() -> new SpringException("Không tồn tại cở sở ID - " + detailPlaceRequest.getId_homeStay()));
        Place place = iPlaceRepository.findById(detailPlaceRequest.getId_place()).orElseThrow(() -> new SpringException("Không tồn tại địa điểm ID - " + detailPlaceRequest.getId_place()));
        List<DetailPlace> detailPlaces = iDetailPlaceRepository.findByPlaceAndHomeStay(place, homeStay);
        if (detailPlaces.size() != 0) {
            throw new SpringException("Địa điểm này đã tồn tại trong cơ sở");
        }
        iDetailPlaceRepository.save(detailPlaceMapper.mapToSave(place, homeStay));
    }

    @Transactional(readOnly = true)
    public List<DetailPlaceResponse> getAllByHomeStay(Long id) {
        return iDetailPlaceRepository.findByHomeStay_Id(id)
                .stream()
                .map(detailPlaceMapper::mapToDto)
                .collect(toList());
    }

    public void deleteDetailPlace(Long id) {
        iDetailPlaceRepository.deleteById(id);
    }

}
