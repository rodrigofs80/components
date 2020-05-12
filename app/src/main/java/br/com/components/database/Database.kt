package br.com.components.database

import br.com.components.model.Botao

class Database {
    companion object {
        fun getBotoes() = listOf(
            Botao(1, "TopAppBar"),
            Botao(2, "Bottom\nAppBar"),
            Botao(3, "Banner"),
            Botao(4, "Bottom\nNavigation"),
            Botao(5, "Buttons"),
            Botao(6, "Cards"),
            Botao(7, "Teste 7"),
            Botao(8, "Teste 8")
        )
    }
}