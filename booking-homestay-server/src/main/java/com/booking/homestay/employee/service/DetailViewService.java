package com.booking.homestay.employee.service;

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
public class DetailViewService {

    private final IDetailViewRepository iDetailViewRepository;
    private final DetailViewMapper detailViewMapper;
    private final IHouseRepository iHouseRepository;
    private final IViewRepository iViewRepository;

    public void save(DetailViewRequest detailViewRequest) {
        House house = iHouseRepository.findById(detailViewRequest.getId_house()).orElseThrow(() -> new SpringException("Không tồn tại nhà ID - " + detailViewRequest.getId_house()));
        View view = iViewRepository.findById(detailViewRequest.getId_view()).orElseThrow(() -> new SpringException("Không tồn tại cảnh quan ID - " + detailViewRequest.getId_view()));
        List<DetailView> detailViews = iDetailViewRepository.findByHouseAndView(house, view);
        if (detailViews.size() != 0) {
            throw new SpringException("Cảnh quan này đã tồn tại trong nhà");
        }
        iDetailViewRepository.save(detailViewMapper.mapToSave(view, house));
    }

    @Transactional(readOnly = true)
    public List<DetailViewResponse> getAllByHouse(Long id) {
        return iDetailViewRepository.findByHouse_Id(id)
                .stream()
                .map(detailViewMapper::mapToDto)
                .collect(toList());
    }

    public void deleteDetailView(Long id) {
        iDetailViewRepository.deleteById(id);
    }

}
