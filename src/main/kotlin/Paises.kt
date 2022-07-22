import kotlin.math.roundToInt

class Pais(var nombre: String = "", var codigoAlfa3: String = "", var poblacion: Int = 0,
    var superficie: Double = 0.0, var continente: String = "", var codigoMonedaLocal: String = "",
    var cotizacionNacionalDolar: Double = 0.0, var paisesLimitrofes: MutableList<Pais> = mutableListOf(),
    var bloquesRegionales: MutableList<String> = mutableListOf(), var idiomasOficiales: MutableList<String> = mutableListOf()) {

    //Es plurinacional si tiene 2 o mas idiomas oficiales
    fun esPlurinacional(): Boolean {
        return this.idiomasOficiales.size >= 2
    }

    //Es isla si no tiene paises limitrofes
    fun esIsla(): Boolean {
        return this.paisesLimitrofes.isEmpty()
    }

    //Densidad poblacional: poblacion / superficie
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
    fun necesitaTraduccion(pais: Pais): Boolean {
        return idiomasOficiales.intersect(pais.idiomasOficiales).isEmpty()
    }

    //Revisa si un pais es un aliado potencial del actual
    fun aliadoPotencial(pais: Pais): Boolean {
        return !this.necesitaTraduccion(pais) && this.compartenBloqueRegional(pais)
    }

    //Revisa si un pais comparte bloque regional con el actual
    fun compartenBloqueRegional(pais: Pais): Boolean {
        return bloquesRegionales.intersect(pais.bloquesRegionales).isNotEmpty()
    }

    //Revisa si la moneda de un pais es mal "debil" que el actual
    fun convieneIrDeCompras(pais: Pais): Boolean {
        return pais.cotizacionNacionalDolar > this.cotizacionNacionalDolar
    }

    //Retorna el valor equivalente de la moneda del pais actual a la de un pais
    fun aCuantoEquivale(pais: Pais, monto: Double): Double {
        return ((convertirADolar(monto) * pais.cotizacionNacionalDolar) * 100.0).roundToInt() / 100.0
    }

    //Convierte un monto de la moneda del pais a valor dolar
    fun convertirADolar(monto: Double): Double {
        return monto.div(cotizacionNacionalDolar)
    }

}