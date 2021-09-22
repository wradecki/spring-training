package pl.training.shop.commons.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultPageDto<T> {

    private List<T> data;
    private int pageNumber;
    private int totalPages;

}
