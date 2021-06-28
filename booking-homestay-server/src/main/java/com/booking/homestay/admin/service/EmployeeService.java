package com.booking.homestay.admin.service;

import com.booking.homestay.admin.dto.EmployeeRequest;
import com.booking.homestay.admin.dto.EmployeeResponse;
import com.booking.homestay.exception.SpringException;
import com.booking.homestay.admin.mapper.EmployeeMapper;
import com.booking.homestay.model.HomeStay;
import com.booking.homestay.model.NotificationEmail;
import com.booking.homestay.model.User;
import com.booking.homestay.repository.IHomeStayRepository;
import com.booking.homestay.repository.IUserRepository;
import com.booking.homestay.shared.service.AuthService;
import com.booking.homestay.shared.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional
public class EmployeeService {

    private final PasswordEncoder passwordEncoder;
    private final IUserRepository IUserRepository;
    private final IHomeStayRepository IHomeStayRepository;
    private final EmployeeMapper employeeMapper;
    private final MailService mailService;
    private final AuthService authService;

    public void save(EmployeeRequest employeeRequest) {
        String password = "homestay123";
        Optional<User> username = IUserRepository.findByUserName(employeeRequest.getUserName());
        Optional<User> email = IUserRepository.findByEmail(employeeRequest.getEmail());
        Optional<User> phone = IUserRepository.findByPhone(employeeRequest.getPhone());
        if (username.isPresent() || email.isPresent() || phone.isPresent()) {
            throw new SpringException("Tài khoản, email, số điện thoại đã tồn tại");
        } else {
            User user = new User();
            user.setUserName(employeeRequest.getUserName());
            user.setEmail(employeeRequest.getEmail());
            user.setPhone(employeeRequest.getPhone());
            user.setPassword(passwordEncoder.encode(password));
            user.setCreatedDate(Instant.now());
            user.setEnabled(true);
            user.setStatus(true);
            user.setRole("Employee");
            User userId = authService.getCurrentUser();
            user.setCreator(userId);
            HomeStay homeStay = IHomeStayRepository.findById(employeeRequest.getId_homeStay()).orElseThrow(()
                    -> new SpringException("Không tồn tại home stay ID - " + employeeRequest.getId_homeStay()));
            user.setHomeStay(homeStay);
            IUserRepository.save(user);
            mailService.sendMail(new NotificationEmail("Thông tin tài khoản nhân viên",
                    user.getEmail(), "Bạn đã trở thành thành viên của công ty chúng tôi, " +
                    "Vui lòng nhấp vào liên kết này để trở lại trang chủ : " +
                    "http://localhost:4200/" + "     Tài khoản của bạn là ||  Username: "
                    + user.getUserName() + " Password: " + password + "  .Vui lòng thay đổi mật khẩu của bạn khi đăng nhập thành công"));
        }
    }


    @Transactional(readOnly = true)
    public List<EmployeeResponse> getAllEmployee() {
        return IUserRepository.findByEmployeeNotLock()
                .stream()
                .map(employeeMapper::mapToDto)
                .collect(toList());
    }


    public void deleteEmployee(Long id) {
        User user = IUserRepository.findById(id).orElseThrow(() -> new SpringException("Tài khoản không tồn tại ID - " + id));
        user.setStatus(false);
        IUserRepository.save(user);
    }

    @Transactional(readOnly = true)
    public EmployeeResponse getEmployeeById(Long id) {
        User user = IUserRepository.findById(id).orElseThrow(() -> new SpringException("Tài khoản không tồn tại ID - " + id));
        return employeeMapper.mapToDto(user);
    }

    public void editEmployee(EmployeeRequest employeeRequest) {
        User user = IUserRepository.findById(employeeRequest.getId()).orElseThrow(() -> new SpringException("Tài khoản không tồn tại ID - " + employeeRequest.getId()));
        Optional<User> email = IUserRepository.findByEmail(employeeRequest.getEmail());
        Optional<User> phone = IUserRepository.findByPhone(employeeRequest.getPhone());
        if (!email.isPresent() && !phone.isPresent()) {
            IUserRepository.save(employeeMapper.mapEditToDtoById(employeeRequest, user));
        } else if (email.isPresent() && !phone.isPresent()) {
            if (email.get().getEmail().equals(employeeRequest.getEmail()) && email.get().getId().equals(employeeRequest.getId())) {
                IUserRepository.save(employeeMapper.mapEditToDtoById(employeeRequest, user));
            } else {
                throw new SpringException("Email đã tồn tại");
            }
        } else if (!email.isPresent() && phone.isPresent()) {
            if (phone.get().getPhone().equals(employeeRequest.getPhone()) && phone.get().getId().equals(employeeRequest.getId())) {
                IUserRepository.save(employeeMapper.mapEditToDtoById(employeeRequest, user));
            } else {
                throw new SpringException("Sô điện thoại đã tồn tại");
            }
        } else if (email.isPresent() && phone.isPresent()) {
            if (email.get().getEmail().equals(employeeRequest.getEmail()) && phone.get().getPhone().equals(employeeRequest.getPhone()) && email.get().getId().equals(employeeRequest.getId()) && phone.get().getId().equals(employeeRequest.getId())) {
                IUserRepository.save(employeeMapper.mapEditToDtoById(employeeRequest, user));
            } else if (!email.get().getEmail().equals(employeeRequest.getEmail()) && phone.get().getPhone().equals(employeeRequest.getPhone()) && phone.get().getId().equals(employeeRequest.getId())) {
                throw new SpringException("Email đã tồn tại");
            } else if (email.get().getEmail().equals(employeeRequest.getEmail()) && !phone.get().getPhone().equals(employeeRequest.getPhone()) && email.get().getId().equals(employeeRequest.getId())) {
                throw new SpringException("Số điện thoại đã tồn tại");
            } else {
                throw new SpringException("Email và số điện thoại đã tồn tại");
            }
        } else {
            IUserRepository.save(employeeMapper.mapEditToDtoById(employeeRequest, user));
        }
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponse> getEmployeeLock() {
        return IUserRepository.findByEmployeeLock()
                .stream()
                .map(employeeMapper::mapToDto)
                .collect(toList());
    }

    public void EmployeeUnlock(Long id) {
        User user = IUserRepository.findById(id).orElseThrow(() -> new SpringException("Tài khoản không tồn tại ID - " + id));
        user.setStatus(true);
        IUserRepository.save(user);
    }


    @Transactional(readOnly = true)
    public List<EmployeeResponse> checkEmployeeWait() {
        return IUserRepository.findByEmployeeCheck()
                .stream()
                .map(employeeMapper::mapToDto)
                .collect(toList());
    }
}
