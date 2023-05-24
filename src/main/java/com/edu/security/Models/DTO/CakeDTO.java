package com.edu.security.Models.DTO;

import com.edu.security.Models.Entities.CakeIngredient;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class CakeDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @NotBlank
    private String name;

    @NotNull
    private Integer cost;

    private Set<CakeIngredient> types;

}
