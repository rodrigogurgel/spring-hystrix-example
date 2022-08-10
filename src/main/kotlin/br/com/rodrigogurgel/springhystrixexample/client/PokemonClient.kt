package br.com.rodrigogurgel.springhystrixexample.client

import br.com.rodrigogurgel.springhystrixexample.domain.Pokemon
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient("pokemon")
interface PokemonClient {
    @GetMapping("/{id}")
    fun getPokemon(@PathVariable id: Int): Pokemon
}