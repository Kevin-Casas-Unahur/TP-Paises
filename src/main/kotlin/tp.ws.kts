
interface PaisInter{

    var nombre: String
    var codigoAlfa3: String
    var poblacion: Int
    var superficie: Double
    var continente: String
    var codigoMonedaLocal: String
    var cotizacionNacionalDolar: Double
    var paisesLimitrofes: MutableList<PaisInter>
    var bloquesRegionales: MutableList<String>
    var idiomasOficiales: MutableList<String>



    fun esPlurinacional(): Boolean {
        return this.idiomasOficiales.size >= 2
    }

    fun esIsla(): Boolean {
        return this.paisesLimitrofes.size == 0
    }

    fun densidadPoblacional(): Int {
        return (this.poblacion / this.superficie).toInt()
    }

    //El maximo en poblacion de los vecinos, pero cuenta también al pais local
    fun vecinoMasPoblado(): PaisInter {
        val paisesACheckear = paisesLimitrofes
        paisesACheckear.add(this)
        return paisesACheckear.maxByOrNull { it.poblacion }!!
    }

    fun esLimitrofeDe(pais: PaisInter): Boolean {
        return this.paisesLimitrofes.contains(pais)
    }

    fun necesitaTraduccion(pais: PaisInter): Boolean {
        return idiomasOficiales.intersect(pais.idiomasOficiales).isEmpty()
    }

    fun aliadoPotencial(pais: PaisInter): Boolean {
        return !this.necesitaTraduccion(pais) && this.compartenBloqueRegional(pais)
    }

    fun compartenBloqueRegional(pais: PaisInter): Boolean {
        return bloquesRegionales.intersect(pais.bloquesRegionales).isNotEmpty()
    }

    fun convieneIrDeCompras(pais: PaisInter): Boolean {
        return cotizacionNacionalDolar > cotizacionNacionalDolar
    }

    fun aCuantoEquivale(pais: PaisInter, monto: Double): Double {
        return this.convertirADolar(monto) * pais.cotizacionNacionalDolar
    }

    fun convertirADolar(monto: Double): Double {
        return monto.div(cotizacionNacionalDolar)
    }
}

class Pais(override var nombre: String = "", override var codigoAlfa3: String = "", override var poblacion: Int = 0,
            override var superficie: Double = 0.0, override var continente: String = "",
            override  var codigoMonedaLocal: String = "", override var cotizacionNacionalDolar: Double = 0.0): PaisInter{

    init {Observatorio.listaDePaises.add(this)}

    override var paisesLimitrofes: MutableList<PaisInter>
        get() = paisesLimitrofes
        set(listaPaises) {paisesLimitrofes = listaPaises}

    override var bloquesRegionales: MutableList<String>
        get() = bloquesRegionales
        set(bloques) {bloquesRegionales = bloques}

    override var idiomasOficiales: MutableList<String>
        get() = idiomasOficiales
        set(idiomas) {idiomasOficiales = idiomas}
}
