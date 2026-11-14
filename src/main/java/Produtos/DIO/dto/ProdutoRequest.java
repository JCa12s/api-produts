package Produtos.DIO.dto;

import jakarta.validation.constraints.*;

public record ProdutoRequest(
        @NotBlank(message = "name é obrigatório")
        String name,

        @NotBlank(message = "type é obrigatório")
        @Pattern(regexp = "gelado|iogurte|gelado-mucua",
                message = "type deve ser: gelado, iogurte ou gelado-mucua")
        String type,

        @NotNull(message = "price é obrigatório")
        @Positive(message = "price deve ser maior que zero")
        Double price,

        String description
) {}
