package com.booking.homestay.shared.service;


import com.booking.homestay.exception.SpringException;
import com.booking.homestay.model.*;
import com.booking.homestay.repository.IUserRepository;
import com.booking.homestay.repository.IVerificationTokenAccountRepository;
import com.booking.homestay.repository.IVerificationTokenPasswordRepository;
import com.booking.homestay.security.service.JwtProvider;
import com.booking.homestay.shared.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final IUserRepository IUserRepository;
    private final IVerificationTokenAccountRepository IVerificationTokenAccountRepository;
    private final IVerificationTokenPasswordRepository IVerificationTokenPasswordRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;


    public void register(RegisterRequest registerRequest) {
        Optional<User> username = IUserRepository.findByUserName(registerRequest.getUserName());
        Optional<User> email = IUserRepository.findByEmail(registerRequest.getEmail());
        Optional<User> phone = IUserRepository.findByPhone(registerRequest.getPhone());
        if (username.isPresent() || email.isPresent() || phone.isPresent()) {
            throw new SpringException("Tài khoản, email hoặc số điện thoại bị trùng");
        }
        User user = new User();
        user.setUserName(registerRequest.getUserName());
        user.setEmail(registerRequest.getEmail());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPhone(registerRequest.getPhone());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreatedDate(Instant.now());
        user.setEnabled(false);
        user.setStatus(true);
        user.setRole("Member");
        IUserRepository.save(user);
        String token = generateVerificationTokenAccount(user);
        mailService.sendMail(new NotificationEmail("Vui lòng kích hoạt tài khoản mới của bạn",
                user.getEmail(), "Cảm ơn bạn đã đăng ký tại khoản tại website chúng tôi, " +
                "Vui lòng nhấp vào đường dẫn này để kích hoạt tài khoản bạn mới đăng ký, " +
                "Hạn sử dụng trong vòng 2 giờ : " +
                "http://localhost:4200/accountVerification/" + token));
    }

    private String generateVerificationTokenAccount(User user) {
        String token = UUID.randomUUID().toString();
        VerificationTokenAccount verificationTokenAccount = new VerificationTokenAccount();
        verificationTokenAccount.setToken(token);
        verificationTokenAccount.setUser(user);
        verificationTokenAccount.setExpiryDate(Instant.now().plusMillis(7200000));
        IVerificationTokenAccountRepository.save(verificationTokenAccount);
        return token;
    }

    public void verifyAccount(String token) {
        Optional<VerificationTokenAccount> verificationToken = IVerificationTokenAccountRepository.findByToken(token);
        fetchUserAndEnable(verificationToken.orElseThrow(() -> new SpringException("Mã token không hợp lệ")));
    }

    private void fetchUserAndEnable(VerificationTokenAccount verificationTokenAccount) {
        Instant time = Instant.now();
        if (time.isAfter(verificationTokenAccount.getExpiryDate())) {
            IVerificationTokenAccountRepository.deleteById(verificationTokenAccount.getId());
            IUserRepository.deleteById(verificationTokenAccount.getUser().getId());
            throw new SpringException("Mã token đã hết hạn.");
        }
        String username = verificationTokenAccount.getUser().getUserName();
        User user = IUserRepository.findByUserName(username).orElseThrow(() -> new SpringException("Không tìm thấy tên tài khoản là: " + username));
        user.setEnabled(true);
        IUserRepository.save(user);
        IVerificationTokenAccountRepository.deleteById(verificationTokenAccount.getId());
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        User username = IUserRepository.findByUserName(loginRequest.getUserName()).orElseThrow(() -> new SpringException("Tài khoản không tồn tại" ));
        User user = IUserRepository.findByUserNameAndStatusTrue(loginRequest.getUserName()).orElseThrow(() -> new SpringException("Tài khoản của bạn đã bị khóa, liên hệ quản trị viên để biết thêm thông tin chi tiết" ));
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        RefreshToken refreshToken = refreshTokenService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshToken.getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .userName(loginRequest.getUserName())
                .role(user.getRole())
                .image(user.getImage())
                .build();
    }

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        try{
            org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                    getContext().getAuthentication().getPrincipal();
            return IUserRepository.findByUserName(principal.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy tài khoản nào có user name là - " + principal.getUsername()));
        }catch (Exception e){
          return null;
        }
    }

    public void forgotPassword(EmailRequest emailRequest) {
        User user = IUserRepository.findByEmail(emailRequest.getEmail()).orElseThrow(() -> new SpringException("Không tìm thấy tài khoản nào có email là: " + emailRequest.getEmail()));
        String token = generateVerificationTokenPassword(user);
        mailService.sendMail(new NotificationEmail("Xác nhận yêu cầu lấy lại mật khẩu",
                user.getEmail(), "Vui lòng nhấp vào đường dẫn bên dưới để làm mới mật khẩu của bạn, " +
                "Hạn sủ dụng 2 giờ: " +
                "http://localhost:4200/passwordVerification/" + token));
    }

    private String generateVerificationTokenPassword(User user) {
        String token = UUID.randomUUID().toString();
        VerificationTokenPassword verificationTokenPassword = new VerificationTokenPassword();
        verificationTokenPassword.setToken(token);
        verificationTokenPassword.setUser(user);
        verificationTokenPassword.setExpiryDate(Instant.now().plusMillis(7200000));
        IVerificationTokenPasswordRepository.save(verificationTokenPassword);
        return token;
    }

    public String verifyPassword(String token) {
        VerificationTokenPassword verificationToken = IVerificationTokenPasswordRepository.findByToken(token)
                .orElseThrow(() -> new SpringException("Mã token không hợp lệ - " + token));
        Instant time = Instant.now();
        if (time.isAfter(verificationToken.getExpiryDate())) {
            IVerificationTokenAccountRepository.deleteById(verificationToken.getId());
            throw new SpringException("Mã token đã hết hạn");
        }
        String username = verificationToken.getUser().getUserName();
        return username;
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken(), refreshTokenRequest.getUserName());
        String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUserName());
        User user = IUserRepository.findByUserName(refreshTokenRequest.getUserName()).orElseThrow(() -> new SpringException("Không tìm thấy tên tài khoản là: " + refreshTokenRequest.getUserName()));
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .userName(refreshTokenRequest.getUserName())
                .role(user.getRole())
                .build();
    }


    public void editPassword(AccoutEditRequest accoutEditRequest) {
        User username = IUserRepository.findByUserName(accoutEditRequest.getUserName()).orElseThrow(() -> new SpringException("Không tìm thấy tên tài khoản là: " + accoutEditRequest.getUserName()));
        username.setPassword(passwordEncoder.encode(accoutEditRequest.getPassword()));
        IUserRepository.save(username);
        List<VerificationTokenPassword> listTokenByUser = IVerificationTokenPasswordRepository.findByUser_Id(username.getId());
        for (VerificationTokenPassword verificationTokenPassword : listTokenByUser) {
            IVerificationTokenPasswordRepository.deleteById(verificationTokenPassword.getId());
        }
    }

    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }


}
