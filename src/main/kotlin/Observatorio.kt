object Observatorio{

    var listaDePaises = mutableListOf<Pais>()

     fun paisConNombre(nombrePais: String): Pais {
        return if (this.listaDePaises.isEmpty() || !listaDePaises.any{it.nombre == nombrePais}) {
            error("El pais no existe")
        } else {
            listaDePaises.find{ it.nombre == nombrePais}!!
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
        return codigosISO.subList(0, 5)
    }

    fun continenteConMasPaisesPlurinacionales(): String {

        val listaContinentes = mutableMapOf<String, Int>()

        listaDePaises.forEach{if(!listaContinentes.containsKey(it.continente)) {listaContinentes[it.continente] = 0 } }

        listaDePaises.forEach {if(it.esPlurinacional() ) {listaContinentes[it.continente]!!.plus(1) } }

        return listaContinentes.maxByOrNull {it.value}!!.key
    }

    fun promedioDePoblacionDePaisesInsulares(): Int {
        val paisesInsulares = listaDePaises.filter{it.esIsla()}
        return (paisesInsulares.sumBy { it.poblacion }.div(paisesInsulares.size)).toInt()
    }

}
