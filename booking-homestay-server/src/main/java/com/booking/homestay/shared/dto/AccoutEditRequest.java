package com.booking.homestay.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccoutEditRequest {

    private String userName;

    private String password;

}
