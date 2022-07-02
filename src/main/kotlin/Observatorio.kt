object Observatorio {

    var listaDePaises = mutableListOf<Tp_ws.PaisInter>()

    fun paisConNombre(nombrePais: String): Tp_ws.PaisInter {
        return if (!listaDePaises.any{it.nombre == nombrePais}) {
            error("No existe el pais")
        } else {
            listaDePaises.find { it.nombre == nombrePais }!!
        }
    }

    fun sonLimitrofes(primerPais:String, segundoPais: String): Boolean {
        return this.paisConNombre(primerPais).esLimitrofeDe(this.paisConNombre(segundoPais))
    }

    fun necesitanTraduccion(primerPais:String, segundoPais: String): Boolean {
        return this.paisConNombre(primerPais).necesitaTraduccion(this.paisConNombre(segundoPais))
    }

    fun sonPotencialesAliados(primerPais:String, segundoPais: String): Boolean {
        return this.paisConNombre(primerPais).aliadoPotencial(this.paisConNombre(segundoPais))
    }

    fun convieneIrDeCompras(primerPais:String, segundoPais: String): Boolean {
        return this.paisConNombre(primerPais).convieneIrDeCompras(this.paisConNombre(segundoPais))
    }

    fun cuantoEquivaleLaMonedaEntre(primerPais:String, segundoPais: String, montoDelPrimerPais: Double): Double {
        return this.paisConNombre(primerPais).aCuantoEquivale(this.paisConNombre(segundoPais), montoDelPrimerPais)
    }

    fun codigosISODeLos5MasPoblados(): List<String> {
        val paisesOrdenados = listaDePaises
        paisesOrdenados.sortByDescending { it.poblacion }
        val codigosISO = paisesOrdenados.map { it.codigoAlfa3 }
        return codigosISO.subList(0, 4)
    }
}