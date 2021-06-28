package com.booking.homestay.employee.service;


import com.booking.homestay.employee.dto.HouseRequest;
import com.booking.homestay.employee.dto.HouseResponse;
import com.booking.homestay.employee.mapper.HouseMapper;
import com.booking.homestay.exception.SpringException;
import com.booking.homestay.model.*;
import com.booking.homestay.repository.*;
import com.booking.homestay.shared.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional
public class HouseService {

    private final AuthService authService;
    private final HouseMapper houseMapper;
    private final IHouseRepository iHouseRepository;
    private final IDetailViewRepository iDetailViewRepository;
    private final IDetailUtilityRepository iDetailUtilityRepository;
    private final IBookingRepository iBookingRepository;

    public Long save(HouseRequest houseRequest) {
        List<House> houseName = iHouseRepository.findByHouseNameAndHomeStay(houseRequest.getHouseName(), authService.getCurrentUser().getHomeStay());
        if (houseName.size() == 0) {
           House house = iHouseRepository.save(houseMapper.mapToSave(houseRequest, authService.getCurrentUser()));
           return house.getId();
        } else {
            throw new SpringException("Nhà đã tồn tại trong khu nhà");
        }
    }

    @Transactional(readOnly = true)
    public List<HouseResponse> getAllHouseByUser() {
        return iHouseRepository.findByHomeStay(authService.getCurrentUser().getHomeStay())
                .stream()
                .map(houseMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<HouseResponse> getAllHouseByHomeStay(Long id) {
        return iHouseRepository.findByHomeStay_IdAndStatusTrue(id)
                .stream()
                .map(houseMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<HouseResponse> getAllHouse() {
        return iHouseRepository.findHouseByStatusTrue()
                .stream()
                .map(houseMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<HouseResponse> getHouseNoLock() {
        return iHouseRepository.findByHomeStayAndStatusTrue(authService.getCurrentUser().getHomeStay())
                .stream()
                .map(houseMapper::mapToDto)
                .collect(toList());
    }


    public void deleteHouse(Long id) {
        List<DetailView> detailViews = iDetailViewRepository.findByHouse_Id(id);
        List<DetailUtility> detailUtility = iDetailUtilityRepository.findByHouse_Id(id);
        List<Booking> bookings = iBookingRepository.findByHouse_Id(id);
        if (detailViews.size() != 0) {
            iDetailViewRepository.deleteByHouse_Id(id);
        }
        if (detailUtility.size() != 0) {
            iDetailUtilityRepository.deleteByHouse_Id(id);
        }
        if (bookings.size() != 0) {
            throw new SpringException("Nhà đã tồn tại đơn đặt");
        }
        iHouseRepository.deleteById(id);

    }

    public void editHouse(HouseRequest houseRequest) {
        House house = iHouseRepository.findById(houseRequest.getId()).orElseThrow(() -> new SpringException("Không tồn tại nhà ID - " + houseRequest.getId()));
        List<House> houseName = iHouseRepository.findByHouseNameAndHomeStay(houseRequest.getHouseName(), house.getHomeStay());
        if (houseName.size() == 0) {
            iHouseRepository.save(houseMapper.mapToEdit(houseRequest, house));
        } else if (houseName.size() != 0 && houseName.get(0).getHouseName().equals(houseRequest.getHouseName()) && houseName.get(0).getId().equals(houseRequest.getId())) {
            iHouseRepository.save(houseMapper.mapToEdit(houseRequest, house));
        } else {
            throw new SpringException("Nhà đã tồn tại trong khu nhà");
        }
    }

    @Transactional(readOnly = true)
    public HouseResponse getHouseById(Long id) {
        House house = iHouseRepository.findById(id).orElseThrow(() -> new SpringException("Không tồn tại nhà ID - " + id));
        return houseMapper.mapToDto(house);
    }


    public void lockHouse(Long id) {
        House house = iHouseRepository.findById(id).orElseThrow(() -> new SpringException("Không tồn tại nhà ID - " + id));
        house.setStatus(false);
    }

    public void unlockHouse(Long id) {
        House house = iHouseRepository.findById(id).orElseThrow(() -> new SpringException("Không tồn tại nhà ID - " + id));
        house.setStatus(true);
    }
}
