package ar.edu.unahur.obj2.semillasAlViento

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class SemillasTest : DescribeSpec ({

    var parcela  = Parcela(10,10,8)
    var parcelaAncha = Parcela(10,5,10)
    var parcelaLarga = Parcela(5,10,15)

    var menta = Menta(2019,0.5f)//explicitamente decir que es float. Si no deberia ser double y seria lo mismo?


    describe("Parcela") {


        it("Calcular superficie") {
            parcela.superficie().shouldBe(100)
        }

        it("Cantidad maxima de plantas cuando la parcela es mas ancha que larga"){
            parcelaAncha.cantidadMaximaPlantas().shouldBe(10)
        }

        it("Cantidad maxima de plantas cuando la parcela es mas larga que ancha"){
            parcelaLarga.cantidadMaximaPlantas().shouldBe(26)
        }

        it("Plantar en parcela"){
            parcela.plantar(menta)
            parcela.cantidadPlantas.shouldBe(1)
        }
    }


    describe("Parcela agricultora") {

        var listaDeParcelas = mutableListOf<Parcela>()
        var agricultora = Agricultora(listaDeParcelas)

        it("Comprar parcela") {
           agricultora.comprarParcela(parcela)
           listaDeParcelas.count().shouldBe(1)
        }

        it("Parcelas semilleras"){
            agricultora.parcelasSemilleras().count().shouldBe(0)
        }

        it("Plantar estrategicamente"){
            agricultora.comprarParcela(parcelaAncha)
            agricultora.plantarEstrategicamente(menta)
            // Esto falla porque no se usa el metodo plantar y por ende no se actualiza la variable que
            // contiene la cantidad de plantas
            agricultora.parcelas.first().cantidadPlantas.shouldBe(1)
        }

    }

})