object Observatorio{

    var listaDePaises = mutableListOf<Pais>()

    //Busca en la colleccion de paises a un pais por su nombre
    fun paisConNombre(nombrePais: String): Pais {
        return if (this.listaDePaises.isEmpty() || !listaDePaises.any{it.nombre == nombrePais}) {
            error("El pais no existe")
        } else {
            listaDePaises.find{ it.nombre == nombrePais}!!
        }
    }

    //Revisa si dos paises son limitrofes el uno con el otro
    fun sonLimitrofes(primerPais:String, segundoPais: String): Boolean {
        return this.paisConNombre(primerPais).esLimitrofeDe(this.paisConNombre(segundoPais))
    }

    //Revisa si dos paises cualquiera comparten al menos un lenguaje oficial
    fun necesitanTraduccion(primerPais:String, segundoPais: String): Boolean {
        return this.paisConNombre(primerPais).necesitaTraduccion(this.paisConNombre(segundoPais))
    }

    //Revisa si dos paises son aliados potenciales
    fun sonPotencialesAliados(primerPais:String, segundoPais: String): Boolean {
        return this.paisConNombre(primerPais).aliadoPotencial(this.paisConNombre(segundoPais))
    }

    //Revisa si la moneda del primer pais es "más fuerte" que la del segundo pais
    fun convieneIrDeCompras(primerPais:String, segundoPais: String): Boolean {
        return this.paisConNombre(primerPais).convieneIrDeCompras(this.paisConNombre(segundoPais))
    }

    //Calcula el valor equivalente de una cantidad de dinero del primer pais a la moneda del segundo pais
    fun cuantoEquivaleLaMonedaEntre(primerPais:String, segundoPais: String, montoDelPrimerPais: Double): Double {
        return this.paisConNombre(primerPais).aCuantoEquivale(this.paisConNombre(segundoPais), montoDelPrimerPais)
    }

    //Retorna una colleccion tipo Lista con los codigos ISO de los 5 paises mas poblados
    fun codigosISODeLos5MasPoblados(): List<String> {
        return if(this.listaDePaises.size >= 5) {
            val paisesOrdenados = listaDePaises
            paisesOrdenados.sortByDescending { it.poblacion }
            val codigosISO = paisesOrdenados.map { it.codigoAlfa3 }
            codigosISO.subList(0, 5)
        } else {
            error("Hay menos de 5 paises registrados")
        }
    }

    //Retorna el continente con más paises plurinacionales
    //De haber dos o más continentes con la misma cantidad de paises plurinacionales retorna el continente del primer pais plurinacional
    fun continenteConMasPaisesPlurinacionales(): String {

        return if(this.listaDePaises.any { it.esPlurinacional() }) {
            val listaContinentes = mutableMapOf<String, Int>()

            listaDePaises.forEach{if(!listaContinentes.containsKey(it.continente)) {listaContinentes[it.continente] = 0 } }

            listaDePaises.forEach {if(it.esPlurinacional() ) {listaContinentes[it.continente]!!.plus(1) } }

            listaContinentes.maxByOrNull {it.value}!!.key
        } else {
            "Ninguno"
        }

    }

    //Retorna el valor promedio de la poblacion de los paises insulares
    fun promedioDePoblacionDePaisesInsulares(): Int {
        val paisesInsulares = listaDePaises.filter{it.esIsla()}
        return (paisesInsulares.sumBy { it.poblacion }.div(paisesInsulares.size)).toInt()
    }

}
