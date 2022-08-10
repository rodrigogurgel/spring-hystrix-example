package br.com.rodrigogurgel.springhystrixexample.service

import br.com.rodrigogurgel.springhystrixexample.client.PokemonClient
import br.com.rodrigogurgel.springhystrixexample.domain.Pokemon
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class PokemonService(
    @Qualifier("pokemonClient")
    private val pokemonClient: PokemonClient
) {
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(PokemonService::class.java)
    }
    fun getPokemon(id: Int): Pokemon {
        logger.info("get pokemon id: {}", id)
        return pokemonClient.getPokemon(id)
    }
}