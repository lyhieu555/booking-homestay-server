package com.booking.homestay.shared.service;


import com.booking.homestay.exception.SpringException;
import com.booking.homestay.shared.dto.ProfileRequest;
import com.booking.homestay.shared.dto.ProfileResponse;
import com.booking.homestay.shared.mapper.ProfileMapper;
import com.booking.homestay.model.User;
import com.booking.homestay.repository.IHomeStayRepository;
import com.booking.homestay.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@AllArgsConstructor
@Transactional
public class ProfileService {

    private final PasswordEncoder passwordEncoder;
    private final IUserRepository IUserRepository;
    private final AuthService authService;
    private final ProfileMapper profileMapper;


    @Transactional(readOnly = true)
    public String checkUser() {
        User user = authService.getCurrentUser();
        if (user.getRole().equals("Employee") && user.getHomeStay() == null) {
            return "EmployeeNot";
        } else {
            if (user.getAddress() != null && user.getDateOfBirth() != null
                    && user.getFirstName() != null && user.getLastName() != null
                    && user.getSex() != null ) {
                return "null";
            } else if (user.getRole().equals("Employee")) {
                return "Employee";
            } else if (user.getRole().equals("Admin")) {
                return "Admin";
            }
            if (user.getRole().equals("Member")) {
                return "Member";
            }
        }
        return "null";
    }

    @Transactional(readOnly = true)
    public ProfileResponse getProfile() {
        User user = authService.getCurrentUser();
        return profileMapper.mapToDtoByUserName(user);
    }

    @Transactional(readOnly = true)
    public ProfileResponse getProfileId(Long id) {
        User user = IUserRepository.findById(id).orElseThrow(() -> new SpringException("Không có tài khoản nào có ID: " + id));
        return profileMapper.mapToDtoByUserName(user);
    }

    public void update(ProfileRequest profileRequest) {
        User user = authService.getCurrentUser();
        IUserRepository.save(profileMapper.mapUpdateToDtoById(profileRequest, user));
    }

    public void edit(ProfileRequest profileRequest) {
        Optional<User> email = IUserRepository.findByEmail(profileRequest.getEmail());
        Optional<User> phone = IUserRepository.findByPhone(profileRequest.getPhone());
        if (!email.isPresent() && !phone.isPresent()) {
            IUserRepository.save(profileMapper.mapEditToDtoById(profileRequest, authService.getCurrentUser()));
        } else if (email.isPresent() && !phone.isPresent()) {
            if (email.get().getEmail().equals(profileRequest.getEmail()) && email.get().getId().equals(profileRequest.getId())) {
                IUserRepository.save(profileMapper.mapEditToDtoById(profileRequest, authService.getCurrentUser()));
            } else {
                throw new SpringException("Email đã tồn tại");
            }
        } else if (!email.isPresent() && phone.isPresent()) {
            if (phone.get().getPhone().equals(profileRequest.getPhone()) && phone.get().getId().equals(profileRequest.getId())) {
                IUserRepository.save(profileMapper.mapEditToDtoById(profileRequest, authService.getCurrentUser()));
            } else {
                throw new SpringException("Số điện thoại đã tồn tại");
            }
        } else if (email.isPresent() && phone.isPresent()) {
            if (email.get().getEmail().equals(profileRequest.getEmail()) && phone.get().getPhone().equals(profileRequest.getPhone()) && email.get().getId().equals(profileRequest.getId()) && phone.get().getId().equals(profileRequest.getId())) {
                IUserRepository.save(profileMapper.mapEditToDtoById(profileRequest, authService.getCurrentUser()));
            } else if (!email.get().getEmail().equals(profileRequest.getEmail()) && phone.get().getPhone().equals(profileRequest.getPhone()) && phone.get().getId().equals(profileRequest.getId())) {
                throw new SpringException("Email đã tồn tại");
            } else if (email.get().getEmail().equals(profileRequest.getEmail()) && !phone.get().getPhone().equals(profileRequest.getPhone()) && email.get().getId().equals(profileRequest.getId())) {
                throw new SpringException("Số điện thoại đã tồn tại");
            } else {
                throw new SpringException("Email và số điện thoại đã tồn tại");
            }
        } else {
            IUserRepository.save(profileMapper.mapEditToDtoById(profileRequest, authService.getCurrentUser()));
        }
    }

    public void editPassword(ProfileRequest profileRequest) {
        if (passwordEncoder.matches(profileRequest.getPassword(), authService.getCurrentUser().getPassword())) {
            User user = authService.getCurrentUser();
            user.setPassword(passwordEncoder.encode(profileRequest.getPasswordEdit()));
            IUserRepository.save(user);
        } else {
            throw new SpringException("Mật khẩu cũ không khớp");
        }

    }
}
