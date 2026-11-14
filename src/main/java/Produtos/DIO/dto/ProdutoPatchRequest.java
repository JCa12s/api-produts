package Produtos.DIO.dto;

import jakarta.validation.constraints.*;

public record ProdutoPatchRequest(
        String name,

        @Pattern(regexp = "gelado|iogurte|gelado-mucua",
                message = "type deve ser: gelado, iogurte ou gelado-mucua")
        String type,

        @Positive(message = "price deve ser maior que zero")
        Double price,

        String description
) {}
