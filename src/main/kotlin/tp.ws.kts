
interface PaisInter{

    var nombre: String
    var codigoAlfa3: String
    var poblacion: Int
    var superficie: Float
    var continente: String
    var codigoMonedaLocal: Int
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
        var paisesACheckear = paisesLimitrofes
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

class Pais(override var nombre: String): PaisInter{

    init {Observatorio.listaDePaises.add(this)}

    override var codigoAlfa3: String = ""
        set(value: String) {codigoAlfa3 =  value}


    override var poblacion: Int = 0
        set(value: Int) {poblacion = value}

    override var superficie: Float = 0.0F
        set(value: Float) {superficie = value}

    override var continente: String = ""
        set(value: String) {continente = value}

    override var codigoMonedaLocal: Int = 0
        set(value: Int) {codigoMonedaLocal = value}

    override var cotizacionNacionalDolar: Double = 0.0
        set(value: Double) {cotizacionNacionalDolar = value}

    override var paisesLimitrofes: MutableList<PaisInter>
        get() = paisesLimitrofes
        set(value: MutableList<PaisInter>) {paisesLimitrofes = value}

    override var bloquesRegionales: MutableList<String>
        get() = bloquesRegionales
        set(value) {bloquesRegionales = value}

    override var idiomasOficiales: MutableList<String>
        get() = idiomasOficiales
        set(value) {idiomasOficiales = value}
}
/*
abstract class Bloque{
    var paisesQueLoComponen: MutableList<PaisInter>

}*/

abstract class Pais: PaisInter{

}