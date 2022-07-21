package ar.edu.unahur.obj2.impostoresPaises

import Observatorio
import Pais
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe


class PaisesTest: DescribeSpec ({

    val argentina = Pais("Argentina", "ARG", 45380000, 2780000.0, "America", "SRA", 128.22)

    val brasil = Pais("Brasil", "BRA", 212600000, 851600000.0, "America", "BRL", 5.40)

    val uruguay = Pais("Uruguay", "URY", 3474000, 176215.0, "America", "UYU", 41.09)

    val bolivia = Pais("Bolivia", "BOL", 11670000, 1099000000.0, "America", "BOB", 6.88)

    val cuba = Pais("Cuba", "CUB", 11330000, 113860.0, "America", "CUP", 23.96)

    //Idiomas Paises
    argentina.idiomasOficiales.add("Español")
    brasil.idiomasOficiales.add("Portugues")
    uruguay.idiomasOficiales.add("Español")
    bolivia.idiomasOficiales.add("Español")
    bolivia.idiomasOficiales.add("Aimara")
    bolivia.idiomasOficiales.add("guaraní")
    cuba.idiomasOficiales.add("Español")

    //Paises Limitrofes
    argentina.paisesLimitrofes.add(uruguay)
    argentina.paisesLimitrofes.add(bolivia)
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
    cuba.bloquesRegionales.add("UNASUR")



    describe("Son plurinacionales") {
        argentina.esPlurinacional().shouldBeFalse()
        brasil.esPlurinacional().shouldBeFalse()
        uruguay.esPlurinacional().shouldBeFalse()
        bolivia.esPlurinacional().shouldBeTrue()
        cuba.esPlurinacional().shouldBeFalse()
    }

    describe("Son islas") {
        argentina.esIsla().shouldBeFalse()
        brasil.esIsla().shouldBeFalse()
        uruguay.esIsla().shouldBeFalse()
        bolivia.esIsla().shouldBeFalse()
        cuba.esIsla().shouldBeTrue()
    }


    describe("Vecino mas poblado") {
        argentina.vecinoMasPoblado().shouldBe(argentina)
        brasil.vecinoMasPoblado().shouldBe(brasil)
        uruguay.vecinoMasPoblado().shouldBe(brasil)
        bolivia.vecinoMasPoblado().shouldBe(brasil)
        cuba.vecinoMasPoblado().shouldBe(cuba)
    }

    describe("Son Limitrofes") {
        argentina.esLimitrofeDe(bolivia).shouldBeTrue()
        argentina.esLimitrofeDe(brasil).shouldBeFalse()
    }

    describe("Necesitan traduccion") {
        argentina.necesitaTraduccion(brasil).shouldBeTrue()
        argentina.necesitaTraduccion(uruguay).shouldBeFalse()
    }

    describe("Son potenciales aliados") {
        argentina.aliadoPotencial(brasil).shouldBeFalse()
        argentina.aliadoPotencial(uruguay).shouldBeTrue()
        argentina.aliadoPotencial(cuba).shouldBeFalse()
    }

    describe("Conviene ir de compras") {
        argentina.convieneIrDeCompras(brasil).shouldBeFalse()
        brasil.convieneIrDeCompras(argentina).shouldBeTrue()
    }

    describe("Equivalencia monedas") {
        argentina.aCuantoEquivale(brasil, 100.0).shouldBe(4.21)
    }

})