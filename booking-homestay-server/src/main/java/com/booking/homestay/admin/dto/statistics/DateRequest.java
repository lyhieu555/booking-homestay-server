package com.booking.homestay.admin.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateRequest {

    private Date firstDay;

    private Date lastDay;

}
