package com.booking.homestay.shared.service;

import com.booking.homestay.exception.SpringException;
import com.booking.homestay.model.RefreshToken;
import com.booking.homestay.model.User;
import com.booking.homestay.repository.IRefreshTokenRepository;
import com.booking.homestay.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {

    private final IRefreshTokenRepository IRefreshTokenRepository;
    private final IUserRepository IUserRepository;

    public RefreshToken generateRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        IRefreshTokenRepository.deleteByUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreateDate(Instant.now());
        refreshToken.setUser(user);
        return IRefreshTokenRepository.save(refreshToken);
    }

    void validateRefreshToken(String token, String username) {
        RefreshToken refreshToken  = IRefreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new SpringException("Mã Token không hợp lệ"));
        User user  = IUserRepository.findByUserName(username)
                .orElseThrow(() -> new SpringException("Không tìm thấy tài khoản này"));
        if(!user.getId().equals(refreshToken.getUser().getId())){
            throw new SpringException("Người dùng không khớp với mã Token");
        }
    }


    public void deleteRefreshToken(String token) {
        IRefreshTokenRepository.deleteByToken(token);
    }

}
