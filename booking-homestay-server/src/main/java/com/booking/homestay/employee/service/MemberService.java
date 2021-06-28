package com.booking.homestay.employee.service;


import com.booking.homestay.employee.dto.MemberRequest;
import com.booking.homestay.employee.dto.MemberResponse;
import com.booking.homestay.exception.SpringException;
import com.booking.homestay.employee.mapper.MemberMapper;
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
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final IUserRepository IUserRepository;
    private final MemberMapper memberMapper;
    private final MailService mailService;
    private final AuthService authService;

    public void save(MemberRequest memberRequest) {
        String password = "homestay123";
        Optional<User> username = IUserRepository.findByUserName(memberRequest.getUserName());
        Optional<User> email = IUserRepository.findByEmail(memberRequest.getEmail());
        Optional<User> phone = IUserRepository.findByPhone(memberRequest.getPhone());
        if (username.isPresent() || email.isPresent() || phone.isPresent()) {
            throw new SpringException("Tài khoản, email hoặc số điện thoại của bạn đã tồn tại");
        } else {
            User user = new User();
            user.setUserName(memberRequest.getUserName());
            user.setEmail(memberRequest.getEmail());
            user.setPhone(memberRequest.getPhone());
            user.setPassword(passwordEncoder.encode(password));
            user.setCreatedDate(Instant.now());
            user.setEnabled(true);
            user.setStatus(true);
            User userId = authService.getCurrentUser();
            user.setCreator(userId);
            user.setRole("Member");
            IUserRepository.save(user);
            mailService.sendMail(new NotificationEmail("Thông tin tài khoản thành viên",
                    user.getEmail(), "Bạn đã trở thành thành viên của chúng tôi " +
                    "Vui lòng nhấp vào liên kết này để trở lại trang chủ : " +
                    "http://localhost:4200/" + "      Tài khoản của bạn là ||  Username: "
                    + user.getUserName() + " Password: " + password + "  .Vui lòng thay đổi mật khẩu của bạn khi đăng nhập thành công"));
        }
    }


    @Transactional(readOnly = true)
    public List<MemberResponse> getAllMember() {
        return IUserRepository.findByMemberNotLock()
                .stream()
                .map(memberMapper::mapToDto)
                .collect(toList());
    }


    public void deleteMember(Long id) {
        User user = IUserRepository.findById(id).orElseThrow(() -> new SpringException("Không tồn tại tài khoản có ID - " + id));
        user.setStatus(false);
        IUserRepository.save(user);
    }

    @Transactional(readOnly = true)
    public MemberResponse getMemberById(Long id) {
        User user = IUserRepository.findById(id).orElseThrow(() -> new SpringException("Không tồn tại tài khoản có ID - " + id));
        return memberMapper.mapToDto(user);
    }

    public void editMember(MemberRequest memberRequest) {
        User user = IUserRepository.findById(memberRequest.getId()).orElseThrow(() -> new SpringException("Không tồn tại tài khoản có ID - " + memberRequest.getId()));
        Optional<User> email = IUserRepository.findByEmail(memberRequest.getEmail());
        Optional<User> phone = IUserRepository.findByPhone(memberRequest.getPhone());
        if (!email.isPresent() && !phone.isPresent()) {
            IUserRepository.save(memberMapper.mapEditToDtoById(memberRequest, user));
        } else if (email.isPresent() && !phone.isPresent()) {
            if (email.get().getEmail().equals(user.getEmail()) && email.get().getId().equals(user.getId())) {
                IUserRepository.save(memberMapper.mapEditToDtoById(memberRequest, user));
            } else {
                throw new SpringException("Email đã tồn tại");
            }
        } else if (!email.isPresent() && phone.isPresent()) {
            if (phone.get().getPhone().equals(user.getPhone()) && phone.get().getId().equals(user.getId())) {
                IUserRepository.save(memberMapper.mapEditToDtoById(memberRequest, user));
            } else {
                throw new SpringException("Số điện thoại đã tồn tại");
            }
        } else if (email.isPresent() && phone.isPresent()) {
            if (email.get().getEmail().equals(user.getEmail()) && phone.get().getPhone().equals(user.getPhone()) && email.get().getId().equals(user.getId()) && phone.get().getId().equals(user.getId())) {
                IUserRepository.save(memberMapper.mapEditToDtoById(memberRequest, user));
            } else if (!email.get().getEmail().equals(user.getEmail()) && phone.get().getPhone().equals(user.getPhone()) && phone.get().getId().equals(user.getId())) {
                throw new SpringException("Email đã tồn tại");
            } else if (email.get().getEmail().equals(user.getEmail()) && !phone.get().getPhone().equals(user.getPhone()) && email.get().getId().equals(user.getId())) {
                throw new SpringException("Số điện thoại đã tồn tại");
            } else {
                throw new SpringException("Email và số điện thoại đã tồn tại");
            }
        } else {
            IUserRepository.save(memberMapper.mapEditToDtoById(memberRequest, user));
        }
    }

    @Transactional(readOnly = true)
    public List<MemberResponse> getMemberLock() {
        return IUserRepository.findByMemberLock()
                .stream()
                .map(memberMapper::mapToDto)
                .collect(toList());
    }

    public void MemberUnlock(Long id) {
        User user = IUserRepository.findById(id).orElseThrow(() -> new SpringException("Không tồn tại tài khoản có ID - " + id));
        user.setStatus(true);
        IUserRepository.save(user);
    }


}
