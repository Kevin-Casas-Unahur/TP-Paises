
interface PaisInter{

    var nombre: String
    var codigoAlfa3: String
    var poblacion: Int
    var superficie: Float
    var continente: String
    var codigoMonedaLocal: Int
    fun cotizacionNacionalDolar():Int
    var paisesLimitrofes: MutableList<PaisInter>
    var bloquesRegionales: MutableList<Bloque>
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

    // fun vecinoMasPoblado():Pais{
    // el maximo en poblacion de los vecinos, pero cuenta también al pais local
    // }

    fun esLimitrofeDe(pais: Pais): Boolean {
        return this.paisesLimitrofes.contains(pais)
    }

    fun necesitaTraduccion(pais: Pais){
    }

    fun aliadoPotencial(pais: Pais){
    }

    fun convieneIrDeCompras(pais: Pais){
    }

    fun aCuantoEquivale(pais: Pais){
    }
}

abstract class Bloque{
    var paisesQueLoComponen: MutableList<PaisInter>

}

abstract class Pais: PaisInter{

}