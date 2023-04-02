package com.coh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo {
    private Integer pageNumber;
    private Integer pageSize=5;
    private Integer countRow;
    private Integer countPage;


}
