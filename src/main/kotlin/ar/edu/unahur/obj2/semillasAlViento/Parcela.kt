package ar.edu.unahur.obj2.semillasAlViento

class Parcela(val ancho: Int, val largo: Int, val horasSolPorDia: Int) {
    val plantas = mutableListOf<Planta>()

    fun superficie() = ancho * largo

    fun cantidadMaximaPlantas() =
        if (ancho > largo) superficie() / 5 else superficie() / 3 + largo

    fun plantar(planta: Planta) {
        if (cantidadDePlantas() == this.cantidadMaximaPlantas()) {
            Error("Ya no hay lugar en esta parcela")
        } else if (horasSolPorDia > planta.horasDeSolQueTolera() + 2) {
            Error("No se puede plantar esto acá, se va a quemar")
        } else {
            plantas.add(planta)
        }
    }

    fun parcelaTieneComplicaciones() = this.plantas.any { it.horasDeSolQueTolera() < this.horasSolPorDia }

    fun parcelaSemillera() = plantas.all { it.daSemillas() }

    fun cantidadDePlantas() = plantas.count()
}

class Agricultora(val parcelas: List<Parcela>) {

    fun parcelasSemilleras() = parcelas.filter { it.parcelaSemillera() }

    fun obtenerParcelaEstrategica() = parcelas.maxBy { it.cantidadMaximaPlantas() - it.cantidadDePlantas() }!!

    fun plantarEstrategicamente(planta: Planta) {
        obtenerParcelaEstrategica().plantar(planta)
    }
}
