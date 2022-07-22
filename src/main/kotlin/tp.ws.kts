import kotlin.math.roundToInt


class Pais(){

    var nombre: String = ""
    var codigoAlfa3: String = ""
    var poblacion: Int = 0
    var superficie: Double = 0.0
    var continente: String = ""
    var codigoMonedaLocal: String = ""
    var cotizacionNacionalDolar: Double = 0.0
    var paisesLimitrofes: MutableList<PaisInter> = mutableListOf()
    var bloquesRegionales: MutableList<String> = mutableListOf()
    var idiomasOficiales: MutableList<String> = mutableListOf()

    init {
        Observatorio.listaDePaises.add(this)
    }

    fun paisesLimitrofes(pais: PaisInter) {
        paisesLimitrofes.add(pais)
        Observatorio.paisConNombre(this.nombre).paisesLimitrofes = this.paisesLimitrofes
    }

    //Es plurinacional si tiene 2 o mas idiomas oficiales
    fun esPlurinacional(): Boolean {
        return this.idiomasOficiales.size >= 2
    }

    //Es isla si no tiene paises limitrofes
    fun esIsla(): Boolean {
        return this.paisesLimitrofes.isEmpty()
    }

    fun densidadPoblacional(): Int {
        return (this.poblacion / this.superficie).toInt()
    }

    //El maximo en poblacion de los vecinos, pero cuenta también al pais local
    fun vecinoMasPoblado(): Pais {
        val paisesACheckear = paisesLimitrofes
        paisesACheckear.add(this)
        return paisesACheckear.maxByOrNull { it.poblacion }!!
    }

    //Revisa si un pais es limitrofe con el actual
    fun esLimitrofeDe(pais: Pais): Boolean {
        return this.paisesLimitrofes.contains(pais)
    }

    //Revisa si un pais comparte al menos 1 lenguaje oficial con el actual
    fun necesitaTraduccion(pais: PaisInter): Boolean {
        return idiomasOficiales.intersect(pais.idiomasOficiales).isEmpty()
    }

    //Revisa si un pais es un aliado potencial del actual
    fun aliadoPotencial(pais: PaisInter): Boolean {
        return !this.necesitaTraduccion(pais) && this.compartenBloqueRegional(pais)
    }

    //Revisa si un pais comparte bloque regional con el actual
    fun compartenBloqueRegional(pais: PaisInter): Boolean {
        return bloquesRegionales.intersect(pais.bloquesRegionales).isNotEmpty()
    }

    //Revisa si la moneda de un pais es mal "debil" que el actual
    fun convieneIrDeCompras(pais: PaisInter): Boolean {
        return pais.cotizacionNacionalDolar > this.cotizacionNacionalDolar
    }

    //Retorna el valor equivalente de la moneda del pais actual a la de un pais
    fun aCuantoEquivale(pais: PaisInter, monto: Double): Double {
        return ((convertirADolar(monto) * pais.cotizacionNacionalDolar) * 100.0).roundToInt() / 100.0
    }

    //Convierte un monto de la moneda del pais a valor dolar
    fun convertirADolar(monto: Double): Double {
        return monto.div(cotizacionNacionalDolar)
    }



}