package pl.training.shop.commons.web;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionDto {

    private LocalDateTime timestamp = LocalDateTime.now();
    @NonNull
    private String description;

}
