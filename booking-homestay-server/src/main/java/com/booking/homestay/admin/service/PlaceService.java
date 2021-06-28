package com.booking.homestay.admin.service;

import com.booking.homestay.admin.dto.PlaceRequest;
import com.booking.homestay.admin.dto.PlaceResponse;
import com.booking.homestay.admin.mapper.PlaceMapper;
import com.booking.homestay.exception.SpringException;
import com.booking.homestay.model.*;
import com.booking.homestay.repository.IDetailPlaceRepository;
import com.booking.homestay.repository.IHomeStayRepository;
import com.booking.homestay.repository.IPlaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional
public class PlaceService {

    private final IPlaceRepository iPlaceRepository;
    private final IHomeStayRepository homeStayRepository;
    private final PlaceMapper placeMapper;
    private final IDetailPlaceRepository iDetailPlaceRepository;

    public void save(PlaceRequest placeRequest) {
        Optional<Place> place = iPlaceRepository.findByPlaceName(placeRequest.getPlaceName());
        if (place.isPresent()) {
            throw new SpringException("Địa điểm đã tồn tại");
        } else {
            iPlaceRepository.save(placeMapper.map(placeRequest));
        }
    }

    @Transactional(readOnly = true)
    public List<PlaceResponse> getAllPlace() {
        return iPlaceRepository.findAll()
                .stream()
                .map(placeMapper::mapToDto)
                .collect(toList());
    }

    public void deletePlace(Long id) {
        Optional<List<DetailPlace>> detailPlaces = iDetailPlaceRepository.findByPlace_Id(id);
        if(detailPlaces.isPresent()){
            throw new SpringException("Địa điểm đã tồn tại cơ sở");
        } else {
            iPlaceRepository.deleteById(id);
        }
    }


    public void editPlace(PlaceRequest placeRequest) {
        Optional<Place> placeName = iPlaceRepository.findByPlaceName(placeRequest.getPlaceName());
        Place place = iPlaceRepository.findById(placeRequest.getId()).orElseThrow(() -> new SpringException("Không tồn tại địa điểm ID - " + placeRequest.getId()));
        if (!placeName.isPresent()) {
            iPlaceRepository.save(placeMapper.mapEditToDtoById(placeRequest,place));
        } else if(placeName.isPresent() && placeName.get().getPlaceName().equals(placeRequest.getPlaceName()) && placeName.get().getId().equals(placeRequest.getId())){
            iPlaceRepository.save(placeMapper.mapEditToDtoById(placeRequest,place));
        } else {
            throw new SpringException("Địa điểm đã tồn tại");
        }
    }

    @Transactional(readOnly = true)
    public PlaceResponse getPlaceById(Long id) {
        Place place = iPlaceRepository.findById(id).orElseThrow(() -> new SpringException("Không tồn tại địa điểm ID - " + id));
        return placeMapper.mapToDto(place);
    }

}
