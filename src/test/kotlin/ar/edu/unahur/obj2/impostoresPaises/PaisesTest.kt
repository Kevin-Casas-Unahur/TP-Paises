package ar.edu.unahur.obj2.impostoresPaises

import Observatorio
import Tp_ws
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe


class PaisesTest: DescribeSpec ({

    val observatorio = Observatorio

    val argentina = Tp_ws.Pais("Argentina", "ARG", 45380000, 2780000.0, "America", "SRA", 128.22)

    val brasil = Tp_ws.Pais("Brasil", "BRA", 212600000, 851600000.0, "America", "BRL", 5.40)

    val uruguay = Tp_ws.Pais("Uruguay", "URY", 3474000, 176215.0, "America", "UYU", 41.09)

    val bolivia = Tp_ws.Pais("Bolivia", "BOL", 11670000, 1099000000.0, "America", "BOB", 6.88)


    //Idiomas Paises
    argentina.idiomasOficiales.add("Español")
    brasil.idiomasOficiales.add("Portugues")
    uruguay.idiomasOficiales.add("Español")
    bolivia.idiomasOficiales.add("Español")
    bolivia.idiomasOficiales.add("Aimara")
    bolivia.idiomasOficiales.add("guaraní")

    //Paises Limitrofes
    argentina.paisesLimitrofes.add(uruguay)
    brasil.paisesLimitrofes.add(uruguay)
    uruguay.paisesLimitrofes.add(argentina)
    uruguay.paisesLimitrofes.add(brasil)
    bolivia.paisesLimitrofes.add(argentina)
    bolivia.paisesLimitrofes.add(brasil)

    //Bloques regionales
    argentina.bloquesRegionales.add("OEA")
    brasil.bloquesRegionales.add("OEA")
    uruguay.bloquesRegionales.add("OEA")
    bolivia.bloquesRegionales.add("OEA")



    describe("Son plurinacionales") {
        argentina.esPlurinacional().shouldBeFalse()
        brasil.esPlurinacional().shouldBeFalse()
        uruguay.esPlurinacional().shouldBeFalse()
        bolivia.esPlurinacional().shouldBeTrue()
    }

    describe("Son islas") {

    }

    describe("Densidad poblacional") {

    }

    describe("Vecino mas poblado") {

    }

    describe("Son Limitrofes") {

    }

    describe("Necesitan traduccion") {

    }

    describe("Son potenciales aliados") {

    }

    describe("Conviene ir de compras") {

    }

    describe("Equivalencia monedas") {

    }


})