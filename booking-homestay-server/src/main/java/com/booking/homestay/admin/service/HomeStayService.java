package com.booking.homestay.admin.service;

import com.booking.homestay.admin.dto.HomeStayRequest;
import com.booking.homestay.admin.dto.HomeStayResponse;
import com.booking.homestay.exception.SpringException;
import com.booking.homestay.admin.mapper.HomeStayMapper;
import com.booking.homestay.model.*;
import com.booking.homestay.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional
public class HomeStayService {

    private final IHomeStayRepository iHomeStayRepository;
    private final IVillageRepository iVillageRepository;
    private final IDistrictRepository iDistrictRepository;
    private final ICityRepository iCityRepository;
    private final IUserRepository iUserRepository;
    private final HomeStayMapper homeStayMapper;

    public Long save(HomeStayRequest homeStayRequest) {
        City city = iCityRepository.findByCityName(homeStayRequest.getCityName()).orElseThrow(() -> new SpringException("Không tồn tại thành phố - " + homeStayRequest.getCityName()));
        List<District> district = iDistrictRepository.findByDistrictNameAndCity_Id(homeStayRequest.getDistrictName(), city.getId());
        if(district.size()==0){
            throw new SpringException("Quận không tồn tại");
        }
        List<Village> villages = iVillageRepository.findByVillageNameAndDistrict(homeStayRequest.getVillageName(), district.get(0));
        if(villages.size()==0){
            throw new SpringException("Phường không tồn tại");
        }
        Optional<HomeStay> homeStayName = iHomeStayRepository.findByHomeStayName(homeStayRequest.getHomeStayName());
        if (homeStayName.isPresent()) {
            throw new SpringException("Khu nhà đã tồn tại");
        } else {
          HomeStay homeStay =  iHomeStayRepository.save(homeStayMapper.map(homeStayRequest,villages.get(0)));
            return homeStay.getId();
        }
    }


    @Transactional(readOnly = true)
    public List<HomeStayResponse> getAllHomeStay() {
        return iHomeStayRepository.findByHomeStayNotLock()
                .stream()
                .map(homeStayMapper::mapToDto)
                .collect(toList());
    }

    public void deleteHomeStay(Long id) {
        HomeStay homeStay = iHomeStayRepository.findById(id).orElseThrow(() -> new SpringException("Không tồn tại home stay ID - " + id));
        List<User> userByHomeStay = iUserRepository.findByHomeStay(homeStay);
        for (User user : userByHomeStay) {
            user.setHomeStay(null);
            iUserRepository.save(user);
        }
        homeStay.setStatus(false);
        iHomeStayRepository.save(homeStay);
    }


    public void editHomeStay(HomeStayRequest homeStayRequest) {
        City city = iCityRepository.findByCityName(homeStayRequest.getCityName()).orElseThrow(() -> new SpringException("Không tồn tại thành phố - " + homeStayRequest.getCityName()));
        List<District> district = iDistrictRepository.findByDistrictNameAndCity_Id(homeStayRequest.getDistrictName(), city.getId());
        if(district.size()==0){
            throw new SpringException("Quận không tồn tại");
        }
        List<Village> villages = iVillageRepository.findByVillageNameAndDistrict(homeStayRequest.getVillageName(), district.get(0));
        if(villages.size()==0){
            throw new SpringException("Phường không tồn tại");
        }
        HomeStay homeStay = iHomeStayRepository.findById(homeStayRequest.getId()).orElseThrow(() -> new SpringException("Không tồn tại khu nhà ID - " + homeStayRequest.getId()));
        Optional<HomeStay> homeStayName = iHomeStayRepository.findByHomeStayName(homeStayRequest.getHomeStayName());
        if (!homeStayName.isPresent()) {
            iHomeStayRepository.save(homeStayMapper.mapEditToDtoById(homeStayRequest, homeStay, villages.get(0)));
        } else if(homeStayName.isPresent() && homeStayName.get().getHomeStayName().equals(homeStayRequest.getHomeStayName()) && homeStayName.get().getId().equals(homeStayRequest.getId())){
            iHomeStayRepository.save(homeStayMapper.mapEditToDtoById(homeStayRequest, homeStay, villages.get(0)));
        } else {
            throw new SpringException("Khu nhà đã tồn tại");
        }
    }

    @Transactional(readOnly = true)
    public HomeStayResponse getHomeStayById(Long id) {
        HomeStay homeStay = iHomeStayRepository.findById(id).orElseThrow(() -> new SpringException("Không tồn tại khu nhà ID - " + id));
        return homeStayMapper.mapToDto(homeStay);
    }

    @Transactional(readOnly = true)
    public List<HomeStayResponse> getHomeStayLock() {
        return iHomeStayRepository.findByHomeStayLock()
                .stream()
                .map(homeStayMapper::mapToDto)
                .collect(toList());
    }

    public void HomStayUnlock(Long id) {
        HomeStay homeStay = iHomeStayRepository.findById(id).orElseThrow(() -> new SpringException("Không tồn tại khu nhà ID - " + id));
        homeStay.setStatus(true);
        iHomeStayRepository.save(homeStay);
    }
}
