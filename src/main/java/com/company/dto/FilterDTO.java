package com.company.dto;

import com.company.enums.HomeType;
import com.company.enums.PostType;

public record FilterDTO(HomeType homeType, String Address, double field, int roomCount, long price, PostType postType) {
}
