interface ObservatorioInter {

    var listaDePaises: MutableList<Tp_ws.PaisInter>

    fun paisConNombre(nombrePais: String): Tp_ws.PaisInter
}

object Observatorio: ObservatorioInter {

    override var listaDePaises = mutableListOf<Tp_ws.PaisInter>()

    override fun paisConNombre(nombrePais: String): Tp_ws.PaisInter {
        return if (!listaDePaises.any{it.nombre == nombrePais}) {
            error("No existe el pais")
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
        return codigosISO.subList(0, 4)
    }

    fun continenteConMasPaisesPlurinacionales(): String {
        val listaContinentes = mutableMapOf<String, Int>()

        listaDePaises.forEach{if(!listaContinentes.containsKey(it.continente)){listaContinentes.keys.add(it.continente)}}
        listaDePaises.forEach { if(it.esPlurinacional() ) { listaContinentes[it.continente]!!.plus(1)} }

        return listaContinentes.maxByOrNull {it.value}!!.key
    }

    fun promedioDePoblacionDePaisesInsulares(): Int {
        val paisesInsulares = listaDePaises.filter{it.esIsla()}
        return (paisesInsulares.sumBy { it.poblacion }.div(paisesInsulares.size)).toInt()
    }


}

open class ObservatorioDecoBase: ObservatorioInter {

    var decorador: ObservatorioInter = Observatorio

    fun decorador(unDecorador: ObservatorioInter) {this.decorador = unDecorador}

    override var listaDePaises: MutableList<Tp_ws.PaisInter> = this.decorador.listaDePaises
        get() = this.decorador.listaDePaises

    override fun paisConNombre(nombrePais: String): Tp_ws.PaisInter {
        return this.decorador.paisConNombre(nombrePais)
    }

}

object ObservatorioLimitrofes: ObservatorioDecoBase() {


}

object ObservatorioTraduccion: ObservatorioDecoBase() {


}

object ObservatorioAliados: ObservatorioDecoBase() {

}

object ObservatorioCompras: ObservatorioDecoBase() {


}

object ObservatorioMoneda: ObservatorioDecoBase() {


}

object ObservatorioPoblacion: ObservatorioDecoBase() {


}

object ObservatorioContinentes: ObservatorioDecoBase() {


}


