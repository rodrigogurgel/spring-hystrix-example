package br.com.rodrigogurgel.springhystrixexample.controller

import br.com.rodrigogurgel.springhystrixexample.dto.PokemonDTO
import br.com.rodrigogurgel.springhystrixexample.service.PokemonService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class PokemonController(
    private val pokemonService: PokemonService
) {
    @GetMapping("/{id}")
    fun getPokemon(
        @PathVariable("id") id: Int
    ): PokemonDTO {
        return pokemonService.getPokemon(id).let { PokemonDTO(it.id, it.name) }
    }
}