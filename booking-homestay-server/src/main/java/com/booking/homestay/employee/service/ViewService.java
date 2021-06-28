package com.booking.homestay.employee.service;

import com.booking.homestay.admin.dto.UtilityRequest;
import com.booking.homestay.admin.dto.UtilityResponse;
import com.booking.homestay.admin.mapper.UtilityMapper;
import com.booking.homestay.employee.dto.ViewRequest;
import com.booking.homestay.employee.dto.ViewResponse;
import com.booking.homestay.employee.mapper.ViewMapper;
import com.booking.homestay.exception.SpringException;
import com.booking.homestay.model.DetailUtility;
import com.booking.homestay.model.DetailView;
import com.booking.homestay.model.Utility;
import com.booking.homestay.model.View;
import com.booking.homestay.repository.IDetailUtilityRepository;
import com.booking.homestay.repository.IDetailViewRepository;
import com.booking.homestay.repository.IUtilityRepositoty;
import com.booking.homestay.repository.IViewRepository;
import com.booking.homestay.shared.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional
public class ViewService {

    private final IViewRepository iViewRepository;
    private final IDetailViewRepository iDetailViewRepository;
    private final ViewMapper viewMapper;

    public void save(ViewRequest viewRequest) {
        Optional<View> view = iViewRepository.findByViewName(viewRequest.getViewName());
        if (view.isPresent()) {
            throw new SpringException("Cảnh quan đã tồn tại");
        } else {
            iViewRepository.save(viewMapper.map(viewRequest));
        }
    }

    @Transactional(readOnly = true)
    public List<ViewResponse> getAllView() {
        return iViewRepository.findAll()
                .stream()
                .map(viewMapper::mapToDto)
                .collect(toList());
    }

    public void deleteView(Long id) {
        Optional<List<DetailView>> detailViews = iDetailViewRepository.findByView_Id(id);
        if(detailViews.isPresent()){
            throw new SpringException("Cảnh quan đã tồn tại trong nhà");
        }
        iViewRepository.deleteById(id);
    }


    public void editView(ViewRequest viewRequest) {
        Optional<View> viewName = iViewRepository.findByViewName(viewRequest.getViewName());
        if (!viewName.isPresent()) {
            iViewRepository.save(viewMapper.mapEditToDtoById(viewRequest));
        } else if(viewName.isPresent() && viewName.get().getViewName().equals(viewRequest.getViewName()) && viewName.get().getId().equals(viewRequest.getId())){
            iViewRepository.save(viewMapper.mapEditToDtoById(viewRequest));
        } else {
            throw new SpringException("Cảnh quan đã tồn tại");
        }
    }

    @Transactional(readOnly = true)
    public ViewResponse getViewById(Long id) {
        View view = iViewRepository.findById(id).orElseThrow(() -> new SpringException("Không tồn tại cảnh quan I - " + id));
        return viewMapper.mapToDto(view);
    }

}
