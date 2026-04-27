package com.company.dto;

import com.company.enums.HomeType;
import com.company.enums.PostType;

public record PostDTO(HomeType homeType, AddressDTO address, double field, int roomCount, long price, PostType postType, String description) {
}
